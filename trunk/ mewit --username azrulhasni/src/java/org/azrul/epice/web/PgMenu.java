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
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.Hyperlink;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.PanelLayout;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.TableRowGroup;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.metacube.components.calendar.CalendarMarker;
import net.metacube.components.calendar.CalendarMarkerCollections;
import net.metacube.components.calendar.CalendarModel;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.SearchItemsQueryDAO;
import org.azrul.epice.dao.factory.SearchItemsQueryDAOFactory;
import org.azrul.epice.dao.query.DateBasedItemsFilter;
import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.dao.query.SearchItemsQuery;
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
import org.azrul.epice.util.LogUtil;
import org.azrul.epice.util.ReportUtil;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul Hasni MADISA
 */
public class PgMenu extends AbstractPageBean {
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
    private PanelLayout layoutPanel1 = new PanelLayout();

    public PanelLayout getLayoutPanel1() {
        return layoutPanel1;
    }

    public void setLayoutPanel1(PanelLayout pl) {
        this.layoutPanel1 = pl;
    }
    private Hyperlink hlUnconfirmedTasks = new Hyperlink();

    public Hyperlink getHlUnconfirmedTasks() {
        return hlUnconfirmedTasks;
    }

    public void setHlUnconfirmedTasks(Hyperlink h) {
        this.hlUnconfirmedTasks = h;
    }
    private Hyperlink hlNearDeadline = new Hyperlink();

    public Hyperlink getHlNearDeadline() {
        return hlNearDeadline;
    }

    public void setHlNearDeadline(Hyperlink h) {
        this.hlNearDeadline = h;
    }
    private Hyperlink hlPassDeadline = new Hyperlink();

    public Hyperlink getHlPassDeadline() {
        return hlPassDeadline;
    }

    public void setHlPassDeadline(Hyperlink h) {
        this.hlPassDeadline = h;
    }
    private Hyperlink hlDoneUnconfirmedTasks = new Hyperlink();

    public Hyperlink getHlDoneUnconfirmedTasks() {
        return hlDoneUnconfirmedTasks;
    }

    public void setHlDoneUnconfirmedTasks(Hyperlink h) {
        this.hlDoneUnconfirmedTasks = h;
    }
    private Hyperlink hlMonitoredTasksDeadlineNear = new Hyperlink();

    public Hyperlink getHlMonitoredTasksDeadlineNear() {
        return hlMonitoredTasksDeadlineNear;
    }

    public void setHlMonitoredTasksDeadlineNear(Hyperlink h) {
        this.hlMonitoredTasksDeadlineNear = h;
    }
    private Hyperlink hlSentTasksNearDeadline = new Hyperlink();

    public Hyperlink getHlSentTasksNearDeadline() {
        return hlSentTasksNearDeadline;
    }

    public void setHlSentTasksNearDeadline(Hyperlink h) {
        this.hlSentTasksNearDeadline = h;
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
    private Hyperlink hlNegotiatedItems = new Hyperlink();

    public Hyperlink getHlNegotiatedItems() {
        return hlNegotiatedItems;
    }

    public void setHlNegotiatedItems(Hyperlink h) {
        this.hlNegotiatedItems = h;
    }
    private Hyperlink hlRejectedItems = new Hyperlink();

    public Hyperlink getHlRejectedItems() {
        return hlRejectedItems;
    }

    public void setHlRejectedItems(Hyperlink h) {
        this.hlRejectedItems = h;
    }
    private Hyperlink hlNeedRedoItems = new Hyperlink();

    public Hyperlink getHlNeedRedoItems() {
        return hlNeedRedoItems;
    }

    public void setHlNeedRedoItems(Hyperlink h) {
        this.hlNeedRedoItems = h;
    }
    private StaticText staticText3 = new StaticText();

    public StaticText getStaticText3() {
        return staticText3;
    }

    public void setStaticText3(StaticText st) {
        this.staticText3 = st;
    }
    private Hyperlink hlDownloadAsExcel = new Hyperlink();

    public Hyperlink getHlDownloadAsExcel() {
        return hlDownloadAsExcel;
    }

    public void setHlDownloadAsExcel(Hyperlink h) {
        this.hlDownloadAsExcel = h;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }
    private StaticText staticText4 = new StaticText();

    public StaticText getStaticText4() {
        return staticText4;
    }

    public void setStaticText4(StaticText st) {
        this.staticText4 = st;
    }
    private Hyperlink hlReceivedItemsNotDone = new Hyperlink();

    public Hyperlink getHlReceivedItemsNotDone() {
        return hlReceivedItemsNotDone;
    }

    public void setHlReceivedItemsNotDone(Hyperlink h) {
        this.hlReceivedItemsNotDone = h;
    }
    private Hyperlink hlSentItemsNotDone = new Hyperlink();

    public Hyperlink getHlSentItemsNotDone() {
        return hlSentItemsNotDone;
    }

    public void setHlSentItemsNotDone(Hyperlink h) {
        this.hlSentItemsNotDone = h;
    }    // </editor-fold>

    private ItemDAO itemDAO = ItemDAOFactory.getInstance();
    private SearchItemsQueryDAO itemsQueryDAO = SearchItemsQueryDAOFactory.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat(ResourceBundle.getBundle("epice").getString("SHORT_DATE_FORMAT"));
    private TableRowGroup trgReceivedItemsDeadlineOn = new TableRowGroup();
    private String toUserFilter = "";

    public TableRowGroup getTrgReceivedItemsDeadlineOn() {
        return trgReceivedItemsDeadlineOn;
    }

    public void setTrgReceivedItemsDeadlineOn(TableRowGroup trg) {
        this.trgReceivedItemsDeadlineOn = trg;
    }
    private TableRowGroup trgSavedSearchFront = new TableRowGroup();

    public TableRowGroup getTrgSavedSearchFront() {
        return trgSavedSearchFront;
    }

    public void setTrgSavedSearchFront(TableRowGroup trg) {
        this.trgSavedSearchFront = trg;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgMenu() {
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
            log("PgMenu Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

        Person user = getSessionBean1().getCurrentUser();
        ResourceBundle props = ResourceBundle.getBundle("epice");

        ItemsFilter q1 = SentItemsDoneUnconfirmedFilterFactory.getInstance();
        hlDoneUnconfirmedTasks.setText("Tasks done but unconfirmed [" + itemDAO.runCountItems(user, q1) + "]");
        ItemsFilter  q2 = SupervisedItemsNearDeadlineFilterFactory.getInstance();
        hlMonitoredTasksDeadlineNear.setText("Monitored tasks near deadline [" + itemDAO.runCountItems(user, q2) + "]");
        ItemsFilter  q3 = ReceivedItemsNearDeadlineFilterFactory.getInstance();
        hlNearDeadline.setText("Items near deadline [" + itemDAO.runCountItems(user,  q3) + "]");
        ItemsFilter  q4 = SentItemsNegotiatedFilterFactory.getInstance();
        hlNegotiatedItems.setText("Negotiated items [" + itemDAO.runCountItems(user, q4) + "]");
        ItemsFilter  q5 = ReceivedItemsOverdueFilterFactory.getInstance();
        hlPassDeadline.setText("Items pass deadline [" + itemDAO.runCountItems(user, q5) + "]");
        ItemsFilter  q6 = SentItemsNearDeadlineFilterFactory.getInstance();
        hlSentTasksNearDeadline.setText("Sent tasks near deadlines [" + itemDAO.runCountItems(user,  q6) + "]");
        ItemsFilter  q7 = ReceivedItemsNotConfirmedFilterFactory.getInstance();
        hlUnconfirmedTasks.setText("Unconfirmed Items [" + itemDAO.runCountItems(user,  q7) + "]");
        ItemsFilter  q8 = ReceivedItemsNeedRedoFilterFactory.getInstance();
        hlNeedRedoItems.setText("Items need redo [" + itemDAO.runCountItems(user, q8) + "]");
        ItemsFilter  q9 = SentItemsRejectedFilterFactory.getInstance();
        hlRejectedItems.setText("Rejected items [" + itemDAO.runCountItems(user, q9) + "]");
        ItemsFilter  q10 = SentItemsNotDoneFilterFactory.getInstance();
        hlSentItemsNotDone.setText("Sent items - Not done [" + itemDAO.runCountItems(user, q10) + "]");
        ItemsFilter  q11 = ReceivedItemsNotDoneFilterFactory.getInstance();
        hlReceivedItemsNotDone.setText("Received items - Not done [" + itemDAO.runCountItems(user, q11) + "]");

        SearchItemsQuery searchItemsQuery = SearchItemsQueryFactory.getInstance();
        searchItemsQuery.setOwner(user);
        getSessionBean1().setSearchItemsQueryQuery(SearchItemsQueryQueryFactory.getInstance());
        getSessionBean1().refreshSearchItemsQueryDP(itemsQueryDAO.runItemsQueryQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQueryQuery()));

        //load current undone items to calendar
        CalendarMarkerCollections calMarkers = (CalendarMarkerCollections) getBean("calMarkers");
        calMarkers.reset();
        SearchItemsQuery siq = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(q11);
        siq.setFilters(filters);
        List<Item> itemsNotDone = itemDAO.runItemsQuery(user,  siq);
        Map<String, Integer> itemCounterPerDate = new HashMap<String, Integer>();
        for (Item item : itemsNotDone) {
            GregorianCalendar calDeadLine = (GregorianCalendar) GregorianCalendar.getInstance();
            calDeadLine.setTime(item.getDeadLine());

            String deadLine = sdf.format(calDeadLine.getTime());
            if (!itemCounterPerDate.containsKey(deadLine)) {
                itemCounterPerDate.put(deadLine, 1);
            } else {
                Integer in = itemCounterPerDate.get(deadLine);
                itemCounterPerDate.put(deadLine, in + 1);
            }

        }
        try {
            for (Map.Entry<String, Integer> entry : itemCounterPerDate.entrySet()) {
                CalendarMarker marker = new CalendarMarker();
                marker.setMarkerDate(sdf.parse(entry.getKey()));
                marker.setMarkerText("[" + entry.getValue() + "]");
                marker.setId(Integer.toString(entry.getKey().hashCode()));
                calMarkers.addMarker(marker);
            }
        } catch (ParseException ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
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
        //this code is in prerender so that calendar's current date can be updated before this code is executed
        DateBasedItemsFilter receivedItemsFilter = ReceivedItemsDeadlineOnDateFilterFactory.getInstance();
        Date dateOnCal = ((CalendarModel)getBean("calModel")).getDate();
        receivedItemsFilter.setOnDate(dateOnCal);
        getSessionBean1().setReceivedItemsDeadlineOnDateFilter(receivedItemsFilter);
        SearchItemsQuery siq = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(receivedItemsFilter);
        siq.setFilters(filters);
        List<Item> items = itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), siq);
        getSessionBean1().refreshReceivedItemsDeadlineOnDateDP(items);
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

    

    public String btnNewItem_action() {
        getSessionBean1().setParent(null);
        getSessionBean1().emptyPreviousPages();
        return "gotoNewItem";
    }

    public String btnLogout_action() {
        ((HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true))).invalidate();
        return "logout";
    }

    public String btnEditList_action() {
        Person user = getSessionBean1().getCurrentUser();
        Map<String, String> buddiesEmail = new HashMap<String, String>();
        for (Person buddy : user.getBuddies()) {
            buddiesEmail.put(buddy.getEmail(), buddy.getEmail() + "*");
        }
        getSessionBean1().setUserBuddiesEmail(buddiesEmail);

        Map<String, String> supervisorsEmail = new HashMap<String, String>();
        for (Person sup : user.getSupervisors()) {
            supervisorsEmail.put(sup.getEmail(), sup.getEmail() + "*");
        }
        getSessionBean1().setUserSupervisorsEmail(supervisorsEmail);
        //getSessionBean1().setPreviousPage("PgMenu");
        getSessionBean1().emptyPreviousPages();
        return "gotoManagePersonalLists";
    }

  

    public String hlUnconfirmedTasks_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(ReceivedItemsNotConfirmedFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlNearDeadline_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(ReceivedItemsNearDeadlineFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlPassDeadline_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(ReceivedItemsOverdueFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlDoneUnconfirmedTasks_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.

        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(SentItemsDoneUnconfirmedFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlMonitoredTasksDeadlineNear_action() {

        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(SupervisedItemsNearDeadlineFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlSentTasksNearDeadline_action() {

        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(SentItemsNearDeadlineFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String btnGotoSupervisedItems_action() {
    
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(AllSupervisedItemsFilterFactory.getInstance());
        query.setFilters(filters);
        return "gotoSearch";
    }

    public String hlNegotiatedItems_action() {
       SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(SentItemsNegotiatedFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String btnProfile_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        getSessionBean1().emptyPreviousPages();
        return "gotoEditProfile";
    }

    public String hlNeedRedoItems_action() {
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(ReceivedItemsNeedRedoFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlRejectedItems_action() {
       SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(SentItemsRejectedFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String btnSearch_action() {
        getSessionBean1().emptyPreviousPages();
        return "gotoSearch";
    }

    public String btnManageSavedSearch_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.

        getSessionBean1().setSearchItemsQueryQuery(SearchItemsQueryQueryFactory.getInstance());
        getSessionBean1().refreshSearchItemsQueryDP(itemsQueryDAO.runItemsQueryQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSearchItemsQueryQuery()));

        getSessionBean1().emptyPreviousPages();
        return "gotoManagedSavedSearch";
    }

    public String hlSavedSearch_action() {
//        RowKey rk = getTrgSavedSearchFront().getRowKey();
//        ItemsQuery searchedItemsQuery = (ItemsQuery) (getSessionBean1().getSearchItemsQueryDP().getObject(rk));
//        getSessionBean1().setSearchItemsQuery(searchedItemsQuery);
//        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(),cbShowArchive.isChecked(), getSessionBean1().getSearchItemsQuery()));
//
//        getSessionBean1().emptyPreviousPages();
        return "gotoSearch";
    }

    public String hlDownloadAsExcel_action() {
        Person user = getSessionBean1().getCurrentUser();
        List<Item> items = new ArrayList<Item>();

        items.addAll(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), SearchItemsQueryFactory.getInstance()));

        String csvItems = ReportUtil.itemsToCSV(items, user);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("text/csv");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        response.setHeader("Content-Disposition", "attachment; filename=items_" + sdf.format(new Date()) + ".csv");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            int c;
            for (int i = 0; i < csvItems.length(); i++) {
                c = csvItems.charAt(i);
                out.write(c);
            }
            out.flush();
        } catch (IOException ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            }
        }
        return null;
    }

    public String hlReceivedItemsNotDone_action() {
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(ReceivedItemsNotDoneFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    public String hlSentItemsNotDone_action() {
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        List<ItemsFilter> filters = new ArrayList<ItemsFilter>();
        filters.add(SentItemsNotDoneFilterFactory.getInstance());
        query.setFilters(filters);
        getSessionBean1().setSearchItemsQuery(query);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), query));
        return "gotoSearch";
    }

    protected CalendarModel getCalendarModel() {
        return (CalendarModel) getBean("calModel");
    }

    public String getChosenDateStr() {
        Date date = getCalendarModel().getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(getSessionBean1().getShortDateFormat());
        return sdf.format(date);
    }

    public String btnGotoReceivedItem_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        RowKey rk = getTrgReceivedItemsDeadlineOn().getRowKey();
        getSessionBean1().setCurrentReceivedItem((Item) (getSessionBean1().getReceivedItemsDeadlineOnDateDP().getObject(rk)));
        getSessionBean1().setPreviousPage("PgMenu");
        return "gotoReceivedItem";
    }

    public void setToUserFilter(String value) {
        int index = value.lastIndexOf(',');
        if (index >= 0) {
            getSessionBean1().getToUsersFilteredOptions().filter(getSessionBean1().getCurrentUser().getBuddies(), value.substring(index + 1).trim());
        } else {
            getSessionBean1().getToUsersFilteredOptions().filter(getSessionBean1().getCurrentUser().getBuddies(), value.trim());
        }
        toUserFilter = value;
    }

    public String getToUserFilter() {
        return toUserFilter;
    }


    public void cbShowArchive_processValueChange(ValueChangeEvent event) {
        getSessionBean1().setArchiveShown((Boolean) event.getNewValue());
    }

    public String hlSavedSearchFront_action() {
        RowKey rk = getTrgSavedSearchFront().getRowKey();
        SearchItemsQuery searchedItemsQuery = (SearchItemsQuery) (getSessionBean1().getSearchItemsQueryDP().getObject(rk));
        getSessionBean1().setSearchItemsQuery(searchedItemsQuery);
        getSessionBean1().refreshSearchItemsDP(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(),getSessionBean1().getSearchItemsQuery()));
        getSessionBean1().emptyPreviousPages();
        return null;
    }
}

