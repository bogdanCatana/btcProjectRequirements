package com.btc.acLabs.bl.internal.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.dmos.RequirementImpl;
import com.btc.acLabs.bl.services.ExcelService;
import com.btc.acLabs.bl.services.RequirementFactory;
import com.btc.acLabs.bl.services.RequirementService;

@Component
public class ExcelServiceImpl implements ExcelService {

	private RequirementService requirementService;
	private RequirementFactory requirementFactory;
	
	public void exportRequirements(File output, List<Requirement> requirements) throws IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		
		XSSFSheet sheet = workbook.createSheet("Requirements Data");
		
		FileOutputStream out= new FileOutputStream(output);
		
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
		
		Cell seventhcell=detailsrow.createCell(6);
		seventhcell.setCellValue("Parent");
		
		
		int rownum=1;
		for(int i=0;i<requirements.size();i++)
		{
			Row row = sheet.createRow(rownum++);
			int cellnum=0;
			
			Cell no_cell=row.createCell(cellnum++);
			no_cell.setCellValue((i+1));
			
			Cell name_cell=row.createCell(cellnum++);
			name_cell.setCellValue(requirements.get(i).getName());
			
			Cell short_description_cell=row.createCell(cellnum++);
			short_description_cell.setCellValue(requirements.get(i).getShortDescription());
			
			Cell long_description_cell=row.createCell(cellnum++);
			long_description_cell.setCellValue(requirements.get(i).getLongDescription());
			
			Cell creation_date_cell=row.createCell(cellnum++);
			creation_date_cell.setCellValue(requirements.get(i).getCreationDate().toLocaleString());
			
			Cell last_modified_date_cell=row.createCell(cellnum++);
			last_modified_date_cell.setCellValue(requirements.get(i).getLastModifiedDate().toLocaleString());
							    			
			Cell parent_cell=row.createCell(cellnum++);
			for(Requirement idxReq : requirements)
				for(Integer idxChild : idxReq.getChilds())
					if(requirements.get(i).getId() == idxChild)
							parent_cell.setCellValue(idxReq.getName());
		}
		workbook.write(out);
		out.close();
		workbook.close();
	}
	
	public boolean importRequirements(FileInputStream file) throws IOException {
		
		boolean sameName = false; 
		
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
				Requirement tmp = requirementFactory.create(name, short_description, long_description, creation_date,
						last_modified_date);
				requirementService.create(tmp);
				if (parent != null && parent != "") {
					tmp.setIsChild(true);
					requirementService.updateRequirement(tmp);
					setRelation(parent, tmp.getId());
				}
			} else {
				sameName = true;
			}
			
		}
		file.close();
		workbook.close();
		
		return sameName;
	}
	
	private void setRelation(String parent, int id) {
		for (Requirement req : requirementService.getAll())
			if (req.getName().equals(parent)) {
				req.setChild(id);
				requirementService.updateRequirement(req);
			}
	}

	private boolean alreadyExists(String n) {
		for (Requirement req : requirementService.getAll())
			if (req.getName().equals(n))
				return true;
		return false;
	}
	
	@Reference
	public void setRequirementService(RequirementService service) {
		this.requirementService = service;
	}
	
	@Reference
	public void setRequirementFactory(RequirementFactory factory) {
		this.requirementFactory = factory;
	}
}
