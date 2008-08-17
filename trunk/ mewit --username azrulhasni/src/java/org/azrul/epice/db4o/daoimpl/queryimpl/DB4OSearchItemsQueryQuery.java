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

import org.azrul.epice.dao.query.SearchItemsQueryQuery;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OSearchItemsQueryQuery implements SearchItemsQueryQuery {

    @Override
    public List<SearchItemsQuery> run(Person _user) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            return new ArrayList<SearchItemsQuery>(run(_user, db));
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public List<SearchItemsQuery> run(Person _user, ObjectContainer db) throws Exception {
        List<SearchItemsQuery> list = new ArrayList<SearchItemsQuery>();
        ObjectSet<Person> os = db.get(_user);
        if (os.hasNext()) {
            final Person user = (Person) os.next();
            DB4OSearchItemsQuery query = new DB4OSearchItemsQuery();
            query.setOwner(user);
            ObjectSet<SearchItemsQuery> results = db.get(query);
            list.addAll(results);
        }
        return list;
    }
}
