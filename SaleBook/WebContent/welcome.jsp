<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
hello....
<form action="${pageContext.request.contextPath }/book/findByAuthor.action" method="post">
  <input type="text" name = "author" >   
  <input type="submit" value="查询">
</form> 
	<input type="button" value="首页">
	<span>分类
	<select id="all">
        <option>人文社科</option>
        <option>小说</option>
        <option>教育</option>
        <option>文艺</option>
        <option>生活</option>
         <option>科技</option>
          <option>童书</option>
          <option>经管</option>
    </select>
    </span>
	<input type="button" value="购物车">
	<input type="button" value="订单管理">
	<a href="${pageContext.request.contextPath }/jsp/user/register.jsp"><input type="button" value="注册"></a>
	<a href="${pageContext.request.contextPath }/jsp/user/login.jsp"><input type="button" value="登录"></a>
</body>
</html>