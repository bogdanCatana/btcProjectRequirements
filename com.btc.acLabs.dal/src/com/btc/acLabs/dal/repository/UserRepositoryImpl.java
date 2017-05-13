package com.btc.acLabs.dal.repository;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.btc.acLabs.bl.dmos.User;
import com.btc.acLabs.bl.internal.repository.UserRepository;
import com.btc.acLabs.dal.utility.LoginUtility;

@Component
public class UserRepositoryImpl implements UserRepository {

	@Override
	public void create(User r) {
		LoginUtility ut=LoginUtility.getInstance();
		ut.create(r);
		
	}

	@Override
	public List<User> getAll() {
		LoginUtility ut=LoginUtility.getInstance();
		return ut.readAll();
	}

}
