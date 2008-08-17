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
import org.azrul.epice.web.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableProperty;

@Searchable(root = false)
public class FileRepository implements Serializable{
    private String id;
    @SearchableProperty
    private String description;
    private Person owner;
    @SearchableComponent
    private Set<Attachment> attachments = new HashSet<Attachment>();
    private transient AttachmentDataProvider dataProvider = new AttachmentDataProvider();

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

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }
    
    public List<Attachment> getAttachmentsList(){
        List<Attachment> atts = new ArrayList<Attachment>();
        atts.addAll(getAttachments());
        return atts;
    }
    
    public void setAttachmentsList(List<Attachment> atts){
        
    }
    
    @Override
    public boolean equals(Object o){
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
    
    public AttachmentDataProvider dataProvider(){
        return dataProvider;
    }
    
    public void refreshDataProvider(Person currentUser){
        if (dataProvider == null){
            dataProvider = new AttachmentDataProvider();
        }
        if (attachments!=null && !attachments.isEmpty()){
            dataProvider.setList(new ArrayList(attachments));
        }
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
