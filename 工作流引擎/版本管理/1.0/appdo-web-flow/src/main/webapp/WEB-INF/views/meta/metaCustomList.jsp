<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>元数据管理</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<div class="easyui-layout" style="width: 100%; height: 100%;"
		fit="true">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>元数据管理->元数据</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding:5px;">
	<table id="metaCustomTable" class="easyui-datagrid" 
	    url="${ctx}/settingMeta/dataGridMetaCustom" //数据加载路径 
        title="元数据" //表格标题
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
	            <th field="metaname" width="15%" >元数据名称</th>
	            <th field="metacode" width="15%">元数据编码</th>
	            <th field="userName" width="15%">制单人</th>
	            <th field="createtime" width="15%">制单日期</th>
	            <th field="year" width="15%">年度</th>
	           <!--  <th field="metaService" width="30%" >提交服务名</th>
	            <th field="querymethod" width="30%" >查询服务名</th>
	            <th field="functionid" width="20%" >功能</th> -->
	            <th field="_operate" formatter="formatOper" width="20%">操作</th><!-- 自定义列  -->
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
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="showDiv()">添加</a>
		</div>
	</div>
	</div>
	<div id="showDiv" class="easyui-dialog" 
	
			style="width:440px;padding:20px 20px"
			closed="true" buttons="#edit-buttons"  data-options="resizable:true,modal:true">
		<form id="metaAddForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
			<table width="100%">
				<tr class="fitem">
					<td><label>元数据名称：</label><input type="hidden" name="metaCustom.id"></td>
					<td><input type="text" class="easyui-validatebox txt" style="height:25px;" name="metaCustom.metaname"></td>
				</tr>
				<tr class="fitem">
					<td><label>元数据编码：</label></td>
					<td><input type="text" name="metaCustom.metacode" class="easyui-validatebox txt" maxlength="150" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<%-- <tr class="fitem">
					<td><label>VO类路径：</label></td>
					<td><input type="text" name="metaCustom.classpath" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>服务名：</label></td>
					<td><input type="text" name="metaCustom.metaService" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>查询：</label></td>
					<td><input type="text" name="metaCustom.querymethod" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>功能：</label></td>
					<td><input type="text" name="metaCustom.functionid" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>父元数据：</label></td>
					<td>
						<select name="metaCustom.parentid" class="easyui-validatebox" style="margin-top:10px;height:25px;">
							<option value="-1">--请选择--</option>
						<c:forEach items="${listMeta }" var="meta"  varStatus="id">
							<option value="${meta.id }">${meta.metaname }</option>
						</c:forEach>
						</select>
				</tr> --%>
				<tr class="fitem">
					<td><label>备注：</label></td>
					<td><textarea rows="3" cols="40" name="metaCustom.remark"></textarea>
				</tr>
			</table>
		</form>
		<div id="edit-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-ok" onclick="addAd(this)">确定</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:85px;" iconCls="icon-cancel" onclick="javascript:$('#showDiv').dialog('close')">取消</a>
		</div>
	</div>
	
	<div id="showEditDiv" class="easyui-dialog" 
			style="width:440px;padding:20px 20px"
			closed="true" buttons="#edit-buttons"  data-options="resizable:true,modal:true">
		<form id="metaEditForm" method="post" style="margin-left:30px;"  enctype='multipart/form-data'>
			<table width="100%">
				<tr class="fitem">
					<td><label>元数据名称：</label><input type="hidden" id="metaCustom.id" name="metaCustom.id"></td>
					<td><input type="text" class="easyui-validatebox txt" style="height:25px;" id="metaCustom.metaname" name="metaCustom.metaname"></td>
				</tr>
				<tr class="fitem">
					<td><label>元数据编码：</label></td>
					<td><input type="text" id="metaCustom.metacode" name="metaCustom.metacode" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<%-- <tr class="fitem">
					<td><label>VO类路径：</label></td>
					<td><input type="text" id="metaCustom.classpath" name="metaCustom.classpath" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>服务名：</label></td>
					<td><input type="text" id="metaCustom.metaService" name="metaCustom.metaService" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>查询：</label></td>
					<td><input type="text" id="metaCustom.querymethod" name="metaCustom.querymethod" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>功能：</label></td>
					<td><input type="text" id="metaCustom.functionid" name="metaCustom.functionid" class="easyui-validatebox txt" maxlength="100" style="margin-top:10px;height:25px;" ></td>
				</tr>
				<tr class="fitem">
					<td><label>父元数据：</label></td>
					<td>
						<select id="metaCustom.parentid" name="metaCustom.parentid" class="easyui-validatebox" style="margin-top:10px;height:25px;">
							<option value="-1" selected="selected">--请选择--</option>
						<c:forEach items="${listMeta }" var="meta"  varStatus="id">
							<option value="${meta.id }">${meta.metaname }</option>
						</c:forEach>
						</select>
				</tr> --%>
				<tr class="fitem">
					<td><label>备注：</label></td>
					<td><textarea rows="3" cols="40" id="metaCustom.remark" name="metaCustom.remark"></textarea>
				</tr>
			</table>
		</form>
		
		<%-- <form action="<%=request.getContextPath()%>/license/importLicense" method="post" name="license_form" id="license_form" enctype='multipart/form-data' style="margin-left:30px;">
		</form> --%>
		
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

	var format = '<a href="javascript:void(0)" onclick="showEidt('
				+ index
				+ ')">编辑</a>&nbsp;<a href="javascript:void(0)" onclick="removeAd('
				+ row.id
				+ ')">删除</a>&nbsp;<a href="${ctx}/settingMeta/metaCustomFieldList/'+row.id+'">字段</a>&nbsp;<a href="${ctx}/settingMeta/metaBusiList/'+row.id+'">表单列表</a>';
		return format;
	}

	function showDiv() {
		$('#showDiv').dialog('open').dialog('setTitle', '添加元数据');
		$("#showDiv input").val("");
		$("#showDiv select").val("-1");
		$("#showDiv textarea").val("");
	}
	//添加
	function addAd() {
		jQuery.ajax({
			url : '${ctx}/settingMeta/saveMetaCustom',
			type : 'post',
			data : $('#metaAddForm').serialize(),
			dataType : 'json',
			success : function(msg) {
				var obj = eval(msg);
				if (obj.status == 'ok') {
					$('#showDiv').dialog('close');
					$('#metaCustomTable').datagrid('reload'); // reload the user data
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
		var row = $('#metaCustomTable').datagrid('getRows')[index];
		if (row) {
			$('#showEditDiv').dialog('open').dialog('setTitle', '编辑元数据');
			document.getElementById("metaCustom.id").value = row.id;
			document.getElementById("metaCustom.metaname").value = row.metaname;
			document.getElementById("metaCustom.metacode").value = row.metacode;
			document.getElementById("metaCustom.remark").value = row.remark;
		}
	}
	//编辑
	function edit(obj) {
		jQuery.ajax({
			url : '${ctx}/settingMeta/editMetaCustom',
			type : 'post',
			data : $('#metaEditForm').serialize(),
			dataType : 'json',
			success : function(msg) {
				var obj = eval(msg);
				if (obj.status == 'ok') {
					$('#showEditDiv').dialog('close');
					$('#metaCustomTable').datagrid('reload'); // reload the user data
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

	function removeAd(id) {
		if (id != "") {
			$.messager.confirm('删除', '确定要删除吗?', function(r) {
				if (r) {
					jQuery.ajax({
						url : '${ctx}/settingMeta/delMetaCustom/' + id,
						type : 'post',
						dataType : 'json',
						success : function(msg) {
							var obj = eval(msg);
							if (obj.status == 'ok') {
								$('#metaCustomTable').datagrid('reload'); // reload the user data
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
</script>
</html>