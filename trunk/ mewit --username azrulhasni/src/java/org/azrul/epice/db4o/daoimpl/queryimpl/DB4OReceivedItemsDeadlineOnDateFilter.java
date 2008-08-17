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

package org.azrul.epice.db4o.daoimpl.queryimpl;

import java.util.Date;
import java.util.GregorianCalendar;
import org.azrul.epice.dao.query.DateBasedItemsFilter;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class DB4OReceivedItemsDeadlineOnDateFilter implements DateBasedItemsFilter {
    private Date onDate = null;
    private String type = null;
    
    @Override
    public String getType() {
        return type;
    }
    
    public DB4OReceivedItemsDeadlineOnDateFilter (){
        type = "RECEIVED_ITEMS_DEADLINE_TODAY";
    }
    
    @Override
    public boolean filter(Item item, Person user) {
        if (item.getDeadLine() == null) {
            return false;
        }
        GregorianCalendar calOnDate = (GregorianCalendar) GregorianCalendar.getInstance();
        if (onDate != null){
            calOnDate.setTime(onDate);
        }
        GregorianCalendar calItem = (GregorianCalendar) GregorianCalendar.getInstance();
        calItem.setTime(item.getDeadLine());
        return (calItem.get(GregorianCalendar.DAY_OF_YEAR) == calOnDate.get(GregorianCalendar.DAY_OF_YEAR) &&
                    calItem.get(GregorianCalendar.YEAR) == calOnDate.get(GregorianCalendar.YEAR) &&
                    item.getToUser().equals(user) &&
                    item.isArchivedForRecipient() == false &&
                    (   ("SENT-UNCONFIRMED").equals(item.getStatus()) || 
                        ("SENT-NEGOTIATED").equals(item.getStatus()) || 
                        ("SENT-ACCEPTED").equals(item.getStatus()) || 
                        ("NEED-REDO").equals(item.getStatus()) || 
                        ("DELEGATED").equals(item.getStatus()) || 
                        ("NEED-REDO DELEGATED").equals(item.getStatus())));
        

    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }
}

