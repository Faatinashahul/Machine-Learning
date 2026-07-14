import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        String bname=request.getParameter("bname");
        try {

            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("DELETE FROM books WHERE bname=?");
            ps.setString(1,bname);
            response.setContentType("text/html");
            int result=ps.executeUpdate();
            if(result>0){
                out.print("<!DOCTYPE HTML>");
                out.print("<html>");
                out.print("<body>");
                out.print("<p>DELETED SUCCESSFULLY</p>");
                out.print("<a href='index.html'>Back to home</a>");
                out.print("</body>");
                out.print("</html>");

            }else{
                out.print("FAILED DELETION");
            }
            
        } catch (Exception e) {
            out.print(e);
        }
    }
}
