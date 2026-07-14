import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String error = "";
        if (username == null || username.isEmpty()) {
            error = "Username cannot be empty";
        }
        else if (username.length() < 5 || username.length() > 15) {
            error = "Username must be 5–15 characters";
        }
        else if (!username.matches("^[A-Za-z][A-Za-z0-9_]*$")) {
            error = "Invalid username format";
        }

        else if (password == null || password.isEmpty()) {
            error = "Password cannot be empty";
        }
        else if (password.length() < 8 || password.length() > 20) {
            error = "Password must be 8–20 characters";
        }
        else if (password.contains(" ")) 
        {
            error = "Password must not contain spaces";
        }
        else if (!password.matches(".*[A-Z].*") ||
                 !password.matches(".*[a-z].*") ||
                 !password.matches(".*[0-9].*") ||
                 !password.matches(".*[@#$%&*!].*")) {
            error = "Password must contain upper, lower, digit, special char";
        }
        else if (password.equalsIgnoreCase(username)) {
            error = "Password cannot be same as username";
        }

        else {
            String validUser = "Admin_01";
            String validPass = "Admin@123";
            if (username.equalsIgnoreCase(validUser) && password.equals(validPass)) {
                response.sendRedirect("success.html");
                return;
            } 
            else {
                error = "Invalid credentials";
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("errorMsg", error);
        response.sendRedirect("error.html");
    }
}
