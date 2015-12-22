<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单</title>
<%@ include file="../../../common/common.jsp"%>
</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>单据管理->表单</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding:5px;">
	<div class="container">
			<div class="page-header" style="text-align: center;">
			  <h1>${metaCustom.metaname }<input type="hidden" name="metaid" value="${metaCustom.id }"><input type="hidden" name="busiId" value="${metaBusi.id }"></h1>
			</div>
			<form action="" id="busiForm">
			<input type="hidden" id="id" name="id">
			<p style="text-align: center;">
				${originalHtml }
			</p>
			</form>
		</div>
	<div class="container" align="center">
		<form id="submitForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
			<table width="100%" id="todoForm">
				<c:if test="${opentype=='claim' }">
				<tr class="fitem" id="approvetr">
					<td align="center">
						<input type="button" value="签收" onclick="claim('${taskId}')"/>
						<input type="button" value="返回" onclick="window.location.href='${ctx}/workflow/claimList'"/>
						<input type="button" value="查看流程图" onclick="showImage('${instanceid }');"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${opentype=='todo' }">
				<tr>
					<td colspan="2" align="center">
						<c:if test="${checkStartUser }">
						<input type="button" value="提交" onclick="commitStartTask('${taskId}');"/>
						</c:if>
						<c:if test="${!checkStartUser }">
						<input type="button" value="办理" onclick="commit('${taskId}');"/>
						</c:if>
						<input type="button" value="驳回" onclick="toRejectTask();"/>
						<input type="button" value="返回" onclick="window.location.href='${ctx}/workflow/todoList'"/>
						<input type="button" value="查看流程图" onclick="showImage('${instanceid }');"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${opentype=='back' }">
				<tr class="fitem">
					<td align="center">
						<input type="button" value="返回" onclick="window.location.href='${ctx}/workflow/approvedList'"/>
						<input type="button" value="查看流程图" onclick="showImage('${instanceid }');"/>
					</td>
				</tr>
				</c:if>
			</table>
			</form>
		</div>
	<table id="backTaskTable" class="easyui-datagrid" 
	    url="${ctx}/workflow/dataGridApprove/${instanceid}" //数据加载路径 
        title="审批历史管理" //表格标题
        iconCls="icon-table"//表格图标
        rownumbers="true" 		//是否显示行号
        pagination="true" 		//是否使用分页
        pageList="[10,20,30]" 	//页大小选择
        pageSize="10" 			//每页的条数
        striped="true" 			//行条纹
        collapsible="false"  	//显示可折叠按钮
        loadMsg="正在加载，请稍后"	//加载提示
        singleSelect="true"		//是否只能选择单行
        fitColumns="true"
        >
	    <thead >
	        <tr align="center">
	            <!-- <th field="ck"  checkbox="true" ></th> -->
	            <th field="id" hidden="true">id</th> 
	            <th field="taskid" width="15%" >节点名称</th>
	            <th field="userName" width="15%" >审批人</th>
	            <th field="approveresult" width="55%">审批意见</th>
	            <th field="apptype" width="10%" >类型</th>
	            <th field="approve" width="15%">时间</th>
			</tr>
	    </thead>
	</table>
	</div>
	<div id="showDiv" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true" buttons="#edit-buttons"  data-options="resizable:true,modal:true">
		<form id="todoForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
			<table width="100%">
				<tr class="fitem" id="rejecttr">
					<td><label>驳回：</label></td>
					<td>
						<div id="tasksid"></div>
					</td>
				</tr>
				<tr class="fitem">
					<td><label>驳回意见：</label></td>
					<td><input type="text" id="rejectResult" name="rejectResult" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
			</table>
		</form>
		<div id="edit-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-ok" onclick="rejectTask(this)">确定</a>
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
	<div id="showCommitDiv" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true" buttons="#edit-buttons"  data-options="resizable:true,modal:true">
		<form id="commitForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
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
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-cancel" onclick="javascript:$('#showCommitDiv').dialog('close')">取消</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	jQuery.ajax({
		url : '${ctx}/settingMeta/findBusi/${instanceid}',
		type : 'post',
		dataType : 'json',
		success : function(msg){
			var obj = eval(msg);
			if(obj.status == 'ok'){
				var val;
				for(var i=0;i<obj.result.length;i++){
					val=obj.result[i];
					if(val.type=="radios"){
						$("input[name='"+val.code+"'][value='"+val.value+"']").attr("checked",'checked');
					}else if(val.type=="checkboxs"){
						var vals=val.value.split(",");
						for(var j=0;j<vals.length;j++){
							$("input[name='"+val.code+"'][value='"+vals[j]+"']").attr("checked",true);
						}
					}else if(val.type=="textarea"){
						$("textarea[name='"+val.code+"']").text(val.value);
					}else{
						$("input[name='"+val.code+"']").val(val.value);
					}
				}
			}else{
				$.messager.alert('提示',obj.info,'info');
			}
		},
		error: function(msg){
			var obj = eval(msg);
			$.messager.alert('提示',obj.info,'info');
		}
		
	}) 
}); 
function claim(id){
	if (id!=""){
		$.messager.confirm('签收','确定要签收吗?',function(r){
			if (r){
				jQuery.ajax({
					url : '${ctx}/workflow/claimTask/'+id,
					type : 'post',
					dataType : 'json',
					success : function(msg){
						var obj = eval(msg);
						if(obj.status == 'ok'){
							window.location.href="${ctx}/workflow/claimList";
						}else{
							$.messager.alert('提示',obj.info,'info');
						}
					},
					error: function(msg){
						var obj = eval(msg);
						$.messager.alert('提示',obj.info,'info');
					}
					
				})
			}
		});
	}else{
		$.messager.alert("提示","请选择操作项！","info");
	}
}
function commitStartTask(){
	jQuery.ajax({
		url : '${ctx}/settingMeta/commitStartTask/${taskId}',
		type : 'post',
		data : $('#busiForm').serialize(),
		dataType : 'json',
		success : function(msg){
			var obj = eval(msg);
			if(obj.status == 'ok'){
				window.location.href="${ctx}/workflow/todoList";
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
function commitTask(){
	jQuery.ajax({
		url : '${ctx}/workflow/commitTask/${taskId}',
		type : 'post',
		data : $('#commitForm').serialize(),
		dataType : 'json',
		success : function(msg){
			var obj = eval(msg);
			if(obj.status == 'ok'){
				window.location.href="${ctx}/workflow/todoList";
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
function toRejectTask(){
	jQuery.ajax({
		url : '${ctx}/workflow/toRejectTask/${taskId}',
		type : 'post',
		dataType : 'json',
		success : function(msg){
			var obj = eval(msg);
			if(obj.status == 'ok'){
				var html="";
				for(var i=0;i<obj.result.length;i++){
					if(html!=""){
						html+='<br><input type="radio" name="taskkey" value="'+obj.result[i].key+'">'+obj.result[i].name+'</input>';
					}else{
						html+='<input type="radio" name="taskkey" value="'+obj.result[i].key+'" checked="checked">'+obj.result[i].name+'</input>';
					}
				}
				$("#tasksid").html(html);
				$('#showDiv').dialog('open').dialog('setTitle','驳回');
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
function rejectTask(){
	if($("input[name='taskkey'][checked]").val()){
		jQuery.ajax({
			url : '${ctx}/workflow/rejectTask/${taskId}',
			type : 'post',
			data : {approveResult:$("#rejectResult").val(),taskKey:$("input[name='taskkey'][checked]").val()},
			dataType : 'json',
			success : function(msg){
				var obj = eval(msg);
				if(obj.status == 'ok'){
					window.location.href="${ctx}/workflow/todoList";
				}else {
					$.messager.alert('提示',obj.info,'info');
				}
			},
			error: function(msg){
				var obj = eval(msg);
				$.messager.alert('提示',obj.info,'info');
			}
		})
	}else{
		$.messager.alert('提示',"请选择驳回节点",'info');
	}
	return;
}
function showImage(id){
	$("#resourceImage").html('<img src="${ctx}/workflow/processImage/'+id+'"/>');
	$('#showImage').dialog('open').dialog('setTitle','查看流程图');
}
function commit(id){
	if (id!=""){
		jQuery.ajax({
			url : '${ctx}/workflow/toCommitTask/'+id,
			type : 'post',
			dataType : 'json',
			success : function(msg){
				var obj = eval(msg);
				if(obj.status == '000'){
					$('#showCommitDiv').dialog('open').dialog('setTitle','办理');
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
(function($){
	$.fn.serializeJson=function(){
		var serializeObj={};
		var array=this.serializeArray();
		var str=this.serialize();
		$(array).each(function(){
			if(serializeObj[this.name]){
				if($.isArray(serializeObj[this.name])){
					serializeObj[this.name].push(this.value);
				}else{
					serializeObj[this.name]=[serializeObj[this.name],this.value];
				}
			}else{
				serializeObj[this.name]=this.value;	
			}
		});
		return serializeObj;
	};
})(jQuery);
</script>
</html>