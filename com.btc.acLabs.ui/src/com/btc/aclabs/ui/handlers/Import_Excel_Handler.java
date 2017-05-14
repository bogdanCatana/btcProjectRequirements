package com.btc.aclabs.ui.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.MessageBox;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.dmos.RequirementImpl;
import com.btc.acLabs.bl.services.RequirementService;

public class Import_Excel_Handler {
	@Inject
	private RequirementService p;
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
				if (p.getAll().isEmpty() == false) {

					int option = JOptionPane.showConfirmDialog(null, "Do you want to overwrite the existing DataBase?",
							"Warning!", JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == 0)
						p.clearDataBase();
					if (option == 2)
						return;
					if (option == JOptionPane.CLOSED_OPTION)
						return;

				}

				// Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);

				// Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheetAt(0);
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					cellIterator.next();
					String name = "", short_description = "", long_description = "", creation_date = "",
							last_modified_date = "";
					String parent = null;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						int columnIndex = cell.getColumnIndex();
						switch (columnIndex) {
						case 1:
							name = cell.getStringCellValue();
							break;
						case 2:
							short_description = cell.getStringCellValue();
							break;
						case 3:
							long_description = cell.getStringCellValue();
							break;
						case 4:
							creation_date = cell.getStringCellValue();
							break;
						case 5:
							last_modified_date = cell.getStringCellValue();
							break;
						case 6:
							parent = cell.getStringCellValue();
							break;
						}

					}
					if (!alreadyExists(name)) {
						Requirement tmp = new RequirementImpl(name, short_description, long_description, creation_date,
								last_modified_date);
						p.create(tmp);
						if (parent != null && parent != "") {
							tmp.setIsChild(true);
							p.updateRequirement(tmp);
							setRelation(parent, tmp.getId());
						}
					} else {
						sameName = true;
					}
					
				}
				file.close();
				workbook.close();
				
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

	private void setRelation(String parent, int id) {
		for (Requirement req : p.getAll())
			if (req.getName().equals(parent)) {
				req.setChild(id);
				p.updateRequirement(req);
			}
	}

	public boolean alreadyExists(String n) {
		for (Requirement req : p.getAll())
			if (req.getName().equals(n))
				return true;
		return false;
	}

}
