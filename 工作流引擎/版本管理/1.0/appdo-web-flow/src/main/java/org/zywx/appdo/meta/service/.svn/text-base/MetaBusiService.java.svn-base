package org.zywx.appdo.meta.service;

import java.util.List;
import java.util.Map;

import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.meta.entity.MetaBusi;
import org.zywx.appdo.meta.entity.MetaCustom;
import org.zywx.appdo.meta.entity.MetaCustomField;
import org.zywx.appdo.meta.entity.MetaTemplate;

import com.alibaba.dubbo.common.json.JSONObject;

public interface MetaBusiService extends BaseBiz<MetaBusi> {
	
	/**
	 * 保存表单模板
	 * 
	 * @param metaBusi
	 * @param parseForm
	 * @return
	 */
	Long saveMetaBusi(MetaBusi metaBusi, String parseForm);

	/**
	 * 获取文件内容
	 * 
	 * @param filepath
	 * @return
	 */
	String findForm(String filepath);

	/**
	 * 通过restful方式保存表单后启动流程
	 * 
	 * @param id
	 * @param json
	 * @param metaCustom
	 * @param listField
	 * @param tenantId
	 * @param token
	 * @param key
	 * @param variables
	 * @return
	 */
	String saveRestfulForm(String id, JSONObject json, MetaCustom metaCustom, List<MetaCustomField> listField,
			String tenantId, String key, Map<String, Object> variables);

	/**
	 * restful只保存表单不启动流程
	 * 
	 * @param id
	 * @param json
	 * @param metaCustom
	 * @param listField
	 * @param tenantId
	 * @param token
	 * @return
	 */
	String saveRestfulForm(String id, JSONObject json, MetaCustom metaCustom, List<MetaCustomField> listField,
			String tenantId, String metabusiid);

	/**
	 * 获取表单分页信息
	 * 
	 * @param metaid
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridBusi(String tenantId, long metaid, DataGrid dataGrid);
	
	/**
	 * 
	 * getFileBlobByTemplateId(得到模版字段类型信息)    <br/> 
	 * @user pengpeng.yuan@zymobi.com 
	 * @param   MetaBusiService  <br/>  
	 * @return  MetaTemplate  <br/>
	 * @method  @param map
	 * @method  @return  <br/>  
	 * @Exception 异常对象    <br/>
	 */
	MetaTemplate getFileBlobByTemplateId(Map<String, Object> map);

}
