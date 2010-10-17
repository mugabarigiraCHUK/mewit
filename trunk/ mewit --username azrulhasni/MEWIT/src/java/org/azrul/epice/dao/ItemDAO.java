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

import org.azrul.epice.dao.query.QueryResult;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Item.Priority;

/**
 *
 * @author Azrul Hasni MADISA
 */
public interface ItemDAO {
   Item updateFromFreshItem(Item newItem, String username);

   Item updateFromFreshItem(Item newItem, String username, FileRepository fileRepository);

   //Item copyFromInterfaceToDB(Item newItem, String username);

   Set<Item> createItems(String user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject,Date startDate, Date deadline, boolean mergeParentChildSupervisors, List<String> fileNames, Priority priority);

   Set<Item> createItems(String user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject,Date startDate, Date deadline, boolean mergeParentChildSupervisors, FileRepository fileRepository, Priority priority);


   //Set<Item> createItems(String user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject,Date startDate, Date deadline, boolean mergeParentChildSupervisors, FileRepository fileRepo);
    
    Item findItemById(String id);

    List<Item> findItemsById(List<String> itemIds);
    
    Item refresh(Item item);
    
    List<Item> refreshAll(List<Item> items);

    QueryResult runItemsQuery(String user, SearchItemsQuery query, int startRow, int resultCount);

    Item update(Item _item, String currentUser, boolean accept, boolean negotiate, boolean reject, Date negotiatedDeadline, String reasonNego, String reasonReject, String feedback, boolean feedbackApproved, String comments,Date startDate,Date endDate,  Priority priority);

    boolean labelAndFilterForArchive(final boolean _archiveIncluded, final boolean _referenceOnly, final String _user, Item ritem);

    void labelWithType(Item ritem,final String _user);

    void delete(Item item);
   
    Item addTags(final Item item, Set<String> tags);
    
    Item removeTag(final Item _item, String tag);
    
    List<Item> archiveItems(final String user,final List<Item> items);

    List<Item>  unarchiveItems(final String currentUser, final List<Item> items);
    
//    Set<String> getAllTags(final String currentUser);

    Item updatePriority(Item _item, Priority newPriority);

    Item createItem(String currentUser,String itemId, String fileRepoId);
}
