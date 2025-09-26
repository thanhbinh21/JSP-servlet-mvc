<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
    <style>
        .product { border: 1px solid #ccc; margin: 10px; padding: 10px; width: 250px; float: left; }
        .product img { max-width: 200px; max-height: 200px; }
    </style>
</head>
<body>
<h1>Product List</h1>
<a href="${pageContext.request.contextPath}/cart">View Cart</a>
<hr>

<c:forEach items="${products}" var="p">
    <div class="product">
        <h3>${p.model}</h3>
        <c:if test="${not empty p.imgUrl}">
            <img src="${pageContext.request.contextPath}/images/${p.imgUrl}"
                 alt="${p.model}" onerror="this.style.display='none'">
        </c:if>
        <p><strong>Description:</strong> ${p.description}</p>
        <p><strong>Price:</strong> $${p.price}</p>
        <p><strong>In Stock:</strong> ${p.quantity}</p>

        <form action="${pageContext.request.contextPath}/cart" method="post">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="id" value="${p.id}">
            <input type="submit" value="Add to Cart">
        </form>

        <a href="${pageContext.request.contextPath}/products?id=${p.id}">View Details</a>
    </div>
</c:forEach>
</body>
</html>