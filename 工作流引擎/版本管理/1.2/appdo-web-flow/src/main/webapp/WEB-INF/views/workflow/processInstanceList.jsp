<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程实例列表</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;"
		fit="true">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>流程管理->流程实例</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding:5px;">
	<table id="processInstanceTable" class="easyui-datagrid" 
	    url="${ctx}/workflow/dataGridProcessInstance" //数据加载路径 
        title="流程实例列表" //表格标题
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
	            <th field="name" width="15%" >名称</th>
	            <th field="key" width="15%">流程定义key</th>
	            <th field="defname" width="15%">流程定义名称</th>
	            <th field="_operate" width="15%"  formatter="formatOpt">操作</th>
			</tr>
	    </thead>
	</table>
	<div id="enlarge_images" style="display:none;position: absolute;">			
			<img id="limg" src="">
	</div>
	<div id="toolbar" class="lb_search">
		<div  style="float:right">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="">返回</a>
		</div>
	</div>
	</div>
	<div id="showDiv" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true"  data-options="resizable:true,modal:true" fit="true">
		<form id="metaAddForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
			<table width="100%">
				<tr class="fitem">
					<td><div id="resourceImage"></div></td>
				</tr>
			</table>
		</form>
	<script type="text/javascript">
	function formatOpt(val,row,index){
		var format = '<a href="javascript:void(0)" onclick="showDiagram('+index+')">查看流程图</a>';
		if(row.suspend){
			format+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="active('+row.id+');">恢复</a>';
		}else{
			format+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="suspend('+row.id+');">挂起</a>';
		}
		format+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="showImage('+row.id+');">查看</a>';
		return format ;
	} 
	function suspend(id){
		if (id!=""){
			$.messager.confirm('挂起','确定要挂起吗?',function(r){
				if (r){
					suspended('suspend',id);
				}
			});
		}else{
			$.messager.alert("提示","请选择操作项！","info");
		}
	}
	function active(id){
		if (id!=""){
			$.messager.confirm('恢复','确定要恢复吗?',function(r){
				if (r){
					suspended('active',id);
				}
			});
		}else{
			$.messager.alert("提示","请选择操作项！","info");
		}
	}
	function suspended(opttype,id){
		jQuery.ajax({
			url : '${ctx}/workflow/isSuspended/'+opttype+'/'+id,
			type : 'post',
			dataType : 'json',
			success : function(msg){
				var obj = eval(msg);
				if(obj.status == 'ok'){
					$('#processInstanceTable').datagrid('reload');
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
	function showImage(id){
		$("#resourceImage").html('<img src="${ctx}/workflow/process/trace/auto/'+id+'"/>');
		$('#showDiv').dialog('open').dialog('setTitle','查看流程图');
	}
	function showDiagram(index){
		var row = $('#processInstanceTable').datagrid('getRows')[index];
		if (row){ 
			$("#resourceImage").html('<iframe src="${ctx}/workflow/diagram/?processDefinitionId='+row.defid+'&processInstanceId='+row.id+'" width="100%" height="680"></iframe>');
			$('#showDiv').dialog('open').dialog('setTitle','查看流程图');
		}
	}
	</script>
</div>
</body>
</html>