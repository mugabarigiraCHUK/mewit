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

import java.util.List;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;


public class ReportUtil {

    public static String itemsToCSV(List<Item> items, Person user) {
        StringBuffer str = new StringBuffer();
        str.append("_ID,");
        str.append("PARENT_ID,");
        str.append("SENDER_NAME,");
        str.append("SENDER_EMAIL,");
        str.append("RECIPIENT_NAME,");
        str.append("RECIPIENT_EMAIL,");
        str.append("SUBJECT,");
//        str.append("DESCRIPTION,");
        str.append("SENT_DATE,");
        str.append("DEAD_LINE,");
        str.append("DEAD_LINE_STATUS,");
        str.append("STATUS,");
//        str.append("FEEDBACK,");
//        str.append("COMMENTS_ON_FEEDBACK,");
        str.append("NEGOTIATED_DEAD_LINE,");
//        str.append("REASON_FOR_NEGOTIATION,");
        str.append("REDO_COUNTER,");
        str.append("TYPE");
        str.append("\n");
        for (Item i : items) {
            //determine type
            int itemTypeState = 0;

            //received item
            if (i.getToUsers().contains(user)) {
                itemTypeState = 2;
            } else {
                itemTypeState = 1;
            }

            //sent item
            if (i.getFromUser().equals(user)) {
                if (itemTypeState == 2) {
                    itemTypeState = 3;
                } else {
                    itemTypeState = 5;
                }
            } else {
                if (itemTypeState == 2) {
                    itemTypeState = 4;
                } else {
                    itemTypeState = 6;
                }
            }
            //supervised
            if (i.getSupervisors().contains(user)) {
                if (itemTypeState == 6) {
                    itemTypeState = 7;
                }
            } else {
                if (itemTypeState == 6) {
                    itemTypeState = 8;
                }
            }

            if (itemTypeState == 3) {
                i.setType("TO YOURSELF");
            } else if (itemTypeState == 4) {
                i.setType("RECEIVED");
            } else if (itemTypeState == 5) {
                i.setType("SENT");
            } else if (itemTypeState == 7) {
                i.setType("SUPERVISED");
            }

            //create string
            str.append(i.getId() + ",");
            if (i.getParent() == null) {
                str.append(" ,");
            } else {
                str.append(i.getParent().getId() + ",");
            }
            str.append(i.getFromUser().getName() + ",");
            str.append(i.getFromUser().getEmail() + ",");
            str.append(i.getToUser().getName() + ",");
            str.append(i.getToUser().getEmail() + ",");
            str.append(i.getSubject().replace(',', ';') + ",");
//            if (i.getDescription()!=null){
//                str.append(i.getDescription().replace(',', ';').replace("\n","") + ",");
//            }else{
//                str.append(",");
//            }
            str.append(i.getSentDate() + ",");
            str.append(i.getDeadLine() + ",");
            str.append(i.getDeadlineStatus() + ",");
            str.append(i.getStatus() + ",");
//            if (i.getFeedback()!=null){
//                str.append(i.getFeedback().replace(',', ';').replace("\n","") + ",");
//            }else{
//                str.append(",");
//            }
//            if (i.getCommentsOnFeedback()!=null){
//                str.append(i.getCommentsOnFeedback().replace(',', ';').replace("\n","") + ",");
//            }else{
//                str.append(",");
//            }
            if (i.getNegotiatedDeadLine() == null) {
                str.append(" ,");
            } else {
                str.append(i.getNegotiatedDeadLine() + ",");
//                if (i.getReasonForNegotiatiationOfDeadLine()!=null){
//                    str.append(i.getReasonForNegotiatiationOfDeadLine().replace(',', ';').replace("\n","") + ",");
//                }else{
//                    str.append(",");
//                }
            }
            str.append(i.getRedoCounter()+",");
            str.append(i.getType());
            str.append("\n");
        }
        return str.toString();
    }
}
