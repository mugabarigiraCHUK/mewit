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

package org.azrul.epice.exception;

public class UserNotExistException extends RuntimeException{
    private String nonExistingUserEmail = null;
    
    public UserNotExistException(){
        
    }
    
    public UserNotExistException(String email){
        this.nonExistingUserEmail = email;
    }
    
    public UserNotExistException(Exception e){
        super(e);
    }

    public String getNonExistingUserEmail() {
        return nonExistingUserEmail;
    }

    public void setNonExistingUserEmail(String nonExistingUserEmail) {
        this.nonExistingUserEmail = nonExistingUserEmail;
    }
}
