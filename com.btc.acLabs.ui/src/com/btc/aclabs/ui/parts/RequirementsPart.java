package com.btc.aclabs.ui.parts;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.dmos.RequirementImpl;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;


public class RequirementsPart {
	// main shell
	// labels, texts and buttons for adding requirements
	private Label labelName;
	private Text textName;
	private Label labelShortDescription;
	private Text textShortDescription;
	private Label labelLongDescription;
	private Text textLongDescription;
	private Button buttonAdd;
	// for the "no requirements" case
	private MessageBox box;
	private GridData gridData;
	// filling label for empty cell
	private Label fillingLabel;
	// for subreq adding
	private Combo comboDropDown;
	private Label labelSubReq;
	@Inject
	private RequirementRepository reqDataBase;

	@PostConstruct
	public void createComposite(Composite parent) {

		parent.setLayout(new GridLayout(3, true));

		// new objects
		gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		labelName = new Label(parent, SWT.NONE);
		textName = new Text(parent, SWT.BORDER);
		labelShortDescription = new Label(parent, SWT.NONE);
		textShortDescription = new Text(parent, SWT.BORDER);
		labelLongDescription = new Label(parent, SWT.NONE);
		textLongDescription = new Text(parent, SWT.BORDER);
		labelSubReq = new Label(parent, SWT.NONE);
		comboDropDown = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER);
		buttonAdd = new Button(parent, SWT.NONE);

		fillingLabel = new Label(parent, SWT.NONE);
		box = new MessageBox(parent.getShell(), SWT.OK);

		// in the beginning set buttonAdd to false(no text)
		buttonAdd.setEnabled(false);
		labelName.setText("Name:");
		gridData.horizontalSpan = 2;
		textName.setLayoutData(gridData);
		textName.setMessage("Enter requirement name");
		// setting enabled depending on text in the name label
		textName.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				// if there's no text, the button is set to false
				if (textName.getText() == "")
					buttonAdd.setEnabled(false);
				// else is set to true
				else
					buttonAdd.setEnabled(true);
			}
		});

		labelShortDescription.setText("Short description:");
		textShortDescription.setLayoutData(gridData);
		textShortDescription.setMessage("Enter short description");

		labelLongDescription.setText("Long description: ");
		textLongDescription.setLayoutData(gridData);
		textLongDescription.setMessage("Enter long description");
		// the same functionality as the add button when "enter" is pressed
		textLongDescription.addTraverseListener(new TraverseListener() {

			@Override
			public void keyTraversed(TraverseEvent e) {
				// TODO Auto-generated method stub
				if (textName.getText() != "") {
					Requirement temp = new RequirementImpl(textName.getText(), textShortDescription.getText(),
							textLongDescription.getText());
					enterAsAddEvent(e, temp);
					if (comboDropDown.getText() != "") {
						setRelation(comboDropDown.getText(), temp.getId());
						temp.setIsChild(true);
						reqDataBase.updateRequirement(temp);
					}
				}
			}
		});

		labelSubReq.setText("Subrequirement of:");
		comboDropDown.setLayoutData(gridData);
		//it refreshes on click
		comboDropDown.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				fillDropDown();
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		;
		fillDropDown();

		buttonAdd.setText("Add requirement");
		buttonAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				Requirement temp = new RequirementImpl(textName.getText(), textShortDescription.getText(),
						textLongDescription.getText());
				reqDataBase.create(temp);
				if (comboDropDown.getText() != "") {
					setRelation(comboDropDown.getText(), temp.getId());
					temp.setIsChild(true);
					reqDataBase.updateRequirement(temp);
				}
				setEmptyTexts();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void fillDropDown() {
		comboDropDown.removeAll();
		for (Requirement idx : reqDataBase.getAll()) {
			comboDropDown.add(idx.getName());
		}
	}

	//add on "enter" key - only on long description
	private void enterAsAddEvent(TraverseEvent e, Requirement temp) {
		if (e.keyCode == SWT.CR) {
			reqDataBase.create(temp);
			fillDropDown();
			setEmptyTexts();
		}
	}

	private void setEmptyTexts() {
		textName.setText("");
		textLongDescription.setText("");
		textShortDescription.setText("");
	}

	//sets child-parent relationship
	private boolean setRelation(String reqName, int id) {
		for (Requirement idx : reqDataBase.getAll()) {
			if (idx.getName().equals(reqName)) {
				idx.setChild(id);
				reqDataBase.updateRequirement(idx);
				comboDropDown.setText("");
				return true;
			}
		}
		return false;
	}

	@Focus
	public void setFocus() {
		// textName.setFocus();
	}

}
