package com.btc.aclabs.ui.services;

import java.util.ArrayList;
import java.util.List;

import com.btc.acLabs.bl.dmos.Requirement;

//this class uses Singleton Pattern to ensure that only one instance of this class exists
//so there is only one requirementsList that is accessed by all the parts
public class RequirementsListManager {
	private List<Requirement> requirementsList;
	private static RequirementsListManager instance;

	private RequirementsListManager() {
		requirementsList = new ArrayList<Requirement>();
	}
	//lazy instantiation and synchronized for multiple threads
	public static synchronized RequirementsListManager getInstance() {
		if (instance == null)
			instance = new RequirementsListManager();
		return instance;
	}
	
	public boolean addRequirement(Requirement input){
		if(requirementsList.add(input))
			return true;
		return false;
	}
	
	public List<Requirement> getRequirementsList(){
		return requirementsList;
	}
	public void clearList()
	{
		this.requirementsList.clear();
	}
}
