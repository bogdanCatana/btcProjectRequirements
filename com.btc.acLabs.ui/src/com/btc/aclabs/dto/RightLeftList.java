package com.btc.aclabs.dto;
import java.util.List;
import java.util.ArrayList;

public class RightLeftList {
	private static List<Requirements> list = null;		//asta e deocamdata statica
	private int elementCrt = -1;
	public RightLeftList(List<Requirements>  list){
		RightLeftList.list = list;
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
	public Requirements right(){
		if(isValidRight())
			return list.get(++elementCrt);
		return null;
	}
	public Requirements left(){
		if(isValidLeft())
			if(elementCrt == 0)
				return list.get(elementCrt);
			else
				return list.get(elementCrt--);
		return null;
	}
	//asta e pentru a cauta in lista de req
	public static List<Requirements> getList(){
		return list;
	}
	//asta e pentru a afisa in lista de requirementuri
	public static List<String>toList(){
		List<String>tmp = new ArrayList<String>();
		if(list!=null)
		for(Requirements i:list){
			String stmp = i.getName() + " " + i.getShortDescription();
			tmp.add(stmp);
		}
		return tmp;
	}
}
