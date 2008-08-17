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
import com.sun.webui.jsf.component.Checkbox;
import com.sun.webui.jsf.component.Table;
import com.sun.webui.jsf.component.TableRowGroup;
import com.sun.webui.jsf.component.TextField;
import com.sun.webui.jsf.event.TableSelectPhaseListener;
import com.sun.webui.jsf.model.DefaultTableDataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.azrul.epice.dao.AttachmentDAO;
import org.azrul.epice.dao.factory.AttachmentDAOFactory;
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.dao.query.factory.SearchItemsQueryFactory;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author Azrul
 */
public class PgFileFromItems extends AbstractPageBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    private TextField tfSearchTerms = new TextField();

    public TextField getTfSearchTerms() {
        return tfSearchTerms;
    }

    public void setTfSearchTerms(TextField tf) {
        this.tfSearchTerms = tf;
    }
    private DefaultTableDataProvider defaultTableDataProvider = new DefaultTableDataProvider();

    public DefaultTableDataProvider getDefaultTableDataProvider() {
        return defaultTableDataProvider;
    }

    public void setDefaultTableDataProvider(DefaultTableDataProvider dtdp) {
        this.defaultTableDataProvider = dtdp;
    }

    // </editor-fold>
    private TableSelectPhaseListener tablePhaseListener = new TableSelectPhaseListener();
    private ItemDAO itemDAO = ItemDAOFactory.getInstance();
    private AttachmentDAO attachmentDAO = AttachmentDAOFactory.getInstance();
    private Table tbSearchItems = new Table();

    public Table getTbSearchItems() {
        return tbSearchItems;
    }

    public void setTbSearchItems(Table t) {
        this.tbSearchItems = t;
    }
    private TableRowGroup trgSearchItems = new TableRowGroup();

    public TableRowGroup getTrgSearchItems() {
        return trgSearchItems;
    }

    public void setTrgSearchItems(TableRowGroup trg) {
        this.trgSearchItems = trg;
    }
    private Checkbox cbReferenceOnly = new Checkbox();

    public Checkbox getCbReferenceOnly() {
        return cbReferenceOnly;
    }

    public void setCbReferenceOnly(Checkbox c) {
        this.cbReferenceOnly = c;
    }
    private Checkbox cbArchived = new Checkbox();

    public Checkbox getCbArchived() {
        return cbArchived;
    }

    public void setCbArchived(Checkbox c) {
        this.cbArchived = c;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public PgFileFromItems() {
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
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String attId = req.getParameter("attId");
        if (attId != null) {
            try {
                FileInputStream fileToDownload = null;
                ServletOutputStream out = null;
                Attachment attachment = attachmentDAO.findAttachmentById(attId);
                File file = new File(attachment.getFilePath());
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
            } catch (IOException ex) {
                Logger.getLogger(PgFileFromItems.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("PgFileFromItems Initialization Failure", e);
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
    
    public String getUrl(){
        ResourceBundle props = ResourceBundle.getBundle("epice");
        return props.getString("EPICE_URL")+"faces/PgFileFromItems.jsp";
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected RequestBean1 getRequestBean1() {
        return (RequestBean1) getBean("RequestBean1");
    }

    public String btnDoSearch_action() {
        SearchItemsQuery query = SearchItemsQueryFactory.getInstance();
        query.setSearchTerm((String) tfSearchTerms.getText());
        query.setReferenceOnly(cbReferenceOnly.isChecked());

        getSessionBean1().setSearchItemsQuery(query);
        List<Item> searchResults = itemDAO.runItemsQuery(getSessionBean1().getCurrentUser(),getSessionBean1().getSearchItemsQuery());
        getSessionBean1().refreshSearchItemsDP(itemDAO.refreshAll(searchResults));
        return null;
    }

    public String btnChoose_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        RowKey[] selectedRowKeys = getTrgSearchItems().getSelectedRowKeys();
        List<Attachment> attachments = new ArrayList<Attachment>();
        for (int i = 0; i < selectedRowKeys.length; i++) {
            Item selectedItem = (Item) getSessionBean1().getSearchItemsDP().getObject(selectedRowKeys[i]);
            attachments.addAll(selectedItem.getFileRepository().getAttachments());
        }
        FileRepositoryDAO fileRepoDAO = FileRepositoryDAOFactory.getInstance();
        FileRepository fileRepo = fileRepoDAO.addNewAttachments(getSessionBean1().getCurrentFileRepository(), attachments);
        getSessionBean1().setCurrentFileRepository(fileRepo);
        getSessionBean1().getCurrentFileRepository().refreshDataProvider(getSessionBean1().getCurrentUser());
        //getSessionBean1().setChosenItemsForAttachments(items);
        tablePhaseListener.clear();

        return "gotoFileRepository";
    }

    public String btnCancel_action() {
        // TODO: Process the action. Return value is a navigation
        // case name where null will return to the same page.
        return "gotoFileRepository";
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

    public void tfSearchTerms_processValueChange(ValueChangeEvent event) {
        btnDoSearch_action();
    }
}

