  /* Copyright (C) 2008 Azrul Hasni MADISA, Abu Mansur MANAF  
  *   
  * This program is distributed in the hope that it will be useful,  
  * but WITHOUT ANY WARRANTY; without even the implied warranty of  
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
  * GNU General Public License for more details.  
  *   
  * You should have received a copy of the GNU General Public License  
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.  
  */ 

package org.azrul.epice.db4o.daoimpl.queryimpl;

import org.azrul.epice.dao.query.SearchItemsQuery;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.dao.query.SortBySentDate;
import org.azrul.epice.db4o.daoimpl.DB4OItemDAO;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.ItemIndexer;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OSearchItemsQuery implements SearchItemsQuery {

    private String id = null;
    private Person owner = null;
    private String searchTerm = null;
    private List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
    private boolean referenceOnly;
    private transient ItemDAO itemDAO = new DB4OItemDAO();
    private boolean archiveIncluded;
    
    public List<Item> run(Person _user){
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            List<Item> ritems = new ArrayList<Item>(run(_user, db));
            return ritems;
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return new ArrayList<Item>();
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    private List<Item> run(final Person _user, ObjectContainer db) throws Exception {
        int state = 0; // state ==0 : get all, state ==1: search
        if (getSearchTerm() != null) {
            if (getSearchTerm().equals("")) {
                state = 0;
            } else {
                state = 1;
            }
        } else {
            state = 0;
        }
        List<Item> ritems = new ArrayList<Item>();
        List<Item> _items = new ArrayList<Item>();
        if (state == 1) { //if the search term is not empty, we will do a search ...

            CompassSession session = ItemIndexer.getCompass().openSession();
            CompassTransaction tx = null;
            try {
                tx = session.beginTransaction();
                CompassHits hits = session.find(getSearchTerm());
                for (int i = 0; i < hits.length(); i++) {
                    _items.add((Item) hits.data(i));
                }
            } catch (CompassException ce) {
                if (tx != null) {
                    if (!tx.wasCommitted() && !tx.wasRolledBack()) {
                        tx.rollback();
                    }
                }
                LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ce);

            } finally {
                session.close();
            }
        } else { //if the search term is empty, then we will get all items ...
            _items.addAll(db.query(new Predicate<Item>() {
                @Override
                public boolean match(Item item) {
                    return (item.getToUser().equals(_user) || item.getFromUser().equals(_user) || item.getSupervisors().contains(_user));
                }
            }));

        }
        //... sort it according to date
        Collections.sort(_items, new SortBySentDate());
        //... and refresh + apply filters to the result
        ritems = itemDAO.refreshAndApplyFilters(_items, _user, filters);
        //next, all refreshed items will be processed to determine its type.
        List<Item> items = new ArrayList<Item>();
        for (Item ritem : ritems) {
            if (archiveIncluded) {
                 //labelling (archived or not) if archive state should be shown
                if (_user.equals(ritem.getFromUser())) { //item is a sent object
                    if (ritem.isArchivedForSender() == true) { //if item is archived
                        ritem.setArchived(true);
                    } else {
                        ritem.setArchived(false);
                    }
                } else if (_user.equals(ritem.getToUser())) {
                    if (ritem.isArchivedForRecipient() == true) {
                        ritem.setArchived(true);
                    } else {
                        ritem.setArchived(false);
                    }
                } else { //must be supervised

                    ritem.setArchived(false);
                }
            } else {
                //... else exclude archived items
                if (ritem.isArchivedForRecipient() == true) {//we are excluding archived for reicipient

                    if (ritem.getToUser().equals(_user)) { //item is a received item

                        continue; //exclude

                    }
                }
                if (ritem.isArchivedForSender() == true) {
                    if (ritem.getFromUser().equals(_user)) {
                        continue;
                    }
                }
            }

            if (referenceOnly == true) { //we want only references
                if (ritem.getDeadLine() != null) { //... but this item is not a reference
                    continue; //skip it
                }
            }


            int itemTypeState = 0;

            //received item
            if (ritem.getToUser().equals(_user)) {
                itemTypeState = 2;
            } else {
                itemTypeState = 1;
            }

            //sent item
            if (ritem.getFromUser().equals(_user)) {
                if (itemTypeState == 2) {
                    itemTypeState = 3;
                } else {
                    itemTypeState = 5;
                }
            } else {
                if (itemTypeState == 2) {
                    itemTypeState = 4;
                } else {
                    itemTypeState = 6;
                }
            }
            //supervised
            boolean isSupervisor = false;
            for (Person p : ritem.getSupervisors()) {
                if (_user.equals(p)) {
                    isSupervisor = true;
                    break;
                }
            }

            if (isSupervisor) {
                if (itemTypeState == 6) {
                    itemTypeState = 7;
                } else if (itemTypeState == 5) {
                    itemTypeState = 9;
                }
            } else {
                if (itemTypeState == 6) {
                    itemTypeState = 8;
                }
            }
            
            //do another labelling on item's type
            if (itemTypeState == 3) {
                if (ritem.getDeadLine() == null) {
                    ritem.setType("REFERENCE - TO YOURSELF");
                } else {
                    ritem.setType("TO YOURSELF");
                }
                items.add(ritem);
            } else if (itemTypeState == 4) {
                if (ritem.getDeadLine() == null) {
                    ritem.setType("REFERENCE - RECEIVED");
                } else {
                    ritem.setType("RECEIVED");
                }
                items.add(ritem);
            } else if (itemTypeState == 5) {
                if (ritem.getDeadLine() == null) {
                    ritem.setType("REFERENCE - SENT");
                } else {
                    ritem.setType("SENT");
                }
                items.add(ritem);
            } else if (itemTypeState == 7) {
                if (ritem.getDeadLine() == null) {
                    ritem.setType("REFERENCE - SUPERVISED");
                } else {
                    ritem.setType("SUPERVISED");
                }
                items.add(ritem);
            } else if (itemTypeState == 9) {
                if (ritem.getDeadLine() == null) {
                    ritem.setType("REFERENCE - SENT AND SUPERVISED");
                } else {
                    ritem.setType("SENT AND SUPERVISED");
                }
                items.add(ritem);
            }
        }
        return items;
    }

    public String getDescription() {
        return "Search items";
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Person getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        DB4OSearchItemsQuery q = (DB4OSearchItemsQuery) o;
        return this.getId().equals(q.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    public boolean isReferenceOnly() {
        return referenceOnly;
    }

    public void setReferenceOnly(boolean referenceOnly) {
        this.referenceOnly = referenceOnly;
    }

    @Override
    public List<ItemsFilter> getFilters() {
        return filters;
    }

    @Override
    public void setFilters(List<ItemsFilter> queries) {
        this.filters = queries;
    }

    @Override
    public boolean isArchiveIncluded() {
        return archiveIncluded;
    }

    @Override
    public void setArchiveIncluded(boolean archiveIncluded) {
        this.archiveIncluded = archiveIncluded;
    }
}
