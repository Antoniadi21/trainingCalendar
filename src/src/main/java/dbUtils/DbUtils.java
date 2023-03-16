package dbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtils {
    private static final String url = "jdbc:postgresql://localhost:5432/trainingCalendar";
    private static final String userName = "me";
    private static final String password = "definitelyNotAPassword";
    private static final Logger logger = Logger.getLogger(DbUtils.class.getName());
    private static final String logPath = "src/main/java/Logs/logs.txt";

    static {
        try {
            logger.addHandler(new FileHandler(logPath));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to add handler to logger");
        }
    }

    public static Connection getConnection() {
        Connection connection = null;

        logger.log(Level.INFO, "trying to get connection to db");
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException trying to get connection to db");
        }
        logger.log(Level.INFO, "finished trying to ge connection to db");
        return connection;
    }
}
