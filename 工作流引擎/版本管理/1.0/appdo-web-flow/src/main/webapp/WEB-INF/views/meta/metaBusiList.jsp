<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单管理</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;"
		fit="true">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>元数据管理->元数据->表单</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding:5px;">
	<table id="metaBusiTable" class="easyui-datagrid" 
	    url="${ctx}/settingMeta/dataGridBusi/${id}" //数据加载路径 
        title="表单管理" //表格标题
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
	            <th field="busipath" hidden="true">路径</th>
	            <th field="flowid" hidden="true">流程</th>
	            <th field="version" hidden="true">版本</th>
	            <th field="metaid" hidden="true">模型id</th>
	            <th field="businame" width="15%" >模板名称</th>
	            <th field="busicode" width="15%">模板编码</th>
	            <th field="isapp" width="30%" formatter="ifPcOper" >类型</th>
	            <th field="enable" width="30%" formatter="enableOper">启用</th>
	            
	            <th field="createtime" width="30%" >创建时间</th>
	            <th field="remark" width="30%" >备注</th>
	            <th field="_operate" formatter="formatOper" width="15%">操作</th><!-- 自定义列  -->
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
	<div id="toolbar" class="lb_search">
		<div  style="float:right">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="window.location.href='${ctx}/settingMeta/formDesigner/${metaid }/0'">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="window.location.href='${ctx}/settingMeta/metaCustomList'">返回</a>
		</div>
	</div>
	</div>
	<div id="showEditDiv" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true" buttons="#edit-buttons"  data-options="resizable:true,modal:true">
		<form id="metaEditForm" method="post" style="margin-left:30px;"  enctype='multipart/form-data'>
			<table width="100%">
				<tr class="fitem">
					<td><label>模板名称：</label><input type="hidden" id="metaBusi.id" name="metaBusi.id"></td>
					<td><input type="text" disabled="true" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.businame" name="metaBusi.businame"></td>
				</tr>
				<tr class="fitem">
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.businame" name="metaBusi.businame"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.busicode" name="metaBusi.busicode"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.isapp" name="metaBusi.isapp"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.enable" name="metaBusi.enable"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.version" name="metaBusi.version"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.busipath" name="metaBusi.busipath"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.metaid" name="metaBusi.metaid"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.createtime" name="metaBusi.createtime"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.remark" name="metaBusi.remark"></td>
					<td><input type="hidden" class="easyui-validatebox txt" style="height:25px;" id="metaBusi.tenantId" name="metaBusi.tenantId"></td>
				</tr>
				<tr class="fitem">
					<td><label>选择流程：</label></td>
					<td>
						<select name="metaBusi.flowid" class="easyui-validatebox" style="width:200px;margin-top:10px;height:25px;" id=csmSelect>
							<option value="-1">--请选择--</option>
						<c:forEach items="${flowList }" var="flow"  varStatus="id">
							<option value="${flow.modelkey }">${flow.modelname }</option>
						</c:forEach>
						</select>
				</tr>
			</table>
		</form>
		<div id="edit-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-ok" onclick="edit(this)">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-cancel" onclick="javascript:$('#showEditDiv').dialog('close')">取消</a>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
//增加自定义列
function formatOper(val,row,index){
	var format = '<a href="javascript:void(0)" onclick="showEidt('+index+')">编辑</a>&nbsp;<a href="javascript:void(0)" onclick="removeAd('+row.id+')">删除</a>&nbsp;<a href="javascript:void(0)" onclick="bindAd('+index+')">流程绑定</a>';
	return format ;
} 
function enableOper(val,row,index){
	if(val=='1'){
		return "启用";
	}else{
		return "停用";
	}
}
ifPcOper
function ifPcOper(val,row,index){
	if(val=='pc'){
		return "PC端";
	}else{
		return "app端";
	}
}
function bindAd(index){
	var row = $('#metaBusiTable').datagrid('getRows')[index];
	if (row){
		$('#showEditDiv').dialog('open').dialog('setTitle','绑定流程');
		document.getElementById("metaBusi.id").value=row.id;
		document.getElementById("metaBusi.metaid").value=row.metaid;
		document.getElementById("metaBusi.busicode").value=row.busicode;
		document.getElementById("metaBusi.businame").value=row.businame;
		document.getElementById("metaBusi.isapp").value=row.isapp;
		document.getElementById("metaBusi.enable").value=row.enable;
		document.getElementById("metaBusi.version").value=row.version;
		document.getElementById("metaBusi.busipath").value=row.busipath;
		document.getElementById("metaBusi.createtime").value=row.createtime;
		document.getElementById("metaBusi.remark").value=row.remark;
		document.getElementById("metaBusi.tenantId").value=row.tenantId;
		$("#csmSelect").find('option[value='+row.flowid+']').attr("selected",true);
	}
}
function showEidt(index){
	var row = $('#metaBusiTable').datagrid('getRows')[index];
	if (row){ 
		window.location.href="${ctx}/settingMeta/formDesigner/${metaid }/"+row.id;
	}
}

//编辑
function edit(index){
	var id = document.getElementById("metaBusi.id").value;
	var metaid = document.getElementById("metaBusi.metaid").value;
	var tenantId = document.getElementById("metaBusi.tenantId").value;
	var flow_id = document.getElementById("csmSelect").value;
	var json = {"id":id,"metaid":metaid,"tenantId":tenantId,"flow_id":flow_id};
	var con = JSON.stringify(json);
	jQuery.ajax({
		url : '${ctx}/settingMeta/editMetaBusi/',
		type : 'post',
		data : con,
		contentType : "application/json;charset=UTF-8",
		success : function(msg){
			var obj = eval(msg);
			if(obj.status == 'ok'){
				$('#showEditDiv').dialog('close');
				$('#metaBusiTable').datagrid('reload');	// reload the user data
				$.messager.alert('提示',obj.info,'info');
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


function removeAd(id){
	if (id!=""){
		$.messager.confirm('删除','确定要删除吗?',function(r){
			if (r){
				jQuery.ajax({
					url : '${ctx}/settingMeta/delMetaBusi/'+id,
					type : 'post',
					dataType : 'json',
					success : function(msg){
						var obj = eval(msg);
						if(obj.status == 'ok'){
							$('#metaBusiTable').datagrid('reload');	// reload the user data
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
</script>
</html>