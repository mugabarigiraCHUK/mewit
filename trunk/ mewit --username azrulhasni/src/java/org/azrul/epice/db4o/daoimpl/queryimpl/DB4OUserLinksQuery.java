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
package org.azrul.epice.db4o.daoimpl.queryimpl;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.azrul.epice.dao.query.UserLinksQuery;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.domain.UserLink;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul
 */
public class DB4OUserLinksQuery implements UserLinksQuery, Serializable {

    @Override
    public List<UserLink> run(Person _user){
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            List<UserLink> ritems = new ArrayList<UserLink>(run(_user, db));
            return ritems;
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return new ArrayList<UserLink>();
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }
    
    public List<UserLink> run(Person _user, ObjectContainer db) throws Exception {

        List<UserLink> links = new ArrayList<UserLink>();
        ObjectSet<Person> os = db.get(_user);
        if (os.hasNext()) {
            final Person user = (Person) os.next();
            if (user.getUserLinks() != null) {
                links.addAll(user.getUserLinks());
            }
        }
        return links;
    }
}
