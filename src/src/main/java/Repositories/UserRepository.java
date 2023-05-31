package Repositories;

import Models.User;

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

public class UserRepository {
    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());
    private static final String PROPERTIES_PATH = "D:/labs/java first task/src/src/main/resources/config.properties";
    private Connection connection;

    public UserRepository(Connection connection) {
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

    public User getByLogin(String login) throws SQLException {
        logger.log(Level.INFO, "trying to get data by login");
        String query = "SELECT user_id, password, email, user_age, sex_id " +
                "FROM \"user\" WHERE login = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                SexRepository sexRepository = new SexRepository(connection);
                return new User(resultSet.getInt("user_id"), resultSet.getString("password"),
                        login, resultSet.getInt("user_age"), sexRepository.getSexById(resultSet.getInt("sex_id")),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error trying to get user by login", e);
            throw e;
        }
        return null;
    }
}
