package org.zywx.appdo.flow.service;

import java.util.Map;

import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBiz;
import org.zywx.appdo.flow.entity.BpmTodoProcInst;

/**
 * 关联历史流程信息
 * 
 * @author xingshen.zhao
 *
 */
public interface BpmTodoProcinstService extends BaseBiz<BpmTodoProcInst> {
	/**
	 * 获取我的提交数据列表
	 * 
	 * @param tenantId
	 * @param userId
	 * @param dataGrid
	 * @return
	 */
	DataGrid dataGridSubmit(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid);

	/**
	 * 得到流转中未结束办理过的数据
	 * 
	 * @param paraMap
	 * @param dataGrid
	 * @return
	 */
	DataGrid getMyIsUnfinishTaskList(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid,String isFinished);

}