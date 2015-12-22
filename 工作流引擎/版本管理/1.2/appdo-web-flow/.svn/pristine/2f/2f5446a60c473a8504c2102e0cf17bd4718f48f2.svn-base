package org.zywx.appdo.common.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 自定义表达式类型
 * @author 秦飞
 *
 */
public enum TodoVarEnum {

	VAR_USER("用户","user"),VAR_DICT("字典","dict"),VAR_DEPT("部门","dept"),VAR_ENUMS("枚举","enums");
	private String name;
	private String value;
	
	private TodoVarEnum(String name,String value) {
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
