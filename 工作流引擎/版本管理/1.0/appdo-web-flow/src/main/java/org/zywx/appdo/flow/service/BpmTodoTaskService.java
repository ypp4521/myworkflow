package org.zywx.appdo.flow.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.flow.entity.BpmTodo;
import org.zywx.appdo.flow.entity.BpmTodoTask;

public interface BpmTodoTaskService extends BaseBiz<BpmTodoTask> {

	/**
	 * 获取待签列表数据
	 * 
	 * @param tenantId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridClaim(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid);

	/**
	 * 获取待办数据列表
	 * 
	 * @param tenantId
	 * @param userId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridTodo(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid);

	/**
	 * 获取已办数据列表
	 * 
	 * @param tenantId
	 * @param userId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridApproved(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid);

	/**
	 * 申请人查看我收回数据
	 * 
	 * @param paraMap
	 * @param dataGrid
	 * @return
	 */
	DataGrid getRevokeBackToMyProcList(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid);

	/**
	 * 申请人查看被退回数据
	 * 
	 * @param paraMap
	 * @param dataGrid
	 * @return
	 */
	DataGrid getCommitBackToMyProcList(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid);
}