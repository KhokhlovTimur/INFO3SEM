<%@ page import="models.Order" %>
<%@ page import="holder.LoginsHolder" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 13.10.2022
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Order> foods;

    foods = LoginsHolder.orderRepository.findAll();

%>
<form action="/basket" method="post">
    <table border="2" width="30%" style="float: left;">
        <tr>
            <td><strong>Food</strong></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
            <td><strong>food_id</strong></td>
            <td><strong>name</strong></td>
        </tr>
        <% int count = 0;
            while (count != foods.size()) {
                Order food = foods.get(count);
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
            <td width="20%"><%= food.getFoodId()%>
            </td>
            <td width="20%"><%= food.getFood_name()%>
        </tr>
        <%
                count++;
            }
        %>
    </table>
</form>
</body>
</html>
