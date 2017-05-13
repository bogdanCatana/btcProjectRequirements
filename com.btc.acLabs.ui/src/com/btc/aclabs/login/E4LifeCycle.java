package com.btc.aclabs.login;

import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.btc.acLabs.bl.dmos.User;
import com.btc.acLabs.bl.services.UserService;

@SuppressWarnings("restriction")
public class E4LifeCycle {
	@Inject
	private UserService userService;
	private List<User> usersList;

	@PostContextCreate
	void postContextCreate(IEclipseContext workbenchContext) {
	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
	}

	@ProcessAdditions
	void processAdditions(IEclipseContext workbenchContext) {
		
	        final Shell shell = new Shell(SWT.TOOL|SWT.NO_TRIM);
	        LoginDialog dialog = new LoginDialog(shell);
			if(userService.getAll().isEmpty())
				userService.create("admin", "admin");
			 usersList =userService.getAll();
	        ContextInjectionFactory.inject(dialog, workbenchContext);
	        boolean ok=true;
	        int x=0;
	    
	        if(dialog.open() != Window.OK){
	            // close application
	            System.exit(0);
	        }
	        else
	        {
	        	
	        
	        	 while(ok!=false)
	 	        {
		 	        ok=true;
		 	    
		 	        for(User i:usersList)
		 	        	if(dialog.getUser().equals(i.getName())==true && dialog.getPass().equals(i.getPassword())==true){
		 	        		
		 	        		ok=false;
		 	        	break;
		 	        		
		 	        		
		 	        	}
		 	       if(ok==true)
		 	       {
		 	    	  JOptionPane.showMessageDialog(null, "Try again!", "Login Failed!", 0);
		 	    	  x=dialog.open();
		 	       }
		 	        	
		 	        		
	 		    if(x!=Window.OK)
	 		    	System.exit(0);
	 	        }
	        }
	       
	       

	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
	}
}
