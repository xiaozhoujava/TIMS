﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欢迎登录后台管理系统--我爱模板网 www.5imoban.net</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <ul>
    <li><input name="" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
    <li><input name="" type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
    <li><input name="" type="button" class="loginbtn" value="登录"  onclick="javascript:window.location='main.jsp'"  /></li>
    </ul>
    
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm"></div>
</body>
</html>
