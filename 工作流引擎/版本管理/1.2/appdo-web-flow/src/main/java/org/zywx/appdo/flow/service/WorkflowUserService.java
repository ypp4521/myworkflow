package org.zywx.appdo.flow.service;

import java.util.List;

import org.zywx.appdo.flow.entity.Department;
import org.zywx.appdo.flow.entity.Staff;

/**
 * 查询用户服务
 * @author xingshen.zhao
 *
 */
public interface WorkflowUserService { 
	
	
	/**
	 * 根据ID查询人员详细信息
	 * @param staff
	 * @return
	 */
	public Staff getByUniqueField(String userId);

	
	/**
	 * 根据多个用户id查询用户信息
	 * @param listId
	 * @return
	 */
	public List<Staff> getByUniqueFieldArr(String listId);

	/**
	 * 根据用户名模糊
	 * @param userName
	 * @return
	 */
	public List<Staff> getPersonnelByName(String userName);

	/**
	 * 查询组织机构下员工
	 * @param deptId
	 * @return
	 */
	public List<Staff> getPersonnel(String deptId);

	/**
	 * 返回一个部门下所有的子部门
	 * @param deptId
	 * @return
	 */
	public List<Department> danPersonnel(String deptId);
	
	/**
	 * 获取Ticket用户信息
	 * @param ssoURL
	 * @param ticket
	 * @param baseURL
	 * @return
	 */
	public String getTicketInfo(String ssoURL,String ticket,String baseURL);

}
