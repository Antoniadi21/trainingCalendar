package RepositoriesTests;

import Service.UserService;
import dbUtils.DbUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceTest {
    private static final Logger logger = Logger.getLogger(UserServiceTest.class.getName());
    private static final String PROPERTIES_PATH = "D:/labs/java first task/src/src/main/resources/config.properties";
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

    @Before
    public void prepare() {
        Properties properties = new Properties();
        Connection connection = null;

        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);

            DbUtils dbUtils = new DbUtils(properties.getProperty("db.test"));
            connection = dbUtils.getConnection();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to read properties file", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQException trying to get connection", e);
        }
        userService = new UserService(connection);
    }

    @Test
    public void loginTestWrongLogin() {
        String login = "siuuuu";
        String password = "vya";
        try {
            Assert.assertNull(userService.login(login, password));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException during test", e);
        }
    }

    @Test
    public void loginTestWrongPassword() {
        String login = "vya";
        String password = "siuuuu";
        try {
            Assert.assertNull(userService.login(login, password));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException during test", e);
        }
    }

    @Test
    public void getIdByLoginWrongLogin() {
        String login = "eeeeee";
        try {
            Assert.assertEquals(-1, userService.getIdByLogin(login));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException during test", e);
        }
    }

    @Test
    public void getIdByLoginCorrectLogin() {
        String login = "vya";
        try {
            Assert.assertEquals(1, userService.getIdByLogin(login));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException during test", e);
        }
    }
}
