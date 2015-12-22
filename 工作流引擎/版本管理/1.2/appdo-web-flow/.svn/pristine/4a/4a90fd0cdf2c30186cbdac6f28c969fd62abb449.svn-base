package org.zywx.appdo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum IsAppEnum {
	PC("PC端","pc"),APP("app端","app");
	
	private String name;
	private String value;
	
	private IsAppEnum(String name,String value) {
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
		map.put(value, name);
		return map;
	}
}
