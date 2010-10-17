Wait.widgets = {
	layoutBox1: ["wm.Layout", {"verticalAlign":"middle","width":"100%","height":"100%","horizontalAlign":"left"}, {}, {
		panel2: ["wm.Panel", {"height":"48px","layoutKind":"left-to-right","horizontalAlign":"left","verticalAlign":"top","width":"100%"}, {}],
		panel1: ["wm.Panel", {"height":"48px","layoutKind":"left-to-right","horizontalAlign":"left","verticalAlign":"top","width":"100%"}, {}, {
			picture1: ["wm.Picture", {"source":"resources/images/imagelists/loading.gif","width":"100%","height":"100%"}, {}, {
				binding: ["wm.Binding", {}, {}, {
					wire: ["wm.Wire", {"targetProperty":"source","expression":"\"resources/images/imagelists/loading.gif\""}, {}]
				}]
			}]
		}],
		panel3: ["wm.Panel", {"height":"48px","layoutKind":"left-to-right","horizontalAlign":"center","verticalAlign":"top","width":"100%"}, {}, {
			label1: ["wm.Label", {"width":"96px","height":"48px","caption":"Please wait ..."}, {}, {
				format: ["wm.DataFormatter", {}, {}]
			}]
		}]
	}]
}