package org.zywx.appdo.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.stereotype.Component;
 
/**
 * 自定义的Activiti用户组管理器
 * 
 * 
 */
@Component
public class CustomGroupEntityManager extends GroupEntityManager {
    @Override
    public GroupQuery createNewGroupQuery() {
        return super.createNewGroupQuery();
    }
 
    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        return super.findGroupByQueryCriteria(query, page);
    }
 
    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        return super.findGroupCountByQueryCriteria(query);
    }
 
    @Override
    public List<Group> findGroupsByUser(String userId) {
    	List<Group> groups=new ArrayList<Group>();
        return groups;
    }
    
    @Override
	public List<Group> findGroupsByNativeQuery(
			Map<String, Object> parameterMap, int firstResult, int maxResults) {
		return super.findGroupsByNativeQuery(parameterMap, firstResult, maxResults);
	
    }
	@Override
	public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
		return super.findGroupCountByNativeQuery(parameterMap);
	}
}
