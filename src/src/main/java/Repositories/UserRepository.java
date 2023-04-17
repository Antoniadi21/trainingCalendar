package Repositories;

import Models.User;
import dbUtils.DbUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository {
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

    public List<User> getUserData(String query) {//select id, name, ... instead of select *
        List<User> users = new ArrayList<>();

        logger.log(Level.INFO, "trying to get data from user");
        DbUtils dbUtils = new DbUtils();
        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String password = resultSet.getString("password");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("user_age");
                int sex = resultSet.getInt("sex_id");

                users.add(new User(id, password, login, age, sex, email));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from user", e);
        }
        logger.log(Level.INFO, "finished getting data from user");
        return users;
    }

    public void insertUser(User user) {
        logger.log(Level.INFO, "trying to insert data into user");
        String insertUser = "INSERT INTO \"user\" (\"password\", login, email, user_age, sex_id) " +
                            "VALUES (?, ?, ?, ?, ?)";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUser)) {

            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setInt(5, user.getSexId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to insert data into user", e);
        }
        logger.log(Level.INFO, "finished inserting data into user");
    }

    public void updateUser(User user) {
        logger.log(Level.INFO, "started trying to update user");
        String updateUser = "Update \"user\"" +
                            "SET \"password\" = ?, login = ?, email = ?, user_age = ?, sex_id = ?" +
                            "WHERE user_id = ?";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateUser)) {

            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setInt(5, user.getSexId());
            preparedStatement.setInt(6, user.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to update user", e);
        }
        logger.log(Level.INFO, "finished updating user");
    }

    public void deleteUser(int id) {
        logger.log(Level.INFO, "started trying to delete user");
        String deleteUser = "DELETE FROM \"user\"" +
                            "WHERE user_id = ?";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUser)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to delete user", e);
        }
        logger.log(Level.INFO, "finished deleting user");
    }
}
