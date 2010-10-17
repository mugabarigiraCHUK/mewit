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


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.domain.Item;


/**
 *
 * @author Azrul Hasni MADISA
 */
public class JPASentItemsDoneUnconfirmedFilter implements JPAItemsFilter {
     private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public  JPASentItemsDoneUnconfirmedFilter(){
        type = "SENT_ITEMS_DONE_UNCONFIRMED";
    }
   

    public boolean filter(Item item, String user) {
        return (item.getDeadLine() != null && item.getSender().equals(user) && item.getStatus().equals(Item.Status.DONE_UNCONFIRMED));
    }

    public JPACriteria filter(String user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
