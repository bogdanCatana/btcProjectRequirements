package com.btc.aclabs.ui.handlers;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.e4.core.di.annotations.Execute;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.services.ExcelService;
import com.btc.acLabs.bl.services.RequirementService;


public class Export_Excel_Handler {
	
    @Inject
    private RequirementService requirementService;
    @Inject
    private ExcelService excelService;
    
    @Execute
    public void execute()
    {
    	List<Requirement> list;
    	try{

    		list=requirementService.getAll();
    		if(list.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Nothing to export yet!");
    			
    		}
    		else
    		{
    			Frame frame=new Frame();
    			FileDialog fileDialog=new FileDialog(frame, "Save",FileDialog.SAVE);
    			fileDialog.setVisible(true);
    			
    			String file_path=fileDialog.getDirectory();
    			String file_name=fileDialog.getFile();
    			if(file_name==null)
    			{
    				JOptionPane.showMessageDialog(null, "Action canceled!");
    			}
    			else
    			{
    				File output=new File(file_path + "\\" + file_name + ".xlsx");
    				int res=0;
    				if(output.exists())
    				{
    					 res=JOptionPane.showConfirmDialog(null, "Do you want to overwrite?","Warning!",JOptionPane.YES_NO_OPTION);
    				}
    					if(res==0)
    					{
    						excelService.exportRequirements(output, list);
    					}
    				
    			}
    		}
	    	}catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}    	
    	
    
    }
}
