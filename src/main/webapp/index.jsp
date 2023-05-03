<%@ page import="meppelink.chapter_5.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = (User) session.getAttribute("user");

%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Yo</title>
</head>
<body>

<h1>
    Table of Contents
</h1>
<h3>
    Chapter 3 and 4
</h3>

<ul>
    <li><a href="add">Adding App</a></li>
    <li><a href="temp">Temperature Converter</a></li>
    <li><a href="bmi">Body Mass Index Calculator</a></li>
    <li><a href="countries">Countries App</a></li>
</ul>

<h3>Chapters 5 and 6</h3>
<ul>
    <% if(user != null && user.getPrivileges().equals("admin")){ %>
    <li><a href="view-users">View All Users</a></li>
    <% } %>
    <% if(user == null) { %>
    <li><a href="signup">Add User</a></li>
    <li><a href="login">Login</a></li>
    <% } else { %>
    <li><a href="profile">View Profile</a></li>
    <li><a href="logout">Logout</a></li>
    <% } %>

</ul>

<h3>
    Funstuff
</h3>
<ul>
    <li><a href="send-message">Send a Text Message App</a></li>
    <li><a href="artist">Spotify App</a></li>
    <li><a href="chat">Chatting App</a></li>
    <li><a href="tictactoe">Tic Tac Toe</a></li>
</ul>
<h3>
    Projects
</h3>
<ul>
    <li><a href="https://github.com/mmeppelink20/2048">2048 desktop app</a></li>
    <li><a href="https://github.com/mmeppelink20/StarWarsPlanetApp">Star Wars Planet desktop app</a></li>
    <li><a href="https://github.com/mmeppelink20/JobApplicationForm">Job Application Form</a></li>
</ul>
<h3>
    Final
</h3>
<ul>
    <li><a href="newsLetter">News Letter</a></li>
</ul>
<h3>
    Socials
</h3>
<ul>
    <li><a href="https://github.com/mmeppelink20">GitHub</a></li>
    <li><a href="https://www.linkedin.com/in/matthew-meppelink-12684220a/">LinkedIn</a></li>

</ul>

</body>
</html>