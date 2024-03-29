<%@ page import="java.time.*" %> <%-- this is a directive --%>
<%@ page import="java.time.format.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    ZonedDateTime today = ZonedDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    String currentDate = today.format(dateFormatter);
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
    String currentTime = today.format(timeFormatter);
    // cannot use if statements in declarations, this is a declaration.
%>
<%
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
    String firstNumber = results.containsKey("firstNumber") ? results.get("firstNumber") : "";
    String secondNumber = results.containsKey("secondNumber") ? results.get("secondNumber") : "";
    String num1Error = results.containsKey("num1Error") ? results.get("num1Error") : "";
    String num2Error = results.containsKey("num2Error") ? results.get("num2Error") : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>The Adding App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-3">
            <h1>Add</h1>
            <p class="lead">Enter two numbers and press submit to calculate the sum.</p>
            <%--the action attribute must be the path to the Servlet--%>
            <form method="POST" action="add">
                <div class="form-group mb-2">
                    <label for="firstNumber">First Number:</label>
                    <input type="text" class="form-control" id="firstNumber" name="firstNumber" value="<%= firstNumber%>">
                </div>
                <div class="form-group mb-2">
                    <label for="secondNumber">Second Number:</label>
                    <input type="text" class="form-control" id="secondNumber" name="secondNumber" value="<%= secondNumber%>">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div <%-- End of the column --%>
    </div> <%-- End of the row --%>
    <% if(results.containsKey("sum")) { %> <%-- Scriplet --%>
    <p>
        <%= results.get("firstNumber")%> + <%= results.get("secondNumber")%> = <%= results.get("sum")%>
    </p>
    <% } else { %>
    <p> <%-- Expressions are meant to print things, these are am example of an expression --%>
        <%= currentDate %>
        <%= currentTime %>
    </p>
    <% } %>

</div> <%--End of the container--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
