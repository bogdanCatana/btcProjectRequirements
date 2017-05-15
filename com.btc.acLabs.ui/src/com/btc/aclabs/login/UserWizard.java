package com.btc.aclabs.login;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;

import com.btc.acLabs.bl.services.UserService;

@Creatable
public class UserWizard extends Wizard{
	@Inject
	private UserService userService;
	protected MyPageOne one;
	protected MyPageTwo two;
	
	public UserWizard()
	{
		super();
		setNeedsProgressMonitor(true);
	}
	
	@Override
    public void addPages() {
        one = new MyPageOne();
        two= new MyPageTwo();
       addPage(one);
       addPage(two);
       
    }
	
	@Override
	public boolean performFinish() {
		System.out.println("User registered successfully");
	    userService.create(one.getUserName(), one.getPassword(),two.getFirstName(),two.getLastName(),two.getCompanyName());
        return true;
	}
	@Override
    public String getWindowTitle() {
        return "Register new user";
    }
	
}
