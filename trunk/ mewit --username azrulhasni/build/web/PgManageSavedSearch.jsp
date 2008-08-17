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
        <webuijsf:page binding="#{PgManageSavedSearch.page1}" id="page1">
            <webuijsf:html binding="#{PgManageSavedSearch.html1}" id="html1">
                <webuijsf:head binding="#{PgManageSavedSearch.head1}" id="head1">
                    <webuijsf:link binding="#{PgManageSavedSearch.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgManageSavedSearch.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgManageSavedSearch.form1}" id="form1">
                        <webuijsf:table augmentTitle="false" binding="#{PgManageSavedSearch.tbSaveSearch}" id="tbSaveSearch"
                            style="height: 29px; left: 24px; top: 144px; position: absolute; width: 720px" title="Saved search" width="540">
                            <webuijsf:tableRowGroup binding="#{PgManageSavedSearch.trgSavedSearch}" id="trgSavedSearch" rows="10"
                                sourceData="#{SessionBean1.searchItemsQueryDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn binding="#{PgManageSavedSearch.tableColumn4}" headerText="Search terms" id="tableColumn4" sort="searchTerm">
                                    <webuijsf:staticText binding="#{PgManageSavedSearch.staticText4}" id="staticText4" text="#{currentRow.value['searchTerm']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Archive Included" id="tableColumn3" sort="archiveIncluded">
                                    <webuijsf:staticText id="staticText1" text="#{currentRow.value['archiveIncluded']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgManageSavedSearch.tableColumn2}" headerText="Search" id="tableColumn2">
                                    <webuijsf:button actionExpression="#{PgManageSavedSearch.button2_action}" binding="#{PgManageSavedSearch.button2}"
                                        id="button2" text="Do search"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgManageSavedSearch.tableColumn1}" headerText="Delete" id="tableColumn1">
                                    <webuijsf:button actionExpression="#{PgManageSavedSearch.button1_action}" binding="#{PgManageSavedSearch.button1}"
                                        id="button1" text="Delete"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:button actionExpression="#{PgManageSavedSearch.btnBack_action}" binding="#{PgManageSavedSearch.btnBack}" id="btnBack"
                            style="height: 24px; left: 23px; top: 120px; position: absolute; width: 96px" text="Back"/>
                        <webuijsf:staticText binding="#{PgManageSavedSearch.sent_item1}" id="sent_item1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="Manage saved search"/>
                        <div style="left: 24px; top: 72px; position: absolute">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:image binding="#{PgManageSavedSearch.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute"
                            url="mew.jpg" width="360"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
