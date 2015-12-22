package org.zywx.appdo.flow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.flow.entity.Department;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.utils.PropertyTools;

/**
 * 说明：目前取用户，组织机构信息是从3.3版本提供的接口去取
 * 	         切换到4.0时，修改该文件，取4.0提供的接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/userGroup")
public class UserGroupController {
	@Autowired
	private WorkflowUserService userService;

	/**
	 * 获取人员列表
	 * 
	 * @return
	 */
	@RequestMapping("assignmentList")
	@ResponseBody
	public DataGrid assignmentList(HttpServletRequest request, DataGrid dataGrid, int rows) {
		List<Staff> userList =userService.getPersonnel(PropertyTools.getPropertyByKey("superDept"));
		dataGrid.setRows(userList);
		return dataGrid;
	}

	/**
	 * 获取人员列表
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("candidateUserList")
	@ResponseBody
	public DataGrid candidateUserList(HttpServletRequest request, DataGrid dataGrid, int rows) {
		List<Staff> userList =userService.getPersonnel(PropertyTools.getPropertyByKey("superDept"));
		dataGrid.setRows(userList);
		dataGrid.setTotal(100);
		return dataGrid;
	}

	/**
	 * 查询部门
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("candidateGroupList")
	@ResponseBody
	public DataGrid candidateGroupList(HttpServletRequest request, DataGrid dataGrid, int rows) {
		List<Department> groupList = userService.danPersonnel(PropertyTools.getPropertyByKey("superDept4Org"));
		dataGrid.setRows(groupList);
		return dataGrid;
	}

	/**
	 * 得到所有用户
	 * 
	 * @param request
	 * @param dataGrid
	 * @return
	 */
	@RequestMapping("candidateUserListAll")
	@ResponseBody
	public DataGrid candidateUserListAll(HttpServletRequest request, DataGrid dataGrid) {
		List<Staff> userList = userService.getPersonnel(PropertyTools.getPropertyByKey("superDept"));
		dataGrid.setRows(userList);
		return dataGrid;
	}
}
