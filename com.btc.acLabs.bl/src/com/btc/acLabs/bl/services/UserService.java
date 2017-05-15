package com.btc.acLabs.bl.services;

import java.util.List;

import com.btc.acLabs.bl.dmos.User;

public interface UserService {
	void create(String name,String password,String firstName,String lastName,String companyName);
	
	List<User> getAll();
}
