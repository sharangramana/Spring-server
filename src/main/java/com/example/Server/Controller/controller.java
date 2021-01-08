package com.example.Server.Controller;

import com.example.Server.Model.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

@Controller
public class controller {

    // task 1
    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "Hello World!";
    }

    // task 2
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String postMap(@RequestBody String message) {
        System.out.println(message);
        return "success";
    }

    // task 3
    HashMap<Integer, User> userDetails;

    @RequestMapping(value = "/users/add/", method = RequestMethod.POST)
    @ResponseBody
    public String addUserDetails(@RequestBody User user) {
        System.out.println(user.toString());
        userDetails = new HashMap<>();
        userDetails.put(user.getId(), user);
        return user.toString();
    }

    @RequestMapping(value = "/users/update/", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUserMobilenumById(@RequestBody ObjectNode userObject) {
        int id = userObject.get("id").asInt();
        String phoneNumber = userObject.get("phoneNumber").asText();
        if(userDetails.containsKey(id)) {
            User temp = userDetails.get(id);
            temp.setPhoneNumber(phoneNumber);
            userDetails.put(id, temp);
            System.out.println(Arrays.asList(userDetails));
            return "success";
        }
        return "Invalid User ID";
    }
}
