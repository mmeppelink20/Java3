package meppelink.chapter3_4;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
@WebServlet(
        name = "TemperatureConverter",
        urlPatterns = {"/temp", "/temp-converter"},
        loadOnStartup = 1
)
public class TemperatureConverter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/temp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String temp = request.getParameter("userTemp");
        String unit = request.getParameter("unit-select");
        Map<String, String> results = new HashMap<>();
        try {
            results = convert(temp, unit);
        } catch(IllegalArgumentException e) {
            results.put("userTempError", "That temperature is invalid");
        }



        if(unit.equals("C")) {
            results.put("unit", "F");
        } else {
            results.put("unit", "C");
        }
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/temp.jsp").forward(request, response);
    }

    public Map<String, String> convert(String temp, String unit) {
        Map<String, String> results = new HashMap<>();

        isAValidNumber(temp);
        if(!isAValidTemp(temp, unit)) {
            throw new NumberFormatException();
        }

        if(unit.equals("C")) {
            Double convertedTemp = Double.parseDouble(temp) * (9.0/5.0) + 32;
            temp = Double.toString(convertedTemp);
        }
        if(unit.equals("F")) {
            Double convertedTemp = (Double.parseDouble(temp) - 32.0) * 5.0/9.0;
            temp = Double.toString(convertedTemp);
        }

        results.put("convertedTemp", temp);

        return results;
    }

    public boolean isAValidNumber(String temp) {
        try {
            Double.parseDouble(temp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isAValidTemp(String temp, String unit) {
        boolean result = true;
        if(Double.parseDouble(temp) < -459.67 && unit.equals("F") || Double.parseDouble(temp) < -273.15 && unit.equals("C")) {
            result = false;
        }
        return result;
    }

}



