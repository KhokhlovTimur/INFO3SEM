<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h1>Чат</h1>
    <form><p><label>
        <input name="idGe"  style="width: 230px; height: 45px; font-size: 20px" placeholder="Сгенерируйте ID...">
    </label></p>
    <button id="btn" style="width: 113px; height: 35px; font-size: 18px" type="submit">Отправить</button>
        <button style="width: 113px; height: 35px; font-size: 18px" type="reset">Сбросить</button>
</form>
<form action="/chat" method="get">
    <p>
        <label><input type="text" style="width: 230px; height: 45px; font-size: 20px" name="id" placeholder="Ввод ID" size="18"/></label></p>
    <p>
        <button style="width: 113px; height: 35px; font-size: 20px" type="submit">Перейти</button>
        <button style="width: 113px; height: 35px; font-size: 20px" type="reset">Сбросить</button>
    </p>
</form>
</body>
</html>
<%--<script>--%>
<%--    {--%>
<%--        document.getElementById("btn").addEventListener("click", () => {--%>
<%--            document.getElementById("input").value += (Math.random() + 1).toString(36).substring(3);--%>
<%--            console.log("random", r);--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>

