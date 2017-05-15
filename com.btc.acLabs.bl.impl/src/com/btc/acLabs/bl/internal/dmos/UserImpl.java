package com.btc.acLabs.bl.internal.dmos;

import java.util.Date;

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
	@Basic
	private String firstName;
	@Basic
	private String lastName;
	@Basic 
	private String companyName;
	@Basic
	private Date joinDate;
	
	public UserImpl(String name,String password,String firstName,String lastName,String companyName)
	{
		this.name=name;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.companyName=companyName;
		this.joinDate=new Date();
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
