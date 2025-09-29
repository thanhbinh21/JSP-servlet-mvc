<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Thêm tin tức</title>
</head>
<body>
<h2>Thêm tin tức mới</h2>

<form action="${pageContext.request.contextPath}/addTinTuc" method="post">
    <p>Tiêu đề: <input type="text" name="tieude" required></p>
    <p>Nội dung: <textarea name="noidung" required></textarea></p>
    <p>Liên kết: <input type="url" name="lienket" required></p>
    <p>
        Danh mục:
        <select name="madm">
            <c:forEach var="dm" items="${listdm}">
                <option value="${dm.id}">${dm.name}</option>
            </c:forEach>
        </select>
    </p>
    <input type="submit" value="Thêm">
</form>

<p style="color:red">${error}</p>
<a href="${pageContext.request.contextPath}/listTinTuc">Quay lại</a>
</body>
</html>
