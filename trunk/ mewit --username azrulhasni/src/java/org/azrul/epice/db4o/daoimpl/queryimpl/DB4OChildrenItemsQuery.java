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

import com.db4o.ObjectContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.azrul.epice.dao.query.ChildrenItemsQuery;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OChildrenItemsQuery implements ChildrenItemsQuery{
    private Item parentItem;
   
    
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
    
    private List<Item> run(Person _user, ObjectContainer db) throws Exception {
        //archived state of items are not taken into account since it might break tree structure
        Item parent = new Item();
        parent.setId(parentItem.getId());
        parent = (Item) db.get(parent).next();
        List<Item> list = new ArrayList<Item>();
        list.addAll(parent.getChildren());
        return list;
    }

    public String getDescription() {
        return "Get children of an item";
    }

    public Item getParentItem() {
        return parentItem;
    }

    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }
    
   

}
