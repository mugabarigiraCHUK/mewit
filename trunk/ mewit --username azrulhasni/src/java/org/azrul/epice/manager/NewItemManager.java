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
package org.azrul.epice.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.SendMailUtil;

/**
 *
 * @author Azrul
 */
public class NewItemManager {

    private ItemDAO itemDAO = ItemDAOFactory.getInstance();

    public Set<Item> createAndSentNewItems(Person currentUser, String toEmails, String supervisors, String tags, Date deadLineDate, String action, String subject, FileRepository currentFileRepository, Item parentItem, List<String> linkList) {
        ResourceBundle props = ResourceBundle.getBundle("epice");
        String receiptSubject = props.getString("EMAIL_RECEIPT_TITLE");

        Set<Item> newItems = createNewItems(true, currentUser, toEmails, supervisors, tags, deadLineDate, action, subject, currentFileRepository, parentItem, linkList);
        for (Item item : newItems) {
            if (item.getToUser().getOkToReceiveEmail()==null){
                continue;
            }
            if (item.getToUser().getOkToReceiveEmail()) {
                String receiptText = props.getString("EMAIL_RECEIPT_TEXT");
                receiptText = receiptText.replace("%%Recipient%%", item.getToUser().getEmail());
                receiptText = receiptText.replace("%%Subject%%", item.getSubject());
                receiptText = receiptText.replace("%%Link%%", props.getString("EPICE_URL"));
                receiptText = receiptText.replace("%%itemId%%", item.getId());
                receiptText = receiptText.replace("%%Sender%%", currentUser.getName());
                SendMailUtil.send(currentUser.getEmail(), item.getToUser().getEmail(), receiptText, receiptSubject);
            }
        }
        return newItems;
    }

    public Set<Item> createNewItemsWithoutPersisting(Person currentUser, String toEmails, String supervisors, String tags, Date deadLineDate, String action, String subject, FileRepository currentFileRepository, Item parentItem, List<String> linkList) {
        return createNewItems(false, currentUser, toEmails, supervisors, tags, deadLineDate, action, subject, currentFileRepository, parentItem, linkList);
    }

    private Set<Item> createNewItems(boolean persist, Person currentUser, String toEmails, String supervisors, String tags, Date deadLineDate, String action, String subject, FileRepository currentFileRepository, Item parentItem, List<String> linkList) {

        //establish 'to user' emails
        List<String> toUserEmailList = new ArrayList<String>();
        if (toEmails != null) {
            StringTokenizer emailsTokenizer = new StringTokenizer(toEmails, ",");
            while (emailsTokenizer.hasMoreElements()) {
                String toEmail = emailsTokenizer.nextToken();
                toEmail = toEmail.trim();
                toEmail = toEmail.toLowerCase();
                toUserEmailList.add(toEmail);
            }
        }

        //establish supervisors
        List<String> supervisorEmailList = new ArrayList<String>();
        if (supervisors != null) {
            StringTokenizer supervisorsTokenizer = new StringTokenizer(supervisors, ",");
            while (supervisorsTokenizer.hasMoreElements()) {
                String toSupEmail = supervisorsTokenizer.nextToken();
                toSupEmail = toSupEmail.trim();
                toSupEmail = toSupEmail.toLowerCase();
                supervisorEmailList.add(toSupEmail);
            }
        }

        //establish tags
        List<String> tagList = new ArrayList<String>();
        if (tags != null) {
            StringTokenizer tagsTokenizer = new StringTokenizer(tags, ",");
            while (tagsTokenizer.hasMoreElements()) {
                String tag = tagsTokenizer.nextToken();
                if (tag != null) {
                    tagList.add(tag.trim());
                }
            }
        }



        //if persisting
        if (persist == true) {
            Set<Item> newItems = itemDAO.createItems(currentUser, toUserEmailList, supervisorEmailList, tagList, linkList, parentItem, action, subject, deadLineDate, true, currentFileRepository);
            return newItems;


        //if not create items and save one in setCurrentEditedItem
        } else {
            //put the current entries in a list but do not invite
            Set<Item> newItems = itemDAO.createItemsWithoutPersisting(currentUser, toUserEmailList, supervisorEmailList, tagList, linkList, parentItem, action, subject, deadLineDate, false, currentFileRepository);
            return newItems;

        }
    }
}
