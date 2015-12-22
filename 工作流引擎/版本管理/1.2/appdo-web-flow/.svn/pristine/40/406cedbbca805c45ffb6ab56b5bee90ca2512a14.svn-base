package org.zywx.appdo.flow.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.zywx.appdo.common.DataGrid;

public interface WorkflowService {

	/**
	 * 
	 * @param listAct
	 * @param defKey
	 * @param listVal
	 */
	void findVal(String taskId, List<Map<String, Object>> listVal);

	void findVal(List<ActivityImpl> listAct, String defKey, List<Map<String, Object>> listVal);

	/**
	 * 获取可驳回节点
	 * 
	 * @param taskId
	 * @return
	 */
	List<Map<String, Object>> toRejectTask(String taskId);

	/**
	 * 签收任务
	 * 
	 * @param taskId
	 * @param userId
	 */
	void claimTask(String taskId, String userId);

	/**
	 * 分页获取流程定义信息
	 * 
	 * @param tenantId
	 * @param id
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridProcessDef(String tenantId, String id, DataGrid dataGrid);

	/**
	 * 获取已办理审批信息列表
	 * 
	 * @param tenantId
	 * @param loginname
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridProcessInstance(String tenantId, String loginname, DataGrid dataGrid);

	/**
	 * 获取已办结信息列表
	 * 
	 * @param tenantId
	 * @param loginname
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridProcessInstanceHis(String tenantId, String loginname, DataGrid dataGrid);

	/**
	 * 挂起流程
	 * 
	 * @param instanceid
	 */
	void suspendProcessInstanceById(String processInstanceId);

	/**
	 * 激活流程
	 * 
	 * @param instanceid
	 */
	void activateProcessInstanceById(String processInstanceId);

	/**
	 * 获取流程跟踪图
	 * 
	 * @param processInstanceId
	 * @return
	 */
	InputStream processImage(String processInstanceId);

	/**
	 * 获取流程定义资源文件信息
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	InputStream loadResource(String resourceType, String processDefinitionId);

	/**
	 * 获取流程实例跟踪信息
	 * 
	 * @param resourceType
	 * @param processDefinitionId
	 * @return
	 */
	InputStream loadByProcessInstance(String resourceType, String processInstanceId);

	/**
	 * 读取流程图
	 * 
	 * @param processInstanceId
	 * @return
	 */
	InputStream readResource(String processInstanceId);

	/**
	 * 获取流程模型
	 * 
	 * @param modelId
	 * @return
	 */
	Model queryModel(String modelId);

	/**
	 * 获取流程xml信息
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	InputStream processDefXML(String processDefinitionId);

	/**
	 * 获取流程变量
	 * 
	 * @param processInstanceId
	 * @param id
	 * @return
	 */
	Object queryVariable(String processInstanceId, String id);

	/**
	 * 根据流程实例id查询流程实例
	 * 
	 * @param processInstanceId
	 * @return
	 */
	ProcessInstance queryProcessInstance(String processInstanceId);

	/**
	 * 查询历史节点
	 * 
	 * @param taskId
	 * @return
	 */
	HistoricTaskInstance queryHistoricTaskInstance(String taskId);

	/**
	 * 校验当前办理节点是否是制单节点，如果是制单节点则提供修改功能
	 * 
	 * @param opentype
	 * @param processInstanceId
	 * @param taskId
	 * @return
	 */
	boolean checkStartUser(String opentype, String processInstanceId, String taskId, String userId);

	/**
	 * 根据参数对比指定值
	 * 
	 * @param val
	 * @param execution
	 * @return
	 */
	boolean checkVal(String val, DelegateExecution execution);

	/**
	 * 根据类型和key获取数据并和val进行对比
	 * 
	 * @param val
	 * @param type
	 * @param key
	 * @param execution
	 * @return
	 */
	boolean valueExpression(String val, String operator, String propertity, DelegateExecution execution);

	/**
	 * 根据条件查询人员
	 * 
	 * @param propertity
	 * @param execution
	 * @return
	 */
	List<String> queryUsers(String propertity, DelegateExecution execution);
	
	/**
	 * 根据条件查询办理人员
	 * 
	 * @param propertity
	 * @param execution
	 * @return
	 */
	String queryAssigneeUsers(String propertity, DelegateExecution execution);

	/**
	 * 根据流程定义id获取流程节点列表
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	List<Map<String, Object>> queryActivity(String processDefinitionId);

	/**
	 * 
	 * getMyInvolvedProcList(根据当前查看人，查看当前操作人参与过的所有流程) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param WorkflowService
	 *            <br/>
	 * @return DataGrid <br/>
	 * @method @param paraMap {userId,xxxxxx}
	 * @method @param dataGrid
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	DataGrid getMyInvolvedProcList(Map<String, Object> paraMap, DataGrid dataGrid);
	
	/**
	 * 根据当前流程任务id查询所对应模版的意见字段 <br/>
	 * getTaskOpinionFlowNodeId <br/>
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return String <br/>
	 * @method @param map
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	String getTaskOpinionFlowNodeId(Map<String, Object> map);
}
