<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>Spring MVC表单处理</title>
</head>
<body>
<h2>您拥有以下这些咸鱼：</h2>

<table frame="box" rules="all">
    <tr>
        <td>ID</td>
        <td>书名</td>
        <td>作者</td>
        <td>出版社</td>
        <td>价格</td>
        <td>操作</td>
    </tr>
    <c:if test="${list!= null || fn:length(list) != 0}">
        <c:forEach items="${list}" var="book" begin="0" end="${fn:length(list) }">
            <tr>
                <td>${book.bId}</td>
                <td>${book.bName}</td>
                <td>${book.author}</td>
           		<td>${book.press}</td>
           		<td>${book.price}</td>
           		<td><input type="submit" value="加入购物车">  <input type="button" value="查看详情"></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>