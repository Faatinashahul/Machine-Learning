import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/test")
public class TestDBServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            // JDBC URL (updated for Mac MySQL)
            String url =
                "jdbc:mysql://127.0.0.1:3306/testdb"
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&serverTimezone=UTC";

            String user = "root";
            String pass = "Faa2023-2027";   // your MySQL root password

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con =
                DriverManager.getConnection(url, user, pass);

            Statement stmt = con.createStatement();
            ResultSet rs =
                stmt.executeQuery("SELECT * FROM users");

            out.println("<h2>DB Connected Successfully ✅</h2>");

            while (rs.next()) {
                out.println(
                    "ID: " + rs.getInt("id") +
                    " Name: " + rs.getString("name") +
                    "<br>"
                );
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red'>Error:</h3>");
            out.println(e.getMessage());
            e.printStackTrace(out);
        }
    }
}
