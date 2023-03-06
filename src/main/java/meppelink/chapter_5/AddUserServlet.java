package meppelink.chapter_5;

import meppelink.data_access.UserDAO_MySQL;
import meppelink.twilio.Twilio;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@WebServlet(name = "AddUserServlet", value = "/signup")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/add-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String[] agreeToTerms = request.getParameterValues("agree-to-terms"); // use with checkboxes
        Map<String, String> results = new HashMap<>();
        User user = new User();
        try {
            user.setFirst_name(firstName);
        } catch(IllegalArgumentException e) {
            results.put("firstNameError", e.getMessage());
        }
        try {
            user.setLast_name(lastName);
        } catch(IllegalArgumentException e) {
            results.put("lastNameError", e.getMessage());
        }
        try {
            user.setEmail(email);
        } catch(IllegalArgumentException e) {
            results.put("emailError", e.getMessage());
        }
        try {
            user.setPhone(phone);
        } catch(IllegalArgumentException e) {
            results.put("phoneError", e.getMessage());
        }
        try {
            user.setPassword(password1.toCharArray());
        } catch(IllegalArgumentException e) {
            results.put("password1Error", e.getMessage());
        }
        if(password2.equals("")) {
            results.put("password2Error", "This input is required");
        }
        if(!password1.equals(password2)) {
            results.put("password2Error", "Passwords don't match");
        }
        if(agreeToTerms == null || !agreeToTerms[0].equals("agree")){
            results.put("agreeError", "You must agree to our terms and conditions");
        }

        if(!results.containsKey("firstNameError") && !results.containsKey("lastNameError")
                && !results.containsKey("emailError") && !results.containsKey("phoneError")
                && !results.containsKey("password1Error") && !results.containsKey("password2Error")
                && !results.containsKey("agreeError")
        ) {
            UserDAO_MySQL dao = new UserDAO_MySQL();
            int numRowsAffected = dao.add(user);
            if(numRowsAffected == 1) {
                results.put("userAddSuccess", "Please check your phone for a validation code");
                generate2FA(user);
            }
        } else {
            results.put("firstName", firstName);
            results.put("lastName", lastName);
            results.put("phone", phone);
            results.put("email", email);
            results.put("password1", password1);
            results.put("password2", password2);
            if (agreeToTerms != null && agreeToTerms[0].equals("agree")) {
                results.put("agreeToTerms", agreeToTerms[0]);
            }
        }
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/add-user.jsp").forward(request, response);
    }

    private void generate2FA(User user) {
        int twoFACode = new Random().nextInt(900000) + 100000;
        UserDAO_MySQL dao = new UserDAO_MySQL();
        int result = dao.insertUser2FACode(dao.getUserId(user), twoFACode, "sms");
        send2FA_SMS(String.valueOf(twoFACode) , user.getPhone());
    }

    private void send2FA_SMS(String code, String phoneNumber) {
        Twilio twilio = new Twilio();
        twilio.sendTextMessage(phoneNumber, code);
    }

}

