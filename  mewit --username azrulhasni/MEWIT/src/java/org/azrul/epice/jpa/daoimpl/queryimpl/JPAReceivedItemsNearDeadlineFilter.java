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

import java.util.GregorianCalendar;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class JPAReceivedItemsNearDeadlineFilter implements JPAItemsFilter {
    private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public JPAReceivedItemsNearDeadlineFilter (){
        type = "RECEIVED_ITEMS_NEAR_DEADLINE";
    }

    public boolean filter(Item item, String user) {
        GregorianCalendar today = ((GregorianCalendar) GregorianCalendar.getInstance());
        GregorianCalendar deadline = ((GregorianCalendar) GregorianCalendar.getInstance());
        GregorianCalendar dateInXDays = ((GregorianCalendar) GregorianCalendar.getInstance());
        dateInXDays.add(GregorianCalendar.DAY_OF_YEAR, 2);

        if (item.getDeadLine() != null) {
            deadline.setTime(item.getDeadLine());
            return item.getRecipient().equals(user) && dateInXDays.after(deadline) && today.before(deadline) && ((Item.Status.UNCONFIRMED).equals(item.getStatus()) || (Item.Status.NEGOTIATED).equals(item.getStatus()) || (Item.Status.ACCEPTED).equals(item.getStatus()) || (Item.Status.NEED_REDO).equals(item.getStatus()) || (Item.Status.DELEGATED).equals(item.getStatus()) || (Item.Status.NEED_REDO_DELEGATED).equals(item.getStatus()));
        } else {
            return false;
        }

    }

    public JPACriteria filter(String user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}