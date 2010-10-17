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
import java.util.Date;
import java.util.GregorianCalendar;
import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OReceivedItemsDeadlineOnDateFilter implements DB4OItemsFilter {
    private Date onDate = null;
    private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public DB4OReceivedItemsDeadlineOnDateFilter (){
        type = "RECEIVED_ITEMS_DEADLINE_ON_DATE";
    }

    public DB4OReceivedItemsDeadlineOnDateFilter (Date onDate){
        type = "RECEIVED_ITEMS_DEADLINE_ON_DATE";
        this.onDate = onDate;
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
        if (item.getDeadLine() == null) {
            return false;
        }
        GregorianCalendar calOnDate = (GregorianCalendar) GregorianCalendar.getInstance();
        if (onDate != null){
            calOnDate.setTime(onDate);
        }
        GregorianCalendar calItem = (GregorianCalendar) GregorianCalendar.getInstance();
        calItem.setTime(item.getDeadLine());
        return (calItem.get(GregorianCalendar.DAY_OF_YEAR) == calOnDate.get(GregorianCalendar.DAY_OF_YEAR) &&
                    calItem.get(GregorianCalendar.YEAR) == calOnDate.get(GregorianCalendar.YEAR) &&
                    item.getRecipient().equals(user) &&
                    item.isArchivedForRecipient() == false &&
                    (   (Item.Status.UNCONFIRMED).equals(item.getStatus()) || 
                        (Item.Status.NEGOTIATED).equals(item.getStatus()) || 
                        (Item.Status.ACCEPTED).equals(item.getStatus()) || 
                        (Item.Status.NEED_REDO).equals(item.getStatus()) || 
                        (Item.Status.DELEGATED).equals(item.getStatus()) || 
                        (Item.Status.NEED_REDO_DELEGATED).equals(item.getStatus())));
        

    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }
}

