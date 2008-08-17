   
 /*  
  * SearchItemsQuery.java  
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
   
package org.azrul.epice.dao.query;

import java.util.List;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul
 */
public interface SearchItemsQuery extends ItemsQuery{
    List<ItemsFilter> getFilters();
    String getId();
    Person getOwner();
    String getSearchTerm();
    boolean isReferenceOnly();
    void setFilters(List<ItemsFilter> queries);
    void setId(String id);
    void setOwner(Person owner);
    void setReferenceOnly(boolean referenceOnly);  
    void setArchiveIncluded(boolean archiveIncluded);
    boolean isArchiveIncluded();
    void setSearchTerm(String searchTerm);
}
