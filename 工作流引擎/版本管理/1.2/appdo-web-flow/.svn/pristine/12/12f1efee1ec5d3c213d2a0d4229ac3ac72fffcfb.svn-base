package org.zywx.appdo.meta.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.common.tree.TreeNode;
import org.zywx.appdo.meta.entity.MetaCustom;

public interface MetaCustomService extends BaseBiz<MetaCustom> {

	/**
	 * 获取元数据树形结构数据
	 * @param tenantId
	 * @param id
	 * @return
	 */
	List<TreeNode> metaCustomTree(String tenantId,long id);
	/**
	 * 获取元数据分页列表数据
	 * @param tenantId
	 * @return
	 */
	DataGrid dataGridMetaCustom(String tenantId,DataGrid dataGrid);
	/**
	 * 获取表单数据信息
	 * @param tenantId
	 * @param metaid
	 * @param busiKey
	 * @return
	 */
	List<Map<String,Object>> findBusiByBusiKey(String tenantId,Long metaid,String busiKey,ServletContext servletContext);
	/**
	 * 
	 * @param tenantId
	 * @param metaid
	 * @param busiKey
	 * @return
	 */
	String findBusiVal(String tenantId, Long metaid, String busiKey,String propertity);
}
