package Models;

import java.util.Objects;

public class User {
    private int userId;
    private String password;
    private String login;
    private int age;
    private int sexId;
    private String email;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", age=" + age +
                ", sexId=" + sexId +
                ", email='" + email + '\'' +
                '}';
    }

    public User(int userId, String password, String login, int age, int sexId, String email) {
        this.userId = userId;
        this.password = password;
        this.login = login;
        this.age = age;
        this.sexId = sexId;
        this.email = email;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && age == user.age && sexId == user.sexId && password.equals(user.password) && login.equals(user.login) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, password, login, age, sexId, email);
    }
}
