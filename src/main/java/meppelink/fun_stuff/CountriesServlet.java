package meppelink.fun_stuff;

import meppelink.data_access.CountryDAO_CSV;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CountriesServlet", value = "/countries")
public class CountriesServlet extends HttpServlet {
    private List<Country> countries;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(countries == null) {
            countries = CountryDAO_CSV.getAll(request, response);
        }
        request.setAttribute("countries", countries);
        request.getRequestDispatcher("WEB-INF/countries.jsp").forward(request, response);
    }

}
