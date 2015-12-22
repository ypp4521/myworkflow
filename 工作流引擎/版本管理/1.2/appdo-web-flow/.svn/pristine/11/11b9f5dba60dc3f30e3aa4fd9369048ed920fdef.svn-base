package org.zywx.appdo.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.cxf.common.i18n.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zywx.appdo.bean.QueryWorkFlowAndFieldBean;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBizImpl;
import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.common.page.PageBean;
import org.zywx.appdo.common.page.PageParam;
import org.zywx.appdo.flow.dao.BpmTodoDao;
import org.zywx.appdo.flow.entity.BpmTodo;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.BpmTodoService;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.meta.service.MetaBusiService;
import org.zywx.appdo.meta.service.MetaCustomFieldService;
import org.zywx.appdo.utils.MyStringUtils;
import org.zywx.appdo.workflow.WorkflowTaskService;

@Service
public class BpmTodoServiceImpl extends BaseBizImpl<BpmTodo> implements BpmTodoService {

	@Autowired
	private BpmTodoDao bpmTodoDao;
	@Autowired
	private WorkflowTaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private WorkflowUserService userService;
	@Autowired
	private MetaCustomFieldService metaCustomFieldService;
	@Autowired
	private MetaBusiService metaBusiService;

	@Override
	protected BaseDao<BpmTodo> getDao() {
		return bpmTodoDao;
	}

	/**
	 * 
	 * getBusiCodeByMetaId(根据租户id和metaid查询当前模版单据类型) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param BpmTodoServiceImpl
	 *            <br/>
	 * @return String <br/>
	 * @method @param tenantId
	 * @method @param metaid
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	public String getBusiCodeByMetaId(String tenantId, String metaid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("metaid", metaid);
		String busicode = metaBusiService.getByMap(tenantId, paramMap).getBusicode();
		return busicode;
	}

	/**
	 * 待签收数据
	 */
	@Override
	public DataGrid dataGridClaim(String tenantId, String userId, List<String> candidateGroups, DataGrid dataGrid) {
		// 任务查询
		TaskQuery query = taskService.createTaskQuery().taskTenantId(tenantId).or().taskCandidateUser(userId)
				.taskCandidateGroupIn(candidateGroups).endOr().orderByTaskCreateTime().desc().active();
		if (query == null || query.list() == null || query.count() == 0) {
			return dataGrid;
		}
		// 总数
		// 分页10条
		List<Task> claimList = query.list();
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		BpmTodo bpmTodo = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (Task task : claimList) {
			paramMap.put("instanceid", task.getProcessInstanceId());
			bpmTodo = super.getByMap(tenantId, paramMap);
			if (bpmTodo == null) {
				continue;
			}
			bpmTodo.setBusicode(getBusiCodeByMetaId(tenantId, bpmTodo.getMetaid()));
			bpmTodo.setTaskId(task.getId());
			bpmTodo.setUrl(task.getFormKey());
			bpmTodo.setNodeName(task.getName());
			bpmTodo.setCreatetime(bpmTodo.getCreatetime());
			bpmTodo.setSubmitAt(MyStringUtils.getDateStr(task.getCreateTime()));
			bpmTodo.setSubmitUser(getLastUserInfo(task.getProcessInstanceId()));
			listTodo.add(bpmTodo);
		}
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodo> metaList = new PageBean<BpmTodo>(pageParam, claimList.size(), listTodo);
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(claimList.size());
		dataGrid.setPageCount(metaList.getTotalPage());
		return dataGrid;
	}

	/**
	 * 待办数据
	 */
	@Override
	public DataGrid dataGridTodo(String tenantId, String userId, DataGrid dataGrid) {
		TaskQuery query = taskService.createTaskQuery().taskTenantId(tenantId).taskAssignee(userId)
				.orderByTaskCreateTime().desc().active();
		if (query == null || query.list() == null || query.count() == 0) {
			return dataGrid;
		}
		// 分页10条
		List<Task> claimList = query.list();
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		BpmTodo bpmTodo = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (Task task : claimList) {
			paramMap.put("instanceid", task.getProcessInstanceId());
			bpmTodo = super.getByMap(tenantId, paramMap);
			if (null == bpmTodo) {
				continue;
			}
			if (userId.equals(bpmTodo.getUserId()))
				continue;
			bpmTodo.setTaskId(task.getId());
			paramMap.put("wf_tenantId", tenantId);
			paramMap.put("wf_node_id", task.getTaskDefinitionKey());
			paramMap.put("wf_modelkey", task.getProcessDefinitionId());
			// 根据 流程节点id 查询 表: wf_meta_flow_field 配置的意见节点信息
			QueryWorkFlowAndFieldBean fieldBean = metaCustomFieldService.getWorkFlowNodesList(paramMap);
			if (null != fieldBean) {
				bpmTodo.setOpinionField(fieldBean.getWf_meta_custom_field_code());
			} else {
				bpmTodo.setOpinionField("0");
			}

			bpmTodo.setBusicode(getBusiCodeByMetaId(tenantId, bpmTodo.getMetaid()));
			bpmTodo.setUrl(task.getFormKey());
			bpmTodo.setNodeName(task.getName());
			bpmTodo.setSubmitUser(getLastUserInfo(task.getProcessInstanceId()));
			//提交时间
			bpmTodo.setSubmitAt(MyStringUtils.dateToString(task.getCreateTime(), "YYYY-MM-dd HH:mm:ss"));
			listTodo.add(bpmTodo);
		}
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodo> metaList = new PageBean<BpmTodo>(pageParam, claimList.size(), listTodo);
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(listTodo.size());
		dataGrid.setPageCount(metaList.getTotalPage());
		return dataGrid;
	}

	/**
	 * 已办数据
	 */
	@Override
	public DataGrid dataGridApproved(String tenantId, String userId, DataGrid dataGrid) {

		HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery().taskTenantId(tenantId)
				.taskAssignee(userId).finished().orderByTaskCreateTime().desc();
		if (query == null || query.list() == null || query.count() == 0) {
			return dataGrid;
		}
		// 总数
		List<HistoricTaskInstance> hisList = query.list();
		List<BpmTodo> listHis = new ArrayList<BpmTodo>();
		BpmTodo bpmTodo = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (HistoricTaskInstance task : hisList) {
			paramMap.put("instanceid", task.getProcessInstanceId());
			bpmTodo = super.getByMap(tenantId, paramMap);
			if (bpmTodo == null || userId.equals(bpmTodo.getUserId())) {
				continue;
			}
			bpmTodo.setBusicode(getBusiCodeByMetaId(tenantId, bpmTodo.getMetaid()));
			bpmTodo.setTaskId(task.getId());
			bpmTodo.setUrl(task.getFormKey());
			bpmTodo.setSubmitUser(getLastUserInfo(task.getProcessInstanceId()));
			bpmTodo.setNodeName(task.getName());
			bpmTodo.setSubmitAt(MyStringUtils.getDateStr(task.getEndTime()));
			bpmTodo.setEndTime(MyStringUtils.getDateStr(task.getEndTime()));
			listHis.add(bpmTodo);
		}
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodo> metaList = new PageBean<BpmTodo>(pageParam, hisList.size(), listHis);
		dataGrid.setRows(listHis);
		dataGrid.setTotal(hisList.size());
		dataGrid.setPageCount(metaList.getTotalPage());
		return dataGrid;
	}

	@Override
	public BpmTodo queryBpmTodoByTaskId(String tenantId, String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("instanceid", task.getProcessInstanceId());
		List<BpmTodo> list = super.getList(tenantId, paramMap);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 加载数据 ,根据参数设置是否草稿 办理
	 */
	@Override
	public DataGrid dataGridDraft(Map<String, Object> paraMap, DataGrid dataGrid) {
		Map<String, Object> paramMap = paraMap;
		String instanceid = "-1";
		paramMap.put("instanceid", instanceid);
		paramMap.put("sort", "createtime");
		paramMap.put("dir", "desc");
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodo> metaList = super.getPage(paraMap.get("tenantId").toString(), pageParam, paramMap);
		dataGrid.setRows(metaList.getList());
		dataGrid.setTotal(metaList.getTotalCount());
		dataGrid.setPageCount(metaList.getTotalPage());
		return dataGrid;
	}


	/**
	 * 得到上一步的提交人
	 * 
	 * @param TaskDefinitionKey
	 * @return
	 */
	private String getLastUserInfo(String instanceid) {
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()
				.processInstanceId(instanceid).variableName("submitUser").list();
		if (list != null && list.size() > 0) {
			HistoricVariableInstance dataInfo = list.get(0);
			if (dataInfo.getValue() != null) {
				Staff staff = userService.getByUniqueField(dataInfo.getValue().toString());
				return staff.getFullName();
			} else {
				return "";
			}
		}
		return "";
	}

	/**
	 * 
	 * updateMyProcInst(更新 收回 被退回数据) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param BpmTodoService
	 *            <br/>
	 * @return void <br/>
	 * @method @param paraMap{isback,isrevoke,prco_inset_id} <br/>
	 * @Exception 异常对象 <br/>
	 */
	@Override
	public void updateMyProcInst(Map<String, Object> paraMap) throws Exception {
		if (null == paraMap.get("isback") || null == paraMap.get("isrevoke") || null == paraMap.get("prco_inset_id")) {
			throw new NullPointerException("更新错误，参数有值为空！");
		} else {
			// 更新
			getDao().updateBySqlId(paraMap, "updateMyProcInst");
		}
	}

	/**
	 * getCommitBackToMyProcList(申请人查看我收回数据) bpm_todo.isrevoke=1 <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param OAQueryDataController
	 *            <br/>
	 * @return String <br/>
	 * @method @param request
	 * @method @param jsonParam isback=1 被退回
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	@Override
	public DataGrid getRevokeBackToMyProcList(Map<String, Object> paramMap, DataGrid dataGrid) {
		String tenantId = paramMap.get("tenantId").toString();
		String userId = paramMap.get("userId").toString();
		TaskQuery query = taskService.createTaskQuery().taskTenantId(tenantId).taskAssignee(userId)
				.orderByTaskCreateTime().desc().active();
		List<Task> claimList = query.list();
		if (query == null || query.list() == null || query.count() == 0) {
			return dataGrid;
		}
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		BpmTodo bpmTodo = null;
		String isrevoke = paramMap.get("isrevoke").toString();
		for (Task task : claimList) {
			paramMap.put("instanceid", task.getProcessInstanceId());
			bpmTodo = super.getByMap(tenantId, paramMap);
			if (null == bpmTodo) {
				continue;
			}
			if ("1".equals(isrevoke) && bpmTodo.getIsrevoke().equals(isrevoke)) {
				bpmTodo.setTaskId(task.getId());
				bpmTodo.setUrl(task.getFormKey());
				bpmTodo.setNodeName(task.getName());
				bpmTodo.setSubmitUser(getLastUserInfo(task.getProcessInstanceId()));
				bpmTodo.setSubmitAt(MyStringUtils.getDateStr(task.getCreateTime()));
			}
			bpmTodo.setBusicode(getBusiCodeByMetaId(tenantId, bpmTodo.getMetaid()));
			listTodo.add(bpmTodo);
		}
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodo> metaList = new PageBean<BpmTodo>(pageParam, claimList.size(), listTodo);
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(claimList.size());
		dataGrid.setPageCount(metaList.getTotalPage());
		return dataGrid;
	}

	/**
	 * getCommitBackToMyProcList(申请人查看被退回数据) bpm_todo.isback=1 <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param OAQueryDataController
	 *            <br/>
	 * @return String <br/>
	 * @method @param request
	 * @method @param jsonParam isback=1 被退回
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	@Override
	public DataGrid getCommitBackToMyProcList(Map<String, Object> paramMap, DataGrid dataGrid) {
		String tenantId = paramMap.get("tenantId").toString();
		String userId = paramMap.get("userId").toString();
		TaskQuery query = taskService.createTaskQuery().taskTenantId(tenantId).taskAssignee(userId)
				.orderByTaskCreateTime().desc().active();
		List<Task> claimList = query.list();
		if (query == null || query.list() == null || query.count() == 0) {
			return dataGrid;
		}
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		BpmTodo bpmTodo = null;
		String isback = paramMap.get("isback").toString();
		for (Task task : claimList) {
			paramMap.put("instanceid", task.getProcessInstanceId());
			bpmTodo = super.getByMap(tenantId, paramMap);
			if (null == bpmTodo) {
				continue;
			}

			if ("1".equals(isback) && bpmTodo.getIsback().equals(isback)) {
				List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
						.processInstanceId(task.getExecutionId()).orderByTaskId().desc().list();
				HistoricTaskInstance historicTaskInstance = list.get(1);
				bpmTodo.setTaskId(task.getId());
				bpmTodo.setUrl(task.getFormKey());
				bpmTodo.setNodeName(historicTaskInstance.getName());
				bpmTodo.setSubmitUser(getLastUserInfo(task.getProcessInstanceId()));
				bpmTodo.setSubmitAt(MyStringUtils.getDateStr(historicTaskInstance.getEndTime()));
				bpmTodo.setBusicode(getBusiCodeByMetaId(tenantId, bpmTodo.getMetaid()));
			}
			listTodo.add(bpmTodo);
		}
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodo> metaList = new PageBean<BpmTodo>(pageParam, claimList.size(), listTodo);
		dataGrid.setPageCount(metaList.getTotalPage());
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(claimList.size());
		return dataGrid;
	}
}
