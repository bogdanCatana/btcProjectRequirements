package com.btc.acLabs.bl.internal.services;

import org.osgi.service.component.annotations.Component;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.dmos.RequirementImpl;
import com.btc.acLabs.bl.services.RequirementFactory;

@Component
public class RequirementFactoryImpl implements RequirementFactory {

	public Requirement create(String name, String shortDescription, String longDescription) {
		return new RequirementImpl(name, shortDescription, longDescription);
	}
	
	public Requirement create(String name, String shortDescription, String longDescription, boolean isChild) {
		return new RequirementImpl(name, shortDescription, longDescription, isChild);
	}
	
	public Requirement create(String name, String shortDescription, String longDescription,String creationDate,String lastModifiedDate) {
		return new RequirementImpl(name, shortDescription, longDescription, creationDate, lastModifiedDate);
	}
}
