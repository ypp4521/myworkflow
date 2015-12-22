<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单管理</title>
<%@ include file="../../../common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/ztree/demo.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/ztree/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="${ctx}/static/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/static/js/org_domain_lang_zh.js"></script>
<script type="text/javascript">
	var listType = new Object();
	$(document).ready(function() {
		var setting = {
			async : {
				enable : true,
				url : "${ctx }/settingMeta/metaCustomTree",
				autoParam : [ "id" ],
				dataFilter : filter
			},
			view : {
				showLine: true,
				selectedMulti : false
			},
			data : {
				simpleData : {
					enable : true
				},
				key : {
					name : "name"
				},
				simpleData : {
					pIdKey : "pId"
				}
			},
			callback : {
				onClick : zTreeOnClick
			}
		};
		$.fn.zTree.init($("#tree"), setting);
		$("#selectAll").bind("click", selectAll);
		selectFirstOne();
		$('#orgTabs').tabs({
			border : false
		});
	});
	function filter(treeId, parentNode, childNodes) {
		for (var i = 0, l = childNodes.length; i < l; i++) {

		}
		return childNodes;
	}
	function selectFirstOne() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var selectNodeOrg = zTree.getNodeByTId("tree_1");
		if (selectNodeOrg != null) {
			zTree.selectNode(selectNodeOrg);
		}
	}
	function zTreeOnClick(event, treeId, treeNode) {
		$('#metaTenantTable').datagrid({
			queryParams : {
				'metaId' : treeNode.id
			}
		});
		$("#addtb").show();
		$("#metaidadd").val(treeNode.id);
		$("#metaidedit").val(treeNode.id);
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
	}
</script>
</head>
<body>
	<script>
		setTimeout("divClearBoth()", 100);
		function divClearBoth() {
			$("#clearboth").html("");
		}
	</script>
	<div id="clearboth" style="width: 1000px;">
		<div style="width: 1000px; height: 10000px;"></div>
	</div>

	<!--左侧开始 -->
	<div class="easyui-layout" fit="true">
		<div region="north" split="false" border="true" style="height: 30px;">
			<div class="titleTop" style="height: 18px;">
				<div style="float: left;">
					<img class="titleImage" src="${ctx}/static/images/daohang.png" />
				</div>
				<div style="float: left;">流程管理->模型管理</div>
			</div>
		</div>
		<!-- 单据类型 -->
		<div data-options="region:'west'" split="true" title=""
			style="width: 235px; padding: 1px; overflow-x: hidden; overflow-y: auto;">
			<div id="orgTabs" class="easyui-tabs" fit="true">
				<div title="单据类型">
					<!--搜索开始-->
					<div class="easyui-panel"
						style="width: 500px; border: 0px; padding-top: 0; margin-top: 0px;">
						<ul id="tree" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
		<div id="mainPanle" region="center"
			style="width: 100%; height: 100%; overflow-y: hidden; padding: 5px;">
			<div class="easyui-layout"
				style="width: 100%; height: 100%; border: none;" fit="true">
				<div region="north" split="false" border="false">
					<div style="width: 99%; height: 33px; margin-left: 1%;">
						<c:if test="${tenantId==null }">
							租户：<input type="text" id="personnelSearchKey"
								name="personnelSearchKey" class="easyui-validatebox textbox" />
							<a href="javascript:void(0)" id="search"
								class="easyui-linkbutton" iconCls="icon-search"
								onclick="doSearch()">选择</a>
						</c:if>
					</div>
				</div>
				<div region="center" split="false" border="false"
					style="border: none;">

					<table id="metaTenantTable" class="easyui-datagrid"
						style="float: right; padding: 5px"
						url="${ctx }/settingMeta/dataGridMetaTenant?tenantId=${tenantId}"
						//数据加载路径 title="模型列表" //表格标题 iconCls="icon-table" //表格图标
						rownumbers="true" //是否显示行号 pagination="true" //是否使用分页
						toolbar="#searchtb" //工具栏绑定 pageList="[10,20,30]" //页大小选择
						pageSize="20" //每页的条数 striped="true" //行条纹 collapsible="false"
						//显示可折叠按钮 loadMsg="正在加载，请稍后" //加载提示 singleSelect="false"
						//是否只能选择单行 fitColumns="true" fit="true">
						<thead width="100%">
							<tr align="center">
								<th field="ck" checkbox="true" formatter="entcheckOper"
									width="10%"></th>
								<th field="id" hidden="true"></th>
								<th field="modelname" width="25%" sortable="true">模型名称</th>
								<th field="singletype" width="25%" sortable="true"
									formatter="singletypeOper">制单人类型</th>
								<th field="singler" width="25%" sortable="true">制单人</th>
								<th field="_operate" formatter="entformatOper" width="25%">操作</th>
								<!-- 自定义列 -->
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div id="searchtb" class="lb_search">
				<a id="addtb" href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" onclick="showDiv()"
					style="float: right; margin: auto; margin-right: 5px; display: none">添加</a>
			</div>
		</div>
		<div id="showDiv" class="easyui-dialog"
			style="width: 440px; padding: 20px 20px" closed="true"
			buttons="#edit-buttons" data-options="resizable:true,modal:true">
			<form id="metaAddForm" method="post" style="margin-left: 30px;"
				enctype='multipart/form-data'>
				<table width="100%">
					<tr class="fitem">
						<td><label>模型名称：</label><input type="hidden"
							name="metaTenant.id"><input type="hidden" id="metaidadd"
							name="metaTenant.metaid"></td>
						<td><input type="text" class="easyui-validatebox txt"
							style="height: 25px;" name="metaTenant.modelname"></td>
					</tr>
					<c:if test="${tenantId==null }">
						<tr class="fitem">
							<td><label>租户：</label></td>
							<td><input type="text" name="metaTenant.tenantId"
								class="easyui-validatebox txt" maxlength="100"
								style="margin-top: 10px; height: 25px;"></td>
						</tr>
					</c:if>
					<c:if test="${tenantId!=null }">
						<input type="hidden" name="metaTenant.tenantId"
							value="${tenantId }" />
					</c:if>
					<tr class="fitem">
						<td><label>制单人类型：</label></td>
						<td><select name="metaTenant.singletype"
							class="easyui-validatebox"
							style="margin-top: 10px; height: 25px;">
								<c:forEach items="${listType }" var="type" varStatus="id">
									<option value="${type.value }">${type.name }</option>
									<script type="text/javascript">
										listType["${type.value }"] = '${type.name }';
									</script>
								</c:forEach>
						</select></td>
					</tr>
					<tr class="fitem">
						<td><label>制单人：</label></td>
						<td><input type="text" name="metaTenant.singler"
							class="easyui-validatebox txt" maxlength="100"
							style="margin-top: 10px; height: 25px;"></td>
					</tr>
					<tr class="fitem">
						<td><label>备注：</label></td>
						<td><textarea rows="3" cols="40" name="metaTenant.remark"></textarea>
					</tr>
				</table>
			</form>
			<div id="edit-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 85px;" iconCls="icon-ok" onclick="addAd(this)">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 85px;" iconCls="icon-cancel"
					onclick="javascript:$('#showDiv').dialog('close')">取消</a>
			</div>
		</div>
		<div id="showEditDiv" class="easyui-dialog"
			style="width: 440px; padding: 20px 20px" closed="true"
			buttons="#edit-buttons" data-options="resizable:true,modal:true">
			<form id="metaEditForm" method="post" style="margin-left: 30px;"
				enctype='multipart/form-data'>
				<table width="100%">
					<tr class="fitem">
						<td><label>模型名称：</label><input type="hidden"
							id="metaTenant.id" name="metaTenant.id"><input
							type="hidden" id="metaidedit" name="metaTenant.metaid"></td>
						<td><input type="text" class="easyui-validatebox txt"
							style="height: 25px;" id="metaTenant.modelname"
							name="metaTenant.modelname"></td>
					</tr>
					<c:if test="${tenantId==null }">
						<tr class="fitem">
							<td><label>租户：</label></td>
							<td><input type="text" id="metaTenant.tenantId"
								name="metaTenant.tenantId" class="easyui-validatebox txt"
								maxlength="100" style="margin-top: 10px; height: 25px;"></td>
						</tr>
					</c:if>
					<c:if test="${tenantId!=null }">
						<input type="hidden" name="metaTenant.tenantId"
							value="${tenantId }" />
					</c:if>
					<tr class="fitem">
						<td><label>制单人类型：</label></td>
						<td><select id="metaTenant.singletype"
							name="metaTenant.singletype" class="easyui-validatebox"
							style="margin-top: 10px; height: 25px;">
								<c:forEach items="${listType }" var="type" varStatus="id">
									<option value="${type.value }">${type.name }</option>
									<script type="text/javascript">
										listType["${type.value }"] = '${type.name }';
									</script>
								</c:forEach>
						</select></td>
					</tr>
					<tr class="fitem">
						<td><label>制单人：</label></td>
						<td><input type="text" id="metaTenant.singler"
							name="metaTenant.singler" class="easyui-validatebox txt"
							maxlength="100" style="margin-top: 10px; height: 25px;"></td>
					</tr>
					<tr class="fitem">
						<td><label>备注：</label></td>
						<td><textarea rows="3" cols="40" id="metaTenant.remark"
								name="metaTenant.remark"></textarea>
					</tr>
				</table>
			</form>
			<div id="edit-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 85px;" iconCls="icon-ok" onclick="edit(this)">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 85px;" iconCls="icon-cancel"
					onclick="javascript:$('#showEditDiv').dialog('close')">取消</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function entcheckOper(val, row, index) {
			return '<input type="checkbox" name="id" value="'+row.id+'" />';
		}
		function singletypeOper(val, row, index) {
			return listType[val];
		}
		//增加自定义列
		function entformatOper(val, row, index) {
			var item = '<a href="javascript:void(0)" onclick="showEidt('
					+ index + ')">编辑</a>';
			if (row.deploystate == "0") {
				item += '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="removeAd('
						+ row.id
						+ ','
						+ row.tenantId
						+ ')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="deploy('
						+ row.id + ',' + row.tenantId + ')">部署</a>'
				item += '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="editPerson('
						+ index + ')">流程设计</a>';		
			} else {

				item += '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="defList('
						+ index + ')">流程列表</a>';
				item += '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="showProc('+index+')">查看流程图</a> &nbsp;&nbsp;<a href="javascript:void(0)" onclick="removeAd('
				+ row.id
				+ ','
				+ row.tenantId
				+ ')">删除</a>';		
			}
			item += '&nbsp;&nbsp;<a href="javascript:void(0)" onclick="editPerson('+ index + ')">重新设计</a>';
			return item;
		}
		function doSearch() {
		}

		function showDiv() {
			$('#showDiv').dialog('open').dialog('setTitle', '添加模型');
			$("#showDiv input:text").val("");
			$("#showDiv select").val("1");
			$("#showDiv textarea").val("");
		}
		function editPerson(index) {
			var row = $('#metaTenantTable').datagrid('getRows')[index];
			if (row) {
				window.location.href = '${ctx}/workflow/modeler?modelId='+ row.modelid;
			}
		}
		//添加
		function addAd(obj) {
			jQuery
					.ajax({
						url : '${ctx}/settingMeta/saveMetaTenant',
						type : 'post',
						data : $('#metaAddForm').serialize(),
						dataType : 'json',
						success : function(msg) {
							var obj = eval(msg);
							if (obj.status == 'ok') {
								$('#metaTenantTable').datagrid('reload');
								$('#showDiv').dialog('close');
								window.location.href = '${ctx}/workflow/modeler?modelId='
										+ obj.result;
							} else {
								$.messager.alert('提示', obj.info, 'info');
							}
						},
						error : function(msg) {
							var obj = eval(msg);
							$.messager.alert('提示', obj.info, 'info');
						}
					})
		}
		function showEidt(index) {
			var row = $('#metaTenantTable').datagrid('getRows')[index];
			if (row) {
				$('#showEditDiv').dialog('open').dialog('setTitle', '编辑元数据');
				document.getElementById("metaTenant.id").value = row.id;
				document.getElementById("metaidedit").value = row.metaid;
				document.getElementById("metaTenant.modelname").value = row.modelname;
				if (document.getElementById("metaTenant.tenantId")) {
					document.getElementById("metaTenant.tenantId").value = row.tenantId;
				}
				document.getElementById("metaTenant.singler").value = row.singler;
				document.getElementById("metaTenant.singletype").value = row.singletype;
				document.getElementById("metaTenant.remark").value = row.remark;
			}
		}
		function showProc(index) {
			var row = $('#metaTenantTable').datagrid('getRows')[index];
			if (row) {
				//window.location.href = '${ctx}/workflow/modeler?modelId='+ row.modelid;
				window.location.href = '${ctx}/workflow/modeler?dis=1&modelId='+ row.modelid;
				//window.location.href = '${ctx}/workflow/designer/65001';//eayui设计器不可用
			}
		}
		//编辑
		function edit(obj) {
			jQuery
					.ajax({
						url : '${ctx}/settingMeta/editMetaTenant',
						type : 'post',
						data : $('#metaEditForm').serialize(),
						dataType : 'json',
						success : function(msg) {
							var obj = eval(msg);
							if (obj.status == 'ok') {
								$('#metaTenantTable').datagrid('reload');
								$('#showEditDiv').dialog('close');
								/* window.location.href = '${ctx}/workflow/modeler?modelId='
										+ obj.result; */
								$.messager.alert('提示', obj.info, 'info');
							} else {
								$.messager.alert('提示', obj.info, 'info');
							}
						},
						error : function(msg) {
							var obj = eval(msg);
							$.messager.alert('提示', obj.info, 'info');
						}

					})
		}
		function removeAd(id, tenantId) {
			if (id != "") {
				$.messager.confirm('删除', '确定要删除吗?', function(r) {
					if (r) {
						jQuery.ajax({
							url : '${ctx}/settingMeta/delMetaTenant/' + id
									+ '/' + tenantId,
							type : 'post',
							dataType : 'json',
							success : function(msg) {
								var obj = eval(msg);
								if (obj.status == 'ok') {
									$('#metaTenantTable').datagrid('reload');
								} else {
									$.messager.alert('提示', obj.info, 'info');
								}
							},
							error : function(msg) {
								var obj = eval(msg);
								$.messager.alert('提示', obj.info, 'info');
							}

						})
					}
				});
			} else {
				$.messager.alert("提示", "请选择操作项！", "info");
			}
		}
		function deploy(id, tenantId) {
			if (id != "") {
				$.messager.confirm('提示', '确定要部署吗?部署之后将无法进行变更！', function(r) {
					if (r) {
						jQuery.ajax({
							url : '${ctx}/settingMeta/deployModel/' + id + '/'
									+ tenantId,
							type : 'post',
							dataType : 'json',
							success : function(msg) {
								var obj = eval(msg);
								if (obj.status == 'ok') {
									$('#metaTenantTable').datagrid('reload');
								} else {
									$.messager.alert('提示', obj.info, 'info');
								}
							},
							error : function(msg) {
								var obj = eval(msg);
								$.messager.alert('提示', obj.info, 'info');
							}
						})
					}
				});
			} else {
				$.messager.alert("提示", "请选择操作项！", "info");
			}
		}
		function defList(index) {
			var row = $('#metaTenantTable').datagrid('getRows')[index];
			if (row) {
				window.location.href = "${ctx}/workflow/processDefList/"
						+ row.modelkey;
			}
		}
	</script>

</body>
</html>