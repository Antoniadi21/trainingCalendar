package RepositoriesTests;

import Models.Sex;
import Models.User;
import Repositories.UserRepository;
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

public class UserRepositoryTest {
    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());
    private static final String PROPERTIES_PATH = "D:/labs/java first task/src/src/main/resources/config.properties";
    private UserRepository userRepository;

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
        userRepository = new UserRepository(connection);
    }

    @Test
    public void testGetByLoginWithWrongLogin() {
        String wrongLogin = "wrongLogin";
        try {
            Assert.assertNull(userRepository.getByLogin(wrongLogin));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in test", e);
        }
    }

    @Test
    public void testGetByLoginWithCorrectLogin() {
        String correctLogin = "vya";
        User expected = new User(1, "vya", "vya", 14, new Sex(1, 'м'), "vya@gmail.com");
        try {
            Assert.assertEquals(expected, userRepository.getByLogin(correctLogin));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in test", e);
        }
    }
}
