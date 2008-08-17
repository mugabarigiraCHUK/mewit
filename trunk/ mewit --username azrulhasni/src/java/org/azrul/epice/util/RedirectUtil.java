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

import java.io.IOException;
import java.util.logging.Level;
import javax.faces.context.FacesContext;

public class RedirectUtil {
    public RedirectUtil() {
    }
    
    public static void run(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
             LogUtil.getLogger().log(Level.SEVERE,"RedirectUrl.run()",e);
        }
    }
}
