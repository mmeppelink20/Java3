package meppelink.fun_stuff;

import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SongServlet", value = "/song")
public class SongServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String song = request.getParameter("track");
        request.setAttribute("song", song);
        request.getRequestDispatcher("WEB-INF/funstuff/song.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
