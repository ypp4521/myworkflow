package org.zywx.appdo.workflow;

import java.util.Map;

import org.activiti.engine.TaskService;

public interface WorkflowTaskService extends TaskService {

	void complete(String taskId, Map<String, Object> variables,String destinationTaskKey);	
}
