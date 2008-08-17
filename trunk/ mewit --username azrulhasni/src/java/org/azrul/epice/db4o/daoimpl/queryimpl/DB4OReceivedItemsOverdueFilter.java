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

import org.azrul.epice.dao.query.ItemsFilter;
import java.util.GregorianCalendar;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OReceivedItemsOverdueFilter implements ItemsFilter {

   private String type = null;
    
    @Override
    public String getType() {
        return type;
    }
    
    public DB4OReceivedItemsOverdueFilter (){
        type = "RECEIVED_ITEMS_OVERDUE";
    }

    public boolean filter(Item item, Person user) {
        final GregorianCalendar today = ((GregorianCalendar) GregorianCalendar.getInstance());
        final GregorianCalendar deadline = ((GregorianCalendar) GregorianCalendar.getInstance());
        if (item.getDeadLine() == null) {
            return false;
        }
        deadline.setTime(item.getDeadLine());
        return item.getToUser().equals(user) && today.after(deadline) && (("SENT-UNCONFIRMED").equals(item.getStatus()) || ("SENT-NEGOTIATED").equals(item.getStatus()) || ("SENT-ACCEPTED").equals(item.getStatus()) || ("NEED-REDO").equals(item.getStatus()) || ("DELEGATED").equals(item.getStatus()) || ("NEED-REDO DELEGATED").equals(item.getStatus()));  
    }
}