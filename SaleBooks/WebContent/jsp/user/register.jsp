<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/user/register.action" method="post">
	
    用户名:<input type="text" name = "username" ><p> <!-- name属性必须与controller中传入得参数名同名 -->
    密码:<input type="password" name = "password" ><p>
    性别:<input type="text" name = "sex" ><p>
    <input type="submit" value="注册"> 
     <a href="${pageContext.request.contextPath }/welcome.jsp"> <input type="button" value="返回"> </a>
 
</body>
</html>