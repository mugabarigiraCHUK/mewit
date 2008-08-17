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

import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Hyperlink;
import com.sun.webui.jsf.component.StaticText;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Person;
import org.azrul.epice.domain.UserLink;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul
 */
public class PgUserInfo extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }

    // </editor-fold>
    private PersonDAO personDAO = PersonDAOFactory.getInstance();
    private StaticText stName = new StaticText();

    public StaticText getStName() {
        return stName;
    }

    public void setStName(StaticText st) {
        this.stName = st;
    }
    private Hyperlink hlEmail = new Hyperlink();

    public Hyperlink getHlEmail() {
        return hlEmail;
    }

    public void setHlEmail(Hyperlink h) {
        this.hlEmail = h;
    }
    private StaticText stOfficeAdddress = new StaticText();

    public StaticText getStOfficeAdddress() {
        return stOfficeAdddress;
    }

    public void setStOfficeAdddress(StaticText st) {
        this.stOfficeAdddress = st;
    }
    private StaticText stDepartment = new StaticText();

    public StaticText getStDepartment() {
        return stDepartment;
    }

    public void setStDepartment(StaticText st) {
        this.stDepartment = st;
    }
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgUserInfo() {
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     * 
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    @Override
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
        boolean unknownUser = false;
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (req.getParameter("email")==null && req.getAttribute("email")==null){
            if (getSessionBean1().getInspectedUser()==null){
                unknownUser = true;
            }
        }else{
            String email = (String) (req.getParameter("email") != null ? req.getParameter("email") : req.getAttribute("email"));
            Person inspectedUser = personDAO.findPersonByEmail(email);
            getSessionBean1().setInspectedUser(inspectedUser);
        }
        
        if (req.getParameter("from")==null && req.getAttribute("from")==null){
        }else{
            String from = (String) (req.getParameter("email") != null ? req.getParameter("from") : req.getAttribute("from"));
            getSessionBean1().setPreviousPage(from);
        }
       
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("PgUserInfo Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        stName.setText(getSessionBean1().getInspectedUser().getName());
        hlEmail.setText(getSessionBean1().getInspectedUser().getEmail());
        hlEmail.setUrl("mailto:"+getSessionBean1().getInspectedUser().getEmail());
        stOfficeAdddress.setText(getSessionBean1().getInspectedUser().getOfficeAddress());
        stDepartment.setText(getSessionBean1().getInspectedUser().getDepartment());
        
        
        
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    @Override
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    @Override
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
    }
    
    public UserLinkDataProvider getUserItemsDP(){
        UserLinkDataProvider uldp = new UserLinkDataProvider();
        if (getSessionBean1().getInspectedUser().getUserLinks()==null){
            return uldp;
        }
        List<UserLink> userLinks = new ArrayList<UserLink>(getSessionBean1().getInspectedUser().getUserLinks());
        uldp.setList(userLinks);
        return uldp;
    }

    public String btnBack_action() {
        if (("PgSearch").equals(getSessionBean1().getPreviousPage())){
             getSessionBean1().popPreviousPage();
            return "gotoSearch";
        }else if (("PgManagePersonalLists").equals(getSessionBean1().getPreviousPage())){
             getSessionBean1().popPreviousPage();
            return "gotoManagePersonalLists";
        }else{
            return null;
        }
    }

   
    
}

