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
import java.util.HashSet;
import java.util.Set;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;


public class CleanUpPerson {
     public static void main(String[] args){
        Set<Person> persons = new HashSet<Person>();
        Person azrul = new Person();
        azrul.setEmail("azrulhasni@yahoo.com");
        persons.add(azrul);
        ObjectContainer db = null;
        try{
            db = DBUtil.getDB();
            ObjectSet<Person> osp = db.get(azrul);
            
                Person p = osp.next();
                
               //if (persons.contains(p)){
                    db.ext().delete(p);
//                }else{
//                    persons.add(p);
//                }
            
            
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
