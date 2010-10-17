dojo.declare("Login", wm.Page, {
  start: function() {
    
  },
  loginServVarError: function(inSender, inError) {
    try {
      alert("Sorry, wrong password");
    } catch(e) {
      console.error('ERROR IN loginServVarError: ' + e); 
    } 
  },
 
  loginServVarSuccess: function(inSender, inData) {
    try {
     
      
    } catch(e) {
      console.error('ERROR IN loginServVarSuccess: ' + e); 
    } 
  },
  retrievePasswordServVarResult: function(inSender, inData) {
    try {
       app.pageDialog.dismiss();
      alert("Password successfully sent to your email");
      
    } catch(e) {
      console.error('ERROR IN retrievePasswordServVarResult: ' + e); 
    } 
  },
  btnLoginClick: function(inSender, inEvent) {
    try {
     app.pageDialog.showPage("Wait",true,350,250);  
     this.loginServVar.update();
      
      
    } catch(e) {
      console.error('ERROR IN btnLoginClick: ' + e); 
    } 
  },
  loginServVarResult: function(inSender, inData) {
    try {
     app.pageDialog.dismiss();
      
    } catch(e) {
      console.error('ERROR IN loginServVarResult: ' + e); 
    } 
  },
  btnGetNewPasswordClick: function(inSender, inEvent) {
    try {
      app.pageDialog.showPage("Wait",true,350,250);  
      this.retrievePasswordServVar.update();
      
    } catch(e) {
      console.error('ERROR IN btnGetNewPasswordClick: ' + e); 
    } 
  },
  retrievePasswordServVarSuccess: function(inSender, inData) {
    try {
     
      
    } catch(e) {
      console.error('ERROR IN retrievePasswordServVarSuccess: ' + e); 
    } 
  },
  _end: 0
});