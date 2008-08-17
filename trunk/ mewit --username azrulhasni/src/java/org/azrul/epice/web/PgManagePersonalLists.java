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

import com.sun.data.provider.RowKey;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Body;
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Label;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Listbox;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.Table;
import com.sun.webui.jsf.component.TableRowGroup;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.event.TableSelectPhaseListener;
import com.sun.webui.jsf.model.DefaultOptionsList;
import com.sun.webui.jsf.model.Option;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.UserLink;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul Hasni MADISA
 */
public class PgManagePersonalLists extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
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
    private Label label2 = new Label();

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label l) {
        this.label2 = l;
    }
    private TextField tfBuddy = new TextField();

    public TextField getTfBuddy() {
        return tfBuddy;
    }

    public void setTfBuddy(TextField tf) {
        this.tfBuddy = tf;
    }
    private TextField tfSupervisor = new TextField();

    public TextField getTfSupervisor() {
        return tfSupervisor;
    }

    public void setTfSupervisor(TextField tf) {
        this.tfSupervisor = tf;
    }
    private Listbox lbBuddies = new Listbox();

    public Listbox getLbBuddies() {
        return lbBuddies;
    }

    public void setLbBuddies(Listbox l) {
        this.lbBuddies = l;
    }
    private DefaultOptionsList lbBuddiesDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbBuddiesDefaultOptions() {
        return lbBuddiesDefaultOptions;
    }

    public void setLbBuddiesDefaultOptions(DefaultOptionsList dol) {
        this.lbBuddiesDefaultOptions = dol;
    }
    private Listbox lbSupervisors = new Listbox();

    public Listbox getLbSupervisors() {
        return lbSupervisors;
    }

    public void setLbSupervisors(Listbox l) {
        this.lbSupervisors = l;
    }
    private DefaultOptionsList lbSupervisorsDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbSupervisorsDefaultOptions() {
        return lbSupervisorsDefaultOptions;
    }

    public void setLbSupervisorsDefaultOptions(DefaultOptionsList dol) {
        this.lbSupervisorsDefaultOptions = dol;
    }
    private Button btnSave = new Button();

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button b) {
        this.btnSave = b;
    }
    private Button btnAddToBuddies = new Button();

    public Button getBtnAddToBuddies() {
        return btnAddToBuddies;
    }

    public void setBtnAddToBuddies(Button b) {
        this.btnAddToBuddies = b;
    }
    private Button btnAddSupervisor = new Button();

    public Button getBtnAddSupervisor() {
        return btnAddSupervisor;
    }

    public void setBtnAddSupervisor(Button b) {
        this.btnAddSupervisor = b;
    }
    private Button btnRemoveBuddy = new Button();

    public Button getBtnRemoveBuddy() {
        return btnRemoveBuddy;
    }

    public void setBtnRemoveBuddy(Button b) {
        this.btnRemoveBuddy = b;
    }
    private Button btnRemoveSupervisor = new Button();

    public Button getBtnRemoveSupervisor() {
        return btnRemoveSupervisor;
    }

    public void setBtnRemoveSupervisor(Button b) {
        this.btnRemoveSupervisor = b;
    }
    private StaticText stWarningEmailSupervisorNotInDB = new StaticText();

    public StaticText getStWarningEmailSupervisorNotInDB() {
        return stWarningEmailSupervisorNotInDB;
    }

    public void setStWarningEmailSupervisorNotInDB(StaticText st) {
        this.stWarningEmailSupervisorNotInDB = st;
    }
    private StaticText stWarningEmailBuddyNotInDB = new StaticText();

    public StaticText getStWarningEmailBuddyNotInDB() {
        return stWarningEmailBuddyNotInDB;
    }

    public void setStWarningEmailBuddyNotInDB(StaticText st) {
        this.stWarningEmailBuddyNotInDB = st;
    }
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }    // </editor-fold>

    PersonDAO personDAO = PersonDAOFactory.getInstance();
    private TextField tfLinkURL = new TextField();
    private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();

    public TextField getTfLinkURL() {
        return tfLinkURL;
    }

    public void setTfLinkURL(TextField tf) {
        this.tfLinkURL = tf;
    }
    private TextField tfLinkDescription = new TextField();

    public TextField getTfLinkDescription() {
        return tfLinkDescription;
    }

    public void setTfLinkDescription(TextField tf) {
        this.tfLinkDescription = tf;
    }
    private Table tbUserLinks = new Table();

    public Table getTbUserLinks() {
        return tbUserLinks;
    }

    public void setTbUserLinks(Table t) {
        this.tbUserLinks = t;
    }
    private TableRowGroup trgUserLinks = new TableRowGroup();

    public TableRowGroup getTrgUserLinks() {
        return trgUserLinks;
    }

    public void setTrgUserLinks(TableRowGroup trg) {
        this.trgUserLinks = trg;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgManagePersonalLists() {
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
            log("PgManagePersonalLists Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        // getSessionBean1().setUserLinks(linkDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getUserLinksQuery()));
        refreshLists();
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

    public String btnSave_action() {
        Person user = getSessionBean1().getCurrentUser();
        personDAO.addBuddiesAndSupervisorsByEmails(user, getSessionBean1().getUserBuddiesEmail().keySet(), getSessionBean1().getUserSupervisorsEmail().keySet());
        personDAO.addLinks(user, getSessionBean1().getUserLinks());
        getSessionBean1().popPreviousPage();
        return "gotoMenu";
    }

    public String btnAddToBuddies_action() {

        String email = null;
        if (getTfBuddy().getText() != null) {
            email = (String) getTfBuddy().getText();
        }
        if (email != null) {
            Person user = new Person();
            user.setEmail(email);
            String emailDisplay = email;
            if (personDAO.refresh(user) != null) {
                stWarningEmailBuddyNotInDB.setVisible(false);
            } else {
                stWarningEmailBuddyNotInDB.setVisible(true);
                stWarningEmailBuddyNotInDB.setText(email + " is not in the database. An inivitation email will be sent to the user");

            }
            getSessionBean1().getUserBuddiesEmail().put(email, emailDisplay);
        }
        refreshLists();
        //reset
        getTfBuddy().setText("");



        return null;
    }

    public String btnAddSupervisor_action() {

        String email = null;
        if (getTfSupervisor().getText() != null) {
            email = (String) getTfSupervisor().getText();
        }
        if (email != null) {
            Person user = new Person();
            user.setEmail(email);
            String emailDisplay = email;
            if (personDAO.refresh(user) != null) {
                emailDisplay = emailDisplay + "*";
                stWarningEmailSupervisorNotInDB.setVisible(false);
            } else {
                stWarningEmailSupervisorNotInDB.setVisible(true);
                stWarningEmailSupervisorNotInDB.setText(email + " is not in the database. An inivitation email will be sent to the user");

            }
            getSessionBean1().getUserSupervisorsEmail().put(email, emailDisplay);
        }
        refreshLists();

        //reset
        getTfSupervisor().setText("");


        return null;
    }

    public void refreshLists() {
        List<Option> toOptions = new ArrayList<Option>();
        if (!getSessionBean1().getUserBuddiesEmail().isEmpty()) {
            for (String key : getSessionBean1().getUserBuddiesEmail().keySet()) {
                Option o = new Option(key, getSessionBean1().getUserBuddiesEmail().get(key));
                toOptions.add(o);
            }
            getLbBuddiesDefaultOptions().setOptions(toOptions.toArray(new Option[1]));
        } else {
            getLbBuddiesDefaultOptions().setOptions(new Option[]{new Option("", "")});
        }

        List<Option> supOptions = new ArrayList<Option>();
        if (!getSessionBean1().getUserSupervisorsEmail().isEmpty()) {
            for (String key : getSessionBean1().getUserSupervisorsEmail().keySet()) {
                Option o = new Option(key, getSessionBean1().getUserSupervisorsEmail().get(key));
                supOptions.add(o);
            }
            getLbSupervisorsDefaultOptions().setOptions(supOptions.toArray(new Option[1]));
        } else {
            getLbSupervisorsDefaultOptions().setOptions(new Option[]{new Option("", "")});
        }

        getSessionBean1().refreshUserLinksDP(getSessionBean1().getUserLinks());

    }

    public String btnRemoveBuddy_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        getSessionBean1().getUserBuddiesEmail().remove((String) getLbBuddies().getSelected());
        refreshLists();
        return null;
    }

    public String btnRemoveSupervisor_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        getSessionBean1().getUserSupervisorsEmail().remove((String) getLbSupervisors().getSelected());
        refreshLists();
        return null;
    }

    public void lbBuddies_processValueChange(ValueChangeEvent event) {
    }

    public String btnAdd_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        org.azrul.epice.domain.UserLink link = new org.azrul.epice.domain.UserLink();
        String url = (String) tfLinkURL.getText();
        if (!url.contains("://")) {
            url = "http://" + url;
        }
        link.setUrl(url);
        link.setDescription((String) tfLinkDescription.getText());
        getSessionBean1().getUserLinks().add(link);
        refreshLists();


        return null;
    }

    public void setSelected(Object object) {
        RowKey rowKey = (RowKey) getValue("#{currentRow.tableRow}");
        if (rowKey != null) {
            tablePhaseListener.setSelected(rowKey, object);
        }
    }

    public Object getSelected() {
        RowKey rowKey = (RowKey) getValue("#{currentRow.tableRow}");
        return tablePhaseListener.getSelected(rowKey);

    }

    public Object getSelectedValue() {
        RowKey rowKey = (RowKey) getValue("#{currentRow.tableRow}");
        return (rowKey != null) ? rowKey.getRowId() : null;

    }

    public boolean getSelectedState() {
        RowKey rowKey = (RowKey) getValue("#{currentRow.tableRow}");
        return tablePhaseListener.isSelected(rowKey);
    }

    public String btnDelete_action() {
        RowKey[] selectedRowKeys = getTrgUserLinks().getSelectedRowKeys();
        List<UserLink> linksToBeDeleted = new ArrayList<UserLink>();
        for (int i = 0; i < selectedRowKeys.length; i++) {
            UserLink selectedLink = (UserLink) getSessionBean1().getUserLinksDP().getObject(selectedRowKeys[i]);
            linksToBeDeleted.add(selectedLink);
        }
        getSessionBean1().getUserLinks().removeAll(linksToBeDeleted);
        getSessionBean1().refreshUserLinksDP(getSessionBean1().getUserLinks());
        tablePhaseListener.clear();
        return null;
    }

    public String btnSeeProfileBuddy_action() {
        String email  = (String)getLbBuddies().getValue();
        String from = "PgManagePersonalLists";
        ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).setAttribute("email",email);
        ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).setAttribute("from",from);
        return "gotoUserInfo";
    }

    public String btnSeeProfileSupervisor_action() {
        String email  = (String)getLbSupervisors().getValue();
        String from = "PgManagePersonalLists";
        ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).setAttribute("email",email);
        ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).setAttribute("from",from);
        return "gotoUserInfo";
    }
}

