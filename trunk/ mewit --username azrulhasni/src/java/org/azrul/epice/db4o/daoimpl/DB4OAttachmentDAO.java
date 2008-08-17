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

import org.azrul.epice.dao.AttachmentDAO;
import com.db4o.DatabaseClosedException;
import com.db4o.DatabaseReadOnlyException;
import com.db4o.Db4oIOException;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OAttachmentDAO implements AttachmentDAO {
    
    public Attachment createWithoutPersisting(String filename, String ownerEmail) {
        DB4OPersonDAO personDAO = new DB4OPersonDAO();
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Person user = personDAO.findPersonByEmail(ownerEmail,db);
            Attachment attachment = new Attachment();
            attachment.setFile(filename);
            attachment.setOwner(user);
            attachment.setUploadDate(new Date());
            attachment.setId(UUID.randomUUID().toString());
            
            return attachment;

        } catch (Exception ex) {
            
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

   


    public Attachment findAttachmentById(String id) {
        ObjectContainer db = null;
        try {
            return findAttachmentById(id, db);
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public Attachment refresh(final Attachment attachment) {
        return findAttachmentById(attachment.getId());
    }

    public Attachment refresh(final Attachment attachment, ObjectContainer db) {
        return findAttachmentById(attachment.getId(), db);
    }

    private Attachment findAttachmentById(String id, ObjectContainer db) throws Db4oIOException, DatabaseClosedException {
        db = DBUtil.getDB();
        Attachment attachment = new Attachment();
        attachment.setId(id);
        ObjectSet<Attachment> os = db.get(attachment);
        if (os.hasNext()) {
            return os.next();
        } else {
            return null;
        }
    }

    public Attachment persistNew(final Attachment _attachment, ObjectContainer db) throws DatabaseReadOnlyException, DatabaseClosedException {
        Attachment attachment = refresh(_attachment, db);
        if (attachment == null) {
            
            db.set(_attachment);
            attachment = refresh(_attachment, db);
        } 
        return attachment;
    }
}
