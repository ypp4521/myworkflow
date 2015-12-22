<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>节点列表</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;"
		fit="true">
		<div region="north" split="false" border="false" class="st_head">
			<div class="titleTop">
				<span><img class="titleImage"
					src="${ctx }/static/images/daohang.png"> </span> <span>流程管理->模型管理->流程图->节点列表</span>
			</div>
		</div>
		<div region="center" style="overflow-y: hidden; padding: 5px;">
			<table id="processDefTable" class="easyui-datagrid"
				url="${ctx}/workflow/dataGridActivity/${id}" //数据加载路径
				title="${name}-节点列表" //表格标题 iconCls="icon-table" //表格图标
				rownumbers="true" //是否显示行号 pagination="false" //是否使用分页
				toolbar="#toolbar" //工具栏绑定 pageList="[10,20,30]" //页大小选择
				pageSize="10" //每页的条数 striped="true" //行条纹 collapsible="false"
				//显示可折叠按钮 loadMsg="正在加载，请稍后" //加载提示 singleSelect="true" //是否只能选择单行
				fitColumns="true" fit="true">
				<thead>
					<tr align="center">
						<!-- <th field="ck"  checkbox="true" ></th> -->
						<th field="id" hidden="true">id</th>
						<th field="name" width="15%">名称</th>
						<th field="_operate" width="15%" formatter="formatImage">操作</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar" class="lb_search">
				<div style="float: right">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add"
						onclick="window.location.href='${ctx}/settingModel/modelList'">返回</a>
				</div>
			</div>

			<!-- 加载表单信息 -->
			<div id="showEditDiv" class="easyui-dialog"
				style="width: 440px; padding: 20px 20px" closed="true"
				buttons="#edit-buttons" data-options="resizable:true,modal:true">
				<form id="metaEditForm" method="post" style="margin-left: 30px;"
					enctype='multipart/form-data'>
					<table width="100%">
						<tr class="fitem">
							<td><label>模型名称：</label><input type="hidden" id="flow_id"
								name="flow_id" value="${id}"></td>
							<td><input type="text" class="easyui-validatebox txt"
								style="height: 25px;" id="flow_name" readonly="readonly"
								name="flow_name" value="${name}"> <input type="hidden"
								id="wf_meta_busi_id" name="wf_meta_busi_id"
								value="${metaBusi_id}"></td>
						</tr>
						<tr class="fitem">
							<td><label>意见填写节点：</label></td>
							<td><input type="text" class="easyui-validatebox txt"
								style="height: 25px;" id="flow_node_name" readonly="readonly"
								name="flow_node_name" value=""></td>
						</tr>
						<tr class="fitem">
							<td><label>表单模版选择：</label></td>
							<td><select id="wf_meta_customs" name="wf_meta_customs"
								class="easyui-validatebox"
								style="margin-top: 10px; height: 25px;">
									<option value="${metaBusi_id }">${metaBusi_name }</option>
							</select> <input type="hidden" id="wf_meta_busi_id" name="wf_meta_busi_id"
								value="${metaBusi_code}"></td>
						</tr>
						<tr class="fitem">
							<td><label>表单意见字段：</label></td>
							<td><select id="wf_meta_custom_field"
								name="wf_meta_custom_field" class="easyui-validatebox"
								style="margin-top: 10px; height: 25px;">
									<c:forEach items="${fieldList }" var="field" varStatus="id">
										<option value="${field.fieldcode}">${field.fieldname }</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</form>
				<div id="edit-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						style="width: 85px;" iconCls="icon-ok"
						onclick="addFlowField(this)">绑定</a> <a href="javascript:void(0)"
						class="easyui-linkbutton" style="width: 85px;"
						iconCls="icon-cancel"
						onclick="javascript:$('#showEditDiv').dialog('close')">取消</a>
				</div>
			</div>
			<!-- 加载表单信息 -->
		</div>
		<script type="text/javascript">
			function formatImage(val, row, index) {
				var error = '<%=request.getAttribute("error") %>';
				if(error){
					var item = '<a href="javascript:void(0)" onclick="showEidt('
						+ index + ')">绑定表单意见字段</a>';
					return item;
				}else{
					return '';
				}
			}
			function showEidt(index) {
				var row = $('#processDefTable').datagrid('getRows')[index];
				if (row) {
					$('#showEditDiv').dialog('open').dialog('setTitle',
							'流程节点绑定表单意见字段配置');
					$('#flow_node_name').val(row.name);
					$('#wf_node_id').val(row.id);
				}
			}
			//绑定调用方法
			function addFlowField() {
				
				//得到选择的表单字段
				var wf_meta_custom_field_code = $('select#wf_meta_custom_field')
						.find('option:selected').val();
				var wf_meta_custom_field_name = $('select#wf_meta_custom_field')
						.find('option:selected').text();
				var jsonParam = {
					"wf_node_id" : $('#wf_node_id').val(),
					"wf_node_name" : $('#flow_node_name').val(),
					"wf_meta_busi_id" : $('#wf_meta_busi_id').val(),
					"wf_meta_customs_id" : $('#custom_id').val(),
					"wf_meta_customs_code" : $('#custom_code').val(),
					"wf_modelkey" : $('#flow_id').val(),
					"wf_meta_custom_field_code" : wf_meta_custom_field_code,
					"wf_meta_custom_field_name" : wf_meta_custom_field_name,
					"do":"add"
				};
				var json = JSON.stringify(jsonParam);
				$.ajax({
					type : "post",
					url : "http://localhost:8080/appdo-web-flow/settingMeta/doBingWorkFlowNodesByModelId",
					contentType : "application/json",
					data : json,
					success : function(data) {
						if(data.status!="ok"){
							$.messager.alert('提示', data.info, 'info');
						}else{
							$.messager.alert('提示', '流程绑定表单意见字段成功', 'info');
						}
						$('#showEditDiv').dialog('close');
					},
					error : function(error) {
						$.messager.alert('错误','操作失败', 'error');
					}
				});
				
			}
		</script>
		<input type="hidden" id="custom_id" value="${custom_id}"> <input
			type="hidden" id="custom_name" value="${custom_name}"> <input
			type="hidden" id="custom_code" value="${custom_code}"> <input
			type="hidden" id="wf_node_id">
</body>
</html>