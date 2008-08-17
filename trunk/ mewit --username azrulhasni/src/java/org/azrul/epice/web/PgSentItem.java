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
import com.sun.data.provider.impl.ObjectListDataProvider;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import com.sun.webui.jsf.component.Body;
import com.sun.webui.jsf.component.Button;
import com.sun.webui.jsf.component.Calendar;
import com.sun.webui.jsf.component.Checkbox;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.Hyperlink;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Listbox;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.Property;
import com.sun.webui.jsf.component.PropertySheet;
import com.sun.webui.jsf.component.PropertySheetSection;
import com.sun.webui.jsf.component.RadioButton;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.Table;
import com.sun.webui.jsf.component.TableColumn;
import com.sun.webui.jsf.component.TableRowGroup;
import com.sun.webui.jsf.component.TextArea;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.component.Tree;
import com.sun.webui.jsf.model.DefaultOptionsList;
import com.sun.webui.jsf.model.Option;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.util.LogUtil;
import org.azrul.epice.util.StringWrapper;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul
 */
public class PgSentItem extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern(getSessionBean1().getLongDateFormat());
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
    private TextArea taComments = new TextArea();

    public TextArea getTaComments() {
        return taComments;
    }

    public void setTaComments(TextArea ta) {
        this.taComments = ta;
    }
    private Button btnSubmit1 = new Button();

    public Button getBtnSubmit1() {
        return btnSubmit1;
    }

    public void setBtnSubmit1(Button b) {
        this.btnSubmit1 = b;
    }
    private TextArea taFeedback = new TextArea();

    public TextArea getTaFeedback() {
        return taFeedback;
    }

    public void setTaFeedback(TextArea ta) {
        this.taFeedback = ta;
    }
    private TextField tfItemId = new TextField();

    public TextField getTfItemId() {
        return tfItemId;
    }

    public void setTfItemId(TextField tf) {
        this.tfItemId = tf;
    }
    private TextArea taAction = new TextArea();

    public TextArea getTaAction() {
        return taAction;
    }

    public void setTaAction(TextArea ta) {
        this.taAction = ta;
    }
    private TextArea taReasonNegoReject = new TextArea();

    public TextArea getTaReasonNegoReject() {
        return taReasonNegoReject;
    }

    public void setTaReasonNegoReject(TextArea ta) {
        this.taReasonNegoReject = ta;
    }
    private Listbox lbTo = new Listbox();

    public Listbox getLbTo() {
        return lbTo;
    }

    public void setLbTo(Listbox l) {
        this.lbTo = l;
    }
    private TextField tfSubject = new TextField();

    public TextField getTfSubject() {
        return tfSubject;
    }

    public void setTfSubject(TextField tf) {
        this.tfSubject = tf;
    }
    private TextField tfDeadline = new TextField();

    public TextField getTfDeadline() {
        return tfDeadline;
    }

    public void setTfDeadline(TextField tf) {
        this.tfDeadline = tf;
    }
    private Listbox lbTag = new Listbox();

    public Listbox getLbTag() {
        return lbTag;
    }

    public void setLbTag(Listbox l) {
        this.lbTag = l;
    }
    private Button btnBack1 = new Button();

    public Button getBtnBack1() {
        return btnBack1;
    }

    public void setBtnBack1(Button b) {
        this.btnBack1 = b;
    }
    private TextField tfFrom = new TextField();

    public TextField getTfFrom() {
        return tfFrom;
    }

    public void setTfFrom(TextField tf) {
        this.tfFrom = tf;
    }
    private TextField tfStatus = new TextField();

    public TextField getTfStatus() {
        return tfStatus;
    }

    public void setTfStatus(TextField tf) {
        this.tfStatus = tf;
    }
    private TextField tfSentDate = new TextField();

    public TextField getTfSentDate() {
        return tfSentDate;
    }

    public void setTfSentDate(TextField tf) {
        this.tfSentDate = tf;
    }
    private DefaultOptionsList lbToDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbToDefaultOptions() {
        return lbToDefaultOptions;
    }

    public void setLbToDefaultOptions(DefaultOptionsList dol) {
        this.lbToDefaultOptions = dol;
    }
    private DefaultOptionsList lbTagDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbTagDefaultOptions() {
        return lbTagDefaultOptions;
    }

    public void setLbTagDefaultOptions(DefaultOptionsList dol) {
        this.lbTagDefaultOptions = dol;
    }
    private Checkbox cbWorkApproved = new Checkbox();

    public Checkbox getCbWorkApproved() {
        return cbWorkApproved;
    }

    public void setCbWorkApproved(Checkbox c) {
        this.cbWorkApproved = c;
    }
    private PropertySheet propertySheet1 = new PropertySheet();

    public PropertySheet getPropertySheet1() {
        return propertySheet1;
    }

    public void setPropertySheet1(PropertySheet ps) {
        this.propertySheet1 = ps;
    }
    private PropertySheetSection properties = new PropertySheetSection();

    public PropertySheetSection getProperties() {
        return properties;
    }

    public void setProperties(PropertySheetSection pss) {
        this.properties = pss;
    }
    private Property property1 = new Property();

    public Property getProperty1() {
        return property1;
    }

    public void setProperty1(Property p) {
        this.property1 = p;
    }
    private Property property2 = new Property();

    public Property getProperty2() {
        return property2;
    }

    public void setProperty2(Property p) {
        this.property2 = p;
    }
    private Property property3 = new Property();

    public Property getProperty3() {
        return property3;
    }

    public void setProperty3(Property p) {
        this.property3 = p;
    }
    private Property property4 = new Property();

    public Property getProperty4() {
        return property4;
    }

    public void setProperty4(Property p) {
        this.property4 = p;
    }
    private Property property5 = new Property();

    public Property getProperty5() {
        return property5;
    }

    public void setProperty5(Property p) {
        this.property5 = p;
    }
    private Property property6 = new Property();

    public Property getProperty6() {
        return property6;
    }

    public void setProperty6(Property p) {
        this.property6 = p;
    }
    private Property property7 = new Property();

    public Property getProperty7() {
        return property7;
    }

    public void setProperty7(Property p) {
        this.property7 = p;
    }
    private Property property8 = new Property();

    public Property getProperty8() {
        return property8;
    }

    public void setProperty8(Property p) {
        this.property8 = p;
    }
    private Property property9 = new Property();

    public Property getProperty9() {
        return property9;
    }

    public void setProperty9(Property p) {
        this.property9 = p;
    }
    private Property property10 = new Property();

    public Property getProperty10() {
        return property10;
    }

    public void setProperty10(Property p) {
        this.property10 = p;
    }
    private Property property11 = new Property();

    public Property getProperty11() {
        return property11;
    }

    public void setProperty11(Property p) {
        this.property11 = p;
    }
    private Property property13 = new Property();

    public Property getProperty13() {
        return property13;
    }

    public void setProperty13(Property p) {
        this.property13 = p;
    }
    private Property property14 = new Property();

    public Property getProperty14() {
        return property14;
    }

    public void setProperty14(Property p) {
        this.property14 = p;
    }
    private Property property15 = new Property();

    public Property getProperty15() {
        return property15;
    }

    public void setProperty15(Property p) {
        this.property15 = p;
    }
    private Property property16 = new Property();

    public Property getProperty16() {
        return property16;
    }

    public void setProperty16(Property p) {
        this.property16 = p;
    }
    private Property property17 = new Property();

    public Property getProperty17() {
        return property17;
    }

    public void setProperty17(Property p) {
        this.property17 = p;
    }
    private Table tbLinks = new Table();

    public Table getTbLinks() {
        return tbLinks;
    }

    public void setTbLinks(Table t) {
        this.tbLinks = t;
    }
    private TableRowGroup tableRowGroup1 = new TableRowGroup();

    public TableRowGroup getTableRowGroup1() {
        return tableRowGroup1;
    }

    public void setTableRowGroup1(TableRowGroup trg) {
        this.tableRowGroup1 = trg;
    }
    private TableColumn tableColumn1 = new TableColumn();

    public TableColumn getTableColumn1() {
        return tableColumn1;
    }

    public void setTableColumn1(TableColumn tc) {
        this.tableColumn1 = tc;
    }
    private TableColumn tableColumn2 = new TableColumn();

    public TableColumn getTableColumn2() {
        return tableColumn2;
    }

    public void setTableColumn2(TableColumn tc) {
        this.tableColumn2 = tc;
    }
    private StaticText staticText2 = new StaticText();

    public StaticText getStaticText2() {
        return staticText2;
    }

    public void setStaticText2(StaticText st) {
        this.staticText2 = st;
    }
    private TableColumn tableColumn3 = new TableColumn();

    public TableColumn getTableColumn3() {
        return tableColumn3;
    }

    public void setTableColumn3(TableColumn tc) {
        this.tableColumn3 = tc;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private ObjectListDataProvider linksDataProvider = new ObjectListDataProvider();

    public ObjectListDataProvider getLinksDataProvider() {
        linksDataProvider.setList(getLinks());
        return linksDataProvider;
    }

    public void setLinksDataProvider(ObjectListDataProvider oldp) {
        this.linksDataProvider = oldp;
    }
    private Hyperlink hyperlink1 = new Hyperlink();

    public Hyperlink getHyperlink1() {
        return hyperlink1;
    }

    public void setHyperlink1(Hyperlink h) {
        this.hyperlink1 = h;
    }
    private Calendar calNegoDeadline = new Calendar();

    public Calendar getCalNegoDeadline() {
        return calNegoDeadline;
    }

    public void setCalNegoDeadline(Calendar c) {
        this.calNegoDeadline = c;
    }
    private StaticText stTitle = new StaticText();

    public StaticText getStTitle() {
        return stTitle;
    }

    public void setStTitle(StaticText st) {
        this.stTitle = st;
    }
    private Button btnEdit = new Button();

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button b) {
        this.btnEdit = b;
    }
    private Property property18 = new Property();

    public Property getProperty18() {
        return property18;
    }

    public void setProperty18(Property p) {
        this.property18 = p;
    }
    private TextField tfNumberOfRedos = new TextField();

    public TextField getTfNumberOfRedos() {
        return tfNumberOfRedos;
    }

    public void setTfNumberOfRedos(TextField tf) {
        this.tfNumberOfRedos = tf;
    }
    private Property property19 = new Property();

    public Property getProperty19() {
        return property19;
    }

    public void setProperty19(Property p) {
        this.property19 = p;
    }
    private Tree trSupervisedItems = new Tree();

    public Tree getTrSupervisedItems() {
        return trSupervisedItems;
    }

    public void setTrSupervisedItems(Tree t) {
        this.trSupervisedItems = t;
    }
    private Button btnTreeView = new Button();

    public Button getBtnTreeView() {
        return btnTreeView;
    }

    public void setBtnTreeView(Button b) {
        this.btnTreeView = b;
    }
    private Button btnDelete = new Button();

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button b) {
        this.btnDelete = b;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }
    private Button btnGotoAttachments = new Button();

    public Button getBtnGotoAttachments() {
        return btnGotoAttachments;
    }

    public void setBtnGotoAttachments(Button b) {
        this.btnGotoAttachments = b;
    }
    private Button btnAcceptRejectNSeeParent = new Button();

    public Button getBtnAcceptRejectNSeeParent() {
        return btnAcceptRejectNSeeParent;
    }

    public void setBtnAcceptRejectNSeeParent(Button b) {
        this.btnAcceptRejectNSeeParent = b;
    }
    // </editor-fold>
    ItemDAO itemDAO = ItemDAOFactory.getInstance();
    private TextField tfNegoDeadlineTime = new TextField();

    public TextField getTfNegoDeadlineTime() {
        return tfNegoDeadlineTime;
    }

    public void setTfNegoDeadlineTime(TextField tf) {
        this.tfNegoDeadlineTime = tf;
    }
    private RadioButton rbNegoDeadlineAM = new RadioButton();

    public RadioButton getRbNegoDeadlineAM() {
        return rbNegoDeadlineAM;
    }

    public void setRbNegoDeadlineAM(RadioButton rb) {
        this.rbNegoDeadlineAM = rb;
    }
    private RadioButton rbNegoDeadlinePM = new RadioButton();

    public RadioButton getRbNegoDeadlinePM() {
        return rbNegoDeadlinePM;
    }

    public void setRbNegoDeadlinePM(RadioButton rb) {
        this.rbNegoDeadlinePM = rb;
    }
    private TextField tfAddTags = new TextField();

    public TextField getTfAddTags() {
        return tfAddTags;
    }

    public void setTfAddTags(TextField tf) {
        this.tfAddTags = tf;
    }
    private Button btnRemoveTag = new Button();

    public Button getBtnRemoveTag() {
        return btnRemoveTag;
    }

    public void setBtnRemoveTag(Button b) {
        this.btnRemoveTag = b;
    }
    private TextField tfTo = new TextField();

    public TextField getTfTo() {
        return tfTo;
    }

    public void setTfTo(TextField tf) {
        this.tfTo = tf;
    }
    private Button btnAddTags = new Button();

    public Button getBtnAddTags() {
        return btnAddTags;
    }

    public void setBtnAddTags(Button b) {
        this.btnAddTags = b;
    }
    private Checkbox cbAdvanceView = new Checkbox();

    public Checkbox getCbAdvanceView() {
        return cbAdvanceView;
    }

    public void setCbAdvanceView(Checkbox c) {
        this.cbAdvanceView = c;
    }
    private TableRowGroup trgAttachments = new TableRowGroup();

    public TableRowGroup getTrgAttachments() {
        return trgAttachments;
    }

    public void setTrgAttachments(TableRowGroup trg) {
        this.trgAttachments = trg;
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgSentItem() {
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
            log("PgSentItem Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

        // ResourceBundle props = ResourceBundle.getBundle("epice");
        Person user = getSessionBean1().getCurrentUser();
        Item sentItem = null;
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();


        if (req.getParameter("itemId") != null) {
            sentItem = itemDAO.findItemById(req.getParameter("itemId"));
            if (sentItem != null) {
                getSessionBean1().setCurrentSentItem(sentItem);
                getSessionBean1().setPreviousPage(req.getParameter("prevPage"));
            }
        } else {
            sentItem = itemDAO.refresh(getSessionBean1().getCurrentSentItem());
        }

        //verifiy if sentItem really exist
        if (sentItem == null) {
            //LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return;
        }
        
        if (cbAdvanceView.getSelected() == null || (Boolean.FALSE).equals(cbAdvanceView.getSelected())){
            tfItemId.setVisible(false);
            tfFrom.setVisible(false);
            lbTag.setVisible(false);
            tfSentDate.setVisible(false);
            tbLinks.setVisible(false);
        }else{
            tfItemId.setVisible(true);
            tfFrom.setVisible(true);
            lbTag.setVisible(true);
            tfSentDate.setVisible(true);
            tbLinks.setVisible(true);
        }

        tfItemId.setText(sentItem.getId());
        tfSubject.setText(sentItem.getSubject());
        tfDeadline.setText(sentItem.getDeadLine());
        tfFrom.setText(sentItem.getFromUser().getEmail());

        calNegoDeadline.setSelectedDate(sentItem.getNegotiatedDeadLine());
        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        if (sentItem.getNegotiatedDeadLine() != null) {
            gcal.setTime(sentItem.getNegotiatedDeadLine());
            SimpleDateFormat sdf = new SimpleDateFormat(ResourceBundle.getBundle("epice").getString("HM_ONLY_DATE_FORMAT"));
            tfNegoDeadlineTime.setText(sdf.format(gcal.getTime()));
            if (gcal.get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
                rbNegoDeadlineAM.setSelected(true);
                rbNegoDeadlinePM.setSelected(false);
            } else {
                rbNegoDeadlineAM.setSelected(false);
                rbNegoDeadlinePM.setSelected(true);
            }
        }

        tfSentDate.setText(sentItem.getSentDate());
        tfStatus.setText(sentItem.getStatus());
        tfSubject.setText(sentItem.getSubject());
        taAction.setText(sentItem.getDescription());
        taComments.setText(sentItem.getCommentsOnFeedback());
        taReasonNegoReject.setText(sentItem.getReasonForNegotiatiationOfDeadLine());
        taFeedback.setText(sentItem.getFeedback());
        tfNumberOfRedos.setText(sentItem.getRedoCounter());
        tfTo.setText(sentItem.getToUser().getName()+"["+sentItem.getToUser().getEmail()+"]");

        //hide children button if from tree
        if (("PgTreeView").equals(getSessionBean1().getPreviousPage())) {
            btnTreeView.setVisible(false);
        }

        List<Option> toUsersOptions = new ArrayList<Option>();
        for (Person p : sentItem.getToUsers()) {
            Option o = new Option(p.getEmail(), p.getName() + "[" + p.getEmail() + "]");
            toUsersOptions.add(o);
        }
        DefaultOptionsList dolUsers = new DefaultOptionsList();
        dolUsers.setOptions(toUsersOptions.toArray(new Option[1]));
        setLbToDefaultOptions(dolUsers);


        if (sentItem.getTags() != null) {
            List<Option> tagsOptions = new ArrayList<Option>();
            for (String tag : sentItem.getTags()) {
                Option o = new Option(tag, tag);
                tagsOptions.add(o);
            }
            DefaultOptionsList dolTags = new DefaultOptionsList();
            if (tagsOptions.isEmpty()) {
                Option[] oo = {new Option("", "")};
                dolTags.setOptions(oo);
            } else {
                dolTags.setOptions(tagsOptions.toArray(new Option[1]));
            }


            setLbTagDefaultOptions(dolTags);
        } else {
            DefaultOptionsList dolTags = new DefaultOptionsList();
            Option[] oo = {new Option("", "")};
            dolTags.setOptions(oo);
            setLbTagDefaultOptions(dolTags);
        }

        //set item approved by default
        cbWorkApproved.setSelected(new Boolean(true));
        
        //refresh file repo
        getSessionBean1().setCurrentFileRepository(sentItem.getFileRepository());
        getSessionBean1().getCurrentFileRepository().refreshDataProvider(getSessionBean1().getCurrentUser());
        

        //disable submit button if not user
        //i.e. user is supervisor
        if (!sentItem.getFromUser().equals(user)) {
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            stTitle.setText("Supervised item");
            btnRemoveTag.setVisible(false);
            btnAddTags.setVisible(false);
            tfAddTags.setVisible(false);
                    
           
        } else {
            stTitle.setText("Sent item");
        }
        //treat states
        if (("SENT-UNCONFIRMED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(false);

            taFeedback.setVisible(false);

            taComments.setVisible(false);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);

            //i.e. user is supervisor
            if (!sentItem.getFromUser().equals(user)) {
                btnDelete.setVisible(false);
                btnEdit.setVisible(false);
            } else {
                btnDelete.setVisible(true);
                btnEdit.setVisible(true);
            }

        } else if (("SENT-NEGOTIATED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);
            if (sentItem.getFromUser().equals(user)) {
//                cbApproveNegoDeadline.setVisible(true);
//                cbApproveNegoDeadline.setDisabled(false);
                calNegoDeadline.setVisible(true);
                calNegoDeadline.setDisabled(false);
                tfNegoDeadlineTime.setVisible(true);
                tfNegoDeadlineTime.setDisabled(false);
                rbNegoDeadlineAM.setVisible(true);
                rbNegoDeadlineAM.setDisabled(false);
                rbNegoDeadlinePM.setVisible(true);
                rbNegoDeadlinePM.setDisabled(false);



                btnSubmit1.setText("Approve/Disaprove");
                btnSubmit1.setVisible(true);

//                if (sentItem.getParent() != null) {
//                    btnAcceptRejectNSeeParent.setVisible(true);
//                }
            } else {
//                cbApproveNegoDeadline.setVisible(true);
//                cbApproveNegoDeadline.setDisabled(true);
                calNegoDeadline.setVisible(false);
                calNegoDeadline.setDisabled(true);
                tfNegoDeadlineTime.setVisible(false);
                tfNegoDeadlineTime.setDisabled(true);
                rbNegoDeadlineAM.setVisible(false);
                rbNegoDeadlineAM.setDisabled(true);
                rbNegoDeadlinePM.setVisible(false);
                rbNegoDeadlinePM.setDisabled(true);

                rbNegoDeadlineAM.setSelected(false);
                rbNegoDeadlinePM.setSelected(false);

                btnSubmit1.setVisible(false);

            }



            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);
            taReasonNegoReject.setText(sentItem.getReasonForNegotiatiationOfDeadLine());

            taFeedback.setVisible(false);

            taComments.setVisible(false);
            btnEdit.setVisible(false);
            btnSubmit1.setVisible(true);
            btnSubmit1.setText("Approve/Disaprove");
            btnAcceptRejectNSeeParent.setVisible(false);
//            if (sentItem.getParent() != null) {
//                btnAcceptRejectNSeeParent.setVisible(true);
//            } else {
//                btnAcceptRejectNSeeParent.setVisible(false);
//            }
            btnDelete.setVisible(false);
        } else if (("SENT-ACCEPTED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(false);
            btnEdit.setVisible(false);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            btnDelete.setVisible(false);
        } else if (("DELEGATED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(false);
            btnEdit.setVisible(false);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            btnDelete.setVisible(false);
        } else if (("SENT-REJECTED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);
            taReasonNegoReject.setText(sentItem.getReasonForRejectionOfTask());

            taFeedback.setVisible(false);
            taFeedback.setDisabled(true);

            taComments.setVisible(false);
            btnEdit.setVisible(true);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            btnDelete.setVisible(false);
        } else if (("DONE-UNCONFIRMED").equals(sentItem.getStatus())) {

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            if (sentItem.getFromUser().equals(user)) {
                taComments.setVisible(true);
                taComments.setDisabled(false);

                cbWorkApproved.setVisible(true);
                cbWorkApproved.setDisabled(false);

                btnSubmit1.setVisible(true);
                btnSubmit1.setText("Approve/Disapprove");
                if (sentItem.getParent() != null) {
                    btnAcceptRejectNSeeParent.setVisible(true);
                } else {
                    btnAcceptRejectNSeeParent.setVisible(false);
                }
            } else {
                taComments.setVisible(true);
                taComments.setDisabled(true);

                cbWorkApproved.setVisible(true);
                cbWorkApproved.setDisabled(true);

                btnSubmit1.setVisible(false);
                btnAcceptRejectNSeeParent.setVisible(false);
            }


        } else if (("DONE-CONFIRMED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(true);
            taComments.setDisabled(true);
            btnEdit.setVisible(false);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            btnDelete.setVisible(false);
        } else if (("NEED-REDO").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(true);
            taComments.setDisabled(true);

            btnEdit.setVisible(false);

            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            btnDelete.setVisible(false);
        } else if (("NEED-REDO DELEGATED").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

//            cbApproveNegoDeadline.setVisible(false);
//            cbApproveNegoDeadline.setDisabled(true);

            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(true);
            taComments.setDisabled(true);

            btnEdit.setVisible(false);

            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            btnDelete.setVisible(false);
        } else if (("REFERENCE").equals(sentItem.getStatus())) {
            cbWorkApproved.setVisible(false);
            cbWorkApproved.setDisabled(true);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(false);

            taFeedback.setVisible(false);

            taComments.setVisible(false);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);

            btnDelete.setVisible(false);
            btnEdit.setVisible(false);


            tfDeadline.setVisible(false);
            btnSubmit1.setVisible(false);
            btnAcceptRejectNSeeParent.setVisible(false);
            tfNumberOfRedos.setVisible(false);


        }

        //if there exist an item in the tempSentItem, refresh the gui with it
        Item tempSentItem = getSessionBean1().getTempSentItem();
        if (tempSentItem != null) {
            setupGUIFromSentItem(tempSentItem);
            getSessionBean1().setTempSentItem(null);
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
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public String btnSubmit_action() {
        Item sentItem = getSessionBean1().getCurrentSentItem();

        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        Date negotiatedDeadLine = null;
        if (calNegoDeadline.getSelectedDate() != null) {
            gcal.setTime(calNegoDeadline.getSelectedDate());
            Date deadlineTime = (Date) tfNegoDeadlineTime.getValue();
            if (deadlineTime != null) {
                GregorianCalendar gcalHM = (GregorianCalendar) GregorianCalendar.getInstance();
                gcalHM.setTime(deadlineTime);
                gcal.set(GregorianCalendar.HOUR, gcalHM.get(GregorianCalendar.HOUR));
                gcal.set(GregorianCalendar.MINUTE, gcalHM.get(GregorianCalendar.MINUTE));

                if (rbNegoDeadlineAM.getSelected() != null && rbNegoDeadlinePM.getSelected() != null) {
                    if (rbNegoDeadlineAM.getSelected().equals(true) && rbNegoDeadlinePM.getSelected().equals(false)) {
                        gcal.set(GregorianCalendar.AM_PM, GregorianCalendar.AM);
                    } else if (rbNegoDeadlineAM.getSelected().equals(false) && rbNegoDeadlinePM.getSelected().equals(true)) {
                        gcal.set(GregorianCalendar.AM_PM, GregorianCalendar.PM);
                    }
                }
            }
            negotiatedDeadLine = gcal.getTime();
        }


        Boolean isApproved = (Boolean) cbWorkApproved.getValue();
        String comments = (String) taComments.getText();
        sentItem = itemDAO.updateSentItem(sentItem, negotiatedDeadLine, isApproved, comments);
//        if (("DONE-CONFIRMED").equals(sentItem.getStatus()) && sentItem.getParent()!= null){
//            
//        }else{
        getSessionBean1().setCurrentSentItem(sentItem);
        return btnBack_action();
//        }

    }

    private Item createSentItemFromGUI() {
        Item sentItem = new Item();

        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        if (calNegoDeadline.getSelectedDate() != null) {
            gcal.setTime(calNegoDeadline.getSelectedDate());
            Date deadlineTime = (Date) tfNegoDeadlineTime.getValue();
            if (deadlineTime != null) {
                GregorianCalendar gcalHM = (GregorianCalendar) GregorianCalendar.getInstance();
                gcalHM.setTime(deadlineTime);
                gcal.set(GregorianCalendar.HOUR, gcalHM.get(GregorianCalendar.HOUR));
                gcal.set(GregorianCalendar.MINUTE, gcalHM.get(GregorianCalendar.MINUTE));

                if (rbNegoDeadlineAM.getSelected() != null && rbNegoDeadlinePM.getSelected() != null) {
                    if (rbNegoDeadlineAM.getSelected().equals(true) && rbNegoDeadlinePM.getSelected().equals(false)) {
                        gcal.set(GregorianCalendar.AM_PM, GregorianCalendar.AM);
                    } else if (rbNegoDeadlineAM.getSelected().equals(false) && rbNegoDeadlinePM.getSelected().equals(true)) {
                        gcal.set(GregorianCalendar.AM_PM, GregorianCalendar.PM);
                    }
                }
            }
            sentItem.setNegotiatedDeadLine(gcal.getTime());
        }
        sentItem.setCommentsOnFeedback((String) taComments.getText());
        if ((new Boolean(true)).equals((Boolean) cbWorkApproved.getValue())) {
            sentItem.setRedoCounter(0);
        } else {
            sentItem.setRedoCounter(1);
        }
        return sentItem;
    }

    private void setupGUIFromSentItem(Item sentItem) {
        calNegoDeadline.setSelectedDate(sentItem.getNegotiatedDeadLine());
        if (sentItem.getNegotiatedDeadLine() != null) {
            GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
            gcal.setTime(sentItem.getNegotiatedDeadLine());
            SimpleDateFormat sdf = new SimpleDateFormat(ResourceBundle.getBundle("epice").getString("HM_ONLY_DATE_FORMAT"));
            tfNegoDeadlineTime.setText(sdf.format(gcal.getTime()));
            if (gcal.get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
                rbNegoDeadlineAM.setSelected(true);
                rbNegoDeadlinePM.setSelected(false);
            } else {
                rbNegoDeadlineAM.setSelected(false);
                rbNegoDeadlinePM.setSelected(true);
            }
            taComments.setText(sentItem.getCommentsOnFeedback());
        }
        if (sentItem.getRedoCounter() == 0) {
            cbWorkApproved.setSelected(new Boolean(true));
        } else {
            cbWorkApproved.setSelected(new Boolean(false));
        }
    }

    public String btnBack_action() {
        //reset current file repo
        getSessionBean1().setCurrentFileRepository(null);

        //do redirect
        if (("PgSearch").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQuery()));
            getSessionBean1().popPreviousPage();
            return "gotoSearch";
        } else if (("PgReceivedItem").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().popPreviousPage();
            getSessionBean1().refreshChildrenItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getChildrenItemsQuery()));
            return "gotoReceivedItem";
        }  else if (("PgTreeView").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().popPreviousPage();
            return "gotoTreeView";
        } else {
            getSessionBean1().popPreviousPage();
            return null;
        }
    }

    public List<StringWrapper> getLinks() {
        Item sentItem = (Item) getSessionBean1().getCurrentSentItem();
        List<StringWrapper> links = new ArrayList<StringWrapper>();
        if (sentItem != null) {
            for (String link : sentItem.getLinks()) {
                links.add(new StringWrapper(link));
            }
        }
        return links;
    }

    public String btnEdit_action() {

        getSessionBean1().setPreviousPage("PgSentItem");
        getSessionBean1().setCurrentEditedItem(getSessionBean1().getCurrentSentItem());

        //reset current file repo
        getSessionBean1().setCurrentFileRepository(null);

        //edit item
        return "gotoNewItem";
    }

    public String hyperlink1_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }

    public String btnTreeView_action() {

        getSessionBean1().setCurrentTreeViewItem(getSessionBean1().getCurrentSentItem());

        //empty tree items
        getSessionBean1().getSupervisedItemsSingleRootList().clear();

        //empty tree 
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        PgTreeView pgTreeView = (PgTreeView) app.getExpressionFactory().createValueExpression(ctx.getELContext(), "#{PgTreeView}", PgTreeView.class).getValue(ctx.getELContext());
        if (pgTreeView != null) {
            pgTreeView.getTrSupervisedItems().getChildren().clear();
        }

        //reset current file repo
        getSessionBean1().setCurrentFileRepository(null);

        return "gotoTreeView";
    }

    public String btnDelete_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        Item selectedSentItem = getSessionBean1().getCurrentSentItem();
        if (("SENT-UNCONFIRMED").equals(selectedSentItem.getStatus())) {
            itemDAO.delete(selectedSentItem);
        }
        //reset current file repo
        getSessionBean1().setCurrentFileRepository(null);
        return btnBack_action();
    }

    public String btnGotoAttachments_action() {
        Person user = getSessionBean1().getCurrentUser();
        Item sentItem = getSessionBean1().getCurrentSentItem();
        if (getSessionBean1().getCurrentFileRepository() == null) {
            //if the user is just a supervisor, disable upload
            if (!sentItem.getFromUser().equals(user)) {
                getSessionBean1().setEnableUpload(false);
            } else {
                getSessionBean1().setEnableUpload(("SENT-ACCEPTED").equals(sentItem.getStatus()) || ("DELEGATED").equals(sentItem.getStatus()) || ("NEED-REDO").equals(sentItem.getStatus()) || ("NEED-REDO DELEGATED").equals(sentItem.getStatus()));
            }
            getSessionBean1().setCurrentFileRepository(getSessionBean1().getCurrentSentItem().getFileRepository());
        }
        getSessionBean1().getCurrentFileRepository().refreshDataProvider(getSessionBean1().getCurrentUser());
        getSessionBean1().setTempSentItem(createSentItemFromGUI());
        getSessionBean1().setPreviousPage("PgSentItem");
        return "gotoFileRepository";
    }

    public String btnAcceptRejectNSeeParent_action() {
        Item sentItem = getSessionBean1().getCurrentSentItem();

        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        Date negotiatedDeadLine = null;
        if (calNegoDeadline.getSelectedDate() != null) {
            gcal.setTime(calNegoDeadline.getSelectedDate());
            Date deadlineTime = (Date) tfNegoDeadlineTime.getValue();
            if (deadlineTime != null) {
                GregorianCalendar gcalHM = (GregorianCalendar) GregorianCalendar.getInstance();
                gcalHM.setTime(deadlineTime);
                gcal.set(GregorianCalendar.HOUR, gcalHM.get(GregorianCalendar.HOUR));
                gcal.set(GregorianCalendar.MINUTE, gcalHM.get(GregorianCalendar.MINUTE));

                if (rbNegoDeadlineAM.getSelected() != null && rbNegoDeadlinePM.getSelected() != null) {
                    if (rbNegoDeadlineAM.getSelected().equals(true) && rbNegoDeadlinePM.getSelected().equals(false)) {
                        gcal.set(GregorianCalendar.AM_PM, GregorianCalendar.AM);
                    } else if (rbNegoDeadlineAM.getSelected().equals(false) && rbNegoDeadlinePM.getSelected().equals(true)) {
                        gcal.set(GregorianCalendar.AM_PM, GregorianCalendar.PM);
                    }
                }
            }
            negotiatedDeadLine = gcal.getTime();
        }
        Boolean isApproved = (Boolean) cbWorkApproved.getValue();
        String comments = (String) taComments.getText();
        sentItem = itemDAO.updateSentItem(sentItem, negotiatedDeadLine, isApproved, comments);
        getSessionBean1().setCurrentSentItem(sentItem);
        if (sentItem.getParent() != null) {
            getSessionBean1().setCurrentReceivedItem(sentItem.getParent());
            getSessionBean1().setPreviousPage("PgSentItem");
            return "gotoReceivedItem";
        } else {
            return btnBack_action();
        }
    }

    public String btnAddTags_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        Item sentItem = (Item) getSessionBean1().getCurrentSentItem();
        String tags = (String) getTfAddTags().getText();
        Set<String> tagSet = new HashSet<String>();
        if (tags != null) {
            StringTokenizer tagsTokenizer = new StringTokenizer(tags, ",");
            while (tagsTokenizer.hasMoreElements()) {
                String tag = tagsTokenizer.nextToken();
                if (tag != null) {
                    tagSet.add(tag.trim());
                }
            }
        }
        sentItem = itemDAO.addTags(sentItem, tagSet);
        //save tags to application context
        getApplicationBean1().addTags(tagSet);

        //refresh sent item
        getSessionBean1().setCurrentSentItem(sentItem);
        getSessionBean1().setTempSentItem(createSentItemFromGUI());
        init();
        return null;
    }

    public String btnRemoveTag_action() {
        Item sentItem = (Item) getSessionBean1().getCurrentSentItem();
       
        Object o =  getLbTag().getSelected();
        if (o != null) {
            String tag = (String) o;
            sentItem = itemDAO.removeTag(sentItem, tag);
            getSessionBean1().setCurrentSentItem(sentItem);
            getSessionBean1().setTempSentItem(createSentItemFromGUI());
            init();
        }

        return null;
    }

    public void cbAdvanceView_processValueChange(ValueChangeEvent event) {
         if ((Boolean.FALSE).equals(cbAdvanceView.getSelected())){
            tfItemId.setVisible(false);
            tfFrom.setVisible(false);
            lbTag.setVisible(false);
            tfSentDate.setVisible(false);
            tbLinks.setVisible(false);
        }else{
            tfItemId.setVisible(true);
            tfFrom.setVisible(true);
            lbTag.setVisible(true);
            tfSentDate.setVisible(true);
            tbLinks.setVisible(true);
        }
    }
    
    public Option[] tagsFilter(String word) {
        String suffix = word;
        String prefix = "";
        int lastComma = word.lastIndexOf(',');
        if (lastComma >= 0) {
            suffix = word.substring(word.lastIndexOf(',') + 1);
            prefix = word.substring(0, word.lastIndexOf(','));
        }
        List<Option> filteredValues = new ArrayList<Option>();
        if (lastComma >= 0) { //prefix exist

            suffix = suffix.trim();
            for (String t : getApplicationBean1().getTags()) {
                if (suffix.length() == 0 || t.indexOf(suffix) >= 0) {
                    filteredValues.add(new Option(prefix + ", " + t, t));
                }
            }
        } else {
            for (String t : getApplicationBean1().getTags()) {
                if (t.indexOf(suffix) >= 0) {
                    filteredValues.add(new Option(t, t));
                }
            }
        }
        return filteredValues.toArray(new Option[1]);
    }

    public String hlOpenAttachment_action() {
        FileInputStream fileToDownload = null;
        ServletOutputStream out = null;
        try {
            RowKey rk = getTrgAttachments().getRowKey();
            Attachment attachment = (Attachment) getSessionBean1().getFileRepositoryDP().getObject(rk);
            java.io.File file = new java.io.File(attachment.getFilePath());
            fileToDownload = new FileInputStream(file);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            out = response.getOutputStream();

            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.setContentLength(fileToDownload.available());

            int c;
            while ((c = fileToDownload.read()) != -1) {
                out.write(c);
            }
            out.flush();

        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);

        } finally {
            if (fileToDownload != null) {
                try {
                    fileToDownload.close();
                } catch (IOException ex) {
                    LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
                }
            }
        }
        return null;
    }
}

