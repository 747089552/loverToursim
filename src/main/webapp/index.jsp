<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html lang="zh-cn">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<style>
    .form-horizontal{
        text-align: center;
        margin-top: 20%;
    }
</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <script language="javascript" type="text/javascript" src="<%=basePath %>/js/jquery-3.2.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="<%=basePath %>/js/jquery.validate.js"></script>
</head>
<body class="sessions-new" onload="loadFun()">

<form class="form-signin form-horizontal" id="logForm" action="<%=basePath %>/loging" method="post">
    <input type="text" class="form-control" placeholder="用户名"  name="userName" id="userName">

    <div class="form-group">
        <div class="input-group">
            <input type="password" class="form-control" placeholder="密码"  name="userPassword" id="userPassword">
        </div>
    </div>
    <div class="form-group">
        <button type="button" id="loginButton" onclick="login()">登录</button><button type="button" style="margin-left: 50px" id="regist" onclick="regist()">注册</button>
    </div>
</form>
</body>
<script type="text/javascript">
    function loadFun(){
        $("#logForm").validate({
            rules:{
                userName:{
                    required: true,
                },
                userPassword: {
                    required: true
                }
            },
            messages:{
                userName:{
                    required:"用户名不为空",
                },
                userPassword: {
                    required: "密码不为空",
                }
            }
        }).resetForm();
    }

    function login(){

        if(!$("#logForm").valid()){
            return;
        }

        $('#logForm').submit();
    }

</script>
</html>