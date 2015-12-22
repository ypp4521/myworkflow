package org.zywx.appdo.common.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 字段类型
 * @author 秦飞
 *
 */
public enum FieldTodoEnum {

	FIELD("字段","1"),NAME("标题","2"),CODE("编码","3");
	private String name;
	private String value;
	
	private FieldTodoEnum(String name,String value) {
		this.name=name;
		this.value=value;
	}
	
	public Map<String,String> getMap(){
		Map<String,String>	map=new HashMap<String, String>();
		map.put("name", name);
		map.put("value", value);
		return map;
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
	
}
