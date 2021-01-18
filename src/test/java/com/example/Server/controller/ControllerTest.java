package com.example.Server.controller;

import com.example.Server.exceptions.MissingMobileNumException;
import com.example.Server.exceptions.UserAlreadyPresentException;
import com.example.Server.exceptions.UserNotFoundException;
import com.example.Server.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void addUserDetails() {
        User user = new User(123, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "admin");
        Controller controller = new Controller();

        assertEquals(user.toString(), controller.addUserDetails(user));

        Throwable exception = assertThrows(UserAlreadyPresentException.class, () -> controller.addUserDetails(user));
        assertEquals("User is already present with this ID", exception.getMessage());
    }

    @Test
    void updateUserMobilenumById() {
        User user = new User(123, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "admin");
        Controller controller = new Controller();
        controller.addUserDetails(user);

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("id", 123);
        objectNode.put("phoneNumber", "96000060700");
        assertEquals("success", controller.updateUserMobilenumById(objectNode));

        objectNode.put("id", 0);
        objectNode.put("phoneNumber", "96000060700");
        assertEquals("Invalid User ID", controller.updateUserMobilenumById(objectNode));

        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode.put("id", 123);
        Throwable exception1 = assertThrows(MissingMobileNumException.class, () -> controller.updateUserMobilenumById(objectNode1));
        assertEquals("Please enter valid Credentials to update", exception1.getMessage());

        ObjectNode objectNode2 = mapper.createObjectNode();
        objectNode.put("phoneNumber", "96000060700");
        Throwable exception2 = assertThrows(MissingMobileNumException.class, () -> controller.updateUserMobilenumById(objectNode2));
        assertEquals("Please enter valid Credentials to update", exception2.getMessage());
    }


    @Test
    void deleteUserById() {
        User user = new User(123, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "admin");
        Controller controller = new Controller();
        controller.addUserDetails(user);
        assertEquals("User is Successfully deleted", controller.deleteUserById(123));

        Throwable exception = assertThrows(UserNotFoundException.class, () -> controller.deleteUserById(120));
        assertEquals("The requested user is not present in the database", exception.getMessage());
    }

    @Test
    void getUsersByRole() {
        User user1 = new User(123, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "admin");
        User user2 = new User(124, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "admin");
        User user3 = new User(125, "sharang", "Male",
                "sharang@gmail.com", "9688060700", "trainee");
        Controller controller = new Controller();
        controller.addUserDetails(user1);
        controller.addUserDetails(user2);
        controller.addUserDetails(user3);

        List<User> actualList = Arrays.asList(user1, user2);
        List<User> responseList = controller.getUsersByRole("admin");

        assertEquals(actualList, responseList);
    }
}