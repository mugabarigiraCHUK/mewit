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

package org.azrul.epice.domain;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

public class Item implements Serializable, Comparable<Item> {

    /**
     * @return the priority
     */
    public Priority getPriority() {
        if(priority==null){
            priority = Priority.NOT_SET;
        }
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    

    public enum Priority {NOT_SET,HIGH,MEDIUM,LOW,LONGTERM};
    public enum Status {NONE,UNCONFIRMED,ACCEPTED,NEGOTIATED,DELEGATED,REJECTED,DONE_UNCONFIRMED,DONE_CONFIRMED,NEED_REDO,NEED_REDO_DELEGATED,REFERENCE};
   
    private String id;
    private String sender;
    private Set<String> recipients = new TreeSet<String>();
    private String recipient;
    private String subject;
  
    private Date sentDate;
    private Date deadLine;
    private Date startDate;
    private Status status;
    private String task;
    private Date negotiatedDeadLine;
    private String feedback;
    private String commentsOnFeedback;
    private String reasonForNegotiatiationOfDeadLine;
    private String reasonForRejectionOfTask;
    private int redoCounter = 0;
    private FileRepository fileRepository;
    private boolean archivedForSender;
    private boolean archivedForRecipient;
    private String readState;
    private String parentId;
    private String type = "";
    private boolean archived;
    private Priority priority;

    private Set<String> links = new TreeSet<String>();
    private Set<String> tags = new TreeSet<String>();
    private Set<ChildItem> children = new TreeSet<ChildItem>();
    private Set<String> supervisors = new TreeSet<String>();


    public Item() {
        //priority=Priority.MEDIUM;
        //childrenSortedImage.addAll(children);
        super();
    }

    public Item(Item item) {
        copyFrom(item);
    }

    public void copyFrom(Item item){
        this.sender = item.sender;
        this.recipients.addAll(item.recipients);
        this.recipient = item.recipient;
        this.subject = item.subject;
        this.sentDate = item.sentDate;
        this.deadLine = item.deadLine;
        this.startDate = item.startDate;
        this.status = item.status;
        this.task = item.task;
        this.negotiatedDeadLine = item.negotiatedDeadLine;
        this.feedback = item.feedback;
        this.links.addAll(item.links);
        this.tags = item.tags;
        this.getChildren().addAll(item.getChildren());
        this.supervisors.addAll(item.supervisors);
        this.commentsOnFeedback = item.commentsOnFeedback;
        this.reasonForNegotiatiationOfDeadLine = item.reasonForNegotiatiationOfDeadLine;
        this.reasonForRejectionOfTask = item.reasonForRejectionOfTask;
        this.redoCounter = item.redoCounter;
        this.archivedForSender = item.archivedForSender;
        this.archivedForRecipient= item.archivedForRecipient;
        this.parentId=item.parentId; //we use parentId instead of real parent to avoid circular references
        //this.priority = item.priority;
    }

 

    public Item(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

  

    public void setRecipients(Set<String> toUsers) {
        this.recipients = toUsers;
    }

    public Set<String> getRecipients() {
        return recipients;
    }
    
    public void setRecipientsList(List<String> toUsers) {
        this.recipients = new TreeSet<String>(toUsers);
    }

    public List<String> getRecipientsList() {
        return new ArrayList<String>(recipients);
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setTask(String description) {
        this.task = description;
    }

    public String getTask() {
        return task;
    }

    public void setNegotiatedDeadLine(Date negotiatedDeadLine) {
        this.negotiatedDeadLine = negotiatedDeadLine;
    }

    public Date getNegotiatedDeadLine() {
        return negotiatedDeadLine;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setLinks(Set<String> links) {
        this.links = links;
    }

    public Set<String> getLinks() {
        if (links==null){
            links = new TreeSet<String>();
        }
        return links;
    }

     public List<String> getLinksList(){
        List<String> linkslist = new ArrayList<String>();
        linkslist.addAll(getLinks());
        return linkslist;
    }
    
    public void setSender(String fromUser) {
        this.sender = fromUser;
    }

    public String getSender() {
        return sender;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (this.id == null){
            return false;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return this.getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    

    public void setRecipient(String toUser) {
        this.recipient = toUser;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setCommentsOnFeedback(String commentsOnFeedback) {
        this.commentsOnFeedback = commentsOnFeedback;
    }

    public String getCommentsOnFeedback() {
        return commentsOnFeedback;
    }

    public void setReasonForNegotiatiationOfDeadLine(String reasonForNegotiatiationOfDeadLine) {
        this.reasonForNegotiatiationOfDeadLine = reasonForNegotiatiationOfDeadLine;
    }

    public String getReasonForNegotiatiationOfDeadLine() {
        return reasonForNegotiatiationOfDeadLine;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getTags() {
        return tags;
    }

     public List<String> getTagsList(){
        List<String> tagslist = new ArrayList<String>();
        tagslist.addAll(getTags());
        return tagslist;
    }

    public Set<String> getSupervisors() {
        if (supervisors==null){
            supervisors = new TreeSet<String>();
        }
        return supervisors;
    }

    public List<String> getSupervisorsList(){
        List<String> supervisorslist = new ArrayList<String>();
        supervisorslist.addAll(getSupervisors());
        return supervisorslist;
    }

    public void setSupervisors(Set<String> supervisors) {
        this.supervisors = supervisors;
    }

     public void setChildren(Set<ChildItem> children) {
        this.children = children;
    }

    public void addChild(ChildItem childItem) {
        this.getChildren().add(childItem);
    }

    public Set<ChildItem> getChildren() {
         if (children==null){
            children = new TreeSet<ChildItem>();
        }
        return children;
    }

   
    
    public boolean getDeletable(){
        if ((Item.Status.UNCONFIRMED).equals(getStatus())){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean getNotDeletable(){
        return !getDeletable();
    }

   

//    public String getLevels() {
//        Item i = this;
//        String level = "=>";
//        while (i.getParent() != null) {
//            i = i.getParent();
//            level = level + "=>";
//        }
//        return level;
//    }

    public int compareTo(Item o) {
        return this.getId().compareTo(o.getId());
    }

    public int getRedoCounter() {
        return redoCounter;
    }

    public void setRedoCounter(int redoCounter) {
        this.redoCounter = redoCounter;
    }

    public void addRedoCounter() {
        this.redoCounter++;
    }

    @Override
    public String toString() {
        return getId() + "|" + getSubject() + "|" + getStatus() + "|" + getTask() + "|" + getCommentsOnFeedback() + "|" + getFeedback() + "|" + getReasonForNegotiatiationOfDeadLine() + "|" + getReasonForRejectionOfTask();
    }

    public String getReasonForRejectionOfTask() {
        return reasonForRejectionOfTask;
    }

    public void setReasonForRejectionOfTask(String reasonForRejectionOfTask) {
        this.reasonForRejectionOfTask = reasonForRejectionOfTask;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeadlineStatus() {
        GregorianCalendar today = (GregorianCalendar) GregorianCalendar.getInstance();
        today.setTime(new Date());

        GregorianCalendar duedate = (GregorianCalendar) GregorianCalendar.getInstance();
        if (getDeadLine() != null) {
            duedate.setTime(getDeadLine());
        } else {
            return "DEADLINE NOT SET";
        }
        if ((Item.Status.DONE_CONFIRMED).equals(getStatus()) || (Item.Status.DONE_UNCONFIRMED).equals(getStatus())) {
            return "DONE";
        } else {
            if (duedate.before(today)) {
                return "OVERDUE";
            } else {
                duedate.set(GregorianCalendar.HOUR_OF_DAY, 0);
                duedate.set(GregorianCalendar.MINUTE, 0);
                duedate.set(GregorianCalendar.SECOND, 0);
                duedate.set(GregorianCalendar.MILLISECOND, 0);
                today.set(GregorianCalendar.HOUR_OF_DAY, 0);
                today.set(GregorianCalendar.MINUTE, 0);
                today.set(GregorianCalendar.SECOND, 0);
                today.set(GregorianCalendar.MILLISECOND, 0);
                long difference = duedate.getTime().getTime() - today.getTime().getTime();
                long days = Math.round(((Math.abs(difference)) / (1000 * 60 * 60 * 24)))+1; 
                if (days == 1){
                    return "DUE TODAY";
                }else if (days == 0){
                    return "OVERDUE";
                }else if (days == 2 ){
                    return "DUE TOMORROW";
                }else{
                    return "DUE IN " + days + " DAYS";
                }
            }
        }

    }

    public void setDeadlineStatus(String str) {
    //do nothing
    }

    public FileRepository getFileRepository() {
        return fileRepository;
    }

    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public boolean isArchivedForSender() {
        return archivedForSender;
    }

    public void setArchivedForSender(boolean archivedForSender) {
        this.archivedForSender = archivedForSender;
    }

    public boolean isArchivedForRecipient() {
        return archivedForRecipient;
    }

    public void setArchivedForRecipient(boolean archivedForRecipient) {
        this.archivedForRecipient = archivedForRecipient;
    }

    public String getSubject() {
        if (subject==null){
            return "";
        }
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isReference() {
        return deadLine==null;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the readState
     */
    public String getReadState() {
        return readState;
    }

    /**
     * @param readState the readState to set
     */
    public void setReadState(String readState) {
        this.readState = readState;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ChildItem findChildById(String id){
        for (ChildItem c:children){
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

//    /**
//     * @return the children
//     */
//    public Set<Item> getChildren() {
//        if (children == null){
//            children = new TreeSet<Item>();
//        }
//        return children;
//    }
//
//    /**
//     * @param children the children to set
//     */
//    public void setChildren(Set<Item> children) {
//        this.children = children;
//        this.childrenSortedImage.addAll(children);
//    }

   

   

    

   
}
