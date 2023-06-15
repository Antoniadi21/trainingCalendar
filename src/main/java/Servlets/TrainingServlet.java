package Servlets;

import Service.TrainingService;
import Service.UserService;
import dbUtils.DbUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "TrainingServlet", value = "/training")
public class TrainingServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(TrainingServlet.class.getName());
    private static final String PROPERTIES_PATH = "D:/labs/java first task/src/src/main/resources/config.properties";
    private UserService userService;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrainingService trainingService = new TrainingService();

        try {
            request.getSession().setAttribute("trainings",
                    trainingService.getUserTraining(userService.getIdByLogin(request.getSession().getAttribute("login").toString()),
                            LocalDate.now().plusMonths(Integer.parseInt(request.getSession().getAttribute("monthOffset").toString()))));
        } catch (SQLException | NullPointerException e) {
            response.sendRedirect(request.getContextPath() + "/error?errorMessage=internalServerError");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/training.jsp");
        requestDispatcher.forward(request, response);
    }
}
