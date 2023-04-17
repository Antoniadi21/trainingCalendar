package Repositories;

import Models.Sex;
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

public class SexRepository {
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

    public List<Sex> getSexData(String query) {
        logger.log(Level.INFO, "started trying to get data from table sex");
        List<Sex> result = new ArrayList<>();
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("sex_id");
                char sex = resultSet.getString("sex").charAt(0);

                result.add(new Sex(id, sex));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to get data from table sex", e);
        }
        logger.log(Level.INFO, "finished getting data from table sex");
        return result;
    }

    public void insertSex(Sex sex) {
        logger.log(Level.INFO, "started trying to insert data into sex");
        String insertSex = "INSERT INTO sex (sex) " +
                           "VALUES (?)";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSex)) {

            preparedStatement.setString(1, Character.toString(sex.getSex()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to insert data into sex", e);
        }
        logger.log(Level.INFO, "finished inserting data into sex");
    }

    public void updateSex(Sex sex) {
        logger.log(Level.INFO, "started trying to update sex");
        String updateSex = "UPDATE sex " +
                           "SET sex = ?" +
                           "WHERE sex_id = ?";
        DbUtils dbUtils = new DbUtils();

        try (Connection connection = dbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSex)) {

            preparedStatement.setString(1, Character.toString(sex.getSex()));
            preparedStatement.setInt(2, sex.getSexId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to update data in sex", e);
        }
        logger.log(Level.INFO, "finished updating data into sex");
    }
}
