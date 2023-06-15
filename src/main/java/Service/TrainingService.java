package Service;

import Repositories.TrainingRepository;
import Models.Training;
import dbUtils.DbUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingService {
    private static final Logger logger = Logger.getLogger(TrainingService.class.getName());
    private static final String PROPERTIES_PATH = Path.of("main").toAbsolutePath() + "/resources/config.properties";
    private final TrainingRepository trainingRepository;

    public TrainingService(Connection connection) {
        trainingRepository = new TrainingRepository(connection);
    }

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

    public TrainingService() {
        Properties properties = new Properties();
        Connection connection = null;

        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);
            DbUtils dbUtils = new DbUtils(properties.getProperty("db.host"));

            connection = dbUtils.getConnection();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to read properties file", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException trying to get connection", e);
        }
        trainingRepository = new TrainingRepository(connection);
    }

    public List<Training> getUserTraining(int id, LocalDate date) throws SQLException {
        return trainingRepository.getTrainingDataOfUserInMonth(id, date);
    }
}
