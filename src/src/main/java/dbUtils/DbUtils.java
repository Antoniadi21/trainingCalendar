package dbUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtils {
    // в проперти файл
    private static String url;
    private static String userName;
    private static String password;
    private static final Logger logger = Logger.getLogger(DbUtils.class.getName());
    private static String logPath;

    private static final String PROPERTIES_PATH = "src/src/main/resources/config.properties";

    static {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);

            url = properties.getProperty("db.host");
            userName = properties.getProperty("db.userName");
            password = properties.getProperty("db.password");
            logPath = properties.getProperty("logger.logPath");

            logger.addHandler(new FileHandler(logPath));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to read properties file", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;

        logger.log(Level.INFO, "trying to get connection to db");
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException trying to get connection to db", e);
        }
        logger.log(Level.INFO, "finished trying to ge connection to db");
        return connection;
    }
}
