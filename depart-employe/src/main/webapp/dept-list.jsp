<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Departments</h2>
<a href="departments?action=add">Add Department</a>
<ul>
    <c:forEach var="d" items="${departments}">
        <li>
                ${d.id} - ${d.name}
            [<a href="departments?action=edit&id=${d.id}">Edit</a>]
            [<a href="departments?action=delete&id=${d.id}">Delete</a>]
            [<a href="employees?action=list&deptId=${d.id}">Employees</a>]
        </li>
    </c:forEach>
</ul>
</body>
</html>
