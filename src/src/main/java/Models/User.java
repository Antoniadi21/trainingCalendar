package Models;

import java.util.Objects;

public class User {
    private int userID;
    private String passwordHash;
    private String login;
    private int age;
    private int sex_id;
    private String email;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex_id() {
        return sex_id;
    }

    public void setSex_id(int sex_id) {
        this.sex_id = sex_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
