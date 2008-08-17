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

import java.util.Map;
import java.util.ResourceBundle;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.SendMailUtil;
import org.azrul.epice.web.SessionBean1;

/**
 *
 * @author Azrul
 */
public class LoginManager {

    private PersonDAO personDAO = PersonDAOFactory.getInstance();
    private ItemDAO itemDAO = ItemDAOFactory.getInstance();

    //if user is logged in and the user is accessing a particular item, let him
    //if user is NOT logged in and the user is accessing a particular item, 
    //=>
    public boolean initLogin(String itemId, SessionBean1 sessionBean1, Map sessionMap) {
        boolean redirect = false;
        
        if (itemId != null) {
            String userEmail = (String) sessionMap.get("LOGGED_IN");
            Item item = getItemDAO().findItemById(itemId);
            if (userEmail != null) {
                if (item.getToUser().getEmail().equals(userEmail)) {
                    //user is logged in and has rights => save item and redirect to PgReceivedItem
                    sessionBean1.setCurrentReceivedItem(item);
                    sessionBean1.setPreviousPage("PgMenu");
                    redirect = true;

                } else {
                    //user is logged in but doesn't have the rights, =>display login page
                }
            } else {
                //differ rights checking to after logged in is done, save access info.
                sessionBean1.setQuickAccessItem(item);
            }
        }
        return redirect;
    }

    public String doLogin(String email, String password, Map session, SessionBean1 sessionBean1) {
        Person user = getPersonDAO().findPersonByEmailPassword(email, password);
        if (user != null) {
            session.put("LOGGED_IN", user.getEmail());
            sessionBean1.setCurrentUser(user);
            if (sessionBean1.getQuickAccessItem() == null) {
                return "successful";
            } else {
                Item item = sessionBean1.getQuickAccessItem();
                if (item.getToUser().equals(user)) {
                    sessionBean1.setCurrentReceivedItem(sessionBean1.getQuickAccessItem());
                    sessionBean1.setQuickAccessItem(null);
                    sessionBean1.setPreviousPage("PgMenu");
                    return "quickAccess";
                } else {
                    sessionBean1.setQuickAccessItem(null);
                    return "successful";
                }
            }
        } else {
            return null;
        }
    }

    public String getBackPassword(SessionBean1 sessionBean1, boolean sendEmail) {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        ResourceBundle props = ResourceBundle.getBundle("epice");
        String emailReactivateText = props.getString("EMAIL_REACTIVATE_TEXT");
        Person user = sessionBean1.getCurrentUser();

        String password = getPersonDAO().setNewRandomPassword(user);
        if (sendEmail) {
            emailReactivateText = emailReactivateText.replace("%%Link%%", props.getString("EPICE_URL")).replace("%%email%%", user.getEmail()).replace("%%key%%", password);
            SendMailUtil.send(props.getString("SMTP_USER"), user.getEmail(), emailReactivateText, props.getString("EMAIL_REACTIVATE_TITLE"));
        }

        return password;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
}
