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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Azrul Hasni MADISA
 */
@Entity
public class Attachment implements Serializable, Comparable {

    @Id
    private String id = null;
    private String owner = null;
    private String file = null;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date uploadDate = null;
    @Transient
    private String upload_date = null;
    @Transient
    private String owner_id = null;
    private transient boolean deletable;
    private transient File localFile = null;

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

    public String getClientFilePath(String currentUser) {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        return props.getString("FILE_REPOSITORY") + "/" + currentUser + "/" + getFile();
    }

    public String getFilePath() {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        return props.getString("FILE_REPOSITORY") + "/" + getFile();
    }

    public void setFilePath(String filePath){

    }

    public String getFileName() {
        if (getFile() == null) {
            return null;
        }
        File f = new File(getFile());
        return f.getName();
    }

    public void setFileName(String fileName){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.id == null) {
            return false;
        }
        if (o instanceof Attachment) {
            Attachment a = (Attachment) o;
            if (this.id.equals(a.id)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
       // this.owner_id = owner.substring(0,10);
    }

    public boolean getDeletable() {
        return isDeletable();
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.uploadDate = uploadDate;
        this.upload_date = sdf.format(uploadDate);

    }

    /**
     * @return the localFile
     */
    public File getLocalFile() {
        return localFile;
    }

    /**
     * @param localFile the localFile to set
     */
    public void setLocalFile(File localFile) {
        this.localFile = localFile;
    }

    public int compareTo(Object o) {
        if (o instanceof Attachment) {
            Attachment att = (Attachment)o;
            int compareResult = this.getUploadDate().compareTo(att.getUploadDate());
            if (compareResult == 0) {
                return this.getId().compareTo(att.getId());
            } else {
                return compareResult;
            }
        }else{
            return -1;
        }
    }

    /**
     * @return the upload_date
     */
    public String getUpload_date() {
        return upload_date;
    }

    /**
     * @param upload_date the upload_date to set
     */
    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    

    /**
     * @return the deletable
     */
    public boolean isDeletable() {
        return deletable;
    }
}
