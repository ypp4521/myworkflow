package org.zywx.appdo.meta.service;

import java.util.List;
import java.util.Map;

import org.zywx.appdo.bean.QueryWorkFlowAndFieldBean;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.meta.entity.MetaCustomField;

public interface MetaCustomFieldService extends BaseBiz<MetaCustomField> {

	/**
	 * 查询元数据字段列表
	 * 
	 * @param tenantId
	 * @param id
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridMetaCustomField(String tenantId, long id, DataGrid dataGrid);

	/**
	 * 查询元数据字段列表-接口用
	 * 
	 * @param tenantId
	 * @param id
	 * @param dataGrid
	 * @return
	 */
	DataGrid getCustomFieldApp(String tenantId, long id, DataGrid dataGrid);

	/**
	 * 
	 * getTemplateFieldSByIdAndMetaId(根据租户id和模版id得到字段) <br/>
	 * 
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return List<MetaCustomField> <br/>
	 * @method <br/>
	 * @Exception 异常对象 <br/>
	 */
	List<MetaCustomField> getTemplateFieldSByIdAndMetaId(Map<String, Object> map);

	/**
	 * 
	 * saveWorkFlowNodesByModelId(增加流程绑定表单模版方法) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return void <br/>
	 * @method @param map <br/>
	 * @Exception 异常对象 <br/>
	 */
	void saveWorkFlowNodesByModelId(Map<String, Object> map);

	/**
	 * 
	 * getWorkFlowNodesList(查询流程绑定字段信息 ，主要传给 app 和 pc) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return List<QueryWorkFlowAndFieldBean> <br/>
	 * @method @param map
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	QueryWorkFlowAndFieldBean getWorkFlowNodesList(Map<String, Object> map);

	/**
	 * 
	 * updateQueryWorkFlowAndFieldBean(更新流程节点与意见字段信息) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return void <br/>
	 * @method @param map <br/>
	 * @Exception 异常对象 <br/>
	 */
	void updateQueryWorkFlowAndFieldBean(Map<String, Object> map);

	/**
	 * 
	 * deleteQueryWorkFlowAndFieldBean(删除流程节点与意见字段信息) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomFieldService
	 *            <br/>
	 * @return void <br/>
	 * @method @param map <br/>
	 * @Exception 异常对象 <br/>
	 */
	void deleteQueryWorkFlowAndFieldBean(Map<String, Object> map);

}
