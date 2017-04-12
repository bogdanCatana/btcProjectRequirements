package com.btc.aclabs.ui.parts;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.btc.aclabs.dto.Requirements;
import com.btc.aclabs.dto.RightLeftList;

public class RequirementsPart {
	// main shell
	// labels, texts and buttons for adding requirements
	private Label labelName;
	private List<Requirements> requirementsList;
	private Text textName;
	private Label labelShortDescription;
	private Text textShortDescription;
	private Label labelLongDescription;
	private Text textLongDescription;
	private Button buttonAdd;
	// display button in cmd
	private Button buttonDisplay;
	// for display with arrows
	private Button buttonLeft;
	private Button buttonRight;
	private Text outTextArrows;
	// for the "no requirements" case
	private MessageBox box;
	private GridData gridData;
	// list use for display with arrows
	private RightLeftList displayList;
	//filling label for empty cell
	private Label fillingLabel;
	

	@PostConstruct
	public void createComposite(Composite parent) {

		parent.setLayout(new GridLayout(3, true));
		// new objects
		requirementsList = new ArrayList<Requirements>();
		gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		labelName = new Label(parent, SWT.NONE);
		textName = new Text(parent, SWT.BORDER);
		labelShortDescription = new Label(parent, SWT.NONE);
		textShortDescription = new Text(parent, SWT.BORDER);
		labelLongDescription = new Label(parent, SWT.NONE);
		textLongDescription = new Text(parent, SWT.BORDER);
		buttonAdd = new Button(parent, SWT.NONE);
		buttonDisplay = new Button(parent, SWT.NONE);
		fillingLabel = new Label(parent, SWT.NONE);
		buttonLeft = new Button(parent, SWT.NONE);
		outTextArrows = new Text(parent, SWT.MULTI | SWT.BORDER);
		buttonRight = new Button(parent, SWT.NONE);
		box = new MessageBox(parent.getShell(), SWT.OK);

		// in the beginning set buttonAdd to false(no text)
		buttonAdd.setEnabled(false);
		labelName.setText("Name:");
		gridData.horizontalSpan = 2;
		textName.setLayoutData(gridData);
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

		labelLongDescription.setText("Long description: ");
		textLongDescription.setLayoutData(gridData);
		// the same functionality as the add button when "enter" is pressed
		textLongDescription.addTraverseListener(new TraverseListener() {

			@Override
			public void keyTraversed(TraverseEvent e) {
				// TODO Auto-generated method stub
				if (e.keyCode == SWT.CR) {
					requirementsList.add(new Requirements(textName.getText(), textShortDescription.getText(), textLongDescription.getText()));
					displayList = new RightLeftList(requirementsList);
					buttonRight.setEnabled(true);
					buttonLeft.setEnabled(true);
					// e.doit = false;
				}
			}
		});
		
		buttonAdd.setText("Add requirement");
		buttonAdd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				requirementsList.add(new Requirements(textName.getText(), textShortDescription.getText(), textLongDescription.getText()));
				displayList = new RightLeftList(requirementsList);
				buttonRight.setEnabled(true);
				buttonLeft.setEnabled(true);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// buttonDisplay.setEnabled(false);
		buttonDisplay.setText("Display all");
		buttonDisplay.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (requirementsList.size() > 0)
					for (Requirements idx : requirementsList)
						System.out.println(idx);
				// show error in case of no requirements
				else {
					box.setText("No requirements");
					box.setMessage("There are no requirements entries!");
					box.open();
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// arrows and outLabel
		buttonLeft.setEnabled(false);
		buttonRight.setEnabled(false);
		gridData = new GridData(SWT.RIGHT, SWT.NULL, true, true);
		buttonLeft.setText("<-");
		buttonLeft.setLayoutData(gridData);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		outTextArrows.setLayoutData(gridData);
		buttonRight.setText("->");
		gridData = new GridData(SWT.LEFT, SWT.NULL, true, true);
		buttonRight.setLayoutData(gridData);

		// functionality outLabel and arrows
		buttonRight.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (displayList.isValidRight())
					outTextArrows.setText(displayList.right().toString());
				else
					outTextArrows.setText("No more req!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		buttonLeft.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if (displayList.isValidLeft())
					outTextArrows.setText(displayList.left().toString());
				else
					outTextArrows.setText("No more req!");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
