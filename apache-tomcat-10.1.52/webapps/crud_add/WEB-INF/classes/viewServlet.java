
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        try{
            Connection con=Dbconnection.getCon();
            response.setContentType("text/html");
            PreparedStatement ps=con.prepareStatement("SELECT * FROM books");
            ResultSet rs=ps.executeQuery();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>BOOK NAME</th>");
            out.println("<th>BOOK AUTHOR</th>");
            out.println("<th> YEAR</th>");
            out.println("</tr>");
            while(rs.next()){
                out.println("<tr>");
                out.println("<td>"+rs.getString("bname")+"</td>");
                out.println("<td>"+rs.getString("author")+"</td>");
                out.println("<td>"+rs.getString("year")+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        }
        catch(Exception e){
            out.print(e);
        }
    }
    
}
