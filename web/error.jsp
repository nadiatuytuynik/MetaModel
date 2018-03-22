<%--
  Created by IntelliJ IDEA.
  User: Nadia
  Date: 11.03.2018
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<h1>Ooops! :(</h1>

<li><%=exception.getClass() %></li>
<li><%=exception.getMessage() %></li>

</body>
</html>
