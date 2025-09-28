<html>
<body>
<h2>Edit Department</h2>
<form action="departments" method="post">
    <input type="hidden" name="id" value="${department.id}" />
    Name: <input type="text" name="name" value="${department.name}" />
    <input type="submit" value="Update" />
</form>
</body>
</html>
