<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../../common/taglibs.jsp"%>
<%@ include file="../../../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请表</title>

<link rel="stylesheet" href="${ctx }/static/sheet/bootstrap.min.css">
<link rel="stylesheet"
	href="${ctx }/static/sheet/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript"
	src="${ctx }/static/sheet/jquery-2.1.4.min.js"></script>

<script type="text/javascript"
	src="${ctx }/static/sheet/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${ctx }/static/sheet/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>

<script type="text/javascript"
	src="${ctx }/static/js/flow_js/flow_base_js/LeaveRequest.js"></script>
<script type="text/javascript"
	src="${ctx }/static/js/easyui/jquery.easyui.min.js"></script>

<style>
h2 {
	text-align: center;
}

.main {
	width: 750px;
	border: 2px #000 solid;
	border-collapse: collapse;
	font-size: 14px;
	font-family: "微软雅黑";
	text-align: center;
	margin: 0 auto;
}

input {
	border: none;
	font-size: 14px;
	outline: none;
	text-align: center;
	font-family: "微软雅黑";
}

.main td {
	border: 1px #000 solid;
	height: 40px;
	width: 16%;
}

.main tr {
	width: 100%;
}
/* .main td input,.main td label{
		float: left;
		margin-left: 10px;
		margin-top: 10px;
	} */
.font0 {
	font-size: 0;
}

.font0 input, .font0 label {
	font-size: 14px;
	margin-right: 10px;
	vertical-align: middle;
}

.leavedate input {
	width: 35px;
	border: none;
	border-bottom: 1px solid #000;
	outline: none;
	text-align: right;
}

input[type=checkbox] {
	margin-top: -4px;
}

#otherreason {
	width: 40px;
	border: none;
	border-bottom: 1px solid #000;
	outline: none;
	text-align: left;
}

.leavetxt {
	width: 95%;
	height: 90%;
	resize: none;
	border: none;
	outline: none;
	font-family:"微软雅黑";
	font-size: 14px;
}
</style>
</head>
<body style="text-align: center;">

	<b><h2>请假申请表</h2></b>
	<form id="submitForm" method="post" style="margin-left: 30px;"
		enctype='multipart/form-data'>
		<input type="hidden" name="metaid" id="metaid" value="${metaCustom.id }">
		<input type="hidden" name="busiId" id="busiId" value="${metaBusi.id }"> 
		<input type="hidden" id="id" name="id" value="${id}">
		<table class="main">
			<tr>
				<td bgcolor="#CCCCCC">填表日期</td>
				<td><input name="input_date" type="text" readonly="readonly"
					title="填表日期" value="${userInfo.loginTime}" qplugins="text"
					orghide="0" orgalign="left" orgwidth="150"
					data-options="validType:&#39;length&#39;"
					class="easyui-validatebox" style="width: 100%"></td>
				<td style="width: 18%" bgcolor="#CCCCCC">部门</td>
				<td><input name="dept" rows="3" class="easyui-validatebox"
					style="width: 97%" title="部门" readonly="readonly" qplugins="text"
					orghide="0" value="${userInfo.deptName}" orgalign="left"
					orgwidth="150" data-options="validType:'length'" /></td>
				<td bgcolor="#CCCCCC">申请人</td>
				<td><input name="user_info" type="text" title="申请人"
					value="${userInfo.userName}" qplugins="text" readonly="readonly"
					orghide="0" orgalign="left" orgwidth="150"
					data-options="validType:&#39;length&#39;"
					class="easyui-validatebox" style="width: 100%"></td>
			</tr>
			<tr>
				<td bgcolor="#CCCCCC">请假日期</td>
				<td colspan="5" class="leavedate"><input id="begin_date"
					name="begin_date" readonly="readonly" type="text"
					placeholder="年/月/日/时/分" style="width: 120px"> 至 <input
					id="end_date" name="end_date" type="text" placeholder="年/月/日/时/分"
					readonly="readonly" style="width: 120px"> 共 <input
					id="countday" name="countday" type="text" readonly="readonly"
					style="width: 30px">天 <input id="counthour"
					name="counthour" type="text" readonly="readonly"
					style="width: 30px">时</td>
			</tr>
			<tr>
				<td bgcolor="#CCCCCC">请假类型</td>
				<td colspan="5" class="font0">
					<input type="radio" name="oper_type" value="事假"> <label>事假</label> 
					<input type="radio" name="oper_type" value="病假"> <label>病假</label>
					<input type="radio" name="oper_type" value="婚假"> <label>婚假</label>
					<input type="radio" name="oper_type" value="丧假"> <label>丧假</label>
					<input type="radio" name="oper_type" value="年假"> <label>年假</label>
					<input type="radio" name="oper_type" value="产假"> <label>产假</label>
					<input type="radio" name="oper_type" value="其他"> <label>其他</label>
				</td>
			</tr>
			<tr>
				<td style="height: 90px" bgcolor="#CCCCCC">请假事由</td>
				<td colspan="5"><textarea class="leavetxt" title="请假事由"
						name="reason_info" qplugins="textarea" value="" orgrich="0"
						orgfontsize="" orgwidth="300" orgheight="80"></textarea></td>
			</tr>
			<tr>
				<td style="height: 70px" bgcolor="#CCCCCC">部门领导意见</td>
				<td colspan="2"><input name="dept_opinion" type="text"
					readonly="readonly" title="部门领导意见" value="" qplugins="text"
					orghide="0" orgalign="left" orgwidth="150"
					data-options="validType:&#39;length&#39;"
					class="easyui-validatebox" style="width: 97%"></td>
				<td style="height: 70px" bgcolor="#CCCCCC">人力资源部意见</td>
				<td colspan="2"><input name="hr_opinion" type="text"
					readonly="readonly" title="人力资源部意见" value="" qplugins="text"
					orghide="0" orgalign="left" orgwidth="150"
					data-options="validType:&#39;length&#39;"
					class="easyui-validatebox" style="width: 97%"></td>
			</tr>
			<tr>
				<td style="height: 70px" bgcolor="#CCCCCC">直属副总意见</td>
				<td colspan="2"><input name="dgm_opinion" type="text"
					readonly="readonly" title="直属副总意见" value="" qplugins="text"
					orghide="0" orgalign="left" orgwidth="150"
					data-options="validType:&#39;length&#39;"
					class="easyui-validatebox" style="width: 97%"></td>
				<td style="height: 70px" bgcolor="#CCCCCC">总经理批示</td>
				<td colspan="2"><input name="gm_opinion" type="text"
					readonly="readonly" title="总经理批示" value="" qplugins="text"
					orghide="0" orgalign="left" orgwidth="150"
					data-options="validType:&#39;length&#39;"
					class="easyui-validatebox" style="width: 97%"></td>
			</tr>
			<tr>
				<td colspan="6"><b>
						备注：部门主管及以上人员请假3天以上（含3天），由总经理批准；其余人员请假则由直属副总及人力资源部批准. </b></td>
			</tr>
		</table>
		<br /> <br />
		<div align="center">
			<input type="button" class="btn btn-primary" value="保存到草稿箱"
				onclick="addAd();">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
				class="btn btn-primary" value="提交送审" onClick="addSb();">
		</div>
	</form>
</body>
</html>