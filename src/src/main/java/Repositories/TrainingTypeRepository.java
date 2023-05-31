package Repositories;

import Models.TrainingType;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainingTypeRepository {
    private static final Logger logger = Logger.getLogger(TrainingTypeRepository.class.getName());
    private static final String PROPERTIES_PATH = "src/src/main/resources/config.properties";
    private Connection connection;

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

    public TrainingTypeRepository(Connection connection) {
        this.connection = connection;
    }

    public TrainingType getTrainingTypeById(int id) throws SQLException {
        logger.log(Level.INFO, "started trying to get data from table training_type");
        String query = "SELECT type_id, \"type\" FROM training_type WHERE type_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String type = resultSet.getString("type");
                logger.log(Level.INFO, "finished getting data from table training_type");
                return new TrainingType(id, type);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from table training_type", e);
            throw e;
        }
        return null;
    }
}
