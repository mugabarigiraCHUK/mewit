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
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul
 */
public class EditProfileManager {

    public String updateProfile(String retypedPassword, String oldPassword, String password, String email, String name, String department, String officeAddress, Map sessionMap, Boolean okToReceiveEmail) {
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        String errors = "";

        Person user = personDAO.findPersonByEmailPassword(email, oldPassword);
        if (user == null) {
            errors = "ERROR: Wrong old password, please type again";
        //return null;
        }
        if (!retypedPassword.equals(password)) {
            errors = "ERROR: Password and retyped password are not the same. Please type again";
        }
        personDAO.update(email, name, "", password, "", "", department, officeAddress,okToReceiveEmail);


        sessionMap.clear();

        return errors;
    }
}
