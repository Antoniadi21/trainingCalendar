package Repositories;

import Models.Training;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingRepository {
    private static final Logger logger = Logger.getLogger(TrainingRepository.class.getName());
    private static final String PROPERTIES_PATH = Path.of("main").toAbsolutePath() + "/resources/config.properties";
    private final Connection connection;

    public TrainingRepository(Connection connection) {
        this.connection = connection;
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

    public List<Training> getTrainingDataOfUserInMonth(int userId, LocalDate date) throws SQLException {
        List<Training> trainings = new ArrayList<>();
        String query = "SELECT training_id, duration_in_minutes, type_id, training_date FROM training" +
                " WHERE user_id = ? AND date_part('MONTH', training_date) = ? AND date_part('YEAR', training_date) = ?";

        logger.log(Level.INFO, "trying to get data from db");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, date.getMonthValue());
            preparedStatement.setInt(3, date.getYear());
            ResultSet resultSet = preparedStatement.executeQuery();
            TrainingTypeRepository trainingTypeRepository = new TrainingTypeRepository(connection);

            while (resultSet.next()) {
                LocalDate trainingDate = LocalDate.parse(resultSet.getString("training_date"));
                int id = resultSet.getInt("training_id");
                int type = resultSet.getInt("type_id");
                int durationInMinutes = resultSet.getInt("duration_in_minutes");

                trainings.add(new Training(id, userId, durationInMinutes, trainingDate, trainingTypeRepository.getTrainingTypeById(type)));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from training", e);
            throw e;
        }
        logger.log(Level.INFO, "finished getting data from training");
        return trainings;
    }
}
