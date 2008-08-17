<?xml version="1.0" encoding="UTF-8"?>
<!--
Copyright (C) 2008 Azrul Hasni MADISA, Abu Mansur MANAF  
    
  This program is distributed in the hope that it will be useful,  
  but WITHOUT ANY WARRANTY; without even the implied warranty of  
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
  GNU General Public License for more details.  
  
  You should have received a copy of the GNU General Public License  
  along with this program.  If not, see [http://www.gnu.org/licenses/].
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{PgTreeView.page1}" id="page1">
            <webuijsf:html binding="#{PgTreeView.html1}" id="html1">
                <webuijsf:head binding="#{PgTreeView.head1}" id="head1">
                    <webuijsf:link binding="#{PgTreeView.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgTreeView.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgTreeView.form1}" id="form1">
                        <webuijsf:tree binding="#{PgTreeView.trSupervisedItems}" clientSide="true" id="trSupervisedItems"
                            style="left: 24px; top: 168px; position: absolute" text="Items tree view"/>
                        <webuijsf:button actionExpression="#{PgTreeView.btnGotoSentItem_action}" binding="#{PgTreeView.btnGotoSentItem}" id="btnGotoSentItem"
                            style="height: 24px; left: 23px; top: 120px; position: absolute; width: 96px" text="Back"/>
                        <webuijsf:staticText binding="#{PgTreeView.sent_item1}" id="sent_item1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="Tree view of  items"/>
                        <div style="position: absolute; left: 24px; top: 72px">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:image binding="#{PgTreeView.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
