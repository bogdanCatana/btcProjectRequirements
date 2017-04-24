package com.btc.aclabs.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import com.btc.aclabs.services.excel.Excel_Export;
import com.btc.aclabs.services.excel.Excel_Interface;
import com.btc.aclabs.services.excel.Import_Excel;

public class Export_Import_Part {

	private Button export_excel;
	private Button import_excel;
	private Button export_csv;
	private Button import_csv;
	private Button export_xml;
	private Button import_xml;
	private Button export_html;
	private Button import_html;
	private Button export_json;
	private Button import_json;
	private Excel_Interface excel_exp;
	private Excel_Interface excel_imp;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		excel_exp=new Excel_Export();
		export_excel=new Button(parent,SWT.BORDER_DASH);
		export_excel.setText("Export to Excel");
		export_excel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				excel_exp.write();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		excel_imp=new Import_Excel();
		import_excel=new Button(parent,SWT.BORDER_DASH);
		import_excel.setText("Import from Excel");
		import_excel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				excel_imp.read();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		export_csv=new Button(parent,SWT.BORDER_DASH);
		export_csv.setText("Export to CSV");
		
		import_csv=new Button(parent,SWT.BORDER_DASH);
		import_csv.setText("Import from CSV");
		
		export_xml=new Button(parent, SWT.BORDER_DASH);
		export_xml.setText("Export to XML");
		
		import_xml=new Button(parent, SWT.BORDER_DASH);
		import_xml.setText("Import from XML");
		
		export_json=new Button(parent, SWT.BORDER_DASH);
		export_json.setText("Export to JSON");
		
		import_json=new Button(parent,SWT.BORDER_DASH);
		import_json.setText("Import from JSON");
		
		export_html=new Button(parent, SWT.BORDER_DASH );
		export_html.setText("Export to HTML");
		
		import_html=new Button(parent, SWT.BORDER_DASH);
		import_html.setText("Import from HTML");
	}
}
