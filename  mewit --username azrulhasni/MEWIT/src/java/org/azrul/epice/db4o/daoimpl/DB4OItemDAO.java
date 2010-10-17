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

import org.azrul.epice.dao.ItemDAO;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.query.QueryResult;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.db4o.daoimpl.queryimpl.DB4OQueryRunner;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.ChildItem;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Item.Priority;
import org.azrul.epice.exception.RuleNonCompliantException;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.LogUtil;
////import org.azrul.epice.util.SendMailUtil;
//import org.azrul.epice.util.XMPPServices;
//import org.compass.core.CompassException;
//import org.compass.core.CompassSession;
//import org.compass.core.CompassTransaction;
//import org.jivesoftware.smack.XMPPConnection;

public class DB4OItemDAO implements ItemDAO, Serializable {

    private static String EPICE_URL;

    static {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        EPICE_URL = props.getString("EPICE_URL");
    }

    public Item updateFromFreshItem(Item itemFromNetwork, String username) {
        ObjectContainer db = DBUtil.getDB();
        //CompassSession compassSession = ItemIndexer.getCompass().openSession();

        return update(itemFromNetwork, true, username,
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
                db);
    }

    private Item findItemById(String id, ObjectContainer db) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        ObjectSet<Item> os = db.queryByExample(item);
        if (os.hasNext()) {
            return os.next();
        } else {
            return null;
        }
    }

    private Item findItemById(String id, int depth, ObjectContainer db) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        ObjectSet<Item> os = db.ext().queryByExample(item);
        if (os.hasNext()) {
            return os.next();
        } else {
            return null;
        }
    }

    @Override
    public Item findItemById(String id) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            return findItemById(id, db);
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public Item refresh(Item item) {
        if (item == null) {
            return null;
        }
        Item res = findItemById(item.getId());
        if (res == null) {
            return null;
        }
        res.setType(item.getType());
        return res;
    }

    private Item refresh(Item item, ObjectContainer db) {
        if (item == null) {
            return null;
        }
        Item res = findItemById(item.getId(), db);
        if (res == null) {
            return null;
        }
        res.setType(item.getType());
        return res;

    }

    public List<Item> refreshAll(List<Item> items) {
        List<Item> refreshed = new ArrayList<Item>();
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            for (Item _i : items) {
                Item i = refresh(_i, db);
                if (i != null) {
                    refreshed.add(i);
                }
            }
            return refreshed;
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return null;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public QueryResult runItemsQuery(String user, SearchItemsQuery query, int startRow, int resultCount) {
        List<Item> searchResult = new ArrayList<Item>();
        DB4OQueryRunner queryRunner = new DB4OQueryRunner();
        if (query != null) {
            searchResult = new ArrayList<Item>(queryRunner.run(query, user));
        }
        QueryResult res = new QueryResult();
        res.setItems(searchResult);
        res.setTotal(res.getTotal());
        res.setStartRow(0);
        return res;

    }

    public Item createItem(String currentUser, String id, String fileRepoId) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Item item = new Item();
            item.setId(id);
            db.store(item);
            return item;

        } catch (Exception ex) {
            if (db != null && db.close() == false) {
                db.rollback();
            }

//            if (compassTx != null) {
//                compassTx.rollback();
//            }

            throw new RuntimeException(ex);
        } finally {
            //clean up current file repo
            //getSessionBean1().setCurrentFileRepository(null);

            //clean up db
            if (db != null) {
                DBUtil.closeDB();
            }

            //clean up compass
//            if (compassSession != null) {
//                compassSession.close();
//            }
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

        ObjectContainer db = null;

//        CompassSession compassSession = ItemIndexer.getCompass().openSession();
//        CompassTransaction compassTx = null;
        Set<Item> newItems = null;
        try {
            db = DBUtil.getDB();

            //open tx for compass
//            compassTx = compassSession.beginTransaction();
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
                    db,
                    mergeParentChildSupervisors,
                    fileNames,
                    priority);


            //add to indexer for searching and save to db
            for (Item item : newItems) {
                db.store(item);
//                compassSession.save(item);
            }

            //persist the parent as well
            if (newItems.iterator().hasNext()) { //persist just one parent, since all the newitems has the same parent
                Item parent = findItemById(newItems.iterator().next().getParentId(), db);
                if (parent != null) {
                    db.ext().store(parent, 2);
                }
            }



            //compassTx.commit();
            db.commit();
            return newItems;
        } catch (RuleNonCompliantException re) {
            if (db != null) {
                db.rollback();
            }

//            if (compassTx != null) {
//                compassTx.rollback();
//            }
            throw re;

        } catch (Exception ex) {
            if (db != null && db.close() == false) {
                db.rollback();
            }

//            if (compassTx != null) {
//                compassTx.rollback();
//            }

            throw new RuntimeException(ex);
        } finally {
            //clean up current file repo
            //getSessionBean1().setCurrentFileRepository(null);

            //clean up db
            if (db != null) {
                DBUtil.closeDB();
            }

            //clean up compass
//            if (compassSession != null) {
//                compassSession.close();
//            }
        }
    }

    public Set<Item> createItems(String user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject, Date startDate, Date deadline, boolean mergeParentChildSupervisors, FileRepository fileRepository, Priority priority) {
        return new HashSet<Item>();
    }

    private Set<Item> createItems(final String currentUser,
            List<String> toUserList,
            List<String> supervisorList,
            List<String> tagList,
            List<String> linkList,
            final Item _parent,
            String action,
            String subject,
            Date startDate,
            Date deadline,
            ObjectContainer db,
            boolean mergeParentChildSupervisors,
            final List<String> fileNames,
            Priority priority)
            throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
        //ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        DB4OAttachmentDAO attachmentDAO = new DB4OAttachmentDAO();
        DB4OFileRepositoryDAO fileRepoDAO = new DB4OFileRepositoryDAO();
        PersonDAO personDAO = PersonDAOFactory.getInstance();

        Item parent = null;
        //establish parent
        if (_parent != null) {
            if (db != null) {
                parent = refresh(_parent, db);
            } else {
                parent = _parent;
            }
        }

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

        FileRepository fileRepo = null;
        //establish file repository


        if (parent != null) { //delegate item, fileRepo already exist in parent
            fileRepo = fileRepoDAO.refresh(parent.getFileRepository(), db);
            if (fileNames != null && !fileNames.isEmpty()) {
                Set<Attachment> attachments = new TreeSet<Attachment>();
                for (String fileName : fileNames) {
                    Attachment attachment = attachmentDAO.createWithoutPersisting(fileName, currentUser);
                    attachments.add(attachment);
                }
                fileRepoDAO.addNewAttachments(fileRepo, attachments, currentUser, db);
            }
        } else { //if not delegate, then items are brand new: create new fileRepo
            fileRepo = fileRepoDAO.create(currentUser, "", fileNames, db);
        }





        Set<Item> newItems = new TreeSet<Item>();
        Set<String> toUsersSet = new TreeSet<String>();
        toUsersSet.addAll(toUserList);
        Set<String> supervisorsSet = new TreeSet<String>();
        supervisorsSet.addAll(supervisors);
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
                item.setFileRepository(fileRepo);
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
            item.setFileRepository(fileRepo);
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
        return newItems;
    }

    private void delete(final Item _item,
            ObjectContainer db)
            throws DatabaseClosedException, DatabaseReadOnlyException, Db4oIOException {
        Item item = refresh(_item, db);
        //if item has a parent with only one child *i.e. the current item) switch its status back to 'sent-accepted' from 'delegated'
        Item _parent = findItemById(item.getParentId());
        if (_parent != null) {
            Item parent = refresh(_parent, db);
            if (parent.getChildren().size() == 1) {
                parent.setStatus(Item.Status.ACCEPTED);
            }
            Set<ChildItem> childrens = parent.getChildren();
            childrens.remove(item.getId());
            parent.setChildren(childrens);
            db.store(parent);
        }

        db.ext().delete(item);
//        compassSession.delete(item);
    }

    public void delete(final Item _item) {
        ObjectContainer db = null;
//        CompassTransaction compassTx = null;
//        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        try {
            db = DBUtil.getDB();
//            compassTx = compassSession.beginTransaction();
            delete(_item, db);
//            compassTx.commit();
            db.commit();
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }

//            if (compassTx != null) {
//                compassTx.rollback();
//            }

            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);

        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }

            //clean up compass
//            if (compassSession != null) {
//                compassSession.close();
//            }
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
                    return true;
                }
            }
            if (ritem.isArchivedForSender() == true) {
                if (ritem.getSender().equals(_user)) {
                    return true;
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
        return update(_item,
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
                DBUtil.getDB());
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
            ObjectContainer db) {
        boolean itemModified = false;
        boolean fileRepoModified = false;
        //ObjectContainer db = null;
//        CompassTransaction compassTx = null;
        //CompassSession compassSession = null;
        boolean isnew = false;
        try {
            //save all
            DB4OFileRepositoryDAO fileRepoDAO = new DB4OFileRepositoryDAO();
            //compassSession = ItemIndexer.getCompass().openSession();
            //db = DBUtil.getDB();
//            compassTx = compassSession.beginTransaction();

            Item itemInDB = findItemById(_item.getId(), db);
            if (itemInDB == null) {
                //if item does not exist
                itemInDB = new Item();
                itemInDB.setId(_item.getId());
                //update with in file repo
                itemInDB.setFileRepository(fileRepoDAO.update(_item.getFileRepository(), currentUser, db));
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
            Set<Attachment> differenceToBePersisted = new TreeSet<Attachment>(_item.getFileRepository().getAttachments());
            differenceToBePersisted.removeAll(itemInDB.getFileRepository().getAttachments());

            Set<Attachment> differenceToBeDeleted = new TreeSet<Attachment>(itemInDB.getFileRepository().getAttachments());
            differenceToBeDeleted.removeAll(_item.getFileRepository().getAttachments());
            if (!differenceToBeDeleted.isEmpty()) {
                fileRepoDAO.removeAttachmentFromFileRepository(itemInDB.getFileRepository(), differenceToBeDeleted, currentUser, db);
            }
            if (!differenceToBePersisted.isEmpty()) {
                fileRepoDAO.addNewAttachments(itemInDB.getFileRepository(), differenceToBePersisted, currentUser, db);
            }

            //send
//            if (connection != null) {
//                if (connection.getConnection() != null) {
//                    if (!("TO YOURSELF").equals(itemInDB.getType())) {
//                        if (itemModified == true) {
//
//                        }
//                    }
//                }
//            }
            db.store(itemInDB);
//            compassSession.save(itemInDB);
            db.commit();
//            compassTx.commit();
            return itemInDB;
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
//            if (compassTx != null) {
//                compassTx.rollback();
//            }
            throw new RuntimeException(ex);

        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
//            //clean up compass
//            if (compassSession != null) {
//                compassSession.close();
//            }
        }

    }

    public Item addTags(final Item _item, Set<String> tags) {
        ObjectContainer db = null;
//        CompassSession compassSession = ItemIndexer.getCompass().openSession();
//        CompassTransaction compassTx = null;
        try {
            //open tx for compass
//            compassTx = compassSession.beginTransaction();

            //get DB
            db = DBUtil.getDB();

            Item item = refresh(_item, db);
            if (item != null) {
                Set<String> currentTags = item.getTags();
                currentTags.addAll(tags);
                item.setTags(currentTags);
                db.ext().store(item, 2);
//                compassSession.save(item);
                db.commit();
//                compassTx.commit();
                return item;
            }

        } catch (Exception ex) {
            db.rollback();
//            compassTx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db
//            compassSession.close();
            DBUtil.closeDB();
        }
        return _item;
    }

    public Item removeTag(final Item _item, String tag) {
        ObjectContainer db = null;
//        CompassSession compassSession = ItemIndexer.getCompass().openSession();
//        CompassTransaction compassTx = null;
        try {
            //open tx for compass
//            compassTx = compassSession.beginTransaction();

            //get DB
            db = DBUtil.getDB();

            Item item = refresh(_item, db);
            if (item != null) {
                Set<String> currentTags = item.getTags();
                currentTags.remove(tag);
                item.setTags(currentTags);
                db.ext().store(item, 2);
//                compassSession.save(item);
                db.commit();
//                compassTx.commit();
                return item;
            }

        } catch (Exception ex) {
            db.rollback();
//            if (!compassTx.wasCommitted() && !compassTx.wasRolledBack()) {
//                compassTx.rollback();
//            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db

//            compassSession.close();
            DBUtil.closeDB();
        }
        return _item;
    }

    public List<Item> unarchiveItems(final String currentUser, final List<Item> items) {
        setItemsArchivedState(items, currentUser, false);
        return items;
    }

    public List<Item> archiveItems(final String currentUser, final List<Item> items) {
        setItemsArchivedState(items, currentUser, true);
        return items;
    }

    private void setItemsArchivedState(final List<Item> items, final String currentUser, boolean state) throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
        ObjectContainer db = null;
//        CompassTransaction compassTx = null;
//        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        try {
            db = DBUtil.getDB();
//            compassTx = compassSession.beginTransaction();
            for (Item _item : items) {
                Item item = findItemById(_item.getId(), db);
                if (item != null) {
                    if (currentUser.equals(item.getRecipient())) {
                        // user is sender
                        item.setArchivedForRecipient(state);
                    } else if (currentUser.equals(item.getSender())) {
                        item.setArchivedForSender(state);
                    }
                }
                db.store(item);
//                compassSession.save(item);
            }
            db.commit();
//            compassTx.commit();
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }

//            if (compassTx != null) {
//                compassTx.rollback();
//            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db
            if (db != null) {
                DBUtil.closeDB();
            }

            //clean up compass
//            if (compassSession != null) {
//                compassSession.close();
//            }
        }
    }

//    public Set<String> getAllTags(String currentUser) {
//        Set<String> tags = new TreeSet<String>();
//        ObjectContainer db = null;
//        try {
//            //get DB
//            db = DBUtil.getDB();
//            Item item = new Item();
//            item.setSender(currentUser);
//            ObjectSet<Item> os = db.queryByExample(item);
//            while (os.hasNext()) {
//                tags.addAll(((Item) os.next()).getTags());
//            }
//
//        } catch (Exception ex) {
//            db.rollback();
//            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
//        } finally {
//            //clean up db
//            DBUtil.closeDB();
//        }
//        return tags;
//    }
    public Item updatePriority(Item _item, Priority newPriority) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Item item = refresh(_item, db);
            item.setPriority(newPriority);
            db.store(item);
            db.commit();
            return item;
        } catch (Exception e) {
            if (db != null) {
                db.rollback();
            }
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
        return null;
    }

    public Item updateFromFreshItem(Item newItem, String username, FileRepository fileRepository) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Item> findItemsById(List<String> itemIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


