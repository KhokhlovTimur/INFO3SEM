<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h1></h1>
<form><p><label>
    <input name="login"  style="width: 230px; height: 45px; font-size: 20px" placeholder="Логин">
    <%
        System.out.println(request.getParameter("login")+32);%>
</label></p>
    <button id="btn" style="width: 113px; height: 35px; font-size: 18px" type="submit">Отправить</button>
    <button style="width: 113px; height: 35px; font-size: 18px" type="reset">Сбросить</button>
</form>
<form action="${pageContext.request.contextPath}/note" method="get">
    <p>
        <label><input type="text" style="width: 230px; height: 45px; font-size: 20px" name="password" placeholder="Пароль" size="18"/></label></p>
    <p>
        <button style="width: 113px; height: 35px; font-size: 20px" type="submit">Перейти</button>
        <button style="width: 113px; height: 35px; font-size: 20px" type="reset">Сбросить</button>
    </p>
</form>
</body>
</html>

