package com.btc.acLabs.bl.internal.services;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.dmos.RequirementImpl;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;
import com.btc.acLabs.bl.services.RequirementFactory;
import com.btc.acLabs.bl.services.RequirementService;

@Component(immediate=true)
public class RequirementServiceImpl implements RequirementService {
	
	private RequirementRepository requirementRepository;
	private RequirementFactory requirementFactory;
	
	@Override
	public void create(String name, String shortDescription, String longDescription) {
		System.out.println("Creating requirement...");
		
		Requirement requirement = requirementFactory.create(name, shortDescription, longDescription);
		requirementRepository.create(requirement);
	}
	
	@Override
	public List<Requirement> getAll() {
		return requirementRepository.getAll();
	}
	
	@Override
	public void updateRequirement(Requirement r) {
		requirementRepository.updateRequirement(r);
		
	}

	@Override
	public void deleteRequirement(Requirement r) {
		requirementRepository.deleteRequirement(r);
		
	}

	@Override
	public void clearDataBase() {
		requirementRepository.clearDataBase();
		
	}

	@Override
	public void create(Requirement req) {
		requirementRepository.create(req);
	}
	
	@Reference
	public void setRequirementRepository(RequirementRepository repository) {
		this.requirementRepository = repository;
	}

	@Reference
	public void setRequirementFactory(RequirementFactory factory) {
		this.requirementFactory = factory;
	}
}
