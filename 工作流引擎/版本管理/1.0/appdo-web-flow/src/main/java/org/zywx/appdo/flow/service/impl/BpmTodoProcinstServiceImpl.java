package org.zywx.appdo.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBizImpl;
import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.common.page.PageBean;
import org.zywx.appdo.common.page.PageParam;
import org.zywx.appdo.flow.dao.BpmTodoProcInstDao;
import org.zywx.appdo.flow.entity.BpmTodo;
import org.zywx.appdo.flow.entity.BpmTodoProcInst;
import org.zywx.appdo.flow.entity.RunProcessBean;
import org.zywx.appdo.flow.service.BpmTodoProcinstService;
import org.zywx.appdo.utils.MyStringUtils;
import org.zywx.appdo.workflow.WorkflowTaskService;

@Service
public class BpmTodoProcinstServiceImpl extends BaseBizImpl<BpmTodoProcInst>implements BpmTodoProcinstService {

	@Autowired
	private BpmTodoProcInstDao bpmTodoHiProcInstDao;
	@Autowired
	private WorkflowTaskService taskService;

	@Override
	protected BaseDao<BpmTodoProcInst> getDao() {
		return bpmTodoHiProcInstDao;
	}

	/**
	 * 获取我提交的数据
	 */
	@Override
	public DataGrid dataGridSubmit(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<BpmTodoProcInst> retList = super.getPage(tenantId, pageParam, paraMap);
		// 异常处理
		if (retList == null || retList.getList() == null || retList.getTotalCount() == 0) {
			return dataGrid;
		}
		// 变量定义
		String name = null;
		BpmTodo bpmTodo = null;
		List<Task> tasks = null;
		List<BpmTodo> listTodo = new ArrayList<BpmTodo>();
		for (BpmTodoProcInst procInst : retList.getList()) {
			bpmTodo = copyProperties(procInst);
			name = null;
			tasks = taskService.createTaskQuery().processInstanceId(procInst.getID_()).list();
			// 获取任务节点信息
			if (tasks != null && tasks.size() != 0) {
				for (Task task : tasks) {
					if (name != null) {
						name += "," + task.getName();
					} else {
						name = task.getName();
					}
					bpmTodo.setTaskId(task.getId());
				}
				bpmTodo.setNodeName(name);
			}
			listTodo.add(bpmTodo);
		}
		dataGrid.setRows(listTodo);
		dataGrid.setTotal(retList.getTotalCount());
		dataGrid.setPageCount(retList.getTotalPage());
		return dataGrid;
	}

	/**
	 * 查询指定用户发起的流程 （流程历史 用户发起 结束/未结束）
	 */
	@Override
	public DataGrid getMyIsUnfinishTaskList(String tenantId, Map<String, Object> paraMap, DataGrid dataGrid,
			String isFinished) {
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		// 执行查询
		PageBean<BpmTodoProcInst> retList;
		if ("1".equals(isFinished)) {// 已完成
			retList = super.getPage(tenantId, pageParam, paraMap, "isFinished");
		} else if ("0".equals(isFinished)) {// 未完成
			retList = super.getPage(tenantId, pageParam, paraMap, "isunFinished");
		} else {
			retList = super.getPage(tenantId, pageParam, paraMap);
		}
		// 异常处理
		if (retList == null || retList.getList() == null || retList.getTotalCount() == 0) {
			return dataGrid;
		}
		List<RunProcessBean> list = new ArrayList<RunProcessBean>();
		// 处理返回数据
		RunProcessBean runProcessBean = null;
		for (BpmTodoProcInst procInst : retList.getList()) {
			runProcessBean = new RunProcessBean();
			runProcessBean.setTitle(procInst.getTitle());
			runProcessBean.setBusiName(procInst.getBusiname());
			runProcessBean.setBusinessKey(procInst.getBusinessKey());
			runProcessBean.setProcName(procInst.getNAME_());
			runProcessBean.setInstanceid(procInst.getID_());
			runProcessBean.setDefId(procInst.getPROC_DEF_ID_());
			runProcessBean.setCreatetime(procInst.getCreatetime());
			runProcessBean.setBusiid(procInst.getBusiid());
			runProcessBean.setMetaid(procInst.getMetaid());
			runProcessBean.setTenantId(procInst.getTenantId().toString());
			runProcessBean.setBusicode(procInst.getBusicode());
			if ("1".equals(isFinished)) {
				runProcessBean.setEndTime(MyStringUtils.dateToString(procInst.getEND_TIME_(), "YYYY-MM-dd HH:mm:ss"));
			}
			list.add(runProcessBean);
		}
		dataGrid.setRows(list);
		dataGrid.setTotal(retList.getTotalCount());
		dataGrid.setPageCount(retList.getTotalPage());
		return dataGrid;
	}

	/**
	 * 属性复制
	 * 
	 * @param bpmTodoTask
	 * @param bpmTodo
	 * @return
	 */
	private BpmTodo copyProperties(BpmTodoProcInst bpmTodoProcInst) {
		BpmTodo bpmTodo = new BpmTodo();
		// 基本属性复制
		BeanUtils.copyProperties(bpmTodoProcInst, bpmTodo);
		return bpmTodo;
	}

}
