package meppelink.chapter3_4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "AddServlet",
        urlPatterns = {"/add", "/addition"},
        loadOnStartup = 1
)
public class AddServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstNumber = request.getParameter("firstNumber");
        String secondNumber = request.getParameter("secondNumber");
        Map<String, String> results = add(firstNumber, secondNumber);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/add.jsp").forward(request, response);
    }

    public Map<String, String> add(String firstNumber, String secondNumber) {
        Map<String, String> results = new HashMap<>();

        boolean firstNumberValid = isANumber(firstNumber);
        boolean secondNumberValid = isANumber(secondNumber);

        if(!firstNumberValid) {
            results.put("firstNumberInvalid", "Invalid number");
        }
        if(!secondNumberValid) {
            results.put("secondNumberInvalid", "Invalid number");
        }

        if(firstNumberValid && secondNumberValid) {
            BigDecimal n1 = new BigDecimal(firstNumber);
            BigDecimal n2 = new BigDecimal(secondNumber);
            results.put("sum", n1.add(n2).toString());
        }

        results.put("firstNumber", firstNumber);
        results.put("secondNumber", secondNumber);

        return results;
    }

    public boolean isANumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
