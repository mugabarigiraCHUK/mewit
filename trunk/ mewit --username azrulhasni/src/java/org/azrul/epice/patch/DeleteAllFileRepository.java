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
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.util.DBUtil;


public class DeleteAllFileRepository {
     public static void main(String[] args){
        ObjectContainer db = null;
        try{
            db = DBUtil.getDB();
            
            ObjectSet<FileRepository> frs = db.get(new FileRepository());
            while(frs.hasNext()){
                FileRepository fr = frs.next();
                db.ext().delete(fr);
            }
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
