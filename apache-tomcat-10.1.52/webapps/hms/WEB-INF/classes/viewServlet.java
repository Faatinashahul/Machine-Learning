import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        int pid=Integer.parseInt(request.getParameter("pid"));
        HttpSession session=request.getSession(false);
        if(session!=null){
            Integer last= (Integer)session.getAttribute("last added");
            if(last!=null){
                out.println("LAST ADDED"+last);
            }
        }
        try{
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM patient WHERE pid=?");
            ps.setInt(1,pid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.print("<th>Name</th>");
                out.print("<th>Age</th>");
                out.print("<th>Gender</th>");
                out.print("<th>Contact number</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.print("<td>"+rs.getInt("pid")+"</td>"+"<td>"+rs.getString("pname")+"</td>"+"<td>"+rs.getInt("age")+"</td>"+"<td>"+rs.getString("gender")+"</td>"+"<td>"+rs.getString("no")+"</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");

            }
        }
        catch(Exception e){
            out.println(e);
        }
    }
}
