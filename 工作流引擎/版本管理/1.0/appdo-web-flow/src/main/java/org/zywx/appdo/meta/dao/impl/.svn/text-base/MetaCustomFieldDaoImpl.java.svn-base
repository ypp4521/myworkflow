package org.zywx.appdo.meta.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.zywx.appdo.common.core.dao.BaseDaoImpl;
import org.zywx.appdo.meta.dao.MetaCustomFieldDao;
import org.zywx.appdo.meta.entity.MetaCustomField;

@Repository
public class MetaCustomFieldDaoImpl extends BaseDaoImpl<MetaCustomField> implements MetaCustomFieldDao {
	/**
	 * getTemplateFieldSByIdAndMetaId(根据租户id和模版id得到字段) <br/>
	 * 
	 * @param MetaCustomFieldService
	 * @return List<MetaCustomField> <br/>
	 * @method <br/>
	 * @Exception 异常对象 <br/>
	 */
	public List<MetaCustomField> getTemplateFieldSByIdAndMetaId(Map<String, Object> map) {
		return getSessionTemplate().selectList(getStatement("getTemplateFieldSByIdAndMetaId"),map);
	}
}
