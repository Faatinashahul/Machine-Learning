import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/addServlet")

public class addServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        PrintWriter out=response.getWriter();

        try {
            String bname=request.getParameter("bname");
            String author=request.getParameter("author");
            int year=Integer.parseInt(request.getParameter("year"));
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("INSERT INTO books(bname,author,year) VALUES(?,?,?)");
            ps.setString(1,bname);
            ps.setString(2,author);
            ps.setInt(3,year);
            int rs=ps.executeUpdate();
            if(rs>0){
                out.print("Book added");
            }else{
                out.print("Failed adding the book");
            }
        } catch (Exception e) {
            out.print(e);
        }
    }
}