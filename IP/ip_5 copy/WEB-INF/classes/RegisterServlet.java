import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String course = request.getParameter("course");
        out.println("<html><head>");
        out.println("<style>");
        out.println("table{border-collapse:collapse;width:50%;margin:auto;}");
        out.println("th,td{border:1px solid black;padding:10px;text-align:center;}");
        out.println("th{background-color:#4CAF50;color:white;}");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h2 align='center'>Registration Details</h2>");

        out.println("<table>");
        out.println("<tr><th>Parameter</th><th>Value</th></tr>");
        out.println("<tr><td>Name</td><td>" + name + "</td></tr>");
        out.println("<tr><td>Email</td><td>" + email + "</td></tr>");
        out.println("<tr><td>Gender</td><td>" + gender + "</td></tr>");
        out.println("<tr><td>Course</td><td>" + course + "</td></tr>");
        out.println("</table>");
        out.println("</body></html>");
    }
}
