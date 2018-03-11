<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html lang="zh-cn">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <script language="javascript" type="text/javascript" src="<%=basePath %>/js/jquery-3.2.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="<%=basePath %>/js/query.validate1.js"></script>
</head>
<body class="sessions-new" >
返回的数据：${backData}
</body>
<script type="text/javascript">
</script>
</html>