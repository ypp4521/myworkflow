package org.zywx.appdo.workflow;

import java.util.Map;

import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.task.TaskQuery;

public class WorkflowTaskServiceImpl extends TaskServiceImpl implements WorkflowTaskService {

	public WorkflowTaskServiceImpl(ProcessEngineConfigurationImpl processEngineConfiguration){
		super(processEngineConfiguration);
	}


	@Override
	public void complete(String taskId, Map<String, Object> variables,String destinationTaskKey) {
		commandExecutor.execute(new WorkflowCompleteTaskCmd(taskId, variables,destinationTaskKey));
	}
	@Override
	public TaskQuery createTaskQuery() {
		return new WorkflowTaskQueryImpl(commandExecutor, processEngineConfiguration.getDatabaseType());
	}
}
