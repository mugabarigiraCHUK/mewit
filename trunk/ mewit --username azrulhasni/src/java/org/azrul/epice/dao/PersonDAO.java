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

import java.util.List;
import java.util.Set;
import org.azrul.epice.domain.UserLink;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul Hasni MADISA
 */
public interface PersonDAO {
    
    Person create(String email, String name, String userName, String password, String icNumber, String phone, String department, String officeAddress);
    public Person update(String email, String name, String userName, String password, String icNumber, String phone, String department, String officeAddress,Boolean okToReceivedEmail);
    
    String setNewRandomPassword(final Person user);
    
    void removeFromBuddies(final Person user, String buddyEmail);
    
    void removeFromSupervisor(final Person user, String supEmail);

    void addBuddiesByEmails(final Person user, Set<String> buddyEmailSet);
    
    void addBuddies(final Person user, Set<Person> buddies);

    void addBuddiesAndSupervisorsByEmails(final Person user, Set<String> buddyEmailSet, Set<String> supervisorEmailSet);

    void addSupervisorsByEmails(final Person user, Set<String> supervisorEmailSet);

    Person findPersonByEmail(String email);

    Person findPersonByEmailPassword(String email, String password);

    Person refresh(final Person person);
    
    void persistNew(final Person _user);
    
    void persistNewWithRandomPassword(final Person _user);
    
    void setNewPassword(final Person user, String newPassword);
    
    void addLinks(final Person _user, final List<UserLink> links);

}
