<%@ page import="meppelink.chapter_5.User" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 4/17/2023
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
    User user = (User) request.getSession().getAttribute("user");
%>--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${user.first_name} ${user.last_name} | User Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>${user.first_name} ${user.last_name}</h1>

    <p>${user.email}
    <form action="profile" method="POST"">
    <input type="hidden" name="name" value="testEmail">
    <button class="btn btn-warning" type="submit">Test</button>
    </form>
    ${emailSentMsg}
    </p>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>