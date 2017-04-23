package com.btc.aclabs.ui.services;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
/*
 * another singleton class, it should be used for displaying details,
 * but this functionality is not yet implemented because I didn' figured out
 * a good solution :))
 */
public class DetailsTextManager {
	private Text detailsText;
	private static DetailsTextManager instance; 
	
	private DetailsTextManager(Composite parent, int style){
		detailsText = new Text(parent, style);
	};
	
	public static synchronized DetailsTextManager getInstance(Composite parent, int style){
		if(instance == null)
			instance = new DetailsTextManager(parent, style);
		return instance;
	}
	
	public static synchronized DetailsTextManager getInstanceArgFree(){
		return instance;
	}
	
	public void setText(String text){
		detailsText.setText(text);
	}
}
