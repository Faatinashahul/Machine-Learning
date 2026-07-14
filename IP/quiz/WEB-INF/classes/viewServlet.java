import com.sun.jdi.connect.spi.Connection;
import java.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.hhtp.*;
import jakarta.servlet.annotations.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
@WebServlet("/view")

public class viewServlet extends HttpServlet{
    protected void doGet(HttpRequest res.HttpResponse res)throws IOException,ServletException{
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        Connection con=dao.getCon();
        Statement st=con.createStatement();
        Result rs=con.executeQuery("SELECT*FROM appointment_db")
        while(rx.next()){
            
        }
        

    }
}

