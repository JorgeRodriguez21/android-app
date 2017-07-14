package com.example.jrodri.testtddanduiapplication.handlers;

import com.example.jrodri.testtddanduiapplication.models.User;
import com.example.jrodri.testtddanduiapplication.services.UserDataBaseService;

/**
 * Created by jrodri on 6/7/17.
 */

public class RegisterUserHandler {

    private final static String passwordFormat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$";

    private UserDataBaseService userDataBaseService;

    public RegisterUserHandler(UserDataBaseService userDataBaseService) {
        this.userDataBaseService = userDataBaseService;
    }

    public boolean checkUserIsAnAdult(int age) {
        return age >= 18;
    }

    public boolean checkEmptyString(String data) {
        return data.isEmpty();
    }

    public boolean checkValidPassword(String password) {
        return password.matches(passwordFormat);
    }

    public boolean saveInDatabase(User user) {
        return userDataBaseService.saveEntity(user);
    }

    public boolean login(User user) {
        return userDataBaseService.entityExists(user);
    }
}
