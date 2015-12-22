package org.zywx.appdo.flow.entity;

import javax.persistence.Transient;

import org.zywx.appdo.common.entity.AbstractBaseEntity;

public class BpmTodo extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	private String title;

	private String code;

	private String instanceid;

	private String name;

	private String metaid;

	private String createtime;

	private String remark;

	private String busiid;

	private String businessKey;

	private String taskId;

	private String url;

	private String busipath;

	private String businame;

	private String year;// 年度

	private String userId;// 创建人id

	private String userName;// 创建人id

	/** 是否查看被退回 值不能为空，默认为0 */
	private String isback;
	/** 是否查看收回数据 值不能为空，默认为0 */
	private String isrevoke;

	private String opinionField;// 当前意见字段
	/** 模版类型 001 请假 ，后续编排规范 */
	private String busicode;// 当前意见字段

	public String getBusicode() {
		return busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	public String getOpinionField() {
		return opinionField;
	}

	public void setOpinionField(String opinionField) {
		this.opinionField = opinionField;
	}

	public String getIsback() {
		return isback;
	}

	public void setIsback(String isback) {
		this.isback = isback;
	}

	public String getIsrevoke() {
		return isrevoke;
	}

	public void setIsrevoke(String isrevoke) {
		this.isrevoke = isrevoke;
	}

	// 工作流流程中使用字段
	@Transient
	private String nodeName;
	@Transient
	private String submitAt;
	@Transient
	private String submitUser;
	@Transient
	private String endTime;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBusiname() {
		return businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	public String getBusipath() {
		return busipath;
	}

	public void setBusipath(String busipath) {
		this.busipath = busipath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid == null ? null : instanceid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getMetaid() {
		return metaid;
	}

	public void setMetaid(String metaid) {
		this.metaid = metaid == null ? null : metaid.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getBusiid() {
		return busiid;
	}

	public void setBusiid(String busiid) {
		this.busiid = busiid == null ? null : busiid.trim();
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey == null ? null : businessKey.trim();
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}