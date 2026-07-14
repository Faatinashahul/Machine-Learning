import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.*;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        PrintWriter out=response.getWriter();
        try {
            Connection con=DbConnection.getCon();
            String username=request.getParameter("username");
            String password=request.getParameter("password");

            PreparedStatement ps=con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                out.print("success");
            }
            else{
                out.print("not valid");
            }
        } catch (Exception e) {
            out.print(e);
        }

    }
}
