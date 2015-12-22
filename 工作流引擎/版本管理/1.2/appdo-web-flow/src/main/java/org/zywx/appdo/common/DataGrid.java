package org.zywx.appdo.common;

import java.util.List;

/**
 * easyui的datagrid向后台传递参数使用的model
 * 
 * @author 秦飞
 * 
 */
public class DataGrid {

	private int page = 1;// 当前页
	private int pageSize = 10;// 每页显示记录数
	private String sort = null;// 排序字段名
	private SortDirection order = SortDirection.asc;// 按什么排序(asc,desc)
	private String field;//字段
	private String treefield;//树形数据表文本字段
	private List rows;// 结果集
	private long total;//总记录数
	private String footer;//合计列
	private int pageCount;//总条数
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long l) {
		this.total = l;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public SortDirection getOrder() {
		return order;
	}

	public void setOrder(SortDirection order) {
		this.order = order;
	}
	public String getTreefield() {
		return treefield;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
