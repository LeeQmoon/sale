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
        <td>序号</td>
        <td>姓名</td>
        <td>学号</td>
        <td>创建时间</td>
        <td>修改时间</td>
    </tr>
    <c:if test="${list!= null || fn:length(list) != 0}">
        <c:forEach items="${list}" var="book" begin="0" end="${fn:length(list) }">
            <tr>
                <td>${book.bId}</td>
                <td>${book.bName}</td>
                <td>${book.author}</td>
                    <%--<td>${user.create_at}</td>--%>
                    <%--<td>${user.update_at}</td>--%>
            </tr>
        </c:forEach>
    </c:if>
</table>
<table>
    <tr items="${pageinfo}">
        <form method="GET" action="${pageContext.request.contextPath}/book/findByCategory.action">
            <td><input type="hidden" name="page" value="${pageinfo.getPrePage()}"></td>
            <td><input type="submit" value="上一页"></td>
        </form>
        <td>当前：第${pageinfo.getPrePage()}页<--></td>
        <td>共：${pageinfo.getTotal()}页</td>
        <form method="GET" action="${pageContext.request.contextPath}/book/findByCategory.action">
            <td><input type="hidden" name="page" value="${pageinfo.getNextPage()}"></td>
            <td><input type="submit" value="下一页"></td>
        </form>
        
    </tr>
</table>
<form method="GET" action="${pageContext.request.contextPath}/book/findByCategory.action">
    <table>
        <tr>
            <td>跳转到第:<input type="text" name="page" size="2"/>页</td>
            <td><input type="submit" value="确定"></td>
        </tr>
    </table>
</form>
</body>
</html>