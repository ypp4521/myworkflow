package org.zywx.appdo.flow.service;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.flow.entity.BpmApprove;

public interface BpmApproveService extends BaseBiz<BpmApprove> {

	/**
	 * 提交流程并保存审批意见
	 * 
	 * @param taskId
	 * @param variables
	 * @return
	 */
	Long completeTask(String taskId, String approve, String approveResult, String tenantId);

	/**
	 * 驳回流程
	 * 
	 * @param taskId
	 * @param taskKey
	 * @param approveResult
	 * @return
	 */
	Long rejectTask(String taskId, String taskKey, String approveResult, String tenantId);

	/**
	 * 收回单据
	 * 
	 * @param task
	 * @param hisTask
	 * @param tenantId
	 * @return
	 */
	Long backTask(Task task, HistoricTaskInstance hisTask, String tenantId);

	/**
	 * 收回单据
	 * 
	 * @param task
	 * @param hisTask
	 * @param tenantId
	 * @return
	 */
	Long backTask(String tenantId, String processInstanceId, String userId);

	/**
	 * 获取审批历史数据
	 * 
	 * @param tenantId
	 * @param instanceId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridApprove(String userId, String tenantId, String instanceid, DataGrid dataGrid);
}
