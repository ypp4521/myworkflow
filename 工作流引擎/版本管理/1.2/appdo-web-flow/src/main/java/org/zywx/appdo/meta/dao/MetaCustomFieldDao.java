package org.zywx.appdo.meta.dao;

import java.util.List;
import java.util.Map;

import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.meta.entity.MetaCustomField;

/**
 * 
 * 项目名称：appdo-web-flow <br/>
 * 类名称：MetaCustomFieldDao <br/>
 * 类描述： 模版字段管理接口 <br/>
 * 创建人：pengpeng.yuan@zymobi.com <br/>
 * 创建时间：2015年11月6日 上午10:40:05 <br/>
 * 修改人：Yuanpp <br/>
 * 文件名：MetaCustomFieldDao.java <br/>
 * 版本信息：1.0 <br/>
 * Copyright Corporation 2015 <br/>
 * 正益移动互联科技股份有限公司<br/>
 * 
 * @version 1.0<br/>
 */
public interface MetaCustomFieldDao extends BaseDao<MetaCustomField> {

	/**
	 * getTemplateFieldSByIdAndMetaId(根据租户id和模版id得到字段)    <br/>  
	 * @param   MetaCustomFieldService  <br/>  
	 * @return  List<MetaCustomField>  <br/>
	 * @method    <br/>  
	 * @Exception 异常对象    <br/>
	 */
	List<MetaCustomField> getTemplateFieldSByIdAndMetaId(Map<String, Object> map);
}
