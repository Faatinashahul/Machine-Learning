
import java.sql.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/view")
public class viewServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        try {
            Connection con=dbConnection.getCon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM students");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head>");
            out.println("<body class='form-body'>");
            out.println("<div class='box'>");
            out.println("<h2>Student Records</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th></tr>");
            while(rs.next()){
                out.println("<tr>");
                out.println("<td>"+rs.getInt(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getString(3)+"</td>");
                out.println("<td>"+rs.getString(4)+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br><a href='index.html' class='btn'>Back to Home</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            con.close();


        } catch (Exception e) {
            out.println(e);
        }
    }
}
