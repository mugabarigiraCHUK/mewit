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

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Azrul
 */
public class RequestMap extends AbstractMap{
     private final HttpServletRequest request;
    private Set entries;

    public RequestMap(HttpServletRequest s) {
        this.request = s;
    }

    public Set entrySet() {
        if (entries == null) {
            entries = new HashSet();
            // loop over request attribute names
            // create new Map.Entry anonymous inner class
            // with attribute name/value
            // ensure setValue on the entry modifies underlying request
            // add to entries
        }
        return entries;
    }

    public Object put(Object key, Object value) {
        entries = null;
        Object originalValue =
                request.getAttribute(key.toString());
        request.setAttribute(key.toString(), value);
        return originalValue;
    }

    public Object get(Object key) {
        return request.getAttribute(key.toString());
    }

    public Object remove(Object key) {
        entries = null;
        Object value = get(key);
        request.removeAttribute(key.toString());
        return value;
    }

    public void clear() {
        entries = null;
    }
}
