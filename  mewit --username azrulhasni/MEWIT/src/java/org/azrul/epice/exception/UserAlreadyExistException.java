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

public class UserAlreadyExistException extends RuntimeException{
   private String existingUserEmail = null;
   
   public UserAlreadyExistException(){
       
   }
   
   public UserAlreadyExistException(String email){
       this.existingUserEmail = email;
   }
   
   public UserAlreadyExistException(Exception e){
      super(e);
   }

    public String getExistingUserEmail() {
        return existingUserEmail;
    }

    public void setExistingUserEmail(String existingUserEmail) {
        this.existingUserEmail = existingUserEmail;
    }
}
