package Models;

import java.util.Objects;

public class User {
    private final int userID;
    private final String passwordHash;
    private final String login;
    private final int age;
    private final int sex_id;
    private final String email;

    public User(int userID, String passwordHash, String login, int age, int sex_id, String email) {
        this.userID = userID;
        this.passwordHash = passwordHash;
        this.login = login;
        this.age = age;
        this.sex_id = sex_id;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && age == user.age && sex_id == user.sex_id && passwordHash.equals(user.passwordHash) && login.equals(user.login) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, passwordHash, login, age, sex_id, email);
    }
}
