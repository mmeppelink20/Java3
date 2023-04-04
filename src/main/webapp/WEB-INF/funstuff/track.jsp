<%@ page import="se.michaelthelin.spotify.model_objects.specification.*" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%
    TrackSimplified[] tracks = (TrackSimplified[]) request.getAttribute("tracks");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Spotify - Tracks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="artist"><strong>Spotify Music App</strong></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
            </ul>
            <form class="d-flex" role="search">
            </form>
        </div>
    </div>
</nav>

<main class="container">
    <input type="button" class="btn btn-success" value="Go back" onclick="history.back()">
    <section class="py-5 text-center container">
        <div class="row py-lg-2">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Tracks</h1>
            </div>
        </div>
    </section>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Artists</th>
            <th scope="col">Duration</th>
            <th scope="col">Explicit</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <% for(TrackSimplified track: tracks) { %>
        <tr>
            <td>
                <%= track.getTrackNumber() %>
            </td>
            <td>
                <a href="song?track=<%= track.getId() %>"> <%= track.getName() %> </a>
            </td>

            <td>
                <% for(ArtistSimplified artist : track.getArtists() ) { %>
                <%= artist.getName() %><br />
                <% } %>
            </td>
            <td><%= String.format("%02d:%02d:%02d",  TimeUnit.MILLISECONDS.toHours(track.getDurationMs()),
                    TimeUnit.MILLISECONDS.toMinutes(track.getDurationMs()) % TimeUnit.HOURS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(track.getDurationMs()) % TimeUnit.MINUTES.toSeconds(1)) %></td>
            <td><%= track.getIsExplicit() ? "Explicit" : "" %></td>
        <% } %>
        </tr>
        </tbody>
    </table>

</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>