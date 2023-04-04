package meppelink.fun_stuff;

import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AlbumServlet", value = "/album")
public class AlbumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("artist");


        AlbumSimplified[] albums = MySpotify.getArtistsAlbums(q);
        Artist artist = MySpotify.getArtistByArtistID(q); // for artist

        request.setAttribute("albums", albums);
        request.setAttribute("artist", artist); // for artist

        request.getRequestDispatcher("WEB-INF/funstuff/album.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
