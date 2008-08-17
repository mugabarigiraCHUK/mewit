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
import com.sun.webui.jsf.component.Form;
import com.sun.webui.jsf.component.Head;
import com.sun.webui.jsf.component.Html;
import com.sun.webui.jsf.component.ImageComponent;
import com.sun.webui.jsf.component.Link;
import com.sun.webui.jsf.component.Page;
import com.sun.webui.jsf.component.StaticText;
import com.sun.webui.jsf.component.Tree;
import com.sun.webui.jsf.component.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.FacesException;
import org.azrul.epice.domain.Item;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.query.SupervisedItemsSingleRootQuery;
import org.azrul.epice.dao.query.factory.SupervisedItemsSingleRootQueryFactory;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul Hasni MADISA
 */
public class PgTreeView extends AbstractPageBean {
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
    private Tree trSupervisedItems = new Tree();

    public Tree getTrSupervisedItems() {
        return trSupervisedItems;
    }

    public void setTrSupervisedItems(Tree t) {
        this.trSupervisedItems = t;
    }
    private Button btnGotoSentItem = new Button();

    public Button getBtnGotoSentItem() {
        return btnGotoSentItem;
    }

    public void setBtnGotoSentItem(Button b) {
        this.btnGotoSentItem = b;
    }
    private StaticText sent_item1 = new StaticText();

    public StaticText getSent_item1() {
        return sent_item1;
    }

    public void setSent_item1(StaticText st) {
        this.sent_item1 = st;
    }
    private ImageComponent image1 = new ImageComponent();

    public ImageComponent getImage1() {
        return image1;
    }

    public void setImage1(ImageComponent ic) {
        this.image1 = ic;
    }

    // </editor-fold>
    ItemDAO itemDAO = ItemDAOFactory.getInstance();

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgTreeView() {
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
            log("PgTreeView Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here


    }

    private class CompareByIdLength implements Comparator {

        public int compare(Object o1, Object o2) {
            Item item1 = (Item) o1;
            Item item2 = (Item) o2;
            if (item1.getId().length() > item2.getId().length()) {
                return 1;
            } else if (item1.getId().length() < item2.getId().length()) {
                return -1;
            } else {
                return 0;
            }
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
        if (getSessionBean1().getSupervisedItemsSingleRootList().isEmpty()) {
            Item sentItem = getSessionBean1().getCurrentTreeViewItem();
            ResourceBundle props = ResourceBundle.getBundle("epice");
            SupervisedItemsSingleRootQuery query = SupervisedItemsSingleRootQueryFactory.getInstance();
            query.setRoot(sentItem);
            getSessionBean1().setSupervisedItemsSingleRootQuery(query);
            getSessionBean1().refreshSupervisedItemsSingleRootList(itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(), getSessionBean1().getSupervisedItemsSingleRootQuery()));


            //if tree is empty, rebuild it
            List<Item> supervisedItems = new ArrayList<Item>();
            supervisedItems.addAll(getSessionBean1().getSupervisedItemsSingleRootList());
            Collections.sort(supervisedItems, new CompareByIdLength());
            Map<String, TreeNode> treeNodes = new HashMap<String, TreeNode>();
            TreeNode root = new TreeNode();
            root.setId("tn_" + sentItem.getId().replace('-', '_').replace('|', '_'));
            root.setText("<table border=0><tr><td>&nbsp;&nbsp;</td><td>From:</td><td><a href='" + props.getString("EPICE_URL") + "faces/PgUserInfo.jsp?email=" + sentItem.getFromUser().getEmail() + "' >" + sentItem.getFromUser().getEmail() + "</a></td></tr><tr><td></td><td>To:</td><td><a href='" + props.getString("EPICE_URL") + "faces/PgUserInfo.jsp?email=" + sentItem.getToUser().getEmail() + "' >" + sentItem.getToUser().getEmail() + "</a></td></tr><tr><td></td><td>Subject:</td><td>" + sentItem.getSubject() + "</td></tr><tr><td></td><td>Status:</td><td>" + sentItem.getStatus() + "</td></tr><tr><td></td><td>Deadline Status:</td><td>" + sentItem.getDeadlineStatus() + "</td></tr><tr><td></td><td>More</td><td><font color=\"#0000ff\"><u><a href=\"" + props.getString("EPICE_URL") + "faces/PgSentItem.jsp?itemId=" + sentItem.getId() + "&prevPage=PgTreeView\">Click here</a></font></u> </td></tr></table>");
            trSupervisedItems.getChildren().add(root);
            treeNodes.put(root.getId(), root);
            for (Item item : supervisedItems) {
                StringBuilder sbTop = new StringBuilder();
                StringBuilder sbRest = new StringBuilder();
                //first
                sbTop.append("<td>&nbsp;&nbsp;</td>");
                sbRest.append("<td></td>");
                //middle
                for (int i = 0; i < item.getId().length(); i++) {
                    if (item.getId().charAt(i) == '|') {
                        sbTop.append("<td>&nbsp;&nbsp;</td>");
                        sbRest.append("<td></td>");
                    }
                }
                if (item.getParent() != null) {
                    TreeNode child = new TreeNode();


                    child.setId("tn_" + item.getId().replace('-', '_').replace('|', '_'));
                    child.setText("<table border=0><tr>" + sbTop.toString() + "<td>From:</td><td><a href='" + props.getString("EPICE_URL") + "faces/PgUserInfo.jsp?email=" + item.getFromUser().getEmail() + "' >" + item.getFromUser().getEmail() + "</a></td></tr><tr>" + sbRest.toString() + "<td>To:</td><td><a href='" + props.getString("EPICE_URL") + "faces/PgUserInfo.jsp?email=" + item.getToUser().getEmail() + "' >" + item.getToUser().getEmail() + "</a></td></tr><tr>" + sbRest.toString() + "<td>Subject:</td><td>" + item.getSubject() + "</td></tr><tr>" + sbRest.toString() + "<td>Status:</td><td>" + item.getStatus() + "</td></tr><tr>" + sbRest.toString() + "<td>Deadline Status:</td><td>" + item.getDeadlineStatus() + "</td></tr><tr>" + sbRest.toString() + "<td>More</td><td><font color=\"#0000ff\"><u><a href=\"" + props.getString("EPICE_URL") + "faces/PgSentItem.jsp?itemId=" + item.getId() + "&prevPage=PgTreeView\">Click here</a></u></font></td></tr></table>");
                    TreeNode parent = treeNodes.get("tn_" + item.getParent().getId().replace('-', '_').replace('|', '_'));
                    if (!treeNodes.containsKey(child.getId())) {
                        parent.getChildren().add(child);
                        treeNodes.put(child.getId(), child);
                    }
                // System.out.println("id="+item.getId() + "   parent id="+item.getParent().getId());
                } else {
                    TreeNode node = new TreeNode();

                    node.setId("tn_" + item.getId().replace('-', '_').replace('|', '_'));
                    //node.setText("<table border=1><tr><td>From:</td><td><a href='"+props.getString("EPICE_URL")+"faces/PgUserInfo.jsp?email="+item.getFromUser().getEmail()+"' >" + item.getFromUser().getEmail() + "</a></td></tr><tr><td>To:</td><td><a href='"+props.getString("EPICE_URL")+"faces/PgUserInfo.jsp?email="+item.getToUser().getEmail()+"' >" + item.getToUser().getEmail() + "</a></td></tr><tr><td>Subject:</td><td>" + item.getSubject() + "</td></tr><tr><td>Status:</td><td>" + item.getStatus() + "</td></tr><tr><td>Deadline Status:</td><td>" + item.getDeadlineStatus() + "</td></tr><tr><td>More</td><td><font color=\"#0000ff\"><u><a href=\"" + props.getString("EPICE_URL") + "facesPgSentItem.jsp?itemId=" + item.getId() + "&prevPage=PgTreeView\">Click here</a></u></font></td></tr></table>");
                    node.setText("<table border=1><tr>" + sbTop.toString() + "<td>From:</td><td><a href='" + props.getString("EPICE_URL") + "faces/PgUserInfo.jsp?email=" + item.getFromUser().getEmail() + "' >" + item.getFromUser().getEmail() + "</a></td></tr><tr>" + sbRest.toString() + "<td>To:</td><td><a href='" + props.getString("EPICE_URL") + "faces/PgUserInfo.jsp?email=" + item.getToUser().getEmail() + "' >" + item.getToUser().getEmail() + "</a></td></tr><tr>" + sbRest.toString() + "<td>Subject:</td><td>" + item.getSubject() + "</td></tr><tr>" + sbRest.toString() + "<td>Status:</td><td>" + item.getStatus() + "</td></tr><tr>" + sbRest.toString() + "<td>Deadline Status:</td><td>" + item.getDeadlineStatus() + "</td></tr><tr>" + sbRest.toString() + "<td>More</td><td><font color=\"#0000ff\"><u><a href=\"" + props.getString("EPICE_URL") + "faces/PgSentItem.jsp?itemId=" + item.getId() + "&prevPage=PgTreeView\">Click here</a></u></font></td></tr></table>");

                    if (!treeNodes.containsKey(node.getId())) {
                        trSupervisedItems.getChildren().add(node);
                        treeNodes.put(node.getId(), node);
                    }
                //  System.out.println("id="+item.getId());
                }
            }
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

    public String btnGotoSentItem_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        getSessionBean1().setCurrentSentItem(getSessionBean1().getCurrentTreeViewItem());
        return "gotoSentItem";
    }
}

