package com.btc.acLabs.dal.repository;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;
import com.btc.acLabs.dal.utils.PersistenceUtility;

@Component(immediate=true)
public class RequirementRepositoryImpl implements RequirementRepository {

	public void create(Requirement requirement) {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		reqDataBase.create(requirement);
	}

	@Override
	public List<Requirement> getAll() {
		PersistenceUtility reqDataBase=PersistenceUtility.getInstance();
		return reqDataBase.readAll();
	}
}
