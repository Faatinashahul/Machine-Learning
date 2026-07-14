import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
@WebServlet("/delete")
public class deleteServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        PrintWriter out=res.getWriter();
        try {
            int id=Integer.parseInt(req.getParameter("id"));
            Connection con=dbConnection.getCon();
            PreparedStatement ps =con.prepareStatement("DELETE FROM students WHERE id=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head>");
            out.println("<body class='form-body'>");
            out.println("<div class='box'>");
            if(rows>0){
                out.println("<h2>Student Deleted Successfully</h2>");
                out.println("<br><a href='view' class='btn'>View Records</a>");
                out.println("<br><a href='index.html' class='btn'>Back to Home</a>");
            }
            else{
                out.println("<h2>Student Not found</h2");
                out.println("<br><a href='index.html' class='btn'>Back to Home</a>");
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            ps.close();
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }  
}
