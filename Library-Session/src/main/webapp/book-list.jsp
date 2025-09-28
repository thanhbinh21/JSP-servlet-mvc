<%--
  Created by IntelliJ IDEA.
  User: binhcode
  Date: 28/09/2025
  Time: 00:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>
    <title>Book List</title>
    <style>
        .book{
            border:1px solid black;
            margin: 10px;
            padding: 10px;
            border-radius: 2px;
            display: flex;
        }
        .book img{
            height: 50px;
        }
    </style>
</head>
<body>
<h1>BookStore</h1>
<a href="${pageContext.request.contextPath}/cart">View Cart</a>
<c:forEach items="${books}" var="book">



    <div class="book">
        <h2>${book.title}</h2>
        <img src="${pageContext.request.contextPath}/images/${book.img}">
        <p>Description: ${book.des}</p>

        <form action="cart" method="post">
            <input type="hidden" name="action" value="add"/>
            <input type="hidden" name="ID" value="${book.id}"/>
            <button type="submit">Add to Cart</button>
        </form>

        <a href="${pageContext.request.contextPath}/books?id=${book.id}">View Details</a>


    </div>

</c:forEach>
</body>
</html>
