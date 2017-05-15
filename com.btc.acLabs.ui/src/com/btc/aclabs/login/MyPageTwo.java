package com.btc.aclabs.login;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MyPageTwo extends WizardPage {
	private Text firstName_text;
	private Text lastName_text;
	private Text companyName_text;
    private Composite container;

    public MyPageTwo() {
        super("Register new User");
        setTitle("Personal details");
        setDescription("Please complete the fields with your personal info");
        setControl(firstName_text);
    }

    @Override
    public void createControl(Composite parent) {
        container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 2;
        Label firstName_label = new Label(container, SWT.NONE);
        firstName_label.setText("Input your First Name");

        firstName_text = new Text(container, SWT.BORDER | SWT.SINGLE);
        firstName_text.setText("");
        firstName_text.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!firstName_text.getText().isEmpty() && !lastName_text.getText().isEmpty() && !companyName_text.getText().isEmpty()) {
                    setPageComplete(true);
                }
                else
                	setPageComplete(false);
            }

			

        });
        
        Label lastName_label = new Label(container, SWT.NONE);
        lastName_label.setText("Input your Last Name");
        
        lastName_text = new Text(container, SWT.BORDER | SWT.SINGLE);
        lastName_text.setText("");
        lastName_text.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyReleased(KeyEvent e) {
            	  if (!firstName_text.getText().isEmpty() && !lastName_text.getText().isEmpty() && !companyName_text.getText().isEmpty()) {
                      setPageComplete(true);
                  }
                  else
                  	setPageComplete(false);
            }

			

        });
        
        Label companyName_label = new Label(container, SWT.NONE);
        companyName_label.setText("Input the Company Name");
        
        companyName_text = new Text(container, SWT.BORDER | SWT.SINGLE);
        companyName_text.setText("");
        companyName_text.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyReleased(KeyEvent e) {
            	  if (!firstName_text.getText().isEmpty() && !lastName_text.getText().isEmpty() && !companyName_text.getText().isEmpty()) {
                      setPageComplete(true);
                  }
                  else
                  	setPageComplete(false);
            }

			

        });
        
        
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        firstName_text.setLayoutData(gd);
        lastName_text.setLayoutData(gd);
        companyName_text.setLayoutData(gd);
       
        // required to avoid an error in the system
        setControl(container);
        setPageComplete(false);
    }

    public String getFirstName() {
        return firstName_text.getText();
    }
    
    public String getLastName()
    {
    	return lastName_text.getText();
    }
    
    public String getCompanyName()
    {
    	return companyName_text.getText();
    }

	
}
