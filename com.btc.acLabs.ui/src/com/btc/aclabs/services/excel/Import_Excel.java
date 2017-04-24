package com.btc.aclabs.services.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.btc.aclabs.dto.Requirements;
import com.btc.aclabs.ui.services.RequirementsListManager;

public class Import_Excel {
	
    private RequirementsListManager r=RequirementsListManager.getInstance();
    @Execute
	public void execute(Shell shell) {
	
		try{
			FileDialog dialog = new FileDialog(shell);
			String [] extensions={"*.xlsx"};
			dialog.setFilterExtensions(extensions);
			dialog.open();
			String file_path=dialog.getFilterPath();
			String file_name=dialog.getFileName();
			if(file_name=="")
			{
				JOptionPane.showMessageDialog(null, "Action canceled!");
			}
			else
			{
			if(r.getRequirementsList().isEmpty()==false)
			{
				int option=JOptionPane.showConfirmDialog(null,"Do you want to overwrite the existing list?","Warning!",JOptionPane.YES_NO_OPTION);
				if(option==0)
					r.clearList();
					
			}
			
			FileInputStream file = new FileInputStream(file_path + "\\" + file_name);
			
			
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
          //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                cellIterator.next();
                String name="",short_description="",long_description="",creation_date="",last_modified_date="";
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    int columnIndex=cell.getColumnIndex();
                    switch (columnIndex) {
                    case 1:
                        name=cell.getStringCellValue();
                        break;
                    case 2:
                        short_description=cell.getStringCellValue();
                        break;
                    case 3:
                        long_description=cell.getStringCellValue();
                        break;
                    case 4:
                    	creation_date=cell.getStringCellValue();
                    	break;
                    case 5:
                    	last_modified_date=cell.getStringCellValue();
                    	break;
                    }
                    	
                }
                   Requirements tmp=new Requirements(name,short_description,long_description);
                   tmp.setLastModifiedDate(last_modified_date);
                   tmp.setCreationDate(creation_date);
                   r.addRequirement(tmp);
                            
              }
            file.close();
              }
		
            
		  }catch(IOException e)
			{
			e.printStackTrace();
			}
		}
	
	
}
