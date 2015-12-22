<%@ page import="java.util.Properties" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %> --%>
 
<%
    request.setAttribute("ctx", request.getContextPath());
%> 
<LINK href="${ctx}/static/images/favicon.ico" type="image/x-icon" rel=icon>
<script type="text/javascript">
var ctx="${ctx}";
</script>
<c:choose>
	<c:when test="${logoInfo==null || logoInfo.theme==null || logoInfo.theme=='' }">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/default/easyui.css">
		<style>.st_head{background:#4A84BB;}.inf{color: #95B8E7;}</style>
	</c:when>
	<c:otherwise>
		<c:if test="${logoInfo.theme==1 }">
			<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/default/easyui.css">
			<style>.st_head{background:#4A84BB;}.inf{color: #95B8E7;}</style>
		</c:if>
		<c:if test="${logoInfo.theme==2 }">
			<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/gray/easyui.css">
			<style>.st_head{background:#0086CD;}.inf{color: #D3D3D3;}</style>
		</c:if>
		<c:if test="${logoInfo.theme==3 }">
			<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/metro/easyui.css">
			<style>.st_head{background:#4A84BB;}</style>
		</c:if>
		<c:if test="${logoInfo.theme==4 }">
			<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/black/easyui.css">
			<style>.st_head{background:url(${ctx}/static/js/easyui/themes/black/images/black.png) repeat-x;}</style>
		</c:if>
		<c:if test="${logoInfo.theme==5 }">
			<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/deepblue/easyui.css">
			<style>.st_head{background:url(${ctx}/static/js/easyui/themes/deepblue/images/deepblue.png) repeat-x;}</style>
		</c:if>
	</c:otherwise>
</c:choose>
<link href="${ctx}/static/csss/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/csss/index.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/static/csss/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/default.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/easyui/themes/color.css">
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/validatebox.js?v=1"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js?v=21"></script>
<script type="text/javascript" src="${ctx}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/static/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${ctx}/static/js/json2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${ctx}/static/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.md5.js"></script>
<link href="${ctx}/static/editor/themes/default/default.css" rel="stylesheet" />
<script src="${ctx}/static/editor/kindeditor-min.js"></script>
<script src="${ctx}/static/editor/lang/zh_CN.js"></script>
    
<script type="text/javascript">
	$(document).ready(function(){
		$(".req_span").html("*");
		$(".req_span1").html(" &nbsp;");
	});
	
	$.ajaxSetup({
	    cache: false, //close AJAX cache  
	    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
	    complete:function(XHR,textStatus){
	        var resText = XHR.responseText;
	        if(resText=='ajaxSessionTimeOut'){
	            sessionTimeOut();
	        }
	    }   
	});
	function sessionTimeOut(){
	    window.top.location.href = ctx ;  
	}
	String.prototype.replaceAll = function(s1,s2){
		if(Object.prototype.toString.call(this) == "[object String]"){
			return this.replace(new RegExp(s1,"gm"),s2);
		}
	}
	//å»é¤å­ç¬¦ä¸²ä¸­çç©ºæ ¼
	String.prototype.repSpace = function(){
		if(Object.prototype.toString.call(this) == "[object String]"){
			return this.replaceAll(" ","");
		}
	}
	
	function isRepeat(arr){
	    var hash = {};
	    for(var i in arr) {
	        if(hash[arr[i]])
	             return true;
	        hash[arr[i]] = true;
	    }
	    return false;
	}
	
    (function ($, K) {
		if (!K)
			throw "KindEditor未定义!";

		function create(target) {
			var opts = $.data(target, 'kindeditor').options;
			var editor = K.create(target, opts);
			$.data(target, 'kindeditor').options.editor = editor;
		}

		$.fn.kindeditor = function (options, param) {
			if (typeof options == 'string') {
				var method = $.fn.kindeditor.methods[options];
				if (method) {
					return method(this, param);
				}
			}
			options = options || {};
			return this.each(function () {
				var state = $.data(this, 'kindeditor');
				if (state) {
					$.extend(state.options, options);
				} else {
					state = $.data(this, 'kindeditor', {
							options : $.extend({}, $.fn.kindeditor.defaults, $.fn.kindeditor.parseOptions(this), options)
						});
				}
				create(this);
			});
		}

		$.fn.kindeditor.parseOptions = function (target) {
			return $.extend({}, $.parser.parseOptions(target, []));
		};

		$.fn.kindeditor.methods = {
			editor : function (jq) {
				return $.data(jq[0], 'kindeditor').options.editor;
			}
		};

		$.fn.kindeditor.defaults = {
			resizeType :2,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [
	    				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	    				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	    				'insertunorderedlist', '|', 'emoticons', 'image','media', 'link'],
			afterChange:function(){
				this.sync();//这个是必须的,如果你要覆盖afterChange事件的话,请记得最好把这句加上.
			}
		};
		$.parser.plugins.push("kindeditor");
	})(jQuery, KindEditor);
</script>
<style>
	.lb_lf1{margin-left:12px;}
	.lb_lf2{margin-left:17px;}
	.lb_lf3{margin-left:24px;}
	.lb_lf4{margin-left:27px;}
	.lb_lf5{margin-left:32px;}
	.lb_lf6{margin-left:37px;}
	.lb_search{height:30px;padding-top:5px;}
	.txt{height:18px;width:200px;}
	.searchtxt{width:120px;}
	.selecttxt{width:120px;height:24px;}	
	.mrg_tp{margin-top:5px;}	
	.req_span{color:#FF0000;}
	.req_span1{width:10px;}
</style>