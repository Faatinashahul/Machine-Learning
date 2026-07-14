import java.io.*;
import jakarta.servlet.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
@WebServlet("/update")
public class updateServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        PrintWriter out=res.getWriter();
        try {
            int id=Integer.parseInt(req.getParameter("id"));
            String name=req.getParameter("name");
            String dept=req.getParameter("dept");
            String email=req.getParameter("email");
            Connection con=dbConnection.getCon();
            PreparedStatement ps=con.prepareStatement("UPDATE students SET name=?, department=?,email=? WHERE id=?");
            ps.setString(1,name);
            ps.setString(2,dept);
            ps.setString(3,email);
            ps.setInt(4,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link rel='stylesheet' href='style.css'>");
                out.println("</head>");
                out.println("<body class='form-body'>");
                out.println("<div class='box'>");
                out.println("<h2>Student Updated Successfully</h2>");
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
