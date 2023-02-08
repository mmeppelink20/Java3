<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Map<String,String> results = (Map<String,String>)request.getAttribute("results");
  if(results == null) {
    results = new HashMap<>();
  }
  String userTemp = results.containsKey("userTemp") ? results.get("userTemp") : "";
  String userTempError = results.containsKey("userTempError") ? results.get("userTempError") : "";

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Temperature Converter</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<header>
    <h1>Temperature Converter</h1>
    <p>Please select the unit of the temperature you're converting</p>
</header>
<div>
    <form action="temp" method="post">
        <input type="text" name="userTemp" id="userTemp" value="<%= userTemp %>" />
        <label for="userTemp">
            <fieldset id="temp-unit-select">
                <input type="radio" name="unit-select" id="celsius" value="C" checked>C</input>
                <input type="radio" name="unit-select" id="fahrenheit" value="F">F</input>
            </fieldset>
        </label>
        <div>
            <p><%= userTempError %></p>
        </div>
        <input type="submit" value="Convert Temperature">
    </form>
    <% if(results.containsKey("convertedTemp")) { %>
    <p> Converted Temp is: <%= results.get("convertedTemp") %> <%= results.get("unit") %> </p>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
