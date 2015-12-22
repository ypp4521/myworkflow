package org.zywx.appdo.meta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zywx.appdo.common.DataGrid;
import org.zywx.appdo.common.core.biz.BaseBizImpl;
import org.zywx.appdo.common.core.dao.BaseDao;
import org.zywx.appdo.common.enums.DeployStateEnum;
import org.zywx.appdo.common.exception.FlowBusinessRuntimeException;
import org.zywx.appdo.common.page.PageBean;
import org.zywx.appdo.common.page.PageParam;
import org.zywx.appdo.meta.dao.MetaTenantDao;
import org.zywx.appdo.meta.entity.MetaTenant;
import org.zywx.appdo.meta.service.MetaTenantService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MetaTenantServiceImpl extends BaseBizImpl<MetaTenant> implements MetaTenantService {

	@Autowired
	private MetaTenantDao metaTenantDao;
	@Autowired
    RepositoryService repositoryService;
	
	@Override
	protected BaseDao<MetaTenant> getDao() {
		return metaTenantDao;
	}

	@Override
	public String saveMetaTenant(MetaTenant metaTenant) {
		try{
			UUID uuid = UUID.randomUUID();
			ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode editorNode = objectMapper.createObjectNode();
	        editorNode.put("id", "canvas");
	        editorNode.put("resourceId", "canvas");
	        ObjectNode stencilSetNode = objectMapper.createObjectNode();
	        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	        editorNode.put("stencilset", stencilSetNode);
	        Model modelData = repositoryService.newModel();

	        ObjectNode modelObjectNode = objectMapper.createObjectNode();
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, metaTenant.getModelname());
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(metaTenant.getRemark()));
	        modelData.setMetaInfo(modelObjectNode.toString());
	        modelData.setName(metaTenant.getModelname());
	        modelData.setKey(StringUtils.defaultString("D"+uuid.toString()));
	        modelData.setTenantId(metaTenant.getTenantId()+"");

	        repositoryService.saveModel(modelData);
	        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
	        metaTenant.setModelid(modelData.getId());
	        metaTenant.setDeploystate(DeployStateEnum.UNDEPLOY.getState());//0为未部署1为已经部署
			metaTenant.setCreatetime(new Date());
			metaTenant.setModelkey("D"+uuid.toString());
			metaTenantDao.save(metaTenant);
			return modelData.getId();
		}catch(Exception e){
			throw new FlowBusinessRuntimeException(e);
		}
	}

	@Override
	public String updateMetaTenant(MetaTenant metaTenant) {
		try{
			//部署
			if("0".equals(metaTenant.getDeploystate())){
//				repositoryService.deleteModel(metaTenant.getModelid());
//				ObjectMapper objectMapper = new ObjectMapper();
//		        ObjectNode editorNode = objectMapper.createObjectNode();
//		        editorNode.put("id", "canvas");
//		        editorNode.put("resourceId", "canvas");
//		        ObjectNode stencilSetNode = objectMapper.createObjectNode();
//		        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
//		        editorNode.put("stencilset", stencilSetNode);
//		        Model modelData = repositoryService.newModel();
//
//		        ObjectNode modelObjectNode = objectMapper.createObjectNode();
//		        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, metaTenant.getModelname());
//		        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
//		        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(metaTenant.getRemark()));
//		        modelData.setMetaInfo(modelObjectNode.toString());
//		        modelData.setName(metaTenant.getModelname());
//		        modelData.setKey(metaTenant.getModelkey());
//		        modelData.setTenantId(metaTenant.getTenantId()+"");
//
//		        repositoryService.saveModel(modelData);
//		        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
//				Model modelData = repositoryService.getModel(metaTenant.getModelid());
//		        metaTenant.setModelid(modelData.getId());
		        metaTenant.setDeploystate(DeployStateEnum.DEPLOY.getState());//0为未部署1为已经部署
				metaTenant.setCreatetime(new Date());
//				metaTenant.setModelkey(metaTenant.getModelkey());
				metaTenantDao.update(metaTenant);
				return metaTenant.getId().toString();
			}else{
				Model modelData = repositoryService.getModel(metaTenant.getModelid());
				metaTenantDao.update(metaTenant);
				return modelData.getId();
//				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(metaTenant.getTenantId().toString()).processDefinitionKey(metaTenant.getModelkey()).latestVersion().singleResult();
//		        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),processDefinition.getResourceName());
//		        XMLInputFactory xif = XMLInputFactory.newInstance();
//		        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
//		        XMLStreamReader xtr = xif.createXMLStreamReader(in);
//		        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
//
//		        BpmnJsonConverter converter = new BpmnJsonConverter();
//		        ObjectNode modelNode = converter.convertToJson(bpmnModel);
//		        
//				ObjectMapper objectMapper = new ObjectMapper();
//		        Model modelData = repositoryService.newModel();
//
//		        ObjectNode modelObjectNode = objectMapper.createObjectNode();
//		        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, metaTenant.getModelname());
//		        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
//		        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(metaTenant.getRemark()));
//		        modelData.setMetaInfo(modelObjectNode.toString());
//		        modelData.setName(metaTenant.getModelname());
//		        modelData.setKey(metaTenant.getModelkey());
//		        modelData.setTenantId(metaTenant.getTenantId()+"");
//
//		        //repositoryService.saveModel(modelData);
//		        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
//		        metaTenant.setId(null);
//		        metaTenant.setModelid(modelData.getId());
//		        metaTenant.setDeploystate(DeployStateEnum.UNDEPLOY.getState());//0为未部署1为已经部署
//				metaTenant.setCreatetime(new Date());
//				metaTenant.setModelkey(metaTenant.getModelkey());
//				metaTenantDao.save(metaTenant);
//				return modelData.getId();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteMetaTenant(MetaTenant metaTenant) {
		try{
			repositoryService.deleteModel(metaTenant.getModelid());
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("id", metaTenant.getId());
			paramMap.put("tenantId", metaTenant.getTenantId());
			return metaTenantDao.deleteById(paramMap);
		}catch(Exception e){
			throw new FlowBusinessRuntimeException(e);
		}
	}

	@Override
	public String deployModel(MetaTenant metaTenant) {
		try{
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("modelkey", metaTenant.getModelkey());
			paramMap.put("tenantId", metaTenant.getTenantId());
			paramMap.put("deploystate", DeployStateEnum.DEPLOY.getState());
			metaTenantDao.deleteByConditions(paramMap);
			metaTenant.setDeploystate(DeployStateEnum.DEPLOY.getState());
			metaTenantDao.update(metaTenant);
			Model modelData = repositoryService.getModel(metaTenant.getModelid());
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().tenantId(metaTenant.getTenantId().toString()).name(modelData.getName()).addString(processName, new String(bpmnBytes,"UTF-8")).deploy();
            return deployment.getId();
		}catch(Exception e){
			throw new FlowBusinessRuntimeException(e);
		}
	}

	

	@Override
	public DataGrid dataGridMetaTenant(String tenantId,String metaId, DataGrid dataGrid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sort", "createtime");
		paramMap.put("dir", "desc");
		if(metaId!=null&&metaId.length()!=0){
			paramMap.put("metaid", metaId);
		}
		PageParam pageParam = new PageParam(dataGrid.getPage(), dataGrid.getPageSize());
		PageBean<MetaTenant> metaList = super.getPage(tenantId, pageParam, paramMap);
		dataGrid.setRows(metaList.getList());
		dataGrid.setTotal(metaList.getTotalCount());
		return dataGrid;
	}
	
	/**
	 * checkFlowIsDeployModel(查看流程是否已经部署)    <br/> 
	 * @user pengpeng.yuan@zymobi.com 
	 * @param   MetaTenantService  <br/>  
	 * @return  String  <br/>
	 * @method  @param paramMap
	 * @method  @return  <br/>  
	 * @Exception 异常对象    <br/>
	 */
	public List<MetaTenant> getDeployModelListByMap(Map<String, Object> paramMap){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tenantId", paramMap.get("tenantId"));
		map.put("deploystate", "1");
		List<MetaTenant> list=metaTenantDao.getList(map);
		if (null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}
}
