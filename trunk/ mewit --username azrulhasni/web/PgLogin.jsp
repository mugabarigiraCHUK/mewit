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
        <webuijsf:page binding="#{PgLogin.page1}" id="page1">
            <webuijsf:html binding="#{PgLogin.html1}" id="html1">
                <webuijsf:head binding="#{PgLogin.head1}" id="head1">
                    <webuijsf:link binding="#{PgLogin.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgLogin.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgLogin.form1}" id="form1">
                        <webuijsf:textField binding="#{PgLogin.tfEmail}" columns="40" id="tfEmail"
                            style="height: 24px; left: 96px; top: 120px; position: absolute; width: 192px" tabIndex="1"/>
                        <webuijsf:label binding="#{PgLogin.label1}" id="label1" style="height: 22px; left: 24px; top: 120px; position: absolute; width: 72px" text="Email"/>
                        <webuijsf:label binding="#{PgLogin.label2}" for="pfPassword" id="label2"
                            style="height: 24px; left: 24px; top: 168px; position: absolute; width: 72px" text="Password"/>
                        <webuijsf:button actionExpression="#{PgLogin.btnSubmit_action}" binding="#{PgLogin.btnSubmit}" id="btnSubmit"
                            style="height: 24px; left: 95px; top: 216px; position: absolute; width: 120px" tabIndex="3" text="Log in"/>
                        <webuijsf:passwordField binding="#{PgLogin.pfPassword}" columns="40" id="pfPassword"
                            style="height: 24px; left: 96px; top: 168px; position: absolute; width: 192px" tabIndex="2"/>
                        <webuijsf:staticText binding="#{PgLogin.staticText1}" id="staticText1"
                            style="height: 22px; left: 24px; top: 312px; position: absolute; width: 406px" text="Lost your password? Put your email address here and reactivate your account. "/>
                        <webuijsf:textField binding="#{PgLogin.tfLostPasswordEmail}" columns="40" id="tfLostPasswordEmail" style="position: absolute; left: 96px; top: 336px; width: 240px; height: 24px"/>
                        <webuijsf:label binding="#{PgLogin.label3}" id="label3" style="height: 24px; left: 24px; top: 336px; position: absolute; width: 72px" text="Email"/>
                        <webuijsf:button actionExpression="#{PgLogin.btnGetBackPassword_action}" binding="#{PgLogin.btnGetBackPassword}" id="btnGetBackPassword"
                            style="height: 24px; left: 95px; top: 384px; position: absolute; width: 120px" text="Get back password"/>
                        <webuijsf:staticText id="staticText2"
                            style="color: #000099; font-size: 36px; font-weight: bold; height: 48px; left: 24px; top: 24px; position: absolute; width: 336px" text="MEWIT.NET"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
