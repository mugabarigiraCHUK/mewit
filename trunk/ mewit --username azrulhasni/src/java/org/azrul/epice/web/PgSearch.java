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
import com.sun.webui.jsf.component.Checkbox;
import com.sun.webui.jsf.component.DropDown;
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.Table;
import com.sun.webui.jsf.component.TableColumn;
import com.sun.webui.jsf.component.TableRowGroup;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.event.TableSelectPhaseListener;
import com.sun.webui.jsf.model.SingleSelectOptionsList;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.FacesException;
import javax.faces.event.ValueChangeEvent;
import org.azrul.epice.domain.Item;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.SearchItemsQueryDAO;
import org.azrul.epice.dao.factory.SearchItemsQueryDAOFactory;
import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.dao.query.factory.AllReceivedItemsFilterFactory;
import org.azrul.epice.dao.query.factory.AllSentItemsFilterFactory;
import org.azrul.epice.dao.query.factory.AllSupervisedItemsFilterFactory;
import org.azrul.epice.dao.query.factory.ReceivedItemsDeadlineOnDateFilterFactory;
import org.azrul.epice.dao.query.factory.ReceivedItemsNearDeadlineFilterFactory;
import org.azrul.epice.dao.query.factory.ReceivedItemsNeedRedoFilterFactory;
import org.azrul.epice.dao.query.factory.ReceivedItemsNotConfirmedFilterFactory;
import org.azrul.epice.dao.query.factory.ReceivedItemsNotDoneFilterFactory;
import org.azrul.epice.dao.query.factory.ReceivedItemsOverdueFilterFactory;
import org.azrul.epice.dao.query.factory.SearchItemsQueryFactory;
import org.azrul.epice.dao.query.factory.SearchItemsQueryQueryFactory;
import org.azrul.epice.dao.query.factory.SentItemsDoneUnconfirmedFilterFactory;
import org.azrul.epice.dao.query.factory.SentItemsNearDeadlineFilterFactory;
import org.azrul.epice.dao.query.factory.SentItemsNegotiatedFilterFactory;
import org.azrul.epice.dao.query.factory.SentItemsNotDoneFilterFactory;
import org.azrul.epice.dao.query.factory.SentItemsRejectedFilterFactory;
import org.azrul.epice.dao.query.factory.SupervisedItemsNearDeadlineFilterFactory;

public class PgSearch extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
        ddReceivedItemsFilterDefaultOptions.setOptions(new com.sun.webui.jsf.model.Option[]{new com.sun.webui.jsf.model.Option("ALL_RECEIVED_ITEMS", "All received items"), new com.sun.webui.jsf.model.Option("RECEIVED_ITEMS_NEED_REDO", "Received items need redo"), new com.sun.webui.jsf.model.Option("RECEIVED_ITEMS_DEADLINE_TODAY", "Received items due today"), new com.sun.webui.jsf.model.Option("RECEIVED_ITEMS_NEAR_DEADLINE", "Received items near deadline"), new com.sun.webui.jsf.model.Option("RECEIVED_ITEMS_NOT_CONFIRMED", "Received items not confirmed"), new com.sun.webui.jsf.model.Option("RECEIVED_ITEMS_NOT_DONE", "Received items not done"), new com.sun.webui.jsf.model.Option("RECEIVED_ITEMS_OVERDUE", "Received items overdue")});
        ddSentItemsFilterDefaultOptions.setOptions(new com.sun.webui.jsf.model.Option[]{new com.sun.webui.jsf.model.Option("ALL_SENT_ITEMS", "All sent items"), new com.sun.webui.jsf.model.Option("SENT_ITEMS_DONE_UNCONFIRMED", "Sent items done but unconfirmed"), new com.sun.webui.jsf.model.Option("SENT_ITEMS_NEAR_DEADLINE", "Sent items near deadline"), new com.sun.webui.jsf.model.Option("SENT_ITEMS_NEGOTIATED", "Sent items negotiated"), new com.sun.webui.jsf.model.Option("SENT_ITEMS_NOT_DONE", "Sent items not done"), new com.sun.webui.jsf.model.Option("SENT_ITEMS_REJECTED", "Sent items rejected")});
        ddSupervisedItemsFilterDefaultOptions.setOptions(new com.sun.webui.jsf.model.Option[]{new com.sun.webui.jsf.model.Option("ALL_SUPERVISED_ITEMS", "All supervised items"), new com.sun.webui.jsf.model.Option("SUPERVISED_ITEMS_NEAR_DEADLINE", "Supervised items near deadline")});
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
    private Button btnDoSearch = new Button();

    public Button getBtnDoSearch() {
        return btnDoSearch;
    }

    public void setBtnDoSearch(Button b) {
        this.btnDoSearch = b;
    }
    private Table tbSearchResults = new Table();

    public Table getTbSearchResults() {
        return tbSearchResults;
    }

    public void setTbSearchResults(Table t) {
        this.tbSearchResults = t;
    }
    private TableRowGroup trgSearchResults = new TableRowGroup();

    public TableRowGroup getTrgSearchResults() {
        return trgSearchResults;
    }

    public void setTrgSearchResults(TableRowGroup trg) {
        this.trgSearchResults = trg;
    }
    private TableColumn tableColumn4 = new TableColumn();

    public TableColumn getTableColumn4() {
        return tableColumn4;
    }

    public void setTableColumn4(TableColumn tc) {
        this.tableColumn4 = tc;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private TableColumn tableColumn5 = new TableColumn();

    public TableColumn getTableColumn5() {
        return tableColumn5;
    }

    public void setTableColumn5(TableColumn tc) {
        this.tableColumn5 = tc;
    }
    private StaticText staticText5 = new StaticText();

    public StaticText getStaticText5() {
        return staticText5;
    }

    public void setStaticText5(StaticText st) {
        this.staticText5 = st;
    }
    private TableColumn tableColumn6 = new TableColumn();

    public TableColumn getTableColumn6() {
        return tableColumn6;
    }

    public void setTableColumn6(TableColumn tc) {
        this.tableColumn6 = tc;
    }
    private StaticText staticText6 = new StaticText();

    public StaticText getStaticText6() {
        return staticText6;
    }

    public void setStaticText6(StaticText st) {
        this.staticText6 = st;
    }
    private TableColumn tableColumn7 = new TableColumn();

    public TableColumn getTableColumn7() {
        return tableColumn7;
    }

    public void setTableColumn7(TableColumn tc) {
        this.tableColumn7 = tc;
    }
    private StaticText staticText7 = new StaticText();

    public StaticText getStaticText7() {
        return staticText7;
    }

    public void setStaticText7(StaticText st) {
        this.staticText7 = st;
    }
    private TableColumn tableColumn8 = new TableColumn();

    public TableColumn getTableColumn8() {
        return tableColumn8;
    }

    public void setTableColumn8(TableColumn tc) {
        this.tableColumn8 = tc;
    }
    private Button btnMore = new Button();

    public Button getBtnMore() {
        return btnMore;
    }

    public void setBtnMore(Button b) {
        this.btnMore = b;
    }
    private TextField tfSearchTerms = new TextField();

    public TextField getTfSearchTerms() {
        return tfSearchTerms;
    }

    public void setTfSearchTerms(TextField tf) {
        this.tfSearchTerms = tf;
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
    private Button btnBack = new Button();

    public Button getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(Button b) {
        this.btnBack = b;
    }
    private Button btnSave = new Button();

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button b) {
        this.btnSave = b;
    }
    private Checkbox rbSearchInReceivedItems = new Checkbox();

    public Checkbox getRbSearchInReceivedItems() {
        return rbSearchInReceivedItems;
    }

    public void setRbSearchInReceivedItems(Checkbox c) {
        this.rbSearchInReceivedItems = c;
    }
    private Checkbox rbSearchInSentItems = new Checkbox();

    public Checkbox getRbSearchInSentItems() {
        return rbSearchInSentItems;
    }

    public void setRbSearchInSentItems(Checkbox c) {
        this.rbSearchInSentItems = c;
    }
    private Checkbox rbSearchInSupervisedItems = new Checkbox();

    public Checkbox getRbSearchInSupervisedItems() {
        return rbSearchInSupervisedItems;
    }

    public void setRbSearchInSupervisedItems(Checkbox c) {
        this.rbSearchInSupervisedItems = c;
    }
    private StaticText stTitle = new StaticText();

    public StaticText getStTitle() {
        return stTitle;
    }

    public void setStTitle(StaticText st) {
        this.stTitle = st;
    }
    private TableColumn tableColumn9 = new TableColumn();

    public TableColumn getTableColumn9() {
        return tableColumn9;
    }

    public void setTableColumn9(TableColumn tc) {
        this.tableColumn9 = tc;
    }
    private StaticText staticText8 = new StaticText();

    public StaticText getStaticText8() {
        return staticText8;
    }

    public void setStaticText8(StaticText st) {
        this.staticText8 = st;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }
    private Checkbox cbShowArchive = new Checkbox();

    public Checkbox getCbShowArchive() {
        return cbShowArchive;
    }

    public void setCbShowArchive(Checkbox c) {
        this.cbShowArchive = c;
    }
    private Button btnUnarchive = new Button();

    public Button getBtnUnarchive() {
        return btnUnarchive;
    }

    public void setBtnUnarchive(Button b) {
        this.btnUnarchive = b;
    }
    private Checkbox cbOnlyReference = new Checkbox();

    public Checkbox getCbOnlyReference() {
        return cbOnlyReference;
    }

    public void setCbOnlyReference(Checkbox c) {
        this.cbOnlyReference = c;
    }
    private StaticText stToName = new StaticText();

    public StaticText getStToName() {
        return stToName;
    }

    public void setStToName(StaticText st) {
        this.stToName = st;
    }
    private StaticText stToEmail = new StaticText();

    public StaticText getStToEmail() {
        return stToEmail;
    }

    public void setStToEmail(StaticText st) {
        this.stToEmail = st;
    }
    private StaticText stToOfficeAddress = new StaticText();

    public StaticText getStToOfficeAddress() {
        return stToOfficeAddress;
    }

    public void setStToOfficeAddress(StaticText st) {
        this.stToOfficeAddress = st;
    }
    private SingleSelectOptionsList ddReceivedItemsFilterDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdReceivedItemsFilterDefaultOptions() {
        return ddReceivedItemsFilterDefaultOptions;
    }

    public void setDdReceivedItemsFilterDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddReceivedItemsFilterDefaultOptions = ssol;
    }
    private SingleSelectOptionsList ddSentItemsFilterDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdSentItemsFilterDefaultOptions() {
        return ddSentItemsFilterDefaultOptions;
    }

    public void setDdSentItemsFilterDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddSentItemsFilterDefaultOptions = ssol;
    }
    private SingleSelectOptionsList ddSupervisedItemsFilterDefaultOptions = new SingleSelectOptionsList();

    public SingleSelectOptionsList getDdSupervisedItemsFilterDefaultOptions() {
        return ddSupervisedItemsFilterDefaultOptions;
    }

    public void setDdSupervisedItemsFilterDefaultOptions(SingleSelectOptionsList ssol) {
        this.ddSupervisedItemsFilterDefaultOptions = ssol;
    }
    private DropDown ddReceivedItemsFilter = new DropDown();

    public DropDown getDdReceivedItemsFilter() {
        return ddReceivedItemsFilter;
    }

    public void setDdReceivedItemsFilter(DropDown dd) {
        this.ddReceivedItemsFilter = dd;
    }
    private DropDown ddSupervisedItemsFilter = new DropDown();

    public DropDown getDdSupervisedItemsFilter() {
        return ddSupervisedItemsFilter;
    }

    public void setDdSupervisedItemsFilter(DropDown dd) {
        this.ddSupervisedItemsFilter = dd;
    }
    private DropDown ddSentItemsFilter = new DropDown();

    public DropDown getDdSentItemsFilter() {
        return ddSentItemsFilter;
    }

    public void setDdSentItemsFilter(DropDown dd) {
        this.ddSentItemsFilter = dd;
    }
    // </editor-fold>
    private ItemDAO itemDAO = ItemDAOFactory.getInstance();
    private SearchItemsQueryDAO searchItemsQueryDAO = SearchItemsQueryDAOFactory.getInstance();
    private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();
    private TextField tfUserInfoDepartment = new TextField();

    public TextField getTfUserInfoDepartment() {
        return tfUserInfoDepartment;
    }

    public void setTfUserInfoDepartment(TextField tf) {
        this.tfUserInfoDepartment = tf;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgSearch() {
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
            log("PgSearch Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here

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
        ddReceivedItemsFilter.setItems(getDdReceivedItemsFilterDefaultOptions().getOptions());
        ddSentItemsFilter.setItems(getDdSentItemsFilterDefaultOptions().getOptions());
        ddSupervisedItemsFilter.setItems(getDdSupervisedItemsFilterDefaultOptions().getOptions());



        //if (getSessionBean1().getSearchItemsQuery() instanceof DB4OSearchItemsQuery) {
            stTitle.setText(getSessionBean1().getSearchItemsQuery().getDescription());
            SearchItemsQuery query = getSessionBean1().getSearchItemsQuery();
            if (query != null) {

                tfSearchTerms.setText(query.getSearchTerm());
                cbShowArchive.setSelected(getSessionBean1().isArchiveShown());
                cbOnlyReference.setSelected(new Boolean(query.isReferenceOnly()));
                if (getSessionBean1().isArchiveShown()) {
                    btnUnarchive.setDisabled(false);
                } else {
                    btnUnarchive.setDisabled(true);
                }

                for (ItemsFilter filter : query.getFilters()) {
                    if (filter.getType().contains("RECEIVED")){
                        ddReceivedItemsFilter.setValue(filter.getType());
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    }else if (filter.getType().contains("SENT")){
                        ddSentItemsFilter.setValue(filter.getType());
                        rbSearchInSentItems.setValue(Boolean.TRUE);
                    }else if (filter.getType().contains("SUPERVISED")){
                        ddSupervisedItemsFilter.setValue(filter.getType());
                        rbSearchInSupervisedItems.setValue(Boolean.TRUE);
                    }
                    /*if (filter instanceof DB4OAllReceivedItemsFilter) {
                        ddReceivedItemsFilter.setValue("ALL_RECEIVED_ITEMS");
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OReceivedItemsNeedRedoFilter) {
                        ddReceivedItemsFilter.setValue("RECEIVED_ITEMS_NEED_REDO");
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OReceivedItemsDeadlineOnDateFilter) {
                        ddReceivedItemsFilter.setValue("RECEIVED_ITEMS_DEADLINE_TODAY");
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OReceivedItemsNearDeadlineFilter) {
                        ddReceivedItemsFilter.setValue("RECEIVED_ITEMS_NEAR_DEADLINE");
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OReceivedItemsNotConfirmedFilter) {
                        ddReceivedItemsFilter.setValue("RECEIVED_ITEMS_NOT_CONFIRMED");
                    } else if (filter instanceof DB4OReceivedItemsNotDoneFilter) {
                        ddReceivedItemsFilter.setValue("RECEIVED_ITEMS_NOT_DONE");
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OReceivedItemsOverdueFilter) {
                        ddReceivedItemsFilter.setValue("RECEIVED_ITEMS_OVERDUE");
                        rbSearchInReceivedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OAllSentItemsFilter) {
                        ddSentItemsFilter.setValue("ALL_SENT_ITEMS");
                        rbSearchInSentItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OSentItemsDoneUnconfirmedFilter) {
                        ddSentItemsFilter.setValue("SENT_ITEMS_DONE_UNCONFIRMED");
                        rbSearchInSentItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OSentItemsNearDeadlineFilter) {
                        ddSentItemsFilter.setValue("SENT_ITEMS_NEAR_DEADLINE");
                        rbSearchInSentItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OSentItemsNegotiatedFilter) {
                        ddSentItemsFilter.setValue("SENT_ITEMS_NEGOTIATED");
                    } else if (filter instanceof DB4OSentItemsNotDoneFilter) {
                        ddSentItemsFilter.setValue("SENT_ITEMS_NOT_DONE");
                        rbSearchInSentItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OSentItemsRejectedFilter) {
                        ddSentItemsFilter.setValue("SENT_ITEMS_REJECTED");
                        rbSearchInSentItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OAllSupervisedItemsFilter) {
                        ddSupervisedItemsFilter.setValue("ALL_SUPERVISED_ITEMS");
                        rbSearchInSupervisedItems.setValue(Boolean.TRUE);
                    } else if (filter instanceof DB4OSentItemsRejectedFilter) {
                        ddSupervisedItemsFilter.setValue("SUPERVISED_ITEMS_NEAR_DEADLINE");
                        rbSearchInSupervisedItems.setValue(Boolean.TRUE);
                    }*/
                }
            } else {
                rbSearchInReceivedItems.setValue(new Boolean(true));
                rbSearchInSentItems.setValue(new Boolean(true));
                rbSearchInSupervisedItems.setValue(new Boolean(false));
                cbShowArchive.setSelected(new Boolean(false));
                cbOnlyReference.setSelected(new Boolean(false));
                btnUnarchive.setDisabled(true);
            }
        /*} else {
            stTitle.setText("Avilable items");
            rbSearchInReceivedItems.setValue(new Boolean(true));
            rbSearchInSentItems.setValue(new Boolean(true));
            rbSearchInSupervisedItems.setValue(new Boolean(false));
            cbShowArchive.setSelected(new Boolean(false));
            cbOnlyReference.setSelected(new Boolean(false));
            btnUnarchive.setDisabled(true);
        }*/
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

    public String btnChooseItem_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return null;
    }

    public String btnGotoSentItem_action() {
        return null;
    }

    public String btnDoSearch_action() {
        ResourceBundle props = ResourceBundle.getBundle("epice");
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        if (rbSearchInReceivedItems.isChecked()) {
            if (("ALL_RECEIVED_ITEMS").equals(ddReceivedItemsFilter.getValue())) {
                filters.add(AllReceivedItemsFilterFactory.getInstance());
            } else if (("RECEIVED_ITEMS_NEED_REDO").equals(ddReceivedItemsFilter.getValue())) {
                filters.add(ReceivedItemsNeedRedoFilterFactory.getInstance());
            } else if (("RECEIVED_ITEMS_DEADLINE_TODAY").equals(ddReceivedItemsFilter.getValue())) {
                ItemsFilter q = ReceivedItemsDeadlineOnDateFilterFactory.getInstance();
                filters.add(q);
            } else if (("RECEIVED_ITEMS_NEAR_DEADLINE").equals(ddReceivedItemsFilter.getValue())) {
                ItemsFilter q = ReceivedItemsNearDeadlineFilterFactory.getInstance();
                filters.add(q);
            } else if (("RECEIVED_ITEMS_NOT_CONFIRMED").equals(ddReceivedItemsFilter.getValue())) {
                filters.add(ReceivedItemsNotConfirmedFilterFactory.getInstance());
            } else if (("RECEIVED_ITEMS_NOT_DONE").equals(ddReceivedItemsFilter.getValue())) {
                filters.add(ReceivedItemsNotDoneFilterFactory.getInstance());
            } else if (("RECEIVED_ITEMS_OVERDUE").equals(ddReceivedItemsFilter.getValue())) {
                filters.add(ReceivedItemsOverdueFilterFactory.getInstance());
            }
        }

        if (rbSearchInSentItems.isChecked()) {
            if (("ALL_SENT_ITEMS").equals(ddSentItemsFilter.getValue())) {
                filters.add(AllSentItemsFilterFactory.getInstance());
            } else if (("SENT_ITEMS_DONE_UNCONFIRMED").equals(ddSentItemsFilter.getValue())) {
                filters.add(SentItemsDoneUnconfirmedFilterFactory.getInstance());
            } else if (("SENT_ITEMS_NEAR_DEADLINE").equals(ddSentItemsFilter.getValue())) {
                filters.add(SentItemsNearDeadlineFilterFactory.getInstance());
            } else if (("SENT_ITEMS_NEGOTIATED").equals(ddSentItemsFilter.getValue())) {
                filters.add(SentItemsNegotiatedFilterFactory.getInstance());
            } else if (("SENT_ITEMS_NOT_DONE").equals(ddSentItemsFilter.getValue())) {
                filters.add(SentItemsNotDoneFilterFactory.getInstance());
            } else if (("SENT_ITEMS_REJECTED").equals(ddSentItemsFilter.getValue())) {
                filters.add(SentItemsRejectedFilterFactory.getInstance());
            }
        }

        if (rbSearchInSupervisedItems.isChecked()) {
            if (("ALL_SUPERVISED_ITEMS").equals(ddSupervisedItemsFilter.getValue())) {
                filters.add(AllSupervisedItemsFilterFactory.getInstance());
            } else if (("SUPERVISED_ITEMS_NEAR_DEADLINE").equals(ddSupervisedItemsFilter.getValue())) {
                filters.add(SupervisedItemsNearDeadlineFilterFactory.getInstance());
            }
        }

        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        query.setSearchTerm((String) tfSearchTerms.getText());
        query.setReferenceOnly(cbOnlyReference.isChecked());
        query.setArchiveIncluded(cbShowArchive.isChecked());
        query.setFilters(filters);
        getSessionBean1().setArchiveShown(cbShowArchive.isChecked());

        getSessionBean1().setSearchItemsQuery(query);
        List<Item> _searchedItems = itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQuery());
        getSessionBean1().refreshSearchItemsDP(_searchedItems);
        return null;
    }

    public void tfSearchTerm_processValueChange(ValueChangeEvent event) {
    }

    public String btnSave_action() {
        searchItemsQueryDAO.saveQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQuery());
        return null;
    }

    public String btnMore_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        RowKey rk = getTrgSearchResults().getRowKey();
        Item _searchedItem = (Item) (getSessionBean1().getSearchItemsDP().getObject(rk));
        Item searchedItem = itemDAO.refresh(_searchedItem);
        getSessionBean1().setPreviousPage("PgSearch");

        if (("SUPERVISED").equals(searchedItem.getType()) || ("SENT").equals(searchedItem.getType()) || ("SENT AND SUPERVISED").equals(searchedItem.getType()) || ("REFERENCE - SENT").equals(searchedItem.getType()) || ("REFERENCE - SUPERVISED").equals(searchedItem.getType()) || ("REFERENCE - SENT AND SUPERVISED").equals(searchedItem.getType())) {
            getSessionBean1().setCurrentSentItem(searchedItem);
            return "gotoSentItem";
        } else if (("RECEIVED").equals(searchedItem.getType()) || ("REFERENCE - RECEIVED").equals(searchedItem.getType()) || ("REFERENCE - TO YOURSELF").equals(searchedItem.getType())) {
            getSessionBean1().setCurrentReceivedItem(searchedItem);
            return "gotoReceivedItem";
        } else if (("TO YOURSELF").equals(searchedItem.getType())) {
            if (("DONE-UNCONFIRMED").equals(searchedItem.getStatus())) {
                getSessionBean1().setCurrentSentItem(searchedItem);
                return "gotoSentItem";
            } else {
                getSessionBean1().setCurrentReceivedItem(searchedItem);
                return "gotoReceivedItem";
            }
        }
        return null;
    }

    public String btnBack_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        if (("PgManageSavedSearch").equals(getSessionBean1().getPreviousPage())) {
            getSessionBean1().popPreviousPage();
            return "gotoManagedSavedSearch";
        } else {
            getSessionBean1().popPreviousPage();
            return "gotoMenu";
        }
    }

    public String btnManageSavedSearch_action() {
        getSessionBean1().setSearchItemsQueryQuery(SearchItemsQueryQueryFactory.getInstance());
        getSessionBean1().refreshSearchItemsQueryDP(searchItemsQueryDAO.runItemsQueryQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQueryQuery()));
        getSessionBean1().setPreviousPage("PgSearch");
        return "gotoManageSavedSearch";
    }

    public void cbSearchInSentItems_processValueChange(ValueChangeEvent event) {
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

    public String btnArchive_action() {
        RowKey[] selectedRowKeys = getTrgSearchResults().getSelectedRowKeys();
        List<Item> itemsToBeArchived = new ArrayList<Item>();
        for (int i = 0; i < selectedRowKeys.length; i++) {
            Item selectedItem = (Item) getSessionBean1().getSearchItemsDP().getObject(selectedRowKeys[i]);
            itemsToBeArchived.add(selectedItem);
        }
        itemDAO.archiveItems(getSessionBean1().getCurrentUser(), itemsToBeArchived);
        List<Item> searchedItems = itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQuery());
        getSessionBean1().refreshSearchItemsDP(searchedItems);
        tablePhaseListener.clear();
        return null;
    }

    public String btnUnarchive_action() {
        RowKey[] selectedRowKeys = getTrgSearchResults().getSelectedRowKeys();
        List<Item> itemsToBeArchived = new ArrayList<Item>();
        for (int i = 0; i < selectedRowKeys.length; i++) {
            Item selectedItem = (Item) getSessionBean1().getSearchItemsDP().getObject(selectedRowKeys[i]);
            itemsToBeArchived.add(selectedItem);
        }
        itemDAO.unarchiveItems(getSessionBean1().getCurrentUser(), itemsToBeArchived);
        List<Item> searchedItems = itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQuery());
        getSessionBean1().refreshSearchItemsDP(searchedItems);
        tablePhaseListener.clear();
        return null;
    }

    public boolean getShowArchiveColumn() {
        return cbShowArchive.isChecked();
    }

    public void tfSearchTerms_processValueChange(ValueChangeEvent event) {
        btnDoSearch_action();
    }
}

