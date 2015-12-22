package org.zywx.appdo.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zywx.appdo.bean.QueryWorkFlowAndFieldBean;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBizImpl;
import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.common.page.PageBean;
import org.zywx.appdo.common.page.PageParam;
import org.zywx.appdo.flow.dao.BpmTodoTaskDao;
import org.zywx.appdo.flow.entity.BpmTodo;
import org.zywx.appdo.flow.entity.BpmTodoTask;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.BpmTodoTaskService;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.meta.service.MetaCustomFieldService;
import org.zywx.appdo.utils.MyStringUtils;

@Service
public class BpmTodoTaskServiceImpl extends BaseBizImpl<BpmTodoTask>implements BpmTodoTaskService {

	@Autowired
	private BpmTodoTaskDao bpmTodoRuTaskDao;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private WorkflowUserService userService;
	@Autowired
	private MetaCustomFieldService metaCustomFieldService;

	@Override
	protected BaseDao<BpmTodoTask> getDao() {
		return bpmTodoRuTaskDao;
	}

	/**
	 * 待签收数据OK
	 */
	@Override
	public DataGrid dataGridClaim(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodoTask> list = super.getPage(tenantId, pageParam, paraMap, "getClaimData");
		if (list == null || list.getList() == null || list.getTotalCount() == 0) {
			return dataGrid;
		}
		// 变量定义
		BpmTodo bpmTodo = null;
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		for (BpmTodoTask bpmTodoRuTask : list.getList()) {
			bpmTodo = copyProperties(bpmTodoRuTask);
			listTodo.add(bpmTodo);
		}
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(list.getTotalCount());
		dataGrid.setPageCount(list.getTotalPage());
		return dataGrid;
	}

	/**
	 * 待办数据
	 */
	@Override
	public DataGrid dataGridTodo(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodoTask> list = super.getPage(tenantId, pageParam, paraMap, "getTodoData");
		if (list == null || list.getList() == null || list.getTotalCount() == 0) {
			return dataGrid;
		}

		// 变量定义
		BpmTodo bpmTodo = null;
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		Map<String, Object> paraSug = new HashMap<String, Object>();
		for (BpmTodoTask bpmTodoRuTask : list.getList()) {
			bpmTodo = copyProperties(bpmTodoRuTask);
			// 根据 流程节点id 查询 表: wf_meta_flow_field 配置的意见节点信息
			paraSug.put("wf_tenantId", tenantId);
			paraSug.put("wf_node_id", bpmTodoRuTask.getTASK_DEF_KEY_());
			paraSug.put("wf_modelkey", bpmTodoRuTask.getPROC_DEF_ID_());
			QueryWorkFlowAndFieldBean fieldBean = metaCustomFieldService.getWorkFlowNodesList(paraSug);
			if (null != fieldBean) {
				bpmTodo.setOpinionField(fieldBean.getWf_meta_custom_field_code());
			} else {
				bpmTodo.setOpinionField("");
			}
			if (bpmTodoRuTask.getCLAIM_TIME_() != null) {
				bpmTodo.setSubmitAt(MyStringUtils.getDateStr(bpmTodoRuTask.getCLAIM_TIME_()));
			}
			listTodo.add(bpmTodo);
		}
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(list.getTotalCount());
		dataGrid.setPageCount(list.getTotalPage());
		return dataGrid;
	}

	/**
	 * 已办数据
	 */
	@Override
	public DataGrid dataGridApproved(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodoTask> list = super.getPage(tenantId, pageParam, paraMap, "getApproveData");
		if (list == null || list.getList() == null || list.getTotalCount() == 0) {
			return dataGrid;
		}
		// 变量定义
		BpmTodo bpmTodo = null;
		List<BpmTodo> listHis = new ArrayList<BpmTodo>();
		for (BpmTodoTask bpmTodoRuTask : list.getList()) {
			bpmTodo = copyProperties(bpmTodoRuTask);
			if (bpmTodoRuTask.getEND_TIME_() != null) {
				bpmTodo.setEndTime(MyStringUtils.getDateStr(bpmTodoRuTask.getEND_TIME_()));
			}
			listHis.add(bpmTodo);
		}
		dataGrid.setRows(listHis);
		dataGrid.setTotal(list.getTotalCount());
		dataGrid.setPageCount(list.getTotalPage());
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
	 * 申请人查看我收回数据
	 */
	@Override
	public DataGrid getRevokeBackToMyProcList(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodoTask> list = super.getPage(tenantId, pageParam, paraMap);
		if (list == null || list.getList() == null || list.getTotalCount() == 0) {
			return dataGrid;
		}
		// 变量定义
		BpmTodo bpmTodo = null;
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		for (BpmTodoTask bpmTodoRuTask : list.getList()) {
			bpmTodo = copyProperties(bpmTodoRuTask);
			listTodo.add(bpmTodo);
		}
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(list.getTotalCount());
		dataGrid.setPageCount(list.getTotalPage());
		return dataGrid;
	}

	/**
	 * 申请人查看被退回数据
	 */
	@Override
	public DataGrid getCommitBackToMyProcList(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodoTask> list = super.getPage(tenantId, pageParam, paraMap);
		if (list == null || list.getList() == null || list.getTotalCount() == 0) {
			return dataGrid;
		}
		// 变量定义
		BpmTodo bpmTodo = null;
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		for (BpmTodoTask bpmTodoRuTask : list.getList()) {
			bpmTodo = copyProperties(bpmTodoRuTask);
			List<HistoricTaskInstance> listInner = historyService.createHistoricTaskInstanceQuery()
					.processInstanceId(bpmTodoRuTask.getEXECUTION_ID_()).orderByTaskId().desc().list();
			HistoricTaskInstance historicTaskInstance = listInner.get(1);
			bpmTodo.setNodeName(historicTaskInstance.getName());
			bpmTodo.setSubmitAt(MyStringUtils.getDateStr(historicTaskInstance.getEndTime()));
			listTodo.add(bpmTodo);
		}
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(list.getTotalCount());
		dataGrid.setPageCount(list.getTotalPage());
		return dataGrid;
	}

	/**
	 * 属性复制
	 * 
	 * @param bpmTodoTask
	 * @param bpmTodo
	 * @return
	 */
	private BpmTodo copyProperties(BpmTodoTask bpmTodoTask) {
		BpmTodo bpmTodo = new BpmTodo();
		// 基本属性复制
		BeanUtils.copyProperties(bpmTodoTask, bpmTodo);
		// 通用功能复制
		if (bpmTodoTask.getBusicode() != null && !"".equals(bpmTodoTask.getBusicode())) {
			bpmTodo.setBusicode(bpmTodoTask.getBusicode());
		}
		if (bpmTodoTask.getID_() != null && !"".equals(bpmTodoTask.getID_())) {
			bpmTodo.setTaskId(bpmTodoTask.getID_());
		}
		if (bpmTodoTask.getFORM_KEY_() != null && !"".equals(bpmTodoTask.getFORM_KEY_())) {
			bpmTodo.setUrl(bpmTodoTask.getFORM_KEY_());
		}
		if (bpmTodoTask.getNAME_() != null && !"".equals(bpmTodoTask.getNAME_())) {
			bpmTodo.setNodeName(bpmTodoTask.getNAME_());
		}
		if (bpmTodoTask.getCreatetime() != null && !"".equals(bpmTodoTask.getCreatetime())) {
			bpmTodo.setCreatetime(bpmTodoTask.getCreatetime());
		}
		if (bpmTodoTask.getEND_TIME_() != null) {
			bpmTodo.setEndTime(MyStringUtils.getDateStr(bpmTodoTask.getEND_TIME_()));
		}
		if (bpmTodoTask.getCREATE_TIME_() != null) {
			bpmTodo.setSubmitAt(MyStringUtils.getDateStr(bpmTodoTask.getCREATE_TIME_()));
		}
		if (bpmTodoTask.getPROC_INST_ID_() != null) {
			bpmTodo.setSubmitUser(getLastUserInfo(bpmTodoTask.getPROC_INST_ID_()));
		}
		return bpmTodo;
	}
}
