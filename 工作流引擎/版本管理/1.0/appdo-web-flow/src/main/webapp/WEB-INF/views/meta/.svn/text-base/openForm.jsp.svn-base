<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../../../common/common.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>${metaCustom.metaname }</title>
    <link href="${ctx }/static/css/site.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx }/static/sheet/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx }/static/sheet/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
	<script type="text/javascript" src="${ctx }/static/sheet/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/sheet/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/sheet/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx }/static/sheet/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<div class="easyui-layout" style="width: 100%; height: 100%;">
	<div region="north" split="false" border="false" class="st_head">
		<div class="titleTop">
			<span><img class="titleImage" src="${ctx }/static/images/daohang.png">
			</span>
			<span>单据管理->${metaCustom.metaname }</span>
		</div>
	</div>
	<div  region="center" style=" overflow-y: hidden;padding-left:50px;padding-top:10px">
	<form id="submitForm" method="post" style="margin-left:30px;" enctype='multipart/form-data'>
		<div class="container">
			<div class="page-header" align="left">
			  <h1>${metaCustom.metaname }<input type="hidden" name="metaid" value="${metaCustom.id }"><input type="hidden" name="busiId" value="${metaBusi.id }"><input type="hidden" id="id" name="id"></h1>
			</div>
			<p style="text-align: center;">
				<jsp:include page="../myflow/flow_base_jsp/${functionid}.jsp"></jsp:include>
			</p>
		</div>
		<div align="center">
			<input type="button" class="btn btn-primary" value="保存" onclick="addAd();">
			<input type="button" class="btn btn-primary" value="提交" onclick="addSb();">
		</div>
	</form>
	</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		if('${id}'){
			$("#id").val('${id}');
			jQuery.ajax({
				url : '${ctx}/settingMeta/findBusiByBusiKey/${metaCustom.id }/${id}',
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
		}else{
			
		}
		$('.st_head').removeClass('panel-body');
		$('.layout-panel-center').css('top','25px');
		$('.layout-panel-north').css('border','none');
	});
	function addSb(){
		jQuery.ajax({
			url : '${ctx}/settingMeta/submitForm',
			type : 'post',
			data : $('#submitForm').serialize(),
			dataType : 'json',
			success : function(msg){
				var obj = eval(msg);
				if(obj.status == 'ok'){
					$.messager.alert('提示',obj.info,'info');
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
	function addAd(){
		$.ajax({
			url : '${ctx}/settingMeta/saveForm',
			type : 'post',
			data : $('#submitForm').serialize(),
			dataType : 'json',
			success : function(data){
				var obj = eval('(' + data + ')');
				if(obj.status == 'ok'){
					$("#id").val(obj.result);
					$.messager.alert('提示',obj.info,'info');
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
	</script>
</body>
</html>