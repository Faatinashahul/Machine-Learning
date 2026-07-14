<%@ page import="java.sql.*" %>
<%@ page import="com.complaint.dao.dbConnectiondao" %>
<%
    // Session Protection
    //if (session.getAttribute("admin") == null) {
       // response.sendRedirect("adminLogin.html");
       // return;
    //}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style>
body {
    font-family: 'Segoe UI', sans-serif;
    margin: 0;
    background:  rgb(232, 242, 245);
}
.dashboard-container {
    width: 95%;
    max-width: 1200px;
    margin: 40px auto;
    padding: 30px;
    border-radius: 12px;
    background: #ffffff;
    box-shadow: 0 20px 50px rgba(0,0,0,0.12);
}
.dashboard-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 25px;
}
.logout {
    padding: 8px 16px;
    background: #ef4444;
    color: white;
    text-decoration: none;
    border-radius: 6px;
    font-size: 14px;
}
.logout:hover {
    background: #a20b0b;
}
table {
    width: 100%;
    border-collapse: collapse;
}
th {
    text-align: left;
    padding: 14px;
    font-size: 13px;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: 700;
    color: #555;
    border-bottom: 2px solid #eee;
}
td {
    padding: 16px 14px;
    border-bottom: 1px solid #f2f2f2;
    font-size: 14px;
}
tr:hover {
    background: #f9fafb;
}
.status {
    padding: 5px 12px;
    border-radius: 20px;
    font-size: 12px;
    font-weight: 600;
}
.pending {
    background: #fff3cd;
    color: #856404;
}
.progress {
    background: #d1ecf1;
    color: #0c5460;
}
.resolved {
    background: #d4edda;
    color: #155724;
}
.update-box {
    display: flex;
    flex-direction: column;
    gap: 8px;
}
select, input[type="text"] {
    padding: 6px 8px;
    border: 1px solid #ddd;
    border-radius: 6px;
    font-size: 13px;
}
input[type="submit"] {
    padding: 6px 12px;
    background: #0d6e97;
    border: none;
    color: white;
    border-radius: 6px;
    cursor: pointer;
    font-size: 13px;
}
input[type="submit"]:hover {
    background: #0b709b;
}
</style>
</head>

<body>
<div class="dashboard-container">
    <div class="dashboard-header">
        <h2>Admin Dashboard</h2>
        <form action="LogoutServlet" method="get">
            <button type="submit" class="logout">Logout</button>
        </form>
    </div>
    <table>
        <tr>
            <th>Complaint ID</th>
            <th>Category</th>
            <th>Description</th>
            <th>Status</th>
            <th>Remarks</th>
            <th>Update</th>
        </tr>
<%
    try {
        Connection con = dbConnectiondao.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM complaints");
        while (rs.next()) {
            String status = rs.getString("status");
            String statusClass = "pending";
            if ("In Progress".equalsIgnoreCase(status)) {
                statusClass = "progress";
            } else if ("Resolved".equalsIgnoreCase(status)) {
                statusClass = "resolved";
            }
%>
<tr>
    <td><%= rs.getString("complaint_id") %></td>
    <td><%= rs.getString("category") %></td>
    <td><%= rs.getString("description") %></td>
    <td>
        <span class="status <%= statusClass %>">
            <%= status %>
        </span>
    </td>
    <td><%= rs.getString("remarks") %></td>
    <td>
        <form action="updateStatus" method="post">
            <input type="hidden"
                   name="cid"
                   value="<%= rs.getString("complaint_id") %>">
            <div class="update-box">
                <select name="status">
                    <option <%= "Pending".equals(status) ? "selected" : "" %>>Pending</option>
                    <option <%= "In Progress".equals(status) ? "selected" : "" %>>In Progress</option>
                    <option <%= "Resolved".equals(status) ? "selected" : "" %>>Resolved</option>
                </select>
                <input type="text"
                       name="remarks"
                       value="<%= rs.getString("remarks") == null ? "" : rs.getString("remarks") %>"
                       placeholder="Enter remarks">
                <input type="submit" value="Update">
            </div>
        </form>
    </td>
</tr>
<%
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
    </table>
</div>

</body>
</html>