package org.zywx.appdo.flow.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zywx.appdo.flow.entity.Department;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.WorkflowSendPostService;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.utils.PropertyTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 人员通用服务
 * 
 * @author Administrator
 *
 */
@Component
public class WorkflowUserServiceImpl implements WorkflowUserService {
	@Autowired
	protected WorkflowSendPostService sendPostService;
	private static String emmUrl;
	private static String domainName;

	static {
		try {
			emmUrl = PropertyTools.getPropertyByKey("emmUrl");
			domainName = URLEncoder.encode(PropertyTools.getPropertyByKey("domainName"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询人员详细信息
	 * 
	 * @param staff
	 * @return
	 */
	@Override
	public Staff getByUniqueField(String userId) {
		String param = "uniqueField=" + userId + "&domainName=" + domainName;
		String emmInterface = "/mum/personnel/getByUniqueField";
		String url = emmUrl + emmInterface;
		String str = sendPostService.sendGet(url, param);
		JSONArray jsArr = JSON.parseArray(str);
		if (jsArr.size() == 0) {
			try {
				throw new Exception("staff is not exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Staff staff = new Staff();
			return staff;
		} else {
			JSONObject jsobj = jsArr.getJSONObject(0);
			Staff staff = new Staff();
			staff.setStaffId(jsobj.getString("uniqueField"));
			staff.setFullName(jsobj.getString("name"));
			staff.setSexId(jsobj.getString("userSex"));
			staff.setMobileNo(jsobj.getString("mobileNo"));
			staff.setEmail(jsobj.getString("email"));
			staff.setLoginName(jsobj.getString("loginName"));
			staff.setTeleNo(jsobj.getString("officePhone"));
			staff.setUserIcon(jsobj.getString("avatorLoc"));
			staff.setRoleId(jsobj.getString("jobId"));
			staff.setRoleName(jsobj.getString("jobName"));
			String brcId = jsobj.getString("topOrgIds").split(",")[0];
			String dptId = jsobj.getString("orgIds").split(",")[0];
			String[] orgNameArr = jsobj.getString("orgName").split(",")[0].split("/");
			String brcName = orgNameArr[0];
			String dptName = orgNameArr[orgNameArr.length - 1];
			staff.setBrcId(brcId);
			staff.setBrcName(brcName);
			staff.setDptId(dptId);
			staff.setDptName(dptName);
			staff.setLdrUserId(jsobj.getString("higherLevelUniqueField"));
			staff.setLdrFullName(jsobj.getString("higherLevelName"));
			return staff;
		}
	}

	/**
	 * 根据多个用户id查询用户信息
	 * 
	 * @param listId
	 * @return
	 */
	@Override
	public List<Staff> getByUniqueFieldArr(String listId) {
		String param = "uniqueField=" + listId + "&domainName=" + domainName;
		String emmInterface = "/mum/personnel/getByUniqueFieldArr";
		String url = emmUrl + emmInterface;
		String str = sendPostService.sendGet(url, param);
		JSONArray jsArr = JSON.parseArray(str);
		if (jsArr == null || jsArr.size() == 0) {
			List<Staff> list = new ArrayList<Staff>();
			return list;
		} else {
			List<Staff> list = new ArrayList<Staff>();
			for (int i = 0; i < jsArr.size(); i++) {
				JSONObject jsobj = jsArr.getJSONObject(i);
				Staff staff = new Staff();
				staff.setStaffId(jsobj.getString("uniqueField"));
				staff.setFullName(jsobj.getString("name"));
				staff.setSexId(jsobj.getString("userSex"));
				staff.setMobileNo(jsobj.getString("mobileNo"));
				staff.setEmail(jsobj.getString("email"));
				staff.setLoginName(jsobj.getString("loginName"));
				staff.setTeleNo(jsobj.getString("officePhone"));
				staff.setUserIcon(jsobj.getString("avatorLoc"));
				staff.setRoleId(jsobj.getString("jobId"));
				staff.setRoleName(jsobj.getString("jobName"));
				String brcId = jsobj.getString("topOrgIds").split(",")[0];
				String dptId = jsobj.getString("orgIds").split(",")[0];
				String[] orgNameArr = jsobj.getString("orgName").split(",")[0].split("/");
				String brcName = orgNameArr[0];
				String dptName = orgNameArr[orgNameArr.length - 1];
				staff.setBrcId(brcId);
				staff.setBrcName(brcName);
				staff.setDptId(dptId);
				staff.setDptName(dptName);
				staff.setLdrUserId(jsobj.getString("higherLevelUniqueField"));
				staff.setLdrFullName(jsobj.getString("higherLevelName"));
				list.add(staff);
			}
			return list;
		}
	}

	/**
	 * 根据用户名模糊
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public List<Staff> getPersonnelByName(String userName) {
		String param = "userName=" + userName + "&domainName=" + domainName;
		String emmInterface = "/mum/personnel/getPersonnelByName";
		String url = emmUrl + emmInterface;
		String str = sendPostService.sendGet(url, param);
		JSONArray jsArr = JSON.parseArray(str);
		if (jsArr.size() == 0) {
			List<Staff> list = new ArrayList<Staff>();
			return list;
		} else {
			List<Staff> list = new ArrayList<Staff>();
			for (int i = 0; i < jsArr.size(); i++) {
				JSONObject jsobj = jsArr.getJSONObject(i);
				Staff staff = new Staff();
				staff.setStaffId(jsobj.getString("uniqueField"));
				staff.setFullName(jsobj.getString("name"));
				staff.setSexId(jsobj.getString("userSex"));
				staff.setMobileNo(jsobj.getString("mobileNo"));
				staff.setEmail(jsobj.getString("email"));
				staff.setLoginName(jsobj.getString("loginName"));
				staff.setTeleNo(jsobj.getString("officePhone"));
				staff.setUserIcon(jsobj.getString("avatorLoc"));
				staff.setRoleId(jsobj.getString("jobId"));
				staff.setRoleName(jsobj.getString("jobName"));
				String brcId = jsobj.getString("topOrgIds").split(",")[0];
				String dptId = jsobj.getString("orgIds").split(",")[0];
				String[] orgNameArr = jsobj.getString("orgName").split(",")[0].split("/");
				String brcName = orgNameArr[0];
				String dptName = orgNameArr[orgNameArr.length - 1];
				staff.setBrcId(brcId);
				staff.setBrcName(brcName);
				staff.setDptId(dptId);
				staff.setDptName(dptName);
				staff.setLdrUserId(jsobj.getString("higherLevelUniqueField"));
				staff.setLdrFullName(jsobj.getString("higherLevelName"));
				list.add(staff);
			}
			return list;
		}
	}

	/**
	 * 查询组织机构下员工
	 * 
	 * @param deptId
	 * @return
	 */
	@Override
	public List<Staff> getPersonnel(String deptId) {
		String param = "orgId=" + deptId + "&domainName=" + domainName;
		String emmInterface = "/mum/personnel/getPersonnel";
		String url = emmUrl + emmInterface;
		String str = sendPostService.sendGet(url, param);
		JSONObject jsonStr= (JSONObject) JSON.parse(str);
		JSONArray jsArr = JSON.parseArray(jsonStr.get("userList").toString());
		
		if (jsArr.size() == 0) {
			List<Staff> list = new ArrayList<Staff>();
			return list;
		} else {
			List<Staff> list = new ArrayList<Staff>();
			for (int i = 0; i < jsArr.size(); i++) {
				JSONObject jsobj = jsArr.getJSONObject(i);
				Staff staff = new Staff();
				staff.setStaffId(jsobj.getString("uniqueField"));
				staff.setFullName(jsobj.getString("name"));
				staff.setSexId(jsobj.getString("userSex"));
				staff.setMobileNo(jsobj.getString("mobileNo"));
				staff.setEmail(jsobj.getString("email"));
				staff.setLoginName(jsobj.getString("loginName"));
				staff.setTeleNo(jsobj.getString("officePhone"));
				staff.setUserIcon(jsobj.getString("avatorLoc"));
				staff.setRoleId(jsobj.getString("jobId"));
				staff.setRoleName(jsobj.getString("jobName"));
				String brcId = jsobj.getString("topOrgIds").split(",")[0];
				String dptId = jsobj.getString("orgIds").split(",")[0];
				String[] orgNameArr = jsobj.getString("orgName").split(",")[0].split("/");
				String brcName = orgNameArr[0];
				String dptName = orgNameArr[orgNameArr.length - 1];
				staff.setBrcId(brcId);
				staff.setBrcName(brcName);
				staff.setDptId(dptId);
				staff.setDptName(dptName);
				staff.setLdrUserId(jsobj.getString("higherLevelUniqueField"));
				staff.setLdrFullName(jsobj.getString("higherLevelName"));
				list.add(staff);
			}
			return list;
		}
	}

	/**
	 * 返回一个部门下所有的子部门
	 * 
	 * @param deptId
	 * @return
	 */
	@Override
	public List<Department> danPersonnel(String deptId) {
		String param = "orgId=" + deptId + "&domainName=" + domainName;
		String emmInterface = "/mum/personnel/danPersonnel";
		String url = emmUrl + emmInterface;
		String str = sendPostService.sendGet(url, param);
		System.out.println("==========danPersonnel===========[" + str + "]");
		if (str == null && "".equals(str)) {
			List<Department> list = new ArrayList<Department>();
			return list;
		}
		JSONArray jsArr = JSON.parseArray(str);
		if (jsArr.size() == 0) {
			List<Department> list = new ArrayList<Department>();
			return list;
		} else {
			List<Department> list = new ArrayList<Department>();
			for (int i = 0; i < jsArr.size(); i++) {
				JSONObject jsobj = jsArr.getJSONObject(i);
				Department department = new Department();
				department.setDtpId(jsobj.getString("id"));
				department.setDptName(jsobj.getString("name"));
				department.setSuperDptId(jsobj.getString("pId"));
				list.add(department);
			}
			return list;
		}
	}

	/**
	 * 获取ticket信息
	 */
	@Override
	public String getTicketInfo(String ssoURL, String ticket, String baseURL) {
		String param = "ticket=" + ticket + "&service=" + baseURL;
		String url = ssoURL+"/serviceValidate";
		String str = sendPostService.sendGet(url, param);
		System.out.println("==========getTicketInfo===========[" + str + "]");
		return str;
	}
}
