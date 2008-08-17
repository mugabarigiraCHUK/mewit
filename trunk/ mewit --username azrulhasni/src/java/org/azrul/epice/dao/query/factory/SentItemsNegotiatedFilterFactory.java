   
 /*  
  * SentItemsNegotiatedFilterFactory.java  
  *   
  * Copyright (C) 2008 Azrul Hasni MADISA, Abu Mansur MANAF  
  *   
  * This program is distributed in the hope that it will be useful,  
  * but WITHOUT ANY WARRANTY; without even the implied warranty of  
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
  * GNU General Public License for more details.  
  *   
  * You should have received a copy of the GNU General Public License  
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.  
  */  
   
package org.azrul.epice.dao.query.factory;

import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.db4o.daoimpl.queryimpl.DB4OSentItemsNegotiatedFilter;

/**
 *
 * @author Azrul
 */
public class SentItemsNegotiatedFilterFactory {
    public static ItemsFilter getInstance(){
        return new DB4OSentItemsNegotiatedFilter();
    }
}
