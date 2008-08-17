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

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;


/**
 *
 * @author Azrul Hasni MADISA
 */
@Searchable(root = false)
public class Attachment implements Serializable{
    private String id = null;
    private Person owner = null;
    @SearchableProperty
    private String file = null;
    private Date uploadDate = null;
    private transient boolean deletable;

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    public String getFilePath(){
        ResourceBundle props = ResourceBundle.getBundle("epice");
        return props.getString("FILE_REPOSITORY")+"/"+getFile();
    }
    
    public void setFilePath(String filePath){
        
    }
    
    public String getFileName() {
        if (getFile()==null){
            return null;
        }
        File f = new File(getFile());
        return f.getName();
    }

    public void setFileName(String name) {
      
    }
    
    @Override
    public boolean equals(Object o){
        Attachment a = (Attachment)o;
        if (this.id.equals(a.id)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    
}
