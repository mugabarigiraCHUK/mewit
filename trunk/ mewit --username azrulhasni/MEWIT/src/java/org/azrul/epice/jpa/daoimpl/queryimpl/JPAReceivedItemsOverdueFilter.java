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
public class JPAReceivedItemsOverdueFilter implements JPAItemsFilter {

   private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public JPAReceivedItemsOverdueFilter (){
        type = "RECEIVED_ITEMS_OVERDUE";
    }

    public boolean filter(Item item, String user) {
        final GregorianCalendar today = ((GregorianCalendar) GregorianCalendar.getInstance());
        final GregorianCalendar deadline = ((GregorianCalendar) GregorianCalendar.getInstance());
        if (item.getDeadLine() == null) {
            return false;
        }
        deadline.setTime(item.getDeadLine());
        return item.getRecipient().equals(user) && today.after(deadline) && ((Item.Status.UNCONFIRMED).equals(item.getStatus()) || (Item.Status.NEGOTIATED).equals(item.getStatus()) || (Item.Status.ACCEPTED).equals(item.getStatus()) || (Item.Status.NEED_REDO).equals(item.getStatus()) || (Item.Status.DELEGATED).equals(item.getStatus()) || (Item.Status.NEED_REDO_DELEGATED).equals(item.getStatus()));  
    }

    public JPACriteria filter(String user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}