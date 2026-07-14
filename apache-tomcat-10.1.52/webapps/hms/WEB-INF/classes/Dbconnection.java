import java.sql.*;
public class Dbconnection
{
    public static Connection getCon(){
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/patient_db","root","Faa2023-2027");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
