<%@ page import="java.util.List" %>
<%@ page import="meppelink.fun_stuff.Country" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Country> countries = (List<Country>)request.getAttribute("countries");
%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Countries of the World</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<header>
  <h1>Where in the world?</h1>
</header>
<div class="container my-4">
  <div class="row">
    <% for(Country country: countries) { %>
    <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
      <h3><%= country.getName() %></h3>
      <p><%= country.getContinent() %></p>
      <p><%= country.getPopulation() %></p>
    </div>
    <% } %>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>