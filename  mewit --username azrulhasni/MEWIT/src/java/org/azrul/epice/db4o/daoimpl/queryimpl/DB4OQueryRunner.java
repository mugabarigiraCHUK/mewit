/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.db4o.daoimpl.queryimpl;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.azrul.epice.dao.ItemDAO;

import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.dao.query.SortBySentDate;
import org.azrul.epice.db4o.daoimpl.DB4OItemDAO;
import org.azrul.epice.domain.Item;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author azrulhasni
 */
public class DB4OQueryRunner {
    private ItemDAO itemDAO;

    public DB4OQueryRunner(){
        itemDAO = new DB4OItemDAO();
    }

    public List<Item> run(SearchItemsQuery query, String _user) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            List<Item> ritems = new ArrayList<Item>(run(query,_user, db));
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

      public List<Item> refreshAndApplyANDFilters(List<Item> items,
            final String currentUser,
            final List<DB4OItemsFilter> filters) {
        List<Item> refreshed = new ArrayList<Item>();
        DB4OFilterGroup filterGroup = new DB4OFilterGroup();
        filterGroup.setFilters(filters);
        filterGroup.setUseOR(false);
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            for (final Item _i : items) {
                if (_i == null) {
                    continue;
                }
                if (_i.getId() == null) {
                    continue;
                }

                ObjectSet<Item> os = db.query(filterGroup.filter(_i, currentUser).get());

                refreshed.addAll(os);
            }
            return refreshed;
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public List<Item> refreshAndApplyORFilters(List<Item> items,
            final String currentUser,
            final List<DB4OItemsFilter> filters) {
        List<Item> refreshed = new ArrayList<Item>();
        DB4OFilterGroup filterGroup = new DB4OFilterGroup();
        filterGroup.setFilters(filters);
        filterGroup.setUseOR(true);
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            for (final Item _i : items) {
                if (_i == null) {
                    continue;
                }
                if (_i.getId() == null) {
                    continue;
                }
                ObjectSet<Item> os = db.query(filterGroup.filter(_i,currentUser).get());

                refreshed.addAll(os);
            }
            return refreshed;
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    private List<Item> labelWithTypeAndFilterArchived(SearchItemsQuery query, List<Item> ritems, final String _user) {
        //next, all refreshed items will be processed to determine its type.
        List<Item> items = new ArrayList<Item>();
        for (Item ritem : ritems) {
            if (ritem != null) {
                if (getItemDAO().labelAndFilterForArchive(query.isArchiveIncluded(), query.isReferenceOnly(), _user, ritem)) {
                    continue;
                }

                getItemDAO().labelWithType(ritem, _user);

//                //label children if any
//                List<Item> children = new ArrayList<Item>();
//                children.addAll(ritem.getChildren());
//                labelWithTypeAndFilterArchived(query,children, _user);
                items.add(ritem);
            }
        }
        return items;
    }

    private List<Item> run(final SearchItemsQuery query, final String _user, ObjectContainer db) throws Exception {
        return run(query,_user, db, query.getUseOR());
    }

    private List<Item> run(final SearchItemsQuery query, final String _user, ObjectContainer db, boolean useOR) throws Exception {
        int state = 0; // state ==0 : get all, state ==1: search
        if (query.getSearchTerm() != null) {
            if (query.getSearchTerm().equals("")) {
                state = 0;
            } else {
                state = 1;
            }
        } else {
            state = 0;
        }
        List<Item> _items = new ArrayList<Item>();
        List<DB4OItemsFilter> itemsFilters = DB4OItemsFilterFinder.get(query.getFilters(), query.getParameters());
        final DB4OFilterGroup filterGroup = new DB4OFilterGroup();
        filterGroup.setFilters(itemsFilters);
        filterGroup.setUseOR(query.getUseOR());
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (state == 1) { //if the search term is not empty, we will do a search ...
            final Pattern regexPattern = Pattern.compile(query.getSearchTerm(), Pattern.CASE_INSENSITIVE);

            _items.addAll(db.query(new Predicate<Item>() {
                
                public boolean match(Item item) {
                    if (filterGroup.booleanFilter(item, _user)) {
                        Matcher regexMatcher = regexPattern.matcher(item.getSubject() + " " + item.getTask() + " " + item.getSender() + " " + item.getRecipient() + " " + sdf.format(item.getDeadLine()) + " " + sdf.format(item.getStartDate()));
                        regexMatcher.useAnchoringBounds(false);
                        return regexMatcher.find();
                    } else {
                        return false;
                    }
                }
            }));
        } else { //if the search term is empty, then we will get all items ...
            _items.addAll(db.query(new Predicate<Item>() {
                
                public boolean match(Item item) {
                    return filterGroup.booleanFilter(item, _user);
                }
            }));

        }
        //... sort it according to date
        Collections.sort(_items, new SortBySentDate());
     
        List<Item> items = labelWithTypeAndFilterArchived(query,_items, _user);
        return items;
    }

    /**
     * @return the itemDAO
     */
    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    /**
     * @param itemDAO the itemDAO to set
     */
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
}
