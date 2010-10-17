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

import java.util.Collection;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Selection;
import org.azrul.epice.dao.AttachmentDAO;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class JPAAttachmentDAO implements AttachmentDAO {

    public static String PUNAME = "MEWITPU";

    public Attachment create(String attachmentID, String filename, String ownerEmail) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            Attachment attachment = new Attachment();
            attachment.setFile(filename);
            attachment.setOwner(ownerEmail);
            attachment.setUploadDate(new Date());
            attachment.setId(attachmentID);
            em.persist(attachment);
            em.flush();
            tx.commit();
            return attachment;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        return em.find(Attachment.class, id);
    }

    public Set<Attachment> findAttachmentsByIds(List<String> ids) {
//        ObjectContainer db = null;
//        try {
//            return findAttachmentsByIds(ids, db);
//        } catch (Exception ex) {
//            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
//            return null;
//        } finally {
//            if (db != null) {
//                DBUtil.closeDB();
//            }
//        }
        return null;
    }

    public Attachment refresh(final Attachment attachment) {
        return attachment;
    }

    public Attachment refresh(final Attachment attachment, ObjectContainer db) {
        return attachment;
    }

    private Attachment findAttachmentById(String id, EntityManager em) throws Db4oIOException, DatabaseClosedException {
        return em.find(Attachment.class, id);
    }

    private Set<Attachment> findAttachmentsByIds(final List<String> ids, EntityManager em) throws Db4oIOException, DatabaseClosedException {
        CriteriaBuilder cbuilder = em.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Attachment> criteria = cbuilder.createQuery(Attachment.class);
        Root<Attachment> attachment = criteria.from(Attachment.class);
        criteria.select(attachment).where(attachment.get("id").in(ids));
        List<Attachment> attachments = em.createQuery(criteria).getResultList();
        return new TreeSet<Attachment>(attachments);
    }

    public Attachment update(final Attachment _attachment, EntityManager em) throws DatabaseReadOnlyException, DatabaseClosedException {
            em.merge(_attachment);
            return _attachment;
    }

    public void delete(Attachment attachment) {
          EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();

            em.remove(attachment);
            em.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            
        } finally {
            em.close();
        }
    }
}
