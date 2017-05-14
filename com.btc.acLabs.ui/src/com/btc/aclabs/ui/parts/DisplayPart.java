package com.btc.aclabs.ui.parts;


import java.util.ConcurrentModificationException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
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
import com.btc.acLabs.bl.services.RequirementService;

import org.eclipse.swt.widgets.List;

public class DisplayPart {
	// text field for searching
	private Text txtInput;
	// list for displaying the requirements
	private List listView;
	// used for layout
	private GridData gridData;
	private Button refresh;
	private Button deleteReqButton;
	// private DetailsTextManager detailsText;
	@Inject
	private MDirtyable dirty;
	@Inject
	private RequirementService reqDataBase;
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
				// dirty.setDirty(true);
				listView.removeAll();
				for (Requirement i : reqDataBase.getAll()) {
					if (i.getName().contains(txtInput.getText())) {

						listView.add(i.getName());

					}
				}

				if (txtInput.getText().equals(""))
					fillListView();
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		refresh = new Button(parent, SWT.NONE);
		refresh.setLayoutData(gridData);
		refresh.setText("Refresh");

		deleteReqButton = new Button(parent, SWT.NONE);
		deleteReqButton.setText("Delete");
		deleteReqButton.setEnabled(false);

		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		listView = new List(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		listView.setLayoutData(gridData);

		fillListView();
		/*
		 * this should not be the only way to update the list because on the
		 * very long ones this is inefficient
		 */
		refresh.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				fillListView();
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
				if (listView.isSelected(listView.getSelectionIndex()) == true) {

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
				if (listView.isSelected(listView.getSelectionIndex()) == true) {

					if (e.count == 2) {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnsupportedLookAndFeelException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JTextField name_field = new JTextField();
						name_field.setEditable(true);
						JTextField short_description_field = new JTextField();
						short_description_field.setEditable(true);
						JTextField long_description_field = new JTextField();
						long_description_field.setEditable(true);
						Object[] req_edit = { "Name: ", name_field, "Short Description", short_description_field,
								"Long Description", long_description_field };
						int result = JOptionPane.showConfirmDialog(null, req_edit, "EDIT",
								JOptionPane.OK_CANCEL_OPTION);
						String req_name = listView.getItem(listView.getSelectionIndex());

						if (result == JOptionPane.OK_OPTION) {
							Requirement editable = null;
							fillList = reqDataBase.getAll();
							for (Requirement i : fillList)
								if (req_name.equals(i.getName()))
									editable = i;
							editable.setName(name_field.getText());
							editable.setShortDescription(short_description_field.getText());
							editable.setLongDescription(long_description_field.getText());
							editable.setLastModifiedDate();
							reqDataBase.updateRequirement(editable);
							fillListView();

						}
					}
				}

			}
		});
	}

	//returns a requirement with a specific id
	private Requirement reqById(int id) {
		for (Requirement r : reqDataBase.getAll())
			if (r.getId() == id)
				return r;
		return null;
	}

	//prints spaces for hierarchy
	private String tab(int level) {
		String ret = "   ";
		for (int i = 0; i < level; ++i)
			ret += "   ";
		return ret;
	}

	//add childs to the listView, recursive
	private void addChilds(Requirement req, int level) {
		if (req.getChilds().size() == 0)
			return;
		if (req == null)
			return;
		for (Integer idx : req.getChilds()) {
				Requirement current = reqById(idx);
				listView.add(tab(level) + current.getName());
				addChilds(current, level + 1);
		}
	}

	// private method for filling the list, also used for refreshing
	private void fillListView() {
		// reqDataBase= PersistenceUtility.getInstance();
		fillList = reqDataBase.getAll();
		listView.removeAll();
		for (Requirement idx : fillList) {
			if (!idx.getIsChild()) {
				listView.add(idx.getName());
				addChilds(idx, 1);
			}

		}

		deleteReqButton.setEnabled(false);
	}

	//updates the database after deleting, mainly the childs List
	private void updateAfterDelete(int id) {
		for (Requirement idx : reqDataBase.getAll()) {
			if (idx.getChilds().size() != 0)
				for (Integer iChild : idx.getChilds())
					if (iChild == id) {
						idx.removeChild(id);
						reqDataBase.updateRequirement(idx);
					}
		}
	}

	//recursive function for deleting childs, it goes from the youngest to the oldest
	private void deleteChilds(Requirement i) {
		if (i.getChilds().size() == 0)
			return ;
		for (Integer iChild : i.getChilds()) {
			try{
				deleteChilds(reqById(iChild));
				reqDataBase.deleteRequirement(reqById(iChild));
				updateAfterDelete(iChild);
			} catch(ConcurrentModificationException e){
				System.out.println("ceva" );
				e.printStackTrace(System.out);
			}
		}
	}
	
	private void deleteReq() {
		fillList = reqDataBase.getAll();
		String name = listView.getItem(listView.getSelectionIndex());
		// getting rid of white spaces(used for hierarchy)
		name = name.trim();
		for (Requirement i : fillList)
			if (name.equals(i.getName())) {
				reqDataBase.deleteRequirement(i);
				updateAfterDelete(i.getId());
				deleteChilds(i);
			}
		fillListView();
		deleteReqButton.setEnabled(false);
		txtInput.setText("");
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}

}