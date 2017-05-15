package com.btc.aclabs.ui.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.btc.acLabs.bl.services.ExcelService;
import com.btc.acLabs.bl.services.RequirementService;

public class Import_Excel_Handler {

	@Inject
	private RequirementService requirementService;
	@Inject
	private ExcelService excelService;

	private FileInputStream file;
	private boolean sameName = false;
	private MessageBox messageBox;

	@Execute
	public void execute(Shell shell) {

		try {
			FileDialog dialog = new FileDialog(shell);
			String[] extensions = { "*.xlsx" };
			dialog.setFilterExtensions(extensions);
			dialog.open();
			String file_path = dialog.getFilterPath();
			String file_name = dialog.getFileName();
			if (file_name == "") {
				JOptionPane.showMessageDialog(null, "Action canceled!");
			} else {
				File f = new File(file_path + "\\" + file_name);
				if (f.exists() == false) {
					JOptionPane.showMessageDialog(null, "File does not exist!", "ERROR", 0, null);
					return;
				}
				if (f.canWrite() == true)
					file = new FileInputStream(f);
				else {
					JOptionPane.showMessageDialog(null, "File is read-only!", "ERROR", 0, null);
					return;
				}
				if (requirementService.getAll().isEmpty() == false) {

					int option = JOptionPane.showConfirmDialog(null, "Do you want to overwrite the existing DataBase?",
							"Warning!", JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == 0)
						requirementService.clearDataBase();
					if (option == 2)
						return;
					if (option == JOptionPane.CLOSED_OPTION)
						return;

				}

				sameName = excelService.importRequirements(file);
				
				if (sameName == true) {
					messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
					messageBox.setText("Warning");
					messageBox.setMessage("One or more requirements have been already added!");
					messageBox.open();
					sameName = false;
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
