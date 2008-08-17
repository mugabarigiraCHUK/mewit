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

package org.azrul.epice.util;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;



public class InitAndSecure implements Filter {
    private FilterConfig _filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)request;
        if (httpReq.getSession(true).getAttribute("DB_UTIL")==null){
            DBUtil dbUtil = new DBUtil();
            httpReq.getSession(true).setAttribute("DB_UTIL", dbUtil);
        }
            /*
             *        

            <resource-env-ref>
            <description>
            DB4O URL
            </description>
            <resource-env-ref-name>
            epice/DB4Ourl
            </resource-env-ref-name>
            <resource-env-ref-type>
            java.lang.String
            </resource-env-ref-type>
            </resource-env-ref>

             */       
              
           /*   Context initCtx;
              String yapFile = null;
              try {
                  initCtx = new InitialContext();
                  Context envCtx = (Context) initCtx.lookup("java:comp/env");
                  yapFile = (String)envCtx.lookup("epice/DB4Ourl");
                  dbUtil.setYapFile(yapFile);
              } catch (NamingException e) {
                  //dbUtil.setYapFile("C:\\DB4O_Database\\database");
                   dbUtil.setYapFile("/export/home/mew/database");
              } finally{
                  httpReq.getSession(true).setAttribute("DB_UTIL", dbUtil);
              }
        }*/
        //if (httpReq.getPathInfo().contains("login")){
            chain.doFilter(request, response);
        /*}else{
            if ( httpReq.getSession(true).getAttribute("USER")==null){
                httpReq.getRequestDispatcher("/faces/login.jsp").forward(request, response);
            }else{
                chain.doFilter(request, response);
            }
        }*/
      
        
    }
}
