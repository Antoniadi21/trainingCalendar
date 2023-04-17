package Repositories;

import Models.Training;
import dbUtils.DbUtils;

import java.io.FileInputStream;
import java.io.IOException;
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
    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());
    private static final String PROPERTIES_PATH = "src/src/main/resources/config.properties";

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

    public List<Training> getTrainingData(String query) {
        List<Training> trainings = new ArrayList<>();

        logger.log(Level.INFO, "trying to get data from db");
        DbUtils dbUtils = new DbUtils();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("training_id");
                int userId = resultSet.getInt("user_id");
                int durationInMinutes = resultSet.getInt("duration_in_minutes");
                LocalDate date = LocalDate.parse(resultSet.getString("training_date"));

                trainings.add(new Training(id, userId, durationInMinutes, date));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from training", e);
        }
        logger.log(Level.INFO, "finished getting data from training");
        return trainings;
    }

    public void insertTraining(Training training) {
        logger.log(Level.INFO, "trying to insert data into training");
        String insertTraining = "INSERT INTO training (user_id, duration_in_minutes, training_date) VALUES(?, ?, ?)";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertTraining)) {

            preparedStatement.setInt(1, training.getUserId());
            preparedStatement.setInt(2, training.getDurationInMinutes());
            preparedStatement.setString(3, training.getDate().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to insert data into training", e);
        }
        logger.log(Level.INFO, "finished inserting data into training");
    }

    public void updateTraining(Training training) {
        logger.log(Level.INFO, "trying to update training");
        String updateTraining = "Update training " +
                                "SET user_id = ?, duration_in_minutes = ?, training_date = ? " +
                                "WHERE training_id = ?";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateTraining)) {

            preparedStatement.setInt(1, training.getUserId());
            preparedStatement.setInt(2, training.getDurationInMinutes());
            preparedStatement.setString(3, training.getDate().toString());
            preparedStatement.setInt(4, training.getTrainingId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to update training", e);
        }
        logger.log(Level.INFO, "finished updating training");
    }

    public void deleteTraining(int id) {
        logger.log(Level.INFO, "trying to delete training");
        String deleteTraining = "DELETE FROM training " +
                                "WHERE training_id = ?";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteTraining)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to delete user", e);
        }
        logger.log(Level.INFO, "finished deleting user");
    }
}
