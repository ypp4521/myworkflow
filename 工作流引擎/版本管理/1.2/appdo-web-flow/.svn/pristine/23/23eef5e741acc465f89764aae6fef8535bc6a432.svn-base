package org.zywx.appdo.meta.service.impl;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBizImpl;
import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.common.exception.FlowBusinessRuntimeException;
import org.zywx.appdo.common.page.PageBean;
import org.zywx.appdo.common.page.PageParam;
import org.zywx.appdo.common.tree.TreeNode;
import org.zywx.appdo.meta.dao.MetaBusiDao;
import org.zywx.appdo.meta.dao.MetaCustomDao;
import org.zywx.appdo.meta.entity.MetaBusi;
import org.zywx.appdo.meta.entity.MetaCustom;
import org.zywx.appdo.meta.entity.MetaCustomField;
import org.zywx.appdo.meta.service.MetaCustomFieldService;
import org.zywx.appdo.meta.service.MetaCustomService;
import org.zywx.appdo.utils.PropertyTools;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;

@Service
public class MetaCustomServiceImpl extends BaseBizImpl<MetaCustom>implements MetaCustomService {

	@Autowired
	private MetaCustomDao metaCustomDao;
	@Autowired
	private MetaCustomFieldService metaCustomFieldService;

	@Override
	protected BaseDao<MetaCustom> getDao() {
		return metaCustomDao;
	}

	@Override
	public List<TreeNode> metaCustomTree(String tenantId, long parentid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", parentid);
		paramMap.put("tenantId", tenantId);
		List<MetaCustom> listMeta = metaCustomDao.getList(paramMap);
		List<TreeNode> listTree = new ArrayList<TreeNode>();
		TreeNode treeNode = null;
		for (MetaCustom meta : listMeta) {
			List<MetaCustom> list = null;
			treeNode = new TreeNode();
			treeNode.setId(meta.getId() + "");
			treeNode.setpId(meta.getParentid());
			treeNode.setName(meta.getMetaname());
			treeNode.setOpen(true);
			paramMap.put("parentid", meta.getId());
			paramMap.put("tenantId", tenantId);
			list = metaCustomDao.getList(paramMap);
			if (list != null && list.size() != 0) {
				treeNode.setIsParent("true");
			} else {
				treeNode.setIsParent("false");
			}
			treeNode.setIsleaf(true);
			listTree.add(treeNode);
		}
		return listTree;
	}

	@Override
	public DataGrid dataGridMetaCustom(String tenantId, DataGrid dataGrid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sort", "createtime");
		paramMap.put("dir", "desc");
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<MetaCustom> metaList = super.getPage(tenantId, pageParam, paramMap);
		dataGrid.setRows(metaList.getList());
		dataGrid.setTotal(metaList.getTotalCount());
		return dataGrid;
	}

	/*
	 * description：根据主键值查找数据 author：xingshen.zhao date：2015年10月28日
	 */
	@Override
	public List<Map<String, Object>> findBusiByBusiKey(String tenantId, Long metaid, String busiKey,
			ServletContext servletContext) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			MetaCustom metaCustom = super.getById(tenantId, metaid);
			Map<String, Object> map = null;
			PropertyDescriptor pd = null;
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("metaid", metaid);
			List<MetaCustomField> listField = metaCustomFieldService.getList(tenantId, paramMap);
			if (metaCustom.getQuerymethod() != null && metaCustom.getQuerymethod().indexOf("/") > -1) {
				// 提交数据到协同办公远程借口
				PostMethod pm = new PostMethod(metaCustom.getQuerymethod());
				try {
					JSONObject param = new JSONObject();
					JSONObject condition = new JSONObject();
					JSONObject content = new JSONObject();
					pm.setRequestHeader("accept", "application/json");
					pm.setRequestHeader("Encoding", "UTF-8");
					// 拼装condition
					condition.put("condition", "");
					// 拼装content
					content.put("objectId", busiKey);
					content.put("entityTypeId", "24");
					// 拼装传入参数
					param.put("ifno", "zywx-note-0003");
					param.put("condition", condition);
					param.put("content", content);
					pm.setParameter(PropertyTools.getPropertyByKey("postBody"), JSON.json(param));
					HttpClient hc = new HttpClient();
					hc.getParams().setContentCharset("UTF-8");
					int code = hc.executeMethod(pm);
					if (code == 200) {
						// 取得返回值，获取主键值
						String result = pm.getResponseBodyAsString();
						JSONObject json = (JSONObject) ((JSONObject) (JSONObject) ((JSONObject) (JSONObject) ((JSONObject) JSON
								.parse(result)).get("msg")).get("item")).get("entity");
						// (String)((JSONObject)((JSONObject)JSON.parse(result)).get("msg")).get("objectId");
						for (MetaCustomField field : listField) {
							map = new HashMap<String, Object>();
							map.put("type", field.getFieldtype());
							map.put("code", field.getFieldcode());
							map.put("value", json.get(field.getFieldcode()));
							listMap.add(map);
						}
						try {
							map = new HashMap<String, Object>();
							map.put("type", "hidden");
							map.put("code", PropertyTools.getPropertyByKey("id"));
							map.put("value", json.get(PropertyTools.getPropertyByKey("id")));
							listMap.add(map);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						throw new FlowBusinessRuntimeException("请求失败");
					}
				} finally {
					pm.releaseConnection();
				}
			} else {
				Object obj = null;
				if (servletContext == null) {
					obj = ContextLoader.getCurrentWebApplicationContext().getBean(metaCustom.getMetaService());
				} else {
					obj = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
							.getBean(metaCustom.getMetaService());
				}
				Method wM = null;
				if (metaCustom.getQuerymethod() != null && metaCustom.getQuerymethod().length() != 0) {
					wM = obj.getClass().getDeclaredMethod(metaCustom.getQuerymethod(), String.class, Long.class);
				} else {
					wM = obj.getClass().getDeclaredMethod(PropertyTools.getPropertyByKey("getById"), String.class,
							Long.class);
				}
				Object busi = wM.invoke(obj, tenantId, Long.parseLong(busiKey));
				Class c = Class.forName(metaCustom.getClasspath());
				for (MetaCustomField field : listField) {
					map = new HashMap<String, Object>();
					map.put("type", field.getFieldtype());
					map.put("code", field.getFieldcode());
					pd = new PropertyDescriptor(field.getFieldcode(), c);
					wM = pd.getReadMethod();// 获得写方法
					map.put("value", wM.invoke(busi));
					listMap.add(map);
				}
				try {
					map = new HashMap<String, Object>();
					map.put("type", "hidden");
					map.put("code", PropertyTools.getPropertyByKey("id"));
					pd = new PropertyDescriptor(PropertyTools.getPropertyByKey("id"), c);
					wM = pd.getReadMethod();// 获得写方法
					map.put("value", wM.invoke(busi));
					listMap.add(map);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public String findBusiVal(String tenantId, Long metaid, String busiKey, String propertity) {
		try {
			MetaCustom metaCustom = super.getById(tenantId, metaid);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("metaid", metaid);
			if (metaCustom.getQuerymethod().indexOf("/") > -1) {
				HttpClient htpc = new HttpClient();
				GetMethod getMethod = new GetMethod(metaCustom.getQuerymethod() + "/" + busiKey + "/" + tenantId);
				getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
				getMethod.getParams().setContentCharset("UTF-8");
				// 执行getMethod
				int statusCode = htpc.executeMethod(getMethod);
				if (statusCode != 200) {
					throw new FlowBusinessRuntimeException("获取数据异常");
				}
				// 读取内容
				// byte[] responseBody = getMethod.getResponseBody();
				// 这里的编码规则要与上面的相对应
				BufferedReader br = new BufferedReader(
						new InputStreamReader(getMethod.getResponseBodyAsStream(), "utf-8"));
				String tmp = null;
				StringBuffer sb = new StringBuffer();
				while ((tmp = br.readLine()) != null) {
					sb.append(tmp);
				}
				;
				// 处理内容
				JSONObject json = (JSONObject) JSON.parse(sb.toString());
				return json.getString(propertity);
			} else {
				Object obj = ContextLoader.getCurrentWebApplicationContext().getBean(metaCustom.getMetaService());
				Method wM = null;
				if (metaCustom.getQuerymethod() != null && metaCustom.getQuerymethod().length() != 0) {
					wM = obj.getClass().getDeclaredMethod(metaCustom.getQuerymethod(), String.class, Long.class);
				} else {
					wM = obj.getClass().getDeclaredMethod(PropertyTools.getPropertyByKey("getById"), String.class,
							Long.class);
				}
				Object busi = wM.invoke(obj, tenantId, Long.parseLong(busiKey));
				Class cla = Class.forName(metaCustom.getClasspath());
				PropertyDescriptor pd = new PropertyDescriptor(propertity, cla);
				Method m = pd.getReadMethod();// 获得get方法
				return m.invoke(Class.forName(metaCustom.getClasspath())).toString();
			}
		} catch (Exception e) {
		}
		return null;
	}
}
