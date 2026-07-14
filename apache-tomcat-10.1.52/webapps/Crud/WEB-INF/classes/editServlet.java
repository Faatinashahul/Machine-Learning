import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/editServlet")
public class editServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        try {
            String bname=request.getParameter("bname");
            String author=request.getParameter("author");
            int year=Integer.parseInt(request.getParameter("year"));
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("UPDATE books SET author =? ,year=? WHERE bname =?");
            
            ps.setString(1,author);
            ps.setInt(2,year);
            ps.setString(3,bname);

            int result=ps.executeUpdate();

            if(result>0){
                out.print("Book updated");
            }else{
                out.print("failed");
            }
            response.sendRedirect("index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
