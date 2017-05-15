package com.btc.aclabs.login;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MyPageOne extends WizardPage{
	private Text username_text;
	private Text password_text;
    private Composite container;
    
    
	public MyPageOne() {
		super("Register new User");
		setTitle("Register new User");
        setDescription("Please complete the fields");
	}

	 @Override
	    public void createControl(Composite parent) {
	        container = new Composite(parent, SWT.NONE);
	        GridLayout layout = new GridLayout();
	        container.setLayout(layout);
	        layout.numColumns = 2;
	        Label user_label = new Label(container, SWT.NONE);
	        user_label.setText("Input a username");
	        
	        username_text = new Text(container, SWT.BORDER | SWT.SINGLE);
	        username_text.setText("");
	        username_text.addKeyListener(new KeyListener() {

	          
	            @Override
	            public void keyReleased(KeyEvent e) {
	                if (!username_text.getText().isEmpty() && !password_text.getText().isEmpty()) {
	                    setPageComplete(true);

	                }
	                else
                    	setPageComplete(false);
	            }

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

			

	        });
	        
	        Label password_label = new Label(container, SWT.NONE);
	        password_label.setText("Input a password");

	        password_text=new Text(container, SWT.BORDER | SWT.SINGLE);
	        password_text.setText("");
	        password_text.addKeyListener(new KeyListener() {

	          
	            @Override
	            public void keyReleased(KeyEvent e) {
	            	  if (!username_text.getText().isEmpty() && !password_text.getText().isEmpty()) {
		                    setPageComplete(true);

		                }
		                else
	                    	setPageComplete(false);
	            }

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

			

	        });
	        
	        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	        username_text.setLayoutData(gd);
	        password_text.setLayoutData(gd);
	        // required to avoid an error in the system
	        setControl(container);
	        setPageComplete(false);

	    }

	    public String getUserName() {
	        return username_text.getText();
	    }
	    
	    public String getPassword()
	    {
	    	return password_text.getText();
	    }
	

}
