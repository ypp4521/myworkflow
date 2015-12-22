package org.zywx.appdo.workflow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.SpringProcessEngineConfiguration;

public class WorkflowSpringProcessEngineConfiguration extends SpringProcessEngineConfiguration {

	protected WorkflowTaskService taskService = new WorkflowTaskServiceImpl(this);

	public WorkflowTaskService getTaskService() {
		return taskService;
	}

	public ProcessEngineConfigurationImpl setTaskService(WorkflowTaskService taskService) {
		this.taskService = taskService;
		return this;
	}

	public ProcessEngine buildProcessEngine() {
		init();
		ProcessEngine processEngine = new WorkflowProcessEngineImpl(this);
		ProcessEngines.setInitialized(true);
		autoDeployResources(processEngine);
		return processEngine;
	}

	protected void initServices() {
		initService(repositoryService);
		initService(runtimeService);
		initService(historyService);
		initService(identityService);
		initService(taskService);
		initService(formService);
		initService(managementService);
	}
}
