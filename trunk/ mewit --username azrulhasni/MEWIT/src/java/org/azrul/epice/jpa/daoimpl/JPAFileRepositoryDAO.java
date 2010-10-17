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
package org.azrul.epice.jpa.daoimpl;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.azrul.epice.dao.FileRepositoryDAO;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.UUID;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
public class JPAFileRepositoryDAO implements FileRepositoryDAO {

    private static String PUNAME = "MEWITPU";

    public FileRepository update(final FileRepository _fileRepository, String currentUser) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileRepository fileRepo = update(_fileRepository, currentUser, em);
            tx.commit();
            return fileRepo;
        } catch (FileRepositoryNotExistException ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;
        } finally {
            em.close();
        }
    }

    public FileRepository createWithoutPersisting(final String owner, String description) {
        FileRepository fileRepo = new FileRepository();
        fileRepo.setOwner(owner);
        fileRepo.setId(UUID.randomUUID().toString());
        fileRepo.setDescription(description);
        return fileRepo;
    }


    public FileRepository create(final String _id, final String _owner, String description){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileRepository fileRepo = create(_id, _owner, "", em);
            tx.commit();
            return fileRepo;
        } catch (FileRepositoryNotExistException ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;
        } finally {
            em.close();
        }
    }


    public FileRepository create(final String owner, String description) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileRepository fileRepo = create(owner, "", em);
            tx.commit();
            return fileRepo;
        } catch (FileRepositoryNotExistException ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;
        } finally {
            em.close();
        }
    }

    public FileRepository create(final String owner, String description, EntityManager em) {
        if (owner != null) {
            FileRepository fileRepo = new FileRepository();
            fileRepo.setOwner(owner);
            fileRepo.setId(UUID.randomUUID().toString());
            fileRepo.setDescription(description);
            em.persist(fileRepo);
            return fileRepo;
        } else {
            return null;
        }
    }

    public FileRepository create(final String id, final String owner, String description, EntityManager em) {
        if (owner != null) {
            FileRepository fileRepo = new FileRepository();
            fileRepo.setOwner(owner);
            fileRepo.setId(id);
            fileRepo.setDescription(description);
            em.persist(fileRepo);
            return fileRepo;
        } else {
            return null;
        }
    }

    public FileRepository create(final String owner, String description, List<String> files, EntityManager em) {
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
                    em.persist(attachment);
                }
                fileRepo.setAttachments(attachments);
                em.persist(fileRepo);
            } else {
                em.persist(fileRepo);
            }
            return fileRepo;
        } else {
            return null;
        }
    }

    public FileRepository addNewAttachments(final FileRepository _fileRepo, final Set<Attachment> attachments, final String currentUser) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileRepository fileRepo = addNewAttachments(_fileRepo, attachments, currentUser, em);
            em.flush();
            tx.commit();

            return fileRepo;
        } catch (FileRepositoryNotExistException ex) {
            tx.rollback();
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public FileRepository addNewAttachments(final FileRepository fileRepo, final Set<Attachment> attachments, final String currentUser, EntityManager em) {

        for (Attachment attachment : attachments) {
            attachment.setOwner(currentUser);
            em.persist(attachment);
            fileRepo.getAttachments().add(attachment);
        }
        em.merge(fileRepo);
        return fileRepo;

    }

    public FileRepository addNewAttachment(final FileRepository _fileRepo, final Attachment attachment) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(attachment);
            FileRepository fileRepo = findFileRepositoryById(_fileRepo.getId(), em);
            em.refresh(fileRepo);
            fileRepo.getAttachments().add(attachment);
            em.merge(fileRepo);
            em.flush();
            tx.commit();
            return fileRepo;

        } catch (FileRepositoryNotExistException ex) {
            tx.rollback();
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public FileRepository findFileRepositoryById(String id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        FileRepository fileRepo = findFileRepositoryById(id, em);
        em.close();
        return fileRepo;

    }

    public FileRepository refresh(final FileRepository fileRepository) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        FileRepository fileRepo = refresh(fileRepository, em);
        em.close();
        return fileRepo;
    }

    public FileRepository refresh(final FileRepository fileRepository, EntityManager em) {
        return em.find(FileRepository.class, fileRepository.getId());
    }

    @Override
    public FileRepository removeAttachmentsFromFileRepository(final FileRepository fileRepository, Set<Attachment> attachmentsToBeDeleted, String currentUser) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            FileRepository fileRepo = removeAttachmentsFromFileRepository(fileRepository, attachmentsToBeDeleted, currentUser, em);
            tx.commit();
            return fileRepo;

        } catch (FileRepositoryNotExistException ex) {
            tx.rollback();
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            throw ex;

        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public FileRepository removeAttachmentsFromFileRepository(final FileRepository fileRepository, Set<Attachment> attachmentsToBeDeleted, String currentUser, EntityManager em) {
        //JPAAttachmentDAO attachmentDAO = new JPAAttachmentDAO();
        for (Attachment attachment : attachmentsToBeDeleted) {
            fileRepository.getAttachments().remove(attachment);
            em.remove(attachment);
        }
        em.merge(fileRepository);
        return fileRepository;
    }

    private FileRepository findFileRepositoryById(String id, EntityManager em) throws DatabaseClosedException, Db4oIOException {
        return em.find(FileRepository.class, id);
    }

    public void delete(FileRepository fileRepository) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(fileRepository);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    public FileRepository update(final FileRepository _fileRepository, String currentUser, EntityManager em) throws Db4oIOException, DatabaseClosedException, FileRepositoryNotExistException, DatabaseReadOnlyException {

        FileRepository fileRepository = findFileRepositoryById(_fileRepository.getId());

        //see if fileRepo from DB is different from gui
        Set<Attachment> differenceToBePersisted = new HashSet<Attachment>(_fileRepository.getAttachments());
        differenceToBePersisted.removeAll(fileRepository.getAttachments());
        Set<Attachment> differenceToBeDeleted = new HashSet<Attachment>(fileRepository.getAttachments());
        differenceToBeDeleted.removeAll(_fileRepository.getAttachments());
        if (!differenceToBeDeleted.isEmpty()) {
            removeAttachmentsFromFileRepository(fileRepository, differenceToBeDeleted, currentUser, em);
        }
        if (!differenceToBePersisted.isEmpty()) {
            addNewAttachments(fileRepository, differenceToBePersisted, currentUser, em);
        }
        return fileRepository;
    }
}


