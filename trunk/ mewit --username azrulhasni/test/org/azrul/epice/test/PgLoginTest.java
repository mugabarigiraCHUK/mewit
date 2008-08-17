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
package org.azrul.epice.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.azrul.epice.dao.AttachmentDAO;
import org.azrul.epice.dao.factory.AttachmentDAOFactory;
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.manager.LoginManager;
import org.azrul.epice.util.DBUtil;
import org.azrul.epice.web.SessionBean1;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Azrul
 */
public class PgLoginTest {

    public PgLoginTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

//    public Person setUpPerson(S) {
//        Person person = new Person();
//        person.setIcNumber("770321-14-5774");
//        person.setName("Minnie Mouse");
//        person.setUserName("minnie");
//        person.setEmail("minnie.mouse@company.com");
//        person.setPassword("abc123");
//
//        db.set(person);
//        return person;
//    }
//
//    public void setUpItemSentByPerson(Person person, ObjectContainer db) {
//        Item item = new Item();
//        item.setId("123456");
//        item.s
//    }
    @After
    public void tearDown() {
    }

    // @Test
    public void doNominalSussessTest() {
        File file = null;
        try {
            file = File.createTempFile("epice", ".yap");
        } catch (IOException ex) {
            Logger.getLogger(PgLoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBUtil.setYap(file.getPath());

        PersonDAO personDAO = PersonDAOFactory.getInstance();
        Person minnie = personDAO.create("minnie.mouse@company.com", "Minnie Mouse", "minnie", "abc123", "770202-15-0667", "0179986721", "R&D", "Billadam, 63000 Cyberjaya");
        //Person donald = personDAO.create("donald.duck@company.com", "Donald Duck", "donald", "abc123", "770202-15-0668", "0179986722", "R&D", "Billadam, 63000 Cyberjaya");
        LoginManager loginManager = new LoginManager();
        SessionBean1 sessionBean1 = new SessionBean1();
        Map sessionMap = new HashMap();
        String result = loginManager.doLogin("minnie.mouse@company.com", "abc123", sessionMap, sessionBean1);
        Assert.assertEquals(result, "successful");
        Assert.assertEquals(sessionMap.get("LOGGED_IN"), "minnie.mouse@company.com");
    }

    @Test
    public void doTestGetBackPassword() {
        File file = null;
        try {
            file = File.createTempFile("epice3", ".yap");
        } catch (IOException ex) {
            Logger.getLogger(PgLoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBUtil.setYap(file.getPath());
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        Person minnie = personDAO.create("minnie.mouse@company.com", "Minnie Mouse", "minnie", "abc123", "770202-15-0667", "0179986721", "R&D", "Billadam, 63000 Cyberjaya");
        
        LoginManager loginManager  = new LoginManager();
        SessionBean1 sessionBean1 = new SessionBean1();
        sessionBean1.setCurrentUser(minnie);
        String password = loginManager.getBackPassword(sessionBean1,false);
        minnie = personDAO.refresh(minnie);
        Assert.assertEquals(minnie.getPassword(),password);
                
    }

    //@Test
    public void doTestInitLogin() {
        File file = null;
        try {
            file = File.createTempFile("epice2", ".yap");
        } catch (IOException ex) {
            Logger.getLogger(PgLoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBUtil.setYap(file.getPath());

        try {
            PersonDAO personDAO = PersonDAOFactory.getInstance();;
            Person minnie = personDAO.create("minnie.mouse@company.com", "Minnie Mouse", "minnie", "abc123", "770202-15-0667", "0179986721", "R&D", "Billadam, 63000 Cyberjaya");
            Person donald = personDAO.create("donald.duck@company.com", "Donald Duck", "donald", "abc123", "770202-15-0668", "0179986722", "R&D", "Billadam, 63000 Cyberjaya");
            ItemDAO itemDAO = ItemDAOFactory.getInstance();
            List<String> toUserEmails = new ArrayList<String>();
            toUserEmails.add("donald.duck@company.com");
            GregorianCalendar calDeadline = (GregorianCalendar) GregorianCalendar.getInstance();
            calDeadline.clear();

            calDeadline.set(Calendar.DAY_OF_MONTH, 27);
            calDeadline.set(Calendar.MONTH, 5);
            calDeadline.set(Calendar.YEAR, 2008);
            calDeadline.set(Calendar.HOUR_OF_DAY, 18);

            FileRepositoryDAO fileRepoDAO = FileRepositoryDAOFactory.getInstance();
            AttachmentDAO attachmentDAO = AttachmentDAOFactory.getInstance();

            File f1 = File.createTempFile("epice_attachment", ".txt");
            Attachment attachment1 = attachmentDAO.createWithoutPersisting(f1.getPath(), "minnie.mouse@company.com");

            File f2 = File.createTempFile("epice_attachment", ".txt");
            Attachment attachment2 = attachmentDAO.createWithoutPersisting(f2.getPath(), "minnie.mouse@company.com");

            FileRepository fileRepo = fileRepoDAO.create(minnie, "A file repo");
            fileRepoDAO.addNewAttachment(fileRepo, attachment1);
            fileRepoDAO.addNewAttachment(fileRepo, attachment2);

            Set<Item> items = itemDAO.createItems(minnie, toUserEmails, null, new ArrayList<String>(), new ArrayList<String>(), null, "Do something", "Do something", calDeadline.getTime(), false, fileRepo);

            Map requestMap = new HashMap();
            requestMap.put("itemId", items.iterator().next().getId());
            SessionBean1 sessionBean1 = new SessionBean1();
            Map sessionMap = new HashMap();
            sessionMap.put("LOGGED_IN", "donald.duck@company.com");
            LoginManager loginManager = new LoginManager();
            loginManager.initLogin("itemId", sessionBean1, sessionMap);
            Assert.assertEquals(sessionBean1.getCurrentReceivedItem(), items.iterator().next());

        } catch (IOException ex) {
            Logger.getLogger(PgLoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}