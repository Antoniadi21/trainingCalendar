package Servlets;

import Models.User;
import Repositories.SexRepository;
import Service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        RequestDispatcher requestDispatcher;
        User user = userService.login(login, password);
        if (user != null) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("age", user.getAge());
            request.getSession().setAttribute("sex", user.getSexId());
            requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/welcome.jsp");
        } else {
            request.setAttribute("loggingAttempt", "invalid data");
            requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        }
        requestDispatcher.forward(request, response);
    }
}
