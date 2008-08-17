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
        <webuijsf:page id="page1">
            <webuijsf:html id="html1">
                <webuijsf:head id="head1">
                    <webuijsf:link id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body id="body1" style="-rave-layout: grid">
                    <webuijsf:form id="form1">
                        <webuijsf:staticText id="sent_item1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="Attachments from items"/>
                        <webuijsf:image height="48" id="image1" style="left: 384px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                        <webuijsf:textField binding="#{PgFileFromItems.tfSearchTerms}" columns="40" id="tfSearchTerms"
                            style="height: 24px; left: 120px; top: 72px; position: absolute; width: 288px" valueChangeListenerExpression="#{PgFileFromItems.tfSearchTerms_processValueChange}"/>
                        <webuijsf:button actionExpression="#{PgFileFromItems.btnDoSearch_action}" id="btnDoSearch"
                            style="height: 24px; left: 407px; top: 72px; position: absolute; width: 120px" text="Search"/>
                        <webuijsf:button actionExpression="#{PgFileFromItems.btnChoose_action}" id="btnChoose"
                            style="height: 24px; left: 527px; top: 72px; position: absolute; width: 120px" text="Choose"/>
                        <webuijsf:button actionExpression="#{PgFileFromItems.btnCancel_action}" id="btnCancel"
                            style="height: 24px; left: 647px; top: 72px; position: absolute; width: 96px" text="Cancel"/>
                        <webuijsf:table augmentTitle="false" binding="#{PgFileFromItems.tbSearchItems}" id="tbSearchItems" paginateButton="true"
                            paginationControls="true" selectMultipleButton="true" style="left: 24px; top: 144px; position: absolute; width: 0px" title="Items" width="0">
                            <webuijsf:tableRowGroup aboveColumnHeader="true" binding="#{PgFileFromItems.trgSearchItems}" id="trgSearchItems" rows="10"
                                selected="#{PgFileFromItems.selectedState}" sourceData="#{SessionBean1.searchItemsDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn id="trgSearchItemsSelectionColumn"
                                    onClick="setTimeout(function(){document.getElementById('form1:table1').initAllRows()}, 0);" selectId="trgSearchItemsSelectionChild">
                                    <webuijsf:checkbox id="trgSearchItemsSelectionChild" selected="#{PgFileFromItems.selected}" selectedValue="#{PgFileFromItems.selectedValue}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Subject" id="tableColumn1" sort="subject">
                                    <webuijsf:staticText id="staticText1" text="#{currentRow.value['subject']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="From" id="tableColumn2" sort="fromUser">
                                    <webuijsf:staticText id="staticText2" text="#{currentRow.value['fromUser'].email}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="To" id="tableColumn3" sort="toUser">
                                    <webuijsf:staticText id="staticText3" text="#{currentRow.value['toUser'].email}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Sent Date" id="tableColumn4" sort="sentDate">
                                    <webuijsf:staticText id="staticText5" text="#{currentRow.value['sentDate']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Attachments" id="tableColumn5" sort="fileRepository">
                                    <webuijsf:panelGroup id="groupPanel1">
                                        <webuijsf:table augmentTitle="false" id="table2" width="49">
                                            <webuijsf:tableRowGroup id="tableRowGroup2" rows="10"
                                                sourceData="#{currentRow.value['fileRepository'].attachmentsList}" sourceVar="currentRow2">
                                                <webuijsf:tableColumn id="tableColumn6" sort="column1" width="49">
                                                    <webuijsf:hyperlink id="staticText6" text="#{currentRow2.value['fileName']}" url="#{PgFileFromItems.url}?attId=#{currentRow2.value['id']}"/>
                                                </webuijsf:tableColumn>
                                            </webuijsf:tableRowGroup>
                                        </webuijsf:table>
                                    </webuijsf:panelGroup>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:staticText id="staticText4" style="position: absolute; left: 24px; top: 72px; width: 96px; height: 24px" text="Search terms"/>
                        <webuijsf:staticText id="staticText7" style="height: 24px; left: 24px; top: 96px; position: absolute; width: 360px" text="Put in search terms and click 'Seach'. Leave empty to get all items"/>
                        <webuijsf:checkbox binding="#{PgFileFromItems.cbReferenceOnly}" id="cbReferenceOnly" label="Search in reference only" style="position: absolute; left: 24px; top: 120px; width: 192px; height: 24px"/>
                        <webuijsf:checkbox binding="#{PgFileFromItems.cbArchived}" id="cbArchived" label="Search in archive" style="position: absolute; left: 216px; top: 120px; width: 168px; height: 24px"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
