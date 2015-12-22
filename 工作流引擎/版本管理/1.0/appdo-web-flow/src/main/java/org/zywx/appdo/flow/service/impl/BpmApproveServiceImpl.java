package org.zywx.appdo.flow.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.GatewayActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.TaskActivityBehavior;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBizImpl;
import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.common.enums.ApptypeEnum;
import org.zywx.appdo.common.exception.FlowBusinessRuntimeException;
import org.zywx.appdo.common.page.PageBean;
import org.zywx.appdo.common.page.PageParam;
import org.zywx.appdo.flow.dao.BpmApproveDao;
import org.zywx.appdo.flow.entity.BpmApprove;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.BpmApproveService;
import org.zywx.appdo.flow.service.BpmTodoService;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.flow.util.JumpActivityCmd;
import org.zywx.appdo.utils.MyStringUtils;

@Service
public class BpmApproveServiceImpl extends BaseBizImpl<BpmApprove> implements BpmApproveService {

	@Autowired
	private BpmApproveDao bpmApproveDao;
	@Autowired
	private WorkflowUserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Autowired
    ManagementService managementService;
	@Autowired
	BpmTodoService bpmTodoService;
	@Override
	protected BaseDao<BpmApprove> getDao() {
		return bpmApproveDao;
	}
	/**
	 * 完成任务，提交流程
	 */
	@Override
	public Long completeTask(String taskId,String approve,String approveResult,String tenantId) {
		try{
			Map<String, Object> paraMap = new HashMap<String, Object>();
			Map<String,Object> variables=new HashMap<String, Object>();
			if ("true".equals(approve)) {
				//审核通过
				paraMap.put("isback", 0);
				paraMap.put("isrevoke", 0);
			}else{
				//退回
				paraMap.put("isback", 1);
				paraMap.put("isrevoke", 0);
			}
			
			taskService.setVariable(taskId, "approve", approve);
			Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
			variables.put("submitUser", task.getAssignee());
			//添加流程意见
			taskService.addComment(task.getId(), task.getProcessInstanceId(), approveResult);  
			taskService.complete(taskId);
			BpmApprove bpmApprove=new BpmApprove();
			bpmApprove.setApprove(approve);
			bpmApprove.setTargetkey(task.getTaskDefinitionKey());
			bpmApprove.setApproveresult(approveResult);
			bpmApprove.setInstanceid(task.getProcessInstanceId());
			bpmApprove.setTaskid(taskId);
			bpmApprove.setUserId(task.getAssignee());
			bpmApprove.setCreatetime(new Date());
			bpmApprove.setApptype(ApptypeEnum.COMPLETE.getValue());
			bpmApprove.setTenantId(Long.parseLong(tenantId));
			long key = getDao().save(bpmApprove);
			
			paraMap.put("prco_inset_id", task.getProcessInstanceId());
			//更新 还原是否收回操作 被退回数据
			bpmTodoService.updateMyProcInst(paraMap);
			return key;
		}catch(Exception e){
			throw new FlowBusinessRuntimeException("流程提交失败");
		}
	}
	@Override
	public Long rejectTask(String taskId, String taskKey, String approveResult,String tenantId) {
		try{
			Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
			Command<Object> cmd = new JumpActivityCmd(task.getProcessInstanceId(), taskKey);
	        managementService.executeCommand(cmd);
	        BpmApprove bpmApprove=new BpmApprove();
			bpmApprove.setApproveresult(approveResult);
			bpmApprove.setInstanceid(task.getProcessInstanceId());
			bpmApprove.setTaskid(taskId);
			bpmApprove.setUserId(task.getAssignee());
			bpmApprove.setCreatetime(new Date());
			bpmApprove.setApptype(ApptypeEnum.REJECT.getValue());
			bpmApprove.setTargetkey(taskKey);
			bpmApprove.setTenantId(Long.parseLong(tenantId));
			return getDao().save(bpmApprove);
		}catch(Exception e){
			throw new FlowBusinessRuntimeException("流程驳回失败");
		}
	}
	@Override
	public Long backTask(Task task, HistoricTaskInstance hisTask,String tenantId) {
		try{
			ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());  
			ActivityImpl activityImpl=pde.findActivity(task.getTaskDefinitionKey());
			if(findIncomingTask(activityImpl, hisTask.getTaskDefinitionKey())){
				Command<Object> cmd = new JumpActivityCmd(task.getProcessInstanceId(), hisTask.getTaskDefinitionKey());
		        managementService.executeCommand(cmd);
			}
			BpmApprove bpmApprove=new BpmApprove();
			bpmApprove.setInstanceid(task.getProcessInstanceId());
			bpmApprove.setTaskid(task.getId());
			//bpmApprove.setCreatetime(new Date());
			bpmApprove.setUserId(task.getAssignee());
			bpmApprove.setApptype(ApptypeEnum.BACK.getValue());
			bpmApprove.setTargetkey(hisTask.getTaskDefinitionKey());
			bpmApprove.setTenantId(Long.parseLong(tenantId));
			bpmApprove.setSubmitAt(MyStringUtils.now("yyyy-MM-dd HH:mm:ss"));
			return getDao().save(bpmApprove);
		}catch(Exception e){
			throw new FlowBusinessRuntimeException("单据已在办理中或已收回中无法收回");
		}
	}
	private boolean findIncomingTask(ActivityImpl activityImpl,String taskKey){
		List<PvmTransition> tranList=activityImpl.getIncomingTransitions();
		ActivityImpl pvmAct=null;
		for(PvmTransition tran:tranList){
			pvmAct=(ActivityImpl) tran.getSource();
			if(pvmAct.getActivityBehavior() instanceof TaskActivityBehavior){
				if(pvmAct.getId().equals(taskKey)){
					return true;
				}else{
					throw new FlowBusinessRuntimeException("不能收回");
				}
			}else if(pvmAct.getActivityBehavior() instanceof GatewayActivityBehavior){
				return findIncomingTask(pvmAct, taskKey);
			}
		}
		return false;
	}
	
	/**
	 * 获取查询历史记录
	 * zorro
	 * 2015-11-12
	 */
	@Override
	public DataGrid dataGridApprove(String userId,String tenantId, String instanceid,DataGrid dataGrid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("instanceid", instanceid);
		paramMap.put("sort", "createtime");
		paramMap.put("dir", "desc");
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmApprove> metaList = super.getPage(tenantId, pageParam, paramMap);
		List<BpmApprove> list=metaList.getList();
		ApptypeEnum[] apptypes=ApptypeEnum.values();
		HistoricTaskInstance task=null;
		for(BpmApprove approve:list){
			task=historyService.createHistoricTaskInstanceQuery().taskId(approve.getTaskid()).singleResult();
			if (task == null) {
				continue;
			}
			if (null == task.getEndTime()) {
				approve.setSubmitAt(MyStringUtils.getDateStr(task.getCreateTime()));
			}else{
				approve.setSubmitAt(MyStringUtils.getDateStr(task.getEndTime()));
			}
			//if(approve.getTaskid()==null||"".equals(approve.getTaskid())||"制单".equals(approve.getApptype())) continue;
			for(ApptypeEnum apptype:apptypes){
				if(approve.getApptype().equals(apptype.getValue())){
					approve.setApptype(apptype.getName());
				}
			}
			approve.setTaskid(task.getName());
			approve.setUserId(task.getAssignee());
			Staff staff=userService.getByUniqueField(approve.getUserId());
			approve.setUserName(staff.getFullName());
		}
		dataGrid.setRows(list);
		dataGrid.setTotal(metaList.getTotalCount());
		dataGrid.setPageCount(metaList.getTotalPage());
		return dataGrid;
	}
	
	@Override
	public Long backTask(String tenantId,String processInstanceId, String userId) {
		List<Task> taskList=taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    	if(taskList.size()>1){
    		throw new FlowBusinessRuntimeException("无法回收");
    	}else{
    		HistoricTaskInstance hisTask=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).taskAssignee(userId).orderByTaskDueDate().desc().list().get(0);
    		return backTask(taskList.get(0), hisTask, tenantId);
    	}
	}
}
