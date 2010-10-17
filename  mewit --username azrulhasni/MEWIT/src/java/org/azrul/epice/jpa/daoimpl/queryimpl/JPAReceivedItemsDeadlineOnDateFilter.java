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

package org.azrul.epice.jpa.daoimpl.queryimpl;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class JPAReceivedItemsDeadlineOnDateFilter implements JPAItemsFilter {
    private Date onDate = null;
    private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public JPAReceivedItemsDeadlineOnDateFilter (){
        type = "RECEIVED_ITEMS_DEADLINE_TODAY";
    }

    public JPAReceivedItemsDeadlineOnDateFilter (Date onDate){
        type = "RECEIVED_ITEMS_DEADLINE_TODAY";
        this.onDate = onDate;
    }
    
 
    public boolean filter(Item item, String user) {
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

    public JPACriteria filter(String user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

