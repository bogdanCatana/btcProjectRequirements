package com.btc.acLabs.bl.dmos;

import java.util.Date;

public interface Requirement {
	String getName();

	String getShortDescription();

	String getLongDescription();

	//no set for creationDate as it shouldn't ever change
	Date getCreationDate();

	Date getLastModifiedDate();
}
