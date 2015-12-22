package org.zywx.appdo.flow.entity;

import java.util.Date;

import org.zywx.appdo.common.entity.AbstractBaseEntity;

/**
 * 当前任务信息
 * @author xingshen.zhao
 *
 */
public class BpmTodoTask extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID_;
	private String REV_;
	private String EXECUTION_ID_;
	private String PROC_INST_ID_;
	private String PROC_DEF_ID_;
	private String NAME_;
	private String PARENT_TASK_ID_;
	private String DESCRIPTION_;
	private String TASK_DEF_KEY_;
	private String ASSIGNEE_;
	private String DELEGATION_;
	private String PRIORITY_;
	private String DURATION_;
	private Date END_TIME_;
	private Date CREATE_TIME_;
	private Date START_TIME_;
	private Date CLAIM_TIME_;
	private String DUE_DATE_;
	private String CATEGORY_;
	private String SUSPENSION_STATE_;
	private String FORM_KEY_;
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
	public String getID_() {
		return ID_;
	}
	public void setID_(String iD_) {
		ID_ = iD_;
	}
	public String getREV_() {
		return REV_;
	}
	public void setREV_(String rEV_) {
		REV_ = rEV_;
	}
	
	public String getDURATION_() {
		return DURATION_;
	}
	public void setDURATION_(String dURATION_) {
		DURATION_ = dURATION_;
	}
	public Date getCLAIM_TIME_() {
		return CLAIM_TIME_;
	}
	public void setCLAIM_TIME_(Date cLAIM_TIME_) {
		CLAIM_TIME_ = cLAIM_TIME_;
	}
	public Date getSTART_TIME_() {
		return START_TIME_;
	}
	public void setSTART_TIME_(Date sTART_TIME_) {
		START_TIME_ = sTART_TIME_;
	}
	public Date getEND_TIME_() {
		return END_TIME_;
	}
	public void setEND_TIME_(Date eND_TIME_) {
		END_TIME_ = eND_TIME_;
	}
	public String getEXECUTION_ID_() {
		return EXECUTION_ID_;
	}
	public void setEXECUTION_ID_(String eXECUTION_ID_) {
		EXECUTION_ID_ = eXECUTION_ID_;
	}
	public String getPROC_INST_ID_() {
		return PROC_INST_ID_;
	}
	public void setPROC_INST_ID_(String pROC_INST_ID_) {
		PROC_INST_ID_ = pROC_INST_ID_;
	}
	public String getPROC_DEF_ID_() {
		return PROC_DEF_ID_;
	}
	public void setPROC_DEF_ID_(String pROC_DEF_ID_) {
		PROC_DEF_ID_ = pROC_DEF_ID_;
	}
	public String getNAME_() {
		return NAME_;
	}
	public void setNAME_(String nAME_) {
		NAME_ = nAME_;
	}
	public String getPARENT_TASK_ID_() {
		return PARENT_TASK_ID_;
	}
	public void setPARENT_TASK_ID_(String pARENT_TASK_ID_) {
		PARENT_TASK_ID_ = pARENT_TASK_ID_;
	}
	public String getDESCRIPTION_() {
		return DESCRIPTION_;
	}
	public void setDESCRIPTION_(String dESCRIPTION_) {
		DESCRIPTION_ = dESCRIPTION_;
	}
	public String getTASK_DEF_KEY_() {
		return TASK_DEF_KEY_;
	}
	public void setTASK_DEF_KEY_(String tASK_DEF_KEY_) {
		TASK_DEF_KEY_ = tASK_DEF_KEY_;
	}
	public String getASSIGNEE_() {
		return ASSIGNEE_;
	}
	public void setASSIGNEE_(String aSSIGNEE_) {
		ASSIGNEE_ = aSSIGNEE_;
	}
	public String getDELEGATION_() {
		return DELEGATION_;
	}
	public void setDELEGATION_(String dELEGATION_) {
		DELEGATION_ = dELEGATION_;
	}
	public String getPRIORITY_() {
		return PRIORITY_;
	}
	public void setPRIORITY_(String pRIORITY_) {
		PRIORITY_ = pRIORITY_;
	}
	public Date getCREATE_TIME_() {
		return CREATE_TIME_;
	}
	public void setCREATE_TIME_(Date cREATE_TIME_) {
		CREATE_TIME_ = cREATE_TIME_;
	}
	public String getDUE_DATE_() {
		return DUE_DATE_;
	}
	public void setDUE_DATE_(String dUE_DATE_) {
		DUE_DATE_ = dUE_DATE_;
	}
	public String getCATEGORY_() {
		return CATEGORY_;
	}
	public void setCATEGORY_(String cATEGORY_) {
		CATEGORY_ = cATEGORY_;
	}
	public String getSUSPENSION_STATE_() {
		return SUSPENSION_STATE_;
	}
	public void setSUSPENSION_STATE_(String sUSPENSION_STATE_) {
		SUSPENSION_STATE_ = sUSPENSION_STATE_;
	}
	public String getFORM_KEY_() {
		return FORM_KEY_;
	}
	public void setFORM_KEY_(String fORM_KEY_) {
		FORM_KEY_ = fORM_KEY_;
	}
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
	
	
	
}
