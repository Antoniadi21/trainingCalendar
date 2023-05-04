package Service;

import Models.User;
import Repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public User login(String login, String password) {
        User user = userRepository.getByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
