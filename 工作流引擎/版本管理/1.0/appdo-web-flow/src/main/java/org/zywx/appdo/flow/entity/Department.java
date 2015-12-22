package org.zywx.appdo.flow.entity;

public class Department {
	
	private String dtpId;//部门ID
	
	private String dptName;//部门名称
	
	private String superDptId;//上级部门ID

	public String getDtpId() {
		return dtpId;
	}

	public void setDtpId(String dtpId) {
		this.dtpId = dtpId;
	}

	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	public String getSuperDptId() {
		return superDptId;
	}

	public void setSuperDptId(String superDptId) {
		this.superDptId = superDptId;
	}
	

}
