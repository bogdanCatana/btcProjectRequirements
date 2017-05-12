package com.btc.aclabs.ui.parts;

import java.awt.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;



import org.eclipse.swt.widgets.List;

public class DisplayPart {
	//text field for searching
	private Text txtInput;
	//list for displaying the requirements
	private List listView;
	//used for layout
	private GridData gridData;
	private Button refresh;
	private Button deleteReqButton;
	//private DetailsTextManager detailsText;
	@Inject
	private MDirtyable dirty;
	@Inject
	private RequirementRepository reqDataBase;
	private java.util.List<Requirement> fillList;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
	    fillList = reqDataBase.getAll();
		
		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Search...");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				//dirty.setDirty(true);
				listView.removeAll();
				for(Requirement i:reqDataBase.getAll())
				{
					if(i.getName().contains(txtInput.getText())){
					
					listView.add(i.getName());
					
					}
				}
				
				if(txtInput.getText().equals(""))
					fillListView(fillList);
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		refresh = new Button(parent, SWT.NONE);
		refresh.setLayoutData(gridData);
		refresh.setText("Refresh");
		
		deleteReqButton=new Button(parent,SWT.NONE);
		deleteReqButton.setText("Delete");
		deleteReqButton.setEnabled(false);
		
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		listView = new List(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		listView.setLayoutData(gridData);
		
		fillListView(fillList);
		/* 
		 * this should not be the only way to update the list because on the very long
		 * ones this is inefficient
		 */
		refresh.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				fillListView(fillList);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		deleteReqButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
					deleteReq();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listView.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				//get the index of the selected requirement and then using get for obtaining
				//the specified requirement
				/*detailsText = DetailsTextManager.getInstanceArgFree();
				int idx = listView.getSelectionIndex();
				Requirements aux = fillList.get(idx);
				detailsText.setText(aux.toString());
				*/
				if(listView.isSelected(listView.getSelectionIndex())==true){
					
					deleteReqButton.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listView.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(listView.isSelected(listView.getSelectionIndex())==true){
					
					if(e.count==2)
					{
						JTextField name_field=new JTextField();
						name_field.setEditable(true);
						JTextField short_description_field=new JTextField();
						short_description_field.setEditable(true);
						JTextField long_description_field=new JTextField();
						long_description_field.setEditable(true);
						Object[] req_edit={
								"Name: ",name_field,
								"Short Description",short_description_field,
								"Long Description",long_description_field
						};
						 int result = JOptionPane.showConfirmDialog(null, req_edit, "EDIT", JOptionPane.OK_CANCEL_OPTION);
						 String req_name=listView.getItem(listView.getSelectionIndex());
						 
						 if(result == JOptionPane.OK_OPTION)
						 {
							 Requirement editable=null;
							 fillList=reqDataBase.getAll();
							 for(Requirement i:fillList)
									if(req_name.equals(i.getName()))
										editable=i;
							editable.setName(name_field.getText());
							editable.setShortDescription(short_description_field.getText());
							editable.setLongDescription(long_description_field.getText());
							editable.setLastModifiedDate();
							reqDataBase.updateRequirement(editable);
							fillListView(fillList);
							 
						 }
					}
				}
				
			}
		});
	}
	
	//private method for filling the list, also used for refreshing
	private void fillListView(java.util.List<Requirement> fillList){
		//reqDataBase= PersistenceUtility.getInstance();
		fillList=reqDataBase.getAll();
		listView.removeAll();
		for(Requirement idx : fillList){
			listView.add(idx.getName());
		}
		
		deleteReqButton.setEnabled(false);
	}
	private void deleteReq()
	{
		fillList=reqDataBase.getAll();
		String name=listView.getItem(listView.getSelectionIndex());
		for(Requirement i:fillList)
			if(name.equals(i.getName()))
				reqDataBase.deleteRequirement(i);	
		fillListView(fillList);
		deleteReqButton.setEnabled(false);
		txtInput.setText("");
	}



	@Persist
	public void save() {
		dirty.setDirty(false);
	}

}