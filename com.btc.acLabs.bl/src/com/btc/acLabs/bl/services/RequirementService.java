package com.btc.acLabs.bl.services;

import java.util.List;

import com.btc.acLabs.bl.dmos.Requirement;

public interface RequirementService {
	void create(String name, String shortDescription, String longDescription);
	
	void create(Requirement req);

	List<Requirement> getAll();
	
	void updateRequirement(Requirement r);
	
	void deleteRequirement(Requirement r);
	
	void clearDataBase();
}
