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

import com.db4o.query.Predicate;

import java.util.GregorianCalendar;
import org.azrul.epice.domain.Item;


/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OSentItemsNearDeadlineFilter implements DB4OItemsFilter {
     private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public DB4OSentItemsNearDeadlineFilter(){
        type = "SENT_ITEMS_NEAR_DEADLINE";
    }
    

      public DB4OItemPredicate filter(final Item item, final String user){
        Predicate predicate = new Predicate() {

            
            public boolean match(Object et) {
                return booleanFilter(item,user);
            }
        };
        DB4OItemPredicate itemPredicate = new DB4OItemPredicate();
        itemPredicate.set(predicate);
        return itemPredicate;
    }

     public boolean booleanFilter(Item item, String user) {
        GregorianCalendar today = ((GregorianCalendar) GregorianCalendar.getInstance());
        GregorianCalendar deadline = ((GregorianCalendar) GregorianCalendar.getInstance());
        GregorianCalendar dateInXDays = ((GregorianCalendar) GregorianCalendar.getInstance());
        dateInXDays.add(GregorianCalendar.DAY_OF_YEAR, 2);
        if (item.getDeadLine() == null) {
            return false;
        }
        deadline.setTime(item.getDeadLine());
        return item.getSender().equals(user) &&
                dateInXDays.after(deadline) &&
                today.before(deadline) &&
                ((Item.Status.UNCONFIRMED==item.getStatus()) ||
                 (Item.Status.NEGOTIATED==item.getStatus()) ||
                 (Item.Status.ACCEPTED==item.getStatus()) ||
                 (Item.Status.NEED_REDO==item.getStatus()) ||
                 (Item.Status.DELEGATED==item.getStatus()) ||
                 (Item.Status.NEED_REDO_DELEGATED==item.getStatus()));
    }
}
