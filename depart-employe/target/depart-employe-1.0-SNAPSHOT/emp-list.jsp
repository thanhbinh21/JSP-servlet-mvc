<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Employees of Department ${department.name}</h2>
<ul>
    <c:forEach var="e" items="${employees}">
        <li>${e.id} - ${e.name} - ${e.salary}</li>
    </c:forEach>
</ul>
<a href="departments">Back to Departments</a>
</body>
</html>
