package org.zywx.appdo.meta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.enums.SingleTypeEnum;
import org.zywx.appdo.utils.PropertyTools;

/**
 * 流程模型控制器
 *
 * @author qf
 */
@Controller
@RequestMapping(value = "/settingModel")
public class ModelController{

    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 模型列表
     */
    @RequestMapping(value = "modelList")
    public ModelAndView modelList(HttpServletRequest request) {
    	SingleTypeEnum[] types=SingleTypeEnum.values();
    	List<Map<String,String>> listType=new ArrayList<Map<String,String>>();
    	for(SingleTypeEnum type:types){
    		listType.add(type.getMap());
    	}
    	request.setAttribute("listType", listType);
    	request.setAttribute("tenantId", PropertyTools.getPropertyByKey("tenantId"));
        ModelAndView mav = new ModelAndView("model/modelList");
        return mav;
    }
    /**
     * 获取流程模型列表
     * @param request
     * @param dataGrid
     * @param rows
     * @return
     */
    @RequestMapping("dataGridModel")
    @ResponseBody
    public DataGrid dataGridModel(@RequestParam(value="metaId", defaultValue="") String metaId,@RequestParam(value="tenantId", defaultValue="") String tenantId,HttpServletRequest request,DataGrid dataGrid,int rows){
		dataGrid.setPageSize(rows);
		return dataGrid;
    }
 
}
