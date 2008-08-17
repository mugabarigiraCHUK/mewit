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
                        <webuijsf:staticText id="staticText1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 142px" text="User Info"/>
                        <webuijsf:image height="48" id="image1" style="left: 696px; top: 24px; position: absolute" url="mew.jpg" width="360"/>
                        <webuijsf:propertySheet id="propertySheet1" style="height: 142px; left: 24px; top: 120px; position: absolute">
                            <webuijsf:propertySheetSection id="section1">
                                <webuijsf:property id="property6">
                                    <webuijsf:button actionExpression="#{PgUserInfo.btnBack_action}" id="btnBack" style="height: 24px; width: 96px" text="Back"/>
                                </webuijsf:property>
                                <webuijsf:property id="property1" label="Name:">
                                    <webuijsf:staticText binding="#{PgUserInfo.stName}" id="stName"/>
                                </webuijsf:property>
                                <webuijsf:property id="property3" label="Email:">
                                    <webuijsf:hyperlink binding="#{PgUserInfo.hlEmail}" id="hlEmail"/>
                                </webuijsf:property>
                                <webuijsf:property id="property2" label="Organization and address">
                                    <webuijsf:staticText binding="#{PgUserInfo.stOfficeAdddress}" id="stOfficeAdddress"/>
                                </webuijsf:property>
                                <webuijsf:property id="property4" label="Department">
                                    <webuijsf:staticText binding="#{PgUserInfo.stDepartment}" id="stDepartment"/>
                                </webuijsf:property>
                                <webuijsf:property id="property5"/>
                            </webuijsf:propertySheetSection>
                        </webuijsf:propertySheet>
                        <webuijsf:table augmentTitle="false" id="table1" style="height: 53px; left: 48px; top: 264px; position: absolute; width: 144px"
                            title="Links" width="144">
                            <webuijsf:tableRowGroup id="tableRowGroup1" rows="10" sourceData="#{PgUserInfo.userItemsDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn headerText="Description" id="tableColumn1" sort="description">
                                    <webuijsf:staticText id="staticText2" text="#{currentRow.value['description']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn headerText="Url" id="tableColumn2" sort="url">
                                    <webuijsf:hyperlink id="hyperlink1" text="#{currentRow.value['url']}" url="#{currentRow.value['url']}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <div style="left: 24px; top: 72px; position: absolute">
                            <jsp:directive.include file="PgfTopMenu.jspf"/>
                        </div>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
