import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class View_cart_servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        double grandTotal = 0;
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Shopping Cart</title>");
        out.println("<style>");
        out.println("table { width:50%; border-collapse:collapse; }");
        out.println("th, td { border:1px solid black; padding:8px; text-align:left; }");
        out.println("th { background-color:#f2f2f2; }");
        out.println("a { color:#007BFF; text-decoration:none; }");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Your Cart</h2>");
        if (cart == null || cart.size() == 0) {
            out.println("<p>Cart is empty</p>");
        } else {

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Price</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Subtotal</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            for (CartItem item : cart) {

                double subtotal = item.getPrice() * item.getQuantity();
                grandTotal += subtotal;
                out.println("<tr>");
                out.println("<td>" + item.getName() + "</td>");
                out.println("<td>" + item.getPrice() + "</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>" + subtotal + "</td>");
                out.println("<td>");
                out.println("<a href='Remove_from_cart_servlet?name=" + item.getName() + "'>Remove</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<h3>Grand Total: " + grandTotal + "</h3>");
        }

        out.println("<br>");
        out.println("<a href='products.html' style=\"text-decoration: none;\">Back to Products</a>");

        out.println("</body>");
        out.println("</html>");
    }
}