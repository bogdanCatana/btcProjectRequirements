package com.btc.aclabs.ui.services;

import java.util.ArrayList;
import java.util.List;

import com.btc.aclabs.dto.Requirements;

//this class uses Singleton Pattern to ensure that only one instance of this class exists
//so there is only one requirementsList that is accessed by all the parts
public class RequirementsListManager {
	private List<Requirements> requirementsList;
	private static RequirementsListManager instance;

	private RequirementsListManager() {
		requirementsList = new ArrayList<Requirements>();
	}
	//lazy instantiation and synchronized for multiple threads
	public static synchronized RequirementsListManager getInstance() {
		if (instance == null)
			instance = new RequirementsListManager();
		return instance;
	}
	
	public boolean addRequirement(Requirements input){
		if(requirementsList.add(input))
			return true;
		return false;
	}
	
	public List<Requirements> getRequirementsList(){
		return requirementsList;
	}
}
