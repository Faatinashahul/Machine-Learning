import com.complaint.dao.complaintDAO;
import com.complaint.dao.dbConnectiondao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.html");
            return;
        }
        String complaintId = request.getParameter("cid");
        String status = request.getParameter("status");
        String remarks = request.getParameter("remarks");
        try {
            Connection con = dbConnectiondao.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE complaints " +
                "SET status=?, remarks=?, updated_at=NOW() " +
                "WHERE complaint_id=?"
            );
            ps.setString(1, status);
            ps.setString(2, remarks);
            ps.setString(3, complaintId);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("dashboard.jsp");
    }
}
