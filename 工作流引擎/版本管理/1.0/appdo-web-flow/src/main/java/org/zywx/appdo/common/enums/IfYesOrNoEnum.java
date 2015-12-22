package org.zywx.appdo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum IfYesOrNoEnum {

	YES("是","1"),NO("否","0");
	
	private String name;
	private String value;
	@Override
	public String toString() {
		return this.value+"_"+this.name;
	}
	private IfYesOrNoEnum(String name,String value) {
		this.name=name;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Map<String,String> getMap(){
		Map<String,String>	map=new HashMap<String, String>();
		map.put("name", name);
		map.put("value", value);
		return map;
	}

}
