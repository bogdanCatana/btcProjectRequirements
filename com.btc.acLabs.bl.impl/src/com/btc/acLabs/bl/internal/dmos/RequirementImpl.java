package com.btc.acLabs.bl.internal.dmos;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.btc.acLabs.bl.dmos.Requirement;

@Entity
public class RequirementImpl implements Requirement {
	@Id
	@GeneratedValue
	private int id;//primary key 
	@Basic
	private String name; 
	@Basic
	private String shortDescription;  //represented as a column in database
	@Basic
	private String longDescription;
	@Basic
	private Date creationDate;
	@Basic
	private Date lastModifiedDate;
	@Basic
	private List<Integer> childs;
	@Basic
	private boolean isChild;
	
	public RequirementImpl(String n, String sd, String ld){
		this.name = n;
		this.shortDescription = sd;
		this.longDescription = ld;
		this.creationDate = new Date();
		//at the creation of the requirement the last modified date is the same as the creation one
		this.lastModifiedDate = new Date();
		childs = new LinkedList<Integer>();

	}
	public RequirementImpl(String n, String sd, String ld, boolean isChild){
		this.name = n;
		this.shortDescription = sd;
		this.longDescription = ld;
		this.creationDate = new Date();
		//at the creation of the requirement the last modified date is the same as the creation one
		this.lastModifiedDate = new Date();
		childs = new LinkedList<Integer>();
		this.isChild = isChild;
	}
	public RequirementImpl(String n, String sd, String ld,String creation_Date,String last_modifiedDate){
		this.name = n;
		this.shortDescription = sd;
		this.longDescription = ld;
		this.creationDate = new Date(creation_Date);
		//at the creation of the requirement the last modified date is the same as the creation one
		this.lastModifiedDate = new Date(last_modifiedDate);
		childs = new LinkedList<Integer>();

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
	
	public void setLastModifiedDate()
	{
		this.lastModifiedDate=new Date();
	}
	
	public void setChild(int id){
		childs.add(id);
	} 
	
	public int getId(){
		return id;
	}
	
	public void setIsChild(boolean set){
		isChild = set;
	}
	
	public boolean getIsChild(){
		return isChild;
	}
	
	public List<Integer> getChilds(){
		return childs;
	}
	
	public boolean removeChild(int id){
		for(Integer idx : childs)
			if(idx == id){
				childs.remove(childs.indexOf(id));
				return true;
			}
		return false;
	}
	
	public String toString(){
		return "Name:" + name + "\nShort Description:\n" + shortDescription + "\nLong Description: " + longDescription + "\nCreation Date: " + creationDate + "\nLast Modified Date: " + lastModifiedDate;
	}
}
