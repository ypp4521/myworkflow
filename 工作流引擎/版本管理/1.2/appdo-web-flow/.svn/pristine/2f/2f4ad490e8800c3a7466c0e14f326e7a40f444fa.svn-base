<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待办理</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;"
		fit="true">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>任务管理->待办->办理</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding:5px;">
	<table id="todoTable" class="easyui-datagrid" 
	    url="${ctx}/workflow/dataGridTodo" //数据加载路径 
        title="待办管理" //表格标题
        iconCls="icon-table"//表格图标
        rownumbers="true" 		//是否显示行号
        pagination="true" 		//是否使用分页
        toolbar="#toolbar"  	//工具栏绑定
        pageList="[10,20,30]" 	//页大小选择
        pageSize="10" 			//每页的条数
        striped="true" 			//行条纹
        collapsible="false"  	//显示可折叠按钮
        loadMsg="正在加载，请稍后"	//加载提示
        singleSelect="true"		//是否只能选择单行
        fitColumns="true"
        fit="true"
        >
	    <thead >
	        <tr align="center">
	            <!-- <th field="ck"  checkbox="true" ></th> -->
	            <th field="id" hidden="true">id</th> 
	            <th field="title" width="15%" >标题</th>
	            <th field="code" width="15%">编码</th>
	            <th field="name" width="30%" >名称</th>
	            <th field="_operate" formatter="formatOper" width="10%">操作</th><!-- 自定义列  -->
			</tr>
	    </thead>
	</table>
	<!-- <div id="enlarge_images" class="easyui-dialog" closed = true title=""
			style="float:left; width:400px;border:0;"
			> -->
			<!-- <div id="enlarge_images" 
			style="width:400px;display:none"
			> -->
	<div id="enlarge_images" style="display:none;position: absolute;">			
			<img id="limg" src="">
	</div>
	</div>
	<div id="showDiv" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true" buttons="#edit-buttons"  data-options="resizable:true,modal:true">
		<form id="todoForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
			<table width="100%">
				<tr class="fitem" id="approvetr">
					<td><label>审批：</label></td>
					<td>
						<div id="approveid"></div>
					</td>
				</tr>
				<tr class="fitem">
					<td><label>审批意见：</label></td>
					<td><input type="text" name="approveResult" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
			</table>
		</form>
		<div id="edit-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-ok" onclick="commitTask(this)">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-cancel" onclick="javascript:$('#showDiv').dialog('close')">取消</a>
		</div>
	</div>
	<div id="showImage" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true"  data-options="resizable:true,modal:true" fit="true">
		<table width="100%">
			<tr class="fitem">
				<td><div id="resourceImage"></div></td>
			</tr>
		</table>
	</div>
</div>
</body>
<script type="text/javascript">
//增加自定义列
function formatOper(val,row,index){
	var format = '<a href="javascript:void(0)" onclick="commit('+row.taskId+')">办理</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="openTaskForm('+row.taskId+','+row.instanceid+')">查看单据</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="showImage('+row.instanceid+')">查看流程图</a>';
	return format ;
} 
var taskId;
function commit(id){
	if (id!=""){
		taskId=id;
		jQuery.ajax({
			url : '${ctx}/workflow/toCommitTask/'+id,
			type : 'post',
			dataType : 'json',
			success : function(msg){
				var obj = eval(msg);
				if(obj.status == '000'){
					$('#showDiv').dialog('open').dialog('setTitle','办理');
					if(obj.result.length!=0){
						var approves="";
						for(var i=0;i<obj.result.length;i++){
							approves+='<input type="radio" name="approve" value="'+obj.result[i].key+'_'+obj.result[i].value+'"  checked="checked"/>'+obj.result[i].name;
						}
						$("#approveid").html(approves);
					}else{
						$("#approvetr").hide();
					}
					$("#showDiv textarea").val("");
				}else{
					$.messager.alert('提示',obj.info,'info');
				}
			},
			error: function(msg){
				var obj = eval(msg);
				$.messager.alert('提示',obj.info,'info');
			}
			
		})
	}else{
		$.messager.alert("提示","请选择操作项！","info");
	}
}
function commitTask(){
	jQuery.ajax({
		url : '${ctx}/workflow/commitTask/'+taskId,
		type : 'post',
		data : $('#todoForm').serialize(),
		dataType : 'json',
		success : function(msg){
			var obj = eval(msg);
			if(obj.status == 'ok'){
				$('#showDiv').dialog('close');
				$('#todoTable').datagrid('reload');	// reload the user data
			}else {
				$.messager.alert('提示',obj.info,'info');
			}
		},
		error: function(msg){
			var obj = eval(msg);
			$.messager.alert('提示',obj.info,'info');
		}
		
	})
	
}
function openTaskForm(taskid,instanceid){
	window.location.href='${ctx}/settingMeta/openTaskForm/todo/'+instanceid+'/'+taskid;
}
function showImage(id){
	$("#resourceImage").html('<img src="${ctx}/workflow/processImage/'+id+'"/>');
	$('#showImage').dialog('open').dialog('setTitle','查看流程图');
}
</script>
</html>