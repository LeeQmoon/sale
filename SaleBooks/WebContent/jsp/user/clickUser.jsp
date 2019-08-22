<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品查询列表</title>
</head>
<body>
 
	<table width="100%" border=1>
		<tr>
			<td>选择</td>
			<td>商品名称</td>
			<td>商品价格</td>
			<td>生产数量</td>
			<td>商品类别</td>
			<td>商品图片</td>
			<td>商品信息</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${goods_list }" var="item">
			<tr>
				<td>${item.id}<input type="hidden" name="id" value="${item.id}" /></td>
				<td>${item.name }</td>
				<td>${item.price }</td>
				<td>${item.num }</td>
				<td>${item.classInfo.class_name }</td>
				<td><img style="width:110px;height:120px" alt="商品图片"
					src="http://localhost/pic/${item.pic }"></td>
				<td>${item.des }</td>
				<td>
				<a href="${pageContext.request.contextPath }/findGoodsById.action?id=${item.id}">修改</a>
				<a href="${pageContext.request.contextPath }/delGoods.action?id=${item.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div align="center">  
            <font size="2">第  
            ${page.pageNow} 页</font> <a href="queryPages.action?pageNow=1">首页</a>  
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">  
                <a href="queryPages.action?pageNow=${page.pageNow - 1}">上一页</a>  
            </c:when>  
            <c:when test="${page.pageNow - 1 <= 0}">  
                <a href="queryPages.action?pageNow=1">上一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="queryPages.action?pageNow=${page.pageNow}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">  
                <a href="queryPages.action?pageNow=${page.pageNow + 1}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">  
                <a href="queryPages.action?pageNow=${page.totalPageCount}">下一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="queryPages.action?pageNow=${page.pageNow}">尾页</a>  
            </c:when>  
            <c:otherwise>  
                <a href="queryPages.action?pageNow=${page.totalPageCount}">尾页</a>  
            </c:otherwise>  
        </c:choose>  
    </div>  
</body>
</html>
 ———————————————— 
版权声明：本文为CSDN博主「阿木侠」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_36380516/article/details/73606693