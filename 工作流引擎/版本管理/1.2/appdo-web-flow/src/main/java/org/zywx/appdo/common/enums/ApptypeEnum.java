package org.zywx.appdo.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程操作
 * @author qf
 *
 */
public enum ApptypeEnum {
	
	COMPLETE("提交","0"),BACK("弃审","1"),REJECT("驳回","2");
	
	private String name;
	private String value;
	
	private ApptypeEnum(String name,String value) {
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
