package com.btc.aclabs.login;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginDialog extends TitleAreaDialog{
	private Text textUsername;
    private Text textPassword;
    private String user;
    private String pass;
    private Button loginButton;
 
    @Inject
    MApplication application;
 
    public LoginDialog(Shell parentShell) {
        super(parentShell);
    }
 
    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        setTitle("Login");
        setMessage("Please provide credentials");
        return contents;
    }
 
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
 
        Composite container = new Composite(area, SWT.NULL);
        container.setLayout(new GridLayout(2, false));
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
 
        new Label(container, SWT.NULL).setText("Username");
        textUsername = new Text(container, SWT.BORDER);
        textUsername.setMessage("Enter username...");
        textUsername.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
 
 
        new Label(container, SWT.NULL).setText("Password");
        textPassword = new Text(container, SWT.PASSWORD | SWT.BORDER);
        textPassword.setMessage("Enter password...");
        textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        textUsername.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				user=textUsername.getText();
				if(textUsername.getText().equals("") || textPassword.getText().equals(""))
					loginButton.setEnabled(false);
				else
					loginButton.setEnabled(true);
				
				
			}
		});
        textPassword.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				pass=textPassword.getText();
				if(textPassword.getText().equals("") || textUsername.getText().equals(""))
					loginButton.setEnabled(false);
				else
					loginButton.setEnabled(true);
				
			}
		});
 
        return area;
    }
    
    protected void createButtonsForButtonBar(Composite parent) {
		loginButton=createButton(parent, IDialogConstants.OK_ID, "Login", true);
        loginButton.setEnabled(false);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		
	}
 
    @Override
    protected Point getInitialSize() {
        return new Point(500, 300);
    }
    
    public String getUser()
    {
    	return user;
    }
    public String getPass()
    {
    	return pass;
    }
    
  
 
}
