package com.btc.acLabs.bl.internal.services;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.dmos.RequirementImpl;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;
import com.btc.acLabs.bl.services.RequirementService;

@Component(immediate=true)
public class RequirementServiceImpl implements RequirementService{

	private RequirementRepository requirementRepository;
	
	@Override
	public void create(String name, String shortDescription, String longDescription) {
		System.out.println("Creating requirement...");
		
		RequirementImpl requirement = new RequirementImpl(name, shortDescription, longDescription);
		requirementRepository.create(requirement);
	}
	
	@Override
	public List<Requirement> getAll() {
		return requirementRepository.getAll();
	}
	
	@Reference
	public void setRequirementRepository(RequirementRepository repository) {
		this.requirementRepository = repository;
	}

	
}
