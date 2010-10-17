dojo.require('dojox.uuid.Uuid');
dojo.require('dojox.uuid.generateRandomUuid');
dojo.require('dojox.json.query');
dojo.declare("Main", wm.Page, {
  start: function() {
    this.findCurrentPersonServVar.update();
  },
  searchItemsServVarBeforeUpdate: function(inSender, ioInput) {
    try {
      app.pageDialog.showPage("Wait",true,350,250);   
    } catch(e) {
      console.error('ERROR IN searchItemsServVarBeforeUpdate: ' + e); 
    } 
  },
  searchItemsServVarResult: function(inSender, inData) {
    try  {
      app.pageDialog.dismiss();
    } catch(e) {
      console.error('ERROR IN searchItemsServVarResult: ' + e); 
    } 
  },
  
  onDataGridSelectionChanged: function(inSender) {
    try {
     
      
    } catch(e) {
      console.error('ERROR IN onDataGridSelectionChanged: ' + e); 
    } 
  },
 onDataGridSelected: function(inSender, inIndex) {
       try {
         var item = this.dataGrid1.dataSet.getItem(inIndex).getData();
         this.currentItem.setData(item);
          //console.log('attachments length='+dojo.toJson(item.fileRepository));

        } catch(e) {
            console.error('ERROR IN onDataGridSelected: ' + e);
        }
    },
 
  updateItemServVarSuccess: function(inSender, inData) {
    try {
     console.log(inData);
      
    } catch(e) {
      console.error('ERROR IN updateItemServVarSuccess: ' + e); 
    } 
  },
  modifyItemServVarSuccess: function(inSender, inData) {
    try {
       app.pageDialog.dismiss();
       var item = inData;
       console.log('item status='+item.status);
       if (item.status!='UNCONFIRMED'){
         row = this.dataGrid1.selected.itemIndex;
         var item2 = this.dataGrid1.dataSet.getItem(row).getData();
         if (item2.id==item.id){
           //console.log('find item 2:'+dojox.json.query('$',item2))
           this.dataGrid1.dataSet.removeItem(row);
           this.dataGrid1.dataSet.addItem(inData, row);
           this.dataGrid1.select(row);
         }
         this.currentItem.setData(inData);
      }else{
          this.subjectEditor2.setValue("readonly",true);
          this.priorityEditor1.setValue("readonly",true);
          this.recipientEditor2.setValue("readonly",true);
          this.startDateEditor2.setValue("readonly",true);
          this.deadLineEditor2.setValue("readonly",true);
          this.taskEditor2.setValue("readonly",true);
          this.linksEditor1.setValue("readonly",true);           
          this.tagsEditor1.setValue("readonly",true);
          this.ffuAttachments.setValue("readonly",true);
          this.attachmentList.setValue("readonly",true);
      }
    } catch(e) {
      console.error('ERROR IN modifyItemServVarSuccess: ' + e); 
    } 
  },
  selAcceptNegoRejectChange: function(inSender, inDisplayValue, inDataValue) {

    try {
      this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",false);
      this.negotiatedDeadLineEditor2.setValue("showing",false);
      this.reasonForRejectionOfTaskEditor2.setValue("showing",false);
           
      if (inDataValue=='Accept'){
        
      }else if (inDataValue=='Negotiate'){
        this.negotiatedDeadLineEditor2.setValue("showing",true);
        this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",true);
      }else if (inDataValue=='Reject'){
        this.reasonForRejectionOfTaskEditor2.setValue("showing",true);
      }
      
    } catch(e) {
      console.error('ERROR IN selAcceptNegoRejectChange: ' + e); 
    } 
  },
  sessionIdResult: function(inSender, inData) {
    try {
       this.userIdentity.setDisplayValue(inData);
      
    } catch(e) {
      console.error('ERROR IN userIdResult: ' + e); 
    } 
  },
  attachmentListDblclick: function(inSender, inEvent, inItem) {
    try {
        console.log('sender='+inSender);
        console.log('item='+inItem.getData().fileName);     
        url= 'http://localhost:8084/MEWIT/Download?attachmentId='+inItem.getData().id;
        window.open(url,'Download'); 

    } catch(e) {
      console.error('ERROR IN list1Dblclick: ' + e); 
    } 
  },
 
  attachmentFileUploadResult: function(inSender, inData) {
    try {
    
      
    } catch(e) {
      console.error('ERROR IN attachmentFileUploadResult: ' + e); 
    } 
  },
  attachmentFileUploadSuccess: function(inSender, inData) {
    try {
     
      
    } catch(e) {
      console.error('ERROR IN attachmentFileUploadSuccess: ' + e); 
    } 
  },
  attachmentFileUploadBegin: function(inSender) {
    try {
     
      
    } catch(e) {
      console.error('ERROR IN attachmentFileUploadBegin: ' + e); 
    } 
  },
  ffuAttachmentsComplete: function(inSender, inResult) {
    try {
      this.formCurrentItem.populateDataOutput();
      var item3 = this.formCurrentItem.dataOutput.getData();
      
      var links = this.linksEditor1.editor.getEditorValue();
      var tags = this.tagsEditor1.editor.getEditorValue();
      if (links!=undefined){
         item3.links= links.split(',');
      }
      if (tags!=undefined){
         item3.tags= tags.split(',');
      }      
      
      item3.fileRepository = this.currentItem.getData().fileRepository;
      
      item3.recipients =  this.currentItem.getData().recipients;
      item3.recipientsList =  this.currentItem.getData().recipientsList;
      item3.supervisors =  this.currentItem.getData().supervisors;
     
      console.log('from ffuattachmentscomplete item3='+dojo.toJson(item3));
      console.log('from ffuattachmentscomplete currentItem='+dojo.toJson(this.currentItem.getData()));
      this.addAttachmentServVar.setValue('input.file',inResult.result);
      this.addAttachmentServVar.setValue('input.item',item3);
      this.addAttachmentServVar.update();
      
    } catch(e) {
      console.error('ERROR IN ffuAttachmentsComplete: ' + e); 
    } 
  },
  addAttachmentServVarSuccess: function(inSender, inData) {
    try {
        if (this.dataGrid1.selected!=null){
          var row = this.dataGrid1.selected.itemIndex;
          this.dataGrid1.dataSet.removeItem(row);
          this.dataGrid1.dataSet.addItem(inData, row)
          this.dataGrid1.select(row);
        }
        var item2 = inData;
        item2.fileRepository = inData.fileRepository;
        this.currentItem.setData(item2);
        this.attachmentList.update();
    } catch(e) {
        console.error('ERROR IN addAttachmentServVarSuccess: ' + e); 
    } 
  },
  addAttachmentServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);  
      
    } catch(e) {
      console.error('ERROR IN addAttachmentServVarBeforeUpdate: ' + e); 
    } 
  },
  addAttachmentServVarResult: function(inSender, inData) {
    try {
     app.pageDialog.dismiss();
      
    } catch(e) {
      console.error('ERROR IN addAttachmentServVarResult: ' + e); 
    } 
  },
  acceptItemServVarSuccess: function(inSender, inData) {
    try {
    
      app.pageDialog.dismiss();
      row = this.dataGrid1.selected.itemIndex;
      this.dataGrid1.dataSet.removeItem(row);
      this.dataGrid1.dataSet.addItem(inData, row)
      this.dataGrid1.select(row);
      
    } catch(e) {
      console.error('ERROR IN acceptItemServVarSuccess: ' + e); 
    } 
  },
  giveFeedbackServVarSuccess: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      row = this.dataGrid1.selected.itemIndex;
      this.dataGrid1.dataSet.removeItem(row);
      this.dataGrid1.dataSet.addItem(inData, row)
      this.dataGrid1.select(row);
    } catch(e) {
      console.error('ERROR IN giveFeedbackServVarSuccess: ' + e); 
    } 
  },
  giveCommentsOnFeedbackServVarSuccess: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      row = this.dataGrid1.selected.itemIndex;
      this.dataGrid1.dataSet.removeItem(row);
      this.dataGrid1.dataSet.addItem(inData, row)
      this.dataGrid1.select(row);
    } catch(e) {
      console.error('ERROR IN giveCommentsOnFeedbackServVarSuccess: ' + e); 
    } 
  },
  negotiateDeadlineServVarSuccess: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      row = this.dataGrid1.selected.itemIndex;
      this.dataGrid1.dataSet.removeItem(row);
      this.dataGrid1.dataSet.addItem(inData, row)
      this.dataGrid1.select(row);
      
    } catch(e) {
      console.error('ERROR IN negotiateDeadlineServVarSuccess: ' + e); 
    } 
  },
  rejectItemServVarSuccess: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      row = this.dataGrid1.selected.itemIndex;
      this.dataGrid1.dataSet.removeItem(row);
      this.dataGrid1.dataSet.addItem(inData, row)
      this.dataGrid1.select(row);
      
    } catch(e) {
      console.error('ERROR IN rejectItemServVarSuccess: ' + e); 
    } 
  },
  respondNegotiatedDeadlineServVarSuccess: function(inSender, inData) {
    try {
     row = this.dataGrid1.selected.itemIndex;
      this.dataGrid1.dataSet.removeItem(row);
      this.dataGrid1.dataSet.addItem(inData, row)
      this.dataGrid1.select(row);
      
    } catch(e) {
      console.error('ERROR IN respondNegotiatedDeadlineServVarSuccess: ' + e); 
    } 
  },
  acceptItemServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);
      
    } catch(e) {
      console.error('ERROR IN acceptItemServVarBeforeUpdate: ' + e); 
    } 
  },
  giveCommentsOnFeedbackServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);
      
    } catch(e) {
      console.error('ERROR IN giveCommentsOnFeedbackServVarBeforeUpdate: ' + e); 
    } 
  },
  giveFeedbackServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);
      
    } catch(e) {
      console.error('ERROR IN giveFeedbackServVarBeforeUpdate: ' + e); 
    } 
  },
  negotiateDeadlineServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);
      
    } catch(e) {
      console.error('ERROR IN negotiateDeadlineServVarBeforeUpdate: ' + e); 
    } 
  },
  rejectItemServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);
      
    } catch(e) {
      console.error('ERROR IN rejectItemServVarBeforeUpdate: ' + e); 
    } 
  },
  respondNegotiatedDeadlineServVarBeforeUpdate: function(inSender, ioInput) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);
      
    } catch(e) {
      console.error('ERROR IN respondNegotiatedDeadlineServVarBeforeUpdate: ' + e); 
    } 
  },
  btnNewItemClick: function(inSender, inEvent) {
    try {
            var item3 = {"archived":false,"archivedForRecipient":false,"archivedForSender":false,"children":[],"commentsOnFeedback":null,"deadLine":null,"deadlineStatus":"DEADLINE NOT SET","deletable":false,"feedback":null,"fileRepository":{"attachments":[],"description":null,"id":null,"owner":null,"tempAttachments":[],"tempAttachmentsList":[]},"id":null,"links":[],"negotiatedDeadLine":null,"notDeletable":true,"parentId":null,"priority":"NOT_SET","readState":null,"reasonForNegotiatiationOfDeadLine":null,"reasonForRejectionOfTask":null,"recipient":null,"recipients":[],"recipientsList":[],"redoCounter":0,"reference":false,"sender":null,"sentDate":null,"startDate":null,"status":null,"subject":"","supervisors":[],"tags":[],"task":null,"type":""} 
            item3.id = dojox.uuid.generateRandomUuid();
            item3.fileRepository.id = dojox.uuid.generateRandomUuid();
            
            this.currentItem.setData(item3);
            this.dataGrid1.selected = null;
            this.btnDelegate.setValue("showing",false);
            this.btnSaveDelegation.setValue("showing",false);
            this.btnModify.setValue("showing",true);
            this.btnGoToParent.setValue("showing",false);

            this.priorityEditor1.setValue("showing",true);
            this.recipientEditor2.setValue("showing",true);
            this.startDateEditor2.setValue("showing",true);
            this.deadLineEditor2.setValue("showing",true);
            this.taskEditor2.setValue("showing",true);
            this.linksEditor1.setValue("showing",true);           
            this.tagsEditor1.setValue("showing",true);
            this.ffuAttachments.setValue("showing",true);
            this.attachmentList.setValue("showing",true);
            this.subjectEditor2.setValue("showing",true);
            
            this.priorityEditor1.setValue("readonly",false);
            this.recipientEditor2.setValue("readonly",false);
            this.startDateEditor2.setValue("readonly",false);
            this.deadLineEditor2.setValue("readonly",false);
            this.taskEditor2.setValue("readonly",false);
            this.linksEditor1.setValue("readonly",false);           
            this.tagsEditor1.setValue("readonly",false);
            this.ffuAttachments.setValue("readonly",false);
            this.attachmentList.setValue("readonly",false);
            this.subjectEditor2.setValue("readonly",false);
            
             this.priorityEditor1.clear();
            this.recipientEditor2.clear();
            this.startDateEditor2.clear();
            this.deadLineEditor2.clear();
            this.taskEditor2.clear();
            this.linksEditor1.clear();           
            this.tagsEditor1.clear();
            this.attachmentList.clear();
            this.subjectEditor2.clear();
            
            this.referenceEditor1.setValue("showing",false);
            this.archivedEditor1.setValue("showing",false);
            this.senderEditor2.setValue("showing",false);
            this.statusEditor1.setValue("showing",false);
            this.deadlineStatusEditor1.setValue("showing",false);
            this.typeEditor1.setValue("showing",false);
            this.btnAttachment.setValue("showing",false);
            this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",false);
            this.negotiatedDeadLineEditor2.setValue("showing",false);
            this.reasonForRejectionOfTaskEditor2.setValue("showing",false);
            this.feedbackEditor1.setValue("showing",false);
            this.commentsOnFeedbackEditor1.setValue("showing",false);
            this.pnlAcceptNegoReject.setValue("showing",false);
            this.pnlApproveOrNot.setValue("showing",false);
            
            this.layerSearchResult.setValue("showing",false);
            this.layerEditor.setValue("showing",true);
            this.gotoEditor.update();

      } catch(e){
            console.error('ERROR IN btnNewItemClick: ' + e); 
      } 
  },
  dataGrid1RowDblClick: function(inSender, inEvent) {
    try {
      this.layerSearchResult.setValue("showing",false);
      this.layerEditor.setValue("showing",true);
      this.gotoEdit.update(); 
    } catch(e) {
      console.error('ERROR IN dataGrid1RowDblClick: ' + e); 
    } 
  },
  btnGoToSearchClick: function(inSender, inEvent) {
    try {
      this.layerSearchResult.setValue("showing",true);
      this.layerEditor.setValue("showing",false);   
      this.gotoSearch.update();
    } catch(e) {
      console.error('ERROR IN btnGoToSearchClick: ' + e); 
    } 
  },
  btnModifyClick: function(inSender, inEvent) {
    try {
      app.pageDialog.showPage("Wait",true,350,250);  
      this.modifyItemServVar.update();
    } catch(e) {
      console.error('ERROR IN btnModifyClick: ' + e); 
    } 
  },
  searchBtnClick: function(inSender, inEvent) {
    try {
      this.searchItemsServVar.setValue('input.resultCount',10);
      this.searchItemsServVar.setValue('input.startRow',0);
      this.searchItemsServVar.setValue('input.searchInItemsNeededAttentionOnly',false);
      this.searchItemsServVar.update();
      //console.log('pages:'+dojo.toJson(this.searchItemsServVar.pages));
    } catch(e) {
      console.error('ERROR IN searchBtnClick: ' + e); 
    } 
  },
  seJumpToPageChange: function(inSender, inDisplayValue, inDataValue) {
    try {
     this.searchItemsServVar.setValue('input.resultCount',10);
     this.searchItemsServVar.setValue('input.startRow',inDataValue-1);
     this.searchItemsServVar.update();
      
    } catch(e) {
      console.error('ERROR IN seJumpToPageChange: ' + e); 
    } 
  },
  currentItemPrepareSetData: function(inSender, inData) {
   try {
      var item = inData;
      
      if (item.status!=null){
            this.priorityEditor1.setValue("showing",true);
            this.subjectEditor2.setValue("showing",true);
            this.senderEditor2.setValue("showing",true);
            this.recipientEditor2.setValue("showing",true);
            this.startDateEditor2.setValue("showing",true);
            this.deadLineEditor2.setValue("showing",true);
            this.deadlineStatusEditor1.setValue("showing",true);
            this.taskEditor2.setValue("showing",true);
            this.archivedEditor1.setValue("showing",true);
            this.linksEditor1.setValue("showing",true);
            this.statusEditor1.setValue("showing",true);
            this.tagsEditor1.setValue("showing",true);
            this.typeEditor1.setValue("showing",true);
            this.referenceEditor1.setValue("showing",true);
      
            this.priorityEditor1.setValue("readonly",true);
            this.subjectEditor2.setValue("readonly",true);
            this.senderEditor2.setValue("readonly",true);
            this.recipientEditor2.setValue("readonly",true);
            this.startDateEditor2.setValue("readonly",true);
            this.deadLineEditor2.setValue("readonly",true);
            this.deadlineStatusEditor1.setValue("readonly",true);
            this.taskEditor2.setValue("readonly",true);
            this.archivedEditor1.setValue("readonly",true);
            this.linksEditor1.setValue("readonly",true);
            this.statusEditor1.setValue("readonly",true);
            this.tagsEditor1.setValue("readonly",true);
            this.typeEditor1.setValue("readonly",true);
            this.referenceEditor1.setValue("readonly",true);
      
            this.btnAttachment.setValue("showing",false);
            this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",false);
            this.negotiatedDeadLineEditor2.setValue("showing",false);
            this.reasonForRejectionOfTaskEditor2.setValue("showing",false);
            this.feedbackEditor1.setValue("showing",false);
            this.commentsOnFeedbackEditor1.setValue("showing",false);
            this.pnlAcceptNegoReject.setValue("showing",false);
            this.pnlApproveOrNot.setValue("showing",false);
            this.btnDelegate.setValue("showing",false);
            this.btnSaveDelegation.setValue("showing",false);
            this.btnModify.setValue("showing",true);
            if (item.parentId!=null){
              this.btnGoToParent.setValue("showing",true);
            }else{
              this.btnGoToParent.setValue("showing",false);
            }
      
            this.btnAttachment.setValue("readonly",false);
            this.reasonForNegotiatiationOfDeadLineEditor2.setValue("readonly",false);
            this.negotiatedDeadLineEditor2.setValue("readonly",false);
            this.reasonForRejectionOfTaskEditor2.setValue("readonly",false);
            this.feedbackEditor1.setValue("readonly",false);
            this.commentsOnFeedbackEditor1.setValue("readonly",false);
            this.selAcceptNegoReject.setValue("readonly",false);
            this.selApproveOrNot.setValue("readonly",false);

            this.priorityEditor1.setValue("border",0);
            this.subjectEditor2.setValue("border",0);
            this.senderEditor2.setValue("border",0);
            this.recipientEditor2.setValue("border",0);
            this.startDateEditor2.setValue("border",0);
            this.deadLineEditor2.setValue("border",0);
            this.deadlineStatusEditor1.setValue("border",0);
            this.taskEditor2.setValue("border",0);
            this.archivedEditor1.setValue("border",0);
            this.linksEditor1.setValue("border",0);
            this.statusEditor1.setValue("border",0);
            this.tagsEditor1.setValue("border",0);
            this.typeEditor1.setValue("border",0);
            this.referenceEditor1.setValue("border",0);

            this.priorityEditor1.setValue("borderColor","#F0F0F0");
            this.subjectEditor2.setValue("borderColor","#F0F0F0");
            this.senderEditor2.setValue("borderColor","#F0F0F0");
            this.recipientEditor2.setValue("borderColorg","#F0F0F0");
            this.startDateEditor2.setValue("borderColor","#F0F0F0");
            this.deadLineEditor2.setValue("borderColor","#F0F0F0");
            this.deadlineStatusEditor1.setValue("borderColor","#F0F0F0");
            this.taskEditor2.setValue("borderColor","#F0F0F0");
            this.archivedEditor1.setValue("borderColor","#F0F0F0");
            this.linksEditor1.setValue("borderColor","#F0F0F0");
            this.statusEditor1.setValue("borderColor","#F0F0F0");
            this.tagsEditor1.setValue("borderColor","#F0F0F0");
            this.typeEditor1.setValue("borderColor","#F0F0F0");
            this.referenceEditor1.setValue("borderColor","#F0F0F0");
      
            this.btnAttachment.setValue("border",0);
            this.reasonForNegotiatiationOfDeadLineEditor2.setValue("border",0);
            this.negotiatedDeadLineEditor2.setValue("border",0);
            this.reasonForRejectionOfTaskEditor2.setValue("border",0);
            this.feedbackEditor1.setValue("border",0);
            this.commentsOnFeedbackEditor1.setValue("border",0);
            this.pnlAcceptNegoReject.setValue("border",0);
            this.pnlApproveOrNot.setValue("border",0);

            this.btnAttachment.setValue("borderColor","#F0F0F0");
            this.reasonForNegotiatiationOfDeadLineEditor2.setValue("borderColor","#F0F0F0");
            this.negotiatedDeadLineEditor2.setValue("borderColor","#F0F0F0");
            this.reasonForRejectionOfTaskEditor2.setValue("borderColor","#F0F0F0");
            this.feedbackEditor1.setValue("borderColor","#F0F0F0");
            this.commentsOnFeedbackEditor1.setValue("borderColor","#F0F0F0");
            this.selAcceptNegoReject.setValue("borderColor","#F0F0F0");
            this.selApproveOrNot.setValue("borderColor","#F0F0F0");
      
            //this.feedbackEditor1.setValue('_classes','{"domNode":["wm_BackgroundColor_LightBlue"]}');
            this.feedbackEditor1.update();
            //console.log('feedback='+dojo.toJson(this.feedbackEditor1));

            //this.subjectEditor1.setValue("showing",true);
            //item = this.dataGrid1.dataSet.getItem(inIndex).getData();
            console.log('is sent='+item.type.indexOf('SENT'));
            console.log('is received='+item.type.indexOf('RECEIVED'));
            console.log('is supervised='+item.type.indexOf('SUPERVISED'));
            console.log('status='+item.status);
            console.log('type='+item.type);
            //this.ffuAttachments.setValue("uploadDirectory",item.id);
            this.ffuAttachments.setValue("showing",false);
            if (item.type.indexOf('SENT')>=0 || item.type.indexOf('SUPERVISED')>=0){
                if (item.status=='UNCONFIRMED'){
                    this.ffuAttachments.setValue("showing",true);
                }else if (item.status=='NEGOTIATED'){
                    this.ffuAttachments.setValue("showing",true);
                    this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",true);
                    this.reasonForNegotiatiationOfDeadLineEditor2.setValue("readonly",true);
                    this.negotiatedDeadLineEditor2.setValue("showing",true);
                    this.negotiatedDeadLineEditor2.setValue("border",3);
                    this.negotiatedDeadLineEditor2.setValue("borderColor","#FF0000");
                }else if (item.status=='ACCEPTED'){
                    this.ffuAttachments.setValue("showing",true);
                }else if (item.status=='DELEGATED'){
                    this.ffuAttachments.setValue("showing",true);
                }else if (item.status=='REJECTED'){
                    this.reasonForRejectionOfTaskEditor2.setValue("showing",true);
                    this.reasonForRejectionOfTaskEditor2.setValue("readonly",true);
                }else if (item.status=='DONE_UNCONFIRMED'){
                    this.ffuAttachments.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                    
                    if (item.type!='SUPERVISED'){
                        this.commentsOnFeedbackEditor1.setValue("showing",true);
                        this.pnlApproveOrNot.setValue("showing",true);
                        this.selApproveOrNot.setValue("showing",true);
                        this.commentsOnFeedbackEditor1.setValue("border",3);
                        this.commentsOnFeedbackEditor1.setValue("borderColor","#FF0000");
                        this.selApproveOrNot.setValue("border",3);
                        this.selApproveOrNot.setValue("borderColor","#FF0000");
                    }
                }else if (item.status=='DONE_CONFIRMED'){
                    this.ffuAttachments.setValue("showing",false);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                    this.commentsOnFeedbackEditor1.setValue("showing",true);
                    this.commentsOnFeedbackEditor1.setValue("readonly",true);
                    this.archivedEditor1.setValue("showing",true);
                    this.archivedEditor1.setValue("readonly",false);
                }else if (item.status=='NEED_REDO'){
                    this.ffuAttachments.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                    this.commentsOnFeedbackEditor1.setValue("showing",true);
                    this.commentsOnFeedbackEditor1.setValue("readonly",true);
           
                }else if (item.status=='NEED_REDO_DELEGATED'){
                    this.ffuAttachments.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                    this.commentsOnFeedbackEditor1.setValue("showing",true);
                    this.commentsOnFeedbackEditor1.setValue("readonly",true);
                }
            }else if (item.type.indexOf('RECEIVED')>=0 || item.type.indexOf('YOURSELF')>=0){
                if (item.status=='UNCONFIRMED'){
                    this.pnlAcceptNegoReject.setValue("showing",true);
                    this.selAcceptNegoReject.setValue("readonly",false);
                    this.reasonForNegotiatiationOfDeadLineEditor2.setValue("border",3);
                    this.reasonForNegotiatiationOfDeadLineEditor2.setValue("borderColor","#FF0000");
                    this.negotiatedDeadLineEditor2.setValue("border",3);
                    this.negotiatedDeadLineEditor2.setValue("borderColor","#FF0000");
                    this.reasonForRejectionOfTaskEditor2.setValue("border",3);
                    this.reasonForRejectionOfTaskEditor2.setValue("borderColor","#FF0000");
                }else if (item.status=='NEGOTIATED'){
                    this.ffuAttachments.setValue("showing",true);
                    this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",true);
                    this.reasonForNegotiatiationOfDeadLineEditor2.setValue("readonly",true);
                    this.negotiatedDeadLineEditor2.setValue("showing",true);
                    this.negotiatedDeadLineEditor2.setValue("readonly",true);
                }else if (item.status=='ACCEPTED'){
                    this.feedbackEditor1.setValue("border",3);
                    this.feedbackEditor1.setValue("borderColor","#FF0000");
                    this.ffuAttachments.setValue("showing",true);
                    this.btnDelegate.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",false);
                }else if (item.status=='DELEGATED'){
                    this.feedbackEditor1.setValue("border",3);
                    this.feedbackEditor1.setValue("borderColor","#FF0000");
                    this.ffuAttachments.setValue("showing",true);
                    this.btnDelegate.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",false);
                }else if (item.status=='REJECTED'){
                    this.reasonForRejectionOfTaskEditor2.setValue("showing",true);
                    this.reasonForRejectionOfTaskEditor2.setValue("readonly",true);
                }else if (item.status=='DONE_UNCONFIRMED'){
                    this.ffuAttachments.setValue("showing",true);
                    this.btnAttachment.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                   
                }else if (item.status=='DONE_CONFIRMED'){
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                    this.commentsOnFeedbackEditor1.setValue("showing",true);
                    this.commentsOnFeedbackEditor1.setValue("readonly",true);
                    this.archivedEditor1.setValue("showing",true);
                    this.archivedEditor1.setValue("readonly",false);
                }else if (item.status=='NEED_REDO'){
                    this.ffuAttachments.setValue("showing",true);
                    this.feedbackEditor1.setValue("border",3);
                    this.feedbackEditor1.setValue("borderColor","#FF0000");
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",false);
                    this.commentsOnFeedbackEditor1.setValue("showing",true);
                    this.commentsOnFeedbackEditor1.setValue("readonly",true);
           
                }else if (item.status=='NEED_REDO_DELEGATED'){
                    this.ffuAttachments.setValue("showing",true);
                    this.feedbackEditor1.setValue("showing",true);
                    this.feedbackEditor1.setValue("readonly",true);
                    this.commentsOnFeedbackEditor1.setValue("showing",true);
                    this.commentsOnFeedbackEditor1.setValue("readonly",true);
                    this.feedbackEditor1.setValue("border",3);
                    this.feedbackEditor1.setValue("borderColor","#FF0000");
                }
            }
            console.log('in current item:'+dojo.toJson(item.subject));
        }
    } catch(e) {
      console.error('ERROR IN currentItemPrepareSetData: ' + e); 
    } 
  },
  btnDelegateClick: function(inSender, inEvent) {
    try {
           var item5 = this.currentItem.getData();
           var item4 = {"archived":false,"archivedForRecipient":false,"archivedForSender":false,"children":[],"commentsOnFeedback":null,"deadLine":null,"deadlineStatus":"DEADLINE NOT SET","deletable":false,"feedback":null,"fileRepository":{"attachments":[],"description":null,"id":null,"owner":null,"tempAttachments":[],"tempAttachmentsList":[]},"id":null,"links":[],"negotiatedDeadLine":null,"notDeletable":true,"parentId":null,"priority":"NOT_SET","readState":null,"reasonForNegotiatiationOfDeadLine":null,"reasonForRejectionOfTask":null,"recipient":null,"recipients":[],"recipientsList":[],"redoCounter":0,"reference":false,"sender":null,"sentDate":null,"startDate":null,"status":null,"subject":"","supervisors":[],"tags":[],"task":null,"type":""} 
            item4.id = dojox.uuid.generateRandomUuid();
            item4.fileRepository = item5.fileRepository;
            item4.parentId = item5.id;
            item4.deadLine = item5.deadLine;
            item4.startDate= item5.startDate;
            item4.subject= 'RE:'+item5.subject;
            item4.task= 'RE:'+item5.task;
            
            this.currentItem.setData(item4);
            this.dataGrid1.selected = null;
            //console.log(dojo.toJson(this.currentItem.getData()));
            this.btnDelegate.setValue("showing",false);

            this.priorityEditor1.setValue("showing",true);
            this.recipientEditor2.setValue("showing",true);
            this.startDateEditor2.setValue("showing",true);
            this.deadLineEditor2.setValue("showing",true);
            this.taskEditor2.setValue("showing",true);
            this.linksEditor1.setValue("showing",true);           
            this.tagsEditor1.setValue("showing",true);
            this.ffuAttachments.setValue("showing",true);
            this.attachmentList.setValue("showing",true);
            this.parentIdEditor1.setValue("showing",true);
            this.parentIdEditor1.setValue("readonly",true);
            this.btnSaveDelegation.setValue("showing",true);
            this.btnModify.setValue("showing",false);
            
            this.attachmentList.setValue("showing",true);
            this.priorityEditor1.setValue("readonly",false);
            this.recipientEditor2.setValue("readonly",false);
            this.startDateEditor2.setValue("readonly",false);
            this.deadLineEditor2.setValue("readonly",false);
            this.taskEditor2.setValue("readonly",false);
            this.linksEditor1.setValue("readonly",false);           
            this.tagsEditor1.setValue("readonly",false);
            this.ffuAttachments.setValue("readonly",false);
            this.attachmentList.setValue("readonly",false);
            this.subjectEditor2.setValue("readonly",false);
            
            this.referenceEditor1.setValue("showing",false);
            this.subjectEditor2.setValue("showing",true);
            this.archivedEditor1.setValue("showing",false);
            this.senderEditor2.setValue("showing",false);
            this.statusEditor1.setValue("showing",false);
            this.deadlineStatusEditor1.setValue("showing",false);
            this.typeEditor1.setValue("showing",false);
            this.btnAttachment.setValue("showing",false);
            this.reasonForNegotiatiationOfDeadLineEditor2.setValue("showing",false);
            this.negotiatedDeadLineEditor2.setValue("showing",false);
            this.reasonForRejectionOfTaskEditor2.setValue("showing",false);
            this.feedbackEditor1.setValue("showing",false);
            this.commentsOnFeedbackEditor1.setValue("showing",false);
            this.pnlAcceptNegoReject.setValue("showing",false);
            this.pnlApproveOrNot.setValue("showing",false);
            
            this.layerSearchResult.setValue("showing",false);
            this.layerEditor.setValue("showing",true);
            this.gotoEditor.update();
      
    } catch(e) {
      console.error('ERROR IN btnDelegateClick: ' + e); 
    } 
  },
  delegateItemServVarResult: function(inSender, inData) {
    try {
         //row = this.dataGrid1.selected.itemIndex;
         //this.dataGrid1.dataSet.removeItem(row);
         //this.dataGrid1.dataSet.addItem(inData, row);
         //this.dataGrid1.select(row);
         //this.currentItem.setData(inData);
          app.pageDialog.dismiss();
         this.priorityEditor1.setValue("readonly",true);
         this.recipientEditor2.setValue("readonly",true);
         this.startDateEditor2.setValue("readonly",true);
         this.deadLineEditor2.setValue("readonly",true);
         this.taskEditor2.setValue("readonly",true);
         this.linksEditor1.setValue("readonly",true);           
         this.tagsEditor1.setValue("readonly",true);
         this.ffuAttachments.setValue("readonly",true);
         this.attachmentList.setValue("readonly",true);
         this.subjectEditor2.setValue("readonly",true);
      
      
    } catch(e) {
      console.error('ERROR IN delegateItemServVarResult: ' + e); 
    } 
  },
  dgChildrenRowDblClick: function(inSender, inEvent) {
    try {
      var childItem2 = this.currentChildItem.getData();
      if (childItem2!=null){ 
        console.log('child double click:'+dojo.toJson(childItem2.dataValue));
        this.findItemById.setValue('input.itemId',childItem2.dataValue.id);
        this.findItemById.update();
        app.pageDialog.showPage("Wait",true,350,250);  
      }
    } catch(e) {
      console.error('ERROR IN dgChildrenRowDblClick: ' + e); 
    } 
  },
  findItemByIdResult: function(inSender, inData) {
    try {
     app.pageDialog.dismiss();
    
     var item2 = inData;
     this.currentItem.setData(item2);
     
      
    } catch(e) {
      console.error('ERROR IN findItemByIdResult: ' + e); 
    } 
  },
  currentItemSetData: function(inSender) {
    try {
      
      
    } catch(e) {
      console.error('ERROR IN currentItemSetData: ' + e); 
    } 
  },
  dgChildrenSelected: function(inSender, inIndex) {
    try {
      var childItem = this.dgChildren.dataSet.getItem(inIndex).getData();  
      this.currentChildItem.setData(childItem); 
      //console.log('child item:'+dojo.toJson(childItem));
    } catch(e) {
      console.error('ERROR IN dgChildrenSelected: ' + e); 
    } 
  },
  findParentServVarResult: function(inSender, inData) {
    try {
     app.pageDialog.dismiss();
    
     var item2 = inData;
     this.currentItem.setData(item2);
      
    } catch(e) {
      console.error('ERROR IN findParentServVarResult: ' + e); 
    } 
  },
  btnGoToParentClick: function(inSender, inEvent) {
    try {
     var child = this.currentItem.getData();
     this.findParentServVar.setValue('input.itemId',child.parentId);
     this.findParentServVar.update();
     app.pageDialog.showPage("Wait",true,350,250);  
      
    } catch(e) {
      console.error('ERROR IN btnGoToParentClick: ' + e); 
    } 
  },
  toggleArchiveServVarResult: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      var items = inData;
      var count = this.dataGrid1.dataSet.getCount();
      for (i=0;i<count;i++){
        var item = this.dataGrid1.dataSet.getItem(i).getData();
        for (j=0;j<items.length;j++){
          if (items[j].id==item.id){
            this.dataGrid1.dataSet.removeItem(i);
            this.dataGrid1.dataSet.addItem(items[j], i);
            break;
          }
        }
      }
      //var item = inData;
      //row = this.dataGrid1.selected.itemIndex;
      //var items = this.dataGrid1.dataSet.getItem(row).getData();
      //for (i=0;i<items.length;i++){
      //  if (item2.id==item.id){
             //console.log('find item 2:'+dojox.json.query('$',item2))
             //this.dataGrid1.dataSet.removeItem(row);
             //this.dataGrid1.dataSet.addItem(inData, row);
             //this.dataGrid1.select(row);
      //  }
      //this.currentItem.setData(item);
      //}
    } catch(e) {
      console.error('ERROR IN toggleArchiveServVarResult: ' + e); 
    } 
  },
   btnArchiveClick: function(inSender, inEvent) {
    try {
      var rows = this.dataGrid1.dijit.selection.getSelected(); 
      var count = this.dataGrid1.dijit.selection.getSelectedCount();
      console.log('row count:'+count);
      console.log('rows:'+dojo.toJson(rows));
      //iterate over selections, add to wm.Variable varSelectedItems
      var itemIds = new Array();
      for(i = 0; i < count; i ++) {
        var index = rows[i];
        var item = this.dataGrid1.dataSet.getItem(index).getData();
        itemIds.push(item.id);
      }
      this.toggleArchiveServVar.setValue('input.itemIds',itemIds);
      this.toggleArchiveServVar.setValue('input.archive',true);
      this.toggleArchiveServVar.update(); 
      app.pageDialog.showPage("Wait",true,350,250);  
    } catch(e) {
      console.error('ERROR IN btnArchiveClick: ' + e); 
    } 
  },
  btnUnarchiveClick: function(inSender, inEvent) {
     try {
      var rows = this.dataGrid1.dijit.selection.getSelected(); 
      var count = this.dataGrid1.dijit.selection.getSelectedCount();
     
      //iterate over selections, add to wm.Variable varSelectedItems
      var itemIds = new Array();
      for(i = 0; i < count; i ++) {
        var index = rows[i];
        var item = this.dataGrid1.dataSet.getItem(index).getData();
        itemIds.push(item.id);
      }
      this.toggleArchiveServVar.setValue('input.itemIds',itemIds);
      this.toggleArchiveServVar.setValue('input.archive',false);
      this.toggleArchiveServVar.update(); 
      app.pageDialog.showPage("Wait",true,350,250);  
    } catch(e) {
      console.error('ERROR IN btnArchiveClick: ' + e); 
    } 
  },
  btnNeededAttentionClick: function(inSender, inEvent) {
    try {
    //SENT_ITEMS_NEEDED_ATTENTION
     this.searchItemsServVar.setValue('input.resultCount',10);
      this.searchItemsServVar.setValue('input.startRow',0);
      this.searchItemsServVar.setValue('input.searchInItemsNeededAttentionOnly',true);
      this.searchItemsServVar.update();
      
    } catch(e) {
      console.error('ERROR IN btnNeededAttentionClick: ' + e); 
    } 
  },
  searchItemsNeededAttentionServVarBeforeUpdate: function(inSender, ioInput) {
    try {
      app.pageDialog.showPage("Wait",true,350,250);   
      
    } catch(e) {
      console.error('ERROR IN searchItemsNeededAttentionServVarBeforeUpdate: ' + e); 
    } 
  },
  searchItemsNeededAttentionServVarResult: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      
    } catch(e) {
      console.error('ERROR IN searchItemsNeededAttentionServVarResult: ' + e); 
    } 
  },
  layerPersonShow: function(inSender) {
    try {
     app.pageDialog.showPage("Wait",true,350,250); 
     this.findCurrentPersonServVar.update();
    } catch(e) {
      console.error('ERROR IN layerPersonShow: ' + e); 
    } 
  },
  
  findCurrentPersonServVarResult: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      //console.log(dojo.toJson(inData));
      //this.lfPerson.setDataSet(inData);
      this.currentPerson.setData(inData);
    } catch(e) {
      console.error('ERROR IN findCurrentPersonServVarResult: ' + e); 
    } 
  },
  currentPersonPrepareSetData: function(inSender, inData) {
    try {
      this.usernameEditor1.setValue("readonly",true);
      this.emailWorkEditor1.setValue("readonly",true);
    } catch(e) {
      console.error('ERROR IN currentPersonPrepareSetData: ' + e); 
    } 
  },
  btnSaveUserClick: function(inSender, inEvent) {
    try {
      this.lfPerson.populateDataOutput();
      var person1 = this.lfPerson.dataOutput.getData();
      this.updatePersonServVar.setValue('input.person',person1);
      this.updatePersonServVar.update();
      app.pageDialog.showPage("Wait",true,350,250);
    } catch(e) {
      console.error('ERROR IN btnSaveUserClick: ' + e); 
    } 
  },
  updatePersonServVarResult: function(inSender, inData) {
    try {
     app.pageDialog.dismiss();
     this.currentPerson.setData(inData);
      
    } catch(e) {
      console.error('ERROR IN updatePersonServVarResult: ' + e); 
    } 
  },
  btnSavePasswordClick: function(inSender, inEvent) {
    try {
       if (this.teNewPassword.dataValue!=this.teRepeateNewPassword.dataValue){
         alert("Repeated passwords are not equal");
       }else{
         app.pageDialog.showPage("Wait",true,350,250);
         this.modifyPasswordServVar.setValue('input.oldPassword',this.teOldPassword.dataValue);
         this.modifyPasswordServVar.setValue('input.newPassword',this.teNewPassword.dataValue); 
         this.modifyPasswordServVar.update();
       }
       
    } catch(e) {
      console.error('ERROR IN btnSavePasswordClick: ' + e); 
    } 
  },
  modifyPasswordServVarResult: function(inSender, inData) {
    try {
     app.pageDialog.dismiss();
     console.log(dojo.toJson(inData));
      
    } catch(e) {
      console.error('ERROR IN modifyPasswordServVarResult: ' + e); 
    } 
  },
  btnLogoutClick: function(inSender, inEvent) {
    try {
     app.pageDialog.showPage("Wait",true,350,250); 
     this.logoutServVar.update();
      
    } catch(e) {
      console.error('ERROR IN btnLogoutClick: ' + e); 
    } 
  },
  logoutServVarResult: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      window.location.reload();
      
    } catch(e) {
      console.error('ERROR IN logoutServVarResult: ' + e); 
    } 
  },
  btnSaveDelegationClick: function(inSender, inEvent) {
    try {
      app.pageDialog.showPage("Wait",true,350,250);
      this.delegateItemServVar.update();
      
    } catch(e) {
      console.error('ERROR IN btnSaveDelegationClick: ' + e); 
    } 
  },
  _end: 0
});