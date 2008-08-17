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
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OAllSupervisedItemsFilter implements ItemsFilter {
    private String type = null;
    
    @Override
    public String getType() {
        return type;
    }
    
    public DB4OAllSupervisedItemsFilter(){
        type = "ALL_SUPERVISED_ITEMS";
    }

    

    @Override
    public boolean filter(Item item, Person supervisor) {
        return item.getSupervisors().contains(supervisor);
    }
}
