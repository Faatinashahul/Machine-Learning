import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/autocompleteServlet")
public class autocompleteServlet extends HttpServlet{
    protected void doGET(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        String input=request.getParameter("input");
        try{
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("SELECT pid FROM patient WHERE CAST(pid AS CHAR)LIKE ?");
            ps.setString(1,input+"%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                out.println(rs.getInt("pid"));
            }
        }
        catch(Exception e){
            out.print(e);
        }
    }
}
