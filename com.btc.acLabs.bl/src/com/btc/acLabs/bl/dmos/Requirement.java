package com.btc.acLabs.bl.dmos;

import java.util.Date;
import java.util.List;

public interface Requirement {
	String getName();

	String getShortDescription();

	String getLongDescription();

	//no set for creationDate as it shouldn't ever change
	Date getCreationDate();

	Date getLastModifiedDate();
	
	void setName(String newName);
	
	void setShortDescription(String newShortDescription);
	
	void setLongDescription(String newLongDescription);
	
	void setLastModifiedDate();
	
	int getId();
	
	void setChild(int id);
	
	void setIsChild(boolean set);
	
	boolean getIsChild();
	
	List<Integer> getChilds();
	
	boolean removeChild(int id);
}
