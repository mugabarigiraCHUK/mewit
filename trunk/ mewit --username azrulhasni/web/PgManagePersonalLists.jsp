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
        <webuijsf:page binding="#{PgManagePersonalLists.page1}" id="page1">
            <webuijsf:html binding="#{PgManagePersonalLists.html1}" id="html1">
                <webuijsf:head binding="#{PgManagePersonalLists.head1}" id="head1">
                    <webuijsf:link binding="#{PgManagePersonalLists.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgManagePersonalLists.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgManagePersonalLists.form1}" id="form1">
                        <webuijsf:button actionExpression="#{PgManagePersonalLists.btnSave_action}" binding="#{PgManagePersonalLists.btnSave}" id="btnSave"
                            style="height: 24px; left: 47px; top: 768px; position: absolute; width: 72px" text="Save"/>
                        <webuijsf:staticText binding="#{PgManagePersonalLists.staticText1}" id="staticText1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="Manage buddies/supervisors"/>
                        <div style="left: 24px; top: 72px; position: absolute">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:image binding="#{PgManagePersonalLists.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute"
                            url="mew.jpg" width="360"/>
                        <webuijsf:panelLayout id="layoutPanel1" style="border-width: 1px; border-style: inset; border-color: rgb(153, 153, 153) rgb(153, 153, 153) rgb(153, 153, 153) rgb(153, 153, 153); height: 286px; left: 24px; top: 456px; position: absolute; width: 742px; -rave-layout: grid">
                            <webuijsf:propertySheet id="propertySheet1" style="left: 0px; top: 0px; position: absolute; width: 573px">
                                <webuijsf:propertySheetSection id="section1" label="Personal links" style="height: 92px">
                                    <webuijsf:property id="property1" label="URL">
                                        <webuijsf:textField binding="#{PgManagePersonalLists.tfLinkURL}" columns="40" id="tfLinkURL"/>
                                    </webuijsf:property>
                                    <webuijsf:property id="property2" label="Description">
                                        <webuijsf:textField binding="#{PgManagePersonalLists.tfLinkDescription}" columns="40" id="tfLinkDescription" style="height: 24px; width: 144px"/>
                                    </webuijsf:property>
                                    <webuijsf:property id="property3">
                                        <webuijsf:button actionExpression="#{PgManagePersonalLists.btnDelete_action}" id="btnDelete" text="Delete"/>
                                        <webuijsf:button actionExpression="#{PgManagePersonalLists.btnAdd_action}" id="btnAdd" text="Add"/>
                                    </webuijsf:property>
                                    <webuijsf:property id="property4"/>
                                </webuijsf:propertySheetSection>
                            </webuijsf:propertySheet>
                            <webuijsf:table augmentTitle="false" binding="#{PgManagePersonalLists.tbUserLinks}" deselectMultipleButton="true" id="tbUserLinks"
                                paginateButton="true" paginationControls="true" selectMultipleButton="true"
                                style="height: 28px; left: 24px; top: 120px; position: absolute; width: 0px" width="0">
                                <webuijsf:tableRowGroup binding="#{PgManagePersonalLists.trgUserLinks}" id="trgUserLinks" rows="3"
                                    selected="#{PgManagePersonalLists.selectedState}" sourceData="#{SessionBean1.userLinksDP}" sourceVar="currentRow">
                                    <webuijsf:tableColumn id="trgUserLinksSelectionColumn"
                                        onClick="setTimeout(function(){document.getElementById('form1:layoutPanel1:table1').initAllRows()}, 0);" selectId="trgUserLinksSelectionChild">
                                        <webuijsf:checkbox id="trgUserLinksSelectionChild" selected="#{PgManagePersonalLists.selected}" selectedValue="#{PgManagePersonalLists.selectedValue}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn headerText="Description" id="tableColumn1" sort="description">
                                        <webuijsf:staticText id="staticText2" text="#{currentRow.value['description']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn headerText="Url" id="tableColumn2" sort="url">
                                        <webuijsf:hyperlink id="hyperlink1" text="#{currentRow.value['url']}" url="#{currentRow.value['url']}"/>
                                    </webuijsf:tableColumn>
                                </webuijsf:tableRowGroup>
                            </webuijsf:table>
                        </webuijsf:panelLayout>
                        <webuijsf:panelLayout id="layoutPanel3" style="border-width: 1px; border-style: inset; border-color: rgb(102, 102, 102) rgb(102, 102, 102) rgb(102, 102, 102) rgb(102, 102, 102); height: 142px; left: 24px; top: 120px; position: absolute; width: 742px; -rave-layout: grid">
                            <webuijsf:staticText binding="#{PgManagePersonalLists.stWarningEmailBuddyNotInDB}" id="stWarningEmailBuddyNotInDB" style="color: rgb(204, 0, 0); font-weight: bold; height: 24px; left: 24px; top: 48px; position: absolute; width: 597px"/>
                            <webuijsf:button actionExpression="#{PgManagePersonalLists.btnRemoveBuddy_action}" binding="#{PgManagePersonalLists.btnRemoveBuddy}"
                                id="btnRemoveBuddy" style="height: 24px; left: 479px; top: 24px; position: absolute; width: 120px" text="Remove buddy"/>
                            <webuijsf:button actionExpression="#{PgManagePersonalLists.btnAddToBuddies_action}"
                                binding="#{PgManagePersonalLists.btnAddToBuddies}" id="btnAddToBuddies"
                                style="height: 24px; left: 359px; top: 24px; position: absolute; width: 120px" text="Add buddy"/>
                            <webuijsf:listbox binding="#{PgManagePersonalLists.lbBuddies}" id="lbBuddies"
                                items="#{PgManagePersonalLists.lbBuddiesDefaultOptions.options}" rows="3" style="left: 120px; top: 72px; position: absolute" valueChangeListenerExpression="#{PgManagePersonalLists.lbBuddies_processValueChange}"/>
                            <webuijsf:label binding="#{PgManagePersonalLists.label1}" id="label1"
                                style="height: 22px; left: 24px; top: 24px; position: absolute; width: 70px" text="Buddy"/>
                            <webuijsf:textField binding="#{PgManagePersonalLists.tfBuddy}" columns="40" id="tfBuddy" style="left: 96px; top: 24px; position: absolute"/>
                            <webuijsf:button actionExpression="#{PgManagePersonalLists.btnSeeProfileBuddy_action}" id="btnSeeProfileBuddy"
                                style="position: absolute; left: 24px; top: 72px; width: 72px; height: 24px" text="See Profile"/>
                        </webuijsf:panelLayout>
                        <webuijsf:panelLayout id="layoutPanel2" style="border-width: 1px; border-style: inset; border-color: rgb(102, 102, 102) rgb(102, 102, 102) rgb(102, 102, 102) rgb(102, 102, 102); height: 142px; left: 24px; top: 288px; position: absolute; width: 742px; -rave-layout: grid">
                            <webuijsf:listbox binding="#{PgManagePersonalLists.lbSupervisors}" id="lbSupervisors"
                                items="#{PgManagePersonalLists.lbSupervisorsDefaultOptions.options}" rows="3" style="left: 120px; top: 72px; position: absolute"/>
                            <webuijsf:textField binding="#{PgManagePersonalLists.tfSupervisor}" columns="40" id="tfSupervisor" style="left: 96px; top: 24px; position: absolute"/>
                            <webuijsf:label binding="#{PgManagePersonalLists.label2}" for="tfSupervisor" id="label2"
                                style="height: 24px; left: 24px; top: 24px; position: absolute; width: 72px" text="Supervisor"/>
                            <webuijsf:staticText binding="#{PgManagePersonalLists.stWarningEmailSupervisorNotInDB}" id="stWarningEmailSupervisorNotInDB" style="color: rgb(204, 0, 0); font-weight: bold; height: 24px; left: 24px; top: 48px; position: absolute; width: 596px"/>
                            <webuijsf:button actionExpression="#{PgManagePersonalLists.btnRemoveSupervisor_action}"
                                binding="#{PgManagePersonalLists.btnRemoveSupervisor}" id="btnRemoveSupervisor"
                                style="height: 24px; left: 480px; top: 25px; position: absolute; width: 120px" text="Remove supervisor"/>
                            <webuijsf:button actionExpression="#{PgManagePersonalLists.btnAddSupervisor_action}"
                                binding="#{PgManagePersonalLists.btnAddSupervisor}" id="btnAddSupervisor"
                                style="height: 24px; left: 360px; top: 25px; position: absolute; width: 119px" text="Add supervisor"/>
                            <webuijsf:button actionExpression="#{PgManagePersonalLists.btnSeeProfileSupervisor_action}" id="btnSeeProfileSupervisor"
                                style="position: absolute; left: 24px; top: 72px; width: 72px; height: 24px" text="See Profile"/>
                        </webuijsf:panelLayout>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
