package com.btc.acLabs.bl.internal.services;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.btc.acLabs.bl.dmos.User;
import com.btc.acLabs.bl.internal.dmos.UserImpl;

import com.btc.acLabs.bl.internal.repository.UserRepository;
import com.btc.acLabs.bl.services.UserService;

@Component(immediate=true)
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	
	@Reference
	public void setUserRepository(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public void create(String name, String password) {
		System.out.println("Creating user...");
		UserImpl new_user=new UserImpl(name,password);
		userRepository.create(new_user);
		
		
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.getAll();
	}

}
