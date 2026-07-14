import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/displayServlet")
public class displayServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        try {
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM books");
            ResultSet rs=ps.executeQuery();
            out.println("<!DOCTYPE HTML>");
            out.println("<html>");
            out.println("<body>");
            out.println("<table border=1>");
            out.println("<tr><th>BOOK NAME</th><th>AUTHOR</th><th>YEAR</th></tr>");
            while(rs.next()){
                out.println("<tr><td>"+rs.getString("bname")+"</td><td>"+rs.getString("author")+"</td><td>"+rs.getInt("year")+"</td></tr>");
                
            
            }
            out.println("</table>");
            out.println("<a href='index.html'>Back to home</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.print(e);
        }
    }
    
}
