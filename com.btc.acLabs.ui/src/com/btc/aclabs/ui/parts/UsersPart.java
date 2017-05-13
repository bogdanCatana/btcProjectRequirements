package com.btc.aclabs.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.btc.acLabs.bl.services.UserService;

public class UsersPart {

	private Text name;
	private Text pass;
	private Label name_label;
	private Label pass_label;
	private Button add_button;
	@Inject
	private UserService userService;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		
		parent.setLayout(new GridLayout(2, true));
		name_label = new Label(parent, SWT.NONE);
		name_label.setText("User: ");
		
		name = new Text(parent, SWT.BORDER);
		
		pass_label=new Label(parent,SWT.NONE);
		pass_label.setText("Pass");
		
		pass=new Text(parent,SWT.BORDER);
		
		add_button=new Button(parent,SWT.NONE);
		add_button.setText("Add");
		
		add_button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				userService.create(name.getText(), pass.getText());
				name.setText("");
				pass.setText("");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
