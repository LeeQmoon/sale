<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/updatePwd.action" method="post">
   旧密码:<input type="password" name = "old_pwd" ><p> <!-- name属性必须与controller中传入得参数名同名 -->
    新密码:<input type="password" name = "new_pwd" ><p>
    <input type="submit" value="确认"> 
    <a href="${pageContext.request.contextPath }/welcomeAndLoginSuccess.jsp"> <input type="button" value="返回"> </a>
</form>
</body>
</html>