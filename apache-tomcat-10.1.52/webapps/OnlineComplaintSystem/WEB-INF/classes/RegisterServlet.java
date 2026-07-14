import com.complaint.dao.complaintDAO;
import com.complaint.dao.dbConnectiondao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String category = request.getParameter("category");
        String desc = request.getParameter("desc");
        try {
            complaintDAO dao = new complaintDAO();
            String result=dao.registerComplaint(name, email, category, desc);
            if (result.equals("DUPLICATE")) {
                out.println("<script>");
                out.println("alert('Duplicate Complaint Found');");
                out.println("window.location='register.html';");
                out.println("</script>");

            } 
            else {
                out.println("<script>");
                out.println("alert('Complaint Registered Successfully! ID: "+ result + "');");
                out.println("window.location='index.html';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}