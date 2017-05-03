package com.btc.aclabs.dto;
import java.util.List;

import com.btc.acLabs.bl.dmos.Requirement;

public class RightLeftList {
	private List<Requirement> list = null;
	private int elementCrt = -1;
	public RightLeftList(List<Requirement>  list){
		this.list = list;
	}
	//checks if the current element is valid
	public boolean isValid(){
		if(list != null && elementCrt < list.size())
			return true;
		return false;
	}
	public boolean isValidLeft(){
		if(list != null && elementCrt - 1 < list.size() && elementCrt - 1 >= -1)
			return true;
		return false;
	}
	public boolean isValidRight(){
		if(list != null && elementCrt + 1 < list.size())
			return true;
		return false;
	}
	public Requirement right(){
		if(isValidRight())
			return list.get(++elementCrt);
		return null;
	}
	public Requirement left(){
		if(isValidLeft())
			if(elementCrt == 0)
				return list.get(elementCrt);
			else
				return list.get(elementCrt--);
		return null;
	}
}
