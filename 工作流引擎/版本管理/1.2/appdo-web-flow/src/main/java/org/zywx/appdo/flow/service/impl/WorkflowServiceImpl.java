package org.zywx.appdo.flow.service.impl;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.ExclusiveGatewayActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.GatewayActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.InclusiveGatewayActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.NoneStartEventActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.TaskActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.el.UelExpressionCondition;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.AstBinary;
import org.activiti.engine.impl.juel.AstIdentifier;
import org.activiti.engine.impl.juel.AstMethod;
import org.activiti.engine.impl.juel.AstUnary;
import org.activiti.engine.impl.juel.Builder;
import org.activiti.engine.impl.juel.Builder.Feature;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.Node;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.juel.TreeValueExpression;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.enums.TodoVarEnum;
import org.zywx.appdo.flow.entity.BpmTodo;
import org.zywx.appdo.flow.entity.Staff;
import org.zywx.appdo.flow.service.BpmTodoService;
import org.zywx.appdo.flow.service.WorkflowService;
import org.zywx.appdo.flow.service.WorkflowUserService;
import org.zywx.appdo.meta.service.MetaCustomService;
import org.zywx.appdo.utils.MyStringUtils;
import org.zywx.appdo.utils.PropertyTools;

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private WorkflowUserService userService;
	@Autowired
	private MetaCustomService metaCustomService;
	@Autowired
	private BpmTodoService bpmTodoService;

	@Override
	public void findVal(List<ActivityImpl> listAct, String defKey, List<Map<String, Object>> listVal) {
		if (listVal == null) {
			listVal = new ArrayList<Map<String, Object>>();
		}
		List<PvmTransition> listTran = null;
		Map<String, Object> map = null;
		for (ActivityImpl activiti : listAct) {
			if (activiti.getId().equals(defKey)) {
				if (activiti.getActivityBehavior() instanceof TaskActivityBehavior) {
					listTran = activiti.getOutgoingTransitions();
					for (PvmTransition tran : listTran) {
						findVal(listAct, tran.getDestination().getId(), listVal);
					}
				} else if (activiti.getActivityBehavior() instanceof ExclusiveGatewayActivityBehavior
						|| activiti.getActivityBehavior() instanceof InclusiveGatewayActivityBehavior) {
					listTran = activiti.getOutgoingTransitions();
					for (PvmTransition tran : listTran) {
						UelExpressionCondition uel = (UelExpressionCondition) tran.getProperty("condition");
						TreeValueExpression tree = new ExpressionFactoryImpl().createValueExpression(
								new SimpleContext(), tran.getProperty("conditionText").toString(), Boolean.class);
						EnumSet<Builder.Feature> features = EnumSet.noneOf(Builder.Feature.class);
						Node node = new Builder(Feature.METHOD_INVOCATIONS)
								.build(tran.getProperty("conditionText").toString()).getRoot().getChild(0);
						map = new HashMap<String, Object>();
						map.put("name", tran.getProperty("name"));
						if (node instanceof AstBinary) {
							AstBinary ast = (AstBinary) node;
							map.put("key", ast.getChild(0).toString());
							map.put("value", ast.getChild(1).toString().replace("\"", ""));
						} else if (node instanceof AstIdentifier) {
							AstIdentifier asti = (AstIdentifier) node;
							map.put("key", asti.getName().toString());
							map.put("value", true);
						} else if (node instanceof AstUnary) {
							AstUnary astu = (AstUnary) node;
							map.put("key", astu.getChild(0).toString());
							map.put("value", false);
						} else if (node instanceof AstMethod) {
							AstMethod astm = (AstMethod) node;
							map = null;
						}
						if (map != null) {
							listVal.add(map);
						}
					}
				}
				continue;
			}
		}
	}

	@Override
	public boolean checkVal(String val, DelegateExecution execution) {
		ExpressionFactory factory = new ExpressionFactoryImpl();
		SimpleContext context = new SimpleContext();
		context.setVariable("count", factory.createValueExpression(10000, String.class));
		ValueExpression e = factory.createValueExpression(context, "${count>=1000}", boolean.class);
		if ("1".equals(val)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> toRejectTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		ActivityImpl activityImpl = pde.findActivity(task.getTaskDefinitionKey());
		List<ActivityImpl> listActivity = new ArrayList<ActivityImpl>();
		findIncomingTasks(activityImpl, listActivity);
		List<Map<String, Object>> rejTaskList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		Map<String, Object> mapCheck = new HashMap<String, Object>();
		List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).list();
		for (ActivityImpl activiti : listActivity) {
			for (HistoricTaskInstance his : taskList) {
				if (activiti.getId().equals(his.getTaskDefinitionKey())) {
					map = new HashMap<String, Object>();
					if (mapCheck.get(his.getTaskDefinitionKey()) == null) {
						map.put("key", his.getTaskDefinitionKey());
						map.put("name", his.getName());
						mapCheck.put(his.getTaskDefinitionKey(), his.getName());
						rejTaskList.add(map);
					}
				}
			}
		}
		return rejTaskList;
	}

	private void findIncomingTasks(ActivityImpl activityImpl, List<ActivityImpl> listActivity) {
		List<PvmTransition> tranList = activityImpl.getIncomingTransitions();
		ActivityImpl pvmAct = null;
		for (PvmTransition tran : tranList) {
			pvmAct = (ActivityImpl) tran.getSource();
			if (pvmAct.getActivityBehavior() instanceof TaskActivityBehavior) {
				listActivity.add(pvmAct);
				findIncomingTasks(pvmAct, listActivity);
			} else if (pvmAct.getActivityBehavior() instanceof GatewayActivityBehavior) {
				findIncomingTasks(pvmAct, listActivity);
			} else if (pvmAct.getActivityBehavior() instanceof NoneStartEventActivityBehavior) {
				// 不需要制单节点，因为涉及到制单节点可能需要修改表单，暂时先屏蔽
				// listActivity.remove(activityImpl);
			}
		}
	}

	private ActivityImpl findIncomingTask(ActivityImpl activityImpl) {
		List<PvmTransition> tranList = activityImpl.getIncomingTransitions();
		ActivityImpl pvmAct = null;
		for (PvmTransition tran : tranList) {
			pvmAct = (ActivityImpl) tran.getSource();
			if (pvmAct.getActivityBehavior() instanceof TaskActivityBehavior) {
				return pvmAct;
			} else if (pvmAct.getActivityBehavior() instanceof GatewayActivityBehavior) {
				return findIncomingTask(pvmAct);
			}
		}
		return null;
	}

	@Override
	public void claimTask(String taskId, String userId) {
		taskService.claim(taskId, userId);
	}

	@Override
	public void findVal(String taskId, List<Map<String, Object>> listVal) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		this.findVal(pde.getActivities(), task.getTaskDefinitionKey(), listVal);
	}

	/**
	 * 
	 * getMyInvolvedProcList(根据当前查看人，查看当前操作人参与过的所有流程) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param WorkflowService
	 *            <br/>
	 * @return DataGrid <br/>
	 * @method @param paraMap {userId,xxxxxx}
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	public DataGrid getMyInvolvedProcList(Map<String, Object> paraMap, DataGrid dataGrid) {
		List<HistoricProcessInstance> hpis = historyService.createHistoricProcessInstanceQuery()
				.involvedUser(paraMap.get("userId").toString()).orderByProcessInstanceStartTime().desc().listPage(
						(dataGrid.getPage() - 1) * dataGrid.getPageSize(), dataGrid.getPage() * dataGrid.getPageSize());
		List<String> listProcId = new ArrayList<String>();
		if (hpis != null && hpis.size() > 0) {
			for (HistoricProcessInstance hs : hpis) {
				listProcId.add(hs.getId());
			}
			dataGrid.setRows(listProcId);
		}
		return dataGrid;
	}

	@Override
	public DataGrid dataGridProcessDef(String tenantId, String id, DataGrid dataGrid) {
		List<ProcessDefinition> defList = repositoryService.createProcessDefinitionQuery()
				.processDefinitionTenantId(tenantId).processDefinitionKey(id).active().listPage(
						(dataGrid.getPage() - 1) * dataGrid.getPageSize(), dataGrid.getPage() * dataGrid.getPageSize());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (ProcessDefinition process : defList) {
			map = new HashMap<String, Object>();
			map.put("id", process.getId());
			map.put("name", process.getName());
			map.put("category", process.getCategory());
			map.put("resourceName", process.getResourceName());
			map.put("version", process.getVersion());
			list.add(map);
		}
		dataGrid.setRows(list);
		dataGrid.setTotal(repositoryService.createProcessDefinitionQuery().processDefinitionKey(id).active().count());
		return dataGrid;
	}

	/**
	 * 流转中数据
	 */
	@Override
	public DataGrid dataGridProcessInstance(String tenantId, String loginname, DataGrid dataGrid) {
		List<ProcessInstance> insList = null;
		if (PropertyTools.getPropertyByKey("admin").equals(loginname)) {
			insList = runtimeService.createProcessInstanceQuery().listPage(
					(dataGrid.getPage() - 1) * dataGrid.getPageSize(), dataGrid.getPage() * dataGrid.getPageSize());
			dataGrid.setTotal(runtimeService.createProcessInstanceQuery().count());
		} else {
			insList = runtimeService.createProcessInstanceQuery().processInstanceTenantId(tenantId).listPage(
					(dataGrid.getPage() - 1) * dataGrid.getPageSize(), dataGrid.getPage() * dataGrid.getPageSize());
			dataGrid.setTotal(runtimeService.createProcessInstanceQuery().processInstanceTenantId(tenantId).count());
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 查询数据
		Map<String, Object> querymap = new HashMap<String, Object>();
		querymap.put("tenantId", tenantId);
		for (ProcessInstance process : insList) {
			map = new HashMap<String, Object>();
			querymap.put("instanceid", process.getId());
			BpmTodo todo = bpmTodoService.getByMap(tenantId, querymap);
			map.put("title", todo.getTitle());
			map.put("name", process.getName());
			map.put("key", process.getBusinessKey());
			map.put("suspend", process.isSuspended());
			map.put("id", process.getProcessInstanceId());
			map.put("defid", process.getProcessDefinitionId());
			map.put("defname", process.getProcessDefinitionName());
			map.put("createAt", todo.getCreatedAtStr());
			map.put("busiName", todo.getBusiname());
			list.add(map);
		}
		dataGrid.setRows(list);
		return dataGrid;
	}

	/**
	 * 获取已完成列表数据
	 */
	@Override
	public DataGrid dataGridProcessInstanceHis(String tenantId, String loginname, DataGrid dataGrid) {
		List<HistoricProcessInstance> insList = null;
		if (PropertyTools.getPropertyByKey("admin").equals(loginname)) {
			insList = historyService.createHistoricProcessInstanceQuery().finished().listPage(
					(dataGrid.getPage() - 1) * dataGrid.getPageSize(), dataGrid.getPage() * dataGrid.getPageSize());
			dataGrid.setTotal(historyService.createHistoricProcessInstanceQuery().finished().count());
		} else {
			insList = historyService.createHistoricProcessInstanceQuery().finished().processInstanceTenantId(tenantId)
					.listPage((dataGrid.getPage() - 1) * dataGrid.getPageSize(),
							dataGrid.getPage() * dataGrid.getPageSize());
			dataGrid.setTotal(historyService.createHistoricProcessInstanceQuery().finished()
					.processInstanceTenantId(tenantId).count());
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 查询数据
		Map<String, Object> querymap = new HashMap<String, Object>();
		querymap.put("tenantId", tenantId);
		for (HistoricProcessInstance process : insList) {
			map = new HashMap<String, Object>();
			querymap.put("instanceid", process.getId());
			BpmTodo todo = bpmTodoService.getByMap(tenantId, querymap);
			map.put("title", todo.getTitle());
			map.put("name", process.getName());
			map.put("key", process.getBusinessKey());
			map.put("defname", process.getTenantId());
			map.put("suspend", process.getDeleteReason());
			map.put("id", process.getProcessDefinitionId());
			map.put("defid", process.getProcessDefinitionId());
			map.put("createAt", todo.getCreatedAtStr());
			map.put("endAt", MyStringUtils.dateToString(process.getEndTime(), "yyyy-mm-dd HH:mi:ss"));
			map.put("busiName", todo.getBusiname());
			list.add(map);
		}
		dataGrid.setRows(list);
		return dataGrid;
	}

	@Override
	public void suspendProcessInstanceById(String processInstanceId) {
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}

	@Override
	public void activateProcessInstanceById(String processInstanceId) {
		runtimeService.activateProcessInstanceById(processInstanceId);
	}

	@Override
	public InputStream processImage(String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstance.getProcessInstanceId());
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		return diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds, Collections.<String> emptyList(),
				processEngine.getProcessEngineConfiguration().getActivityFontName(),
				processEngine.getProcessEngineConfiguration().getLabelFontName(), null, 1.0);
	}

	@Override
	public InputStream loadResource(String resourceType, String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		return repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
	}

	@Override
	public InputStream loadByProcessInstance(String resourceType, String processInstanceId) {
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();

		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		return repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
	}

	/**
	 * 获取流程流转历史流程图 方法重写 <br/> (non-Javadoc) <br/>
	 * 
	 * @see org.zywx.appdo.flow.service.WorkflowService#readResource(java.lang.
	 * String)
	 */
	public InputStream readResource(String processInstanceId) {
		String processDefinitionId = "";
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		if (processInstance == null) {
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstanceId).list().get(0);
			processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		} else {
			return processImage(processInstanceId);
		}
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		// 获取流程历史
		List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();

		List<String> activitiIds = new ArrayList<String>();
		List<String> flowIds = new ArrayList<String>();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		// 获取流程走过的线
		flowIds = getHighLightedFlows(processDefinition, activityInstances);
		// 当前流程节点
		for (HistoricActivityInstance i : activityInstances) {
			activitiIds.add(i.getActivityId());
		}

		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
		// 获取流程图
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		return diagramGenerator.generateDiagram(bpmnModel, "png", activitiIds, flowIds,"宋体","宋体",null,1.0);
//				processEngine.getProcessEngineConfiguration().getActivityFontName(),
//				processEngine.getProcessEngineConfiguration().getLabelFontName(), null, 1.0);
		
		
	}

	@Override
	public Model queryModel(String modelId) {
		return repositoryService.getModel(modelId);
	}

	@Override
	public InputStream processDefXML(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				processDefinition.getResourceName());
	}

	@Override
	public Object queryVariable(String processInstanceId, String id) {
		return runtimeService.getVariable(processInstanceId, id);
	}

	@Override
	public ProcessInstance queryProcessInstance(String processInstanceId) {
		return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	}

	@Override
	public HistoricTaskInstance queryHistoricTaskInstance(String taskId) {
		return historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
	}

	@Override
	public boolean checkStartUser(String opentype, String processInstanceId, String taskId, String userId) {
		if ("todo".equals(opentype)) {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (processInstanceId == null) {
				processInstanceId = task.getProcessInstanceId();
			}
			ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(task.getProcessDefinitionId());
			ActivityImpl activityImpl = pde.findActivity(task.getTaskDefinitionKey());
			List<PvmTransition> listT = activityImpl.getIncomingTransitions();
			if (listT != null && listT.size() == 1) {
				activityImpl = (ActivityImpl) listT.get(0).getSource();
				if (activityImpl.getActivityBehavior() instanceof NoneStartEventActivityBehavior) {
					if (task.getAssignee().equals(
							queryVariable(processInstanceId, PropertyTools.getPropertyByKey("startUserId")).toString())
							&& task.getAssignee().equals(userId)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean valueExpression(String val, String operator, String propertity, DelegateExecution execution) {
		String[] vars = propertity.split("\\.");
		String id = null;
		Class cla = null;
		List<Long> ids = null;
		if (vars.length == 0) {
			return false;
		} else if (vars.length == 1) {
			ExpressionFactory factory = new ExpressionFactoryImpl();
			SimpleContext context = new SimpleContext();
			context.setVariable("ValueExpression", factory.createValueExpression(val, String.class));
			ValueExpression ve = null;
			if (propertity.equals(PropertyTools.getPropertyByKey("startUserId"))) {
				// 从流程变量中获取制单人id
				try {
					id = taskService.getVariable(execution.getId(), PropertyTools.getPropertyByKey("startUserId"))
							.toString();
					ve = factory.createValueExpression(context, "${ValueExpression" + operator + id + "}",
							boolean.class);
				} catch (Exception e) {
					return false;
				}
			} else {
				id = taskService.getVariable(execution.getId(), PropertyTools.getPropertyByKey("metaCustomId"))
						.toString();
				String tenantId = execution.getTenantId();
				id = metaCustomService.findBusiVal(tenantId, Long.parseLong(id), execution.getBusinessKey(),
						propertity);
				ve = factory.createValueExpression(context, "${ValueExpression" + operator + id + "}", boolean.class);
			}
			return (Boolean) ve.getValue(context);
		} else {
			// 读取元数据关联系统管理的数据
			if (vars[0].equals(TodoVarEnum.VAR_USER.getValue())) {
				// 根据{user.id}获取指定用户vars[1]
				try {
				} catch (Exception e) {
					return false;
				}
			} else if (vars[0].equals(TodoVarEnum.VAR_DICT.getValue())) {
				// 根据{dict.key.value}方式获取字典表中的值
			} else if (vars[0].equals(TodoVarEnum.VAR_DEPT.getValue())) {
				try {
					// 根据{dept.id}方式获取部门名称vars[1]
				} catch (Exception e) {
					return false;
				}
			} else if (vars[0].equals(TodoVarEnum.VAR_ENUMS.getValue())) {
				try {
					cla = Class.forName("org.zywx.appdo.common.enums." + vars[1]);
					Method getValue = cla.getMethod("getValue");
					Method getName = cla.getMethod("getName");
					Object[] objs = cla.getEnumConstants();
					for (Object obj : objs) {
						if (vars[2].equals(getValue.invoke(obj))) {
							if (val.equals(getName.invoke(obj).toString())) {
								return true;
							}
						}
					}
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 查询可选人员列表
	 */
	@Override
	public List<String> queryUsers(String propertity, DelegateExecution execution) {
		List<String> users = new ArrayList<String>();
		String[] vars = propertity.split("\\.");
		if (vars.length != 0) {
			if (vars[0].equals(PropertyTools.getPropertyByKey("superior"))) {
				String userId = "";
				// 查询历史中最近操作用户
				List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
						.processInstanceId(execution.getId()).orderByTaskId().desc().list();
				if (list != null) {
					if (list.size() > 0) {
						HistoricTaskInstance historicTaskInstance = list.get(0);
						userId = historicTaskInstance.getAssignee();
					}
				} else {
					userId = runtimeService
							.getVariable(execution.getId(), PropertyTools.getPropertyByKey("startUserId")).toString();
				}
				// 根据上级节点的办理人userid中的人员
				Staff staff = userService.getByUniqueField(userId);
				users.add(staff.getLdrUserId());
			} else if (vars[0].equals(PropertyTools.getPropertyByKey("appUser"))) {
				// 查询app用户
			} else if (vars[0].equals(PropertyTools.getPropertyByKey("currentUser"))) {
				// 查询平级用户
			}
		}
		return users;
	}

	/**
	 * 查询办理人员
	 */
	@Override
	public String queryAssigneeUsers(String propertity, DelegateExecution execution) {
		String users = "";
		String[] vars = propertity.split("\\.");
		if (vars.length != 0) {
			if (vars[0].equals(PropertyTools.getPropertyByKey("superior"))) {
				String userId = "";
				// 查询历史中最近操作用户
				List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
						.processInstanceId(execution.getId()).orderByTaskId().desc().list();
				if (list != null) {
					if (list.size() > 0) {
						HistoricTaskInstance historicTaskInstance = list.get(0);
						userId = historicTaskInstance.getAssignee();
					}
				} else {
					userId = runtimeService
							.getVariable(execution.getId(), PropertyTools.getPropertyByKey("startUserId")).toString();
				}
				// 根据上级节点的办理人userid中的人员
				Staff staff = userService.getByUniqueField(userId);
				users = staff.getLdrUserId();
			} else if (vars[0].equals(PropertyTools.getPropertyByKey("appUser"))) {
				// 查询app用户
			} else if (vars[0].equals(PropertyTools.getPropertyByKey("currentUser"))) {
				// 查询平级用户
			}
		}
		return users;
	}

	@Override
	public List<Map<String, Object>> queryActivity(String processDefinitionId) {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> list = pde.getActivities();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (ActivityImpl activiti : list) {
			if (activiti.getActivityBehavior() instanceof TaskActivityBehavior) {
				map = new HashMap<String, Object>();
				map.put("id", activiti.getId());
				map.put("name", activiti.getProperty("name"));
				listMap.add(map);
			}
		}
		return listMap;
	}

	/**
	 * 根据当前流程任务id查询所对节点信息 <br/>
	 * getTaskOpinionFlowNodeId <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return String <br/>
	 * @method @param map
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	@Override
	public String getTaskOpinionFlowNodeId(Map<String, Object> map) {
		String node_id = null;
		// 取得当前任务信息
		Task task = taskService.createTaskQuery().taskId(map.get("wf_taskid").toString()).singleResult();
		if (null != task) {
			// 取得当前节点信息
			node_id = task.getTaskDefinitionKey();
			node_id += "," + task.getProcessDefinitionId().split("\\:")[0];
		}
		return node_id;
	}

	/**
	 * //获取流程走过的线
	 * 
	 * @param processDefinitionEntity
	 * @param historicActivityInstances
	 * @return
	 */
	private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,
			List<HistoricActivityInstance> historicActivityInstances) {
		List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId  
		for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
			//  对历史流程节点进行遍历
			ActivityImpl activityImpl = processDefinitionEntity
					.findActivity(historicActivityInstances.get(i).getActivityId());// 得到节点定义的详细信息    
			List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();//  用以保存后需开始时间相同的节点    
			ActivityImpl sameActivityImpl1 = processDefinitionEntity
					.findActivity(historicActivityInstances.get(i + 1).getActivityId());//  将后面第一个节点放在时间相同节点的集合里 
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);//  后续第一个节点 
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);//  后续第二个节点 
				if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {//  如果第一个节点和第二个节点开始时间相同保存 
					ActivityImpl sameActivityImpl2 = processDefinitionEntity
							.findActivity(activityImpl2.getActivityId());
					sameStartTimeNodes.add(sameActivityImpl2);
				} else {
					//  有不相同跳出循环 
					break;
				}
			}
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();// 取出节点的所有出去的线
			for (PvmTransition pvmTransition : pvmTransitions) {// 对所有的线进行遍历
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();//  如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示 
				if (sameStartTimeNodes.contains(pvmActivityImpl)) {
					highFlows.add(pvmTransition.getId());
				}
			}
		}
		return highFlows;
	}
}
