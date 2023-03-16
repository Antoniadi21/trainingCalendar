package Repositories;

import Models.User;
import dbUtils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository {
    private static Logger logger = Logger.getLogger(UserRepository.class.getName());
    private static String logPath = "src/main/java/Logs/logs.txt";

    static {
        try {
            logger.addHandler(new FileHandler(logPath));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException trying to add handler to logger");
        }
    }

    public static List<User> getUserData(String query) {
        List<User> users = new ArrayList<>();

        logger.log(Level.INFO, "trying to get data from db");
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String password = resultSet.getString("password_hash");
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("user_age");
                int sex = resultSet.getInt("sex_id");

                users.add(new User(id, password, login, age, sex, email));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from db");
        }
        logger.log(Level.INFO, "finished getting data from db");
        return users;
    }
}
