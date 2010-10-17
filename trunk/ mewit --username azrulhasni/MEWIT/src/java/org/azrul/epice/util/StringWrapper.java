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

package org.azrul.epice.util;


public class StringWrapper {
    private String string;
    
    public StringWrapper(){
        
    }
    
    public StringWrapper(String string){
        this.string = string;
    }
    
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
    
}
