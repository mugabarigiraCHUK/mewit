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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Person;
import org.azrul.epice.manager.EditProfileManager;
import org.azrul.epice.util.DBUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Azrul
 */
public class PgEditProfileTest {

    public PgEditProfileTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void doEditProfileTest(){
         File file = null;
        try {
            file = File.createTempFile("epice", ".yap");
        } catch (IOException ex) {
            Logger.getLogger(PgLoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBUtil.setYap(file.getPath());

        PersonDAO personDAO = PersonDAOFactory.getInstance();
        Person minnie = personDAO.create("minnie.mouse@company.com", "Minnie Mouse", "minnie", "abc123", "770202-15-0667", "0179986721", "R&D", "Billadam, 63000 Cyberjaya");
        EditProfileManager editProfileManager = new EditProfileManager();
        Map sessionMap = new HashMap();
        editProfileManager.updateProfile("_abc123", "abc123", "_abc123", "minnie.mouse@company.com", "_Minnie Mouse", "_R&D", "_Billadam", sessionMap, false);
        minnie = personDAO.refresh(minnie);
        Assert.assertEquals(minnie.getName(),"_Minnie Mouse");
        Assert.assertEquals(minnie.getPassword(), "_abc123");
        Assert.assertEquals(minnie.getDepartment(),"_R&D");
        Assert.assertEquals(minnie.getOfficeAddress(),"_Billadam");
        
        
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

}