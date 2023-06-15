package Service;

import Models.User;
import Repositories.UserRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    private final UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private static final String PROPERTIES_PATH = Path.of("main").toAbsolutePath() + "/resources/config.properties";

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

    public UserService(Connection connection) {
        userRepository = new UserRepository(connection);
    }

    public User login(String login, String password) throws SQLException {
        User user = userRepository.getByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public int getIdByLogin(String login) throws SQLException {
        User user = userRepository.getByLogin(login);
        return user == null ? -1 : user.getUserId();
    }
}
