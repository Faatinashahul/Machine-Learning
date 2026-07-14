import java.sql.*;
public class dbConnection{
    public static Connection getCon(){
        Connection con=null;
        try {
            String url="jdbc:mysql://127.0.0.1:3306/studentdb" + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user="root";
            String password="Faa2023-2027";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    } 
}