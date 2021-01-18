package com.example.Server.controller;

import com.example.Server.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void addUserDetails() {
        User user = new User(123, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "admin");
        Controller controller = new Controller();
        String response = controller.addUserDetails(user);
        assertEquals(user.toString(), response);
    }

}