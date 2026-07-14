import com.complaint.dao.complaintDAO;
import com.complaint.dao.dbConnectiondao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Connection con = dbConnectiondao.getConnection();
            PreparedStatement ps =con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("admin", username);
                res.sendRedirect("dashboard.jsp");
            }
            else {
                out.println("<script>");
                out.println("alert('Invalid Username or Password');");
                out.println("window.location='adminLogin.html';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}