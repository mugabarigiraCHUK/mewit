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


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;

public class LogUtil {

    private static Logger logger = Logger.getLogger("org.azrul.epice");
    

    static {
        StreamHandler sh = null;
//            ResourceBundle props =  ResourceBundle.getBundle("org.azrul.epice.config.epice");
//            String logfile = (String)props.getObject("LOG_FILE");
        sh = new StreamHandler(System.out, new SimpleFormatter());
//            fh = new FileHandler("C:\\mylog.txt");


        logger.addHandler(sh);
        // Request that every detail gets logged.
        logger.setLevel(Level.ALL);

    // Log a simple INFO message.
    }

    public static Logger getLogger() {
        //return Logger.getLogger("org.azrul.epice");
        return logger;
    }
}
