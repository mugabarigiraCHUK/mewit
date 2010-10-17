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
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
@Entity
public class FileRepository implements Serializable{
    @Id
    private String id;
    private String description;
    private String owner;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @OrderBy("uploadDate")
    private List<Attachment> attachments = new ArrayList<Attachment>();
    private transient List<Attachment> tempAttachments = new ArrayList<Attachment>();

    public FileRepository(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
    
//    public List<Attachment> getAttachmentsList(){
//        List<Attachment> atts = new ArrayList<Attachment>();
//        atts.addAll(getAttachments());
//        return atts;
//    }
//
//    public void setAttachmentsList(List<Attachment> atts){
//
//    }
    
    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (this.id == null){
            return false;
        }
        if (!(o instanceof FileRepository)) {
            return false;
        }
        FileRepository a = (FileRepository)o;
        if (this.id.equals(a.id)){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
   

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the tempAttachments
     */
    public List<Attachment> getTempAttachments() {
        if (tempAttachments==null){
            tempAttachments = new ArrayList<Attachment>();
        }
        return tempAttachments;
    }

    public List<Attachment> getTempAttachmentsList() {
        return new ArrayList(tempAttachments);
    }

    /**
     * @param tempAttachments the tempAttachments to set
     */
    public void setTempAttachments(List<Attachment> tempAttachments) {
        this.tempAttachments = tempAttachments;
    }

    public void mergeTempAttachments(){
        this.getAttachments().addAll(this.getTempAttachments());
        this.getTempAttachments().clear();
    }

   
}
