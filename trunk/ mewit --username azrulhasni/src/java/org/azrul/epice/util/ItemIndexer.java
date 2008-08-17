/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import com.db4o.ObjectSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.compass.core.Compass;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
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

import org.compass.core.CompassTransaction;
import org.compass.core.config.CompassConfiguration;


public class ItemIndexer {

    private static Compass compass;

    public static Compass getCompass() {
        return compass;
    }

    public static void setCompass(Compass aCompass) {
        compass = aCompass;
    }

    /** Creates a new instance of Indexer */
    public ItemIndexer() {
    }

    static {
        ResourceBundle props = ResourceBundle.getBundle("epice");
        String indexFile = (String) props.getObject("ITEM_INDEX_FILE");
        CompassConfiguration conf = new CompassConfiguration().setConnection(indexFile).addClass(Item.class).addClass(Person.class).addClass(FileRepository.class).addClass(Attachment.class);
        setCompass(conf.buildCompass());
    }

    public static void addAndCommit(Item item) {
        CompassSession session = getCompass().openSession();
        CompassTransaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
        } catch (CompassException ce) {
            if (tx != null) {
                tx.rollback();
                LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ce);
                
            }
        } finally {
            session.close();
        }
    }
    
  

    public static void rebuildIndexes() {
        ObjectSet<Item> items = DBUtil.getDB().get(new Item());
        while (items.hasNext()) {
            Item item = items.next();
            System.out.println(item.toString());
            addAndCommit(item);
        }
    }

    public static void main(String[] args) {
        rebuildIndexes();
    }
    /*private static IndexWriter indexWriter = null;
    public static IndexWriter getIndexWriter(boolean create) throws IOException {
    if (indexWriter == null) {
    ResourceBundle props = ResourceBundle.getBundle("epice");
    String indexFile = (String)props.getObject("ITEM_INDEX_FILE");
    indexWriter = new IndexWriter(new File(indexFile),new StandardAnalyzer(),create);
    }
    return indexWriter;
    }
    public static void closeIndexWriter() throws IOException {
    if (indexWriter != null) {
    indexWriter.close();
    }
    }
    public static void index(Item item)  {
    try {
    IndexWriter writer = (IndexWriter) getIndexWriter(true);
    Document doc = new Document();
    doc.add(new Field("id", item.getId(), Field.Store.YES, Field.Index.NO));
    doc.add(new Field("content", item.toString(), Field.Store.YES, Field.Index.TOKENIZED));
    writer.addDocument(doc);
    } catch (IOException ex) {
    Logger.getLogger(ItemIndexer.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public static void rebuildIndexes(){
    ObjectSet<Item> items = (new DBUtil()).getDB().get(new Item());
    while (items.hasNext()){
    Item item = items.next();
    System.out.println(item.toString());
    index(item);
    }
    }
    public static void main(String[] args){
    rebuildIndexes();
    }
    /* public static void rebuildIndexes() throw;s IOException {
    //
    // Erase existing index
    //
    getIndexWriter(true);
    //
    // Index all Accommodation entries
    //
    Hotel[] hotels = HotelDatabase.getHotels();
    for (Hotel hotel : hotels) {
    indexHotel(hotel);
    }
    //
    // Don't forget to close the index writer when done
    //
    closeIndexWriter();
    }*/
    }
