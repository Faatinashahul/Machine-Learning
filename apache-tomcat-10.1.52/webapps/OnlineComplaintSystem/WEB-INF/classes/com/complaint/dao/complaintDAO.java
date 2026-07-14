package com.complaint.dao;
import java.sql.*;

public class complaintDAO {
    Connection con;
    public complaintDAO(){
        con=dbConnectiondao.getConnection();
    }
    public String registerComplaint(String name,String email,String category,String desc){
        String complaintID="CMP"+System.currentTimeMillis();
        try {
            //Checking if user already exists
            PreparedStatement checkUser = con.prepareStatement("SELECT user_id FROM users WHERE email=?");
            checkUser.setString(1,email);
            ResultSet userRs = checkUser.executeQuery();
            int userID;
            if(userRs.next()){
                userID = userRs.getInt("user_id");
            }
            else{
                PreparedStatement insertUser = con.prepareStatement("INSERT INTO users(name,email) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
                insertUser.setString(1,name);
                insertUser.setString(2,email);
                insertUser.executeUpdate();
                ResultSet newUser = insertUser.getGeneratedKeys();
                newUser.next();
                userID = newUser.getInt(1);
            }

            //Checking duplicate complaint
            PreparedStatement checkComplaint = con.prepareStatement("SELECT * FROM complaints WHERE description=? AND user_id=?");
            checkComplaint.setString(1,desc);
            checkComplaint.setInt(2,userID);
            ResultSet rs2 = checkComplaint.executeQuery();
            if(rs2.next()){
                return "DUPLICATE";
            }

            //Inserting complaint
            PreparedStatement ps3 = con.prepareStatement("INSERT INTO complaints VALUES(?,?,?,?,?,NULL,NOW(),NULL)");
            ps3.setString(1,complaintID);
            ps3.setInt(2,userID);
            ps3.setString(3,category);
            ps3.setString(4,desc);
            ps3.setString(5,"Pending");
            ps3.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaintID;
    }
}

