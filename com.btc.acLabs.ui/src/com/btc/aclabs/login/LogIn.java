package com.btc.aclabs.login;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;


public class LogIn{
	@PostContextCreate
    void postContextCreate(IEclipseContext workbenchContext) {
    }
 
    /** ... */
 
    @ProcessAdditions
    void processAdditions(IEclipseContext workbenchContext) {
        final Shell shell = new Shell(SWT.TOOL|SWT.NO_TRIM);
        LoginDialog dialog = new LoginDialog(shell);
        ContextInjectionFactory.inject(dialog, workbenchContext);
        if(dialog.open() != Window.OK){
            // close application
            System.exit(-1);
        }
        
        // continue to the initial window
    }
}