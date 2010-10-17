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

import org.azrul.epice.dao.ItemDAO;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedSet;
import java.util.UUID;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.query.QueryResult;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.ChildItem;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Item.Priority;
import org.azrul.epice.jpa.daoimpl.queryimpl.JPAQueryRunner;
import org.azrul.epice.util.LogUtil;
////import org.azrul.epice.util.SendMailUtil;
//import org.azrul.epice.util.XMPPServices;
//import org.compass.core.CompassException;
//import org.compass.core.CompassSession;
//import org.compass.core.CompassTransaction;
//import org.jivesoftware.smack.XMPPConnection;

public class JPAItemDAO implements ItemDAO, Serializable {

    public static String PUNAME = "MEWITPU";
    private static String EPICE_URL;

    static {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        EPICE_URL = props.getString("EPICE_URL");
    }

    public Item updateFromFreshItem(Item itemFromNetwork, String username) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();


            Item item = update(itemFromNetwork, true, username,
                    (Item.Status.ACCEPTED == itemFromNetwork.getStatus()),
                    (Item.Status.NEGOTIATED == itemFromNetwork.getStatus()),
                    (Item.Status.REJECTED == itemFromNetwork.getStatus()),
                    itemFromNetwork.getNegotiatedDeadLine(),
                    itemFromNetwork.getReasonForNegotiatiationOfDeadLine(),
                    itemFromNetwork.getReasonForRejectionOfTask(),
                    itemFromNetwork.getFeedback(),
                    (Item.Status.DONE_CONFIRMED == itemFromNetwork.getStatus()),
                    itemFromNetwork.getCommentsOnFeedback(),
                    itemFromNetwork.getStartDate(),
                    itemFromNetwork.getDeadLine(),
                    itemFromNetwork.getPriority(),
                    em);
            em.flush();
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    private Item findItemById(String id, EntityManager em) {
        if (id == null) {
            return null;
        }
        return em.find(Item.class, id);
    }

    private Item findItemById(String id, int depth, EntityManager em) {
        return findItemById(id, em);
    }

    @Override
    public Item findItemById(String id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        return em.find(Item.class, id);
    }

    public Item refresh(Item _item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            Item item = findItemById(_item.getId(), em);

            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public List<Item> findItemsById(List<String> itemIds) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Item> items = new ArrayList<Item>();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            for (String itemId : itemIds) {
                items.add(em.find(Item.class, itemId));
            }
            tx.commit();
            return items;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public List<Item> refreshAll(List<Item> items) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            for (Item item : items) {
                em.refresh(item);
            }
            tx.commit();
            return items;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public QueryResult runItemsQuery(String user, SearchItemsQuery query, int startRow, int resultCount) {
        QueryResult result = new QueryResult();
        JPAQueryRunner runner = new JPAQueryRunner();
        if (query != null) {
            result = runner.run(query, user, startRow, resultCount);
        }
        return result;

    }

    public Item createItem(String currentUser, String itemId, String fileRepoId) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        JPAFileRepositoryDAO fileRepoDAO = new JPAFileRepositoryDAO();
        try {
            Item item = new Item();
            item.setId(itemId);
            tx.begin();
            FileRepository fileRepo = fileRepoDAO.create(fileRepoId, currentUser, "", em);
            item.setFileRepository(fileRepo);
            em.persist(item);
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    public Set<Item> createItems(String currentUser,
            List<String> toUserEmailList,
            List<String> supervisorEmailList,
            List<String> tagList,
            List<String> linkList,
            Item _parent,
            String action,
            String subject,
            Date startDate,
            Date deadline,
            boolean mergeParentChildSupervisors,
            final FileRepository _fileRepository,
            Priority priority) {


        Set<Item> newItems = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {

            tx.begin();
            JPAFileRepositoryDAO fileRepoDAO = (JPAFileRepositoryDAO) FileRepositoryDAOFactory.getInstance();
            //establish file repository
            FileRepository fileRepository = null;
            if (_fileRepository != null) {
                //try finding it in the db
                fileRepository = fileRepoDAO.findFileRepositoryById(_fileRepository.getId());
                if (fileRepository == null) {
                    
                    //if not in db, create one based on file repo passed in as param.
                    fileRepository = fileRepoDAO.create(_fileRepository.getId(), _fileRepository.getOwner(), _fileRepository.getDescription(), em);
                    //add attachments from fileRepo passed in param
                    HashSet<Attachment> attachments = new HashSet<Attachment>();
                    attachments.addAll(_fileRepository.getAttachments());
                    if (attachments.isEmpty()==false){
                        fileRepository = fileRepoDAO.addNewAttachments(fileRepository, attachments, currentUser, em);
                    }
                } else {
                    if (_parent != null) {
                        //file repository belongs to parent, persist attachments added by child
                        Set<Attachment> differenceToBePersisted = new HashSet<Attachment>(_fileRepository.getAttachments());
                        differenceToBePersisted.removeAll(fileRepository.getAttachments());
                        if (!differenceToBePersisted.isEmpty()) {
                            fileRepository = fileRepoDAO.addNewAttachments(fileRepository, differenceToBePersisted, currentUser, em);
                        }
                    } //else, fileRepo does not change, just use it
                }
            } else {
                //if null, just create one
                fileRepository = fileRepoDAO.create(currentUser, "", em);

            }
            newItems = createItems(currentUser,
                    toUserEmailList,
                    supervisorEmailList,
                    tagList,
                    linkList,
                    _parent,
                    action,
                    subject,
                    startDate,
                    deadline,
                    em,
                    mergeParentChildSupervisors,
                    fileRepository,
                    priority);
            tx.commit();
            return newItems;

        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return newItems;
        } finally {
            em.close();
        }
    }

    public Set<Item> createItems(String currentUser,
            List<String> toUserEmailList,
            List<String> supervisorEmailList,
            List<String> tagList,
            List<String> linkList,
            Item _parent,
            String action,
            String subject,
            Date startDate,
            Date deadline,
            boolean mergeParentChildSupervisors,
            List<String> fileNames,
            Priority priority) {


        Set<Item> newItems = null;
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //create file repo
        JPAAttachmentDAO attachmentDAO = new JPAAttachmentDAO();
        JPAFileRepositoryDAO fileRepoDAO = new JPAFileRepositoryDAO();
        FileRepository fileRepo = null;
        if (_parent != null) { //delegate item, fileRepo already exist in parent
            fileRepo = _parent.getFileRepository();
            if (fileNames != null && !fileNames.isEmpty()) {
                Set<Attachment> attachments = new TreeSet<Attachment>();
                for (String fileName : fileNames) {
                    Attachment attachment = attachmentDAO.createWithoutPersisting(fileName, currentUser);
                    attachments.add(attachment);
                }
                fileRepoDAO.addNewAttachments(fileRepo, attachments, currentUser, em);
            }
        } else { //if not delegate, then items are brand new: create new fileRepo
            fileRepo = fileRepoDAO.create(currentUser, "", fileNames, em);
        }

        //do persistence

        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            newItems = createItems(currentUser,
                    toUserEmailList,
                    supervisorEmailList,
                    tagList,
                    linkList,
                    _parent,
                    action,
                    subject,
                    startDate,
                    deadline,
                    em,
                    mergeParentChildSupervisors,
                    fileRepo,
                    priority);
            tx.commit();
            return newItems;

        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return newItems;
        } finally {
            em.close();
        }
    }

    private Set<Item> createItems(final String currentUser,
            List<String> toUserList,
            List<String> supervisorList,
            List<String> tagList,
            List<String> linkList,
            final Item parent,
            String action,
            String subject,
            Date startDate,
            Date deadline,
            EntityManager em,
            boolean mergeParentChildSupervisors,
            /*final List<String> fileNames*/
            final FileRepository fileRepository,
            Priority priority) {
        //ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        // FileRepositoryDAO fileRepoDAO = FileRepositoryDAOFactory.getInstance();


        //merge supervisor of child and parent if needed
        List<String> supervisors = new ArrayList<String>();
        if (supervisorList != null) {
            supervisors.addAll(supervisorList);
        }
        if (mergeParentChildSupervisors) {
            if (parent != null) {
                supervisors.addAll(parent.getSupervisors());
            }
        }

        //add current user as a supervisor too if we are about to save the item
        supervisors.add(currentUser);


        //establish tags
        Set<String> tags = new TreeSet<String>();
        if (tagList != null) {
            tags.addAll(tagList);
        }


        //get links
        Set<String> links = new TreeSet<String>();
        if (linkList != null) {
            links.addAll(linkList);
        }



//        if (parent != null) { //delegate item, fileRepo already exist in parent
//            fileRepo = parent.getFileRepository();
//            if (fileNames != null && !fileNames.isEmpty()) {
//                Set<Attachment> attachments = new TreeSet<Attachment>();
//                for (String fileName : fileNames) {
//                    Attachment attachment = attachmentDAO.createWithoutPersisting(fileName, currentUser);
//                    attachments.add(attachment);
//                }
//                fileRepoDAO.addNewAttachments(fileRepo, attachments, currentUser, em);
//            }
//        } else { //if not delegate, then items are brand new: create new fileRepo
//            fileRepo = fileRepository;//fileRepoDAO.create(currentUser, "", fileNames, em);
//        }


        Set<Item> newItems = new TreeSet<Item>();
        Set<String> toUsersSet = new TreeSet<String>();
        toUsersSet.addAll(toUserList);
        Set<String> supervisorsSet = new TreeSet<String>();
        if (!supervisors.isEmpty()){
            supervisorsSet.addAll(supervisors);
        }
        if (!toUsersSet.isEmpty()) {
            for (String toUser : toUsersSet) {
                personDAO.verifyUserExistAndInviteIfNot(toUser, toUser, currentUser);
                Item item = new Item();
                item.setId(UUID.randomUUID().toString());
                item.setRecipient(toUser);
                item.setLinks(new TreeSet<String>(links));
                item.setRecipients(toUsersSet);
                item.setSender(currentUser);
                item.setTask(action);
                item.getSupervisors().addAll(supervisorsSet);
                item.setTags(new TreeSet<String>(tags));
                item.setFileRepository(fileRepository);
                item.setSubject(subject);
                item.setDeadLine(deadline);
                item.setStartDate(startDate);
                item.setSentDate(new Date());
                if (deadline != null) {
                    if (toUser.equals(currentUser)) {
                        item.setStatus(Item.Status.ACCEPTED);
                    } else {
                        item.setStatus(Item.Status.UNCONFIRMED);
                    }
                } else {
                    item.setStatus(Item.Status.REFERENCE);
                    item.setStartDate(null);
                }
                if (parent != null) {
                    item.setParentId(parent.getId());
                    item.setId(parent.getId() + "|" + item.getId());
                    if ((Item.Status.NEED_REDO).equals(parent.getStatus())) {
                        parent.setStatus(Item.Status.NEED_REDO_DELEGATED);
                    } else if ((Item.Status.REFERENCE == parent.getStatus())) {
                        //do nothing
                    } else {
                        parent.setStatus(Item.Status.DELEGATED);
                    }
                    ChildItem childItem = new ChildItem();
                    childItem.setId(item.getId());
                    childItem.setRecipient(item.getRecipient());
                    childItem.setStatus(item.getStatus());

                    parent.addChild(childItem);
                }
                item.setPriority(priority);
                newItems.add(item);
            }
        } else {
            Item item = new Item();
            item.setId(UUID.randomUUID().toString());
            item.setRecipient(null);
            item.setLinks(new TreeSet<String>(links));
            item.setRecipients(toUsersSet);
            item.setSender(currentUser);
            item.setTask(action);
            item.getSupervisors().addAll(supervisorsSet);
            item.setTags(new TreeSet<String>(tags));
            item.setFileRepository(fileRepository);
            item.setSubject(subject);
            item.setDeadLine(deadline);
            item.setStartDate(startDate);
            item.setSentDate(new Date());
            item.setPriority(priority);
            if (deadline != null) {
                item.setStatus(Item.Status.UNCONFIRMED);
            } else {
                item.setStatus(Item.Status.REFERENCE);
            }
            if (parent != null) {
                item.setParentId(parent.getId());
                item.setId(parent.getId() + "|" + item.getId());
                if ((Item.Status.NEED_REDO == parent.getStatus())) {
                    parent.setStatus(Item.Status.NEED_REDO_DELEGATED);
                } else if ((Item.Status.REFERENCE == parent.getStatus())) {
                    //do nothing since parent is already REFERENCE
                } else {
                    parent.setStatus(Item.Status.DELEGATED);
                }
                ChildItem childItem = new ChildItem();
                childItem.setId(item.getId());
                childItem.setRecipient(item.getRecipient());
                childItem.setStatus(item.getStatus());

                parent.addChild(childItem);
            }
            newItems.add(item);
        }
        for (Item newItem : newItems) {
            em.persist(newItem);
        }
        if (parent != null) {
            em.merge(parent);
        }
        return newItems;
    }

    private void delete(final Item item, EntityManager em) {
        em.remove(item);
    }

    public void delete(final Item item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            delete(item, em);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            em.close();
        }
    }

    public void labelForArchive(final String _user, Item item){
         if (item != null) {
            //labelling (archived or not) if archive state should be shown
            if (_user.equals(item.getSender())) {
                //if item is archived
                if (item.isArchivedForSender() == true) {
                    //if item is archived
                    item.setArchived(true);
                } else {
                    item.setArchived(false);

                }
            } else if (_user.equals(item.getRecipient())) {
                if (item.isArchivedForRecipient() == true) {
                    item.setArchived(true);
                } else {
                    item.setArchived(false);
                }
            } else {
                //must be supervised
                item.setArchived(false);
            }
        }
    }

     public boolean labelAndFilterForArchive(final boolean _archiveIncluded, final boolean _referenceOnly, final String _user, Item ritem) {
        if (ritem == null) {
            return false;
        }
        if (_archiveIncluded) {
            //labelling (archived or not) if archive state should be shown
            if (_user.equals(ritem.getSender())) {
                //if item is archived
                if (ritem.isArchivedForSender() == true) {
                    //if item is archived
                    ritem.setArchived(true);

                } else {
                    ritem.setArchived(false);

                }
            } else if (_user.equals(ritem.getRecipient())) {
                if (ritem.isArchivedForRecipient() == true) {
                    ritem.setArchived(true);
                } else {
                    ritem.setArchived(false);
                }
            } else {
                //must be supervised
                ritem.setArchived(false);
            }
        } else {
            if (ritem.isArchivedForRecipient() == true) {
                if (ritem.getRecipient().equals(_user)) {
                    return false;
                }
            }
            if (ritem.isArchivedForSender() == true) {
                if (ritem.getSender().equals(_user)) {
                    return false;
                }
            }
        }
        if (_referenceOnly == true) {
            if (ritem.getDeadLine() != null) {
                return true;
            }
        }
        return false;
    }

    public void labelWithType(Item ritem, final String _user) {
        if (ritem.getRecipient() == null) {
            ritem.setType("SENT");
            return;
        }

        int itemTypeState = 0;
        if (ritem == null) {
            return;
        }
        //received item
        if (ritem.getRecipient().equals(_user)) {
            itemTypeState = 2;
        } else {
            itemTypeState = 1;
        }

        //sent item
        if (ritem.getSender().equals(_user)) {
            if (itemTypeState == 2) {
                itemTypeState = 3;
            } else {
                itemTypeState = 5;
            }
        } else {
            if (itemTypeState == 2) {
                itemTypeState = 4;
            } else {
                itemTypeState = 6;
            }
        }
        //supervised
        boolean isSupervisor = false;
        for (String p : ritem.getSupervisors()) {
            if (_user.equals(p)) {
                isSupervisor = true;
                break;
            }
        }

        if (isSupervisor) {
            if (itemTypeState == 6) {
                itemTypeState = 7;
            } else if (itemTypeState == 5) {
                itemTypeState = 9;
            }
        } else {
            if (itemTypeState == 6) {
                itemTypeState = 8;
            }
        }

        //do another labelling on item's type
        if (itemTypeState == 3) {
            if (ritem.getDeadLine() == null) {
                ritem.setType("REFERENCE - TO YOURSELF");
            } else {
                ritem.setType("TO YOURSELF");
            }
        } else if (itemTypeState == 4) {
            if (ritem.getDeadLine() == null) {
                ritem.setType("REFERENCE - RECEIVED");
            } else {
                ritem.setType("RECEIVED");
            }
        } else if (itemTypeState == 5) {
            if (ritem.getDeadLine() == null) {
                ritem.setType("REFERENCE - SENT");
            } else {
                ritem.setType("SENT");
            }
        } else if (itemTypeState == 7) {
            if (ritem.getDeadLine() == null) {
                ritem.setType("REFERENCE - SUPERVISED");
            } else {
                ritem.setType("SUPERVISED");
            }
        } else if (itemTypeState == 9) {
            if (ritem.getDeadLine() == null) {
                ritem.setType("REFERENCE - SENT AND SUPERVISED");
            } else {
                ritem.setType("SENT AND SUPERVISED");
            }
        }
    }

    public Item update(Item _item,
            String currentUser,
            boolean accept,
            boolean negotiate,
            boolean reject,
            Date negotiatedDeadline,
            String reasonNego,
            String reasonReject,
            String feedback,
            boolean feedbackApproved,
            String comments,
            Date startDate,
            Date endDate,
            Priority priority) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Item item = update(_item,
                    false,
                    currentUser,
                    accept,
                    negotiate,
                    reject,
                    negotiatedDeadline,
                    reasonNego,
                    reasonReject,
                    feedback,
                    feedbackApproved,
                    comments,
                    startDate,
                    endDate,
                    priority,
                    em);
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }

    private Item update(Item _item,
            boolean tryToCopy,
            String currentUser,
            boolean accept,
            boolean negotiate,
            boolean reject,
            Date negotiatedDeadline,
            String reasonNego,
            String reasonReject,
            String feedback,
            boolean feedbackApproved,
            String comments,
            Date startDate,
            Date endDate,
            Priority priority,
            EntityManager em) {
        boolean itemModified = false;
        boolean fileRepoModified = false;
        boolean isnew = false;


        JPAFileRepositoryDAO fileRepoDAO = new JPAFileRepositoryDAO();
        Item itemInDB = findItemById(_item.getId(), em);
        if (itemInDB == null) {
            //if item does not exist
            itemInDB = new Item();
            itemInDB.setId(_item.getId());
            //update with in file repo
            itemInDB.setFileRepository(fileRepoDAO.update(_item.getFileRepository(), currentUser, em));
            isnew = true;
        }



        if (tryToCopy == false && isnew == false) {
            labelWithType(_item, currentUser); //since we need to use the label
            if (_item.getType().contains("RECEIVED") || _item.getType().contains("TO YOURSELF")) {
                if (_item.getStatus().equals(Item.Status.UNCONFIRMED)) {
                    if (accept) {
                        itemInDB.setStatus(Item.Status.ACCEPTED);
                        itemModified = true;
                    } else if (negotiate) {
                        itemInDB.setStatus(Item.Status.NEGOTIATED);
                        itemInDB.setNegotiatedDeadLine(negotiatedDeadline);
                        itemInDB.setReasonForNegotiatiationOfDeadLine(reasonNego);
                        itemModified = true;
                    } else if (reject) {
                        itemInDB.setStatus(Item.Status.REJECTED);
                        itemInDB.setReasonForRejectionOfTask(reasonReject);
                        itemModified = true;
                    }
                } else if ((Item.Status.NEGOTIATED == _item.getStatus())) {
                } else if ((Item.Status.ACCEPTED == _item.getStatus())) {
                    if (_item.getType().equals("TO YOURSELF")) {
                        if (!itemInDB.getStartDate().equals(startDate)) {
                            itemInDB.setStartDate(startDate);
                        }
                        if (!itemInDB.getDeadLine().equals(endDate)) {
                            itemInDB.setDeadLine(endDate);
                        }

                        if (feedback != null && !("").equals(feedback) && itemInDB.getStartDate().equals(startDate) && itemInDB.getDeadLine().equals(endDate)) {
                            itemInDB.setFeedback(feedback);
                            itemInDB.setStatus(Item.Status.DONE_CONFIRMED);
                        }
                        itemModified = true;
                        fileRepoModified = true;
                    } else {
                        itemInDB.setFeedback(feedback);
                        itemInDB.setStatus(Item.Status.UNCONFIRMED);
                        itemModified = true;
                        fileRepoModified = true;
                    }
                } else if ((Item.Status.DELEGATED == _item.getStatus())) {
                    itemInDB.setFeedback(feedback);
                    itemInDB.setStatus(Item.Status.UNCONFIRMED);
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.REJECTED == _item.getStatus())) {
                } else if ((Item.Status.UNCONFIRMED == _item.getStatus())) {
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.DONE_CONFIRMED == _item.getStatus())) {
                } else if ((Item.Status.NEED_REDO == _item.getStatus())) {
                    itemInDB.setFeedback(feedback);
                    itemInDB.setStatus(Item.Status.DONE_UNCONFIRMED);
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.NEED_REDO_DELEGATED == _item.getStatus())) {
                    itemInDB.setFeedback(feedback);
                    itemInDB.setStatus(Item.Status.DONE_UNCONFIRMED);
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.REFERENCE == _item.getStatus())) {
                }
            } else if (_item.getType().contains("SENT") || _item.getType().contains("SUPERVISED")) { //for sent, supervised and sent-supervised
                if ((Item.Status.UNCONFIRMED == _item.getStatus())) {
                } else if ((Item.Status.NEGOTIATED == _item.getStatus())) {
                    if (("SUPERVISED").equals(_item.getType())) {
                    } else {
                        if (negotiatedDeadline != null) {
                            itemInDB.setDeadLine(negotiatedDeadline); //if the negotiated deadline has been accepted, copy it from negotiated to the real deadline
                            itemInDB.setStatus(Item.Status.ACCEPTED);
                            itemModified = true;
                        }
                    }
                } else if ((Item.Status.ACCEPTED == _item.getStatus())) {
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.DELEGATED == _item.getStatus())) {
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.REJECTED == _item.getStatus())) {
                } else if ((Item.Status.DONE_UNCONFIRMED == _item.getStatus())) {
                    if (feedbackApproved) {
                        itemInDB.setCommentsOnFeedback(comments);
                        itemInDB.setStatus(Item.Status.DONE_CONFIRMED);
                        itemModified = true;
                    } else {
                        itemInDB.setCommentsOnFeedback(comments);
                        itemInDB.setStatus(Item.Status.NEED_REDO);
                        itemModified = true;
                    }
                } else if ((Item.Status.DONE_CONFIRMED == _item.getStatus())) {
                } else if ((Item.Status.NEED_REDO == _item.getStatus())) {
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.NEED_REDO_DELEGATED == _item.getStatus())) {
                    itemModified = true;
                    fileRepoModified = true;
                } else if ((Item.Status.REFERENCE == _item.getStatus())) {
                }
            }
            if (priority != null) {
                itemInDB.setPriority(priority);
            }
            itemInDB.setType(_item.getType());
        } else {
            itemInDB.copyFrom(_item);
            labelWithType(itemInDB, currentUser); //since we need to use the label
        }
        _item.getFileRepository().mergeTempAttachments();

        //see if fileRepo from DB is different from interface
        Set<Attachment> differenceToBePersisted = new HashSet<Attachment>(_item.getFileRepository().getAttachments());
        differenceToBePersisted.removeAll(itemInDB.getFileRepository().getAttachments());

        Set<Attachment> differenceToBeDeleted = new HashSet<Attachment>(itemInDB.getFileRepository().getAttachments());
        differenceToBeDeleted.removeAll(_item.getFileRepository().getAttachments());
        if (!differenceToBeDeleted.isEmpty()) {
            fileRepoDAO.removeAttachmentsFromFileRepository(itemInDB.getFileRepository(), differenceToBeDeleted, currentUser, em);
        }
        if (!differenceToBePersisted.isEmpty()) {
            fileRepoDAO.addNewAttachments(itemInDB.getFileRepository(), differenceToBePersisted, currentUser, em);
        }

        //see if the item has parent, if yes, update the parent's child item
        if (itemInDB.getParentId() != null) {
            Item parent = findItemById(itemInDB.getParentId(), em);
            ChildItem child = parent.findChildById(itemInDB.getId());
            em.refresh(child);
            child.setStatus(itemInDB.getStatus());
            em.merge(child);
        }

        em.merge(itemInDB);
        return itemInDB;


    }

    public Item addTags(final Item item, Set<String> tags) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Set<String> currentTags = item.getTags();
            currentTags.addAll(tags);
            item.setTags(currentTags);
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            em.close();
        }
        return null;
    }

    public Item removeTag(final Item item, String tag) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Set<String> currentTags = item.getTags();
            currentTags.remove(tag);
            item.setTags(currentTags);
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            em.close();
        }
        return null;
    }

    public List<Item> unarchiveItems(final String currentUser, final List<Item> items) {
        return setItemsArchivedState(items, currentUser, false);
    }

    public List<Item> archiveItems(final String currentUser, final List<Item> items) {
        return setItemsArchivedState(items, currentUser, true);
    }

    private List<Item> setItemsArchivedState(final List<Item> items, final String currentUser, boolean state) throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Item> ritems = new ArrayList<Item>();
        try {
            tx.begin();
            for (Item item : items) {
                if (item.getStatus() == Item.Status.REJECTED || item.getStatus() == Item.Status.DONE_CONFIRMED) {
                    if (currentUser.equals(item.getRecipient())) {
                        // user is sender
                        item.setArchivedForRecipient(state);
                    } else if (currentUser.equals(item.getSender())) {
                        item.setArchivedForSender(state);
                    }
                    ritems.add(em.merge(item));
                }
            }
            tx.commit();
            return ritems;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return ritems;
        } finally {
            em.close();
        }
    }

    public Item updatePriority(Item item, Priority newPriority) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            item.setPriority(newPriority);
            em.merge(item);
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            em.close();
        }
        return null;
    }

    public Item updateFromFreshItem(Item itemFromNetwork, String username, FileRepository fileRepository) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();


            Item item = em.find(Item.class, itemFromNetwork.getId());
            item.setFileRepository(fileRepository);
            em.merge(item);

            em.flush();
            tx.commit();
            return item;
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            em.close();
        }
    }
}


