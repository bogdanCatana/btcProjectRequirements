package com.btc.acLabs.bl.internal.repository;

import java.util.List;

import com.btc.acLabs.bl.dmos.Requirement;

public interface RequirementRepository {

	void create(Requirement requirement);

	List<Requirement> getAll();
	void clearDataBase();
	void deleteRequirement(Requirement r);
}
