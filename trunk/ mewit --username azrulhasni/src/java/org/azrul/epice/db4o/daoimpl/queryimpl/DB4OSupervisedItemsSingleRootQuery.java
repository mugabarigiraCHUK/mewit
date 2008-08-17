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

import org.azrul.epice.dao.query.SupervisedItemsSingleRootQuery;
import org.azrul.epice.dao.query.SortBySentDate;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OSupervisedItemsSingleRootQuery implements SupervisedItemsSingleRootQuery {
    private Item root = null;
    public DB4OSupervisedItemsSingleRootQuery() {
    }
    
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

    private List<Item> run(Person _user,  ObjectContainer db) throws Exception {
        //Note: always ignore archive property of item when dealing with supervised items
       
        List<Item> res = new ArrayList<Item>();
        ObjectSet<Person> os = db.get(_user);
        if (os.hasNext()) {
            final Person supervisor = (Person) os.next();
            if (root == null) {
                return res;
            }

            if (root.getId() == null) {
                return res;
            }

            List<Item> items = db.query(new Predicate<Item>() {
                @Override
                public boolean match(Item item) {
                    //A =  item.getId().contains(root.getId()) --> get all items that involves root
                    //B =  item.getSupervisors().contains(supervisor) --> get all item for whom the current user is the supervisor 
                    //result = A && B 
                    if (item.getDeadLine() != null && item.getId().contains(root.getId()) && item.getSupervisors().contains(supervisor)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            res.addAll(items);
            Collections.sort(res, new SortBySentDate());
        }
        return res;

    }

    public int count(Person _user,ObjectContainer db) throws Exception {
        return -1;
    }

    public String getDescription() {
        return "Supervised items";
    }

    public Item getRoot() {
        return root;
    }

    public void setRoot(Item root) {
        this.root = root;
    }
}
    