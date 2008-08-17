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
<jsp:root version="2.1" xmlns:d="urn:jsptld:WEB-INF/tld/calendar.tld" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
    xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{PgMenu.page1}" id="page1">
            <webuijsf:html binding="#{PgMenu.html1}" id="html1">
                <webuijsf:head binding="#{PgMenu.head1}" id="head1">
                    <webuijsf:link binding="#{PgMenu.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgMenu.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgMenu.form1}" id="form1">
                        <webuijsf:panelLayout binding="#{PgMenu.layoutPanel1}" id="layoutPanel1" style="background-color: rgb(204, 204, 204); height: 742px; left: 24px; top: 168px; position: absolute; width: 190px; -rave-layout: grid">
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlUnconfirmedTasks_action}" binding="#{PgMenu.hlUnconfirmedTasks}"
                                id="hlUnconfirmedTasks" style="height: 21px; left: 0px; top: 120px; position: absolute; width: 189px" tabIndex="9"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlNearDeadline_action}" binding="#{PgMenu.hlNearDeadline}" id="hlNearDeadline"
                                style="height: 21px; left: 0px; top: 144px; position: absolute; width: 189px" tabIndex="10"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlPassDeadline_action}" binding="#{PgMenu.hlPassDeadline}" id="hlPassDeadline"
                                style="height: 24px; left: 0px; top: 192px; position: absolute; width: 189px" tabIndex="12"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlDoneUnconfirmedTasks_action}" binding="#{PgMenu.hlDoneUnconfirmedTasks}"
                                id="hlDoneUnconfirmedTasks" style="height: 24px; left: 0px; top: 288px; position: absolute; width: 189px" tabIndex="13"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlMonitoredTasksDeadlineNear_action}"
                                binding="#{PgMenu.hlMonitoredTasksDeadlineNear}" id="hlMonitoredTasksDeadlineNear"
                                style="height: 24px; left: 0px; top: 312px; position: absolute; width: 189px" tabIndex="14"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlSentTasksNearDeadline_action}" binding="#{PgMenu.hlSentTasksNearDeadline}"
                                id="hlSentTasksNearDeadline" style="height: 24px; left: 0px; top: 336px; position: absolute; width: 189px" tabIndex="15"/>
                            <webuijsf:staticText binding="#{PgMenu.staticText1}" id="staticText1"
                                style="font-weight: bold; height: 24px; left: 0px; top: 240px; position: absolute; text-decoration: underline; width: 192px" text="Sent Tasks"/>
                            <webuijsf:staticText binding="#{PgMenu.staticText2}" id="staticText2"
                                style="font-weight: bold; height: 24px; left: 0px; top: 72px; position: absolute; text-decoration: underline; width: 192px" text="Received Tasks"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlNegotiatedItems_action}" binding="#{PgMenu.hlNegotiatedItems}"
                                id="hlNegotiatedItems" style="height: 24px; left: 0px; top: 360px; position: absolute; width: 192px" tabIndex="16"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlRejectedItems_action}" binding="#{PgMenu.hlRejectedItems}" id="hlRejectedItems"
                                style="height: 23px; left: 0px; top: 384px; position: absolute; width: 191px" tabIndex="17"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlNeedRedoItems_action}" binding="#{PgMenu.hlNeedRedoItems}" id="hlNeedRedoItems"
                                style="height: 24px; left: 0px; top: 168px; position: absolute; width: 192px" tabIndex="11"/>
                            <webuijsf:staticText binding="#{PgMenu.staticText3}" id="staticText3"
                                style="font-weight: bold; height: 49px; left: 0px; top: 0px; position: absolute; text-decoration: underline; width: 192px" text="Download"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlDownloadAsExcel_action}" binding="#{PgMenu.hlDownloadAsExcel}"
                                id="hlDownloadAsExcel" style="height: 24px; left: 0px; top: 24px; position: absolute; width: 189px" text="All Items as ODF file"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlReceivedItemsNotDone_action}" binding="#{PgMenu.hlReceivedItemsNotDone}"
                                id="hlReceivedItemsNotDone" style="height: 21px; left: 0px; top: 96px; position: absolute; width: 191px"/>
                            <webuijsf:hyperlink actionExpression="#{PgMenu.hlSentItemsNotDone_action}" binding="#{PgMenu.hlSentItemsNotDone}"
                                id="hlSentItemsNotDone" style="height: 23px; left: 0px; top: 264px; position: absolute; width: 189px"/>
                            <webuijsf:panelLayout id="layoutPanel2" panelLayout="flow" style="height: 141px; left: 192px; top: 0px; position: absolute; width: 261px">
                                <d:calendar actionListener="#{calModel.processCalendarEvent}"/>
                            </webuijsf:panelLayout>
                            <webuijsf:table augmentTitle="false" id="tbSavedSearchFront"
                                style="height: 28px; left: 0px; top: 432px; position: absolute; width: 191px" title="Saved search" width="191">
                                <webuijsf:tableRowGroup binding="#{PgMenu.trgSavedSearchFront}" id="trgSavedSearchFront" rows="10"
                                    sourceData="#{SessionBean1.searchItemsQueryDP}" sourceVar="currentRow">
                                    <webuijsf:tableColumn id="tableColumn4" sort="searchTerm">
                                        <webuijsf:hyperlink actionExpression="#{PgMenu.hlSavedSearchFront_action}" id="hlSavedSearchFront" text="#{currentRow.value['searchTerm']}"/>
                                    </webuijsf:tableColumn>
                                </webuijsf:tableRowGroup>
                            </webuijsf:table>
                        </webuijsf:panelLayout>
                        <div style="left: 24px; top: 72px; position: absolute">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:image binding="#{PgMenu.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                        <webuijsf:staticText binding="#{PgMenu.staticText4}" id="staticText4"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="Menu"/>
                        <webuijsf:panelLayout id="layoutPanel3" panelLayout="flow" style="height: 142px; left: 480px; top: 168px; position: absolute; width: 574px">
                            <webuijsf:table augmentTitle="false" id="tbReceivedItemsDeadlineOn" style="height: 52px"
                                title="Received items due on #{PgMenu.chosenDateStr}" width="575">
                                <webuijsf:tableRowGroup binding="#{PgMenu.trgReceivedItemsDeadlineOn}" id="trgReceivedItemsDeadlineOn" rows="10"
                                    sourceData="#{SessionBean1.receivedItemsDeadlineOnDateDP}" sourceVar="currentRow">
                                    <webuijsf:tableColumn headerText="Subject" id="tableColumn1" sort="subject">
                                        <webuijsf:staticText id="staticText5" text="#{currentRow.value['subject']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn headerText="From [email]" id="tableColumn2" sort="fromUser">
                                        <webuijsf:staticText id="staticText6" text="#{currentRow.value['fromUser'].email}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn headerText="Deadline" id="tableColumn6" sort="deadLine">
                                        <webuijsf:staticText id="staticText8" text="#{currentRow.value['deadLine']}">
                                            <f:convertDateTime pattern="#{SessionBean1.timeOnlyDateFormat}"/>
                                        </webuijsf:staticText>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn headerText="status" id="tableColumn3" sort="status">
                                        <webuijsf:staticText id="staticText7" text="#{currentRow.value['status']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn headerText="Choose" id="tableColumn5">
                                        <webuijsf:button actionExpression="#{PgMenu.btnGotoReceivedItem_action}" id="btnGotoReceivedItem" text="..."/>
                                    </webuijsf:tableColumn>
                                </webuijsf:tableRowGroup>
                            </webuijsf:table>
                        </webuijsf:panelLayout>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
