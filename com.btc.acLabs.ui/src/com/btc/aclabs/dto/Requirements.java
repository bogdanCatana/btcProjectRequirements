package com.btc.aclabs.dto;

import java.util.Date;

public class Requirements {
	private String name;
	private String shortDescription;
	private String longDescription;
	private Date creationDate;
	private Date lastModifiedDate;
	public Requirements(String n, String sd, String ld, Date date){
		name = n;
		shortDescription = sd;
		longDescription = ld;
		creationDate = date;
		//at the creation of the requirement the last modified date is the same as the creation one
		lastModifiedDate = date;
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
	//this should be used for the edit option
	public void setLastModfiedDate(Date newLastModifiedDate){
		lastModifiedDate = newLastModifiedDate;
	}
	public String toString(){
		return "" + name + "\n" + shortDescription + "\n" + longDescription + "\n" + creationDate + "\n" + lastModifiedDate;
	}
}
