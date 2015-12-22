package org.zywx.appdo.workflow;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.TaskQueryImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.CommandExecutor;

public class WorkflowTaskQueryImpl extends TaskQueryImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkflowTaskQueryImpl(CommandExecutor commandExecutor,String databaseType) {
		super(commandExecutor, databaseType);
	}
	@Override
	protected List<String> getGroupsForCandidateUser(String candidateUser) {
		String userid=candidateUser;
		if(Context.getCommandContext().getGroupIdentityManager() instanceof CustomGroupEntityManager){
			userid=super.tenantId+"&"+userid;
		}
		List<Group> groups = Context.getCommandContext().getGroupIdentityManager().findGroupsByUser(userid);
		List<String> groupIds = new ArrayList<String>();
		for (Group group : groups) {
			groupIds.add(group.getId());
		}
		return groupIds;
	}
}
