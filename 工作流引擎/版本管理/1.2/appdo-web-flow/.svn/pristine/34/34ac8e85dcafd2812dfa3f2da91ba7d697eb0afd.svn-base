<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程图列表</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;"
		fit="true">
		<div region="north" split="false" border="false" class="st_head">
			<div class="titleTop">
				<span><img class="titleImage"
					src="${ctx }/static/images/daohang.png"> </span> <span>流程管理->模型管理->流程图</span>
			</div>
		</div>
		<div region="center" style="overflow-y: hidden; padding: 5px;">
			<table id="processDefTable" class="easyui-datagrid"
				url="${ctx}/workflow/dataGridProcessDef/${id}" //数据加载路径
				title="流程图列表" //表格标题 iconCls="icon-table" //表格图标 rownumbers="true"
				//是否显示行号 pagination="true" //是否使用分页 toolbar="#toolbar" //工具栏绑定
				pageList="[10,20,30]" //页大小选择 pageSize="10" //每页的条数 striped="true"
				//行条纹 collapsible="false" //显示可折叠按钮 loadMsg="正在加载，请稍后" //加载提示
				singleSelect="true" //是否只能选择单行 fitColumns="true" fit="true">
				<thead>
					<tr align="center">
						<!-- <th field="ck"  checkbox="true" ></th> -->
						<th field="id" hidden="true">id</th>
						<th field="name" width="15%" formatter="formatName">名称</th>
						<th field="category" width="15%">类别</th>
						<th field="resourceName" width="20%" formatter="formatXml">xml名称</th>
						<th field="version" width="10%">版本</th>
						<th field="_operate" width="15%" formatter="formatImage">操作</th>
					</tr>
				</thead>
			</table>
			<!-- <div id="enlarge_images" class="easyui-dialog" closed = true title=""
			style="float:left; width:400px;border:0;"
			> -->
			<!-- <div id="enlarge_images" 
			style="width:400px;display:none"
			> -->
			<div id="enlarge_images" style="display: none; position: absolute;">
				<img id="limg" src="">
			</div>
			<div id="toolbar" class="lb_search">
				<div style="float: right">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add"
						onclick="window.location.href='${ctx}/settingModel/modelList'">返回</a>
				</div>
			</div>
		</div>
		<div id="showDiv" class="easyui-dialog"
			style="width: 440px; padding: 20px 20px" fit="true" closed="true"
			buttons="#edit-buttons" data-options="resizable:true,modal:true">
			<form id="metaAddForm" method="post" style="margin-left: 30px;"
				enctype='multipart/form-data'>
				<table width="100%">
					<tr class="fitem">
						<td><div id="resourceImage"></div></td>
					</tr>
				</table>
			</form>
			<script type="text/javascript">
				function formatXml(val, row, index) {
					var format = '<a href="${ctx}/workflow/loadResource/xml/'+row.id+'">'
							+ row.resourceName + '</a>';
					return format;
				}
				function formatName(val, row, index) {
					var format = row.resourceName.split('.')[0];
					return format;
				}
				function formatImage(val, row, index) {
					var formatName = row.resourceName.split('.')[0];
					return '<a href="javascript:void(0)" onclick="showImage('
							+ index
							+ ');">查看流程图</a>&nbsp;&nbsp;<a href="${ctx}/workflow/activityList/'+row.id+'/'+formatName+'">节点列表</a>';
				}
				function showImage(index) {
					var row = $('#processDefTable').datagrid('getRows')[index];
					if (row) {
						$("#resourceImage")
								.html(
										'<img src="${ctx}/workflow/loadResource/image/'+row.id+'"/>');
						$('#showDiv').dialog('open')
								.dialog('setTitle', '查看流程图');
					}
				}
			</script>
		</div>
</body>
</html>