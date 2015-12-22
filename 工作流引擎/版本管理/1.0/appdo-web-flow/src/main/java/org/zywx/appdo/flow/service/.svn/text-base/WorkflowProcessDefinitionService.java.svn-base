package org.zywx.appdo.flow.service;

import org.activiti.engine.repository.ProcessDefinition;

public interface WorkflowProcessDefinitionService {

	ProcessDefinition findProcessDefinitionByPid(String processInstanceId);

	ProcessDefinition findProcessDefinition(String processDefinitionId);

	void deployFromClasspath(String exportDir, String[] processKey)
			throws Exception;

	void redeploy(String exportDir, String[] processKey) throws Exception;

	void deployAllFromClasspath(String exportDir) throws Exception;

}
