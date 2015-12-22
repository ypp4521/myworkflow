package org.zywx.appdo.common.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 流程绑定类型
 * @author 秦飞
 *
 */
public enum SingleTypeEnum {

	ALL("全局", "1"), USER("用户", "2"), ROLE("角色", "3"), DEPART("部门", "4"), USERGROUP("用户组", "5");
	private String name;
	
	private String value;
	
	private SingleTypeEnum(String name,String value) {
		this.name=name;
		this.value=value;
	}
	//覆盖方法
	@Override
	public String toString() {
		return this.value+"_"+this.name;
	}
	
	public String getName(){
		return name;
	}
	
	public String getValue(){
		return value;
	}
	
	public Map<String,String> getMap(){
		Map<String,String>	map=new HashMap<String, String>();
		map.put("name", name);
		map.put("value", value);
		return map;
	}
}
