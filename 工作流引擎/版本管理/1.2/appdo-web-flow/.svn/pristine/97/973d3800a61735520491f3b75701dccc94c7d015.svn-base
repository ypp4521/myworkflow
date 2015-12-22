package org.zywx.appdo.flow.entity;

import org.zywx.appdo.common.entity.AbstractBaseEntity;
import org.zywx.appdo.utils.MyStringUtils;

/**
 * 用户信息
 * 
 * @author xingshen.zhao
 *
 */
public class LoginUserInfo extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	private String suffix;// url后缀
	private String userId;// 用户ID
	private String userName;// 用户名称
	private String deptId;// 部门ID
	private String deptName;// 部门名称
	private Long tenantId;// 租户ID
	private String tenantName;// 租户名称
	private String loginTime;//登录时间

	public String getLoginTime() {
		return MyStringUtils.now("yyyy-MM-dd");
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
}
