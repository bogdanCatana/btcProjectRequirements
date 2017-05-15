package com.btc.acLabs.dal.repository.mocks;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.btc.acLabs.bl.dmos.Requirement;
import com.btc.acLabs.bl.dmos.User;
import com.btc.acLabs.bl.internal.repository.RequirementRepository;
import com.btc.acLabs.dal.utility.PersistenceUtility;

@Component
public class MockRequirementRepositoryImpl implements RequirementRepository {

	private static List<Requirement> requirements = new ArrayList<>();
	private static int requirementId = 0;
	
	@Override
	public void create(Requirement requirement) {
		requirementId++;
		requirement.setId(requirementId);
		requirements.add(requirement);
		
	}

	@Override
	public List<Requirement> getAll() {
		List<Requirement> copyOfList = new ArrayList<>();
		copyOfList.addAll(requirements);
		return copyOfList;
	}

	@Override
	public void clearDataBase() {
		requirements.clear();
	}

	@Override
	public void deleteRequirement(Requirement requirement) {
		for (Requirement req : requirements) {
			if (req.getId() == requirement.getId()) {
				requirements.remove(req);
				return;
			}
		}
		
	}

	@Override
	public void updateRequirement(Requirement requirement) {
		for (int i = 0; i < requirements.size(); i++) {
			Requirement req = requirements.get(i);
			if (req.getId() == requirement.getId()) {
				requirements.set(i, requirement);
			}
		}
	}
	

	
}
