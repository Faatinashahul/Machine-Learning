package com.complaint.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnectiondao {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/complaintdb","root","Faa2023-2027");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
