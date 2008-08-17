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

package org.azrul.epice.db4o.daoimpl;

import org.azrul.epice.db4o.daoimpl.queryimpl.DB4OSearchItemsQuery;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.dao.SearchItemsQueryDAO;
import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import org.azrul.epice.dao.query.SearchItemsQueryQuery;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OSearchItemsQueryDAO implements SearchItemsQueryDAO {
    private transient DB4OPersonDAO personDAO = new DB4OPersonDAO();
     
    private DB4OSearchItemsQuery refresh(final SearchItemsQuery query, ObjectContainer db) {
        DB4OSearchItemsQuery searchItemsQuery = new DB4OSearchItemsQuery();
        searchItemsQuery.setId(query.getId());
        searchItemsQuery = (DB4OSearchItemsQuery) db.get(searchItemsQuery).next();
        return searchItemsQuery;
    }
    
    @Override
    public void saveQuery(final Person  _user, SearchItemsQuery query) {
        ObjectContainer db = null;
        
        try {
            db = DBUtil.getDB();
            Person user = personDAO.refresh(_user, db);
            query.setId(UUID.randomUUID().toString());
            query.setOwner(user);
            db.set(query);
            db.commit();
        } catch (Db4oException ex) {
            if (db!=null){
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;
        } finally {
            DBUtil.closeDB();
        }
    }
     
    public DB4OSearchItemsQuery refresh(final SearchItemsQuery query) {
    ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            return refresh(query,db);
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }
    @Override
     public List<SearchItemsQuery> runItemsQueryQuery(Person user, SearchItemsQueryQuery query){
        return query.run(user);
    } 
    

    public void delete(final SearchItemsQuery query) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            DB4OSearchItemsQuery delSearchItemsQuery = refresh(query,db);
            db.delete(delSearchItemsQuery);
            db.commit();
            
        } catch (Exception ex) {
            if (db!=null){
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);

        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

   
}
