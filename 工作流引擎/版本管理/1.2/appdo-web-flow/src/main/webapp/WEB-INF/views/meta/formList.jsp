<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试功能列表</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
	<c:forEach items="${listMeta }" var="meta">
		<br>
		<a href="../myflow/flow_base_jsp/${meta.busipath }/${meta.isapp }/${meta.functionid }">${meta.metaname }</a>
	</c:forEach>
</body>
</html>