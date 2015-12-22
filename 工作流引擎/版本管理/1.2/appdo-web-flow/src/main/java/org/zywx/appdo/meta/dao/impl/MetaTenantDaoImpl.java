package org.zywx.appdo.meta.dao.impl;

import org.springframework.stereotype.Repository;
import org.zywx.appdo.common.core.dao.BaseDaoImpl;
import org.zywx.appdo.meta.dao.MetaTenantDao;
import org.zywx.appdo.meta.entity.MetaTenant;

@Repository
public class MetaTenantDaoImpl extends BaseDaoImpl<MetaTenant>implements MetaTenantDao {
	/**
	 * 删除流程模型相关
	 * 
	 * @param String modelId
	 */
	public void deleteModelProc(String modelId) {
		getSessionTemplate().delete("org.zywx.appdo.meta.dao.impl.MetaTenantDaoImpl.deleteACT_RE_DEPLOYMENT", modelId);
		getSessionTemplate().delete("org.zywx.appdo.meta.dao.impl.MetaTenantDaoImpl.deleteACT_RE_PROCDEF", modelId);
	}
}
