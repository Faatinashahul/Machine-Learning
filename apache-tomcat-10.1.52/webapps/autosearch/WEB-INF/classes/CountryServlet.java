import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/CountryServlet")
public class CountryServlet extends HttpServlet {
    private static final String URL ="jdbc:mysql://localhost:3306/countries_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Faa2023-2027"; 

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ArrayList<String> countries = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql ="SELECT name FROM countries " +"WHERE LOWER(name) LIKE LOWER(?) " +"ORDER BY name";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countries.add(rs.getString("name"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < countries.size(); i++) {
            json.append("\"").append(countries.get(i)).append("\"");
            if (i < countries.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        out.print(json.toString());
        out.flush();
    }
}