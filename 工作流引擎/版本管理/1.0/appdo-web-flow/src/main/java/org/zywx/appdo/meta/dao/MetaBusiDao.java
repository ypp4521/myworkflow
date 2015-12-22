package org.zywx.appdo.meta.dao;

import java.util.Map;

import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.meta.entity.MetaBusi;
import org.zywx.appdo.meta.entity.MetaTemplate;

public interface MetaBusiDao extends BaseDao<MetaBusi> {

	/**
	 * 查询最大版本号
	 * 
	 * @return
	 */
	int findMaxVersion();

	/**
	 * 
	 * getFileBlobByTemplateId(根据模版类型id和租户id得到模版内容) <br/>
	 * 
	 * @param MetaBusiDao
	 *            <br/>
	 * @return MetaBusi <br/>
	 * @method <br/>
	 * @Exception 异常对象 <br/>
	 */
	MetaTemplate getFileBlobByTemplateId(Map<String, Object> map);
}
