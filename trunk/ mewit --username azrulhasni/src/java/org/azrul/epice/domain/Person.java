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

package org.azrul.epice.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;

@Searchable(root = false)
public class Person implements Serializable {

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    private String icNumber;
    private String password;
    @SearchableProperty
    private String userName;
    @SearchableProperty
    private String name;
    @SearchableProperty
    private String department;
    @SearchableProperty
    private String email;
    private String phone;
    @SearchableProperty
    private String officeAddress;
    
    private Boolean okToReceiveEmail;
    private Set<Person> buddies = new HashSet<Person>();
    private Set<Person> supervisors = new HashSet<Person>();
    private Set<String> personalTags = new HashSet<String>();
    private Set<UserLink> userLinks = new HashSet<UserLink>(); //<URL, Descrption>

    public Person() {
    }

    public Person(String userName) {
        this.userName = userName;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        Person p = (Person) object;
        if (this.getEmail() == null){
            return false;
        }
        return this.getEmail().equals(p.getEmail());
    }

    
    
    public boolean getUserNameNotNull() {
        return !(userName == null);
    }

    public Set<Person> getBuddies() {
        if (buddies==null){
            buddies = new HashSet<Person>();
        }
        return buddies;
    }

    public void setBuddies(Set<Person> buddies) {
        this.buddies = buddies;
    }

    public Set<String> getPersonalTags() {
        if (personalTags==null){
            personalTags = new HashSet<String>();
        }
        return personalTags;
    }

    public void setPersonalTags(Set<String> personalTags) {
        this.personalTags = personalTags;
    }

    public Set<Person> getSupervisors() {
        if (supervisors==null){
            supervisors = new HashSet<Person>();
        }
        return supervisors;
    }

    public void setSupervisors(Set<Person> supervisors) {
        this.supervisors = supervisors;
    }

    public Boolean getOkToReceiveEmail() {
        return okToReceiveEmail;
    }

    public void setOkToReceiveEmail(Boolean okToReceivedEmail) {
        this.okToReceiveEmail = okToReceivedEmail;
    }

    public Set<UserLink> getUserLinks() {
        return userLinks;
    }

    public void setUserLinks(Set<UserLink> links) {
        this.userLinks = links;
    }

}
