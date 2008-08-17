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
        <webuijsf:page binding="#{PgSearch.page1}" id="page1">
            <webuijsf:html binding="#{PgSearch.html1}" id="html1">
                <webuijsf:head binding="#{PgSearch.head1}" id="head1">
                    <webuijsf:link binding="#{PgSearch.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgSearch.body1}" focus="form1:tfSearchTerms" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgSearch.form1}" id="form1">
                        <webuijsf:table augmentTitle="false" binding="#{PgSearch.tbSearchResults}" clearSortButton="true" deselectMultipleButton="true"
                            id="tbSearchResults" paginateButton="true" paginationControls="true" selectMultipleButton="true" sortPanelToggleButton="true"
                            style="height: 147px; left: 24px; top: 240px; position: absolute" title="Search results" width="1032">
                            <webuijsf:tableRowGroup binding="#{PgSearch.trgSearchResults}" id="trgSearchResults" rows="10" selected="#{PgSearch.selectedState}"
                                sourceData="#{SessionBean1.searchItemsDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn id="trgSearchResultsSelectionColumn"
                                    onClick="setTimeout(function(){document.getElementById('form1:tbSearchResults').initAllRows()}, 0);" selectId="trgSearchResultsSelectionChild">
                                    <webuijsf:checkbox id="trgSearchResultsSelectionChild" selected="#{PgSearch.selected}" selectedValue="#{PgSearch.selectedValue}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Archived" id="tableColumn10" sort="archived" visible="#{PgSearch.showArchiveColumn}">
                                    <webuijsf:staticText id="staticText10" text="#{currentRow.value['archived']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn4}" headerText="From" id="tableColumn4" sort="fromUser">
                                    <webuijsf:staticText id="stFromEmail"
                                        onMouseOver="document.getElementById('form1:bhUserInfo').open(event);&#xd;&#xa;document.getElementById('form1:bhUserInfo').setProps({style:{left:-1000,top:-1000}});&#xd;&#xa;&#xd;&#xa;var email = this.getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoEmail').setProps({value:email});&#xd;&#xa;&#xd;&#xa;var nameNodeId = this.id.replace('stFromEmail','stFromName');&#xd;&#xa;var name = document.getElementById(nameNodeId).getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoName').setProps({value:name});&#xd;&#xa;&#xd;&#xa;var officeAddressNodeId = this.id.replace('stFromEmail','stFromOfficeAddress');&#xd;&#xa;var officeAddress = document.getElementById(officeAddressNodeId).getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoOfficeAddress').setProps({value:officeAddress});&#xd;&#xa;&#xd;&#xa;var departmentNodeId = this.id.replace('stFromEmail','stFromDepartment');&#xd;&#xa;var department = document.getElementById(departmentNodeId).getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoDepartment').setProps({value:department});&#xd;&#xa;&#xd;&#xa;var url = '/EPICE/faces/PgUserInfo.jsp?email='+email+'&amp;from=PgSearch';&#xd;&#xa;document.getElementById('form1:userInfoLayout:hlUserInfoMore').setProps({href:url})&#xd;&#xa;" text="#{currentRow.value['fromUser'].email}"/>
                                    <webuijsf:staticText id="stFromName" text="#{currentRow.value['fromUser'].name}" visible="false"/>
                                    <webuijsf:staticText id="stFromOfficeAddress" text="#{currentRow.value['fromUser'].officeAddress}" visible="false"/>
                                    <webuijsf:staticText id="stFromDepartment" text="#{currentRow.value['fromUser'].department}" visible="false"/>
                                    <webuijsf:staticText id="stFromPhone" text="#{currentRow.value['fromUser'].phone}" visible="false"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn5}" headerText="To" id="tableColumn5" sort="toUser">
                                    <webuijsf:staticText binding="#{PgSearch.stToEmail}" id="stToEmail"
                                        onMouseOver="document.getElementById('form1:bhUserInfo').open(event);&#xd;&#xa;document.getElementById('form1:bhUserInfo').setProps({style:{left:-1000,top:-1000}});&#xd;&#xa;&#xd;&#xa;var email = this.getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoEmail').setProps({value:email});&#xd;&#xa;&#xd;&#xa;var nameNodeId = this.id.replace('stToEmail','stToName');&#xd;&#xa;var name = document.getElementById(nameNodeId).getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoName').setProps({value:name});&#xd;&#xa;&#xd;&#xa;var officeAddressNodeId = this.id.replace('stToEmail','stToOfficeAddress');&#xd;&#xa;var officeAddress = document.getElementById(officeAddressNodeId).getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoOfficeAddress').setProps({value:officeAddress});&#xd;&#xa;&#xd;&#xa;var departmentNodeId = this.id.replace('stToEmail','stToDepartment');&#xd;&#xa;var department = document.getElementById(departmentNodeId).getProps('value').value;&#xd;&#xa;document.getElementById('form1:userInfoLayout:tfUserInfoDepartment').setProps({value:department});&#xd;&#xa;&#xd;&#xa;var url = '/EPICE/faces/PgUserInfo.jsp?email='+email+'&amp;from=PgSearch';&#xd;&#xa;document.getElementById('form1:userInfoLayout:hlUserInfoMore').setProps({href:url})&#xd;&#xa;&#xd;&#xa;" text="#{currentRow.value['toUser'].email}"/>
                                    <webuijsf:staticText binding="#{PgSearch.stToName}" id="stToName" text="#{currentRow.value['toUser'].name}" visible="false"/>
                                    <webuijsf:staticText binding="#{PgSearch.stToOfficeAddress}" id="stToOfficeAddress"
                                        text="#{currentRow.value['toUser'].officeAddress}" visible="false"/>
                                    <webuijsf:staticText id="stToDepartment" text="#{currentRow.value['toUser'].department}" visible="false"/>
                                    <webuijsf:staticText id="stToPhone" text="#{currentRow.value['toUser'].phone}" visible="false"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn6}" headerText="Subject" id="tableColumn6" sort="subject" width="144">
                                    <webuijsf:staticText binding="#{PgSearch.staticText6}" id="staticText6" text="#{currentRow.value['subject']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn7}" headerText="Status" id="tableColumn7" sort="status">
                                    <webuijsf:staticText binding="#{PgSearch.staticText7}" id="staticText7" text="#{currentRow.value['status']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Type" id="tableColumn1" sort="type">
                                    <webuijsf:staticText id="staticText9" text="#{currentRow.value['type']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn2}" headerText="Deadline [YYYY-MM-DD HH:MM]" id="tableColumn2" sort="deadLine">
                                    <webuijsf:staticText binding="#{PgSearch.staticText2}" id="staticText2" text="#{currentRow.value['deadLine']}">
                                        <f:convertDateTime pattern="#{SessionBean1.longDateFormat}"/>
                                    </webuijsf:staticText>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn9}" headerText="Deadline Status" id="tableColumn9" sort="deadlineStatus">
                                    <webuijsf:staticText binding="#{PgSearch.staticText8}" id="staticText8" text="#{currentRow.value['deadlineStatus']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgSearch.tableColumn8}" headerText="More" id="tableColumn8">
                                    <webuijsf:button actionExpression="#{PgSearch.btnMore_action}" binding="#{PgSearch.btnMore}" id="btnMore" text="..."/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:textField binding="#{PgSearch.tfSearchTerms}" columns="60" id="tfSearchTerms"
                            style="height: 24px; left: 24px; top: 120px; position: absolute; width: 384px" tabIndex="1" valueChangeListenerExpression="#{PgSearch.tfSearchTerms_processValueChange}"/>
                        <webuijsf:button actionExpression="#{PgSearch.btnDoSearch_action}" binding="#{PgSearch.btnDoSearch}" id="btnDoSearch"
                            style="height: 24px; left: 407px; top: 120px; position: absolute; width: 72px" tabIndex="2" text="Search"/>
                        <webuijsf:button actionExpression="#{PgSearch.btnBack_action}" binding="#{PgSearch.btnBack}" id="btnBack" rendered="false"
                            style="height: 24px; left: 311px; top: 96px; position: absolute; width: 72px" text="Back"/>
                        <webuijsf:button actionExpression="#{PgSearch.btnSave_action}" binding="#{PgSearch.btnSave}" id="btnSave"
                            style="height: 24px; left: 479px; top: 120px; position: absolute; width: 96px" text="Save Search"/>
                        <webuijsf:checkbox binding="#{PgSearch.rbSearchInReceivedItems}" id="rbSearchInReceivedItems" label="Search In Received Items" style="height: 22px; left: 24px; top: 168px; position: absolute; width: 190px"/>
                        <webuijsf:checkbox binding="#{PgSearch.rbSearchInSentItems}" id="rbSearchInSentItems" label="Search In Sent Items" style="height: 24px; left: 216px; top: 168px; position: absolute; width: 166px"/>
                        <webuijsf:checkbox binding="#{PgSearch.rbSearchInSupervisedItems}" id="rbSearchInSupervisedItems" label="Search In Supervised Items" style="height: 24px; left: 384px; top: 168px; position: absolute; width: 190px"/>
                        <webuijsf:staticText binding="#{PgSearch.stTitle}" id="stTitle" style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px"/>
                        <div style="left: 24px; top: 72px; position: absolute">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:image binding="#{PgSearch.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                        <webuijsf:staticText id="staticText1" style="position: absolute; left: 24px; top: 144px; width: 360px; height: 24px" text="Put in search terms and click 'Seach'. Leave empty to get all items"/>
                        <webuijsf:button actionExpression="#{PgSearch.btnArchive_action}" id="btnArchive"
                            style="position: absolute; left: 24px; top: 216px; width: 96px; height: 24px" text="Archive"/>
                        <webuijsf:checkbox binding="#{PgSearch.cbShowArchive}" id="cbShowArchive" label="Show archived items" style="left: 744px; top: 168px; position: absolute; width: 168px"/>
                        <webuijsf:button actionExpression="#{PgSearch.btnUnarchive_action}" binding="#{PgSearch.btnUnarchive}" id="btnUnarchive"
                            style="position: absolute; left: 120px; top: 216px; width: 96px; height: 24px" text="Unarchive"/>
                        <webuijsf:checkbox binding="#{PgSearch.cbOnlyReference}" id="cbOnlyReference" label="Show reference items ONLY" style="position: absolute; left: 576px; top: 168px; width: 168px; height: 24px"/>
                        <webuijsf:bubble id="bhUserInfo" style="height: 214px; left: 144px; top: 360px; position: absolute" title="User Info." width="166">
                            <webuijsf:panelLayout id="userInfoLayout" style="height: 168px; position: relative; width: 182px; -rave-layout: grid">
                                <webuijsf:label for="tfUserInfoEmail" id="label1" text="Email"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:textField columns="25" id="tfUserInfoEmail"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:label id="label2" text="Name"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:textField columns="25" id="tfUserInfoName"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:label id="label3" text="Organization/ Address"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:textField columns="25" id="tfUserInfoOfficeAddress"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:label id="label4" text="Department"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:textField binding="#{PgSearch.tfUserInfoDepartment}" columns="25" id="tfUserInfoDepartment"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <webuijsf:hyperlink id="hlUserInfoMore" target="_self" text="MORE..."/>
                            </webuijsf:panelLayout>
                        </webuijsf:bubble>
                        <webuijsf:dropDown binding="#{PgSearch.ddReceivedItemsFilter}" id="ddReceivedItemsFilter"
                            items="#{PgSearch.ddReceivedItemsFilterDefaultOptions.options}" style="position: absolute; left: 24px; top: 192px; height: 24px" width="192"/>
                        <webuijsf:dropDown binding="#{PgSearch.ddSentItemsFilter}" id="ddSentItemsFilter"
                            items="#{PgSearch.ddSentItemsFilterDefaultOptions.options}" style="position: absolute; left: 216px; top: 192px; height: 24px" width="168"/>
                        <webuijsf:dropDown binding="#{PgSearch.ddSupervisedItemsFilter}" id="ddSupervisedItemsFilter"
                            items="#{PgSearch.ddSupervisedItemsFilterDefaultOptions.options}" style="position: absolute; left: 384px; top: 192px; height: 24px" width="192"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
