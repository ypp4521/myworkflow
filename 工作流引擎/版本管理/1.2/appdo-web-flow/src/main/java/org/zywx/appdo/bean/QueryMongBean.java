package org.zywx.appdo.bean;

import java.io.Serializable;
import java.util.Map;

import org.zywx.appdo.utils.MyJsonUtil;

import com.alibaba.dubbo.common.json.JSONObject;


/**
 * 
 * 项目名称：appdo-web-flow <br/>
 * 类名称：QueryMongBean <br/>
 * 类描述： 用户查询mongdb数据对象json使用 <br/>
 * 创建人：pengpeng.yuan@zymobi.com <br/>
 * 创建时间：2015年11月9日 下午5:03:40 <br/>
 * 修改人：Yuanpp <br/>
 * 文件名：QueryMongBean.java <br/>
 * 版本信息：1.0 <br/>
 * 正益移动互联科技股份有限公司 Copyright Corporation(2015) <br/>
 * 
 * @version 1.0<br/>
 */
public class QueryMongBean implements Serializable {

	/**
	 * 
	 * 创建一个新的实例 QueryMongBean.   <br/>
	 */
	public QueryMongBean(){
		
	}
	/**
	 * 提供构造方法，参数需要json格式字符串 <br/>
	 * 创建一个新的实例 QueryMongBean. <br/>
	 * 
	 * @param jsonString
	 */
	public static QueryMongBean getQueryMongBean(String jsonString) {
		QueryMongBean queryMongBean = MyJsonUtil.convertJsonToQueryMongBean(jsonString);
		return queryMongBean;
	}

	private static final long serialVersionUID = 7236115776920634827L;
	/**
	 * mongdb保存区分表
	 */
	private String entityTypeId;
	/**
	 * 对应模版的具体内容 json字符串
	 * <br/>
	 * Object对应 map<string,object> or jsonobject
	 */
	private Object entity;
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	/**
	 * emoa操作用户
	 */
	private String operateUserId;
	/**
	 * 用户id
	 */
	private String userId;
	private String userType;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 对应模版内容mongdb生成的id
	 */
	private String entityId;
	/**
	 * 操作类型
	 */
	private String operateTypeId;
	/**
	 * mongdb中主键key
	 */
	private String objectId;
	/**
	 * 创建时间
	 */
	private String createdAt;
	/**
	 * 当前年度
	 */
	private String year;

	/**
	 * 对应租户id
	 */
	private String tenantId;
	
	/**
	 * 是否重要员工
	 */
	private String important;

	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	public String getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(String entityTypeId) {
		this.entityTypeId = entityTypeId;
	}


	public String getOperateUserId() {
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getOperateTypeId() {
		return operateTypeId;
	}

	public void setOperateTypeId(String operateTypeId) {
		this.operateTypeId = operateTypeId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}
