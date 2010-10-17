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

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import java.util.ArrayList;
import org.azrul.epice.dao.FileRepositoryDAO;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.UUID;
import java.util.logging.Level;
import org.azrul.epice.dao.AttachmentDAO;
import org.azrul.epice.dao.factory.AttachmentDAOFactory;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.exception.FileRepositoryNotExistException;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OFileRepositoryDAO implements FileRepositoryDAO {

    public FileRepository update(final FileRepository _fileRepository, String currentUser) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = update(_fileRepository, currentUser, db);
            db.commit();
            return fileRepo;
        } catch (FileRepositoryNotExistException ex) {
            if (db != null) {
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public FileRepository createWithoutPersisting(final String owner, String description) {
        FileRepository fileRepo = new FileRepository();
        fileRepo.setOwner(owner);
        fileRepo.setId(UUID.randomUUID().toString());
        fileRepo.setDescription(description);
        return fileRepo;
    }

    public FileRepository create(final String _owner, String description) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = create(_owner, description, db);
            db.commit();
            return fileRepo;
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

    public FileRepository create(final String owner, String description, ObjectContainer db) {
        if (owner != null) {
            FileRepository fileRepo = new FileRepository();
            fileRepo.setOwner(owner);
            fileRepo.setId(UUID.randomUUID().toString());
            fileRepo.setDescription(description);
            db.store(fileRepo);
            return fileRepo;
        } else {
            return null;
        }
    }

    public FileRepository create(final String owner, String description, List<String> files, ObjectContainer db) {
        if (owner != null) {
            FileRepository fileRepo = new FileRepository();
            fileRepo.setOwner(owner);
            fileRepo.setId(UUID.randomUUID().toString());
            fileRepo.setDescription(description);

            if (files != null && !files.isEmpty()) {
                AttachmentDAO attachmentDAO = AttachmentDAOFactory.getInstance();
                List<Attachment> attachments = new ArrayList<Attachment>();
                for (String file : files) {
                    Attachment attachment = attachmentDAO.createWithoutPersisting(file, owner);
                    attachments.add(attachment);
                }
                fileRepo.setAttachments(attachments);
                db.ext().store(fileRepo, 2);
            }else{
                db.store(fileRepo);
            }
            

            return fileRepo;
        } else {
            return null;
        }
    }

    public FileRepository addNewAttachments(final FileRepository _fileRepo, final Set<Attachment> attachments, final String currentUser) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = addNewAttachments(_fileRepo, attachments, currentUser, db);
            db.commit();
            return fileRepo;
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

    public FileRepository addNewAttachments(final FileRepository _fileRepo, final Set<Attachment> attachments, final String currentUser, ObjectContainer db) throws FileRepositoryNotExistException, DatabaseReadOnlyException, Db4oIOException, DatabaseClosedException {
        FileRepository fileRepo = refresh(_fileRepo, db);
        if (fileRepo != null) {
            for (Attachment attachment : attachments) {
                attachment.setOwner(currentUser);
                fileRepo.getAttachments().add(attachment);
            }
            db.ext().store(fileRepo, 2);

            return fileRepo;
        } else {
            throw new FileRepositoryNotExistException();
        }
    }

    public FileRepository addNewAttachment(final FileRepository _fileRepo, final Attachment attachment) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = refresh(_fileRepo, db);
            if (fileRepo != null) {
                String owner = attachment.getOwner();
                attachment.setOwner(owner);
                fileRepo.getAttachments().add(attachment);
                db.ext().store(fileRepo, 2);
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
    public FileRepository removeAttachmentsFromFileRepository(final FileRepository fileRepository, Set<Attachment> attachmentsToBeDeleted, String currentUser) throws FileRepositoryNotExistException {
        ObjectContainer db = null;

        try {
            db = DBUtil.getDB();
            FileRepository fileRepo = removeAttachmentFromFileRepository(fileRepository, attachmentsToBeDeleted, currentUser, db);
            db.commit();
            return fileRepo;
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

    public FileRepository removeAttachmentFromFileRepository(final FileRepository fileRepository, Set<Attachment> attachmentsToBeDeleted, String currentUser, ObjectContainer db) throws FileRepositoryNotExistException {
        DB4OAttachmentDAO attachmentDAO = new DB4OAttachmentDAO();
        FileRepository fr = refresh(fileRepository, db);
        if (fr != null) {
            for (Attachment attachment : attachmentsToBeDeleted) {
                //only allow deletion if the current user owns the attachment OR current user owns the whole repository
                //if (currentUser.equals(attachment.getOwner()) || currentUser.equals(fr.getOwner())) {
                Attachment a = attachmentDAO.refresh(attachment);
                boolean attExist = a != null;

                fr.getAttachments().remove(a);
                if (attExist) {
                    db.ext().delete(a);
                }
                //}
                }
            db.ext().store(fr, 2);
            return fr;
        } else {
            throw new FileRepositoryNotExistException();
        }
    }

    private FileRepository findFileRepositoryById(String id, ObjectContainer db) throws DatabaseClosedException, Db4oIOException {
        FileRepository fileRepository = new FileRepository();
        fileRepository.setId(id);
        ObjectSet<FileRepository> os = db.queryByExample(fileRepository);
        if (os.hasNext()) {
            return os.next();
        } else {
            return null;
        }
    }

    public void delete(FileRepository fileRepository) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            FileRepository fr = refresh(fileRepository);
            if (fr != null) {
                db.ext().delete(fr);
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

    public FileRepository update(final FileRepository _fileRepository, String currentUser, ObjectContainer db) throws Db4oIOException, DatabaseClosedException, FileRepositoryNotExistException, DatabaseReadOnlyException {
        DB4OAttachmentDAO attachmentDAO = new DB4OAttachmentDAO();
        FileRepository fileRepository = refresh(_fileRepository, db);
        if (fileRepository == null) {
            //create new
            for (Attachment att : _fileRepository.getAttachments()) {
                attachmentDAO.update(att, db);
            }
            db.store(_fileRepository);
            return _fileRepository;
        } else {
            //see if fileRepo from DB is different from gui
            Set<Attachment> differenceToBePersisted = new TreeSet<Attachment>(_fileRepository.getAttachments());
            differenceToBePersisted.removeAll(fileRepository.getAttachments());
            Set<Attachment> differenceToBeDeleted = new TreeSet<Attachment>(fileRepository.getAttachments());
            differenceToBeDeleted.removeAll(_fileRepository.getAttachments());
            if (!differenceToBeDeleted.isEmpty()) {
                removeAttachmentFromFileRepository(fileRepository, differenceToBeDeleted, currentUser, db);
            }
            if (!differenceToBePersisted.isEmpty()) {
                addNewAttachments(fileRepository, differenceToBePersisted, currentUser, db);
            }
            return fileRepository;
        }
    }

    public FileRepository create(String _id, String _owner, String description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
