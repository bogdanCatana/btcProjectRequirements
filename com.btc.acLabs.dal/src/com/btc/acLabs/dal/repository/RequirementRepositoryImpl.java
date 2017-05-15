package com.btc.acLabs.dal.repository;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;
import com.btc.acLabs.dal.utility.PersistenceUtility;

public class RequirementRepositoryImpl implements RequirementRepository {

	@Override
	public void create(Requirement requirement) {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		reqDataBase.create(requirement);
		
	}

	@Override
	public List<Requirement> getAll() {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		return reqDataBase.readAll();
	}

	@Override
	public void clearDataBase() {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		reqDataBase.clearDataBase();
		
	}

	@Override
	public void deleteRequirement(Requirement r) {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		reqDataBase.deleteRequirement(r);
		
	}

	@Override
	public void updateRequirement(Requirement r) {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		reqDataBase.updateRequirement(r);
		
	}
	

	
}
