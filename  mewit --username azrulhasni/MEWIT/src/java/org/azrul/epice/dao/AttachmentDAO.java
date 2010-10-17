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

package org.azrul.epice.dao;


import java.util.List;
import java.util.Set;
import org.azrul.epice.domain.Attachment;

/**
 *
 * @author Azrul Hasni MADISA
 */
public interface AttachmentDAO {
    Attachment create(String attachmentID, String filename, String ownerEmail);

    Attachment createWithoutPersisting(String file, String ownerEmail);

    Attachment findAttachmentById(String id);

    Set<Attachment> findAttachmentsByIds(List<String> ids);

    Attachment refresh(final Attachment attachment);


}
