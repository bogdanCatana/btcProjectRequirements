package com.btc.aclabs.services.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.btc.aclabs.dto.Requirements;
import com.btc.aclabs.ui.services.RequirementsListManager;

public class Excel_Export implements Excel_Interface {
	//Blank workbook
    private XSSFWorkbook workbook = new XSSFWorkbook(); 
     
    //Create a blank sheet
    private XSSFSheet sheet = workbook.createSheet("Requirements Data");
    
    private List<Requirements> list;
    private RequirementsListManager r=RequirementsListManager.getInstance();
    
    public void write()
    {
    	
    	try{

    		list=r.getRequirementsList();
    		if(list.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Nothing to export yet!");
    			
    		}
    		else
    		{
    			String file_name=new JOptionPane().showInputDialog("Enter the file name");
	    		 FileOutputStream out=new FileOutputStream("D:\\btcProjectRequirements\\"+file_name + ".xlsx");
	    		//first row which contains labels
	    		Row detailsrow=sheet.createRow(0);
	
	    		Cell firstcell=detailsrow.createCell(0);
	    		firstcell.setCellValue("No.");
	    		
	    		Cell secondcell=detailsrow.createCell(1);
	    		secondcell.setCellValue("Name");
	    		
	    		Cell thirdcell=detailsrow.createCell(2);
	    		thirdcell.setCellValue("Short description");
	    		
	    		Cell fourthcell=detailsrow.createCell(3);
	    		fourthcell.setCellValue("Long description");
	    		
	    		Cell fifthcell=detailsrow.createCell(4);
	    		fifthcell.setCellValue("Creation Date");
	    		
	    		Cell sixthcell=detailsrow.createCell(5);
	    		sixthcell.setCellValue("Last modified Date");
	    		
	    		
	    		
	    		
	    		int rownum=1;
	    		for(int i=0;i<list.size();i++)
	    		{
	    			Row row = sheet.createRow(rownum++);
	    			int cellnum=0;
	    			
	    			Cell no_cell=row.createCell(cellnum++);
	    			no_cell.setCellValue((i+1));
	    			
	    			Cell name_cell=row.createCell(cellnum++);
	    			name_cell.setCellValue(list.get(i).getName());
	    			
	    			Cell short_description_cell=row.createCell(cellnum++);
	    			short_description_cell.setCellValue(list.get(i).getShortDescription());
	    			
	    			Cell long_description_cell=row.createCell(cellnum++);
	    			long_description_cell.setCellValue(list.get(i).getLongDescription());
	    			
	    			Cell creation_date_cell=row.createCell(cellnum++);
	    			creation_date_cell.setCellValue(list.get(i).getCreationDate().toLocaleString());
	    			
	    			Cell last_modified_date_cell=row.createCell(cellnum++);
	    			last_modified_date_cell.setCellValue(list.get(i).getLastModifiedDate().toLocaleString());
	    		}
	    		workbook.write(out);
	    		out.close();
    		}	
	    		
	    	}catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}    	
    	
    
    }
    public void read(){};
}
