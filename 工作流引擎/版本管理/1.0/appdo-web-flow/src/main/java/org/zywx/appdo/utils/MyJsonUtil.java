package org.zywx.appdo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zywx.appdo.bean.QueryMongBean;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.meta.entity.MetaTemplate;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;

import net.sf.json.JSONArray;

/**
 * 
 * 项目名称：appdo-web-flow <br/>
 * 类名称：MyJsonUtil <br/>
 * 类描述： json格式处理 <br/>
 * 创建人：pengpeng.yuan@zymobi.com <br/>
 * 创建时间：2015年11月5日 下午5:17:29 <br/>
 * 修改人：Yuanpp <br/>
 * 文件名：MyJsonUtil.java <br/>
 * 版本信息：1.0 <br/>
 * Copyright Corporation 2015 <br/>
 * 正益移动互联科技股份有限公司<br/>
 * 
 * @version 1.0<br/>
 */
public class MyJsonUtil {

	static String jsonArrayData;
	static JSONArray jsonArray = null;
	static JSONObject result;
	static JSONObject message;

	/**
	 * 将jsonArray转换为json字符串
	 * 
	 * @param list
	 * @param status
	 * @param page
	 * @return
	 */
	public static <T> String loadJsonArrayDataByList(List<T> list, String status, String exception) {
		result=new JSONObject();
		try {
			if ("000".equals(status)) {
				message=new JSONObject();
				if (null != list && !list.isEmpty()) {
					jsonArray = JSONArray.fromObject(list);
					message.put("data", jsonArray);
					message.put("total", list.size());
				} else {
					message.put("total", "0");
				}
				result.put("msg", message);
			} else {
				result.put("msg", exception);
			}
			result.put("status", status);// 000 成功 001 失败
			jsonArrayData = JSON.json(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArrayData;
	}

	/**
	 * 
	 * loadJsonArrayPagrDataByList(对外调用json返回分页数据) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MyJsonUtil
	 *            <br/>
	 * @return String <br/>
	 * @method @param list 集合
	 * @method @param dataCount 数据总行数
	 * @method @param pageNo 当前页
	 * @method @param rowCnt 当前行数
	 * @method @param pageCount 总页数
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	public static <T> String loadJsonArrayPagrDataByList(DataGrid dataGrid, String status, String exception) {
		result=new JSONObject();
		try {
			List list = dataGrid.getRows();
			if ("000".equals(status)) {
				message=new JSONObject();
				if (null != list && !list.isEmpty()) {
					jsonArray = JSONArray.fromObject(list);
					for (Object object : list) {
						if (object instanceof Map) {
							message.put("data", com.alibaba.fastjson.JSONObject.toJSON(list));
						} else {
							message.put("data", jsonArray);
						}
						break;
					}
					message.put("rowCnt", dataGrid.getPageSize());
					message.put("pageNo", dataGrid.getPage());
					message.put("total", dataGrid.getTotal());
					message.put("pageCount", dataGrid.getPageCount());
				} else {
					message.put("total", "0");
				}
				result.put("msg", message);
			}else{
				result.put("msg", exception);
			}
			result.put("status", status);// 000 成功 001 失败
			jsonArrayData = JSON.json(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArrayData;
	}

	/**
	 * 
	 * loadJsonObjectConvert(转换模版对象源代码json) <br/>
	 * 
	 * @param MyJsonUtil
	 *            <br/>
	 * @return String <br/>
	 * @method <br/>
	 * @Exception 异常对象 <br/>
	 */
	public static <T> String loadJsonObjectConvert(Object object, String status, String exception) {
		result=new JSONObject();
		try {
			if ("000".equals(status)) {
				message=new JSONObject();
				if (null != object) {
					Map<String, String> map = new HashMap<String, String>();
					if (object instanceof MetaTemplate) {
						MetaTemplate template = (MetaTemplate) object;
						map.put("id", template.getId().toString());
						map.put("fileBlob", template.getFileValue());
					}
					jsonArray = JSONArray.fromObject(map);
					message.put("data", jsonArray);
					message.put("total", "1");
					
				} else {
					message.put("total", "0");
				}
				result.put("msg", message);
			}else{
				result.put("msg", exception);
			}
			result.put("status", status);// 000 成功 001 失败
			jsonArrayData = JSON.json(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArrayData;
	}

	/**
	 * 
	 * loadJsonArrayFieldsDataByList(加载模版对应字段信息返回json格式) <br/>
	 * 
	 * @param MyJsonUtil
	 *            <br/>
	 * @return String <br/>
	 * @method <br/>
	 * @Exception 异常对象 <br/>
	 */
	public static <T> String loadJsonArrayFieldsDataByList(List<T> list, String status,String exception) {
		result=new JSONObject();
		try {
			if ("000".equals(status)) {
				message=new JSONObject();
				if (null != list && !list.isEmpty()) {
					jsonArray = JSONArray.fromObject(list);
					message.put("data", jsonArray);
					message.put("total", list.size());
					message.put("status", "000");
				} else {
					message.put("total", "0");
				}
				result.put("msg", message);
			}else{
				result.put("msg", exception);
			}
			result.put("status", status);// 000 成功 001 失败
			jsonArrayData = JSON.json(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArrayData;
	}

	/**
	 * 将JSONArray对象转换成Map-List集合
	 * 
	 * @param jsonArr
	 * @return
	 */
	public static Object JsonToList(JSONArray jsonArr) {
		List<Object> jsonObjList = new ArrayList<Object>();
		for (Object obj : jsonArr) {
			if (obj instanceof net.sf.json.JSONArray) {
				jsonObjList.add(JsonToList((JSONArray) obj));
			} else if (obj instanceof net.sf.json.JSONObject) {
				jsonObjList.add(JsonToMap((net.sf.json.JSONObject) obj));
			} else {
				jsonObjList.add(obj);
			}
		}
		return jsonObjList;
	}

	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> JsonToMap(net.sf.json.JSONObject json) {
		Map<String, Object> columnValMap = new HashMap<String, Object>();
		Set<Object> jsonKeys = json.keySet();
		for (Object key : jsonKeys) {
			Object JsonValObj = json.get(key);
			if (JsonValObj instanceof JSONArray) {
				columnValMap.put((String) key, JsonToList((JSONArray) JsonValObj));
			} else if (key instanceof JSONObject) {
				columnValMap.put((String) key, JsonToMap((net.sf.json.JSONObject) JsonValObj));
			} else {
				columnValMap.put((String) key, JsonValObj);
			}
		}
		return columnValMap;
	}

	/**
	 * 将json对象转换为map集合，通过此方法获取存放map集合键的list集合
	 * 
	 * @param obj
	 * @return
	 */
	public static List<Object> mapKeys(Object obj) {
		List<Object> keysList = new ArrayList<Object>();
		Map<String, Object> columnValMap = new HashMap<String, Object>();
		String columnStr = "column";
		if (obj instanceof JSONArray) {
			List<Map<String, Object>> jsonObjList = new ArrayList<Map<String, Object>>();
			jsonObjList = (List<Map<String, Object>>) JsonToList((JSONArray) obj);
			columnValMap = (Map<String, Object>) (jsonObjList.get(0));
		} else if (obj instanceof JSONObject) {
			columnValMap = JsonToMap((net.sf.json.JSONObject) obj);
		} else {
			keysList.add(obj);
		}
		for (int i = 0; i < columnValMap.keySet().size(); i++) {
			keysList.add(columnStr + (i + 1));
		}
		return keysList;
	}

	public static void main(String[] args) {
		// String _json =
		// "{\"tenantId\":\"611\",\"billKey\":\"38\",\"billData\":{\"input_date\":\"2015-11-07\",\"dept\":\"\",\"user_info\":\"陈雪\",\"oper_type\":\"\",\"dept_opinion\":\"\",\"hr_opinion\":\"\",\"dgm_opinion\":\"\",\"gm_opinion\":\"\",\"nd\":\"2015\",\"proc_inst_id\":\"22\"},\"userId\":\"131\"}";
		// // JSONArray _jsonArray = JSONArray.fromObject(_json);
		// Map<String, Object> columnValMap =
		// JsonToMap(net.sf.json.JSONObject.fromObject(_json));
		// System.out.println(columnValMap);
		// Map<String, Object> billData = (Map<String, Object>)
		// columnValMap.get("billData");
		// System.out.println(billData);
		// Map<String,Object> billDataMap =
		// JsonToMap(net.sf.json.JSONObject.fromObject(billData));
		// System.out.println(billDataMap);
		// for (String key : billData.keySet()) {
		// System.out.println("key = " + key + "; value = " +
		// billData.get(key));
		// }
		// String json = "{\"entityTypeId\": \"24\",\"entity\": {\"input_date\":
		// \"2015-11-09\",\"dept\": \"企业级移动应用研发部\",\"user_info\":
		// \"陈雪\",\"oper_type\": \"\", \"reason_info\":
		// \"333333333333\",\"dept_opinion\": \"\",\"hr_opinion\":
		// \"\",\"dgm_opinion\": \"\",\"gm_opinion\": \"\",\"billKey\":
		// \"38\",\"metaid\": \"20\"},\"operateUserId\": \"131\",\"userId\":
		// \"131\",\"entityId\": \"E150eb71cb191618\",\"operateTypeId\":
		// \"01\",\"objectId\":
		// \"56405f87b610b6ea0a1ac0ef\",\"createdAt\":\"2015-11-09T08:55:35.553Z\",\"tenantId\":
		// \"611\"}";
		// convertJsonToQueryMongBean(json);
		// System.out.println(com.alibaba.fastjson.JSON.parse(""));

	}

	/**
	 * convertJsonObjectMap(json字符串转为map集合) <br/>
	 * 
	 * @param MyJsonUtil<br/>
	 * @return Map<String,Object> <br/>
	 * @method @param jsonString
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	public static Map<String, Object> convertJsonObjectMap(String jsonString, String jsonKey) {
		Map<String, Object> columnValMap = JsonToMap(net.sf.json.JSONObject.fromObject(jsonString));
		System.out.println(columnValMap);
		Map<String, Object> billData = (Map<String, Object>) columnValMap.get(jsonKey);
		// 如
		// jsonKey = entity
		// {
		// "entityTypeId": "24",
		// "entity": {
		// "input_date": "2015-11-09"
		// }
		//
		return billData;
	}

	/**
	 * 
	 * convertJsonToQueryMongBean(解析mongdb数据格式固定方法) <br/>
	 * 
	 * @user pengpeng.yuan@zymobi.com
	 * @param MyJsonUtil
	 *            <br/>
	 * @return QueryMongBean <br/>
	 * @method @param json
	 * @method @return <br/>
	 * @Exception 异常对象 <br/>
	 */
	public static QueryMongBean convertJsonToQueryMongBean(String json) {
		QueryMongBean bean = new QueryMongBean();
		// 进行格式化
		try {
			Map<String, Object> columnValMap = JsonToMap(net.sf.json.JSONObject.fromObject(json));
			Map<String, Object> billData = convertJsonObjectMap(json, "entity");
			// System.out.println(JSON.parse(columnValMap.get("entity").toString()));

			// 处理mongdb时间格式
			if (columnValMap.get("createdAt") != null) {
				String createdAt = columnValMap.get("createdAt").toString();
				createdAt = createdAt.replaceAll("T", " ").split("\\.")[0];
				bean.setCreatedAt(createdAt);
			}
			// 设置 bean属性
			bean.setEntity(JSON.parse(columnValMap.get("entity").toString()));
			bean.setEntityId(columnValMap.get("entityId").toString());
			bean.setEntityTypeId(columnValMap.get("entityTypeId").toString());
			bean.setObjectId(columnValMap.get("objectId").toString());
			bean.setOperateTypeId(columnValMap.get("operateTypeId").toString());
			bean.setOperateUserId(columnValMap.get("operateUserId").toString());
			bean.setTenantId(columnValMap.get("tenantId").toString());
			bean.setUserId(columnValMap.get("userId").toString());
			bean.setUserType(columnValMap.get("userType").toString());
			bean.setYear(MyStringUtils.getNowYear());
			
		} catch (com.alibaba.dubbo.common.json.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bean;
	}

	/**
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> convertJsonToMap(String json) {
		// 进行格式化
		Map<String, Object> columnValMap = JsonToMap(net.sf.json.JSONObject.fromObject(json));
		return columnValMap;

	}

}
