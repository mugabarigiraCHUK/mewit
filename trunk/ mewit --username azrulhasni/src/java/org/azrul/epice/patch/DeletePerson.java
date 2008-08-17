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
package org.azrul.epice.patch;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;


public class DeletePerson {
    
    public static void main(String[] args){
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        
       
        Person _personToBeRemoved = new Person();
        _personToBeRemoved.setEmail("azrulhasni@yahoo.com");
        
        ObjectContainer db = null;
        try{
            db = DBUtil.getDB();
            Person personToBeRemoved = (Person) db.get(_personToBeRemoved).next();
            ObjectSet<Person> osp = db.get(new Person());
            while (osp.hasNext()){
                Person p = osp.next();
                personDAO.removeFromBuddies(p,personToBeRemoved.getEmail());
                personDAO.removeFromSupervisor(p,personToBeRemoved.getEmail());
            }
            db.ext().delete(personToBeRemoved);
            db.commit();
        }catch(Exception e){
            if (db != null){
                db.rollback();
            }
        }finally{
            if (db!=null){
                DBUtil.closeDB();
            }
        }
        
    }
}
