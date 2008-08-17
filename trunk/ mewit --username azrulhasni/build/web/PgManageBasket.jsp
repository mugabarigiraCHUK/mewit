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
        <webuijsf:page binding="#{PgManageBasket.page1}" id="page1">
            <webuijsf:html binding="#{PgManageBasket.html1}" id="html1">
                <webuijsf:head binding="#{PgManageBasket.head1}" id="head1">
                    <webuijsf:link binding="#{PgManageBasket.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgManageBasket.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgManageBasket.form1}" id="form1">
                        <webuijsf:table augmentTitle="false" binding="#{PgManageBasket.table1}" id="table1"
                            style="height: 77px; left: 24px; top: 24px; position: absolute" title="My baskets" width="408">
                            <webuijsf:tableRowGroup binding="#{PgManageBasket.tableRowGroup1}" id="tableRowGroup1" rows="10"
                                sourceData="#{PgManageBasket.defaultTableDataProvider}" sourceVar="currentRow">
                                <webuijsf:tableColumn binding="#{PgManageBasket.tableColumn1}" headerText="column1" id="tableColumn1" sort="column1">
                                    <webuijsf:staticText binding="#{PgManageBasket.staticText1}" id="staticText1" text="#{currentRow.value['column1']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgManageBasket.tableColumn2}" headerText="column2" id="tableColumn2" sort="column2">
                                    <webuijsf:staticText binding="#{PgManageBasket.staticText2}" id="staticText2" text="#{currentRow.value['column2']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgManageBasket.tableColumn3}" headerText="column3" id="tableColumn3" sort="column3">
                                    <webuijsf:staticText binding="#{PgManageBasket.staticText3}" id="staticText3" text="#{currentRow.value['column3']}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:table augmentTitle="false" binding="#{PgManageBasket.table2}" id="table2"
                            style="position: absolute; left: 24px; top: 216px; height: 120px" title="Available baskets" width="408">
                            <webuijsf:tableRowGroup binding="#{PgManageBasket.tableRowGroup2}" id="tableRowGroup2" rows="10"
                                sourceData="#{PgManageBasket.defaultTableDataProvider}" sourceVar="currentRow">
                                <webuijsf:tableColumn binding="#{PgManageBasket.tableColumn4}" headerText="column1" id="tableColumn4" sort="column1">
                                    <webuijsf:staticText binding="#{PgManageBasket.staticText4}" id="staticText4" text="#{currentRow.value['column1']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgManageBasket.tableColumn5}" headerText="column2" id="tableColumn5" sort="column2">
                                    <webuijsf:staticText binding="#{PgManageBasket.staticText5}" id="staticText5" text="#{currentRow.value['column2']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgManageBasket.tableColumn6}" headerText="column3" id="tableColumn6" sort="column3">
                                    <webuijsf:staticText binding="#{PgManageBasket.staticText6}" id="staticText6" text="#{currentRow.value['column3']}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
