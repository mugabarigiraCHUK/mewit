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
        <webuijsf:page binding="#{PgSentItem.page1}" id="page1">
            <webuijsf:html binding="#{PgSentItem.html1}" id="html1">
                <webuijsf:head binding="#{PgSentItem.head1}" id="head1">
                    <webuijsf:link binding="#{PgSentItem.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgSentItem.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgSentItem.form1}" id="form1">
                        <webuijsf:staticText binding="#{PgSentItem.stTitle}" id="stTitle" style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px"/>
                        <div style="position: absolute; left: 24px; top: 72px">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                            <webuijsf:checkbox binding="#{PgSentItem.cbAdvanceView}" id="cbAdvanceView"
                                label="Advance View (Item ID, Sender, Tags, Sent date, Links)"
                                onClick="woodstock4_3.common.timeoutSubmitForm(this.form, 'cbAdvanceView');" valueChangeListenerExpression="#{PgSentItem.cbAdvanceView_processValueChange}"/>
                        </div>
                        <webuijsf:image binding="#{PgSentItem.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                        <webuijsf:propertySheet binding="#{PgSentItem.propertySheet1}" id="propertySheet1" style="height: 670px; left: 24px; top: 144px; position: absolute; width: 718px">
                            <webuijsf:propertySheetSection binding="#{PgSentItem.properties}" id="properties" style="height: 643px; width: 719px">
                                <webuijsf:property binding="#{PgSentItem.property1}" id="property1" label="Item Id" style="background-color: #99ff99" visible="#{PgSentItem.tfItemId.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfItemId}" columns="80" disabled="true" id="tfItemId" style="height: 24px; width: 240px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property2}" id="property2" label="From" style="background-color: #99ff99" visible="#{PgSentItem.tfFrom.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfFrom}" columns="80" disabled="true" id="tfFrom" style="height: 24px; width: 240px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property3}" id="property3" label="Status" visible="#{PgSentItem.tfStatus.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfStatus}" columns="80" disabled="true" id="tfStatus" style="height: 24px; width: 144px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property4}" id="property4" label="To" labelAlign="left" visible="#{PgSentItem.lbTo.visible}">
                                    <h:panelGrid id="gridPanel2" style="margin-left: 0px">
                                        <webuijsf:textField binding="#{PgSentItem.tfTo}" columns="80" disabled="true" id="tfTo" style=""/>
                                        <webuijsf:panelLayout id="layoutPanel2" style="height: 48px; width: 100%">
                                            <webuijsf:label for="lbTo" id="label2" style="height: 46px; left: 0px; top: 0px; position: absolute" text="Team "/>
                                            <webuijsf:listbox binding="#{PgSentItem.lbTo}" id="lbTo" items="#{PgSentItem.lbToDefaultOptions.options}" rows="3" style="left: 48px; top: 0px; position: absolute"/>
                                        </webuijsf:panelLayout>
                                    </h:panelGrid>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property5}" id="property5" label="Tags"
                                    style="border: 2px solid teal; background-color: #99ff99" visible="#{PgSentItem.lbTag.visible}">
                                    <h:panelGrid id="gridPanel1">
                                        <webuijsf:listbox binding="#{PgSentItem.lbTag}" id="lbTag" items="#{PgSentItem.lbTagDefaultOptions.options}" rows="3" style="height: 96px; width: 168px"/>
                                        <webuijsf:textField autoComplete="true" autoCompleteExpression="#{PgSentItem.tagsFilter}"
                                            binding="#{PgSentItem.tfAddTags}" columns="80" id="tfAddTags"/>
                                        <webuijsf:panelLayout id="layoutPanel1" panelLayout="flow" style="height: 22px; width: 100%">
                                            <webuijsf:button actionExpression="#{PgSentItem.btnAddTags_action}" binding="#{PgSentItem.btnAddTags}"
                                                id="btnAddTags" text="Add New Tags"/>
                                            <webuijsf:button actionExpression="#{PgSentItem.btnRemoveTag_action}" binding="#{PgSentItem.btnRemoveTag}"
                                                id="btnRemoveTag" text="Remove Tag"/>
                                        </webuijsf:panelLayout>
                                    </h:panelGrid>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property6}" id="property6" label="Subject" visible="#{PgSentItem.tfSubject.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfSubject}" columns="80" disabled="true" id="tfSubject" style="height: 24px; width: 456px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property7}" id="property7" label="Sent date (YYYY-MM-DD)"
                                    style="background-color: #99ff99" visible="#{PgSentItem.tfSentDate.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfSentDate}" columns="15" converter="#{PgSentItem.dateTimeConverter1}"
                                        disabled="true" id="tfSentDate" style="height: 24px; width: 168px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property8}" id="property8" label="Deadline (YYYY-MM-DD)" visible="#{PgSentItem.tfDeadline.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfDeadline}" columns="15" converter="#{PgSentItem.dateTimeConverter1}"
                                        disabled="true" id="tfDeadline" style="height: 24px; width: 144px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property17}" id="property17" label="Links" style="background-color: #99ff99" visible="#{PgSentItem.tbLinks.visible}">
                                    <webuijsf:table augmentTitle="false" binding="#{PgSentItem.tbLinks}" id="tbLinks" width="480">
                                        <webuijsf:tableRowGroup binding="#{PgSentItem.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                            sourceData="#{PgSentItem.linksDataProvider}" sourceVar="currentRow">
                                            <webuijsf:tableColumn binding="#{PgSentItem.tableColumn1}" headerText="Links" id="tableColumn1" sort="string">
                                                <webuijsf:hyperlink actionExpression="#{PgSentItem.hyperlink1_action}" binding="#{PgSentItem.hyperlink1}"
                                                    id="hyperlink1" target="_blank" text="#{currentRow.value['string']}" url="#{currentRow.value['string']}"/>
                                            </webuijsf:tableColumn>
                                        </webuijsf:tableRowGroup>
                                    </webuijsf:table>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property9}" id="property9" label="Action" visible="#{PgSentItem.taAction.visible}">
                                    <webuijsf:textArea binding="#{PgSentItem.taAction}" columns="80" disabled="true" id="taAction" rows="3"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property11}" id="property11" label="Reason for  negotiation / rejection" visible="#{PgSentItem.taReasonNegoReject.visible}">
                                    <webuijsf:textArea binding="#{PgSentItem.taReasonNegoReject}" columns="80" disabled="true" id="taReasonNegoReject" rows="3" style="height: 72px; width: 384px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property10}" id="property10" label="Negotiated deadline" visible="#{PgSentItem.calNegoDeadline.visible}">
                                    <webuijsf:calendar binding="#{PgSentItem.calNegoDeadline}" dateFormatPattern="#{SessionBean1.shortDateFormat}"
                                        dateFormatPatternHelp="YYYY-MM-DD" id="calNegoDeadline"/>
                                    <webuijsf:label id="label1" text="Time (HH:MM)"/>
                                    <webuijsf:textField binding="#{PgSentItem.tfNegoDeadlineTime}" columns="5" id="tfNegoDeadlineTime">
                                        <f:convertDateTime pattern="hh:mm"/>
                                    </webuijsf:textField>
                                    <webuijsf:radioButton binding="#{PgSentItem.rbNegoDeadlineAM}" id="rbNegoDeadlineAM" label="AM" name="radioButton-group-property10"/>
                                    <webuijsf:radioButton binding="#{PgSentItem.rbNegoDeadlinePM}" id="rbNegoDeadlinePM" label="PM" name="radioButton-group-property10"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property13}" id="property13" label="Feedback" visible="#{PgSentItem.taFeedback.visible}">
                                    <webuijsf:textArea binding="#{PgSentItem.taFeedback}" columns="80" disabled="true" id="taFeedback" rows="3"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property14}" id="property14" label="Comments" visible="#{PgSentItem.taComments.visible}">
                                    <webuijsf:textArea binding="#{PgSentItem.taComments}" columns="80" id="taComments" rows="3"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property18}" id="property18" label="Number of redos" visible="#{PgSentItem.tfNumberOfRedos.visible}">
                                    <webuijsf:textField binding="#{PgSentItem.tfNumberOfRedos}" disabled="true" id="tfNumberOfRedos"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property15}" id="property15" label="Approve result" visible="#{PgSentItem.cbWorkApproved.visible}">
                                    <webuijsf:checkbox binding="#{PgSentItem.cbWorkApproved}" id="cbWorkApproved" style="height: 24px; width: 192px"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property19}" id="property19" label="Item tree" rendered="false" visible="#{PgSentItem.trSupervisedItems.visible}">
                                    <webuijsf:tree binding="#{PgSentItem.trSupervisedItems}" id="trSupervisedItems" text="Item tree"/>
                                </webuijsf:property>
                                <webuijsf:property binding="#{PgSentItem.property16}" id="property16">
                                    <webuijsf:button actionExpression="#{PgSentItem.btnSubmit_action}" binding="#{PgSentItem.btnSubmit1}" id="btnSubmit1" style="height: 24px; width: 143px"/>
                                    <webuijsf:button actionExpression="#{PgSentItem.btnAcceptRejectNSeeParent_action}"
                                        binding="#{PgSentItem.btnAcceptRejectNSeeParent}" id="btnAcceptRejectNSeeParent" style="height: 25px" text="Accept/Reject And See Parent Item"/>
                                    <webuijsf:button actionExpression="#{PgSentItem.btnTreeView_action}" binding="#{PgSentItem.btnTreeView}" id="btnTreeView"
                                        immediate="true" style="height: 24px; width: 141px" text="See Children In Tree View"/>
                                    <webuijsf:button actionExpression="#{PgSentItem.btnEdit_action}" binding="#{PgSentItem.btnEdit}" id="btnEdit"
                                        rendered="false" style="height: 24px; width: 186px" text="Copy and create a new item"/>
                                    <webuijsf:button actionExpression="#{PgSentItem.btnDelete_action}" binding="#{PgSentItem.btnDelete}" id="btnDelete"
                                        style="height: 24px; width: 98px" text="Delete this item"/>
                                    <webuijsf:button actionExpression="#{PgSentItem.btnBack_action}" binding="#{PgSentItem.btnBack1}" id="btnBack1"
                                        style="height: 24px; width: 96px" text="Back"/>
                                </webuijsf:property>
                            </webuijsf:propertySheetSection>
                        </webuijsf:propertySheet>
                        <webuijsf:table augmentTitle="false" id="tbAttachments" style="height: 29px; left: 768px; top: 216px; position: absolute"
                            title="Attachment" width="312">
                            <webuijsf:tableRowGroup binding="#{PgSentItem.trgAttachments}" id="trgAttachments" rows="10"
                                sourceData="#{SessionBean1.fileRepositoryDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn headerText="File" id="tableColumn4" sort="fileName">
                                    <webuijsf:hyperlink actionExpression="#{PgSentItem.hlOpenAttachment_action}" id="hlOpenAttachment" text="#{currentRow.value['fileName']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Uploaded By" id="tableColumn5" sort="owner">
                                    <webuijsf:staticText id="staticText1" text="#{currentRow.value['owner'].email}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:panelLayout id="layoutPanel3" panelLayout="flow" style="height: 22px; left: 768px; top: 168px; position: absolute; width: 310px">
                            <webuijsf:button actionExpression="#{PgSentItem.btnGotoAttachments_action}" binding="#{PgSentItem.btnGotoAttachments}"
                                id="btnGotoAttachments" style="height: 26px" text="Manage attachment"/>
                        </webuijsf:panelLayout>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
