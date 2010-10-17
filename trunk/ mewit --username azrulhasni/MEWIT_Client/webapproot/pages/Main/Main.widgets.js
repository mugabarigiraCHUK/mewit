Main.widgets = {
	searchItemsServVar: ["wm.ServiceVariable", {"service":"SearchItemsService","operation":"doSearch"}, {"onBeforeUpdate":"searchItemsServVarBeforeUpdate","onResult":"searchItemsServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doSearchInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire2: ["wm.Wire", {"targetProperty":"searchInSupervisedItems","source":"cbeSearchInSupervisedItems.dataValue"}, {}],
				wire3: ["wm.Wire", {"targetProperty":"searchTerm","source":"searchBox.dataValue"}, {}],
				wire: ["wm.Wire", {"targetProperty":"searchInReceivedItems","source":"cbeSearchInReceivedItems.dataValue"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"searchInSentItems","source":"cbeSearchInSentItems.dataValue"}, {}],
				wire4: ["wm.Wire", {"targetProperty":"searchArchived","source":"cbeSearchInArchivedItems.dataValue"}, {}]
			}]
		}]
	}],
	modifyItemServVar: ["wm.ServiceVariable", {"service":"ModifyItemService","operation":"doModify"}, {"onSuccess":"modifyItemServVarSuccess"}, {
		input: ["wm.ServiceInput", {"type":"doModifyInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"currentItem","source":"dataGrid1.selectedItem"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"archived","source":"archivedEditor1.dataValue"}, {}],
				wire2: ["wm.Wire", {"targetProperty":"commentsOnFeedback","source":"commentsOnFeedbackEditor1.dataValue"}, {}],
				wire3: ["wm.Wire", {"targetProperty":"deadline","source":"deadLineEditor2.dataValue"}, {}],
				wire4: ["wm.Wire", {"targetProperty":"feedback","source":"feedbackEditor1.dataValue"}, {}],
				wire5: ["wm.Wire", {"targetProperty":"itemId","source":"idEditor1.dataValue"}, {}],
				wire6: ["wm.Wire", {"targetProperty":"negotiatedDeadline","source":"negotiatedDeadLineEditor2.dataValue"}, {}],
				wire7: ["wm.Wire", {"targetProperty":"priority","source":"priorityEditor1.dataValue"}, {}],
				wire8: ["wm.Wire", {"targetProperty":"reasonForNegotiation","source":"reasonForNegotiatiationOfDeadLineEditor2.dataValue"}, {}],
				wire9: ["wm.Wire", {"targetProperty":"reasonForRejection","source":"reasonForRejectionOfTaskEditor2.dataValue"}, {}],
				wire10: ["wm.Wire", {"targetProperty":"recipient","source":"recipientEditor2.dataValue"}, {}],
				wire11: ["wm.Wire", {"targetProperty":"reference","source":"referenceEditor1.dataValue"}, {}],
				wire12: ["wm.Wire", {"targetProperty":"sender","source":"senderEditor2.dataValue"}, {}],
				wire13: ["wm.Wire", {"targetProperty":"startDate","source":"startDateEditor2.dataValue"}, {}],
				wire14: ["wm.Wire", {"targetProperty":"status","source":"statusEditor1.dataValue"}, {}],
				wire15: ["wm.Wire", {"targetProperty":"subject","source":"subjectEditor2.dataValue"}, {}],
				wire16: ["wm.Wire", {"targetProperty":"task","source":"taskEditor2.dataValue"}, {}],
				wire17: ["wm.Wire", {"targetProperty":"type","source":"typeEditor1.dataValue"}, {}],
				wire19: ["wm.Wire", {"targetProperty":"confirmedUnconfirmed","source":"selApproveOrNot.dataValue"}, {}],
				wire20: ["wm.Wire", {"targetProperty":"sessionId","source":"sessionId"}, {}],
				wire21: ["wm.Wire", {"targetProperty":"links","source":"linksEditor1.dataValue"}, {}],
				wire22: ["wm.Wire", {"targetProperty":"tags","source":"tagsEditor1.dataValue"}, {}],
				wire23: ["wm.Wire", {"targetProperty":"fileRepository","source":"currentItem.fileRepository"}, {}],
				wire18: ["wm.Wire", {"targetProperty":"acceptNegoReject","source":"selAcceptNegoReject.dataValue"}, {}],
				wire24: ["wm.Wire", {"targetProperty":"parentId","source":"parentIdEditor1.dataValue"}, {}]
			}]
		}]
	}],
	setCurrentItemIdServVar: ["wm.ServiceVariable", {"service":"Session","operation":"setCurrentSelectedItem"}, {}, {
		input: ["wm.ServiceInput", {"type":"setCurrentSelectedItemInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"dataGrid1.selectedItem.id"}, {}]
			}]
		}]
	}],
	fileUploadServVar: ["wm.ServiceVariable", {"service":"FileServices","operation":"doUpload"}, {}, {
		input: ["wm.ServiceInput", {"type":"doUploadInputs"}, {}]
	}],
	addAttachmentServVar: ["wm.ServiceVariable", {"service":"UpdateItemWithAttachment","operation":"doAddAttachment"}, {"onSuccess":"addAttachmentServVarSuccess","onBeforeUpdate":"addAttachmentServVarBeforeUpdate","onResult":"addAttachmentServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doAddAttachmentInputs"}, {}]
	}],
	doDownloadServVar: ["wm.ServiceVariable", {"service":"FileServices","operation":"doDownloadFile"}, {}, {
		input: ["wm.ServiceInput", {"type":"doDownloadFileInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"attachment","source":"attachmentList.selectedItem"}, {}]
			}]
		}]
	}],
	acceptItemServVar: ["wm.ServiceVariable", {"service":"AcceptItem","operation":"doAccept"}, {"onSuccess":"acceptItemServVarSuccess","onBeforeUpdate":"acceptItemServVarBeforeUpdate"}, {
		input: ["wm.ServiceInput", {"type":"doAcceptInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"idEditor1.dataValue"}, {}]
			}]
		}]
	}],
	delegateItemServVar: ["wm.ServiceVariable", {"service":"DelegateItem","operation":"doDelegate"}, {"onResult":"delegateItemServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doDelegateInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"deadline","source":"deadLineEditor2.dataValue"}, {}],
				wire2: ["wm.Wire", {"targetProperty":"startDate","source":"startDateEditor2.dataValue"}, {}],
				wire3: ["wm.Wire", {"targetProperty":"fileRepository","source":"currentItem.fileRepository"}, {}],
				wire4: ["wm.Wire", {"targetProperty":"links","source":"linksEditor1.dataValue"}, {}],
				wire5: ["wm.Wire", {"targetProperty":"tags","source":"tagsEditor1.dataValue"}, {}],
				wire7: ["wm.Wire", {"targetProperty":"task","source":"taskEditor2.dataValue"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"parentId","source":"parentIdEditor1.dataValue"}, {}],
				wire6: ["wm.Wire", {"targetProperty":"targets","source":"recipientEditor2.dataValue"}, {}],
				wire8: ["wm.Wire", {"targetProperty":"subject","source":"subjectEditor2.dataValue"}, {}]
			}]
		}]
	}],
	respondNegotiatedDeadlineServVar: ["wm.ServiceVariable", {"service":"RespondNegotiatedDeadline","operation":"doRespondNegotiateDeadline"}, {"onBeforeUpdate":"respondNegotiatedDeadlineServVarBeforeUpdate","onSuccess":"respondNegotiatedDeadlineServVarSuccess"}, {
		input: ["wm.ServiceInput", {"type":"doRespondNegotiateDeadlineInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"idEditor1.dataValue"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"newDeadline","source":"negotiatedDeadLineEditor2.dataValue"}, {}]
			}]
		}]
	}],
	giveCommentsOnFeedbackServVar: ["wm.ServiceVariable", {"service":"GiveCommentsOnFeedback","operation":"doGiveCommentsOnFeedback"}, {"onSuccess":"giveCommentsOnFeedbackServVarSuccess","onBeforeUpdate":"giveCommentsOnFeedbackServVarBeforeUpdate"}, {
		input: ["wm.ServiceInput", {"type":"doGiveCommentsOnFeedbackInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"formCurrentItem.dataOutput.id"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"comments","source":"commentsOnFeedbackEditor1.dataValue"}, {}]
			}]
		}]
	}],
	giveFeedbackServVar: ["wm.ServiceVariable", {"service":"GiveFeedback","operation":"doGiveFeedback"}, {"onSuccess":"giveFeedbackServVarSuccess","onBeforeUpdate":"giveFeedbackServVarBeforeUpdate"}, {
		input: ["wm.ServiceInput", {"type":"doGiveFeedbackInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire1: ["wm.Wire", {"targetProperty":"feedback","source":"feedbackEditor1.dataValue"}, {}],
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"idEditor1.dataValue"}, {}]
			}]
		}]
	}],
	negotiateDeadlineServVar: ["wm.ServiceVariable", {"service":"NegotiateDeadline","operation":"doNegotiateDeadline"}, {"onSuccess":"negotiateDeadlineServVarSuccess","onBeforeUpdate":"negotiateDeadlineServVarBeforeUpdate"}, {
		input: ["wm.ServiceInput", {"type":"doNegotiateDeadlineInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"idEditor1.dataValue"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"newDeadline","source":"negotiatedDeadLineEditor2.dataValue"}, {}],
				wire2: ["wm.Wire", {"targetProperty":"reason","source":"reasonForNegotiatiationOfDeadLineEditor2.dataValue"}, {}]
			}]
		}]
	}],
	rejectItemServVar: ["wm.ServiceVariable", {"service":"RejectItem","operation":"doReject"}, {"onSuccess":"rejectItemServVarSuccess","onBeforeUpdate":"rejectItemServVarBeforeUpdate"}, {
		input: ["wm.ServiceInput", {"type":"doRejectInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"idEditor1.dataValue"}, {}],
				wire1: ["wm.Wire", {"targetProperty":"reason","source":"reasonForRejectionOfTaskEditor2.dataValue"}, {}]
			}]
		}]
	}],
	sessionId: ["wm.ServiceVariable", {"service":"Session","operation":"getCurrentSessionId"}, {"onResult":"sessionIdResult"}, {
		input: ["wm.ServiceInput", {"type":"getCurrentSessionIdInputs"}, {}]
	}],
	currentItem: ["wm.Variable", {"type":"org.azrul.epice.domain.Item"}, {"onSetData":"currentItemSetData","onPrepareSetData":"currentItemPrepareSetData"}],
	gotoSearch: ["wm.NavigationCall", {}, {}, {
		input: ["wm.ServiceInput", {"type":"gotoLayerInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"layer","source":"layerSearchResult"}, {}]
			}]
		}]
	}],
	gotoEditor: ["wm.NavigationCall", {}, {}, {
		input: ["wm.ServiceInput", {"type":"gotoLayerInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"layer","source":"layerEditor"}, {}]
			}]
		}]
	}],
	findItemById: ["wm.ServiceVariable", {"service":"SearchItemsService","operation":"findById"}, {"onResult":"findItemByIdResult"}, {
		input: ["wm.ServiceInput", {"type":"findByIdInputs"}, {}]
	}],
	currentChildItem: ["wm.Variable", {"type":"wm.Variable"}, {}],
	findParentServVar: ["wm.ServiceVariable", {"service":"SearchItemsService","operation":"findById"}, {"onResult":"findParentServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"findByIdInputs"}, {}]
	}],
	toggleArchiveServVar: ["wm.ServiceVariable", {"service":"ToggleArchiveService","operation":"doService"}, {"onResult":"toggleArchiveServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doServiceInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"itemId","source":"currentItem.id"}, {}]
			}]
		}]
	}],
	findUserServVar: ["wm.ServiceVariable", {"service":"FindUserService","operation":"doFindPerson"}, {"onResult":"findUserServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doFindPersonInputs"}, {}]
	}],
	currentPerson: ["wm.Variable", {"type":"org.azrul.epice.domain.Person"}, {"onPrepareSetData":"currentPersonPrepareSetData"}],
	findCurrentPersonServVar: ["wm.ServiceVariable", {"service":"FindUserService","operation":"doFindCurrentPerson"}, {"onResult":"findCurrentPersonServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doFindCurrentPersonInputs"}, {}]
	}],
	updatePersonServVar: ["wm.ServiceVariable", {"service":"UpdatePersonService","operation":"doUpdatePerson"}, {"onResult":"updatePersonServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doUpdatePersonInputs"}, {}]
	}],
	modifyPasswordServVar: ["wm.ServiceVariable", {"service":"ModifyPasswordService","operation":"doModifyPassword"}, {"onResult":"modifyPasswordServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"doModifyPasswordInputs"}, {}]
	}],
	gotoLogin: ["wm.NavigationCall", {"operation":"gotoPage"}, {}, {
		input: ["wm.ServiceInput", {"type":"gotoPageInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"pageName","expression":"\"Login\""}, {}]
			}]
		}]
	}],
	logoutServVar: ["wm.ServiceVariable", {"service":"LogoutService","operation":"invalidateSession"}, {"onResult":"logoutServVarResult"}, {
		input: ["wm.ServiceInput", {"type":"invalidateSessionInputs"}, {}]
	}],
	gotoEdit: ["wm.NavigationCall", {}, {}, {
		input: ["wm.ServiceInput", {"type":"gotoLayerInputs"}, {}, {
			binding: ["wm.Binding", {}, {}, {
				wire: ["wm.Wire", {"targetProperty":"layer","source":"layerEditor"}, {}]
			}]
		}]
	}],
	layoutBox1: ["wm.Layout", {"height":"100%","width":"674px","horizontalAlign":"left","verticalAlign":"top"}, {}, {
		TabbedTwoCol: ["wm.Template", {"width":"100%","height":"100%","verticalAlign":"top","horizontalAlign":"left"}, {}, {
			panel10: ["wm.Panel", {"width":"100%","height":"40px","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
				lbWelcome: ["wm.Label", {"height":"28px","width":"63px","caption":"Welcome "}, {}, {
					format: ["wm.DataFormatter", {}, {}]
				}],
				lbUsername: ["wm.Label", {"height":"28px","width":"115px","caption":undefined}, {}, {
					format: ["wm.DataFormatter", {}, {}],
					binding: ["wm.Binding", {}, {}, {
						wire: ["wm.Wire", {"targetProperty":"caption","source":"currentPerson.firstName"}, {}]
					}]
				}],
				btnLogout: ["wm.Button", {"height":"28px","width":"96px","caption":"Logout"}, {"onclick":"btnLogoutClick"}],
				label2: ["wm.Label", {"_classes":{"domNode":["wm_FontSizePx_28px","wm_FontColor_LightBlue","wm_FontFamily_Courier","wm_TextAlign_Right"]},"height":"47px","width":"537px","caption":"MEWIT"}, {}, {
					format: ["wm.DataFormatter", {}, {}]
				}]
			}],
			tabLayers1: ["wm.TabLayers", {}, {}, {
				layerSearchResult: ["wm.Layer", {"caption":"Search","width":"885px","layoutKind":"left-to-right"}, {"onShow":"sessionId"}, {
					splitter1: ["wm.Splitter", {"height":"100%","width":"4px"}, {}],
					panel2: ["wm.Panel", {"_classes":{"domNode":["wm_SilverBlueTheme_MainInsetPanel"]},"width":"100%"}, {}, {
						panel4: ["wm.Panel", {"_classes":{"domNode":["wm_BackgroundColor_White"]},"width":"100%","height":"100%","layoutKind":"left-to-right"}, {}, {
							SearchListDetail: ["wm.Template", {"width":"100%","height":"100%","verticalAlign":"top","horizontalAlign":"left"}, {}, {
								panel1: ["wm.Panel", {"_classes":{"domNode":["wm_SilverBlueTheme_ToolBar"]},"width":"100%","height":"30px","layoutKind":"left-to-right"}, {}, {
									searchBox: ["wm.Editor", {"width":"254px","height":"8px","displayValue":"Apple","margin":"4","padding":"0"}, {}, {
										editor: ["wm._TextEditor", {}, {}]
									}],
									searchBtn: ["wm.Button", {"height":"100%","width":"55px","caption":"Search"}, {"onclick":"searchBtnClick"}],
									btnNeededAttention: ["wm.Button", {"height":"48px","width":"233px","caption":"Search in items needed attention"}, {"onclick":"btnNeededAttentionClick"}],
									btnNewItem: ["wm.Button", {"height":"48px","width":"96px","caption":"Create new item"}, {"onclick":"btnNewItemClick"}],
									btnArchive: ["wm.Button", {"height":"48px","width":"81px","caption":"Archive"}, {"onclick":"btnArchiveClick"}],
									btnUnarchive: ["wm.Button", {"height":"48px","width":"81px","caption":"Unarchive"}, {"onclick":"btnUnarchiveClick"}]
								}],
								panel6: ["wm.Panel", {"width":"100%","height":"40px","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
									seJumpToPage: ["wm.SelectEditor", {"width":"185px","caption":"Jump to page","captionSize":"90%"}, {"onchange":"seJumpToPageChange"}, {
										editor: ["wm._SelectEditor", {"displayField":"data","dataField":"display"}, {}, {
											binding: ["wm.Binding", {}, {}, {
												wire: ["wm.Wire", {"targetProperty":"dataSet","source":"searchItemsServVar.pages"}, {}]
											}]
										}]
									}],
									splitter3: ["wm.Splitter", {"height":"100%","width":"4px"}, {}],
									cbeSearchInReceivedItems: ["wm.CheckBoxEditor", {"_classes":{"domNode":["wm_Border_StyleFirefoxCurved8px"]},"height":"45px","width":"124px","captionAlign":"left","captionSize":"400%","caption":"Search in received items","singleLine":false,"padding":"0"}, {}, {
										editor: ["wm._CheckBoxEditor", {"startChecked":true}, {}]
									}],
									splitter4: ["wm.Splitter", {"height":"100%","width":"4px"}, {}],
									cbeSearchInSentItems: ["wm.CheckBoxEditor", {"_classes":{"domNode":["wm_Border_StyleFirefoxCurved8px"]},"height":"52px","width":"128px","captionAlign":"left","captionSize":"400%","caption":"Search in sent items","singleLine":false}, {}, {
										editor: ["wm._CheckBoxEditor", {"startChecked":true}, {}]
									}],
									userIdentity: ["wm.TextEditor", {"caption":"userId","showing":false}, {}, {
										editor: ["wm._TextEditor", {}, {}]
									}],
									splitter5: ["wm.Splitter", {"height":"100%","width":"4px"}, {}],
									cbeSearchInSupervisedItems: ["wm.CheckBoxEditor", {"_classes":{"domNode":["wm_Border_StyleFirefoxCurved8px"]},"height":"52px","width":"146px","captionAlign":"left","captionSize":"400%","caption":"Search in supervised items","singleLine":false}, {}, {
										editor: ["wm._CheckBoxEditor", {}, {}]
									}],
									splitter6: ["wm.Splitter", {"height":"100%","width":"4px"}, {}],
									cbeSearchInArchivedItems: ["wm.CheckBoxEditor", {"_classes":{"domNode":["wm_Border_StyleFirefoxCurved8px"]},"height":"52px","width":"146px","captionAlign":"left","captionSize":"400%","caption":"Search in archived items","singleLine":false,"emptyValue":"false"}, {}, {
										editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
									}]
								}],
								splitter2: ["wm.Splitter", {"height":"4px","width":"100%"}, {}],
								dataGrid1: ["wm.DataGrid", {"height":"630px"}, {"onSelected":"onDataGridSelected","onSelectionChanged":"onDataGridSelectionChanged","onRowDblClick":"dataGrid1RowDblClick"}, {
									startDate1: ["wm.DataGridColumn", {"index":3,"caption":"Start Date","display":"Date","field":"startDate","autoSize":true}, {}, {
										format: ["wm.DateFormatter", {"datePattern":"yyyy-MM-dd"}, {}]
									}],
									sender1: ["wm.DataGridColumn", {"index":1,"caption":"Sender","field":"sender","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									deadlineStatus1: ["wm.DataGridColumn", {"index":7,"caption":"Deadline Watch","field":"deadlineStatus","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									priority1: ["wm.DataGridColumn", {"caption":"Priority","field":"priority","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									deadLine1: ["wm.DataGridColumn", {"index":4,"caption":"Deadline","display":"Date","field":"deadLine","autoSize":true}, {}, {
										format: ["wm.DateFormatter", {"datePattern":"yyyy-MM-dd"}, {}]
									}],
									recipient1: ["wm.DataGridColumn", {"index":2,"caption":"Recipient","field":"recipient","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									type1: ["wm.DataGridColumn", {"index":8,"caption":"Type","field":"type","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									status1: ["wm.DataGridColumn", {"index":6,"caption":"Status","field":"status","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									archived1: ["wm.DataGridColumn", {"index":9,"caption":"Archived","field":"archived","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									subject1: ["wm.DataGridColumn", {"index":5,"caption":"Subject","field":"subject","autoSize":true}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									binding: ["wm.Binding", {}, {}, {
										wire: ["wm.Wire", {"targetProperty":"dataSet","source":"searchItemsServVar.items"}, {}]
									}]
								}]
							}]
						}]
					}]
				}],
				layerEditor: ["wm.Layer", {"caption":"Edit/View","width":"833px"}, {}, {
					panel9: ["wm.Panel", {"width":"100%","height":"532px","horizontalAlign":"left","verticalAlign":"top"}, {}, {
						panel3: ["wm.Panel", {"width":"100%","height":"28px","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
							btnModify: ["wm.Button", {"height":"29px","width":"96px","caption":"Save"}, {"onclick":"btnModifyClick"}],
							btnAttachment: ["wm.Button", {"height":"29px","width":"151px","caption":"See attachments"}, {}],
							btnDelegate: ["wm.Button", {"height":"29px","width":"96px","caption":"Delegate"}, {"onclick":"btnDelegateClick"}],
							btnGoToSearch: ["wm.Button", {"height":"29px","width":"96px","caption":"Go To Search"}, {"onclick":"btnGoToSearchClick"}],
							btnSaveDelegation: ["wm.Button", {"height":"29px","width":"134px","caption":"Save delegation"}, {"onclick":"btnSaveDelegationClick"}],
							btnGoToParent: ["wm.Button", {"height":"29px","width":"96px","caption":"Go to parent"}, {"onclick":"btnGoToParentClick"}]
						}],
						panel5: ["wm.Panel", {"width":"100%","height":"100%","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
							formCurrentItem: ["wm.LiveForm", {"height":"630px","horizontalAlign":"center","verticalAlign":"top","fitToContent":true,"editorWidth":"70%","width":"644px"}, {}, {
								binding: ["wm.Binding", {}, {}, {
									wire: ["wm.Wire", {"targetProperty":"dataSet","source":"currentItem"}, {}]
								}],
								priorityEditor1: ["wm.Editor", {"width":"70%","caption":"Priority","height":"26px","formField":"priority","display":"Select"}, {}, {
									editor: ["wm._SelectEditor", {"displayField":"name","dataField":"dataValue","options":"NOT_SET,HIGH,MEDIUM,LOW,LONGTERM"}, {}, {
										optionsVar: ["wm.Variable", {"type":"EntryData"}, {}]
									}]
								}],
								subjectEditor2: ["wm.Editor", {"width":"70%","caption":"Subject","height":"26px","formField":"subject"}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								senderEditor2: ["wm.Editor", {"width":"70%","caption":"Sender","height":"26px","formField":"sender","readonly":true}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								recipientEditor2: ["wm.Editor", {"width":"70%","caption":"Recipient","height":"26px","formField":"recipient","readonly":true}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								startDateEditor2: ["wm.Editor", {"width":"70%","caption":"StartDate","height":"26px","formField":"startDate","display":"Date"}, {}, {
									editor: ["wm._DateEditor", {"required":true}, {}]
								}],
								deadLineEditor2: ["wm.Editor", {"width":"70%","caption":"DeadLine","height":"26px","formField":"deadLine","display":"Date"}, {}, {
									editor: ["wm._DateEditor", {"required":true}, {}]
								}],
								deadlineStatusEditor1: ["wm.Editor", {"width":"70%","caption":"DeadlineStatus","height":"26px","formField":"deadlineStatus","readonly":true}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								pnlAcceptNegoReject: ["wm.Panel", {"width":"456px","height":"28px","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
									selAcceptNegoReject: ["wm.SelectEditor", {"width":"452px","caption":"Accept"}, {"onchange":"selAcceptNegoRejectChange"}, {
										editor: ["wm._SelectEditor", {"displayField":"name","dataField":"dataValue","options":"Accept, Negotiate, Reject","required":true}, {}, {
											optionsVar: ["wm.Variable", {"type":"EntryData"}, {}]
										}]
									}]
								}],
								negotiatedDeadLineEditor2: ["wm.Editor", {"width":"70%","caption":"NegotiatedDeadLine","height":"26px","formField":"negotiatedDeadLine","display":"Date"}, {}, {
									editor: ["wm._DateEditor", {}, {}]
								}],
								reasonForNegotiatiationOfDeadLineEditor2: ["wm.Editor", {"width":"70%","caption":"ReasonForNegotiatiationOfDeadLine","height":"26px","formField":"reasonForNegotiatiationOfDeadLine"}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								reasonForRejectionOfTaskEditor2: ["wm.Editor", {"width":"70%","caption":"ReasonForRejectionOfTask","height":"26px","formField":"reasonForRejectionOfTask"}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								taskEditor2: ["wm.Editor", {"_classes":{"domNode":["wm_Border_StyleFirefoxCurved8px"]},"width":"70%","caption":"Task","height":"52px","formField":"task","singleLine":false,"readonly":true}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								feedbackEditor1: ["wm.Editor", {"width":"70%","caption":"Feedback","height":"52px","formField":"feedback","singleLine":false}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								pnlApproveOrNot: ["wm.Panel", {"width":"455px","height":"28px","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
									selApproveOrNot: ["wm.SelectEditor", {"width":"450px","caption":"Approved"}, {"onchange":"selAcceptNegoRejectChange"}, {
										editor: ["wm._SelectEditor", {"displayField":"name","dataField":"dataValue","options":"Approved, Not Approved","required":true}, {}, {
											optionsVar: ["wm.Variable", {"type":"EntryData"}, {}]
										}]
									}]
								}],
								commentsOnFeedbackEditor1: ["wm.Editor", {"width":"70%","caption":"CommentsOnFeedback","height":"52px","formField":"commentsOnFeedback","singleLine":false}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								linksEditor1: ["wm.Editor", {"width":"70%","caption":"Links","height":"26px","formField":"links"}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								statusEditor1: ["wm.Editor", {"width":"70%","caption":"Status","height":"26px","formField":"status","readonly":true}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								tagsEditor1: ["wm.Editor", {"width":"70%","caption":"Tags","height":"26px","formField":"tags"}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								typeEditor1: ["wm.Editor", {"width":"70%","caption":"Type","height":"26px","formField":"type","readonly":true}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								archivedEditor1: ["wm.Editor", {"width":"70%","caption":"Archived","height":"26px","formField":"archived","displayValue":true,"emptyValue":"false","display":"CheckBox"}, {}, {
									editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
								}],
								referenceEditor1: ["wm.Editor", {"width":"70%","caption":"Reference","height":"26px","formField":"reference","displayValue":true,"emptyValue":"false","display":"CheckBox","readonly":true}, {}, {
									editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
								}],
								archivedForRecipientEditor1: ["wm.Editor", {"width":"70%","caption":"ArchivedForRecipient","height":"26px","formField":"archivedForRecipient","displayValue":true,"emptyValue":"false","display":"CheckBox","readonly":true,"showing":false}, {}, {
									editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
								}],
								archivedForSenderEditor1: ["wm.Editor", {"width":"70%","caption":"ArchivedForSender","height":"26px","formField":"archivedForSender","displayValue":true,"emptyValue":"false","display":"CheckBox","readonly":true,"showing":false}, {}, {
									editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
								}],
								childrenEditor1: ["wm.Editor", {"width":"70%","caption":"Children","height":"26px","formField":"children","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								deletableEditor1: ["wm.Editor", {"width":"70%","caption":"Deletable","height":"26px","formField":"deletable","displayValue":true,"emptyValue":"false","display":"CheckBox","readonly":true,"showing":false}, {}, {
									editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
								}],
								fileRepositoryEditor1: ["wm.Editor", {"width":"70%","caption":"FileRepository","height":"26px","formField":"fileRepository","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								notDeletableEditor1: ["wm.Editor", {"width":"70%","caption":"NotDeletable","height":"26px","formField":"notDeletable","displayValue":true,"emptyValue":"false","display":"CheckBox","readonly":true,"showing":false}, {}, {
									editor: ["wm._CheckBoxEditor", {"dataType":"boolean"}, {}]
								}],
								parentIdEditor1: ["wm.Editor", {"width":"70%","caption":"ParentId","height":"26px","formField":"parentId","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								readStateEditor1: ["wm.Editor", {"width":"70%","caption":"ReadState","height":"26px","formField":"readState","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								recipientsEditor1: ["wm.Editor", {"width":"70%","caption":"Recipients","height":"26px","formField":"recipients","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}],
								recipientsListEditor1: ["wm.Editor", {"width":"70%","caption":"RecipientsList","height":"26px","formField":"recipientsList","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								redoCounterEditor1: ["wm.Editor", {"width":"70%","caption":"RedoCounter","height":"26px","formField":"redoCounter","display":"Number","readonly":true,"showing":false}, {}, {
									editor: ["wm._NumberEditor", {"required":true}, {}]
								}],
								sentDateEditor1: ["wm.Editor", {"width":"70%","caption":"SentDate","height":"26px","formField":"sentDate","display":"Date","readonly":true,"showing":false}, {}, {
									editor: ["wm._DateEditor", {"required":true}, {}]
								}],
								supervisorsEditor1: ["wm.Editor", {"width":"70%","caption":"Supervisors","height":"26px","formField":"supervisors","readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {}, {}]
								}],
								idEditor1: ["wm.Editor", {"width":"70%","caption":"Id","height":"26px","formField":"id","disabled":true,"readonly":true,"showing":false}, {}, {
									editor: ["wm._TextEditor", {"required":true}, {}]
								}]
							}],
							panel7: ["wm.Panel", {"width":"344px","height":"630px","horizontalAlign":"left","verticalAlign":"top"}, {}, {
								ffuAttachments: ["wm.custom.FancyFileUpload", {"border":"1","borderColor":"#99BBE8","uploadDirectory":"C:/Documents and Settings/azrulm/DB4ODatabase/temp","width":"305px","height":"99px","headerText":"Attachments","service":"FileServices","fitToContent":true}, {"onComplete":"ffuAttachmentsComplete"}, {

								}],
								attachmentList: ["wm.List", {"_classes":{"domNode":["wm_SilverBlueTheme_LightBlueInsetPanel","wm_Border_StyleFirefoxCurved4px"]},"height":"127px","width":"307px","dataFields":"fileName,owner","scrollY":true}, {"ondblclick":"attachmentListDblclick"}, {
									binding: ["wm.Binding", {}, {}, {
										wire: ["wm.Wire", {"targetProperty":"dataSet","source":"currentItem.fileRepository.attachments"}, {}]
									}]
								}],
								lbChildren: ["wm.Label", {"height":"48px","width":"96px","caption":"Children Items"}, {}, {
									format: ["wm.DataFormatter", {}, {}]
								}],
								dgChildren: ["wm.DataGrid", {"_classes":{"domNode":["wm_BackgroundChromeBar_SteelBlue","wm_Border_StyleFirefoxCurved8px"]},"border":"1","width":"307px"}, {"onRowDblClick":"dgChildrenRowDblClick","onSelected":"dgChildrenSelected"}, {
									binding: ["wm.Binding", {}, {}, {
										wire: ["wm.Wire", {"targetProperty":"dataSet","source":"currentItem.children"}, {}]
									}],
									status1: ["wm.DataGridColumn", {"index":1,"caption":"status","field":"status","columnWidth":"71px"}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}],
									recipient1: ["wm.DataGridColumn", {"caption":"recipient","field":"recipient"}, {}, {
										format: ["wm.DataFormatter", {}, {}]
									}]
								}]
							}]
						}]
					}]
				}],
				layerPerson: ["wm.Layer", {"caption":"Profile","horizontalAlign":"left","verticalAlign":"top"}, {"onShow":"layerPersonShow"}, {
					lfPerson: ["wm.LiveForm", {"height":"215px","horizontalAlign":"left","verticalAlign":"top","fitToContent":true,"width":"672px"}, {}, {
						binding: ["wm.Binding", {}, {}, {
							wire: ["wm.Wire", {"targetProperty":"dataSet","expression":undefined,"source":"currentPerson"}, {}]
						}],
						panel8: ["wm.Panel", {"width":"100%","height":"31px","horizontalAlign":"left","verticalAlign":"top","layoutKind":"left-to-right"}, {}, {
							btnSaveUser: ["wm.Button", {"height":"25px","width":"96px","caption":"Save"}, {"onclick":"btnSaveUserClick"}]
						}],
						usernameEditor1: ["wm.Editor", {"width":"100%","caption":"Login name","height":"26px","formField":"username","readonly":true}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}],
						cellPhoneWorkEditor1: ["wm.Editor", {"width":"100%","caption":"Cell phone number","height":"26px","formField":"cellPhoneWork"}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}],
						emailWorkEditor1: ["wm.Editor", {"width":"100%","caption":"Email","height":"26px","formField":"emailWork","readonly":true}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}],
						firstNameEditor1: ["wm.Editor", {"width":"100%","caption":"First name","height":"26px","formField":"firstName"}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}],
						lastNameEditor1: ["wm.Editor", {"width":"100%","caption":"Last name","height":"26px","formField":"lastName"}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}],
						organizationEditor1: ["wm.Editor", {"width":"100%","caption":"Organization","height":"26px","formField":"organization"}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}],
						potsPhoneWorkEditor1: ["wm.Editor", {"width":"100%","caption":"Landline phone number ","height":"26px","formField":"potsPhoneWork"}, {}, {
							editor: ["wm._TextEditor", {"required":true}, {}]
						}]
					}]
				}],
				layerPassword: ["wm.Layer", {"caption":"Password","horizontalAlign":"left","verticalAlign":"top"}, {}, {
					btnSavePassword: ["wm.Button", {"height":"28px","width":"67px","caption":"Save"}, {"onclick":"btnSavePasswordClick"}],
					teOldPassword: ["wm.TextEditor", {"width":"449px","caption":"Current Password"}, {}, {
						editor: ["wm._TextEditor", {"required":true,"password":true}, {}]
					}],
					teNewPassword: ["wm.TextEditor", {"width":"449px","caption":"New Password"}, {}, {
						editor: ["wm._TextEditor", {"required":true,"password":true}, {}]
					}],
					teRepeateNewPassword: ["wm.TextEditor", {"width":"449px","caption":"Repeate new password"}, {}, {
						editor: ["wm._TextEditor", {"required":true,"password":true}, {}]
					}]
				}]
			}]
		}]
	}]
}