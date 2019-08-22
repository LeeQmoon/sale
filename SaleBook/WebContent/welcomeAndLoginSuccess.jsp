<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	嘿嘿.....
	<a href="${pageContext.request.contextPath }/book/findAll.action"><input type="button" value="首页"></a>
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
	<a href="${pageContext.request.contextPath }/cart/myCart.action"><input type="button" value="购物车"></a>
	<input type="button" value="订单管理">
  	<a href="${pageContext.request.contextPath }/order/addOrder.action"> <input type="button" value="个人信息-更改密码"> </a>
  	<a href="${pageContext.request.contextPath }/welcome.jsp"> <input type="button" value="退出"> </a>
</body>
</html>