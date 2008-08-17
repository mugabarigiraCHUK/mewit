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
        <webuijsf:page binding="#{PgFileRepository.page1}" id="page1">
            <webuijsf:html binding="#{PgFileRepository.html1}" id="html1">
                <webuijsf:head binding="#{PgFileRepository.head1}" id="head1" title="">
                    <webuijsf:link binding="#{PgFileRepository.link1}" id="link1" url="/resources/stylesheet.css"/>
                </webuijsf:head>
                <webuijsf:body binding="#{PgFileRepository.body1}" id="body1" style="-rave-layout: grid">
                    <webuijsf:form binding="#{PgFileRepository.form1}" id="form1">
                        <webuijsf:image binding="#{PgFileRepository.image1}" height="48" id="image1" style="left: 480px; top: 24px; position: absolute"
                            url="mew.jpg" width="360"/>
                        <webuijsf:staticText binding="#{PgFileRepository.sent_item1}" id="sent_item1"
                            style="font-size: 18px; font-weight: bold; height: 46px; left: 24px; top: 24px; position: absolute; text-decoration: underline; width: 286px" text="File repository"/>
                        <webuijsf:upload binding="#{PgFileRepository.fuUpload}" id="fuUpload" style="height: 24px; left: 24px; top: 144px; position: absolute; width: 288px"/>
                        <webuijsf:table augmentTitle="false" binding="#{PgFileRepository.tbAttachments}" deselectMultipleButton="true" id="tbAttachments"
                            paginateButton="true" paginationControls="true" selectMultipleButton="true"
                            style="height: 29px; left: 24px; top: 216px; position: absolute; width: 648px" title="Files" width="648">
                            <webuijsf:tableRowGroup binding="#{PgFileRepository.trgAttachments}" id="trgAttachments" rows="10"
                                selected="#{PgFileRepository.selectedState}" sourceData="#{SessionBean1.fileRepositoryDP}" sourceVar="currentRow">
                                <webuijsf:tableColumn binding="#{PgFileRepository.trgAttachmentsSelectionColumn}" id="trgAttachmentsSelectionColumn"
                                    onClick="setTimeout(function(){document.getElementById('form1:table1').initAllRows()}, 0);" selectId="trgAttachmentsSelectionChild">
                                    <webuijsf:checkbox binding="#{PgFileRepository.trgAttachmentsSelectionChild}" id="trgAttachmentsSelectionChild"
                                        selected="#{PgFileRepository.selected}" selectedValue="#{PgFileRepository.selectedValue}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgFileRepository.tableColumn4}" headerText="File" id="tableColumn4" sort="fileName">
                                    <webuijsf:hyperlink actionExpression="#{PgFileRepository.hlOpenAttachment_action}"
                                        binding="#{PgFileRepository.hlOpenAttachment}" id="hlOpenAttachment" text="#{currentRow.value['fileName']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgFileRepository.tableColumn5}" headerText="Uploaded by" id="tableColumn5" sort="owner" width="225">
                                    <webuijsf:staticText binding="#{PgFileRepository.staticText5}" id="staticText5" text="#{currentRow.value['owner'].name} [#{currentRow.value['owner'].email}]"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{PgFileRepository.tableColumn1}" headerText="Upload Date [YYYY-MM-DD HH:MM]" id="tableColumn1" sort="uploadDate">
                                    <webuijsf:staticText binding="#{PgFileRepository.staticText2}" id="staticText2" text="#{currentRow.value['uploadDate']}">
                                        <f:convertDateTime pattern="#{SessionBean1.longDateFormat}"/>
                                    </webuijsf:staticText>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <webuijsf:button actionExpression="#{PgFileRepository.btnUpload_action}" binding="#{PgFileRepository.btnUpload}" id="btnUpload"
                            style="height: 24px; left: 359px; top: 144px; position: absolute; width: 96px" text="Upload"/>
                        <webuijsf:button actionExpression="#{PgFileRepository.btnBack_action}" binding="#{PgFileRepository.btnBack}" id="btnBack"
                            style="height: 24px; left: 23px; top: 168px; position: absolute; width: 120px" text="Done uploading"/>
                        <webuijsf:staticText binding="#{PgFileRepository.staticText1}" id="staticText1"
                            style="height: 24px; left: 24px; top: 120px; position: absolute; width: 46px" text="Owner:"/>
                        <webuijsf:staticText binding="#{PgFileRepository.stOwner}" id="stOwner" style="height: 22px; left: 72px; top: 120px; position: absolute; width: 382px"/>
                        <webuijsf:button actionExpression="#{PgFileRepository.btnDeleteSelected_action}" binding="#{PgFileRepository.btnDeleteSelected}"
                            id="btnDeleteSelected"
                            onClick="return confirm(&quot;Are you sure you wish to delete these entries? Note: You can delete any file in you own the repository. You can only delete files uploaded by yourself if not&quot;);"
                            style="height: 24px; left: 143px; top: 168px; position: absolute; width: 96px" text="Delete Selected"/>
                        <webuijsf:button actionExpression="#{PgFileRepository.btnAttachFromItems_action}" binding="#{PgFileRepository.btnAttachFromItems}"
                            id="btnAttachFromItems" style="height: 24px; left: 456px; top: 144px; position: absolute; width: 167px" text="Attach files from available items"/>
                        <webuijsf:button actionExpression="#{PgFileRepository.button1_action}" id="button1"
                            style="height: 24px; left: 240px; top: 168px; position: absolute; width: 191px" text="Download All Selected As Zip File"/>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
