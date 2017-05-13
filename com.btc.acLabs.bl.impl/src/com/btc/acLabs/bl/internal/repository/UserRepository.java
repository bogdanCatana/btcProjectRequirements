package com.btc.acLabs.bl.internal.repository;

import java.util.List;

import com.btc.acLabs.bl.dmos.User;

public interface UserRepository {
	void create(User r);
	
	List<User> getAll();
}
