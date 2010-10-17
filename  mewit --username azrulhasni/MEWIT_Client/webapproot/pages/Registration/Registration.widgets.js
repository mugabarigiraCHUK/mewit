Registration.widgets = {
	loginServVar: ["wm.ServiceVariable", {"service":"LoginService","operation":"doLogin"}, {"onSuccess":"loginServVarSuccess","onError":"loginServVarError"}, {
		input: ["wm.ServiceInput", {"type":"doLoginInputs"}, {}]
	}],
	updatePersonServVar: ["wm.ServiceVariable", {"service":"UpdatePersonService","operation":"doUpdatePerson"}, {"onSuccess":"updatePersonServVarSuccess"}, {
		input: ["wm.ServiceInput", {"type":"doUpdatePersonInputs"}, {}]
	}],
	layoutBox1: ["wm.Layout", {"height":"100%","width":"100%","horizontalAlign":"left","verticalAlign":"top"}, {}, {
		label1: ["wm.Label", {"height":"48px","width":"96px","caption":"Registration"}, {}, {
			format: ["wm.DataFormatter", {}, {}]
		}],
		btnSaveRegistration: ["wm.Button", {"height":"29px","width":"95px","caption":"Save"}, {"onclick":"btnSaveRegistrationClick"}],
		liveForm1: ["wm.LiveForm", {"height":"236px","horizontalAlign":"left","verticalAlign":"top","fitToContent":true}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"dataSet","expression":undefined,"source":"loginServVar"}, {}]
			}],
			usernameEditor1: ["wm.Editor", {"width":"70%","caption":"Username","height":"26px","formField":"username","readonly":true}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}],
			passwordEditor1: ["wm.Editor", {"width":"70%","caption":"Password","height":"26px","formField":"password"}, {}, {
				editor: ["wm._TextEditor", {"password":true,"required":true}, {}]
			}],
			repeatePasswordEditor1: ["wm.Editor", {"width":"70%","caption":"Repeate pasword","height":"26px"}, {}, {
				editor: ["wm._TextEditor", {"password":true,"required":true}, {}]
			}],
			emailWorkEditor1: ["wm.Editor", {"width":"70%","caption":"Email","height":"26px","formField":"emailWork","readonly":true}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}],
			firstNameEditor1: ["wm.Editor", {"width":"70%","caption":"First name","height":"26px","formField":"firstName"}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}],
			lastNameEditor1: ["wm.Editor", {"width":"70%","caption":"Last name","height":"26px","formField":"lastName"}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}],
			organizationEditor1: ["wm.Editor", {"width":"70%","caption":"Organization","height":"26px","formField":"organization"}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}],
			cellPhoneWorkEditor1: ["wm.Editor", {"width":"70%","caption":"Cell phone number","height":"26px","formField":"cellPhoneWork"}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}],
			potsPhoneWorkEditor1: ["wm.Editor", {"width":"70%","caption":"Phone number (Work)","height":"26px","formField":"potsPhoneWork"}, {}, {
				editor: ["wm._TextEditor", {"required":true}, {}]
			}]
		}]
	}]
}