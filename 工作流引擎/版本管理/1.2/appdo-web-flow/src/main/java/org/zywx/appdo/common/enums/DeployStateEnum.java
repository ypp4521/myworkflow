package org.zywx.appdo.common.enums;
/**
 * 发布状态
 * @author 秦飞
 *
 */
public enum DeployStateEnum {

	UNDEPLOY("0"),DEPLOY("1");
	
	private String state;
	
	private DeployStateEnum(String state) {
		this.state=state;
	}
	
	public String getState(){
		return state;
	}
}
