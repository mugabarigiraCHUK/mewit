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
        <webuijsf:page binding="#{PgEditProfile.page1}" id="page1">
            <webuijsf:html binding="#{PgEditProfile.html1}" id="html1">
                <webuijsf:head binding="#{PgEditProfile.head1}" id="head1" title="MEWit:: Edit Profile">
                    <webuijsf:link binding="#{PgEditProfile.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgEditProfile.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgEditProfile.form1}" id="form1">
                        <webuijsf:label binding="#{PgEditProfile.label1}" for="tfEmail" id="label1"
                            style="height: 22px; left: 24px; top: 168px; position: absolute; width: 46px" text="Email"/>
                        <webuijsf:textField binding="#{PgEditProfile.tfEmail}" columns="40" id="tfEmail" readOnly="true" style="height: 24px; left: 192px; top: 168px; position: absolute; width: 288px"/>
                        <webuijsf:label binding="#{PgEditProfile.label2}" for="pfPassword" id="label2"
                            style="height: 24px; left: 24px; top: 264px; position: absolute; width: 118px" text="New password"/>
                        <webuijsf:passwordField binding="#{PgEditProfile.pfPassword}" id="pfPassword" required="true"
                            style="left: 192px; top: 264px; position: absolute" validatorExpression="#{PgEditProfile.lengthValidator.validate}"/>
                        <webuijsf:passwordField binding="#{PgEditProfile.pfRetypePassword}" id="pfRetypePassword" required="true"
                            style="left: 192px; top: 312px; position: absolute" validatorExpression="#{PgEditProfile.pfRetypePassword_validate}"/>
                        <webuijsf:label binding="#{PgEditProfile.label3}" for="pfRetypePassword" id="label3"
                            style="height: 22px; left: 24px; top: 312px; position: absolute; width: 142px" text="Retype new password"/>
                        <webuijsf:textField binding="#{PgEditProfile.tfName}" columns="40" id="tfName" required="true"
                            style="height: 24px; left: 192px; top: 360px; position: absolute; width: 288px" validatorExpression="#{PgEditProfile.lengthValidator.validate}"/>
                        <webuijsf:textField binding="#{PgEditProfile.tfOfficeAddress}" columns="40" id="tfOfficeAddress" required="true"
                            style="height: 24px; left: 192px; top: 408px; position: absolute; width: 288px" validatorExpression="#{PgEditProfile.lengthValidator.validate}"/>
                        <webuijsf:label binding="#{PgEditProfile.label4}" for="tfName" id="label4"
                            style="height: 22px; left: 24px; top: 360px; position: absolute; width: 72px" text="Name"/>
                        <webuijsf:label binding="#{PgEditProfile.label5}" for="tfOfficeAddress" id="label5"
                            style="height: 24px; left: 24px; top: 408px; position: absolute; width: 96px" text="Organization and Address"/>
                        <webuijsf:label binding="#{PgEditProfile.label6}" for="tfDepartment" id="label6"
                            style="height: 22px; left: 24px; top: 456px; position: absolute; width: 96px" text="Department"/>
                        <webuijsf:textField binding="#{PgEditProfile.tfDepartment}" columns="40" id="tfDepartment" required="true"
                            style="height: 24px; left: 192px; top: 456px; position: absolute; width: 288px" validatorExpression="#{PgEditProfile.lengthValidator.validate}"/>
                        <webuijsf:button actionExpression="#{PgEditProfile.btnSubmit_action}" binding="#{PgEditProfile.btnSubmit}" id="btnSubmit"
                            style="height: 24px; left: 143px; top: 552px; position: absolute; width: 96px" text="Submit"/>
                        <webuijsf:staticText binding="#{PgEditProfile.staticText1}" id="staticText1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="User"/>
                        <webuijsf:staticText binding="#{PgEditProfile.staticText2}" id="staticText2"
                            style="color: rgb(255, 0, 0); font-size: 12px; font-weight: bold; height: 24px; left: 24px; top: 120px; position: absolute; width: 432px" text="User needs to relogin if the profile is changed"/>
                        <webuijsf:button actionExpression="#{PgEditProfile.btnBack_action}" binding="#{PgEditProfile.btnBack}" id="btnBack" immediate="true"
                            rendered="false" style="position: absolute; left: 240px; top: 456px; width: 96px; height: 24px" text="Back"/>
                        <webuijsf:label binding="#{PgEditProfile.label7}" id="label7"
                            style="height: 22px; left: 24px; top: 216px; position: absolute; width: 94px" text="Old password"/>
                        <webuijsf:passwordField binding="#{PgEditProfile.pfOldPassword}" id="pfOldPassword" style="height: 24px; left: 192px; top: 216px; position: absolute; width: 120px"/>
                        <div style="position: absolute; left: 24px; top: 72px">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                        <webuijsf:image binding="#{PgEditProfile.image1}" height="48" id="image1" style="left: 696px; top: 24px; position: absolute"
                            url="mew.jpg" width="360"/>
                        <webuijsf:staticText binding="#{PgEditProfile.stErrors}" id="stErrors" style="color: rgb(255, 0, 0); font-size: 12px; font-weight: bold; height: 24px; left: 24px; top: 144px; position: absolute; width: 432px"/>
                        <webuijsf:checkbox binding="#{PgEditProfile.cbOkToReceiveEmail}" id="cbOkToReceiveEmail"
                            label="I want to receive email alerts if a new task is delivered to me" style="left: 24px; top: 504px; position: absolute" valueChangeListenerExpression="#{PgEditProfile.cbOkToReceiveEmail_processValueChange}"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
