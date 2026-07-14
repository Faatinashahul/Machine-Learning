import com.complaint.dao.complaintDAO;
import com.complaint.dao.dbConnectiondao;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class TrackServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        PrintWriter out=res.getWriter();
        String cid=req.getParameter("cid");
        try{
            Connection con=dbConnectiondao.getConnection();
            PreparedStatement ps=con.prepareStatement("select status,remarks from complaints where complaint_id=?");
            ps.setString(1,cid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                String status = rs.getString("status");
                String remarks = rs.getString("remarks");

                String color = "#f0ad4e"; 

                if(status.equalsIgnoreCase("Resolved")){
                    color = "#28a745"; 
                }
                else if(status.equalsIgnoreCase("In Progress")){
                    color = "#007bff"; 
                }

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Track Complaint</title>");
                out.println("<link rel='stylesheet' href='style.css'>");
                out.println("</head>");
                out.println("<body class='index2'>");

                out.println("<div class='formal-track-box'>");

                out.println("<h2>Complaint Tracking</h2>");
                out.println("<hr>");

                out.println("<div class='track-row'><span>ID</span><span>" + cid + "</span></div>");

                out.println("<div class='track-row'><span>Status</span>"
                        + "<span class='status-badge' style='background:" + color + "'>"
                        + status + "</span></div>");

                out.println("<div class='track-row'><span>Remarks</span><span>"
                        + (remarks == null ? "Not Updated Yet" : remarks)
                        + "</span></div>");

                out.println("<a href='index.html' class='formal-btn'>Back to Home</a>");

                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
            else{
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Track Complaint</title>");
                out.println("<link rel='stylesheet' href='style.css'>");
                out.println("</head>");
                out.println("<body class='index2'>");
                out.println("<div class='formal-track-box'>");
                out.println("<h2>Invalid Complaint ID</h2>");
                out.println("<a href='index.html' class='formal-btn'>Back</a>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }

        }catch(Exception e){
        e.printStackTrace();
        }
 }
}