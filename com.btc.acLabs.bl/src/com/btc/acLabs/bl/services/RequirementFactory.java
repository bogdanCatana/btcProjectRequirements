package com.btc.acLabs.bl.services;

import com.btc.acLabs.bl.dmos.Requirement;

public interface RequirementFactory {

	Requirement create(String name, String shortDescription, String longDescription);
	
	Requirement create(String name, String shortDescription, String longDescription, boolean isChild);
	
	public Requirement create(String name, String shortDescription, String longDescription,String creationDate,String lastModifiedDate);
}
