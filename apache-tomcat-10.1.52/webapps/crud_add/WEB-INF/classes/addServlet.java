import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/addServlet")
public class addServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        String bname=request.getParameter("bname");
        String author=request.getParameter("author");
        String year=request.getParameter("year");
        try{
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("INSERT INTO books (bname,author,year) VALUES (?,?,?)");
            ps.setString(1,bname);
            ps.setString(2,author);
            ps.setString(3,year);

            int result=ps.executeUpdate();
            if(result>0){
                out.println("added");
            }else{
                out.println("failed");
            }
        }
        catch(Exception e){
            out.print(e);
        }
    }
}