package org.zywx.appdo.flow.util;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
/**
 * 
 * 项目名称：appdo-web-flow <br/>     
 * 类名称：JumpActivityCmd  <br/>    
 * 类描述：    自由跳转实现类<br/>  
 * 创建人：pengpeng.yuan@zymobi.com <br/>     
 * 创建时间：2015年11月17日 上午10:28:46  <br/>    
 * 修改人：Yuanpp    <br/>  
 * 文件名：JumpActivityCmd.java  <br/>  
 * 版本信息：1.0  <br/>  
 * 正益移动互联科技股份有限公司 Copyright Corporation(2015)   <br/>     
 * @version 1.0<br/>
 */
public class JumpActivityCmd implements Command<Object> {
    private String activityId;//跳转目标activityID
    private String processInstanceId;//流程实例id
    private String jumpOrigin;

    public JumpActivityCmd(String processInstanceId, String activityId) {
        this(processInstanceId, activityId, "jump");
    }

    public JumpActivityCmd(String processInstanceId, String activityId, String jumpOrigin) {
        this.activityId = activityId;
        this.processInstanceId = processInstanceId;
        this.jumpOrigin = jumpOrigin;
    }

    public Object execute(CommandContext commandContext) {

        ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findExecutionById(processInstanceId);

        executionEntity.destroyScope(jumpOrigin);

        ProcessDefinitionImpl processDefinition = executionEntity.getProcessDefinition();
        ActivityImpl activity = processDefinition.findActivity(activityId);

        executionEntity.executeActivity(activity);

        return executionEntity;
    }
}
