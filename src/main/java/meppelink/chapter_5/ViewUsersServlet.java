package meppelink.chapter_5;

import meppelink.data_access.UserDAO_MySQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ViewUsersServlet", value = "/view-users")
public class ViewUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // add this code in the doGet method of any servlet
        // that you want users to log in first before viewing
        HttpSession session = request.getSession(); // get the session
        System.out.println("Session ID: " + session.getId());
        System.out.println("Max inactive interval: " + session.getMaxInactiveInterval());
        if(session.getAttribute("user") == null) { // if the user is not logged in
            response.sendRedirect("login"); // redirect to the login page
            return; // stop the servlet from running
        }


        UserDAO_MySQL user_data = new UserDAO_MySQL();
        request.setAttribute("users",user_data.getAll());
        User user = (User)session.getAttribute("user");
        if( user.getPrivileges().equals("admin")) {
            request.getRequestDispatcher("WEB-INF/view-users.jsp").forward(request,response);
        } else {
            response.sendRedirect( request.getContextPath() ); // send them to the homepage
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}