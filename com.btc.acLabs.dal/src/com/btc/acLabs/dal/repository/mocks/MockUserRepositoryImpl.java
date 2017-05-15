package com.btc.acLabs.dal.repository.mocks;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.btc.acLabs.bl.dmos.User;
import com.btc.acLabs.bl.internal.repository.UserRepository;
import com.btc.acLabs.dal.utility.LoginUtility;

@Component
public class MockUserRepositoryImpl implements UserRepository {

	private static List<User> users = new ArrayList<>();

	@Override
	public void create(User user) {
		users.add(user);
	}

	@Override
	public List<User> getAll() {
		List<User> copyOfList = new ArrayList<>();
		copyOfList.addAll(users);
		return copyOfList;
	}

}
