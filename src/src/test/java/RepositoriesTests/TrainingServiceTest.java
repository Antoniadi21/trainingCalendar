package RepositoriesTests;

import Models.Training;
import Models.TrainingType;
import Service.TrainingService;
import dbUtils.DbUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class TrainingServiceTest {
    private static final Logger logger = Logger.getLogger(TrainingServiceTest.class.getName());
    private static final String PROPERTIES_PATH = "D:/labs/java first task/src/src/main/resources/config.properties";
    private TrainingService trainingService;

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
        trainingService = new TrainingService(connection);
    }

    @Test
    public void getUserTrainingTestEmpty() {
        int id = 1;
        LocalDate date = LocalDate.parse("2000-12-12");
        List<Training> expected = new ArrayList<>();
        try {
            Assert.assertEquals(expected, trainingService.getUserTraining(id, date));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in test", e);
        }
    }

    @Test
    public void getUserTrainingTest() {
        int userId = 1;
        LocalDate date = LocalDate.parse("2023-05-05");
        Training expectedFirst = new Training(1, userId, 30, LocalDate.parse("2023-05-22"),
                new TrainingType(1, "Кардио"));
        Training expectedSecond  = new Training(2, userId, 40, LocalDate.parse("2023-05-21"),
                new TrainingType(1, "Кардио"));

        try {
            List<Training> actual = trainingService.getUserTraining(userId, date);
            assertThat(actual, containsInAnyOrder(expectedFirst, expectedSecond));
            assertThat(actual, hasSize(2));
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException in the test");
        }
    }
}
