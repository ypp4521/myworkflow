package org.zywx.appdo.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.ExtensionElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.UserTaskParseHandler;
import org.zywx.appdo.utils.PropertyTools;

public class ExtensionUserTaskParseHandler extends UserTaskParseHandler {

	@Override
	protected void executeParse(BpmnParse bpmnParse, UserTask userTask) {
		// 调用上层的解析
		super.executeParse(bpmnParse, userTask);
		//
		// ActivityImpl activity =
		// bpmnParse.getCurrentScope().findActivity(userTask.getId());
		// Map<String, ExtensionOperation> operationMap =
		// parseUserTaskOperations(bpmnParse, userTask);
		//
		// //将扩展属性设置给activity
		// activity.setProperty(CommonUtil.PROPERTY_OPERATIONS_DEFINITION,
		// operationMap);
	}

	public Map<String, ExtensionOperation> parseUserTaskOperations(BpmnParse bpmnParse, UserTask userTask) {
		Map<String, ExtensionOperation> operationMap = new HashMap<String, ExtensionOperation>();
		// 获取扩展属性标签元素
		ExtensionElement operationsElement = userTask.getExtensionElements()
				.get(PropertyTools.getPropertyByKey("elementOperation")).get(0);

		if (operationsElement != null) {
			for (List<ExtensionElement> operationElement : operationsElement.getChildElements().values()) {
				// ExtensionOperation userTaskOperation = new
				// ExtensionOperation(operationElement.get(0).getName());
				//
				// if (operationElement != null &&
				// !operationElement[0].getAttributes().isEmpty()) {
				// for (ExtensionAttribute attributeElement :
				// operationElement.getAttributes().values()) {
				// userTaskOperation.addProperty(attributeElement.getName(),
				// attributeElement.getValue());
				// }
				// }
				// operationMap.put(operationElement.getName(),
				// userTaskOperation);
			}
		}

		return operationMap;
	}
}