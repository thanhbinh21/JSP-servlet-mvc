<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<h1>Your Cart</h1>

<c:if test="${empty cart}">
    <p>Your cart is empty.</p>
</c:if>

<c:if test="${not empty cart}">
    <c:forEach var="item" items="${cart.list}">
        <tr>
            <td>${item.book.title}</td>
            <td>${item.book.price}</td>
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="ID" value="${item.book.id}"/>
                    <input type="number" name="QUANTITY" value="${item.qty}" min="1"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
            <td>${item.total}</td>
            <td>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="action" value="remove"/>
                    <input type="hidden" name="ID" value="${item.book.id}"/>
                    <input type="submit" value="Remove"/>
                </form>
            </td>
        </tr>
    </c:forEach>


    <br/>
    <!-- CLEAR -->
    <form action="cart" method="post">
        <input type="hidden" name="action" value="clear"/>
        <input type="submit" value="Clear Cart"/>
    </form>
</c:if>

<br/>
<a href="books">Continue Shopping</a>
</body>
</html>
