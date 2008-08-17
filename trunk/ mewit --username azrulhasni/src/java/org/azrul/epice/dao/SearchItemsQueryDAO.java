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


package org.azrul.epice.dao;


import org.azrul.epice.db4o.daoimpl.queryimpl.DB4OSearchItemsQuery;
import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.dao.query.SearchItemsQuery;
import java.util.List;
import org.azrul.epice.dao.query.SearchItemsQueryQuery;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul Hasni MADISA
 */
public interface SearchItemsQueryDAO {
    public List<SearchItemsQuery> runItemsQueryQuery(Person user, SearchItemsQueryQuery query);
    public void delete(final SearchItemsQuery query);
    public void saveQuery(Person  _user, SearchItemsQuery query);
    public DB4OSearchItemsQuery refresh(final SearchItemsQuery query);
}
