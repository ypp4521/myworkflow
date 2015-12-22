package org.zywx.appdo.meta.controller;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.zywx.appdo.bean.QueryWorkFlowAndFieldBean;
import org.zywx.appdo.common.AjaxResult;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.entity.AbstractBaseEntity;
import org.zywx.appdo.common.enums.EnableEnum;
import org.zywx.appdo.common.enums.FieldTodoEnum;
import org.zywx.appdo.common.enums.FieldTypeEnum;
import org.zywx.appdo.common.enums.IfYesOrNoEnum;
import org.zywx.appdo.common.enums.IsAppEnum;
import org.zywx.appdo.common.exception.FlowBusinessRuntimeException;
import org.zywx.appdo.common.tree.TreeNode;
import org.zywx.appdo.flow.entity.BpmTodo;
import org.zywx.appdo.flow.service.BpmApproveService;
import org.zywx.appdo.flow.service.BpmTodoService;
import org.zywx.appdo.flow.service.WorkflowService;
import org.zywx.appdo.meta.entity.MetaBusi;
import org.zywx.appdo.meta.entity.MetaCustom;
import org.zywx.appdo.meta.entity.MetaCustomField;
import org.zywx.appdo.meta.entity.MetaTenant;
import org.zywx.appdo.meta.service.MetaBusiService;
import org.zywx.appdo.meta.service.MetaCustomFieldService;
import org.zywx.appdo.meta.service.MetaCustomService;
import org.zywx.appdo.meta.service.MetaTenantService;
import org.zywx.appdo.utils.MyJsonUtil;
import org.zywx.appdo.utils.PropertyTools;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.json.ParseException;

/**
 * 元数据管理
 * 
 * @author qf
 *
 */
@Controller
@RequestMapping(value = "/settingMeta")
public class MetaCustomController {
	@Autowired
	private MetaCustomService metaCustomService;
	@Autowired
	private MetaCustomFieldService metaCustomFieldService;
	@Autowired
	private MetaBusiService metaBusiService;
	@Autowired
	private MetaTenantService metaTenantService;
	@Autowired
	private BpmTodoService bpmTodoService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private BpmApproveService bpmApproveService;
	
	AjaxResult ajaxResult = new AjaxResult();
	Map<String, Object> paraMap = new HashMap<String, Object>();

	/**
	 * 元数据列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("metaCustomList")
	public ModelAndView metaCustomList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("meta/metaCustomList");
		request.setAttribute("listMeta", metaCustomService.getList(PropertyTools.getPropertyByKey("tenantId"), null));
		return modelAndView;
	}

	/**
	 * 获取表单列表数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("findFormList")
	public AjaxResult findFormList(HttpServletRequest request) {
		ajaxResult.setStatus("ok");
		ajaxResult.setResult(metaCustomService.getList(PropertyTools.getPropertyByKey("tenantId"), null));
		return ajaxResult;
	}

	/**
	 * 获取元数据树
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("metaCustomTree")
	@ResponseBody
	public List<TreeNode> metaCustomTree(@RequestParam(value = "id", defaultValue = "-1") long id,
			HttpServletRequest request) {
		return metaCustomService.metaCustomTree(PropertyTools.getPropertyByKey("tenantId"), id);
	}

	/**
	 * 元数据分页
	 * 
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("dataGridMetaCustom")
	@ResponseBody
	public DataGrid dataGridMetaCustom(HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return metaCustomService.dataGridMetaCustom(PropertyTools.getPropertyByKey("tenantId"), dataGrid);
	}

	/**
	 * 获取流程、元数据、租户绑定信息
	 * 
	 * @param metaId
	 * @param tenantId
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping("dataGridMetaTenant")
	@ResponseBody
	public DataGrid dataGridMetaTenant(@RequestParam(value = "metaId", defaultValue = "") String metaId,
			@RequestParam(value = "tenantId", defaultValue = "") String tenantId, HttpServletRequest request,
			DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		if (tenantId == null || tenantId.length() == 0) {
			tenantId = PropertyTools.getPropertyByKey("tenantId");
		}
		return metaTenantService.dataGridMetaTenant(tenantId, metaId, dataGrid);
	}

	/**
	 * 保存流程绑定信息
	 * 
	 * @param metaBusi
	 * @param request
	 * @return
	 */
	@RequestMapping("editMetaBusi")
	@ResponseBody
	public AjaxResult editMetaBusi(@RequestBody String jsonParam, HttpServletRequest request) {
		try {
			/*
			 * Map<String, Object> paramMap = new HashMap<String, Object>();
			 * paramMap.put("id",
			 * Long.valueOf(request.getParameter("metaBusi.id")));
			 * paramMap.put("tenantId",
			 * Long.valueOf(PropertyTools.getPropertyByKey("tenantId")));
			 * paramMap.put("flowid", request.getParameter("metaBusi.flowid"));
			 */

			paraMap = MyJsonUtil.convertJsonToMap(jsonParam);
			String flow_id = paraMap.get("flow_id").toString();
			paraMap.remove("flow_id");
			MetaBusi metaBusi = metaBusiService.getByMap(paraMap.get("tenantId").toString(), paraMap);
			if (null == metaBusi) {
				ajaxResult.setInfo("绑定流程失败");
				ajaxResult.setStatus("no");
			} else {
				metaBusi.setFlowid(flow_id);
			}

			// metaBusi.setId(Long.valueOf(request.getParameter("metaBusi.id")));
			// metaBusi.setBusicode(request.getParameter("metaBusi.busicode"));
			// metaBusi.setBusiname(request.getParameter("metaBusi.businame"));
			// metaBusi.setBusipath(request.getParameter("metaBusi.busipath"));
			// metaBusi.setIsapp(request.getParameter("metaBusi.isapp"));
			// metaBusi.setMetaid(Long.valueOf(request.getParameter("metaBusi.metaid")));
			// metaBusi.setVersion(Integer.valueOf(request.getParameter("metaBusi.version")));
			// metaBusi.setFlowid(request.getParameter("metaBusi.flowid"));
			// metaBusi.setCreatetime(new Date());
			// metaBusi.setRemark(request.getParameter("metaBusi.remark"));
			metaBusiService.update(PropertyTools.getPropertyByKey("tenantId"), metaBusi);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("绑定流程成功");
		} catch (Exception e) {
			ajaxResult.setStatus("no");
			ajaxResult.setInfo("绑定流程失败");
		}
		return ajaxResult;
	}

	/**
	 * 元数据新增
	 * 
	 * @param metaCustom
	 * @param request
	 * @return
	 */
	@RequestMapping("saveMetaCustom")
	@ResponseBody
	public AjaxResult saveMetaCustom(MetaCustom metaCustom, HttpServletRequest request) {
		try {
			Calendar calendar = Calendar.getInstance();
			metaCustom = new MetaCustom();
			metaCustom.setParentid("-1");
			metaCustom.setCreatetime(new Date());
			metaCustom.setMetaname(request.getParameter("metaCustom.metaname"));
			metaCustom.setMetacode(request.getParameter("metaCustom.metacode"));
			metaCustom.setClasspath(request.getParameter("metaCustom.classpath"));
			metaCustom.setMetaService(request.getParameter("metaCustom.metaService"));
			metaCustom.setFunctionid(request.getParameter("metaCustom.functionid"));
			metaCustom.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			metaCustom.setRemark(request.getParameter("metaCustom.remark"));
			metaCustom.setUserId("admin");
			metaCustom.setUserName("admin");
			metaCustomService.save(PropertyTools.getPropertyByKey("tenantId"), metaCustom);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("保存成功");
		} catch (Exception e) {
			ajaxResult.setInfo("保存失败");
		}
		return ajaxResult;
	}

	/**
	 * 元数据修改
	 * 
	 * @param metaCustom
	 * @param request
	 * @return
	 */
	@RequestMapping("editMetaCustom")
	@ResponseBody
	public AjaxResult editMetaCustom(MetaCustom metaCustom, HttpServletRequest request) {
		try {
			Calendar calendar = Calendar.getInstance();
			metaCustom = new MetaCustom();
			metaCustom.setParentid("-1");
			metaCustom.setId(Long.parseLong(request.getParameter("metaCustom.id")));
			metaCustom.setMetaname(request.getParameter("metaCustom.metaname"));
			metaCustom.setMetacode(request.getParameter("metaCustom.metacode"));
			metaCustom.setClasspath(request.getParameter("metaCustom.classpath"));
			metaCustom.setMetaService(request.getParameter("metaCustom.metaService"));
			metaCustom.setFunctionid(request.getParameter("metaCustom.functionid"));
			metaCustom.setQuerymethod(request.getParameter("metaCustom.querymethod"));
			metaCustom.setCreatetime(new Date());
			metaCustom.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
			metaCustom.setUserId("admin");
			metaCustom.setUserName("admin");
			metaCustom.setRemark(request.getParameter("metaCustom.remark"));
			metaCustomService.update(PropertyTools.getPropertyByKey("tenantId"), metaCustom);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("修改成功");
		} catch (Exception e) {
			ajaxResult.setInfo("修改失败");
		}
		return ajaxResult;
	}

	/**
	 * 删除元数据
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMetaCustom/{id}")
	@ResponseBody
	public AjaxResult delMetaCustom(@PathVariable("id") long id, HttpServletRequest request) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("metaid", id);
			List list = metaCustomFieldService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap);
			if (list != null && list.size() != 0) {
				throw new FlowBusinessRuntimeException("已经存在字段");
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("metaid", id);
			list = metaBusiService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap);
			if (list != null && list.size() != 0) {
				throw new FlowBusinessRuntimeException("已经存在单据模板");
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("metaid", id);
			list = metaTenantService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap);
			if (list != null && list.size() != 0) {
				throw new FlowBusinessRuntimeException("已经存在流程绑定");
			}
			metaCustomService.deleteById(PropertyTools.getPropertyByKey("tenantId"), id);
			
			//删除流程绑定意见字段
			Map<String, Object> deleMap = new HashMap<String, Object>();
			deleMap.put("wf_meta_customs_id", id);
			deleMap.put("wf_tenantId", PropertyTools.getPropertyByKey("tenantId"));
			metaCustomFieldService.deleteQueryWorkFlowAndFieldBean(deleMap);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("删除成功");
		} catch (Exception e) {
			ajaxResult.setInfo("删除失败，" + e.getMessage() + "!");
		}
		return ajaxResult;
	}

	/**
	 * 元数据字段列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "metaCustomFieldList/{id}")
	public ModelAndView metaCustomFieldList(@PathVariable("id") long id, HttpServletRequest request) {
		request.setAttribute("id", id);
		FieldTypeEnum[] types = FieldTypeEnum.values();
		IfYesOrNoEnum[] typeY = IfYesOrNoEnum.values();
		List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listIfYesNo = new ArrayList<Map<String, String>>();
		for (FieldTypeEnum type : types) {
			listType.add(type.getMap());
		}
		for (IfYesOrNoEnum type : typeY) {
			listIfYesNo.add(type.getMap());
		}
		request.setAttribute("listType", listType);
		request.setAttribute("listIf", listIfYesNo);
		FieldTodoEnum[] todos = FieldTodoEnum.values();
		List<Map<String, String>> listTodo = new ArrayList<Map<String, String>>();
		for (FieldTodoEnum todo : todos) {
			listTodo.add(todo.getMap());
		}
		request.setAttribute("listTodo", listTodo);
		ModelAndView modelAndView = new ModelAndView("meta/metaCustomFieldList");
		return modelAndView;
	}

	/**
	 * 获取元数据字段数据
	 * 
	 * @param request
	 * @param id
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "dataGridMetaCustomField/{id}")
	@ResponseBody
	public DataGrid dataGridMetaCustomField(@PathVariable("id") long id, HttpServletRequest request, DataGrid dataGrid,
			int rows) {
		dataGrid.setPageSize(rows);
		return metaCustomFieldService.dataGridMetaCustomField(PropertyTools.getPropertyByKey("tenantId"), id, dataGrid);
	}

	/**
	 * 元数据字段新增
	 * 
	 * @param metaCustom
	 * @param request
	 * @return
	 */
	@RequestMapping("saveMetaCustomField")
	@ResponseBody
	public AjaxResult saveMetaCustomField(MetaCustomField metaCustomField, HttpServletRequest request) {
		try {
			metaCustomField = new MetaCustomField();
			metaCustomField.setFieldname(request.getParameter("metaCustomField.fieldname"));
			metaCustomField.setFieldcode(request.getParameter("metaCustomField.fieldcode"));
			metaCustomField.setFieldtype(request.getParameter("metaCustomField.fieldtype"));
			metaCustomField.setChecktype(request.getParameter("metaCustomField.checktype"));
			metaCustomField.setFieldtodo(request.getParameter("metaCustomField.fieldtodo"));
			metaCustomField.setTodotemplate(request.getParameter("metaCustomField.todotemplate"));
			metaCustomField.setMetaid(Long.parseLong(request.getParameter("metaCustomField.metaid")));
			metaCustomField.setCreatetime(new Date());
			metaCustomField.setIfEdit(request.getParameter("metaCustomField.ifEdit"));
			metaCustomField.setIfAuto(request.getParameter("metaCustomField.ifAuto"));
			metaCustomField.setIfVisible(request.getParameter("metaCustomField.ifVisible"));
			metaCustomField.setDefaultValue(request.getParameter("metaCustomField.defaultValue"));
			metaCustomField.setIfVariable(request.getParameter("metaCustomField.ifVariable"));
			metaCustomField.setIsOpinion(request.getParameter("metaCustomField.isOpinion"));
			metaCustomField.setIfVariableValue(request.getParameter("metaCustomField.ifVariableValue"));
			metaCustomField.setRemark(request.getParameter("metaCustomField.remark"));
			metaCustomField.setIsMust(request.getParameter("metaCustomField.isMust"));
			metaCustomField.setOrderByNum(request.getParameter("metaCustomField.orderByNum"));
			metaCustomFieldService.save(PropertyTools.getPropertyByKey("tenantId"), metaCustomField);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setInfo("保存失败");
		}
		return ajaxResult;
	}

	/**
	 * 元数据字段修改
	 * 
	 * @param metaCustom
	 * @param request
	 * @return
	 */
	@RequestMapping("editMetaCustomField")
	@ResponseBody
	public AjaxResult editMetaCustomField(MetaCustomField metaCustomField, HttpServletRequest request) {
		try {
			metaCustomField = new MetaCustomField();
			metaCustomField.setId(Long.parseLong(request.getParameter("metaCustomField.id")));
			metaCustomField.setFieldname(request.getParameter("metaCustomField.fieldname"));
			metaCustomField.setFieldcode(request.getParameter("metaCustomField.fieldcode"));
			metaCustomField.setFieldtype(request.getParameter("metaCustomField.fieldtype"));
			metaCustomField.setChecktype(request.getParameter("metaCustomField.checktype"));
			metaCustomField.setFieldtodo(request.getParameter("metaCustomField.fieldtodo"));
			metaCustomField.setTodotemplate(request.getParameter("metaCustomField.todotemplate"));
			metaCustomField.setMetaid(Long.parseLong(request.getParameter("metaCustomField.metaid")));
			metaCustomField.setIfEdit(request.getParameter("metaCustomField.ifEdit"));
			metaCustomField.setIfAuto(request.getParameter("metaCustomField.ifAuto"));
			metaCustomField.setIfVisible(request.getParameter("metaCustomField.ifVisible"));
			metaCustomField.setIfVariable(request.getParameter("metaCustomField.ifVariable"));
			metaCustomField.setIsOpinion(request.getParameter("metaCustomField.isOpinion"));
			metaCustomField.setIfVariableValue(request.getParameter("metaCustomField.ifVariableValue"));
			metaCustomField.setDefaultValue(request.getParameter("metaCustomField.defaultValue"));
			metaCustomField.setIsMust(request.getParameter("metaCustomField.isMust"));
			metaCustomField.setOrderByNum(request.getParameter("metaCustomField.orderByNum"));
			//metaCustomField.setCreatetime(new Date());
			metaCustomField.setRemark(request.getParameter("metaCustomField.remark"));
			metaCustomFieldService.update(PropertyTools.getPropertyByKey("tenantId"), metaCustomField);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setInfo("修改失败");
		}
		return ajaxResult;
	}

	/**
	 * 删除元数据
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMetaCustomField/{id}")
	@ResponseBody
	public AjaxResult delMetaCustomField(@PathVariable("id") long id, HttpServletRequest request) {
		try {
			metaCustomFieldService.deleteById(PropertyTools.getPropertyByKey("tenantId"), id);
			//删除流程绑定意见字段
			Map<String, Object> deleMap = new HashMap<String, Object>();
			deleMap.put("wf_meta_customs_id", id);
			deleMap.put("wf_tenantId", PropertyTools.getPropertyByKey("tenantId"));
			metaCustomFieldService.deleteQueryWorkFlowAndFieldBean(deleMap);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("删除成功");
		} catch (Exception e) {
			ajaxResult.setInfo("删除失败");
		}
		return ajaxResult;
	}

	/**
	 * 打开表单设计器
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "formDesigner/{metaid}/{busiid}")
	public ModelAndView formDesigner(@PathVariable("metaid") long metaid, @PathVariable("busiid") long busiid,
			HttpServletRequest request) {
		MetaCustom metaCustom = metaCustomService.getById(PropertyTools.getPropertyByKey("tenantId"), metaid);
		request.setAttribute("metaCustom", metaCustom);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("metaid", metaid);
		List<MetaCustomField> listField = metaCustomFieldService.getList(PropertyTools.getPropertyByKey("tenantId"),
				paramMap);
		request.setAttribute("listField", listField);
		request.setAttribute("metaid", metaid);
		MetaBusi metaBusi = metaBusiService.getById(PropertyTools.getPropertyByKey("tenantId"), busiid);
		if (metaBusi != null) {
			request.setAttribute("metaBusi", metaBusi);
			JSONObject json;
			try {
				json = (JSONObject) JSON.parse(metaBusiService.findForm(metaBusi.getBusipath()));
				Object datas = json.get("add_fields");
				Map<String, String> nameMap = new HashMap<String, String>();
				String template = (String) json.get("template");
				String parseHtml = (String) json.get("parse");
				if (!nameMap.isEmpty()) {
					for (Map.Entry<String, String> entry : nameMap.entrySet()) {
						template = template.replaceAll(entry.getKey(), entry.getValue());
						parseHtml = parseHtml.replaceAll(entry.getKey(), entry.getValue());
					}
				}
				request.setAttribute("originalHtml", template);
				request.setAttribute("parseHtml", parseHtml);
			} catch (ParseException e) {
			}
		}
		ModelAndView modelAndView = new ModelAndView("workflow/formDesigner");
		return modelAndView;
	}

	/**
	 * 表单保存操作
	 * 
	 * @param metaid
	 * @param businame
	 * @param busicode
	 * @param parseForm
	 * @param request
	 * @return
	 */
	@RequestMapping("saveMetaBusi")
	@ResponseBody
	public AjaxResult saveMetaBusi(String id, long metaid, String businame, String busicode, String remark,
			String isapp, String parseForm, HttpServletRequest request) {

		try {
			MetaBusi metaBusi = new MetaBusi();
			if (id != null && id.length() != 0) {
				metaBusi = metaBusiService.getById(PropertyTools.getPropertyByKey("tenantId"), Long.parseLong(id));
			} else {
				metaBusi.setBusiname(businame);
				metaBusi.setBusicode(busicode);
				metaBusi.setMetaid(metaid);
				metaBusi.setRemark(remark);
				metaBusi.setTenantId(Long.parseLong(PropertyTools.getPropertyByKey("tenantId")));
				metaBusi.setIsapp(isapp);
			}
			ajaxResult.setResult(metaBusiService.saveMetaBusi(metaBusi, parseForm));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setInfo("保存失败");
		}
		return ajaxResult;
	}

	/**
	 * 获取表单列表
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "metaBusiList/{id}")
	public ModelAndView metaBusiList(@PathVariable("id") long id, HttpServletRequest request) {
		request.setAttribute("metaid", id);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("metaid", id);
		ModelAndView modelAndView = new ModelAndView("meta/metaBusiList");
		MetaBusi metaBusi = metaBusiService.getByMap(PropertyTools.getPropertyByKey("tenantId"), paramMap);
		// MetaBusi metaBusi =
		// metaBusiService.getById(PropertyTools.getPropertyByKey("tenantId"),
		// id);
		if (null != metaBusi) {
			paramMap.put("metaid", metaBusi.getMetaid());
			paramMap.put("deploystate", "1");
			request.setAttribute("flowList",
					metaTenantService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap));
		}
		return modelAndView;
	}

	/**
	 * 获取表单列表数据
	 * 
	 * @param id
	 * @param request
	 * @param dataGrid
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "dataGridBusi/{id}")
	@ResponseBody
	public DataGrid dataGridBusi(@PathVariable("id") long id, HttpServletRequest request, DataGrid dataGrid, int rows) {
		dataGrid.setPageSize(rows);
		return metaBusiService.dataGridBusi(PropertyTools.getPropertyByKey("tenantId"), id, dataGrid);
	}

	/**
	 * 删除表单模板
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMetaBusi/{id}")
	@ResponseBody
	public AjaxResult delMetaBusi(@PathVariable("id") long id, HttpServletRequest request) {

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("busiid", id);
			List<BpmTodo> list = bpmTodoService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap);
			if (list != null && list.size() != 0) {
				throw new FlowBusinessRuntimeException("表单已经被使用");
			} else {
				metaBusiService.deleteById(PropertyTools.getPropertyByKey("tenantId"), id);
				ajaxResult.setStatus("ok");
				ajaxResult.setInfo("删除成功");
			}
		} catch (Exception e) {
			ajaxResult.setInfo("删除失败" + e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 表单预览
	 * 
	 * @param design_content
	 * @param fields
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "metaBusiPreview")
	public ModelAndView metaBusiPreview(String design_content, String fields, HttpServletRequest request) {
		try {
			design_content = design_content.replace("{|-", "").replace("-|}", "");
			request.setAttribute("design_content", design_content);
			request.setAttribute("fields", fields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("meta/metaBusiPreview");
		return modelAndView;
	}

	/**
	 * 保存流程绑定
	 * 
	 * @param metaTenant
	 * @param request
	 * @return
	 */
	@RequestMapping("saveMetaTenant")
	@ResponseBody
	public AjaxResult saveMetaTenant(MetaTenant metaTenant, HttpServletRequest request) {

		try {
			metaTenant = new MetaTenant();
			metaTenant.setModelname(request.getParameter("metaTenant.modelname"));
			metaTenant.setMetaid(Long.parseLong(request.getParameter("metaTenant.metaid")));
			metaTenant.setSingler(request.getParameter("metaTenant.singler"));
			metaTenant.setSingletype(request.getParameter("metaTenant.singletype"));
			metaTenant.setTenantId(Long.parseLong(PropertyTools.getPropertyByKey("tenantId")));
			metaTenant.setTenantId(Long.parseLong(request.getParameter("metaTenant.tenantId")));
			metaTenant.setRemark(request.getParameter("metaTenant.remark"));
			ajaxResult.setResult(metaTenantService.saveMetaTenant(metaTenant));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("保存成功");
		} catch (Exception e) {
			ajaxResult.setInfo("保存失败");
		}
		return ajaxResult;
	}

	/**
	 * 编辑流程绑定信息
	 * 
	 * @param metaTenant
	 * @param request
	 * @return
	 */
	@RequestMapping("editMetaTenant")
	@ResponseBody
	public AjaxResult editMetaTenant(MetaTenant metaTenant, HttpServletRequest request) {

		try {
			metaTenant = metaTenantService.getById(PropertyTools.getPropertyByKey("tenantId"),
					Long.parseLong(request.getParameter("metaTenant.id")));
			metaTenant.setModelname(request.getParameter("metaTenant.modelname"));
			metaTenant.setMetaid(Long.parseLong(request.getParameter("metaTenant.metaid")));
			metaTenant.setSingler(request.getParameter("metaTenant.singler"));
			metaTenant.setSingletype(request.getParameter("metaTenant.singletype"));
			metaTenant.setTenantId(Long.parseLong(PropertyTools.getPropertyByKey("tenantId")));
			metaTenant.setTenantId(Long.parseLong(request.getParameter("metaTenant.tenantId")));
			metaTenant.setRemark(request.getParameter("metaTenant.remark"));
			ajaxResult.setResult(metaTenantService.updateMetaTenant(metaTenant));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setInfo("保存失败");
		}
		return ajaxResult;
	}

	/**
	 * 删除流程绑定信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMetaTenant/{id}/{tenantId}")
	@ResponseBody
	public AjaxResult delMetaTenant(@PathVariable("id") long id, @PathVariable("tenantId") String tenantId,
			HttpServletRequest request) {

		try {
			metaTenantService.deleteMetaTenant(metaTenantService.getById(tenantId, id));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("删除成功");
		} catch (Exception e) {
			ajaxResult.setInfo("删除失败");
		}
		return ajaxResult;
	}

	/**
	 * 部署流程定义
	 * 
	 * @param modelId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "deployModel/{id}/{tenantId}")
	@ResponseBody
	public AjaxResult deployModel(@PathVariable("id") long id, @PathVariable("tenantId") String tenantId,
			HttpServletRequest request) {

		try {
			metaTenantService.deployModel(metaTenantService.getById(tenantId, id));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("部署成功");
		} catch (Exception e) {
			ajaxResult.setInfo("部署失败");
		}
		return ajaxResult;
	}

	/**
	 * 根据功能id打开具体的单据页面
	 * 
	 * @param functionid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "openForm/{isapp}/{functionid}")
	public ModelAndView openForm(@PathVariable("isapp") String isapp, @PathVariable("functionid") String functionid,
			HttpServletRequest request) {
		ModelAndView modelAndView = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("functionid", functionid);
		List<MetaCustom> listMetaCustom = metaCustomService.getList(PropertyTools.getPropertyByKey("tenantId"),
				paramMap);
		if (listMetaCustom != null && listMetaCustom.size() != 0) {
			modelAndView = new ModelAndView("meta/openForm");
			MetaCustom metaCustom = listMetaCustom.get(0);
			request.setAttribute("metaCustom", metaCustom);
			try {
				paramMap = new HashMap<String, Object>();
				paramMap.put("metaid", metaCustom.getId());
				paramMap.put("isapp", isapp);
				paramMap.put("sort", "version");
				paramMap.put("dir", "desc");
				MetaBusi metaBusi = metaBusiService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap)
						.get(0);
				request.setAttribute("metaBusi", metaBusi);
				JSONObject json = null;
				json = (JSONObject) JSON.parse(metaBusiService.findForm(metaBusi.getBusipath()));
				Object datas = json.get("add_fields");
				Map<String, String> nameMap = new HashMap<String, String>();
				String template = (String) json.get("template");
				String parseHtml = (String) json.get("parse");
				if (!nameMap.isEmpty()) {
					for (Map.Entry<String, String> entry : nameMap.entrySet()) {
						template = template.replaceAll(entry.getKey(), entry.getValue());
						parseHtml = parseHtml.replaceAll(entry.getKey(), entry.getValue());
					}
				}
				template = template.replace("{|-", "").replace("-|}", "");
				request.setAttribute("originalHtml", template);
				request.setAttribute("parseHtml", parseHtml);
			} catch (ParseException e) {
			}
		} else {
			modelAndView = new ModelAndView("meta/noForm");
		}
		return modelAndView;
	}

	/**
	 * 获取表单table
	 * 
	 * @param isapp
	 * @param functionid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findForm/{isapp}/{functionid}")
	@ResponseBody
	public AjaxResult findForm(@PathVariable("isapp") String isapp, @PathVariable("functionid") String functionid,
			HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("functionid", functionid);
		List<MetaCustom> listMetaCustom = metaCustomService.getList(PropertyTools.getPropertyByKey("tenantId"),
				paramMap);
		if (listMetaCustom != null && listMetaCustom.size() != 0) {
			MetaCustom metaCustom = listMetaCustom.get(0);
			request.setAttribute("metaCustom", metaCustom);
			try {
				paramMap = new HashMap<String, Object>();
				paramMap.put("metaid", metaCustom.getId());
				paramMap.put("isapp", isapp);
				paramMap.put("sort", "version");
				paramMap.put("dir", "desc");
				MetaBusi metaBusi = metaBusiService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap)
						.get(0);
				request.setAttribute("metaBusi", metaBusi);
				JSONObject json = null;
				json = (JSONObject) JSON.parse(metaBusiService.findForm(metaBusi.getBusipath()));
				Object datas = json.get("add_fields");
				Map<String, String> nameMap = new HashMap<String, String>();
				String template = (String) json.get("template");
				String parseHtml = (String) json.get("parse");
				if (!nameMap.isEmpty()) {
					for (Map.Entry<String, String> entry : nameMap.entrySet()) {
						template = template.replaceAll(entry.getKey(), entry.getValue());
						parseHtml = parseHtml.replaceAll(entry.getKey(), entry.getValue());
					}
				}
				template = template.replace("{|-", "").replace("-|}", "");
				ajaxResult.setResult(template);
				ajaxResult.setStatus("ok");
				ajaxResult.setInfo("获取成功");
			} catch (ParseException e) {
				ajaxResult.setInfo("获取失败");
			}
		} else {
			ajaxResult.setInfo("获取失败");
		}
		return ajaxResult;
	}

	/**
	 * 根据功能id打开具体的单据页面
	 * 
	 * @param functionid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "openTaskForm/{opentype}/{processInstanceId}/{taskId}")
	public ModelAndView openTaskForm(@PathVariable("opentype") String opentype,
			@PathVariable("processInstanceId") String processInstanceId, @PathVariable("taskId") String taskId,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("meta/openTaskForm");
		request.setAttribute("taskId", taskId);
		request.setAttribute("instanceid", processInstanceId);
		request.setAttribute("opentype", opentype);
		long metaid = Long.parseLong(workflowService
				.queryVariable(processInstanceId, PropertyTools.getPropertyByKey("metaCustomId")).toString());
		MetaCustom metaCustom = metaCustomService.getById(PropertyTools.getPropertyByKey("tenantId"), metaid);
		request.setAttribute("metaCustom", metaCustom);
		Map<String, Object> paramMap = null;
		MetaBusi metaBusi = null;
		try {
			try {
				ProcessInstance processInstance = workflowService.queryProcessInstance(processInstanceId);
				paramMap = new HashMap<String, Object>();
				paramMap.put("instanceid", processInstance.getId());
				metaBusi = metaBusiService.getById(PropertyTools.getPropertyByKey("tenantId"), Long.parseLong(
						bpmTodoService.getByMap(PropertyTools.getPropertyByKey("tenantId"), paramMap).getBusiid()));
			} catch (Exception e) {
			}
			if (metaBusi == null) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("metaid", metaCustom.getId());
				paramMap.put("isapp", IsAppEnum.PC.getValue());
				paramMap.put("enable", EnableEnum.ENABLE.getValue());
				paramMap.put("sort", "version");
				paramMap.put("dir", "desc");
				metaBusi = metaBusiService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap).get(0);
			}
			request.setAttribute("metaBusi", metaBusi);
			JSONObject json = null;
			json = (JSONObject) JSON.parse(metaBusiService.findForm(metaBusi.getBusipath()));
			Object datas = json.get("add_fields");
			Map<String, String> nameMap = new HashMap<String, String>();
			String template = (String) json.get("template");
			String parseHtml = (String) json.get("parse");
			if (!nameMap.isEmpty()) {
				for (Map.Entry<String, String> entry : nameMap.entrySet()) {
					template = template.replaceAll(entry.getKey(), entry.getValue());
					parseHtml = parseHtml.replaceAll(entry.getKey(), entry.getValue());
				}
			}
			template = template.replace("{|-", "").replace("-|}", "");
			request.setAttribute("originalHtml", template.replace("easyui-validatebox", ""));
			request.setAttribute("parseHtml", parseHtml);
			if (taskId != null) {
				request.setAttribute("checkStartUser", workflowService.checkStartUser(opentype, processInstanceId,
						taskId, request.getParameter("userId")));
			}
		} catch (ParseException e) {
		}
		return modelAndView;
	}

	/**
	 * 获取表单table信息
	 * 
	 * @param opentype
	 * @param processInstanceId
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findTaskForm/{isapp}/{taskId}")
	@ResponseBody
	public AjaxResult findTaskForm(@PathVariable("isapp") String isapp, @PathVariable("taskId") String taskId,
			HttpServletRequest request) {

		String processInstanceId = workflowService.queryHistoricTaskInstance(taskId).getProcessInstanceId();
		long metaid = Long.parseLong(workflowService
				.queryVariable(processInstanceId, PropertyTools.getPropertyByKey("metaCustomId")).toString());
		MetaCustom metaCustom = metaCustomService.getById(PropertyTools.getPropertyByKey("tenantId"), metaid);
		request.setAttribute("metaCustom", metaCustom);
		Map<String, Object> paramMap = null;
		MetaBusi metaBusi = null;
		try {
			try {
				ProcessInstance processInstance = workflowService.queryProcessInstance(processInstanceId);
				paramMap = new HashMap<String, Object>();
				paramMap.put("instanceid", processInstance.getId());
				metaBusi = metaBusiService.getById(PropertyTools.getPropertyByKey("tenantId"), Long.parseLong(
						bpmTodoService.getByMap(PropertyTools.getPropertyByKey("tenantId"), paramMap).getBusiid()));
			} catch (Exception e) {
			}
			if (metaBusi == null) {
				paramMap = new HashMap<String, Object>();
				paramMap.put("metaid", metaCustom.getId());
				paramMap.put("isapp", isapp);
				paramMap.put("enable", EnableEnum.ENABLE.getValue());
				paramMap.put("sort", "version");
				paramMap.put("dir", "desc");
				metaBusi = metaBusiService.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap).get(0);
			}
			JSONObject json = null;
			json = (JSONObject) JSON.parse(metaBusiService.findForm(metaBusi.getBusipath()));
			Object datas = json.get("add_fields");
			Map<String, String> nameMap = new HashMap<String, String>();
			String template = (String) json.get("template");
			String parseHtml = (String) json.get("parse");
			if (!nameMap.isEmpty()) {
				for (Map.Entry<String, String> entry : nameMap.entrySet()) {
					template = template.replaceAll(entry.getKey(), entry.getValue());
					parseHtml = parseHtml.replaceAll(entry.getKey(), entry.getValue());
				}
			}
			template = template.replace("{|-", "").replace("-|}", "");
			ajaxResult.setResult(template.replace("easyui-validatebox", ""));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo(
					workflowService.checkStartUser("todo", processInstanceId, taskId, request.getParameter("userId"))
							+ "");
		} catch (ParseException e) {
			ajaxResult.setStatus("error");
			ajaxResult.setInfo(e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 根据业务id获取表单信息
	 * 
	 * @param metaid
	 * @param busiKey
	 * @param request
	 * @return
	 */
	@RequestMapping("findBusiByBusiKey/{metaid}/{busiKey}")
	@ResponseBody
	public AjaxResult findBusiByBusiKey(@PathVariable("metaid") Long metaid, @PathVariable("busiKey") String busiKey,
			HttpServletRequest request) {

		try {
			ajaxResult.setResult(metaCustomService.findBusiByBusiKey(PropertyTools.getPropertyByKey("tenantId"), metaid,
					busiKey, request.getServletContext()));
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("获取数据成功");
		} catch (Exception e) {
			ajaxResult.setInfo("获取数据失败");
		}
		return ajaxResult;
	}

	/**
	 * 根据taskid获取表单信息
	 * 
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping("findBusi/{processInstanceId}")
	@ResponseBody
	public AjaxResult findBusi(@PathVariable("processInstanceId") String processInstanceId,
			HttpServletRequest request) {

		try {
			long metaid = Long.parseLong(workflowService
					.queryVariable(processInstanceId, PropertyTools.getPropertyByKey("metaCustomId")).toString());
			MetaCustom metaCustom = metaCustomService.getById(PropertyTools.getPropertyByKey("tenantId"), metaid);
			String busiKey = workflowService.queryProcessInstance(processInstanceId).getBusinessKey();
			Map<String, Object> map = null;
			PropertyDescriptor pd = null;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("metaid", metaid);
			List<MetaCustomField> listField = metaCustomFieldService.getList(PropertyTools.getPropertyByKey("tenantId"),
					paramMap);
			// 调用远程服务查询数据
			if (metaCustom.getQuerymethod() != null && metaCustom.getQuerymethod().indexOf("/") > -1) {
				HttpClient htpc = new HttpClient();
				GetMethod getMethod = new GetMethod(metaCustom.getQuerymethod() + "/" + busiKey + "/"
						+ PropertyTools.getPropertyByKey("tenantId").toString());
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
				}
			} else {
				Object obj = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext())
						.getBean(metaCustom.getMetaService());
				Method wM = null;
				if (metaCustom.getQuerymethod() != null && metaCustom.getQuerymethod().length() != 0) {
					wM = obj.getClass().getDeclaredMethod(metaCustom.getQuerymethod(), String.class, Long.class);
				} else {
					wM = obj.getClass().getDeclaredMethod(PropertyTools.getPropertyByKey("getById"), String.class,
							Long.class);
				}
				Object busi = wM.invoke(obj, PropertyTools.getPropertyByKey("tenantId"), Long.parseLong(busiKey));
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
			ajaxResult.setResult(listMap);
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("获取数据成功");
		} catch (Exception e) {
			ajaxResult.setInfo("获取数据失败");
		}
		return ajaxResult;
	}

	/**
	 * 制单人重新提交单据
	 * 
	 * @param taskId
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("commitStartTask/{taskId}")
	@ResponseBody
	public AjaxResult commitStartTask(@PathVariable("taskId") String taskId, String id, HttpServletRequest request) {
		try {
			if (workflowService.checkStartUser("todo", null, taskId, request.getParameter("userId")) && id != null) {
				BpmTodo bpmTodo = bpmTodoService
						.queryBpmTodoByTaskId(PropertyTools.getPropertyByKey("tenantId").toString(), taskId);
				Long metaid = Long.parseLong(bpmTodo.getMetaid());
				MetaCustom metaCustom = metaCustomService.getById(PropertyTools.getPropertyByKey("tenantId"), metaid);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("metaid", metaid);
				List<MetaCustomField> listField = metaCustomFieldService
						.getList(PropertyTools.getPropertyByKey("tenantId"), paramMap);
				if (metaCustom.getMetaService().indexOf("/") > -1) {
					PostMethod pm = new PostMethod(metaCustom.getMetaService());
					try {
						JSONObject json = new JSONObject();
						for (MetaCustomField field : listField) {
							json.put(field.getFieldcode(), request.getParameter(field.getFieldcode()));
						}
						json.put(PropertyTools.getPropertyByKey("id"), id);
						json.put("tenantId", PropertyTools.getPropertyByKey("tenantId"));
						pm.setRequestHeader("accept", "application/json");
						pm.setRequestHeader("Encoding", "UTF-8");
						pm.setParameter(PropertyTools.getPropertyByKey("postBody"), JSON.json(json));
						HttpClient hc = new HttpClient();
						hc.getParams().setContentCharset("UTF-8");
						int code = hc.executeMethod(pm);
						if (code == 200) {
							id = pm.getResponseBodyAsString();
							bpmApproveService.completeTask(taskId, "", "重新提交",
									PropertyTools.getPropertyByKey("tenantId"));
						} else {
							throw new FlowBusinessRuntimeException("请求失败");
						}
					} finally {
						pm.releaseConnection();
					}
				} else {
					Class c = Class.forName(metaCustom.getClasspath());
					Object obj = WebApplicationContextUtils
							.getRequiredWebApplicationContext(request.getServletContext())
							.getBean(metaCustom.getMetaService());
					Object busi = c.newInstance();
					PropertyDescriptor pd = null;
					Method wM = null;
					for (MetaCustomField field : listField) {
						pd = new PropertyDescriptor(field.getFieldcode(), c);
						wM = pd.getWriteMethod();// 获得写方法
						wM.invoke(busi, request.getParameter(field.getFieldcode()));
					}
					try {
						pd = new PropertyDescriptor(PropertyTools.getPropertyByKey("id"), c);
						wM = pd.getWriteMethod();// 获得写方法
						wM.invoke(busi, id);
					} catch (Exception e) {
					}
					wM = obj.getClass().getDeclaredMethod(PropertyTools.getPropertyByKey("saveMethod"), String.class,
							AbstractBaseEntity.class);
					wM.invoke(obj, PropertyTools.getPropertyByKey("tenantId"), busi);
					bpmApproveService.completeTask(taskId, "", "重新提交", PropertyTools.getPropertyByKey("tenantId"));
				}
			} else {
				throw new FlowBusinessRuntimeException("不能提交");
			}
			ajaxResult.setStatus("ok");
			ajaxResult.setInfo("提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setInfo("提交失败" + e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 
	 * doBingWorkFlowNodesByModelId(流程绑定表单方法 目前一个流程只能绑定一个表单) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MetaCustomController
	 *            <br/>
	 * @return AjaxResult <br/>
	 * @method @param jsonParam
	 * @method @param request
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	@RequestMapping("doBingWorkFlowNodesByModelId")
	@ResponseBody
	public AjaxResult doBingWorkFlowNodesByModelId(@RequestBody String jsonParam, HttpServletRequest request) {
		paraMap = MyJsonUtil.convertJsonToMap(jsonParam);
		String wf_tenantId = PropertyTools.getPropertyByKey("tenantId");
		if (null == paraMap || paraMap.isEmpty()) {
			ajaxResult.setStatus("no");
			ajaxResult.setInfo("参数错误");
		} else {
			paraMap.put("wf_tenantId", wf_tenantId);
			// 删除绑定变量
			if (paraMap.get("do").equals("delete")) {
				metaCustomFieldService.deleteQueryWorkFlowAndFieldBean(paraMap);
				ajaxResult.setStatus("ok");
				ajaxResult.setInfo("删除成功");
			} else {
				// 得到流程节点信息
				// 查询是否已经绑定过意见 如果有更新本次操作 没有新增
				QueryWorkFlowAndFieldBean fieldBean = metaCustomFieldService.getWorkFlowNodesList(paraMap);
				if (null != fieldBean) {
					// 更新
					metaCustomFieldService.updateQueryWorkFlowAndFieldBean(paraMap);
				} else {
					// 插入
					metaCustomFieldService.saveWorkFlowNodesByModelId(paraMap);
				}
				ajaxResult.setStatus("ok");
				ajaxResult.setInfo("操作成功");
			}
		}
		return ajaxResult;
	}
	// 保存 流程节点 和 意见字段的 绑定 插入 wf_meta_flow_field 表中
	// 2015年11月24日 18:48:23
}
