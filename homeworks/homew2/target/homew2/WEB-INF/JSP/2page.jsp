<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
</head>
<body>
<h1><%="ID чата: " +  request.getParameter("id") %></h1>
<form  method="post"><button style="width: 113px; height: 35px; font-size: 20px">Обновить</button></form>
<form method="post">
    <label><input name="value" style="width: 230px; height: 45px; font-size: 20px" placeholder="Напишите сообщение..." ></label>
    <button style="width: 113px; height: 45px; font-size: 20px">Отправить</button>
</form>
<ul>
    <%List<String> entries = (List<String>) request.getAttribute("history"); %>
    <% for (String entry : entries) {
    %>
    <li style="font-size: 24px"><%=entry %></li>
    <% } %>
</ul>
<form action="${pageContext.request.contextPath}/" method="get"><button style="left: 50%; position: absolute; bottom: 0%;">Назад</button></form>
</body>
</html>
