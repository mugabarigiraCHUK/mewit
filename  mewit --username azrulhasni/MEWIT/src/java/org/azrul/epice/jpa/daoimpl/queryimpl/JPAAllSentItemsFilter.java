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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.db4o.daoimpl.queryimpl.DB4OItemsFilter;
import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class JPAAllSentItemsFilter implements JPAItemsFilter {
    private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public JPAAllSentItemsFilter(){
        type = "ALL_SENT_ITEMS";
    }
    
   public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem){
        
        return cb.equal(ritem.get("sender").as(String.class),user);
    }

    public boolean filter(Item item, String user) {
        return (item.getSender().equals(user));
    }
   

}
