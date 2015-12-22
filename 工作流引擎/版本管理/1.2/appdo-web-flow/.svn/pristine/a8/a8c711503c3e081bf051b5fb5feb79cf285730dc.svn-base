package org.zywx.appdo.workflow;

import java.util.Map;

import org.activiti.engine.impl.cmd.CompleteTaskCmd;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

public class WorkflowCompleteTaskCmd extends CompleteTaskCmd {

	private static final long serialVersionUID = 1L;

	protected String destinationTaskKey;

	public WorkflowCompleteTaskCmd(String taskId, Map<String, Object> variables) {
		super(taskId, variables);
	}

	public WorkflowCompleteTaskCmd(String taskId, Map<String, Object> variables, String destinationTaskKey) {
		super(taskId, variables);
		this.destinationTaskKey = destinationTaskKey;
	}

	protected Void execute(CommandContext commandContext, TaskEntity task) {
		if (variables != null) {
			if (localScope) {
				task.setVariablesLocal(variables);
			} else {
				task.setExecutionVariables(variables);
			}
		}
		if (destinationTaskKey == null) {
			task.complete(variables, localScope);
		} else {
			ExecutionEntity executionEntity = commandContext.getExecutionEntityManager()
					.findExecutionById(task.getExecutionId());
			ActivityImpl activity = executionEntity.getProcessDefinition().findActivity(destinationTaskKey);
			if (activity == null) {
				throw new RuntimeException("未找到跳转节点");
			}
			for (TaskEntity taskEntity : commandContext.getTaskEntityManager()
					.findTasksByExecutionId(task.getExecutionId())) {
				commandContext.getTaskEntityManager().deleteTask(taskEntity, "jump", false);
			}
			try {
				executionEntity.executeActivity(activity);
			} catch (Exception e) {
				throw new RuntimeException("流程跳转失败" + e.getMessage());
			}
		}
		return null;
	}
}
