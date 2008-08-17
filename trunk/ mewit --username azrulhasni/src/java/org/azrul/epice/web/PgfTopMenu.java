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
package org.azrul.epice.web;

import com.sun.rave.web.ui.appbase.AbstractFragmentBean;
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.StaticText;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.UserLinkDAO;
import org.azrul.epice.dao.SearchItemsQueryDAO;
import org.azrul.epice.dao.factory.SearchItemsQueryDAOFactory;
import org.azrul.epice.dao.factory.UserLinkDAOFactory;
import org.azrul.epice.dao.query.factory.SearchItemsQueryQueryFactory;
import org.azrul.epice.dao.query.factory.UserLinksQueryFactory;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul Hasni MADISA
 */
public class PgfTopMenu extends AbstractFragmentBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization. <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    private Button btnManageSavedSearch1 = new Button();

    public Button getBtnManageSavedSearch1() {
        return btnManageSavedSearch1;
    }

    public void setBtnManageSavedSearch1(Button b) {
        this.btnManageSavedSearch1 = b;
    }
    private Button btnLogout1 = new Button();

    public Button getBtnLogout1() {
        return btnLogout1;
    }

    public void setBtnLogout1(Button b) {
        this.btnLogout1 = b;
    }
    private Button btnNewItem1 = new Button();

    public Button getBtnNewItem1() {
        return btnNewItem1;
    }

    public void setBtnNewItem1(Button b) {
        this.btnNewItem1 = b;
    }
    private Button btnSearch1 = new Button();

    public Button getBtnSearch1() {
        return btnSearch1;
    }

    public void setBtnSearch1(Button b) {
        this.btnSearch1 = b;
    }
    private Button btnProfile1 = new Button();

    public Button getBtnProfile1() {
        return btnProfile1;
    }

    public void setBtnProfile1(Button b) {
        this.btnProfile1 = b;
    }
    private Button btnEditList1 = new Button();

    public Button getBtnEditList1() {
        return btnEditList1;
    }

    public void setBtnEditList1(Button b) {
        this.btnEditList1 = b;
    }
    private Button btnMainMenu1 = new Button();

    public Button getBtnMainMenu1() {
        return btnMainMenu1;
    }

    public void setBtnMainMenu1(Button b) {
        this.btnMainMenu1 = b;
    }
    private StaticText stWelcome = new StaticText();

    public StaticText getStWelcome() {
        return stWelcome;
    }

    public void setStWelcome(StaticText st) {
        this.stWelcome = st;
    }
    // </editor-fold>
    ItemDAO itemDAO = ItemDAOFactory.getInstance();
    SearchItemsQueryDAO searchItemsQueryDAO = SearchItemsQueryDAOFactory.getInstance();
    UserLinkDAO linkDAO = UserLinkDAOFactory.getInstance();
    

    public PgfTopMenu() {
    }

    /**
     * <p>Callback method that is called whenever a page containing
     * this page fragment is navigated to, either directly via a URL,
     * or indirectly via page navigation.  Override this method to acquire
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        
        
        // <editor-fold defaultstate="collapsed" desc="Visual-Web-managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        Person user = getSessionBean1().getCurrentUser();
        if (user!=null){
            stWelcome.setText("Welcome " + user.getName());
        }
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called.  Override this
     * method to release resources acquired in the <code>init()</code>
     * resources that will be needed for event handlers and lifecycle methods.</p>
     * 
     * <p>The default implementation does nothing.</p>
     */
    @Override
    public void destroy() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

 

    public String btnNewItem_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        getSessionBean1().setParent(null);
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous pages
       getSessionBean1().emptyPreviousPages();
       
       //set Ref/Item
       getSessionBean1().setItemType("PLAIN_ITEM");
        return "gotoNewItem";
    }

    public String btnLogout_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        ((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))).invalidate();
        return "logout";
    }

    public String btnEditList_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        Person user = getSessionBean1().getCurrentUser();
        Map<String, String> buddiesEmail = new HashMap<String, String>();
        for (Person buddy : user.getBuddies()) {
            if (buddy!=null){
                buddiesEmail.put(buddy.getEmail(), buddy.getEmail() + "*");
            }
        }
        getSessionBean1().setUserBuddiesEmail(buddiesEmail);

        Map<String, String> supervisorsEmail = new HashMap<String, String>();
        for (Person sup : user.getSupervisors()) {
            supervisorsEmail.put(sup.getEmail(), sup.getEmail() + "*");
        }
        
        getSessionBean1().setUserLinksQuery(UserLinksQueryFactory.getInstance());
        getSessionBean1().setUserLinks(linkDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getUserLinksQuery()));
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous pages
        getSessionBean1().emptyPreviousPages();
        return "gotoManagePersonalLists";
    }

    public String btnGoToReceivedItems_action() {
        return "gotoReceivedItems";
    }
    
     public String btnSearch_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous search
        getSessionBean1().setSearchItemsQuery(null);
        getSessionBean1().emptySearchItemsDP();
        
        //clean up previous pages
        getSessionBean1().emptyPreviousPages();
        return "gotoSearch";
    }

    public String btnManageSavedSearch_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        

        getSessionBean1().setSearchItemsQueryQuery(SearchItemsQueryQueryFactory.getInstance());
        getSessionBean1().refreshSearchItemsQueryDP(searchItemsQueryDAO.runItemsQueryQuery(getSessionBean1().getCurrentUser(),getSessionBean1().getSearchItemsQueryQuery()));
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous pages
        getSessionBean1().emptyPreviousPages();
        return "gotoManagedSavedSearch";
    }
    
      public String btnProfile_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous pages
        getSessionBean1().emptyPreviousPages();
        return "gotoEditProfile";
    }

    

    public String btnMainMenu1_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous pages
        getSessionBean1().emptyPreviousPages();
        return "gotoMenu";
    }

    public String btnNewReference1_action() {
        if (((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("LOGGED_IN") == null){
            return "logout";
        }
        
        
        //reset links
        getSessionBean1().setLinks(new HashSet<URL>());
        
        getSessionBean1().setParent(null);
        
        //clean up current file repo 
        getSessionBean1().setCurrentFileRepository(null);
        
        //clean up previous pages
       getSessionBean1().emptyPreviousPages();
       
       //set Ref/Item
       getSessionBean1().setItemType("REFERENCE");
        return "gotoNewItem";
    }
}
