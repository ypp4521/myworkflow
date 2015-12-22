package org.zywx.appdo.flow.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zywx.appdo.common.AjaxResult;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.flow.entity.LoginUserInfo;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.BpmApproveService;
import org.zywx.appdo.flow.service.BpmTodoService;
import org.zywx.appdo.flow.service.WorkflowService;
import org.zywx.appdo.flow.service.WorkflowTraceService;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.meta.entity.MetaBusi;
import org.zywx.appdo.meta.entity.MetaCustom;
import org.zywx.appdo.meta.entity.MetaCustomField;
import org.zywx.appdo.meta.service.MetaBusiService;
import org.zywx.appdo.meta.service.MetaCustomFieldService;
import org.zywx.appdo.meta.service.MetaCustomService;
import org.zywx.appdo.utils.PropertyTools;

/**
 * 流程管理控制器
 *
 * @author qf
 */
@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WorkflowUserService userService;
	@Autowired
	protected WorkflowTraceService traceService;
	@Autowired
	private BpmTodoService bpmTodoService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private BpmApproveService bpmApproveService;
	@Autowired
	private MetaCustomService metaCustomService;
	@Autowired
	private MetaBusiService metaBusiService;
	@Autowired
	private MetaCustomFieldService customFieldService;
	/**
	 * 参数集合变量<br/>
	 * 集合中必须包含的参数有：如果值为空，就是value也空，key要有<br/>
	 * 租户 tenantId<br/>
	 * 当前登录人 user_id<br/>
	 * 流程编号 prco_inset_id <br/>
	 * 流程模版编号 meta_id <br/>
	 */
	Map<String, Object> paraMap = null;
	AjaxResult ar = new AjaxResult();
	String result = "";

	/**
	 * 流程设计器
	 * 
	 * @return
	 */
	@RequestMapping("modeler")
	public ModelAndView openModeler(String modelId, HttpServletRequest request) {
		Model model = workflowService.queryModel(modelId);
		String dis = request.getParameter("dis");
		if (!"".equals(dis) && null != dis) {
			request.setAttribute("dis", dis);
		}
		request.setAttribute("key", model.getKey());
		ModelAndView modelAndView = new ModelAndView("workflow/modeler");
		return modelAndView;
	}

	/**
	 * easyui流程设计器
	 * 
	 * @param procdefId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/designer/{procdefId}")
	public ModelAndView opendesigner(@PathVariable("procdefId") String procdefId, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/designer");
		return modelAndView;
	}

	/**
	 * 流程跟踪
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("diagram")
	public ModelAndView diagramViewer(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/diagram");
		return modelAndView;
	}

	@RequestMapping("LeaveRequest")
	public ModelAndView diagramViewera(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/LeaveRequest");
		return modelAndView;
	}

	/**
	 * 测试功能列表，正式系统需要将改列表增加到菜单中
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("formList")
	public ModelAndView formList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("meta/formList");
		request.setAttribute("listMeta",
				metaCustomService.getList(getUserInfo(request).getTenantId().toString(), null));
		return modelAndView;
	}

	/**
	 * 为流程图提供流程定义xml
	 * 
	 * @return
	 */
	@RequestMapping(value = "/processDefXML")
	public void processDefXML(@RequestParam("procdefId") String procdefId, HttpServletResponse response) {
		try {
			InputStream resourceAsStream = workflowService.processDefXML(procdefId);
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 获取草稿箱列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("draftList")
	public ModelAndView draftList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/draftList");
		return modelAndView;
	}
	/**
	 * 待签收列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("claimList")
	public ModelAndView claimList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/claimList");
		return modelAndView;
	}

	/**
	 * 待签收数据
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "dataGridClaim")
	@ResponseBody
	public DataGrid dataGridClaim(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		List<String> groupList = new ArrayList<String>();
		Staff staff = userService.getByUniqueField(getUserInfo(request).getUserId());
		groupList.add(staff.getDptId());
		return bpmTodoService.dataGridClaim(PropertyTools.getPropertyByKey("tenantId"),
				getUserInfo(request).getUserId(), groupList, dataGrid);
	}

	/**
	 * 我的提交列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("submitList")
	public ModelAndView submitList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/submitList");
		return modelAndView;
	}

	/**
	 * 我的提交数据
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	/*@RequestMapping(value = "dataGridSubmit")
	@ResponseBody
	public DataGrid dataGridSubmit(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return bpmTodoService.dataGridSubmit("611",
				"ZY0502", dataGrid);
	}*/

	/**
	 * 待办数据
	 * 
	 * @param id
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	//@RequestMapping(value = "dataGridTodo")
	//@ResponseBody
	/*public DataGrid dataGridTodo(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		//Map<String, Object> map = new HashMap<String, Object>();
		map.put("tenantId",getUserInfo(request).getTenantId().toString());
		map.put("userId",getUserInfo(request).getUserId());
		//return bpmTodoService.dataGridTodo(map, dataGrid);
	}*/

	/**
	 * 待办列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("todoList")
	public ModelAndView todoList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/todoList");
		return modelAndView;
	}

	/**
	 * 已办列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("approvedList")
	public ModelAndView approvedList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/approvedList");
		return modelAndView;
	}

	/**
	 * 获取已办列表数据
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "dataGridApproved")
	@ResponseBody
	public DataGrid dataGridApproved(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return bpmTodoService.dataGridApproved(getUserInfo(request).getTenantId().toString(),
				getUserInfo(request).getUserId(), dataGrid);
	}

	/**
	 * 查看审批历史
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("dataGridApprove/{instanceid}")
	@ResponseBody
	public DataGrid dataGridApprove(@PathVariable("instanceid") String instanceid, HttpServletRequest request,
			DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return bpmApproveService.dataGridApprove(getUserInfo(request).getTenantId().toString(), getUserInfo(request).getTenantId().toString(),
				instanceid, dataGrid);
	}

	/**
	 * 签收任务
	 * 
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping("claimTask/{taskId}")
	@ResponseBody
	public AjaxResult claimTask(@PathVariable("taskId") String taskId, HttpServletRequest request) {
		try {
			workflowService.claimTask(taskId, getUserInfo(request).getUserId());
			ar.setStatus("000");
			ar.setInfo("签收成功");
		} catch (Exception e) {
			ar.setInfo("签收失败");
		}
		return ar;
	}

	/**
	 * 弹出提交流程窗口
	 * 
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping("toCommitTask/{taskId}")
	@ResponseBody
	public AjaxResult toCommitTask(@PathVariable("taskId") String taskId, HttpServletRequest request) {
		try {
			List<Map<String, Object>> listVal = new ArrayList<Map<String, Object>>();
			workflowService.findVal(taskId, listVal);
			ar.setResult(listVal);
			ar.setStatus("000");
			ar.setInfo("获取表单成功");
		} catch (Exception e) {
			ar.setInfo("获取表单失败");
		}
		return ar;
	}

	/**
	 * 提交流程
	 * 
	 * @param taskId
	 * @param approve
	 * @param approveResult
	 * @param request
	 * @return
	 */
	@RequestMapping("commitTask/{taskId}")
	@ResponseBody
	public AjaxResult commitTask(@PathVariable("taskId") String taskId, String approve, String approveResult,
			HttpServletRequest request) {
		try {
			bpmApproveService.completeTask(taskId, approve, approveResult,
					getUserInfo(request).getTenantId().toString());
			ar.setStatus("000");
			ar.setInfo("提交成功");
		} catch (Exception e) {
			ar.setInfo("提交失败" + e.getMessage());
		}
		return ar;
	}

	/**
	 * 回收
	 * 
	 * @param taskId
	 * @param approve
	 * @param approveResult
	 * @param request
	 * @return
	 */
	@RequestMapping("backTask/{instanceid}")
	@ResponseBody
	public AjaxResult backTask(@PathVariable("instanceid") String instanceid, String approveResult,
			HttpServletRequest request) {
		try {
			bpmApproveService.backTask(getUserInfo(request).getTenantId().toString(), instanceid,
					getUserInfo(request).getUserId());
			ar.setStatus("000");
			ar.setInfo("回收成功");
		} catch (Exception e) {
			ar.setInfo("回收失败" + e.getMessage());
		}
		return ar;
	}

	/**
	 * 获取可驳回节点
	 * 
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping("toRejectTask/{taskId}")
	@ResponseBody
	public AjaxResult toRejectTask(@PathVariable("taskId") String taskId, HttpServletRequest request) {
		try {
			ar.setResult(workflowService.toRejectTask(taskId));
			ar.setStatus("000");
			ar.setInfo("获取成功");
		} catch (Exception e) {
			ar.setInfo("获取失败" + e.getMessage());
		}
		return ar;
	}

	/**
	 * 驳回
	 * 
	 * @param taskId
	 * @param taskKey
	 * @param approveResult
	 * @param request
	 * @return
	 */
	@RequestMapping("rejectTask/{taskId}")
	@ResponseBody
	public AjaxResult rejectTask(@PathVariable("taskId") String taskId, String taskKey, String approveResult,
			HttpServletRequest request) {
		try {
			ar.setResult(bpmApproveService.rejectTask(taskId, taskKey, approveResult,
					getUserInfo(request).getTenantId().toString()));
			ar.setStatus("000");
			ar.setInfo("驳回成功");
		} catch (Exception e) {
			ar.setInfo("驳回失败" + e.getMessage());
		}
		return ar;
	}

	/**
	 * 流程定义列表
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("processDefList/{id}")
	public ModelAndView processDefList(@PathVariable("id") String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		ModelAndView modelAndView = new ModelAndView("workflow/processDefList");
		return modelAndView;
	}

	/**
	 * 获取流程定义列表数据
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("dataGridProcessDef/{id}")
	@ResponseBody
	public DataGrid dataGridProcessDef(@PathVariable("id") String id, HttpServletRequest request, DataGrid dataGrid,
			int rows) {
		dataGrid.setPageSize(rows);
		return workflowService.dataGridProcessDef(getUserInfo(request).getTenantId().toString(), id, dataGrid);
	}

	/**
	 * 为管理员提供流程实例跟踪列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("processInstanceList")
	public ModelAndView processInstanceList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/processInstanceList");
		return modelAndView;
	}

	/**
	 * 已完成流程实例列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("processInstanceHisList")
	public ModelAndView processInstanceHisList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("workflow/processInstanceHisList");
		return modelAndView;
	}
	

	@RequestMapping("dataGridProcessInstance")
	@ResponseBody
	public DataGrid dataGridProcessInstance(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return workflowService.dataGridProcessInstance("611",
				"ZY0502", dataGrid);
	}

	/**
	 * 已完成流程实例数据
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("dataGridProcessInstanceHis")
	@ResponseBody
	public DataGrid dataGridProcessInstanceHis(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return workflowService.dataGridProcessInstanceHis(getUserInfo(request).getTenantId().toString(),
				getUserInfo(request).getUserName(), dataGrid);
	}

	/**
	 * 挂起，恢复流程
	 * 
	 * @param opttype
	 * @param instanceid
	 * @param request
	 * @return
	 */
	@RequestMapping("isSuspended/{opttype}/{instanceid}")
	@ResponseBody
	public AjaxResult isSuspended(@PathVariable("opttype") String opttype,
			@PathVariable("instanceid") String instanceid, HttpServletRequest request) {
		try {
			if (opttype.equals("active")) {
				workflowService.activateProcessInstanceById(instanceid);
			} else if (opttype.equals("suspend")) {
				workflowService.suspendProcessInstanceById(instanceid);
			}
			ar.setStatus("000");
			ar.setInfo("操作成功");
		} catch (Exception e) {
			ar.setInfo("操作失败");
		}
		return ar;
	}

	@RequestMapping(value = "processImage/{instanceid}")
	public void processImage(@PathVariable("instanceid") String instanceid, HttpServletResponse response) {
		InputStream imageStream = workflowService.processImage(instanceid);
		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		try {
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
		}
	}

	/**
	 * 读取资源，通过部署ID
	 *
	 * @param processDefinitionId
	 *            流程定义
	 * @param resourceType
	 *            资源类型(xml|image)
	 * @throws Exception
	 */
	@RequestMapping("loadResource/{resourceType}/{processDefinitionId}")
	public void loadResource(@PathVariable("processDefinitionId") String processDefinitionId,
			@PathVariable("resourceType") String resourceType, HttpServletResponse response) throws Exception {
		InputStream resourceAsStream = workflowService.loadResource(resourceType, processDefinitionId);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 读取资源，通过流程ID
	 *
	 * @param resourceType
	 *            资源类型(xml|image)
	 * @param processInstanceId
	 *            流程实例ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "resource/process-instance")
	public void loadByProcessInstance(@RequestParam("resourceType") String resourceType,
			@RequestParam("processInstanceId") String processInstanceId, HttpServletResponse response)
					throws Exception {
		InputStream resourceAsStream = workflowService.loadByProcessInstance(resourceType, processInstanceId);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 输出跟踪流程信息
	 *
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "process/trace/{pid}")
	@ResponseBody
	public List<Map<String, Object>> traceProcess(@PathVariable("pid") String processInstanceId) throws Exception {
		List<Map<String, Object>> activityInfos = traceService.traceProcess(processInstanceId);
		return activityInfos;
	}

	/**
	 * 读取带跟踪的图片
	 */
	@RequestMapping(value = "process/trace/auto/{executionId}")
	public void readResource(@PathVariable("executionId") String executionId, HttpServletResponse response)
			throws Exception {
		InputStream imageStream = workflowService.readResource(executionId);
		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 节点列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("activityList/{id}/{name}")
	public ModelAndView activityList(@PathVariable("id") String id,@PathVariable("name") String name, HttpServletRequest request) {
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		String tenantId = getUserInfo(request).getTenantId().toString();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flowid", id.split("\\:")[0]);
		paramMap.put("tenantId", tenantId);
		//查询流程模版
		MetaBusi metaBusi = metaBusiService.getByMap(tenantId, paramMap);
		if (null == metaBusi || null==metaBusi.getFlowid() || "".equals(metaBusi.getFlowid())) {
			request.setAttribute("error", "选择的表单模版没有绑定流程");
		}else{
			MetaCustom custom = metaCustomService.getById(tenantId, Long.valueOf(metaBusi.getMetaid()));
			//查询所有表单中意见字段
			String metaid = metaBusi.getMetaid().toString();
			paramMap.put("metaid", metaid);
			paramMap.put("isOpinion", "1");
			request.setAttribute("metaBusi_id", metaBusi.getId());
			request.setAttribute("metaBusi_name", metaBusi.getBusiname());
			request.setAttribute("metaBusi_code", metaBusi.getBusicode());
			
			request.setAttribute("custom_id", custom.getId());
			request.setAttribute("custom_name", custom.getMetaname());
			request.setAttribute("custom_code", custom.getMetacode());
			List<MetaCustomField> fieldList = customFieldService.getList(tenantId, paramMap);
			if (null==fieldList || fieldList.isEmpty()) {
				request.setAttribute("error", "模版表中没有设置相关字段请到元数据模版中设置字段");
			}else{
				request.setAttribute("fieldList", fieldList);
			}
		}
		ModelAndView modelAndView = new ModelAndView("workflow/activityList");
		return modelAndView;
	}

	/**
	 * 获取节点列表
	 * 
	 * @param processDefinitionId
	 * @param request
	 * @return
	 */
	@RequestMapping("dataGridActivity/{processDefinitionId}")
	@ResponseBody
	public DataGrid dataGridActivity(@PathVariable("processDefinitionId") String processDefinitionId,
			HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		dg.setRows(workflowService.queryActivity(processDefinitionId));
		return dg;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	public LoginUserInfo getUserInfo(HttpServletRequest request) {
		return (LoginUserInfo) request.getSession().getAttribute("userInfo");
	}
}