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

import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.dao.ItemDAO;
import com.db4o.DatabaseClosedException;
import com.db4o.DatabaseReadOnlyException;
import com.db4o.Db4oIOException;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import org.apache.lucene.store.LockObtainFailedException;
import org.azrul.epice.dao.query.ItemsQuery;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.util.ItemIndexer;
import org.azrul.epice.util.LogUtil;
import org.azrul.epice.util.SendMailUtil;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;
import org.compass.core.engine.SearchEngineException;

public class DB4OItemDAO implements ItemDAO, Serializable {

    private Item findItemById(String id, ObjectContainer db) {
        Item item = new Item();
        item.setId(id);
        ObjectSet<Item> os = db.get(item);
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

    public List<Item> refreshAndApplyFilters(List<Item> items, final Person currentUser, final List<ItemsFilter> queries) {
        List<Item> refreshed = new ArrayList<Item>();
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            for (final Item _i : items) {
                if (_i == null) {
                    continue;
                }
                if (_i.getId() == null) {
                    continue;
                }

                ObjectSet<Item> os = db.query(new Predicate<Item>() {

                    @Override
                    public boolean match(Item item) {
                        if (queries == null) {
                            return true;
                        }
                        if (queries.isEmpty()) {
                            return true;
                        }
                        if (_i.equals(item)) {
                            boolean res = false;
                            for (ItemsFilter query : queries) {
                                res = res || query.filter(item, currentUser);
                            }
                            return res;
                        } else {
                            return false;
                        }
                    }
                });

                refreshed.addAll(os);
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

    public int runCountItems(final Person user, final ItemsFilter itemsFilter) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            return db.query(new Predicate<Item>() {

                @Override
                public boolean match(Item item) {
                    //filter out archived 
                    if (item.isArchivedForRecipient() == true) {//we are excluding archived for reicipient

                        if (item.getToUser().equals(user)) { //item is a received item

                            return false; //exclude

                        }
                    }
                    if (item.isArchivedForSender() == true) {
                        if (item.getFromUser().equals(user)) {
                            return false;
                        }
                    }
                    return itemsFilter.filter(item, user);
                }
            }).size();
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return -1;
        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }
        }
    }

    public List<Item> runItemsQuery(Person user, ItemsQuery query) {
        return new ArrayList<Item>(query.run(user));
    }

    /*to be done outside
     * 1) Refresh current received item
     *    if we are delegating, then we will go back to PgReceivedItem,
    in which case we must set the current item back to parent
     * 2) Establish buddies
     *      each 'to user' becomes a buddy
     * 3) Marshall strings (toUsers' meemail, tags, supervisor's email to lists
     */
    @Override
    public Set<Item> createItems(final Person _user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, final Item _parent, String action, String subject, Date deadline, boolean mergeParentChildSupervisors, final FileRepository _fileRepo) {
//        int RETRY_MAX=1;
        ObjectContainer db = null;

        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        CompassTransaction compassTx = null;
        Set<Item> newItems = null;
//        int retryCount = 0;
//        boolean breakLoop = false;
//        while (breakLoop == false) {   
            try {
                db = DBUtil.getDB();

                //open tx for compass
                compassTx = compassSession.beginTransaction();
                newItems = createItems(_user, toUserEmailList, supervisorEmailList, tagList, linkList, _parent, action, subject, deadline, db, mergeParentChildSupervisors, _fileRepo);

                //add to indexer for searching and save to db
                for (Item item : newItems) {
                    db.set(item);
                    compassSession.save(item);
                }

                //persist the parent as well
                if (newItems.iterator().hasNext()) {
                    Item parent = newItems.iterator().next().getParent();
                    if (parent != null) {
                        db.ext().set(parent, 2);
                    }
                }
                compassTx.commit();
                db.commit();
                //breakLoop = true;
                
            }/* catch (SearchEngineException see) {
                if (see.contains(LockObtainFailedException.class)) {//if the error is due to some jammed lock
                    //ItemIndexer.getCompass().getSearchEngineIndexManager().releaseLocks(); //clear the lock
                    ResourceBundle props = ResourceBundle.getBundle("epice");
                    File lockFile = new File((String) props.getObject("ITEM_INDEX_FILE")+"/index/item/write.lock");
                    lockFile.delete();
                    if (retryCount<RETRY_MAX){
                        breakLoop = false; //retry
                        retryCount++;
                    }else{
                        breakLoop = true;
                    }
                    
                }
            }*/ catch (CompassException ce) {
                if (db != null) {
                    db.rollback();
                }

                if (compassTx != null) {
                    compassTx.rollback();
                }
                LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ce);
                return newItems;
            } catch (Exception ex) {
                if (db != null) {
                    db.rollback();
                }

                if (compassTx != null) {
                    compassTx.rollback();
                }

                LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
                return newItems;
            } finally {
                //clean up current file repo
                //getSessionBean1().setCurrentFileRepository(null);

                //clean up db
                if (db != null) {
                    DBUtil.closeDB();
                }

                //clean up compass
                if (compassSession != null) {
                    compassSession.close();
                }
            }
        //}
        return newItems;
    }

    public Set<Item> createItemsWithoutPersisting(Person user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, Item parent, String action, String subject, Date deadline, boolean mergeParentChildSupervisors, final FileRepository _fileRepo) {
        return createItems(user, toUserEmailList, supervisorEmailList, tagList, linkList, parent, action, subject, deadline, null, mergeParentChildSupervisors, _fileRepo);
    }

    private Set<Item> createItems(final Person _user, List<String> toUserEmailList, List<String> supervisorEmailList, List<String> tagList, List<String> linkList, final Item _parent, String action, String subject, Date deadline, ObjectContainer db, boolean mergeParentChildSupervisors, final FileRepository _fileRepo) throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
        ResourceBundle props = ResourceBundle.getBundle("epice");
        String emailInviteTextProto = props.getString("EMAIL_INVITE_TEXT");
        Set<Person> toUsers = new HashSet<Person>();
        DB4OPersonDAO personDAO = new DB4OPersonDAO();
        DB4OAttachmentDAO attachmentDAO = new DB4OAttachmentDAO();
        DB4OFileRepositoryDAO fileRepoDAO = new DB4OFileRepositoryDAO();
        Person user = null;
        //refresh user
        if (db != null) {
            user = personDAO.refresh(_user, db);
        } else {
            user = _user;
        }

        Item parent = null;
        //establish parent
        if (_parent != null) {
            if (db != null) {
                parent = refresh(_parent, db);
            } else {
                parent = _parent;
            }
        }

        //establish to users
        if (toUserEmailList != null) {

            for (String toEmail : toUserEmailList) {

                toEmail = toEmail.trim();
                toEmail = toEmail.toLowerCase();
                Person to = null;
                if (db != null) {
                    to = personDAO.findPersonByEmail(toEmail, db);
                    if (to != null) {
                        toUsers.add(to);
                    } else {
                        to = new Person();
                        if (toEmail.contains("@") && toEmail.contains(".")) {
                            String randPassword = UUID.randomUUID().toString();
                            to.setEmail(toEmail);
                            if (("true").equals(props.getString("DEMO_MODE"))) {
                                to.setPassword("abc123");
                            } else {
                                to.setPassword(randPassword);
                            }


                            String emailInviteText = new String(emailInviteTextProto);
                            emailInviteText = emailInviteText.replace("%%Link%%", props.getString("EPICE_URL"));
                            emailInviteText = emailInviteText.replace("%%email%%", to.getEmail());
                            emailInviteText = emailInviteText.replace("%%key%%", to.getPassword());
                            emailInviteText = emailInviteText.replace("%%Sender%%", user.getName());
                            SendMailUtil.send(user.getEmail(), toEmail, emailInviteText, props.getString("EMAIL_INVITE_TITLE"));
                            db.set(to);
                            to = (Person) db.get(to).next();
                            toUsers.add(to);
                        }

                    }
                } else {
                    to = new Person();
                    to.setEmail(toEmail);
                    toUsers.add(to);
                }
            }
        }

        //establish supervisors
        Set<Person> supervisors = new HashSet<Person>();

        if (supervisorEmailList != null) {

            for (String supEmail : supervisorEmailList) {
                supEmail = supEmail.trim();
                supEmail = supEmail.toLowerCase();

                if (db != null) {
                    Person sup = personDAO.findPersonByEmail(supEmail, db);
                    if (sup != null) {
                        supervisors.add(sup);
                    } else {
                        sup = new Person();
                        if (supEmail.contains("@") && supEmail.contains(".")) {
                            String randPassword = UUID.randomUUID().toString();
                            if (("true").equals(props.getString("DEMO_MODE"))) {
                                sup.setPassword("abc123");
                            } else {
                                sup.setPassword(randPassword);
                            }
                            //sup.setEmail(supEmail);
                            String emailInviteText = new String(emailInviteTextProto);
                            emailInviteText = emailInviteText.replace("%%Link%%", props.getString("EPICE_URL"));
                            emailInviteText = emailInviteText.replace("%%email%%", sup.getEmail());
                            emailInviteText = emailInviteText.replace("%%key%%", sup.getPassword());
                            emailInviteText = emailInviteText.replace("%%Sender%%", user.getName());
                            SendMailUtil.send(user.getEmail(), supEmail, emailInviteText, props.getString("EMAIL_INVITE_TITLE"));
                            db.set(sup);
                            sup = (Person) db.get(sup).next();
                            supervisors.add(sup);
                        }
                    }
                } else {
                    Person sup = new Person();
                    sup.setEmail(supEmail);
                    supervisors.add(sup);
                }
            }
        }
        if (mergeParentChildSupervisors) {
            if (parent != null) {
                if (db != null) {
                    for (Person s : parent.getSupervisors()) {
                        Person sup = (Person) db.get(s).next();
                        supervisors.add(sup);
                    }
                }
            }
        }
        //add current user as a supervisor too if we are about to save the item
        if (db != null) {
            supervisors.add(user);
        }

        //establish tags
        Set<String> tags = new HashSet<String>(tagList);


        //get links
        Set<String> links = new HashSet<String>(linkList);

        FileRepository fileRepo = null;
        //establish file repository
        if (db != null) {
            if (_fileRepo == null) {
                fileRepo = fileRepoDAO.create(user, "", db);
            } else {
                fileRepo = fileRepoDAO.refresh(_fileRepo, db);

                if (fileRepo == null) {
                    fileRepo = fileRepoDAO.create(_fileRepo.getOwner(), _fileRepo.getDescription(), db);
                }

                Set<Attachment> attachments = new HashSet<Attachment>();
                for (Attachment _at : _fileRepo.getAttachments()) {
                    Person fruser = null;
                    if (_at.getOwner() != null) {
                        fruser = personDAO.refresh(_at.getOwner(), db);
                    } else {
                        fruser = user;
                    }
                    _at.setOwner(fruser);
                    attachments.add(attachmentDAO.persistNew(_at, db));
                }
                fileRepo.setAttachments(attachments);

            }
            db.set(fileRepo);
        } else {
            fileRepo = _fileRepo;
        }




        //put them all together
        Set<Item> newItems = new HashSet<Item>();
        Set<Person> toUsersSet = new HashSet<Person>();
        toUsersSet.addAll(toUsers);
        Set<Person> supervisorsSet = new HashSet<Person>();
        supervisorsSet.addAll(supervisors);
        if (!toUsersSet.isEmpty()) {
            for (Person toUser : toUsersSet) {
                Item item = new Item();
                item.setId(UUID.randomUUID().toString());
                item.setToUser(toUser);
                item.setLinks(new HashSet<String>(links));
                item.setToUsers(toUsersSet);
                item.setFromUser(user);
                item.setDescription(action);
                item.getSupervisors().addAll(supervisorsSet);
                item.setTags(new HashSet<String>(tags));
                item.setFileRepository(fileRepo);
                item.setSubject(subject);
                item.setDeadLine(deadline);
                item.setSentDate(new Date());
                if (deadline != null) {
                    item.setStatus("SENT-UNCONFIRMED");
                } else {
                    item.setStatus("REFERENCE");
                }
                if (parent != null) {
                    item.setParent(parent);
                    item.setId(parent.getId() + "|" + item.getId());
                    if (("NEED-REDO").equals(parent.getStatus())) {
                        parent.setStatus("NEED-REDO DELEGATED");
                    } else if (("REFERENCE").equals(parent.getStatus())) {
                        //do nothing
                    } else {
                        parent.setStatus("DELEGATED");
                    }
                    parent.addChild(item);
                }

                newItems.add(item);
            }
        } else {
            Item item = new Item();
            item.setId(UUID.randomUUID().toString());
            item.setToUser(null);
            item.setLinks(new HashSet<String>(links));
            item.setToUsers(toUsersSet);
            item.setFromUser(user);
            item.setDescription(action);
            item.getSupervisors().addAll(supervisorsSet);
            item.setTags(new HashSet<String>(tags));
            item.setFileRepository(fileRepo);
            item.setSubject(subject);
            item.setDeadLine(deadline);
            item.setSentDate(new Date());
            if (deadline != null) {
                item.setStatus("SENT-UNCONFIRMED");
            } else {
                item.setStatus("REFERENCE");
            }
            if (parent != null) {
                item.setParent(parent);
                item.setId(parent.getId() + "|" + item.getId());
                if (("NEED-REDO").equals(parent.getStatus())) {
                    parent.setStatus("NEED-REDO DELEGATED");
                } else if (("REFERENCE").equals(parent.getStatus())) {
                    //do nothing since parent is already REFERENCE
                } else {
                    parent.setStatus("DELEGATED");
                }
                parent.addChild(item);
            }
            newItems.add(item);
        }



        return newItems;
    }

    private void delete(final Item _item, CompassSession compassSession, ObjectContainer db) throws DatabaseClosedException, DatabaseReadOnlyException, CompassException, Db4oIOException {
        Item item = refresh(_item, db);
        //if item has a parent with only one child *i.e. the current item) switch its status back to 'sent-accepted'
        Item _parent = item.getParent();
        if (_parent != null) {
            Item parent = refresh(_parent, db);
            if (parent.getChildren().size() == 1) {
                parent.setStatus("SENT-ACCEPTED");
            }
            Set<Item> children = parent.getChildren();
            children.remove(item);
            parent.setChildren(children);
            db.set(parent);
        }

        db.ext().delete(item);
        compassSession.delete(item);
    }

//    public void deleteAll(final List<Item> _items) {
//        ObjectContainer db = null;
//        CompassTransaction compassTx = null;
//        CompassSession compassSession = ItemIndexer.getCompass().openSession();
//        try {
//            db = DBUtil.getDB();
//            compassTx = compassSession.beginTransaction();
//
//            for (Item _item : _items) {
//                delete(_item, compassSession, db);
//            }
//            compassTx.commit();
//            db.commit();
//        } catch (CompassException ce) {
//            if (db != null) {
//                db.rollback();
//            }
//
//            if (compassTx != null) {
//                compassTx.rollback();
//            }
//            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ce);
//        } catch (Exception ex) {
//            if (db != null) {
//                db.rollback();
//            }
//
//            if (compassTx != null) {
//                compassTx.rollback();
//            }
////            if (dbArch != null) {
////                dbArch.rollback();
////            }
//            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
//
//        } finally {
//            if (db != null) {
//                DBUtil.closeDB();
//            }
//
//            //clean up compass
//            if (compassSession != null) {
//                compassSession.close();
//            }
//        }
//    }

    public void delete(final Item _item) {
        ObjectContainer db = null;
        CompassTransaction compassTx = null;
        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        try {
            db = DBUtil.getDB();
            compassTx = compassSession.beginTransaction();
            delete(_item, compassSession, db);
            compassTx.commit();
            db.commit();
        } catch (CompassException ce) {
            if (db != null) {
                db.rollback();
            }

            if (compassTx != null) {
                compassTx.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ce);
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }

            if (compassTx != null) {
                compassTx.rollback();
            }
//            if (dbArch != null) {
//                dbArch.rollback();
//            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);

        } finally {
            if (db != null) {
                DBUtil.closeDB();
            }

            //clean up compass
            if (compassSession != null) {
                compassSession.close();
            }
        }
    }

    @Override
    public Item updateSentItem(final Item _sentItem, Date negotiatedDeadLine, Boolean isApproved, String comments) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Item sentItem = findItemById(_sentItem.getId(), db);
            if (sentItem != null) {
                if (("SENT-UNCONFIRMED").equals(sentItem.getStatus())) {
                } else if (("SENT-NEGOTIATED").equals(sentItem.getStatus())) {
                    // if ((new Boolean(true)).equals((Boolean) cbApproveNegoDeadline.getSelected())) {
                    sentItem.setDeadLine(negotiatedDeadLine);
                    sentItem.setStatus("SENT-ACCEPTED");
                // }
                } else if (("SENT-ACCEPTED").equals(sentItem.getStatus())) {
                } else if (("DELEGATED").equals(sentItem.getStatus())) {
                } else if (("SENT-REJECTED").equals(sentItem.getStatus())) {
                } else if (("DONE-UNCONFIRMED").equals(sentItem.getStatus())) {
                    if ((new Boolean(true)).equals(isApproved)) {
                        sentItem.setCommentsOnFeedback(comments);
                        sentItem.setStatus("DONE-CONFIRMED");
                    } else {
                        sentItem.setCommentsOnFeedback(comments);
                        sentItem.addRedoCounter();
                        sentItem.setStatus("NEED-REDO");
                    }
                } else if (("DONE-CONFIRMED").equals(sentItem.getStatus())) {
                } else if (("NEED-REDO").equals(sentItem.getStatus()) || ("NEED-REDO DELEGATED").equals(sentItem.getStatus())) {
                }
                db.set(sentItem);
                db.commit();
            }
            return sentItem;
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            DBUtil.closeDB();
        }
        return null;
    }

    public Item updateReceivedItem(final Item _receivedItem, final String acceptReject, final Date selectedDate, final String reasonNegoReject, final String feedback) {
        ObjectContainer db = null;
        try {
            db = DBUtil.getDB();
            Item receivedItem = new Item();
            receivedItem.setId(_receivedItem.getId());

            if (db.get(receivedItem).hasNext()) {
                receivedItem = (Item) db.get(receivedItem).next();
                if (("SENT-UNCONFIRMED").equals(receivedItem.getStatus())) {
                    receivedItem.setStatus(acceptReject);
                    if (("SENT-NEGOTIATED").equals(acceptReject)) {
                        receivedItem.setNegotiatedDeadLine(selectedDate);
                        receivedItem.setReasonForNegotiatiationOfDeadLine(reasonNegoReject);
                    } else if (("SENT-REJECTED").equals(acceptReject)) {
                        receivedItem.setReasonForRejectionOfTask(reasonNegoReject);
                    } // Retrieve Value from Session

                } else if (("SENT-NEGOTIATED").equals(receivedItem.getStatus())) {
                    //user is waiting for reply to his negotiation, don't do anything
                } else if (("SENT-ACCEPTED").equals(receivedItem.getStatus())) {
                    if (receivedItem.getFromUser().equals(receivedItem.getToUser())) { //if sent to self

                        receivedItem.setStatus("DONE-CONFIRMED");//jump directly to DONE-CONFIRMED

                    } else {
                        receivedItem.setStatus("DONE-UNCONFIRMED");
                    }
                    receivedItem.setFeedback(feedback);
                } else if (("DELEGATED").equals(receivedItem.getStatus())) {
                    if (receivedItem.getFromUser().equals(receivedItem.getToUser())) { //if sent to self

                        receivedItem.setStatus("DONE-CONFIRMED");//jump directly to DONE-CONFIRMED

                    } else {
                        receivedItem.setStatus("DONE-UNCONFIRMED");
                    }
                } else if (("SENT-REJECTED").equals(receivedItem.getStatus())) {
                } else if (("DONE-UNCONFIRMED").equals(receivedItem.getStatus())) {
                } else if (("DONE-CONFIRMED").equals(receivedItem.getStatus())) {
                } else if (("NEED-REDO").equals(receivedItem.getStatus()) || ("NEED-REDO DELEGATED").equals(receivedItem.getStatus())) {
                    if (receivedItem.getFromUser().equals(receivedItem.getToUser())) { //if sent to self

                        receivedItem.setStatus("DONE-CONFIRMED");//jump directly to DONE-CONFIRMED

                    } else {
                        receivedItem.setStatus("DONE-UNCONFIRMED");
                    }
                }
                db.set(receivedItem);
                db.commit();
            }
            return receivedItem;
        } catch (Exception ex) {
            db.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db
            DBUtil.closeDB();
        }
        return null;
    }

    public Item addTags(final Item _item, Set<String> tags) {
        ObjectContainer db = null;
        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        CompassTransaction compassTx = null;
        try {
            //open tx for compass
            compassTx = compassSession.beginTransaction();

            //get DB
            db = DBUtil.getDB();

            Item item = refresh(_item, db);
            if (item != null) {
                Set<String> currentTags = item.getTags();
                currentTags.addAll(tags);
                item.setTags(currentTags);
                db.ext().set(item, 2);
                compassSession.save(item);
                db.commit();
                compassTx.commit();
                return item;
            }

        } catch (Exception ex) {
            db.rollback();
            compassTx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db
            compassSession.close();
            DBUtil.closeDB();
        }
        return _item;
    }

    public Item removeTag(final Item _item, String tag) {
        ObjectContainer db = null;
        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        CompassTransaction compassTx = null;
        try {
            //open tx for compass
            compassTx = compassSession.beginTransaction();

            //get DB
            db = DBUtil.getDB();

            Item item = refresh(_item, db);
            if (item != null) {
                Set<String> currentTags = item.getTags();
                currentTags.remove(tag);
                item.setTags(currentTags);
                db.ext().set(item, 2);
                compassSession.save(item);
                db.commit();
                compassTx.commit();
                return item;
            }

        } catch (Exception ex) {
            db.rollback();
            if (!compassTx.wasCommitted() && !compassTx.wasRolledBack()) {
                compassTx.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db

            compassSession.close();
            DBUtil.closeDB();
        }
        return _item;
    }

    public void unarchiveItems(final Person currentUser, final List<Item> items) {
        setItemsArchivedState(items, currentUser, false);
    }

    public void archiveItems(final Person currentUser, final List<Item> items) {
        setItemsArchivedState(items, currentUser, true);
    }

    private void setItemsArchivedState(final List<Item> items, final Person currentUser, boolean state) throws Db4oIOException, DatabaseClosedException, DatabaseReadOnlyException {
        ObjectContainer db = null;
        CompassTransaction compassTx = null;
        CompassSession compassSession = ItemIndexer.getCompass().openSession();
        try {
            db = DBUtil.getDB();
            compassTx = compassSession.beginTransaction();
            for (Item _item : items) {
                Item item = findItemById(_item.getId(), db);
                if (item != null) {
                    if (currentUser.equals(item.getToUser())) {
                        // user is sender
                        item.setArchivedForRecipient(state);
                    } else if (currentUser.equals(item.getFromUser())) {
                        item.setArchivedForSender(state);
                    }
                }
                db.set(item);
                compassSession.save(item);
            }
            db.commit();
            compassTx.commit();
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }

            if (compassTx != null) {
                compassTx.rollback();
            }
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db
            if (db != null) {
                DBUtil.closeDB();
            }

            //clean up compass
            if (compassSession != null) {
                compassSession.close();
            }
        }
    }

    public Set<String> getAllTags() {
        Set<String> tags = new HashSet<String>();
        ObjectContainer db = null;
        try {
            //get DB
            db = DBUtil.getDB();
            ObjectSet<Item> os = db.get(new Item());
            while (os.hasNext()) {
                tags.addAll(((Item) os.next()).getTags());
            }

        } catch (Exception ex) {
            db.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            //clean up db
            DBUtil.closeDB();
        }
        return tags;
    }
}


