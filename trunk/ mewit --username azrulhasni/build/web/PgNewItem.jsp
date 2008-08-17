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
        <webuijsf:page binding="#{PgNewItem.page1}" id="page1">
            <webuijsf:html binding="#{PgNewItem.html1}" id="html1">
                <webuijsf:head binding="#{PgNewItem.head1}" id="head1">
                    <webuijsf:link binding="#{PgNewItem.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgNewItem.body1}" focus="form1:tfTo" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgNewItem.form1}" id="form1">
                        <div style="height: 46px; left: 24px; top: 72px; position: absolute">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:textField autoComplete="true" autoCompleteExpression="#{PgNewItem.tagsFilter}" binding="#{PgNewItem.tfTags}" columns="80"
                            id="tfTags" style="height: 24px; left: 120px; top: 312px; position: absolute; width: 312px" tabIndex="6"/>
                        <webuijsf:textArea binding="#{PgNewItem.taAction}" columns="77" id="taAction" rows="3"
                            style="height: 48px; left: 120px; top: 192px; position: absolute; width: 360px" tabIndex="4"/>
                        <webuijsf:textField autoComplete="true" autoCompleteExpression="#{PgNewItem.buddiesEmailFilter}" binding="#{PgNewItem.tfSupervisors}"
                            columns="80" id="tfSupervisors" style="height: 24px; left: 120px; top: 360px; position: absolute; width: 216px" tabIndex="7" text="#{PgNewItem.supervisorFilter}"/>
                        <webuijsf:button actionExpression="#{PgNewItem.btnSubmit_action}" binding="#{PgNewItem.btnSubmit}" id="btnSubmit"
                            style="height: 24px; left: 191px; top: 480px; position: absolute; width: 144px" tabIndex="13" text="Save and Send New Item"/>
                        <webuijsf:button actionExpression="#{PgNewItem.btnBack_action}" binding="#{PgNewItem.btnBack}" id="btnBack" rendered="false"
                            style="height: 24px; left: 215px; top: 480px; position: absolute; width: 66px" tabIndex="19" text="Back"/>
                        <webuijsf:textField binding="#{PgNewItem.tfSubject}" columns="80" id="tfSubject" required="true"
                            style="left: 120px; top: 168px; position: absolute" tabIndex="3"/>
                        <webuijsf:calendar binding="#{PgNewItem.calDeadline}" dateFormatPattern="#{SessionBean1.shortDateFormat}"
                            dateFormatPatternHelp="[YYYY-MM-DD]" id="calDeadline" required="true" style="left: 120px; top: 264px; position: absolute" tabIndex="5"/>
                        <webuijsf:staticText binding="#{PgNewItem.staticText1}" id="staticText1"
                            style="color: rgb(153, 153, 153); height: 22px; left: 624px; top: 312px; position: absolute; width: 382px" text="Comma seperated keywords e.g. Client error report, Download bug, Urgent"/>
                        <webuijsf:textField autoComplete="true" autoCompleteExpression="#{PgNewItem.buddiesEmailFilter}" binding="#{PgNewItem.tfTo}"
                            columns="80" id="tfTo" required="true" style="height: 24px; left: 120px; top: 144px; position: absolute; width: 250px" tabIndex="1"/>
                        <webuijsf:textField binding="#{PgNewItem.tfLink}" columns="80" id="tfLink"
                            style="height: 24px; left: 120px; top: 408px; position: absolute; width: 360px" tabIndex="9" text="#{PgNewItem.link}"/>
                        <webuijsf:listbox binding="#{PgNewItem.lbLinks}" id="lbLinks" items="#{SessionBean1.linkOptions}" rows="3"
                            selected="#{PgNewItem.selectedLink}" style="height: 50px; left: 120px; top: 432px; position: absolute; width: 360px" tabIndex="12" valueChangeListenerExpression="#{PgNewItem.lbLinks_processValueChange}"/>
                        <webuijsf:button actionExpression="#{PgNewItem.btnAddToLinks_action}" binding="#{PgNewItem.btnAddToLinks}" id="btnAddToLinks"
                            onClick="document.getElementById('form1:lbLinks').refresh('form1:tfLink');&#xd;&#xa;&#xd;&#xa;return false;"
                            style="height: 24px; left: 623px; top: 408px; position: absolute; width: 96px" tabIndex="10" text="Add to link"/>
                        <webuijsf:button actionExpression="#{PgNewItem.btnRemoveFromLinks_action}" binding="#{PgNewItem.btnRemoveFromLinks}"
                            id="btnRemoveFromLinks"
                            onClick="&#xd;&#xa;document.getElementById('form1:lbLinks').refresh('form1:lbLinks'); &#xd;&#xa;&#xd;&#xa;return false;"
                            style="height: 24px; left: 719px; top: 408px; position: absolute; width: 96px" tabIndex="11" text="Remove from links"/>
                        <webuijsf:staticText binding="#{PgNewItem.stTitle}" id="stTitle" style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px"/>
                        <webuijsf:staticText binding="#{PgNewItem.staticText3}" id="staticText3"
                            style="color: rgb(153, 153, 153); height: 22px; left: 624px; top: 360px; position: absolute; width: 382px" text="Comma seperated emails e.g.john.doe@email.com, jane.doe@company.com"/>
                        <webuijsf:staticText binding="#{PgNewItem.stSupervisors}" id="stSupervisors" style="height: 24px; left: 120px; top: 336px; position: absolute; width: 358px"/>
                        <webuijsf:image binding="#{PgNewItem.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                        <webuijsf:button actionExpression="#{PgNewItem.btnGotoAttachment_action}" binding="#{PgNewItem.btnGotoAttachment}"
                            id="btnGotoAttachment" style="height: 24px; left: 335px; top: 480px; position: absolute; width: 95px" text="Attach Files"/>
                        <webuijsf:label binding="#{PgNewItem.label1}" for="tfTo" id="label1"
                            style="height: 22px; left: 24px; top: 144px; position: absolute; width: 70px" text="To (Email)"/>
                        <webuijsf:label binding="#{PgNewItem.label8}" for="tfSubject" id="label8"
                            style="height: 24px; left: 24px; top: 168px; position: absolute; width: 72px" text="Subject"/>
                        <webuijsf:label binding="#{PgNewItem.label3}" for="taAction" id="label3"
                            style="height: 24px; left: 24px; top: 192px; position: absolute; width: 72px" text="Action"/>
                        <webuijsf:label binding="#{PgNewItem.label5}" for="calDeadline" id="label5"
                            style="height: 22px; left: 24px; top: 264px; position: absolute; width: 72px" text="Deadline"/>
                        <webuijsf:label binding="#{PgNewItem.label2}" for="tfTags" id="label2"
                            style="height: 24px; left: 24px; top: 312px; position: absolute; width: 72px" text="Tags"/>
                        <webuijsf:label binding="#{PgNewItem.lbCurrentSupervisors}" id="lbCurrentSupervisors"
                            style="height: 24px; left: 0px; top: 336px; position: absolute; width: 120px" text="Current supervisors"/>
                        <webuijsf:label binding="#{PgNewItem.label9}" for="tfSupervisors" id="label9"
                            style="height: 24px; left: -48px; top: 360px; position: absolute; width: 168px" text="Add new supervisors (Email)"/>
                        <webuijsf:label binding="#{PgNewItem.label4}" for="tfLink" id="label4"
                            style="height: 24px; left: 24px; top: 408px; position: absolute; width: 72px" text="Links"/>
                        <webuijsf:hiddenField id="hfLinkToBeDeleted" text="#{PgNewItem.selectedLink}"/>
                        <webuijsf:label binding="#{PgNewItem.label6}" id="label6" style="left: 312px; top: 264px; position: absolute" text="Time (HH:MM)"/>
                        <webuijsf:textField binding="#{PgNewItem.tfDeadlineTime}" columns="5" id="tfDeadlineTime" style="left: 408px; top: 264px; position: absolute">
                            <f:convertDateTime pattern="hh:mm"/>
                        </webuijsf:textField>
                        <webuijsf:radioButton binding="#{PgNewItem.rbDeadlineAM}" id="rbDeadlineAM" label="AM" name="radioButton-group-property12"
                            style="left: 456px; top: 264px; position: absolute" valueChangeListenerExpression="#{PgNewItem.rbNegotiatedDeadlineAM_processValueChange}"/>
                        <webuijsf:radioButton binding="#{PgNewItem.rbDeadlinePM}" id="rbDeadlinePM" label="PM" name="radioButton-group-property12" style="left: 504px; top: 264px; position: absolute"/>
                        <webuijsf:staticText id="staticText4"
                            style="color: rgb(153, 153, 153); height: 22px; left: 624px; top: 144px; position: absolute; width: 382px" text="Comma seperated emails e.g.john.doe@email.com, jane.doe@company.com"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
