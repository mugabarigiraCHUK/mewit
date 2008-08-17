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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

@Searchable
public class Item implements Serializable, Comparable<Item> {

    @SearchableId
    private String id;
    @SearchableComponent
    private Person fromUser;
    private Set<Person> toUsers = new HashSet<Person>();
    @SearchableComponent
    private Person toUser;
    @SearchableProperty
    private String subject;
    @SearchableProperty(format = "yyyy-MM-dd")
    private Date sentDate;
    @SearchableProperty(format = "yyyy-MM-dd")
    private Date deadLine;
    @SearchableProperty
    private String status;
    @SearchableProperty
    private String description;
    @SearchableProperty(format = "yyyy-MM-dd")
    private Date negotiatedDeadLine;
    @SearchableProperty
    private String feedback;
    private Set<String> links = new HashSet<String>();
    private Item parent;
    @SearchableProperty
    private Set<String> tags = new HashSet<String>();
    private Set<Person> supervisors = new HashSet<Person>();
    @SearchableProperty
    private String commentsOnFeedback;
    @SearchableProperty
    private String reasonForNegotiatiationOfDeadLine;
    @SearchableProperty
    private String reasonForRejectionOfTask;
    private Set<Item> children = new HashSet<Item>();
    private int redoCounter = 0;
    transient private String type = "";
    private FileRepository fileRepository;
    private boolean archivedForSender;
    private boolean archivedForRecipient;
    private transient boolean archived;

    public Item() {
    }

    public Item(Item item) {
        this.fromUser = item.fromUser;
        this.toUsers.addAll(item.toUsers);
        this.toUser = item.toUser;
        this.subject = item.subject;
        this.sentDate = item.sentDate;
        this.deadLine = item.deadLine;
        this.status = item.status;
        this.description = item.description;
        this.negotiatedDeadLine = item.negotiatedDeadLine;
        this.feedback = item.feedback;
        this.links.addAll(item.links);
        this.parent = item.parent;
        this.children.addAll(item.children);
        this.supervisors.addAll(item.supervisors);
        this.redoCounter = item.redoCounter;
        this.reasonForNegotiatiationOfDeadLine = item.reasonForNegotiatiationOfDeadLine;
        this.reasonForRejectionOfTask = item.reasonForRejectionOfTask;
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

  

    public void setToUsers(Set<Person> toUsers) {
        this.toUsers = toUsers;
    }

    public Set<Person> getToUsers() {
        return toUsers;
    }
    
    public void setToUsersList(List<Person> toUsers) {
        this.toUsers = new HashSet<Person>(toUsers);
    }

    public List<Person> getToUsersList() {
        return new ArrayList<Person>(toUsers);
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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
            links = new HashSet<String>();
        }
        return links;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public Item getParent() {
        return parent;
    }
    
    public void setFromUser(Person fromUser) {
        this.fromUser = fromUser;
    }

    public Person getFromUser() {
//        if (fromUser==null){
//            Person nobody =  new Person();
//            nobody.setEmail("hfiwehiwh"); //some random string
//            return nobody;
//        }
        return fromUser;
    }
    
    

    @Override
    public boolean equals(Object object) {
        Item item = (Item) object;
        return this.getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    

    public void setToUser(Person toUser) {
        this.toUser = toUser;
    }

    public Person getToUser() {
//         if (toUser==null){
//            Person nobody =  new Person();
//            nobody.setEmail("hfiwehiwh"); //some random string
//            return nobody;
//        }
        return toUser;
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

    public Set<Person> getSupervisors() {
        if (supervisors==null){
            supervisors = new HashSet<Person>();
        }
        return supervisors;
    }

    public void setSupervisors(Set<Person> supervisors) {
        this.supervisors = supervisors;
    }

    public Set<Item> getChildren() {
         if (children==null){
            children = new HashSet<Item>();
        }
        return children;
    }
    
    public boolean getDeletable(){
        if (("SENT-UNCONFIRMED").equals(getStatus())){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean getNotDeletable(){
        return !getDeletable();
    }

    public void setChildren(Set<Item> children) {
        this.children = children;
    }

    public void addChild(Item child) {
        this.children.add(child);
    }

    public String getLevels() {
        Item i = this;
        String level = "=>";
        while (i.getParent() != null) {
            i = i.getParent();
            level = level + "=>";
        }
        return level;
    }

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
        return getId() + "|" + getSubject() + "|" + getStatus() + "|" + getDescription() + "|" + getCommentsOnFeedback() + "|" + getFeedback() + "|" + getReasonForNegotiatiationOfDeadLine() + "|" + getReasonForRejectionOfTask();
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
        if (("DONE-CONFIRMED").equals(getStatus()) || ("DONE-UNCONFIRMED").equals(getStatus())) {
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

    

   
}
