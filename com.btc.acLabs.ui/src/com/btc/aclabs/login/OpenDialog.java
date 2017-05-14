package com.btc.aclabs.login;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class OpenDialog extends TitleAreaDialog {
	
	  @Inject
	    MApplication application;
	  private Button login_button;
	  private Button register_button;
	  private Button cancel_button;
	  private boolean register_pressed=false;
	  private boolean cancel_pressed=false;
	  
	public OpenDialog(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	 @Override
	    protected Control createContents(Composite parent) {
	        Control contents = super.createContents(parent);
	        setTitle("Login");
	        return contents;
	    }
	 
	    @Override
	    protected Control createDialogArea(Composite parent) {
	        Composite area = (Composite) super.createDialogArea(parent);
	 
	        return area;
	    }
	    protected void createButtonsForButtonBar(Composite parent) {
	    	login_button=createButton(parent,IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
	        login_button.setText("LogIN");
	        
	        register_button=new Button(parent,SWT.NONE);
	        register_button.setText("Register new user");
	        register_button.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					register_pressed=true;
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
	        
			cancel_button=createButton(parent,IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

			
			
		}
	 
	    @Override
	    protected Point getInitialSize() {
	        return new Point(300, 270);
	    }
	    
	 
	    public  boolean isRegisterPressed()
	    {
	    	return register_pressed;
	    }
	    
	 
}
