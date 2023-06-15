package org.example;

import Models.User;
import Repositories.UserRepository;
import Service.UserService;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        User user = userService.login("vya", "vya");
        System.out.println(user);
    }
}