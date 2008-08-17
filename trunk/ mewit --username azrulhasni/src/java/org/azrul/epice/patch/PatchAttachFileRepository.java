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
import java.util.logging.Level;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;


public class PatchAttachFileRepository {

    public static void main(String[] args) {



        //save in db and reassign sent item
        ObjectContainer db = null;
        try {
            //treat current
            db = DBUtil.getDB();

            //get all items
            Item itemProto = new Item();
            ObjectSet<Item> items = db.get(itemProto);
            while (items.hasNext()) {
                Item item = items.next();
                boolean doit = false;
                if (item.getChildren() == null) {
                    doit = true;
                } else {
                    if (item.getChildren().isEmpty()) {
                        doit = true;
                    }
                }

                if (doit == true) {
                    FileRepository fr = new FileRepository();

                    //find root
                    Item root = item;
                    while (root.getParent() != null) {
                        root = root.getParent();
                    }

                    //set owner of FileRepository to the sender of the original root
                    fr.setOwner(root.getFromUser());

                    //set current item
                    item.setFileRepository(fr);

                    //save current item
                    db.ext().set(item, 2);

                    //treat parents
                    Item it = item;
                    while (it.getParent() != null) {
                        it = it.getParent();
                        it.setFileRepository(fr);
                        db.ext().set(it, 2);
                    }
                }
            }
            db.commit();
        } catch (Exception ex) {
            if (db !=null){
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            DBUtil.closeDB();
        }

    }
}
