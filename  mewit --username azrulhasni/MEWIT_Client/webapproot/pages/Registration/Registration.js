dojo.declare("Registration", wm.Page, {
  start: function() {
    app.pageDialog.showPage("Wait",true,350,250);
    var param_UN = 'username';
    var regexS_UN = "[\\?&]"+param_UN.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]")+"=([^&#]*)";
    var regex_UN = new RegExp( regexS_UN );
    var username = regex_UN.exec( window.location.href );
    
    var param_key = 'key';
    var regexS_key = "[\\?&]"+param_key.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]")+"=([^&#]*)";
    var regex_key = new RegExp( regexS_key );
    var key = regex_key.exec( window.location.href );
    
    this.loginServVar.setValue('input.username',username[1]);
    this.loginServVar.setValue('input.password',key[1]);
    this.loginServVar.update();
  },
  loginServVarSuccess: function(inSender, inData) {
    try {
      app.pageDialog.dismiss();
      this.usernameEditor1.setValue('readonly',true);
      this.emailWorkEditor1.setValue('readonly',true);
    } catch(e) {
      console.error('ERROR IN loginServVarSuccess: ' + e); 
    } 
  },
  loginServVarError: function(inSender, inError) {
    try {
      app.pageDialog.dismiss();
      this.liveForm1.setValue('showing',false);
      this.btnSaveRegistration.setValue('showing',false);
      alert("Sorry, you do not have access to this page");
    } catch(e) {
      console.error('ERROR IN loginServVarError: ' + e); 
    } 
  },
  updatePersonServVarSuccess: function(inSender, inData) {
    try {
     alert("Update successful");
      
    } catch(e) {
      console.error('ERROR IN updatePersonServVarSuccess: ' + e); 
    } 
  },
  btnSaveRegistrationClick: function(inSender, inEvent) {
    try {
     if (this.passwordEditor1.dataValue!=this.repeatePasswordEditor1.dataValue){
         alert("Repeated passwords are not equal");
     }else{
         this.liveForm1.populateDataOutput();
         var person1 = this.liveForm1.dataOutput.getData();
         console.log('person='+dojo.toJson(person1));
         this.updatePersonServVar.setValue('input.person',person1);
         this.updatePersonServVar.update();
     }
    } catch(e) {
      console.error('ERROR IN btnSaveRegistrationClick: ' + e); 
    } 
  },
  _end: 0
});