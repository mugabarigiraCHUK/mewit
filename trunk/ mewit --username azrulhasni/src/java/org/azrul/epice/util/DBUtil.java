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

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import java.io.File;
import java.util.ResourceBundle;

public class DBUtil {

    public static String getYap() {
        return YAPFILENAME;
    }

    public static void setYap(String aYAPFILENAME) {
        YAPFILENAME = aYAPFILENAME;
    }


    public DBUtil() {
    }

   
    private static String YAPFILENAME;
//    private static final int PORT = 8723;
    private static final int PORT = 0;
    static {
        ResourceBundle props = ResourceBundle.getBundle("epice");
        YAPFILENAME=(String) props.getObject("YAP_FILE");
    }
//
    private static final String USER = "billadam";
    private static final String PASSWORD = "akunakkaya666";
    private static ObjectServer objectServer;
    private static final ThreadLocal dbThreadLocal = new ThreadLocal();

    public static ObjectContainer getDB() {
        ObjectContainer oc = (ObjectContainer) dbThreadLocal.get();
        if (oc == null || oc.ext().isClosed()) {
//            if (objectServer == null){
//                getObjectServer();
//            }
//            oc = Db4o.openClient("localhost", PORT, USER, PASSWORD);
            oc = getObjectServer().openClient();
            dbThreadLocal.set(oc);
        }
        return oc;
    }

    public static void closeDB() {
        ObjectContainer oc = (ObjectContainer) dbThreadLocal.get();
        dbThreadLocal.set(null);
        if (oc != null) {
            oc.close();
        }
    }

    public synchronized static ObjectServer getObjectServer() {
        if (objectServer == null) {
            objectServer = getObjectServerForFilename(YAPFILENAME,PORT);
        // and give access
            objectServer.grantAccess(USER, PASSWORD);
        }
        return objectServer;
    }

    public static void shutdown() {
        if (objectServer != null) {
            objectServer.close();
            
        }
    }

    public static ObjectServer getObjectServerForFilename(String yapfilename, int port) {
       // File parentDir = getDbDirectory();
        File dbfile = new File(yapfilename);

        // for replication //////////////////////////
        Db4o.configure().generateUUIDs(Integer.MAX_VALUE);
        Db4o.configure().generateVersionNumbers(Integer.MAX_VALUE);

        // other options
        Db4o.configure().exceptionsOnNotStorable(true);
        Db4o.configure().objectClass("java.math.BigDecimal").translate(new com.db4o.config.TSerializable());
        //Db4o.configure().objectClass(Item.class).objectField("id").indexed(true);
//        Db4o.configure().add(new UniqueFieldValueConstraint(Item.class, "id"));
        //Db4o.configure().objectClass(Person.class).objectField("email").indexed(true);
//        Db4o.configure().add(new UniqueFieldValueConstraint(Person.class, "email"));
        //Db4o.configure().messageLevel(3);
        // now open server
        ObjectServer os = Db4o.openServer(dbfile.getPath(), port);

        return os;
    }

//    private static File getDbDirectory() {
//        // will store data files in {user.home}/db4o/data directory
//        String dbfile = System.getProperty("user.home") + "/db4o/data";
//        File f = new File(dbfile);
//        if (!f.exists()) {
//            f.mkdirs();
//        }
//        return f;
//    }
    public static void main(String[] args){
        shutdown();
        //getObjectServer();
    }
    //    private static final String YAPFILENAME;
//    private static final int PORT = 8723;
////    private static final int PORT = 0;
////    static {
////        ResourceBundle props = ResourceBundle.getBundle("epice");
////        YAPFILENAME = (String) props.getObject("YAP_FILE");
////    }
////
//    private static final String USER = "billadam";
//    private static final String PASSWORD = "akunakkaya666";
//    private static ObjectServer objectServer;
//    private static final ThreadLocal dbThreadLocal = new ThreadLocal();
//
//    public static ObjectContainer getDB() {
//        ObjectContainer oc = (ObjectContainer) dbThreadLocal.get();
//        if (oc == null || oc.ext().isClosed()) {
////            if (objectServer == null){
////                getObjectServer();
////            }
//            Db4o.configure().flushFileBuffers(true);
//            oc = Db4o.openClient("localhost", PORT, USER, PASSWORD);
//            
////            oc = getObjectServer().openClient();
//            dbThreadLocal.set(oc);
//        }
//        return oc;
//        
//    }
//
//    public static void closeDB() {
//        ObjectContainer oc = (ObjectContainer) dbThreadLocal.get();
//        dbThreadLocal.set(null);
//        if (oc != null) {
//            oc.close();
//        }
//    }
//
////    public synchronized static ObjectServer getObjectServer() {
////        if (objectServer == null) {
////            objectServer = getObjectServerForFilename(YAPFILENAME, PORT);
////        // and give access
////            objectServer.grantAccess(USER, PASSWORD);
////        }
////        return objectServer;
////    }
//
//    public static void shutdown() {
//        if (objectServer != null) {
//            objectServer.close();
//            
//        }
//    }
//
////    public static ObjectServer getObjectServerForFilename(String yapfilename, int port) {
////       // File parentDir = getDbDirectory();
////        File dbfile = new File(yapfilename);
////
////        // for replication //////////////////////////
////        Db4o.configure().generateUUIDs(Integer.MAX_VALUE);
////        Db4o.configure().generateVersionNumbers(Integer.MAX_VALUE);
////
////        // other options
////        Db4o.configure().exceptionsOnNotStorable(true);
////        Db4o.configure().objectClass("java.math.BigDecimal").translate(new com.db4o.config.TSerializable());
////
////        // now open server
////        ObjectServer os = Db4o.openServer(dbfile.getPath(), port);
////
////        return os;
////    }
//
////    private static File getDbDirectory() {
////        // will store data files in {user.home}/db4o/data directory
////        String dbfile = System.getProperty("user.home") + "/db4o/data";
////        File f = new File(dbfile);
////        if (!f.exists()) {
////            f.mkdirs();
////        }
////        return f;
////    }
//    public static void main(String[] args){
//        shutdown();
//        //getObjectServer();
//    }
}
