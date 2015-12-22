package org.zywx.appdo.flow.entity;

import java.util.Date;

import org.zywx.appdo.common.entity.AbstractBaseEntity;


/**
 * Bpm_todo表关联历史流程实例表
 * @author xingshen.zhao
 *
 */

public class BpmTodoProcInst extends AbstractBaseEntity {

	/**
	 * 
	 */
	
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
	private String year;
	private String userId;
	private String userName;
	private String isback;
	private String isrevoke;
	private String busicode;
	private String businame;
	private String busi_code;
	private String flowid;
	private String metaname;
	private String metacode;
	private String ID_;
	private String PROC_INST_ID_;
	private String BUSINESS_KEY_;
	private String PROC_DEF_ID_;
	private String START_TIME_;
	private Date END_TIME_;
	private String DURATION_;
	private String START_USER_ID_;
	private String START_ACT_ID_;
	private String END_ACT_ID_;
	private String SUPER_PROCESS_INSTANCE_ID_;
	private String DELETE_REASON_;
	private String NAME_;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInstanceid() {
		return instanceid;
	}
	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMetaid() {
		return metaid;
	}
	public void setMetaid(String metaid) {
		this.metaid = metaid;
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
		this.remark = remark;
	}
	public String getBusiid() {
		return busiid;
	}
	public void setBusiid(String busiid) {
		this.busiid = busiid;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getBusicode() {
		return busicode;
	}
	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}
	public String getBusiname() {
		return businame;
	}
	public void setBusiname(String businame) {
		this.businame = businame;
	}
	public String getBusi_code() {
		return busi_code;
	}
	public void setBusi_code(String busi_code) {
		this.busi_code = busi_code;
	}
	public String getFlowid() {
		return flowid;
	}
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	public String getMetaname() {
		return metaname;
	}
	public void setMetaname(String metaname) {
		this.metaname = metaname;
	}
	public String getMetacode() {
		return metacode;
	}
	public void setMetacode(String metacode) {
		this.metacode = metacode;
	}
	public String getID_() {
		return ID_;
	}
	public void setID_(String iD_) {
		ID_ = iD_;
	}
	public String getPROC_INST_ID_() {
		return PROC_INST_ID_;
	}
	public void setPROC_INST_ID_(String pROC_INST_ID_) {
		PROC_INST_ID_ = pROC_INST_ID_;
	}
	public String getBUSINESS_KEY_() {
		return BUSINESS_KEY_;
	}
	public void setBUSINESS_KEY_(String bUSINESS_KEY_) {
		BUSINESS_KEY_ = bUSINESS_KEY_;
	}
	public String getPROC_DEF_ID_() {
		return PROC_DEF_ID_;
	}
	public void setPROC_DEF_ID_(String pROC_DEF_ID_) {
		PROC_DEF_ID_ = pROC_DEF_ID_;
	}
	public String getSTART_TIME_() {
		return START_TIME_;
	}
	public void setSTART_TIME_(String sTART_TIME_) {
		START_TIME_ = sTART_TIME_;
	}
	public Date getEND_TIME_() {
		return END_TIME_;
	}
	public void setEND_TIME_(Date eND_TIME_) {
		END_TIME_ = eND_TIME_;
	}
	public String getDURATION_() {
		return DURATION_;
	}
	public void setDURATION_(String dURATION_) {
		DURATION_ = dURATION_;
	}
	public String getSTART_USER_ID_() {
		return START_USER_ID_;
	}
	public void setSTART_USER_ID_(String sTART_USER_ID_) {
		START_USER_ID_ = sTART_USER_ID_;
	}
	public String getSTART_ACT_ID_() {
		return START_ACT_ID_;
	}
	public void setSTART_ACT_ID_(String sTART_ACT_ID_) {
		START_ACT_ID_ = sTART_ACT_ID_;
	}
	public String getEND_ACT_ID_() {
		return END_ACT_ID_;
	}
	public void setEND_ACT_ID_(String eND_ACT_ID_) {
		END_ACT_ID_ = eND_ACT_ID_;
	}
	public String getSUPER_PROCESS_INSTANCE_ID_() {
		return SUPER_PROCESS_INSTANCE_ID_;
	}
	public void setSUPER_PROCESS_INSTANCE_ID_(String sUPER_PROCESS_INSTANCE_ID_) {
		SUPER_PROCESS_INSTANCE_ID_ = sUPER_PROCESS_INSTANCE_ID_;
	}
	public String getDELETE_REASON_() {
		return DELETE_REASON_;
	}
	public void setDELETE_REASON_(String dELETE_REASON_) {
		DELETE_REASON_ = dELETE_REASON_;
	}
	public String getNAME_() {
		return NAME_;
	}
	public void setNAME_(String nAME_) {
		NAME_ = nAME_;
	}
}
