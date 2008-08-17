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

import org.azrul.epice.dao.FileRepositoryDAO;
import com.db4o.DatabaseClosedException;
import com.db4o.Db4oIOException;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Person;
import org.azrul.epice.exception.FileRepositoryNotExistException;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OFileRepositoryDAO implements FileRepositoryDAO {

    public FileRepository create(final Person _owner, String description) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            return create(_owner, description, db);
        } catch (FileRepositoryNotExistException ex) {
            if (db != null) {
                db.rollback();
            }
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public FileRepository create(final Person _owner, String description, ObjectContainer db) {
        DB4OPersonDAO personDAO = new DB4OPersonDAO();
        Person owner = personDAO.refresh(_owner, db);
        if (owner != null) {
            FileRepository fileRepo = new FileRepository();
            fileRepo.setOwner(owner);
            fileRepo.setId(UUID.randomUUID().toString());
            fileRepo.setDescription(description);
            db.set(fileRepo);
            return fileRepo;
        } else {
            return null;
        }
    }

   public  FileRepository addNewAttachments(final FileRepository _fileRepo, final List<Attachment> attachments) {
        ObjectContainer db = null;
        DB4OPersonDAO personDAO = new DB4OPersonDAO();
        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = refresh(_fileRepo, db);
            if (fileRepo != null) {
                for (Attachment attachment : attachments) {
                    Person owner = personDAO.refresh(attachment.getOwner(), db);
                    attachment.setOwner(owner);
                    fileRepo.getAttachments().add(attachment);
                }
                db.ext().set(fileRepo, 2);
                db.commit();
                return fileRepo;
            } else {
                throw new FileRepositoryNotExistException();
            }
        } catch (FileRepositoryNotExistException ex) {
            if (db != null) {
                db.rollback();
            }
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } catch (Db4oIOException ex) {
            if (db != null) {
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public FileRepository addNewAttachment(final FileRepository _fileRepo, final Attachment attachment) {
        ObjectContainer db = null;
        DB4OPersonDAO personDAO = new DB4OPersonDAO();
        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = refresh(_fileRepo, db);
            if (fileRepo != null) {
                Person owner = personDAO.refresh(attachment.getOwner(), db);
                attachment.setOwner(owner);
                fileRepo.getAttachments().add(attachment);
                db.ext().set(fileRepo, 2);
                db.commit();
                return fileRepo;
            } else {
                throw new FileRepositoryNotExistException();
            }
        } catch (FileRepositoryNotExistException ex) {
            if (db != null) {
                db.rollback();
            }
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } catch (Db4oIOException ex) {
            if (db != null) {
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public FileRepository findFileRepositoryById(String id) {
        ObjectContainer db = null;
        try {
            return findFileRepositoryById(id, db);
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public FileRepository refresh(final FileRepository fileRepository) {
        return findFileRepositoryById(fileRepository.getId());
    }

    public FileRepository refresh(final FileRepository fileRepository, ObjectContainer db) {
        return findFileRepositoryById(fileRepository.getId(), db);
    }

    @Override
    public FileRepository removeAttachmentFromFileRepository(final FileRepository fileRepository, List<Attachment> attachmentsToBeDeleted, Person currentUser) throws FileRepositoryNotExistException {
        ObjectContainer db = null;
        DB4OAttachmentDAO attachmentDAO = new DB4OAttachmentDAO();
        try {
            db = DBUtil.getDB();
            FileRepository fr = refresh(fileRepository);
            if (fr != null) {
                for (Attachment attachment : attachmentsToBeDeleted) {
                    //only allow deletion if the current user owns the attachment OR current user owns the whole repository
                    if (currentUser.equals(attachment.getOwner()) || currentUser.equals(fr.getOwner())) {
                        Attachment a = attachmentDAO.refresh(attachment);
                        boolean attExist = a != null;

                        fr.getAttachments().remove(a);
                        if (attExist) {
                            db.ext().delete(a);
                        }
                    }
                }
                db.ext().set(fr, 2);
                db.commit();
                return fr;
            } else {
                throw new FileRepositoryNotExistException();
            }

        } catch (FileRepositoryNotExistException ex) {
            if (db != null) {
                db.rollback();
            }
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;
        } catch (Db4oIOException ex) {
            if (db != null) {
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    private FileRepository findFileRepositoryById(String id, ObjectContainer db) throws DatabaseClosedException, Db4oIOException {
        db = DBUtil.getDB();
        FileRepository fileRepository = new FileRepository();
        fileRepository.setId(id);
        ObjectSet<FileRepository> os = db.get(fileRepository);
        if (os.hasNext()) {
            return os.next();
        } else {
            return null;
        }
    }
}