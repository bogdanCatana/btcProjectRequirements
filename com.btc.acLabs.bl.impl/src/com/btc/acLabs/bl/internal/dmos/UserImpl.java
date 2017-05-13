package com.btc.acLabs.bl.internal.dmos;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.btc.acLabs.bl.dmos.User;

@Entity
public class UserImpl implements User {

	@Id
	@GeneratedValue
	private int id;
	@Basic
	private String name; 
	@Basic
	private String password;
	
	public UserImpl(String name,String password)
	{
		this.name=name;
		this.password=password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
	
		return password;
	}
	
	
}
