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

package org.azrul.epice.web;

import com.sun.data.provider.impl.ObjectListDataProvider;
import java.util.ArrayList;
import java.util.List;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.dao.query.factory.SearchItemsQueryFactory;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class SearchItemsQueryDataProvider extends ObjectListDataProvider{
     public SearchItemsQueryDataProvider(){
        List<SearchItemsQuery> items = new ArrayList<SearchItemsQuery>();
        items.add(SearchItemsQueryFactory.getInstance());
        setList(items);
    }
}