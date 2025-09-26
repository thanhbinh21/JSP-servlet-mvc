<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h1>Shopping Cart</h1>

<c:if test="${empty cart.items}">
    <p>Your cart is empty!</p>
</c:if>

<c:if test="${not empty cart.items}">
    <table>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th>Action</th>
        </tr>

        <c:forEach items="${cart.items}" var="item">
            <tr>
                <td>${item.product.model}</td>
                <td>$${item.product.price}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="productId" value="${item.product.id}">
                        <input type="number" name="quantity" value="${item.quantity}" min="1" style="width: 60px;">
                        <input type="submit" value="Update">
                    </form>
                </td>
                <td>$${item.total}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="productId" value="${item.product.id}">
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="3" style="text-align: right;"><strong>Total:</strong></td>
            <td><strong>$${cart.totalCart}</strong></td>
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="action" value="clear">
                    <input type="submit" value="Clear Cart">
                </form>
            </td>
        </tr>
    </table>
</c:if>

<br>
<a href="${pageContext.request.contextPath}/products">Continue Shopping</a>
</body>
</html>