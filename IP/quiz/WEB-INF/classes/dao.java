import com.sun.jdi.connect.spi.Connection;
import java.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.hhtp.*;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.DriverManager;

public class dao extends HttpServlet{
    
    public Connection getCon(){
        Connection con=null;
    }
    try {
                String url="jdbc:mysql://localhost:3306/appointment_db"
            String user="root";
            String password="Faa2023-2027";
            class.ForName("com.mysql.cj.jdbc.driver");
            con=DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    return con;

}

