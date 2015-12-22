package org.zywx.appdo.flow.entity;

import java.util.Date;

import javax.persistence.Transient;

import org.zywx.appdo.common.entity.AbstractBaseEntity;

/**
 * 流程审批历史
 * 
 * @author qf
 *
 */
public class BpmApprove extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String instanceid;

	private String taskid;

	private String approve;

	private String approveresult;

	private String createtime;

	private String apptype;

	private String targetkey;

	private String year;
	private String userId;
	private String userName;
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	//工作流流程中使用字段

	private String submitAt;
	
	public String getSubmitAt() {
		return submitAt;
	}

	public void setSubmitAt(String submitAt) {
		this.submitAt = submitAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid == null ? null : instanceid.trim();
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid == null ? null : taskid.trim();
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve == null ? null : approve.trim();
	}

	public String getApproveresult() {
		return approveresult;
	}

	public void setApproveresult(String approveresult) {
		this.approveresult = approveresult == null ? null : approveresult.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype == null ? null : apptype.trim();
	}

	public String getTargetkey() {
		return targetkey;
	}

	public void setTargetkey(String targetkey) {
		this.targetkey = targetkey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}