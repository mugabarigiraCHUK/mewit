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


public class DB4OAllReceivedItemsFilter implements ItemsFilter {
    private String type = null;
    
    @Override
    public String getType() {
        return type;
    }
    
    public DB4OAllReceivedItemsFilter(){
        type = "ALL_RECEIVED_ITEMS";
    }
    
    @Override
    public boolean filter(Item item,Person user) {
            return (item.getToUser().equals(user));
    }

   
}
