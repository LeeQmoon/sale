<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/user/login.action" method="post">
    username:<input type="text" name = "username" ><p> <!-- name属性必须与controller中传入得参数名同名 -->
    password:<input type="password" name = "password" ><p>
    <input type="submit" value="登录"> 
   <a href="${pageContext.request.contextPath }/welcome.jsp"> <input type="button" value="返回"> </a>
    
</form> 
 <%-- <div class="login">
       <form action="${pageContext.request.contextPath }/user/login.action" method="post">
        <div class="login-title">登录会员<span><a href="javascript:void(0);" class="close-login">关闭</a></span></div>
        <div class="login-input-content">
        
            <div class="login-input">
                <label>用&nbsp;户&nbsp;&nbsp;名：</label>
                <input type="text" placeholder="请输入用户名"  name="username" id="username" class="list-input"/>
            </div>
            <div class="login-input">
                <label>登录密码：</label>
                <input type="password" placeholder="请输入登录密码" name="password" id="password" class="list-input"/>
            </div>
        </div>
        <div class="control-group">
            <div class="controls" >
                <button class="btn btn-success" style="width:120px;" >确认</button>           
            </div>
        </div>
       </form>
    </div> --%>
</body>
</html>
