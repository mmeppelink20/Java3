<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String,String> results = (Map<String,String>)request.getAttribute("results");
    if(results == null) {
        results = new HashMap<>();
    }
    String phone = results.containsKey("phone") ? results.get("phone") : "";
    String message = results.containsKey("message") ? results.get("message") : "";
    String messageError = results.containsKey("messageError") ? results.get("messageError") : "";
    String messageSuccess = results.containsKey("messageSuccess") ? results.get("messageSuccess") : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Messaging App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-md-6">
            <h2>Send a message or call</h2>
            <p class="lead">Enter a phone number to send them a call or text</p>
            <select class="form-select w-35 mb-4" id="call-text-select">
                <option>Text or Call</option>
                <option value="message">Text</option>
                <option value="voice">Call</option>
            </select>
            <form action="send-message" method="post" id="send-message" class="form">
                <div class="form-group mb-2">
                    <label for="phone">Phone number</label>
                    <input type="text" id="phone" name="phone" class="form-control" value="<%= phone %>">
                </div>
                <div class="form-group mb-2">
                    <label for="message">Message</label>
                    <textarea id="message" name="message" class="form-control" rows="3"><%= message %></textarea>
                </div>
                <input type="submit" value="Send" class="btn btn-primary mb-2">
            </form>

            <form action="send-voice" method="post" id="send-voice" class="form">
                <div class="form-group mb-2">
                    <label for="phoneCall">Phone number</label>
                    <input type="text" id="phoneCall" name="phone" class="form-control" value="<%= phone %>">
                </div>
                <div class="form-group mb-2">
                    <label for="messageCall">Message</label>
                    <textarea id="messageCall" name="message" class="form-control" rows="3"><%= message %></textarea>
                </div>
                <input type="submit" value="Send" class="btn btn-primary mb-2">
            </form>
            <% if(!messageError.equals("")) { %>
            <div class="alert alert-danger" role="alert">
                <%= messageError %>
            </div>
            <% } %>
            <% if(!messageSuccess.equals("")) { %>
            <div class="alert alert-success" role="alert">
                <%= messageSuccess %>
            </div>
            <% } %>
        </div>
    </div>
</div>

<style>
    .form {
        display: none;
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $('#call-text-select').on('change', function(){
            var demovalue = $(this).val();
            $("form.form").hide();
            $("#send-"+demovalue).show();
        });
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>