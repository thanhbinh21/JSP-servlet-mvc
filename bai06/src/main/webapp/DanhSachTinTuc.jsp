<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Danh sách tin tức</title></head>
<body>
<h2>Danh mục</h2>
<c:forEach var="dm" items="${listDanhMuc}">
    <a href="${pageContext.request.contextPath}/listTinTuc?madm=${dm.id}">${dm.name}</a> |
</c:forEach>

<h2>Tin tức</h2>
<ul>
    <c:forEach var="tt" items="${listTinTuc}">
        <li>
                ${tt.title} -
            <a href="${tt.lienKet}" target="_blank">Xem</a> |
            <a href="${pageContext.request.contextPath}/deleteTinTuc?id=${tt.id}&madm=${tt.maDM}"
               onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
        </li>
    </c:forEach>
</ul>

<a href="${pageContext.request.contextPath}/addTinTuc">➕ Thêm tin tức</a>
</body>
</html>
