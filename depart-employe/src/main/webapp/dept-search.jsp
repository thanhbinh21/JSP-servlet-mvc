<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Search Departments</h2>
<form action="departments" method="get">
    <input type="hidden" name="action" value="search" />
    <input type="text" name="keyword" placeholder="Enter name..." />
    <input type="submit" value="Search" />
</form>
<hr/>
<ul>
    <c:forEach var="d" items="${departments}">
        <li>${d.id} - ${d.name}</li>
    </c:forEach>
</ul>
</body>
</html>
