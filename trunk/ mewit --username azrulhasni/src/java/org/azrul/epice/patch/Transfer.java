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

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;


public class Transfer {
    public static  void main(String[] args){
        ObjectContainer odb = null;
        ObjectContainer db = null;
        try{
            db = DBUtil.getDB();
            odb = Db4o.openFile("C:/DB4O_Database/old_public_database");
            Person p = new Person();
            ObjectSet<Person> osp = odb.get(p);
            while(osp.hasNext()){
                Person ap = osp.next();
                db.set(ap);
            }
            db.commit();
        }catch(Exception e){
            if (db != null){
                db.rollback();
            }
            e.printStackTrace();
        }finally{
            if (odb!=null){
                odb.close();
            }
            if (db != null){
                db.close();
            }
            
        }
    }
}
