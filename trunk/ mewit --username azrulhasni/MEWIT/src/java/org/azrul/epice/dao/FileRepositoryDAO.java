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
import org.azrul.epice.domain.FileRepository;

/**
 *
 * @author Azrul Hasni MADISA
 */
public interface FileRepositoryDAO {
    
    FileRepository create(final String _owner, String description);

    FileRepository createWithoutPersisting(final String owner, String description);

    FileRepository create(final String _id, final String _owner, String description);

    FileRepository addNewAttachment(final FileRepository _fileRepo, final Attachment attachment);
    
    FileRepository addNewAttachments(final FileRepository _fileRepo, final Set<Attachment> attachments, final String currentUser);

    FileRepository findFileRepositoryById(String id);

    FileRepository refresh(final FileRepository fileRepository);

    FileRepository update(final FileRepository fileRepository, final String currentUser);

    FileRepository removeAttachmentsFromFileRepository(final FileRepository fileRepository, Set<Attachment> attachmentsToBeDeleted, String currentUser);

}
