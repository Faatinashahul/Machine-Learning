
import java.sql.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/add")
public class addServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        PrintWriter out=res.getWriter();
        try {
            int id=Integer.parseInt(req.getParameter("id"));
            String name=req.getParameter("name");
            String dept=req.getParameter("dept");
            String email=req.getParameter("email");

            Connection con=dbConnection.getCon();
            PreparedStatement ps=con.prepareStatement(
                "INSERT into students VALUES(?,?,?,?)"
            );
            ps.setInt(1, id);
            ps.setString(2,name);
            ps.setString(3,dept);
            ps.setString(4,email);
            int rows=ps.executeUpdate();
            if(rows>0){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link rel='stylesheet' href='style.css'>");
                out.println("</head>");
                out.println("<body class='form-body'>");
                out.println("<div class='box'>");
                out.println("<h2>Student Added Successfully</h2>");
                out.println("<br><a href='view' class='btn'>View Records</a>");
                out.println("<br><a href='index.html' class='btn'>Back to Home</a>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
            ps.close();
            con.close();

        } catch (Exception e) {
            out.println(e);
        }
    }
}
