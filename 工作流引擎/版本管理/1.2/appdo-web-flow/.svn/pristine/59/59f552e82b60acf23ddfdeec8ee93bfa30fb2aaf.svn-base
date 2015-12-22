package org.zywx.appdo.meta.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.zywx.appdo.common.core.dao.BaseDaoImpl;
import org.zywx.appdo.meta.dao.MetaBusiDao;
import org.zywx.appdo.meta.entity.MetaBusi;
import org.zywx.appdo.meta.entity.MetaTemplate;

@Repository
public class MetaBusiDaoImpl extends BaseDaoImpl<MetaBusi> implements MetaBusiDao {

	@Override
	public int findMaxVersion() {
		return this.getSqlSession().selectOne("org.zywx.appdo.meta.dao.impl.MetaBusiDaoImpl.selectMaxVersion");
	}

	@Override
	public MetaTemplate getFileBlobByTemplateId(Map<String, Object> map) {
		return getSessionTemplate().selectOne(getStatement("getFileBlobById"), map);
	}

}
