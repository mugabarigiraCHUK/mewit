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
        <webuijsf:page binding="#{PgReceivedItem.page1}" id="page1">
            <webuijsf:html binding="#{PgReceivedItem.html1}" id="html1">
                <webuijsf:head binding="#{PgReceivedItem.head1}" id="head1">
                    <webuijsf:link binding="#{PgReceivedItem.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgReceivedItem.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgReceivedItem.form1}" id="form1">
                        <webuijsf:propertySheet binding="#{PgReceivedItem.propertySheet1}" id="propertySheet1" style="height: 742px; left: 24px; top: 144px; position: absolute; width: 742px">
                            <webuijsf:propertySheetSection binding="#{PgReceivedItem.section1}" id="section1">
                                <webuijsf:property binding="#{PgReceivedItem.property1}" id="property1" label="Item ID" style="background-color: #99ff99" visible="#{PgReceivedItem.tfItemId.visible}">
                                    <webuijsf:textField binding="#{PgReceivedItem.tfItemId}" columns="40" disabled="true" id="tfItemId" style="height: 24px; width: 240px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property2}" id="property2" label="Status" style="border-style: solid; " visible="#{PgReceivedItem.tfStatus.visible}">
                                    <webuijsf:textField binding="#{PgReceivedItem.tfStatus}" columns="40" disabled="true" id="tfStatus" style="height: 24px; width: 144px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property3}" id="property3" label="From" visible="#{PgReceivedItem.tfFrom.visible}">
                                    <webuijsf:textField binding="#{PgReceivedItem.tfFrom}" columns="40" disabled="true" id="tfFrom" style="height: 24px; width: 240px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property4}" id="property4" label="To" style="background-color: #99ff99" visible="#{PgReceivedItem.lbTo.visible}">
                                    <webuijsf:listbox binding="#{PgReceivedItem.lbTo}" id="lbTo" items="#{PgReceivedItem.lbToDefaultOptions.options}" rows="3" style="height: 96px; width: 240px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property18}" id="property18" label="Current tags" style="background-color: #99ff99" visible="#{PgReceivedItem.lbTag.visible}">
                                    <h:panelGrid id="gridPanel1">
                                        <webuijsf:listbox binding="#{PgReceivedItem.lbTag}" id="lbTag" items="#{PgReceivedItem.lbTagDefaultOptions.options}"
                                            rows="3" style="height: 96px; width: 168px"/>
                                        <webuijsf:textField autoComplete="true" autoCompleteExpression="#{PgReceivedItem.tagsFilter}"
                                            binding="#{PgReceivedItem.tfAddTags}" columns="40" id="tfAddTags"/>
                                        <webuijsf:panelLayout id="layoutPanel1" panelLayout="flow" style="height: 22px; width: 100%">
                                            <webuijsf:button actionExpression="#{PgReceivedItem.btnAddTags_action}" binding="#{PgReceivedItem.btnAddTags}"
                                                id="btnAddTags" text="Add New Tags"/>
                                            <webuijsf:button actionExpression="#{PgReceivedItem.btnRemoveTag_action}" binding="#{PgReceivedItem.btnRemoveTag}"
                                                id="btnRemoveTag" text="Remove Tag"/>
                                        </webuijsf:panelLayout>
                                    </h:panelGrid>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property6}" id="property6" label="Subject" visible="#{PgReceivedItem.tfSubject.visible}">
                                    <webuijsf:textField binding="#{PgReceivedItem.tfSubject}" columns="63" disabled="true" id="tfSubject" style="height: 24px; width: 456px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property7}" id="property7" label="Action" visible="#{PgReceivedItem.taAction.visible}">
                                    <webuijsf:textArea binding="#{PgReceivedItem.taAction}" columns="60" disabled="true" id="taAction" rows="3"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property8}" id="property8" label="Sent date (YYYY-MM-DD HH:MM)" noWrap="true"
                                    style="background-color: #99ff99" visible="#{PgReceivedItem.tfSentDate.visible}">
                                    <webuijsf:textField binding="#{PgReceivedItem.tfSentDate}" converter="#{PgReceivedItem.dateTimeConverter1}" disabled="true"
                                        id="tfSentDate" style="height: 24px; width: 168px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property9}" id="property9" label="Deadline (YYYY-MM-DD HH:MM)" visible="#{PgReceivedItem.tfDeadline.visible}">
                                    <webuijsf:textField binding="#{PgReceivedItem.tfDeadline}" converter="#{PgReceivedItem.dateTimeConverter1}" disabled="true"
                                        id="tfDeadline" style="height: 24px; width: 144px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property12}" id="property12" label="Negotiated deadline " visible="#{PgReceivedItem.calNegoDeadline.visible}">
                                    <webuijsf:calendar binding="#{PgReceivedItem.calNegoDeadline}" dateFormatPattern="#{SessionBean1.shortDateFormat}"
                                        dateFormatPatternHelp="YYYY-MM-DD" id="calNegoDeadline" style="height: 24px; width: 144px"/>
                                    <webuijsf:label id="label1" text="Time (HH:MM)" visible="#{PgReceivedItem.tfNegoDeadlineTime.visible}"/>
                                    <webuijsf:textField binding="#{PgReceivedItem.tfNegoDeadlineTime}" columns="5" id="tfNegoDeadlineTime">
                                        <f:convertDateTime pattern="hh:mm"/>
                                    </webuijsf:textField>
                                    <webuijsf:radioButton binding="#{PgReceivedItem.rbNegoDeadlineAM}" id="rbNegoDeadlineAM" label="AM"
                                        name="radioButton-group-property12" valueChangeListenerExpression="#{PgReceivedItem.rbNegoDeadlineAM_processValueChange}"/>
                                    <webuijsf:radioButton binding="#{PgReceivedItem.rbNegoDeadlinePM}" id="rbNegoDeadlinePM" label="PM" name="radioButton-group-property12"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property16}" id="property16" label="Links" style="background-color: #99ff99" visible="#{PgReceivedItem.tbLinks.visible}">
                                    <webuijsf:table augmentTitle="false" binding="#{PgReceivedItem.tbLinks}" id="tbLinks" width="384">
                                        <webuijsf:tableRowGroup binding="#{PgReceivedItem.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                            sourceData="#{PgReceivedItem.linksDataProvider}" sourceVar="currentRow">
                                            <webuijsf:tableColumn binding="#{PgReceivedItem.tableColumn1}" headerText="Links" id="tableColumn1" sort="string">
                                                <webuijsf:hyperlink actionExpression="#{PgReceivedItem.hyperlink1_action}"
                                                    binding="#{PgReceivedItem.hyperlink1}" id="hyperlink1" target="_blank" text="#{currentRow.value['string']}" url="#{currentRow.value['string']}"/>
                                            </webuijsf:tableColumn>
                                        </webuijsf:tableRowGroup>
                                    </webuijsf:table>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property10}" id="property10" label="Accept / Reject" visible="#{PgReceivedItem.ddAcceptReject.visible}">
                                    <webuijsf:dropDown binding="#{PgReceivedItem.ddAcceptReject}" id="ddAcceptReject" immediate="true"
                                        items="#{PgReceivedItem.ddAcceptRejectDefaultOptions.options}"
                                        onChange="webui.suntheme.common.timeoutSubmitForm(this.form, 'ddAcceptReject');" style="height: 24px; width: 144px" valueChangeListenerExpression="#{PgReceivedItem.ddAcceptReject_processValueChange}"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property11}" id="property11" label="Reason for negotiation / rejection" visible="#{PgReceivedItem.taReasonNegoReject.visible}">
                                    <webuijsf:textArea binding="#{PgReceivedItem.taReasonNegoReject}" columns="60" id="taReasonNegoReject" rows="3" style="height: 72px; width: 384px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property13}" id="property13" label="Feedback" visible="#{PgReceivedItem.taFeedback.visible}">
                                    <webuijsf:textArea binding="#{PgReceivedItem.taFeedback}" columns="60" id="taFeedback" rows="3"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property14}" id="property14" label="Comments" visible="#{PgReceivedItem.taComments.visible}">
                                    <webuijsf:textArea binding="#{PgReceivedItem.taComments}" columns="60" disabled="true" id="taComments" rows="3"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property17}" id="property17" label="Children (delegated tasks)" visible="#{PgReceivedItem.tbChildrenItems.visible}">
                                    <webuijsf:table augmentTitle="false" binding="#{PgReceivedItem.tbChildrenItems}" clearSortButton="true" id="tbChildrenItems"
                                        paginateButton="true" paginationControls="true" sortPanelToggleButton="true" style="height: 47px"
                                            width="504">C
                                        <webuijsf:tableRowGroup
                                            binding="#{PgReceivedItem.trgChildrenItems}" id="trgChildrenItems" rows="10"
                                            sourceData="#{PgReceivedItem.childrenItemsDP}" sourceVar="currentRow">
                                            <webuijsf:tableColumn headerText="Subject" id="tableColumn7" sort="subject">
                                                <webuijsf:staticText id="staticText7" text="#{currentRow.value['subject']}"/>
                                            </webuijsf:tableColumn>
                                            <webuijsf:tableColumn headerText="Status" id="tableColumn8" sort="status">
                                                <webuijsf:staticText id="staticText8" text="#{currentRow.value['status']}"/>
                                            </webuijsf:tableColumn>
                                            <webuijsf:tableColumn headerText="To [email]" id="tableColumn9" sort="toUser">
                                                <webuijsf:staticText id="staticText9" text="#{currentRow.value['toUser'].email}"/>
                                            </webuijsf:tableColumn>
                                            <webuijsf:tableColumn headerText="More" id="tableColumn10">
                                                <webuijsf:button actionExpression="#{PgReceivedItem.btnChooseChildItem_action}" id="button1" text="..."/>
                                            </webuijsf:tableColumn>
                                        </webuijsf:tableRowGroup>
                                    </webuijsf:table>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgReceivedItem.property15}" id="property15">
                                    <webuijsf:button actionExpression="#{PgReceivedItem.btnBack_action}" binding="#{PgReceivedItem.btnBack}" id="btnBack"
                                        style="height: 24px; width: 96px" text="Back"/>
                                    <webuijsf:button actionExpression="#{PgReceivedItem.btnSubmit_action}" binding="#{PgReceivedItem.btnSubmit}" id="btnSubmit" style="height: 24px; width: 95px"/>
                                    <webuijsf:button actionExpression="#{PgReceivedItem.btnDelegate_action}" binding="#{PgReceivedItem.btnDelegate}"
                                        id="btnDelegate" style="height: 24px; width: 96px" text="Delegate"/>
                                </webuijsf:property>
                            </webuijsf:propertySheetSection>
                        </webuijsf:propertySheet>
                        <webuijsf:staticText binding="#{PgReceivedItem.staticText1}" id="staticText1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="Received item"/>
                        <div style="position: absolute; left: 24px; top: 72px">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                            <webuijsf:checkbox binding="#{PgReceivedItem.cbAdvanceView}" id="cbAdvanceView"
                                label="Advance view (Item ID, Recipients, Tags, Sent date, Links)"
                                onClick="woodstock4_3.common.timeoutSubmitForm(this.form, 'cbAdvanceView');" style="" valueChangeListenerExpression="#{PgReceivedItem.cbAdvanceView_processValueChange}"/>
                        </div>
                        <webuijsf:image binding="#{PgReceivedItem.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute"
                            url="mew.jpg" width="360"/>
                        <webuijsf:table augmentTitle="false" id="tbAttachments" style="height: 29px; left: 792px; top: 216px; position: absolute; width: 264px"
                            title="Attachments" width="264">
                            <webuijsf:tableRowGroup binding="#{PgReceivedItem.trgAttachments}" id="trgAttachments" rows="10"
                                sourceData="#{SessionBean1.fileRepositoryDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn headerText="File" height="22" id="tableColumn4" sort="fileName">
                                    <webuijsf:hyperlink actionExpression="#{PgReceivedItem.hyperlink2_action}" id="hyperlink2" text="#{currentRow.value['fileName']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Uploaded By" id="tableColumn5" sort="owner">
                                    <webuijsf:staticText id="staticText4" text="#{currentRow.value['owner'].email}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:panelLayout id="layoutPanel2" panelLayout="flow" style="height: 22px; left: 792px; top: 168px; position: absolute; width: 262px">
                            <webuijsf:button actionExpression="#{PgReceivedItem.btnGotoAttachment_action}" binding="#{PgReceivedItem.btnGotoAttachment}"
                                id="btnGotoAttachment" style="height: 24px" text="Manage Attachments"/>
                        </webuijsf:panelLayout>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
