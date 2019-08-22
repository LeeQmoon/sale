<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@ page import = "org.pojo.*"%>
<%@ page import="com.github.pagehelper.PageInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="../js/jqui/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>三味书屋</title>
</head>
<body>


<table frame="box" rules="all">
    <tr>
        <td>ID</td>
        <td>数量</td>
        <td>价格</td>
        <td>合计</td>
        <td>书ID</td>
        <td>书名</td>
        <td>订单号</td>
    </tr>
    <c:if test="${list!= null || fn:length(list) != 0}">
        <c:forEach items="${list}" var="book" begin="0" end="${fn:length(list) }">
            <tr>
                <td>${book.itemId}</td>
                <td>${book.quantity}</td>
                <td>${book.price}</td>
           		<td>${book.subTotal}</td>
           		<td>${book.bId}</td>
           		<td>${book.bName}</td>
           		<td>${book.orderId}</td>        		
            </tr>
        </c:forEach>
    </c:if>
</table>
 <form name="myForm" action="${pageContext.request.contextPath }/order/findAllOrder.action" method="post">
 					<input type="text" name = "bid">   
 					<input type="submit" value="订单号">
				</form> 
<a href="${pageContext.request.contextPath }/cart/delete.action"><input type="button" value="删除"></a>

</body>
</html>