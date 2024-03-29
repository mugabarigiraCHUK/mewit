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
public class JPAReceivedItemsNeedRedoFilter implements JPAItemsFilter {
    private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public JPAReceivedItemsNeedRedoFilter (){
        type = "RECEIVED_ITEMS_NEED_REDO";
    }
   
    public boolean filter(Item item, String user) {
        return (item.getDeadLine() != null && item.getRecipient().equals(user) && (item.getStatus().equals(Item.Status.NEED_REDO) || item.getStatus().equals(Item.Status.NEED_REDO_DELEGATED)));
    }

    public JPACriteria filter(String user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
