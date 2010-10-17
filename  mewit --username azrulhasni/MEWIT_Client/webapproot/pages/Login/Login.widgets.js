Login.widgets = {
	gotoMain: ["wm.NavigationCall", {"operation":"gotoPage"}, {"onSuccess":"gotoMain"}, {
		input: ["wm.ServiceInput", {"type":"gotoPageInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"pageName","expression":"\"Main\""}, {}]
			}]
		}]
	}],
	loginServVar: ["wm.ServiceVariable", {"service":"LoginService","operation":"doLogin"}, {"onSuccess":"gotoMain","onError":"loginServVarError","onResult":"loginServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doLoginInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"password","source":"tePassword.dataValue"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"username","source":"teUsername.dataValue"}, {}]
			}]
		}]
	}],
	retrievePasswordServVar: ["wm.ServiceVariable", {"service":"RetrievePasswordService","operation":"doRetrievePassword"}, {"onResult":"retrievePasswordServVarResult","onSuccess":"retrievePasswordServVarSuccess"}, {
		input: ["wm.ServiceInput", {"type":"doRetrievePasswordInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"email","source":"teEmailLostPassword.dataValue"}, {}]
			}]
		}]
	}],
	layoutBox1: ["wm.Layout", {"height":"100%","width":"100%","horizontalAlign":"left","verticalAlign":"middle"}, {}, {
		panel2: ["wm.Panel", {"width":"100%","height":"272px","horizontalAlign":"center","verticalAlign":"top"}, {}, {
			label2: ["wm.Label", {"_classes":{"domNode":["wm_FontSizePx_28px","wm_FontColor_LightBlue","wm_FontFamily_Courier"]},"height":"47px","width":"236px","caption":"MEWIT"}, {}, {
				format: ["wm.DataFormatter", {}, {}]
			}],
			panel1: ["wm.Panel", {"_classes":{"domNode":["wm_BackgroundChromeBar_LightBlue"]},"width":"232px","height":"109px","horizontalAlign":"center","verticalAlign":"middle"}, {}, {
				teUsername: ["wm.TextEditor", {"width":"233px","caption":"User name","displayValue":"ahmad.ali@company.com"}, {}, {
					editor: ["wm._TextEditor", {}, {}]
				}],
				tePassword: ["wm.TextEditor", {"width":"233px","caption":"Password","displayValue":"abc123"}, {}, {
					editor: ["wm._TextEditor", {"password":true}, {}]
				}],
				btnLogin: ["wm.Button", {"height":"24px","width":"69px","caption":"Login"}, {"onclick":"btnLoginClick"}]
			}],
			panel3: ["wm.Panel", {"_classes":{"domNode":["wm_BackgroundChromeBar_LightBlue"]},"width":"232px","height":"100%","horizontalAlign":"center","verticalAlign":"top"}, {}, {
				splitter1: ["wm.Splitter", {"height":"4px","width":"100%"}, {}],
				label1: ["wm.Label", {"height":"41px","width":"230px","caption":"Lost your password? Please enter your email. We'll supply  a new password","singleLine":false}, {}, {
					format: ["wm.DataFormatter", {}, {}]
				}],
				teEmailLostPassword: ["wm.TextEditor", {"width":"229px","height":"23px","caption":"Email"}, {}, {
					editor: ["wm._TextEditor", {}, {}]
				}],
				btnGetNewPassword: ["wm.Button", {"height":"24px","width":"137px","caption":"Get new password"}, {"onclick":"btnGetNewPasswordClick"}]
			}]
		}]
	}]
}