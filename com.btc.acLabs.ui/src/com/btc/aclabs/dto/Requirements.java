package com.btc.aclabs.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Requirements {
	@Id
	@GeneratedValue
	private int id;
	@Basic
	private String name; //primary key 
	@Basic
	private String shortDescription;  //represented as a column in database
	@Basic
	private String longDescription;
	@Basic
	private Date creationDate;
	@Basic
	private Date lastModifiedDate;
	
	private static int count=0;
	
	public Requirements(String n, String sd, String ld){
		this.name = n;
		this.shortDescription = sd;
		this.longDescription = ld;
		this.creationDate = new Date();
		//at the creation of the requirement the last modified date is the same as the creation one
		this.lastModifiedDate = new Date();
		count++;
	}
	public String getName(){
		return name;
	}
	public void setName(String newName){
		name = newName;
	}
	public String getShortDescription(){
		return shortDescription;
	}
	public void setShortDescription(String newShortDescription){
		shortDescription = newShortDescription;
	}
	public String getLongDescription(){
		return longDescription;
	}
	public void setLongDescription(String newLongDescription){
		longDescription = newLongDescription;
	}
	//no set for creationDate as it shouldn't ever change
	public Date getCreationDate(){
		return creationDate;
	}
	public Date getLastModifiedDate(){
		return lastModifiedDate;
	}
	public void setCreationDate(String date)
	{
		creationDate=new Date(date);
	}
	//this should be used for the edit option
	public void setLastModfiedDate(){
		lastModifiedDate = new Date();
	}
	//used for setting date when importing from different types of files
	public void setLastModifiedDate(String date)
	{
		lastModifiedDate=new Date(date);
	}
	public String toString(){
		return "Name:" + name + "\nShort Description:\n" + shortDescription + "\nLong Description: " + longDescription + "\nCreation Date: " + creationDate + "\nLast Modified Date: " + lastModifiedDate;
	}
	public static int getNrofRequirements()
	{
		return count;
	}
}
