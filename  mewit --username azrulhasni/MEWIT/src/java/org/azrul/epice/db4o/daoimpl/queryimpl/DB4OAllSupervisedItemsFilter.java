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

import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OAllSupervisedItemsFilter implements DB4OItemsFilter {
    private String type = null;
    
    
    public String getType() {
        return type;
    }
    
    public DB4OAllSupervisedItemsFilter(){
        type = "ALL_SUPERVISED_ITEMS";
    }

    

     public DB4OItemPredicate filter(final Item item, final String supervisor){
        Predicate predicate = new Predicate() {

            
            public boolean match(Object et) {
                return booleanFilter(item,supervisor);
            }
        };
        DB4OItemPredicate itemPredicate = new DB4OItemPredicate();
        itemPredicate.set(predicate);
        return itemPredicate;
    }

     public boolean booleanFilter(Item item, String supervisor) {
        return item.getSupervisors().contains(supervisor);
    }
    
   
}
