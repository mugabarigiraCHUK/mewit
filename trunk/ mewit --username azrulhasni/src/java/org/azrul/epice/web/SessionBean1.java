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

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import com.sun.webui.jsf.model.Option;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import javax.faces.FacesException;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.dao.query.ItemsFilter;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.dao.query.SearchItemsQueryQuery;
import org.azrul.epice.dao.query.UserLinksQuery;
import org.azrul.epice.dao.query.ChildrenItemsQuery;
import org.azrul.epice.dao.query.SupervisedItemsSingleRootQuery;
import org.azrul.epice.dao.query.factory.SearchItemsQueryFactory;
import org.azrul.epice.domain.UserLink;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 *
 * @author Azrul
 */
public class SessionBean1 extends AbstractSessionBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>
    private ItemDataProvider receivedItemsDP = new ItemDataProvider();
    private ItemDataProvider sentItemsDP = new ItemDataProvider();
    private ItemDataProvider supervisedItemsDP = new ItemDataProvider();
    private ItemDataProvider searchItemsDP = new ItemDataProvider();
    private AttachmentDataProvider searchAttachmentDP = new AttachmentDataProvider();
    private ItemDataProvider childrenItemsDP = new ItemDataProvider();
    private ItemDataProvider receivedItemsDeadlineOnDateDP = new ItemDataProvider();
    private Set<Item> supervisedItemsSingleRootList = new HashSet<Item>();
    private SearchItemsQueryDataProvider searchItemsQueryDP = new SearchItemsQueryDataProvider();
    private Map<String, String> userBuddiesEmail = new HashMap<String, String>();
    private Map<String, String> userSupervisorsEmail = new HashMap<String, String>();
    private Set<URL> links = new HashSet<URL>();
    private SupervisedItemsSingleRootQuery supervisedItemsSingleRootQuery = null;
    private SearchItemsQuery searchItemsQuery = null;
    private ChildrenItemsQuery childrenItemsQuery = null;
    private ItemsFilter receivedItemsDeadlineOnDateQuery = null;
    private SearchItemsQueryQuery searchItemsQueryQuery = null;
    private Item parent = null;
    private Stack<String> previousPages = new Stack<String>();
    private Person currentUser = null;
    private Item currentSentItem = null;
    private Item currentReceivedItem = null;
    private Item currentTreeViewItem = null;
    private Item currentEditedItem = null;
    private FileRepository currentFileRepository = null;
    private boolean enableUpload;
    private Item quickAccessItem;
    private Item tempSentItem;
    private Item tempReceivedItem;
    private FilteredOptions toUsersFilteredOptions;
    private FilteredOptions supervisorsFilteredOptions;
    private boolean archiveShown;
    
    private UserLinksQuery userLinksQuery = null;
    private UserLinkDataProvider userLinksDP = new UserLinkDataProvider();
    private List<UserLink> userLinks = new ArrayList<UserLink>();
    
    private Person inspectedUser = null;
    
    private Set<Item> chosenItemsForAttachments = new HashSet<Item>();
    
    private String itemType = null;
    
    private List<String> buddiesEmail = new ArrayList<String>();

    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean1() {
    }

    /**
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     * 
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
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
            log("SessionBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here
    }

    /**
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     * 
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    @Override
    public void passivate() {
    }

    /**
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     * 
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    @Override
    public void activate() {
    }

    /**
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
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


    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public String getPreviousPage() {
        if (previousPages.isEmpty()) {
            return "";
        } else {
            return previousPages.peek();
        }
    }

    public void popPreviousPage() {
        if (!previousPages.isEmpty()) {
            previousPages.pop();
        }
    }

    public void emptyPreviousPages() {
        previousPages.removeAllElements();
    }

    public void setPreviousPage(String previousPage) {
        this.previousPages.push(previousPage);
    }

//    public ItemsQuery getReceivedItemsQuery() {
//        return receivedItemsQuery;
//    }
//
//    public void setReceivedItemsQuery(ItemsQuery receivedItemsQuery) {
//        this.receivedItemsQuery = receivedItemsQuery;
//    }
//
//    public ItemsQuery getSentItemsQuery() {
//        return sentItemsQuery;
//    }
//
//    public void setSentItemsQuery(ItemsQuery sentItemsQuery) {
//        this.sentItemsQuery = sentItemsQuery;
//    }
//
//    public ItemsQuery getSupervisedItemsQuery() {
//        return supervisedItemsQuery;
//    }
//
//    public void setSupervisedItemsQuery(ItemsQuery supervisedItemsQuery) {
//        this.supervisedItemsQuery = supervisedItemsQuery;
//    }

//    public void refreshSupervisedItemsList(List<Item> items) {//throws Exception {
//
//        if (getSupervisedItemsQuery() != null) {
//            getSupervisedItemsList().clear();
//            getSupervisedItemsList().addAll(items);//getSupervisedItemsQuery().run(getCurrentUser(), db));
//
//        }
//    }
//
//    public void emptySupervisedItemsDP() {
//        supervisedItemsDP.setList(new ArrayList<Item>());
//    }
//
//    public Set<Item> getSupervisedItemsList() {
//        if (supervisedItemsList == null) {
//            setSupervisedItemsList(new HashSet<Item>());
//        }
//        return supervisedItemsList;
//    }
//
//    public void setSupervisedItemsList(Set<Item> supervisedItemsList) {
//        this.supervisedItemsList = supervisedItemsList;
//    }
//
//    public ItemDataProvider getSupervisedItemsDP() {
//        return supervisedItemsDP;
//    }
//
//    public void setSupervisedItemsDP(ItemDataProvider supervisedItemsDP) {
//        this.supervisedItemsDP = supervisedItemsDP;
//    }
//
//    public void refreshSupervisedItemsDP(List<Item> items) {//throws Exception {
//
//        if (getSupervisedItemsQuery() != null) {
//            this.supervisedItemsDP.setList(items);//new ArrayList<Item>(getSupervisedItemsQuery().run(getCurrentUser(), db)));
//
//        }
//    }

    public Person getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Person currentUser) {
        this.currentUser = currentUser;
    }

    public Item getCurrentSentItem() {
        return currentSentItem;
    }

    public void setCurrentSentItem(Item currentSentItem) {
        this.currentSentItem = currentSentItem;
    }

    public Item getCurrentReceivedItem() {
        return currentReceivedItem;
    }

    public void setCurrentReceivedItem(Item currentReceivedItem) {
        this.currentReceivedItem = currentReceivedItem;
    }

    public Set<URL> getLinks() {
        return links;
    }

    public void setLinks(Set<URL> links) {
        this.links = links;
    }

    public ItemDataProvider getSearchItemsDP() {
        return searchItemsDP;
    }

    public void setSearchItemsDP(ItemDataProvider searchItemsDP) {
        this.searchItemsDP = searchItemsDP;
    }

    public void emptySearchItemsDP() {
        searchItemsDP.setList(new ArrayList<Item>());
    }

    public void refreshSearchItemsDP(List<Item> items) {// throws Exception {

        //if (getSearchItemsQuery() != null) {
            searchItemsDP.setList(items);//new ArrayList<Item>(getSearchItemsQuery().run(getCurrentUser(), db)));

        //}
    }

    public SearchItemsQuery getSearchItemsQuery() {
        if (searchItemsQuery==null){
            return getSpecificSearchItemsQuery();
        }
        return searchItemsQuery;
    }

    public SearchItemsQuery getSpecificSearchItemsQuery() {
       if (searchItemsQuery != null) {
            return  searchItemsQuery;
        } else {
            return SearchItemsQueryFactory.getInstance();
        }
    }

    public void setSpecificSearchItemsQuery(SearchItemsQuery searchItemsQuery) {
        this.searchItemsQuery = searchItemsQuery;
    }

    public void setSearchItemsQuery(SearchItemsQuery searchItemsQuery) {
        this.searchItemsQuery = searchItemsQuery;
    }

    public SearchItemsQueryDataProvider getSearchItemsQueryDP() {
        return searchItemsQueryDP;
    }

    public void setSearchItemsQueryDP(SearchItemsQueryDataProvider searchItemsQueryDP) {
        this.searchItemsQueryDP = searchItemsQueryDP;
    }

    public void emptySearchItemsQueryDP() {
        searchItemsDP.setList(new ArrayList<Item>());
    }

    public void refreshSearchItemsQueryDP(List<SearchItemsQuery> items) {//throws Exception {
            searchItemsQueryDP.setList(items);//new ArrayList<ItemsQuery>(getSearchItemsQueryQuery().run(getCurrentUser(), db)));
    }

    public SearchItemsQueryQuery getSearchItemsQueryQuery() {
        return searchItemsQueryQuery;
    }

    public void setSearchItemsQueryQuery(SearchItemsQueryQuery searchItemsQueryQuery) {
        this.searchItemsQueryQuery = searchItemsQueryQuery;
    }

    public ItemDataProvider getChildrenItemsDP() {
        return childrenItemsDP;
    }

    public void setChildrenItemsDP(ItemDataProvider childrenItemsDP) {
        this.childrenItemsDP = childrenItemsDP;
    }

    public void emptyChildrenItemsDP() {
        childrenItemsDP.setList(new ArrayList<Item>());
    }

    public void refreshChildrenItemsDP(List<Item> items) {// throws Exception {
        if (items == null){
            return;
        }
        if (items.isEmpty()){
            return;
        }
        if (items.get(0)==null){
            return;
        }
        //if (getChildrenItemsQuery() != null) {
            childrenItemsDP.setList(items);//new ArrayList<Item>(getChildrenItemsQuery().run(getCurrentUser(), db)));

        //}
    }

    public ChildrenItemsQuery getChildrenItemsQuery() {
        return childrenItemsQuery;
    }

    public void setChildrenItemsQuery(ChildrenItemsQuery childrenItemsQuery) {
        this.childrenItemsQuery = childrenItemsQuery;
    }

    public Set<Item> getSupervisedItemsSingleRootList() {
        if (supervisedItemsSingleRootList == null) {
            setSupervisedItemsSingleRootList(new HashSet<Item>());
        }
        return supervisedItemsSingleRootList;
    }

    public void setSupervisedItemsSingleRootList(Set<Item> supervisedItemsSingleRootList) {
        this.supervisedItemsSingleRootList = supervisedItemsSingleRootList;
    }

    public void refreshSupervisedItemsSingleRootList(List<Item> items) {// throws Exception {

       // if (getSupervisedItemsSingleRootQuery() != null) {
            getSupervisedItemsSingleRootList().clear();
            getSupervisedItemsSingleRootList().addAll(items);//getSupervisedItemsSingleRootQuery().run(getCurrentUser(), db));

      //  }
    }

    public SupervisedItemsSingleRootQuery getSupervisedItemsSingleRootQuery() {
        return supervisedItemsSingleRootQuery;
    }

    public void setSupervisedItemsSingleRootQuery(SupervisedItemsSingleRootQuery supervisedItemsSingleRootQuery) {
        this.supervisedItemsSingleRootQuery = supervisedItemsSingleRootQuery;
    }

    public Item getCurrentTreeViewItem() {
        return currentTreeViewItem;
    }

    public void setCurrentTreeViewItem(Item currentTreeViewItem) {
        this.currentTreeViewItem = currentTreeViewItem;
    }

    public Map<String, String> getUserBuddiesEmail() {
        return userBuddiesEmail;
    }

    public void setUserBuddiesEmail(Map<String, String> userBuddiesEmail) {
        this.userBuddiesEmail = userBuddiesEmail;
    }

    public Map<String, String> getUserSupervisorsEmail() {
        return userSupervisorsEmail;
    }

    public void setUserSupervisorsEmail(Map<String, String> userSupervisorsEmail) {
        this.userSupervisorsEmail = userSupervisorsEmail;
    }

    public Item getCurrentEditedItem() {
        return currentEditedItem;
    }

    public void setCurrentEditedItem(Item currentEditedItem) {
        this.currentEditedItem = currentEditedItem;
    }

    public FileRepository getCurrentFileRepository() {
        return currentFileRepository;
    }

    public void setCurrentFileRepository(FileRepository currentFileRepository) {
        this.currentFileRepository = currentFileRepository;
    }

    public AttachmentDataProvider getFileRepositoryDP() {
        return currentFileRepository.dataProvider();
    }

    public boolean getEnableUpload() {
        return enableUpload;
    }

    public void setEnableUpload(boolean enableUpload) {
        this.enableUpload = enableUpload;
    }

    public Item getQuickAccessItem() {
        return quickAccessItem;
    }

    public void setQuickAccessItem(Item quickAccessItem) {
        this.quickAccessItem = quickAccessItem;
    }

    public Item getTempSentItem() {
        return tempSentItem;
    }

    public void setTempSentItem(Item tempSentItem) {
        this.tempSentItem = tempSentItem;
    }

    public Item getTempReceivedItem() {
        return tempReceivedItem;
    }

    public void setTempReceivedItem(Item tempReceivedItem) {
        this.tempReceivedItem = tempReceivedItem;
    }

    public FilteredOptions getToUsersFilteredOptions() {
        return toUsersFilteredOptions;
    }

    public void setToUsersFilteredOptions(FilteredOptions toUsersFilteredOptions) {
        this.toUsersFilteredOptions = toUsersFilteredOptions;
    }

    public FilteredOptions getSupervisorsFilteredOptions() {
        return supervisorsFilteredOptions;
    }

    public void setSupervisorsFilteredOptions(FilteredOptions supervisorsFilteredOptions) {
        this.supervisorsFilteredOptions = supervisorsFilteredOptions;
    }

    public Option[] getLinkOptions() {
        Option[] linkOptions = new Option[links.size()];
        int i = 0;
        for (URL link : links) {
            linkOptions[i] = new Option(link.toString(), link.toString());
            i++;
        }
        return linkOptions;
    }

    public void setLinkOptions(Option[] linkOptions) {
    }

    public String getLongDateFormat() {
        return ResourceBundle.getBundle("epice").getString("LONG_DATE_FORMAT");
    }

    public void setLongDateFormat(String format) {
    }

    public String getShortDateFormat() {
        return ResourceBundle.getBundle("epice").getString("SHORT_DATE_FORMAT");
    }
    
    public void setShortDateFormat(String format) {
    }
    
    public String getTimeOnlyDateFormat() {
        return ResourceBundle.getBundle("epice").getString("TIME_ONLY_DATE_FORMAT");
    }

    public void setTimeOnlyDateFormat(String format) {
    }
    
    public String getHMOnlyDateFormat() {
        return ResourceBundle.getBundle("epice").getString("HM_ONLY_DATE_FORMAT");
    }

    public void setHMOnlyDateFormat(String format) {
    }

    public ItemsFilter getReceivedItemsDeadlineOnDateFilter() {
        return receivedItemsDeadlineOnDateQuery;
    }

    public void setReceivedItemsDeadlineOnDateFilter(ItemsFilter receivedItemsDeadlineOnDate) {
        this.receivedItemsDeadlineOnDateQuery = receivedItemsDeadlineOnDate;
    }

    public ItemDataProvider getReceivedItemsDeadlineOnDateDP() {
        return receivedItemsDeadlineOnDateDP;
    }

    public void setReceivedItemsDeadlineOnDateDP(ItemDataProvider receivedItemsDeadlineOnDateDP) {
        this.receivedItemsDeadlineOnDateDP = receivedItemsDeadlineOnDateDP;
    }
    
    public void refreshReceivedItemsDeadlineOnDateDP(List<Item> items) {
        //if (getReceivedItemsDeadlineOnDateQuery() != null) {
           getReceivedItemsDeadlineOnDateDP().setList(items);
        //}
    }

    
    public UserLinkDataProvider getUserLinksDP() {
        return userLinksDP;
    }

    public void setUserLinksDP(UserLinkDataProvider userLinksDP) {
        this.userLinksDP =userLinksDP;
    }

    public void emptyUserLinksDPDP() {
        userLinksDP.setList(new ArrayList<Item>());
    }

    public void refreshUserLinksDP(List<UserLink> links) {// throws Exception {
       // if (getUserLinksQuery() != null) {
            userLinksDP.setList(links);//new ArrayList<Item>(getSearchItemsQuery().run(getCurrentUser(), db)));
       // }
    }
   

    public UserLinksQuery getUserLinksQuery() {
        return userLinksQuery;
    }

    public void setUserLinksQuery(UserLinksQuery userLinksQuery) {
        this.userLinksQuery = userLinksQuery;
    }

    public List<UserLink> getUserLinks() { 
        if (userLinks == null){
            userLinks = new ArrayList<UserLink>();
        }
        return userLinks;
    }

    public void setUserLinks(List<UserLink> userLinks) {
        this.userLinks = userLinks;
    }

    public Person getInspectedUser() {
        return inspectedUser;
    }

    public void setInspectedUser(Person inspectedUser) {
        this.inspectedUser = inspectedUser;
    }

    public AttachmentDataProvider getSearchAttachmentDP() {
        return searchAttachmentDP;
    }

    public void setSearchAttachmentDP(AttachmentDataProvider searchAttachmentDP) {
        this.searchAttachmentDP = searchAttachmentDP;
    }

    public Set<Item> getChosenItemsForAttachments() {
        return chosenItemsForAttachments;
    }

    public void setChosenItemsForAttachments(Set<Item> chosenItemsForAttachments) {
        this.chosenItemsForAttachments = chosenItemsForAttachments;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public List<String> getBuddiesEmail() {
        return buddiesEmail;
    }

    public void setBuddiesEmail(List<String> buddiesEmail) {
        this.buddiesEmail = buddiesEmail;
    }

    public boolean isArchiveShown() {
        return archiveShown;
    }

    public void setArchiveShown(boolean archiveShown) {
        this.archiveShown = archiveShown;
    }
}  
