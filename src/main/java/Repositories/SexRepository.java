package Repositories;

import Models.Sex;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SexRepository {
    private static final Logger logger = Logger.getLogger(SexRepository.class.getName());
    private static final String PROPERTIES_PATH = Path.of("main").toAbsolutePath() + "/resources/config.properties";
    private Connection connection;

    public SexRepository(Connection connection) {
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

    public Sex getSexById(int id) throws SQLException {
        logger.log(Level.INFO, "started trying to get data from table sex");
        String query = "SELECT sex_id, sex FROM sex WHERE sex_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                char sex = resultSet.getString("sex").charAt(0);
                logger.log(Level.INFO, "finished getting data from table sex");
                return new Sex(id, sex);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from table sex", e);
            throw e;
        }
        return null;
    }
}
