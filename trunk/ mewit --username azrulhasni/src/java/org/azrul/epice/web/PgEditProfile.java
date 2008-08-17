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
import com.sun.webui.jsf.component.Body;
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.Checkbox;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Label;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.PasswordField;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.model.DefaultTableDataProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.manager.EditProfileManager;
import org.azrul.epice.util.SessionMap;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul Hasni MADISA
 */
public class PgEditProfile extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        lengthValidator.setMinimum(1);
    }
    private Page page1 = new Page();

    public Page getPage1() {
        return page1;
    }

    public void setPage1(Page p) {
        this.page1 = p;
    }
    private Html html1 = new Html();

    public Html getHtml1() {
        return html1;
    }

    public void setHtml1(Html h) {
        this.html1 = h;
    }
    private Head head1 = new Head();

    public Head getHead1() {
        return head1;
    }

    public void setHead1(Head h) {
        this.head1 = h;
    }
    private Link link1 = new Link();

    public Link getLink1() {
        return link1;
    }

    public void setLink1(Link l) {
        this.link1 = l;
    }
    private Body body1 = new Body();

    public Body getBody1() {
        return body1;
    }

    public void setBody1(Body b) {
        this.body1 = b;
    }
    private Form form1 = new Form();

    public Form getForm1() {
        return form1;
    }

    public void setForm1(Form f) {
        this.form1 = f;
    }
    private Label label1 = new Label();

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label l) {
        this.label1 = l;
    }
    private TextField tfEmail = new TextField();

    public TextField getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(TextField tf) {
        this.tfEmail = tf;
    }
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private PasswordField pfPassword = new PasswordField();

    public PasswordField getPfPassword() {
        return pfPassword;
    }

    public void setPfPassword(PasswordField pf) {
        this.pfPassword = pf;
    }
    private PasswordField pfRetypePassword = new PasswordField();

    public PasswordField getPfRetypePassword() {
        return pfRetypePassword;
    }

    public void setPfRetypePassword(PasswordField pf) {
        this.pfRetypePassword = pf;
    }
    private Label label3 = new Label();

    public Label getLabel3() {
        return label3;
    }

    public void setLabel3(Label l) {
        this.label3 = l;
    }
    private TextField tfName = new TextField();

    public TextField getTfName() {
        return tfName;
    }

    public void setTfName(TextField tf) {
        this.tfName = tf;
    }
    private TextField tfOfficeAddress = new TextField();

    public TextField getTfOfficeAddress() {
        return tfOfficeAddress;
    }

    public void setTfOfficeAddress(TextField tf) {
        this.tfOfficeAddress = tf;
    }
    private Label label4 = new Label();

    public Label getLabel4() {
        return label4;
    }

    public void setLabel4(Label l) {
        this.label4 = l;
    }
    private Label label5 = new Label();

    public Label getLabel5() {
        return label5;
    }

    public void setLabel5(Label l) {
        this.label5 = l;
    }
    private Label label6 = new Label();

    public Label getLabel6() {
        return label6;
    }

    public void setLabel6(Label l) {
        this.label6 = l;
    }
    private TextField tfDepartment = new TextField();

    public TextField getTfDepartment() {
        return tfDepartment;
    }

    public void setTfDepartment(TextField tf) {
        this.tfDepartment = tf;
    }
    private Button btnSubmit = new Button();

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(Button b) {
        this.btnSubmit = b;
    }
    private LengthValidator lengthValidator = new LengthValidator();

    public LengthValidator getLengthValidator() {
        return lengthValidator;
    }

    public void setLengthValidator(LengthValidator lv) {
        this.lengthValidator = lv;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private Button btnBack = new Button();

    public Button getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(Button b) {
        this.btnBack = b;
    }
    private Label label7 = new Label();

    public Label getLabel7() {
        return label7;
    }

    public void setLabel7(Label l) {
        this.label7 = l;
    }
    private PasswordField pfOldPassword = new PasswordField();

    public PasswordField getPfOldPassword() {
        return pfOldPassword;
    }

    public void setPfOldPassword(PasswordField pf) {
        this.pfOldPassword = pf;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }

    // </editor-fold>
    PersonDAO personDAO = PersonDAOFactory.getInstance();
    private StaticText stErrors = new StaticText();

    public StaticText getStErrors() {
        return stErrors;
    }

    public void setStErrors(StaticText st) {
        this.stErrors = st;
    }
    private Checkbox cbOkToReceiveEmail = new Checkbox();

    public Checkbox getCbOkToReceiveEmail() {
        return cbOkToReceiveEmail;
    }

    public void setCbOkToReceiveEmail(Checkbox c) {
        this.cbOkToReceiveEmail = c;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgEditProfile() {
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

        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("PgEditProfile Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

        Person _user = new Person();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (getSessionBean1().getCurrentUser() != null) {
            _user = getSessionBean1().getCurrentUser();
            btnBack.setDisabled(false);
            btnBack.setVisible(true);

        } else {
            _user.setEmail(req.getParameter("email"));
            _user.setPassword(req.getParameter("key"));
            getPfOldPassword().setDisabled(true);
            btnBack.setDisabled(true);
            btnBack.setVisible(false);
        }


        if (_user.getEmail() == null || _user.getPassword() == null) {

            getTfName().setDisabled(true);
            getTfDepartment().setDisabled(true);
            getTfOfficeAddress().setDisabled(true);
            getPfPassword().setDisabled(true);
            getPfRetypePassword().setDisabled(true);
            getCbOkToReceiveEmail().setDisabled(true);
            return;
        }
        Person user = personDAO.refresh(_user);
        if (user != null) {

            getTfEmail().setText(user.getEmail());
            getTfName().setText(user.getName());
            getTfDepartment().setText(user.getDepartment());
            getTfOfficeAddress().setText(user.getOfficeAddress());
            getCbOkToReceiveEmail().setValue(user.getOkToReceiveEmail());
            if (req.getParameter("key") != null) {
                getPfOldPassword().setText(user.getPassword());
                cbOkToReceiveEmail.setSelected(new Boolean(true));
            }
        } else {
            getTfName().setDisabled(true);
            getTfDepartment().setDisabled(true);
            getTfOfficeAddress().setDisabled(true);
            getPfPassword().setDisabled(true);
            getPfRetypePassword().setDisabled(true);
            getCbOkToReceiveEmail().setDisabled(true);
        }

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
    protected SessionBean1 getSessionBean1() {
        return (SessionBean1) getBean("SessionBean1");
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
 
    public String btnSubmit_action() {
        String errors = null;
        String retypedPassword = (String)getPfRetypePassword().getText();
        String password = (String)getPfPassword().getText();
        Map sessionMap = new SessionMap(((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))));
        String email = (String) getTfEmail().getText();
        String name =  (String) getTfName().getText();
        String department = (String) getTfDepartment().getText();
        String officeAddress = (String) getTfOfficeAddress().getText();
        String oldPassword = (String) getPfOldPassword().getText();
        boolean okToReceiveEmail = (Boolean)getCbOkToReceiveEmail().isChecked();
        
        EditProfileManager editProfileManager = new EditProfileManager();
        editProfileManager.updateProfile(retypedPassword,oldPassword, password, email, name, department, officeAddress, sessionMap,okToReceiveEmail);
        getStErrors().setText(errors);
        return btnBack_action();

    }

    public void pfRetypePassword_validate(FacesContext context, UIComponent component, Object value) {
        if (!(getPfPassword().getText().equals(value))) {
            throw new ValidatorException(new FacesMessage("Not a three-digit number."));
        }
    }

    public String btnBack_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        if (getSessionBean1().getCurrentUser()==null){
             getSessionBean1().popPreviousPage();
            return "gotoLogin";
        }else{
            getSessionBean1().popPreviousPage();
            return "gotoMenu";
        }
    }

    public void cbOkToReceiveEmail_processValueChange(ValueChangeEvent event) {
    }

}

