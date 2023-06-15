package Servlets;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ErrorServlet", value = "/error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String error = request.getParameter("errorMessage");
        response.getOutputStream().println(String.format("Error: %s", error));
    }
}
