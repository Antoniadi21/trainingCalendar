package Servlets;

import Models.User;
import Service.UserService;
import dbUtils.DbUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final String PROPERTIES_PATH = "D:/labs/java first task/src/src/main/resources/config.properties";
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    private UserService userService;

    static {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);

            String logPath = properties.getProperty("logger.logPath");
            logger.addHandler(new FileHandler(logPath));

        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to read properties file", e);
        }
    }

    {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);

            String logPath = properties.getProperty("logger.logPath");
            logger.addHandler(new FileHandler(logPath));

            DbUtils dbUtils = new DbUtils("db.host");
            userService = new UserService(dbUtils.getConnection());

        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to read properties file", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException trying to read properties file", e);
        }
    }

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
        User user;
        try {
            user = userService.login(login, password);
        } catch (SQLException | NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/error?errorMessage=internalServerError");
            return;
        }
        if (user != null) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("age", user.getAge());
            request.getSession().setAttribute("sex", user.getSex().getSex());
            request.getSession().setAttribute("id", user.getUserId());
            request.getSession().setAttribute("monthOffset", 0);
            requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/welcome.jsp");
        } else {
            request.setAttribute("loggingAttempt", "invalid data");
            requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        }
        requestDispatcher.forward(request, response);
    }
}
