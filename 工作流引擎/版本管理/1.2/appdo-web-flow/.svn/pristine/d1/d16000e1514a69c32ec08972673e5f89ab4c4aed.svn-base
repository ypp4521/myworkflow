package org.zywx.appdo.flow.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.flow.entity.BpmTodo;

public interface BpmTodoService extends BaseBiz<BpmTodo> {

	/**
	 * 获取待签列表数据
	 * 
	 * @param tenantId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridClaim(String tenantId, String userId, List<String> candidateGroups, DataGrid dataGrid);

	/**
	 * 获取待办数据列表
	 * 
	 * @param tenantId
	 * @param userId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridTodo(String tenantId, String userId, DataGrid dataGrid);

	/**
	 * 获取已办数据列表
	 * 
	 * @param tenantId
	 * @param userId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridApproved(String tenantId, String userId, DataGrid dataGrid);

	/**
	 * 根据流程实例id查询待办辅助表
	 * 
	 * @param tenantId
	 * @param instanceId
	 * @return
	 */
	BpmTodo queryBpmTodoByTaskId(String tenantId, String taskId);

	/**
	 * 获取草稿分页列表数据
	 * 
	 * @param paraMap
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridDraft(Map<String, Object> paraMap, DataGrid dataGrid);
	
	/**
	 * 
	 * updateMyProcInst(更新 收回 被退回数据)    <br/> 
	 * @user pengpeng.yuan@zymobi.com 
	 * @param   BpmTodoService  <br/>  
	 * @return  void  <br/>
	 * @method  @param paraMap{isback,isrevoke,prco_inset_id}  <br/>  
	 * @Exception 异常对象    <br/>
	 */
	void updateMyProcInst(Map<String, Object> paraMap) throws Exception;
	
	
	/**
	 * getCommitBackToMyProcList(申请人查看我收回数据) bpm_todo.isrevoke=1    <br/> 
	 * @user pengpeng.yuan@zymobi.com 
	 * @param   OAQueryDataController  <br/>  
	 * @return  DataGrid  <br/>
	 * @method  @param request
	 * @method  @param jsonParam isback=1 被退回
	 * @method  @return  <br/>  
	 * @Exception 异常对象    <br/>
	 */
	DataGrid getRevokeBackToMyProcList(Map<String, Object> paraMap, DataGrid dataGrid);
	
	
	/**
	 * getCommitBackToMyProcList(申请人查看被退回数据) bpm_todo.isback=1    <br/> 
	 * @user pengpeng.yuan@zymobi.com 
	 * @param   OAQueryDataController  <br/>  
	 * @return  DataGrid  <br/>
	 * @method  @param request
	 * @method  @param jsonParam isback=1 被退回
	 * @method  @return  <br/>  
	 * @Exception 异常对象    <br/>
	 */
	DataGrid getCommitBackToMyProcList(Map<String, Object> paraMap, DataGrid dataGrid);
}