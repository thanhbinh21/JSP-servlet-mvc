<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Detail</title>
</head>
<body>
<h1>Product Detail</h1>

<c:if test="${not empty product}">
    <h2>${product.model}</h2>
    <c:if test="${not empty product.imgUrl}">
        <img src="${pageContext.request.contextPath}/images/${product.imgUrl}"
             alt="${product.model}" width="300">
    </c:if>
    <p><strong>ID:</strong> ${product.id}</p>
    <p><strong>Description:</strong> ${product.description}</p>
    <p><strong>Price:</strong> $${product.price}</p>
    <p><strong>In Stock:</strong> ${product.quantity}</p>

    <form action="${pageContext.request.contextPath}/cart" method="post">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="id" value="${product.id}">
        Quantity: <input type="number" name="quantity" value="1" min="1" max="${product.quantity}">
        <input type="submit" value="Add to Cart">
    </form>
</c:if>

<c:if test="${empty product}">
    <p>Product not found!</p>
</c:if>

<br>
<a href="${pageContext.request.contextPath}/products">Back to Product List</a>
</body>
</html>