package com.btc.aclabs.ui.parts;


import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.btc.aclabs.ui.services.DetailsTextManager;

public class DetailsPart {
	private GridData gridData;
	private DetailsTextManager detailsText;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		//detailsText = DetailsTextManager.getInstance(parent, SWT.BORDER);
		
		
	}
}
