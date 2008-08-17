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
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;


public class CleanUpAll {

    public static void main(String[] args) {
        Set<Person> persons = new HashSet<Person>();
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            ObjectSet<Person> osp = db.get(new Person());
            while (osp.hasNext()) {
                Person p = osp.next();
                if (persons.contains(p)) {
                    db.ext().delete(p);
                } else {
                    persons.add(p);
                }
            }
            
            ObjectSet<Item> osi = db.get(new Item());
            while (osi.hasNext()) {
                Item i = osi.next();
               
                if (i.getToUser()==null || i.getFromUser()== null){
                    db.ext().delete(i);
                }
            }

//Clean up null values in set
          

            ObjectSet<Item> osi2 = db.get(new Item());
            while (osi2.hasNext()) {
                Item i = osi2.next();
                if (i.getSupervisors().contains(null)){
                    int stop = 1;
                }
//                i.getSupervisors().remove(null);
//                db.ext().set(i,2);
            }

//            ObjectSet<FileRepository> osf = db.get(new FileRepository());
//            while (osf.hasNext()) {
//                FileRepository f = osf.next();
//                f.getAttachments().remove(null);
//                db.ext().set(f,2);
//            }
//Delete all and all
//            ObjectSet<Attachment> attachments = db.get(new Attachment());
//            while (attachments.hasNext()) {
//                Attachment attachment = attachments.next();
//                db.ext().delete(attachment);
//            }
//
//            ObjectSet<FileRepository> frs = db.get(new FileRepository());
//            while (frs.hasNext()) {
//                FileRepository fr = frs.next();
//                db.ext().delete(fr);
//            }
//
//            ObjectSet<Item> items = db.get(new Item());
//            while (items.hasNext()) {
//                Item item = items.next();
//                db.ext().delete(item);
//            }
            db.commit();
        } catch (Exception e) {
            if (db != null) {
                db.rollback();
            }
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }

    }
}
