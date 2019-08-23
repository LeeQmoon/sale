<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>三味书屋</title>

 <style type='text/css'> 
        #code{ 
            font-family:Arial,宋体; 
            font-style:italic; 
            color:blue;
            size:12px;			
            border:0; 
            padding:2px 3px; 
            letter-spacing:8px; 
            font-weight:bolder; 
        } 
</style> 
</head>

<body>

<table border=0><tr><th width=200 height=30></th><th ><img src="${pageContext.request.contextPath }/images/3.jpg"></th></tr></table>
<table border=0><tr><th width=700 height=400></th><th>

<form action="${pageContext.request.contextPath }/user/register.action" method="post">
<table border=0 height=400 >
<tr><th height=65><font  size='4'>用 户 名</th><th><input type="text" style="height:40px" placeholder="请输入用户名" size=40 name="username"></th></tr>
<tr><th height=65><font  size='4'>密    码</th><th><input type="password" style="height:40px" placeholder="请输入密码" size=40 name="password"></th></tr>
<tr><th height=65><font  size='4'>性    别</th><th><input type="text" style="height:40px" placeholder="请输入性别" size=40 name="sex"></th></tr>

<tr><th colspan=2 height=65><input type='submit' style="background-color:red;height:55px;width:160px;font-size:25px;color:white;border:none" 
value='注册'  >

<td colspan=2 height=65><a href="${pageContext.request.contextPath }/jsp/user/login.jsp"> <input type='button' style="background-color:red;height:55px;width:160px;font-size:25px;color:white;border:none" 
value='返回'  ></td>
</th></tr>

</table>
</form></th></tr></table>
<table><tr><th bgcolor='f9f8ed' height=200 width=2000><p>📧联系邮箱：xxx@qq.com</p><p>联系地址：三味书屋</p><p>📞联系电话：xxxx</p></th></tr></table></body>

</body>
</html>