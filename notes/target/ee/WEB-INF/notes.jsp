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

<form method="post">
    <label><input name="message"  placeholder="Напишите сообщение..." ></label>
    <button>Отправить</button>
    <%System.out.println(request.getParameter("login")+100);%>
</form>
<ul>
<% List<String> messages = (List<String>) request.getAttribute("history");%>
 <%   for (String mess: messages) {  %>
       <li><%=mess%></li>
  <%  } %>
</ul>
</body>
</html>
