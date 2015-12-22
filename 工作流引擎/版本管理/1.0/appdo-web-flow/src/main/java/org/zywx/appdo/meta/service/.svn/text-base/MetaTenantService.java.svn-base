package org.zywx.appdo.meta.service;

import java.util.List;
import java.util.Map;

import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.meta.entity.MetaTenant;

public interface MetaTenantService extends BaseBiz<MetaTenant> {

	/**
	 * 保存流程绑定信息表
	 * @param metaTenant
	 * @return
	 */
	public String saveMetaTenant(MetaTenant metaTenant);
	/**
	 * 修改流程绑定信息
	 * @param metaTenant
	 * @return
	 */
	public String updateMetaTenant(MetaTenant metaTenant);
	/**
	 * 删除流程绑定信息
	 * @param id
	 * @return
	 */
	public int deleteMetaTenant(MetaTenant metaTenant);
	/**
	 * 部署流程定义
	 * @param metaTenant
	 * @return
	 */
	public String deployModel(MetaTenant metaTenant);
	/**
	 * 获取流程绑定信息
	 * @param tenantId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridMetaTenant(String tenantId,String metaId,DataGrid dataGrid);
	
	/**
	 * checkFlowIsDeployModel(查看流程是否已经部署)    <br/> 
	 * @user pengpeng.yuan@zymobi.com 
	 * @param   MetaTenantService  <br/>  
	 * @return  String  <br/>
	 * @method  @param paramMap
	 * @method  @return  <br/>  
	 * @Exception 异常对象    <br/>
	 */
	public List<MetaTenant> getDeployModelListByMap(Map<String, Object> paramMap);
}
