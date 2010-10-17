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
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Predicate;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OAttachmentDAO implements AttachmentDAO {
     public Attachment create(String attachmentID, String filename, String ownerEmail) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Attachment attachment = new Attachment();
            attachment.setFile(filename);
            attachment.setOwner(ownerEmail);
            attachment.setUploadDate(new Date());
            attachment.setId(attachmentID);
            db.store(attachment);
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

    public Attachment createWithoutPersisting(String fileName, String username) {


            Attachment attachment = new Attachment();
            attachment.setId(UUID.randomUUID().toString());
            attachment.setFile(fileName);
            attachment.setOwner(username);
            attachment.setUploadDate(new Date());
            
            
            return attachment;

     
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

    public Set<Attachment> findAttachmentsByIds(List<String> ids) {
        ObjectContainer db = null;
        try {
            return findAttachmentsByIds(ids, db);
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
        ObjectSet<Attachment> os = db.queryByExample(attachment);
        if (os.hasNext()) {
            return os.next();
        } else {
            return null;
        }
    }

     private Set<Attachment> findAttachmentsByIds(final List<String> ids, ObjectContainer db) throws Db4oIOException, DatabaseClosedException {
        if (ids==null || ids.isEmpty()){
            return new TreeSet<Attachment>();
        }
        db = DBUtil.getDB();

        ObjectSet<Attachment> os = db.query(new Predicate<Attachment>(){
            @Override
            public boolean match(Attachment attachment) {
               if (ids.contains(attachment.getId())){
                   return true;
               }else{
                   return false;
               }
            }
        });
        Set<Attachment> attachments = new TreeSet<Attachment>();
        attachments.addAll(os);
        return attachments;
    }

    public Attachment update(final Attachment _attachment, ObjectContainer db) throws DatabaseReadOnlyException, DatabaseClosedException {
         Attachment attachment = refresh(_attachment, db);
        if (attachment == null) {
            db.store(_attachment);
            return _attachment;
        }else{
            attachment.setFile(_attachment.getFile());
            attachment.setOwner(_attachment.getOwner());
            attachment.setUploadDate(_attachment.getUploadDate());
            db.store(attachment);
            return attachment;
        }
    }

     public void delete(Attachment attachment){
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Attachment at = refresh(attachment);
            if (at != null) {
                db.ext().delete(at);
                db.commit();
            }

        } catch (Db4oIOException ex) {
            if (db != null) {
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
