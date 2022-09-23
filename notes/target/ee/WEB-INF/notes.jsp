<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 23.09.2022
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post"><input type="text" name="message"><button type="submit">ok</button></form>
<% List<String> messages = (List<String>) request.getAttribute("history");
    for (String mess: messages) {  %>
        <%=mess%>
  <%  } %>

</body>
</html>
