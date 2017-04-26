package com.btc.aclabs.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.btc.aclabs.dal.PersistenceUtility;
import com.btc.aclabs.dto.Requirements;
//import com.btc.aclabs.ui.services.DetailsTextManager;
import com.btc.aclabs.ui.services.RequirementsListManager;

import org.eclipse.swt.widgets.List;

public class DisplayPart {
	//text field for searching
	private Text txtInput;
	//list for displaying the requirements
	private List listView;
	//used for layout
	private GridData gridData;
	private PersistenceUtility reqDataBase;
	private Button refresh;
	//private DetailsTextManager detailsText;
	@Inject
	private MDirtyable dirty;

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		reqDataBase= PersistenceUtility.getInstance();
		java.util.List<Requirements> fillList = reqDataBase.readAll();
		
		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Search...");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		refresh = new Button(parent, SWT.NONE);
		refresh.setLayoutData(gridData);
		refresh.setText("Refresh");
		
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
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	//private method for filling the list, also used for refreshing
	private void fillListView(java.util.List<Requirements> fillList){
		reqDataBase= PersistenceUtility.getInstance();
		fillList=reqDataBase.readAll();
		listView.removeAll();
		for(Requirements idx : fillList)
			listView.add(idx.getName());
		
	}

	@Focus
	public void setFocus() {
		txtInput.setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}

}