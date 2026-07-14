import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/addServlet")
public class addServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        int pid=Integer.parseInt(request.getParameter("pid"));
        HttpSession session=request.getSession();
        session.setAttribute("last added",pid);
        String pname=request.getParameter("pname");
        int age=Integer.parseInt(request.getParameter("age"));
        String gender=request.getParameter("gender");
        String no=request.getParameter("no");
        try{
            Connection con=Dbconnection.getCon();
            PreparedStatement ps=con.prepareStatement("INSERT INTO patient(pid,pname,age,gender,no) VALUES (?,?,?,?,?)");
            ps.setInt(1,pid);
            ps.setString(2,pname);
            ps.setInt(3,age);
            ps.setString(4,gender);
            ps.setString(5,no);

        
            int result=ps.executeUpdate();
            if(result>0){
                out.print("BOOK ADDED");
            }
            else{
                out.print("BOOK ADDITION FAILED");
            }

        }
        catch(Exception e){
            out.print(e);
        }

    }
}