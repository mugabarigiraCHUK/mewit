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
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.Hyperlink;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Listbox;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.PanelGroup;
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
import com.sun.webui.jsf.model.DefaultOptionsList;
import com.sun.webui.jsf.model.Option;
import com.sun.webui.jsf.model.SingleSelectOptionsList;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.query.ChildrenItemsQuery;
import org.azrul.epice.dao.query.factory.ChildrenItemsQueryFactory;
import org.azrul.epice.dao.query.factory.SearchItemsQueryFactory;
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
public class PgReceivedItem extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        ddAcceptRejectDefaultOptions.setOptions(new com.sun.webui.jsf.model.Option[]{new com.sun.webui.jsf.model.Option("SENT-ACCEPTED", "Accept"), new com.sun.webui.jsf.model.Option("SENT-NEGOTIATED", "Accept with negotiation"), new com.sun.webui.jsf.model.Option("SENT-REJECTED", "Reject")});
        dateTimeConverter1.setTimeZone(null);
        dateTimeConverter1.setPattern(getSessionBean1().getLongDateFormat());
        linksDataProvider.setList(null);
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
    private TextField tfItemId = new TextField();

    public TextField getTfItemId() {
        return tfItemId;
    }

    public void setTfItemId(TextField tf) {
        this.tfItemId = tf;
    }
    private TextField tfFrom = new TextField();

    public TextField getTfFrom() {
        return tfFrom;
    }

    public void setTfFrom(TextField tf) {
        this.tfFrom = tf;
    }
    private Listbox lbTo = new Listbox();

    public Listbox getLbTo() {
        return lbTo;
    }

    public void setLbTo(Listbox l) {
        this.lbTo = l;
    }
    private DefaultOptionsList lbToDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbToDefaultOptions() {
        return lbToDefaultOptions;
    }

    public void setLbToDefaultOptions(DefaultOptionsList dol) {
        this.lbToDefaultOptions = dol;
    }
    private TextField tfSubject = new TextField();

    public TextField getTfSubject() {
        return tfSubject;
    }

    public void setTfSubject(TextField tf) {
        this.tfSubject = tf;
    }
    private TextArea taAction = new TextArea();

    public TextArea getTaAction() {
        return taAction;
    }

    public void setTaAction(TextArea ta) {
        this.taAction = ta;
    }
    private TextArea taFeedback = new TextArea();

    public TextArea getTaFeedback() {
        return taFeedback;
    }

    public void setTaFeedback(TextArea ta) {
        this.taFeedback = ta;
    }
    private TextArea taComments = new TextArea();

    public TextArea getTaComments() {
        return taComments;
    }

    public void setTaComments(TextArea ta) {
        this.taComments = ta;
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
    private TextField tfDeadline = new TextField();

    public TextField getTfDeadline() {
        return tfDeadline;
    }

    public void setTfDeadline(TextField tf) {
        this.tfDeadline = tf;
    }
    private Button btnBack = new Button();

    public Button getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(Button b) {
        this.btnBack = b;
    }
    private Button btnSubmit = new Button();

    public Button getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(Button b) {
        this.btnSubmit = b;
    }
    private DropDown ddAcceptReject = new DropDown();

    public DropDown getDdAcceptReject() {
        return ddAcceptReject;
    }

    public void setDdAcceptReject(DropDown dd) {
        this.ddAcceptReject = dd;
    }
    private SingleSelectOptionsList ddAcceptRejectDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdAcceptRejectDefaultOptions() {
        return ddAcceptRejectDefaultOptions;
    }

    public void setDdAcceptRejectDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddAcceptRejectDefaultOptions = ssol;
    }
    private DateTimeConverter dateTimeConverter1 = new DateTimeConverter();

    public DateTimeConverter getDateTimeConverter1() {
        return dateTimeConverter1;
    }

    public void setDateTimeConverter1(DateTimeConverter dtc) {
        this.dateTimeConverter1 = dtc;
    }
    private TextArea taReasonNegoReject = new TextArea();

    public TextArea getTaReasonNegoReject() {
        return taReasonNegoReject;
    }

    public void setTaReasonNegoReject(TextArea ta) {
        this.taReasonNegoReject = ta;
    }
    private Listbox lbTag = new Listbox();

    public Listbox getLbTag() {
        return lbTag;
    }

    public void setLbTag(Listbox l) {
        this.lbTag = l;
    }
    private DefaultOptionsList lbTagDefaultOptions = new DefaultOptionsList();

    public DefaultOptionsList getLbTagDefaultOptions() {
        return lbTagDefaultOptions;
    }

    public void setLbTagDefaultOptions(DefaultOptionsList dol) {
        this.lbTagDefaultOptions = dol;
    }
    private Button btnDelegate = new Button();

    public Button getBtnDelegate() {
        return btnDelegate;
    }

    public void setBtnDelegate(Button b) {
        this.btnDelegate = b;
    }
    private Calendar calNegoDeadline = new Calendar();

    public Calendar getCalNegoDeadline() {
        return calNegoDeadline;
    }

    public void setCalNegoDeadline(Calendar c) {
        this.calNegoDeadline = c;
    }
    private PanelGroup groupPanel1 = new PanelGroup();

    public PanelGroup getGroupPanel1() {
        return groupPanel1;
    }

    public void setGroupPanel1(PanelGroup pg) {
        this.groupPanel1 = pg;
    }
    private PropertySheet propertySheet1 = new PropertySheet();

    public PropertySheet getPropertySheet1() {
        return propertySheet1;
    }

    public void setPropertySheet1(PropertySheet ps) {
        this.propertySheet1 = ps;
    }
    private PropertySheetSection section1 = new PropertySheetSection();

    public PropertySheetSection getSection1() {
        return section1;
    }

    public void setSection1(PropertySheetSection pss) {
        this.section1 = pss;
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
    private Property property12 = new Property();

    public Property getProperty12() {
        return property12;
    }

    public void setProperty12(Property p) {
        this.property12 = p;
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
    private HtmlCommandLink linkAction2 = new HtmlCommandLink();

    public HtmlCommandLink getLinkAction2() {
        return linkAction2;
    }

    public void setLinkAction2(HtmlCommandLink hcl) {
        this.linkAction2 = hcl;
    }
    private HtmlOutputText linkAction2Text = new HtmlOutputText();

    public HtmlOutputText getLinkAction2Text() {
        return linkAction2Text;
    }

    public void setLinkAction2Text(HtmlOutputText hot) {
        this.linkAction2Text = hot;
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
        linksDataProvider.setList(new ArrayList(getLinks()));
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
    private StaticText staticText1 = new StaticText();

    public StaticText getStaticText1() {
        return staticText1;
    }

    public void setStaticText1(StaticText st) {
        this.staticText1 = st;
    }
    private Property property17 = new Property();

    public Property getProperty17() {
        return property17;
    }

    public void setProperty17(Property p) {
        this.property17 = p;
    }
    private Table tbChildrenItems = new Table();

    public Table getTbChildrenItems() {
        return tbChildrenItems;
    }

    public void setTbChildrenItems(Table t) {
        this.tbChildrenItems = t;
    }
    private TableRowGroup trgChildrenItems = new TableRowGroup();

    public TableRowGroup getTrgChildrenItems() {
        return trgChildrenItems;
    }

    public void setTrgChildrenItems(TableRowGroup trg) {
        this.trgChildrenItems = trg;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }
    private Button btnGotoAttachment = new Button();

    public Button getBtnGotoAttachment() {
        return btnGotoAttachment;
    }

    public void setBtnGotoAttachment(Button b) {
        this.btnGotoAttachment = b;
    }
    private Property property18 = new Property();

    public Property getProperty18() {
        return property18;
    }

    public void setProperty18(Property p) {
        this.property18 = p;
    }

    // </editor-fold>
    ItemDAO itemDAO = ItemDAOFactory.getInstance();
    private RadioButton rbNegoDeadlinePM = new RadioButton();

    public RadioButton getRbNegoDeadlinePM() {
        return rbNegoDeadlinePM;
    }

    public void setRbNegoDeadlinePM(RadioButton rb) {
        this.rbNegoDeadlinePM = rb;
    }
    private RadioButton rbNegoDeadlineAM = new RadioButton();

    public RadioButton getRbNegoDeadlineAM() {
        return rbNegoDeadlineAM;
    }

    public void setRbNegoDeadlineAM(RadioButton rb) {
        this.rbNegoDeadlineAM = rb;
    }
    private TextField tfNegoDeadlineTime = new TextField();

    public TextField getTfNegoDeadlineTime() {
        return tfNegoDeadlineTime;
    }

    public void setTfNegoDeadlineTime(TextField tf) {
        this.tfNegoDeadlineTime = tf;
    }
    private TextField tfAddTags = new TextField();

    public TextField getTfAddTags() {
        return tfAddTags;
    }

    public void setTfAddTags(TextField tf) {
        this.tfAddTags = tf;
    }
    private Button btnAddTags = new Button();

    public Button getBtnAddTags() {
        return btnAddTags;
    }

    public void setBtnAddTags(Button b) {
        this.btnAddTags = b;
    }
    private Button btnRemoveTag = new Button();

    public Button getBtnRemoveTag() {
        return btnRemoveTag;
    }

    public void setBtnRemoveTag(Button b) {
        this.btnRemoveTag = b;
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

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgReceivedItem() {
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
            log("PgItem Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
        initForm();

    }

    public void initForm() {
        Item receivedItem = (Item) getSessionBean1().getCurrentReceivedItem();
        receivedItem = itemDAO.refresh(receivedItem);
        
        //refresh file repo
        Boolean uploadable = false;
        //if (getSessionBean1().)
        getSessionBean1().setCurrentFileRepository(receivedItem.getFileRepository());
        getSessionBean1().getCurrentFileRepository().refreshDataProvider(getSessionBean1().getCurrentUser());
     
        //set ui
        tfItemId.setText(receivedItem.getId());
        tfSubject.setText(receivedItem.getSubject());
        tfDeadline.setText(receivedItem.getDeadLine());
        tfFrom.setText(receivedItem.getFromUser().getEmail());
        if (cbAdvanceView.getSelected() == null || (Boolean.FALSE).equals(cbAdvanceView.getSelected())){
            tfItemId.setVisible(false);
            lbTo.setVisible(false);
            lbTag.setVisible(false);
            tfSentDate.setVisible(false);
            tbLinks.setVisible(false);
        }else{
            tfItemId.setVisible(true);
            lbTo.setVisible(true);
            lbTag.setVisible(true);
            tfSentDate.setVisible(true);
            tbLinks.setVisible(true);
        }

        calNegoDeadline.setSelectedDate(receivedItem.getNegotiatedDeadLine());
        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        if (receivedItem.getNegotiatedDeadLine() != null) {
            gcal.setTime(receivedItem.getNegotiatedDeadLine());
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

        tfSentDate.setText(receivedItem.getSentDate());
        tfStatus.setText(receivedItem.getStatus());
        tfSubject.setText(receivedItem.getSubject());
        taAction.setText(receivedItem.getDescription());
        taComments.setText(receivedItem.getCommentsOnFeedback());
        taFeedback.setText(receivedItem.getFeedback());

        List<Option> toUsersOptions = new ArrayList<Option>();
        for (Person p : receivedItem.getToUsers()) {
            Option o = new Option(p.getEmail(), p.getName() + "[" + p.getEmail() + "]");
            toUsersOptions.add(o);
        }
        DefaultOptionsList dolUsers = new DefaultOptionsList();
        dolUsers.setOptions(toUsersOptions.toArray(new Option[1]));
        setLbToDefaultOptions(dolUsers);
        getLinksDataProvider().setList(new ArrayList(receivedItem.getLinks()));

        if (receivedItem.getTags() != null) {
            List<Option> tagsOptions = new ArrayList<Option>();
            for (String tag : receivedItem.getTags()) {
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

        if (("SENT-UNCONFIRMED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(true);

            if (("SENT-REJECTED").equals(ddAcceptReject.getSelected())) {
                calNegoDeadline.setVisible(true);
                calNegoDeadline.setDisabled(true);
                tfNegoDeadlineTime.setVisible(true);
                tfNegoDeadlineTime.setDisabled(true);
                rbNegoDeadlineAM.setVisible(true);
                rbNegoDeadlineAM.setDisabled(true);
                rbNegoDeadlinePM.setVisible(true);
                rbNegoDeadlinePM.setDisabled(true);

                taReasonNegoReject.setVisible(true);
                taReasonNegoReject.setDisabled(false);
            } else if (("SENT-NEGOTIATED").equals(ddAcceptReject.getSelected())) {
                calNegoDeadline.setVisible(true);
                calNegoDeadline.setDisabled(false);
                tfNegoDeadlineTime.setVisible(true);
                tfNegoDeadlineTime.setDisabled(false);
                rbNegoDeadlineAM.setVisible(true);
                rbNegoDeadlineAM.setDisabled(false);
                rbNegoDeadlinePM.setVisible(true);
                rbNegoDeadlinePM.setDisabled(false);

                rbNegoDeadlineAM.setSelected(false);
                rbNegoDeadlinePM.setSelected(false);


                taReasonNegoReject.setVisible(true);
                taReasonNegoReject.setDisabled(false);
            } else {
                calNegoDeadline.setVisible(false);
                calNegoDeadline.setDisabled(true);
                tfNegoDeadlineTime.setVisible(false);
                tfNegoDeadlineTime.setDisabled(true);
                rbNegoDeadlineAM.setVisible(false);
                rbNegoDeadlineAM.setDisabled(true);
                rbNegoDeadlinePM.setVisible(false);
                rbNegoDeadlinePM.setDisabled(true);

                taReasonNegoReject.setVisible(false);
                taReasonNegoReject.setDisabled(true);
            }

            btnRemoveTag.setVisible(false);
            btnAddTags.setVisible(false);
            tfAddTags.setVisible(false);
            taFeedback.setVisible(false);
            taComments.setVisible(false);
            tbChildrenItems.setVisible(false);
            btnDelegate.setVisible(false);
            btnSubmit.setVisible(true);
            btnSubmit.setText("Confirm");

        } else if (("SENT-NEGOTIATED").equals(receivedItem.getStatus())) {

            ddAcceptReject.setVisible(false);

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
            taReasonNegoReject.setText(receivedItem.getReasonForNegotiatiationOfDeadLine());

            tbChildrenItems.setVisible(false);

            taFeedback.setVisible(false);

            taComments.setVisible(false);

            btnDelegate.setVisible(false);

            btnSubmit.setVisible(false);

            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
        } else if (("SENT-ACCEPTED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);

            tbChildrenItems.setVisible(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(false);

            taComments.setVisible(false);

            btnDelegate.setVisible(true);

            btnSubmit.setVisible(true);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
            btnSubmit.setText("Job Is Done");
        } else if (("DELEGATED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(false);

            tbChildrenItems.setVisible(true);

            taComments.setVisible(false);

            btnDelegate.setVisible(true);

            btnSubmit.setVisible(true);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
            btnSubmit.setText("Job Is Done");
        } else if (("SENT-REJECTED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(true);
            taReasonNegoReject.setText(receivedItem.getReasonForRejectionOfTask());

            taFeedback.setVisible(false);
            taFeedback.setDisabled(true);

            tbChildrenItems.setVisible(false);

            taComments.setVisible(false);

            btnDelegate.setVisible(false);

            btnSubmit.setVisible(false);
            btnRemoveTag.setVisible(false);
            btnAddTags.setVisible(false);
            tfAddTags.setVisible(false);
        } else if (("DONE-UNCONFIRMED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);


            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);

            tbChildrenItems.setVisible(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(false);

            btnDelegate.setVisible(false);

            btnSubmit.setVisible(false);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
        } else if (("DONE-CONFIRMED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(true);

            taComments.setVisible(true);
            taComments.setDisabled(true);

            btnDelegate.setVisible(false);

            btnSubmit.setVisible(false);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
        } else if (("NEED-REDO").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(false);

            taComments.setVisible(true);
            taComments.setDisabled(true);

            btnDelegate.setVisible(true);

            btnSubmit.setVisible(true);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
            btnSubmit.setText("Job Is Done");
        } else if (("NEED-REDO DELEGATED").equals(receivedItem.getStatus())) {
            ddAcceptReject.setVisible(false);

            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);

            taFeedback.setVisible(true);
            taFeedback.setDisabled(false);

            taComments.setVisible(true);
            taComments.setDisabled(true);

            btnDelegate.setVisible(true);

            btnSubmit.setVisible(true);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);
            btnSubmit.setText("Job Is Done");
        } else if (("REFERENCE").equals(receivedItem.getStatus())) {
            calNegoDeadline.setVisible(false);
            tfNegoDeadlineTime.setVisible(false);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlinePM.setVisible(false);
            ddAcceptReject.setVisible(false);
            taReasonNegoReject.setVisible(false);
            taFeedback.setVisible(false);
            taComments.setVisible(false);
            btnSubmit.setVisible(false);
            tfDeadline.setVisible(false);
            btnRemoveTag.setVisible(true);
            btnAddTags.setVisible(true);
            tfAddTags.setVisible(true);

        }

        //if temp received item not null, restore GUI with its value
        Item item = getSessionBean1().getTempReceivedItem();
        if (item != null) {
            setupGUIFromItem(item);
            getSessionBean1().setTempReceivedItem(null);
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
        //refresh children list
        ChildrenItemsQuery query = ChildrenItemsQueryFactory.getInstance();
        query.setParentItem(getSessionBean1().getCurrentReceivedItem());
        getSessionBean1().setChildrenItemsQuery(query);
        List<Item> children = itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(),getSessionBean1().getChildrenItemsQuery());
        if (!children.isEmpty()) {
            getSessionBean1().refreshChildrenItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getChildrenItemsQuery()));
        } else {
            getSessionBean1().emptyChildrenItemsDP();
        }
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

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean1 getApplicationBean1() {
        return (ApplicationBean1) getBean("ApplicationBean1");
    }

    public String btnSubmit_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        final String acceptReject = (String) ddAcceptReject.getSelected();

        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        Date selectedDate = null;
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
            selectedDate = gcal.getTime();
        }


        String reasonNegoReject = (String) taReasonNegoReject.getText();
        String feedback = (String) taFeedback.getText();
        Item receivedItem = itemDAO.updateReceivedItem(getSessionBean1().getCurrentReceivedItem(), acceptReject, selectedDate, reasonNegoReject, feedback);
        getSessionBean1().setCurrentReceivedItem(receivedItem);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQuery()));

        //reset current file repo
        getSessionBean1().setCurrentFileRepository(null);
        if (("SENT-ACCEPTED").equals(receivedItem.getStatus()) || ("DELEGATED").equals(receivedItem.getStatus())) {
            initForm(); //if we are accepting or delegating, we need to reload the form
            return null;
        } else {
            return btnBack_action();
        }

    }

    

    public String btnDelegate_action() {


        Item item = getSessionBean1().getCurrentReceivedItem();
        getSessionBean1().setParent(item);

        if (item.getLinks() != null) {
            try {
                for (String link : item.getLinks()) {
                    getSessionBean1().getLinks().add(new URL(link));
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(PgReceivedItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //reset current file repo
        getSessionBean1().setCurrentFileRepository(null);

        //set prev page
        getSessionBean1().setPreviousPage("PgReceivedItem");

        //delegate
        return "gotoNewItem";
    }

    public void ddAcceptReject_processValueChange(ValueChangeEvent vce) {
        
        if (("SENT-REJECTED").equals(vce.getNewValue())) {
            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(false);
        } else if (("SENT-NEGOTIATED").equals(vce.getNewValue())) {
            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(false);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(false);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(false);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(false);

            Item receivedItem = getSessionBean1().getCurrentReceivedItem();
            calNegoDeadline.setSelectedDate(receivedItem.getDeadLine());
            GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();

            if (receivedItem.getDeadLine() != null) {
                gcal.setTime(receivedItem.getDeadLine());
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
            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(false);
        } else if (("SENT-ACCEPTED").equals(vce.getNewValue())){
            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);
        }
    }

    public String dataTable1_firstPageAction() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }

    public Set<StringWrapper> getLinks() {
        Item receivedItem = (Item) getSessionBean1().getCurrentReceivedItem();
        Set<StringWrapper> links = new HashSet<StringWrapper>();
        if (receivedItem.getLinks() != null) {
            for (String link : receivedItem.getLinks()) {
                links.add(new StringWrapper(link));
            }
        }
        return links;
    }

    public String hyperlink1_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }

    public String btnChooseChildItem_action() {
        RowKey rk = getTrgChildrenItems().getRowKey();
        getSessionBean1().setCurrentSentItem((Item) getSessionBean1().getChildrenItemsDP().getObject(rk));
        getSessionBean1().setCurrentFileRepository(getSessionBean1().getCurrentSentItem().getFileRepository());
        getSessionBean1().setPreviousPage("PgReceivedItem");
        return "gotoSentItem"; //go to child
    }

    public String btnGotoAttachment_action() {
        if (getSessionBean1().getCurrentFileRepository() == null) {
            /*Note: since we do this, currentFileRepository and currentSentItem.fileRepository is pointing to the 
            same FileRepository object. Any modif to currentFilerepository (new uploaded docs, deleted docs etc.) 
            will be reflected back to currentSentItem.fileRepository. Therefore if currentSentItem is saved, so will
            the updated currentSentItem.fileRepository*/
            getSessionBean1().setCurrentFileRepository(getSessionBean1().getCurrentReceivedItem().getFileRepository());
        }
        Item receivedItem = getSessionBean1().getCurrentReceivedItem();
        getSessionBean1().setEnableUpload(("SENT-ACCEPTED").equals(receivedItem.getStatus()) || ("DELEGATED").equals(receivedItem.getStatus()) || ("NEED-REDO").equals(receivedItem.getStatus()) || ("NEED-REDO DELEGATED").equals(receivedItem.getStatus()));
            
        getSessionBean1().getCurrentFileRepository().refreshDataProvider(getSessionBean1().getCurrentUser());
        getSessionBean1().setPreviousPage("PgReceivedItem");
        getSessionBean1().setTempReceivedItem(createReceivedItemFromGUI());
        return "gotoFileRepository";
    }

    private Item createReceivedItemFromGUI() {
        Item item = new Item();
        item.setStatus((String) ddAcceptReject.getSelected());
        item.setReasonForNegotiatiationOfDeadLine((String) taReasonNegoReject.getText());

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
            item.setNegotiatedDeadLine(gcal.getTime());
        }


        item.setFeedback((String) taFeedback.getText());
        return item;
    }

    public void setupGUIFromItem(Item item) {
        ddAcceptReject.setSelected(item.getStatus());
        taReasonNegoReject.setText(item.getReasonForNegotiatiationOfDeadLine());


//        
//        calNegoDeadline.setSelectedDate(item.getNegotiatedDeadLine());
//        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
//        if (item.getNegotiatedDeadLine() != null) {
//            gcal.setTime(item.getNegotiatedDeadLine());
//            SimpleDateFormat sdfHM = new SimpleDateFormat("hh:mm");
//            tfNegoDeadlineTime.setText(sdfHM.format(gcal.getTime()));
//            if (gcal.get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
//                rbNegoDeadlineAM.setSelected(true);
//                rbNegoDeadlinePM.setSelected(false);
//            } else {
//                rbNegoDeadlineAM.setSelected(false);
//                rbNegoDeadlinePM.setSelected(true);
//            }
//        }
        if (item.getNegotiatedDeadLine() != null) {
            calNegoDeadline.setSelectedDate(item.getNegotiatedDeadLine());
            GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
            gcal.setTime(item.getNegotiatedDeadLine());
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

        taFeedback.setText(item.getFeedback());
        if (("SENT-REJECTED").equals(item.getStatus())) {
            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(false);
        } else if (("SENT-NEGOTIATED").equals(item.getStatus())) {
            calNegoDeadline.setVisible(true);
            calNegoDeadline.setDisabled(false);
            tfNegoDeadlineTime.setVisible(true);
            tfNegoDeadlineTime.setDisabled(false);
            rbNegoDeadlineAM.setVisible(true);
            rbNegoDeadlineAM.setDisabled(false);
            rbNegoDeadlinePM.setVisible(true);
            rbNegoDeadlinePM.setDisabled(false);



            taReasonNegoReject.setVisible(true);
            taReasonNegoReject.setDisabled(false);
        } else {
            calNegoDeadline.setVisible(false);
            calNegoDeadline.setDisabled(true);
            tfNegoDeadlineTime.setVisible(false);
            tfNegoDeadlineTime.setDisabled(true);
            rbNegoDeadlineAM.setVisible(false);
            rbNegoDeadlineAM.setDisabled(true);
            rbNegoDeadlinePM.setVisible(false);
            rbNegoDeadlinePM.setDisabled(true);

            taReasonNegoReject.setVisible(false);
            taReasonNegoReject.setDisabled(true);
        }
    }

    public ItemDataProvider getChildrenItemsDP() {
        return getSessionBean1().getChildrenItemsDP();
    }

    public void setChildrenItemsDP(ItemDataProvider itemDP) {
        getSessionBean1().setChildrenItemsDP(itemDP);
    }

    public void rbNegoDeadlineAM_processValueChange(ValueChangeEvent event) {
    }

    public String btnAddTags_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        Item receivedItem = (Item) getSessionBean1().getCurrentReceivedItem();
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
        receivedItem = itemDAO.addTags(receivedItem, tagSet);
         //save tags to application context
        getApplicationBean1().addTags(tagSet);

        //refresh sent item
        getSessionBean1().setCurrentReceivedItem(receivedItem);
        getSessionBean1().setTempReceivedItem(createReceivedItemFromGUI());
        initForm();
        return null;
    }

    public String btnRemoveTag_action() {
        Item receivedItem = (Item) getSessionBean1().getCurrentReceivedItem();

        Object o = getLbTag().getSelected();
        if (o != null) {
            String tag = (String) o;
            receivedItem = itemDAO.removeTag(receivedItem, tag);
            getSessionBean1().setCurrentReceivedItem(receivedItem);
            getSessionBean1().setTempSentItem(createReceivedItemFromGUI());
            initForm();
        }
        return null;
    }

    public void cbAdvanceView_processValueChange(ValueChangeEvent event) {
         if ((Boolean.FALSE).equals(cbAdvanceView.getSelected())){
            tfItemId.setVisible(false);
            lbTo.setVisible(false);
            lbTag.setVisible(false);
            tfSentDate.setVisible(false);
            tbLinks.setVisible(false);
        }else{
            tfItemId.setVisible(true);
            lbTo.setVisible(true);
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

    public String hyperlink2_action() {
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

    public String btnBack_action() {
         getSessionBean1().setCurrentFileRepository(null);



        //load default received items query if null (in case of access directly from url)
        if (getSessionBean1().getSearchItemsQuery() == null) {
            getSessionBean1().setSearchItemsQuery(SearchItemsQueryFactory.getInstance());
        }

        //do redirect
        if (("PgSearch").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().popPreviousPage();
            return "gotoSearch";
        } else if (("PgSentItem").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().popPreviousPage();
            return "gotoSentItem"; //go to child item
        } else if (("PgMenu").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().popPreviousPage();
            return "gotoMenu";
        } else if (("PgReceivedItem").equals(getSessionBean1().getPreviousPage())){
            if (getSessionBean1().getCurrentReceivedItem().getFromUser().equals(getSessionBean1().getCurrentReceivedItem().getToUser())){
                return "gotoMenu";
            }else{
                return null;
            }
        }else{
             return null;
        }
    }
}

