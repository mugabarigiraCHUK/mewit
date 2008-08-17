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

import org.azrul.epice.dao.query.ItemsFilter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.azrul.epice.dao.query.ItemsQuery;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul Hasni MADISA
 */
public interface ItemDAO {

    Set<Item> createItems(Person user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject, Date deadline, boolean mergeParentChildSupervisors, FileRepository fileRepo);

    Set<Item> createItemsWithoutPersisting(Person user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject, Date deadline, boolean mergeParentChildSupervisors, FileRepository fileRepo);

    
    Item findItemById(String id);

    Item refresh(Item item);
    
    List<Item> refreshAll(List<Item> items);
    
     public List<Item> refreshAndApplyFilters(List<Item> items, final Person currentUser, final List<ItemsFilter> queries) ;

    List<Item> runItemsQuery(Person user, ItemsQuery query);
    
    int runCountItems(Person user,ItemsFilter filter);
    
    Item updateReceivedItem(final Item _receivedItem, final String acceptReject, final Date selectedDate, final String reasonNegoReject, final String feedback);
    
    Item updateSentItem(final Item _sentItem, Date negotiatedDeadLine, Boolean isApproved, String comments);
    
    void delete(Item item);
    
    //void deleteAll(final List<Item> _items);
    
    Item addTags(final Item item, Set<String> tags);
    
    Item removeTag(final Item _item, String tag);
    
    void archiveItems(final Person user,final List<Item> items);

    void  unarchiveItems(final Person currentUser, final List<Item> items);
    
    Set<String> getAllTags();
}
