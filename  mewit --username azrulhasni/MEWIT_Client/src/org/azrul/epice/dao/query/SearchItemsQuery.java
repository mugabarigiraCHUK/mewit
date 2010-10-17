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
package org.azrul.epice.dao.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.azrul.epice.util.ItemIndexer;
//import org.compass.core.CompassException;
//import org.compass.core.CompassHits;
//import org.compass.core.CompassSession;
//import org.compass.core.CompassTransaction;
//import org.azrul.epice.util.LogUtil;
//import org.compass.core.CompassDetachedHits;

/**
 *
 * @author Azrul Hasni MADISA
 */
public class SearchItemsQuery {

    private String id = null;
    private String owner = null;
    private String searchTerm = null;
    private List<String> filters = new ArrayList<String>();
    private Map<String,Object> parameters;
    private boolean referenceOnly;
    private boolean archiveIncluded;
    private boolean useOR = true;

    public SearchItemsQuery(){
    }

    

    public String getDescription() {
        return "Search items";
    }

   

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        SearchItemsQuery q = (SearchItemsQuery) o;
        return this.getId().equals(q.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    public boolean isReferenceOnly() {
        return referenceOnly;
    }

    public void setReferenceOnly(boolean referenceOnly) {
        this.referenceOnly = referenceOnly;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> queries) {
        this.filters = queries;
    }

    public boolean isArchiveIncluded() {
        return archiveIncluded;
    }

    public void setArchiveIncluded(boolean archiveIncluded) {
        this.archiveIncluded = archiveIncluded;
    }

    /**
     * @return the useOR
     */
    public boolean getUseOR() {
        return useOR;
    }

    /**
     * @param useOR the useOR to set
     */
    public void setUseOR(boolean useOR) {
        this.useOR = useOR;
    }

    /**
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }


}
