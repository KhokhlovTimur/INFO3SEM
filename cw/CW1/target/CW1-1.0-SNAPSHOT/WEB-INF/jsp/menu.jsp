<%@ page import="models.Food" %>
<%@ page import="java.util.List" %>
<%@ page import="holder.LoginsHolder" %><%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 13.10.2022
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Food> foods;
    foods = LoginsHolder.foodRep.findAll();
%>
<form action="/basket" method="post">
    <table border="2" width="30%" style="float: left;">
        <tr>
            <td><strong>Food</strong></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
            <td><strong>id</strong></td>
            <td><strong>name</strong></td>
            <td><strong>price</strong></td>
        </tr>
        <% int count = 0;
            while (count != foods.size()) {
                Food food = foods.get(count);
        %>

        <colgroup>
            <col span="1">
            <col span="1">
            <col span="3">
        </colgroup>
        <tr>
            <td style="width: 10%">
                <input type="checkbox" name="foods" value="<%=Long.valueOf(food.getId())%>">
            </td>
            <td width="10%"><%= food.getId()%>
            </td>
            <td width="20%"><%= food.getName()%>
            </td>
            <td width="20%"><%= food.getPrice()%>
        </tr>
        <%
                count++;
            }
        %>
    </table>
</form>
</body>
</html>
