package com.example.Server.Controller;

import com.example.Server.Model.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class controller {

    // task 1
    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    // task 2
    @PostMapping("/")
    public String postMap(@RequestBody String message) {
        System.out.println(message);
        return "success";
    }
    // task 3
    @PostMapping("/passmessage")
    public void storeValue(@RequestBody String message) {
        System.out.println(message);
        try (PrintWriter out = new PrintWriter("File.txt")) {
            out.println(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/updatemessage")
    public void updateValue(@RequestBody String message) throws IOException {
        System.out.println(message);
        File updateFile = new File("File.txt");
        String oldContent = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(updateFile));
            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String updatedMessage = oldContent.replaceAll("client", message);
            FileWriter writer = new FileWriter(updateFile);
            writer.write(updatedMessage);
            reader.close();
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    HashMap<Integer, User> userDetails = new HashMap<>();

    @PostMapping("/users/add/")
    public String addUserDetails(@RequestBody User user) {
        userDetails.put(user.getId(), user);
        System.out.println(Arrays.asList(userDetails));
        return user.toString();
    }

    @PutMapping("/users/update/")
    public String updateUserMobilenumById(@RequestBody(required = false) ObjectNode userObject) {
        try {
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
        catch (Exception e) {
            return "Please enter valid Credentials to update";
        }
    }

    // delete
    @DeleteMapping("/users/delete/{id}")
    public void deleteUserById(@PathVariable int id) {
        userDetails.remove(id);
        System.out.println(Arrays.asList(userDetails));
    }

    //get Users List By roles
    @GetMapping("/users/get/{role}")
    public ArrayList getUsersByRole(@PathVariable String role) {
        ArrayList<User> matchedProfiles = new ArrayList<>();
        for(Map.Entry<Integer, User> user: userDetails.entrySet()) {
            if(user.getValue().getRole().equals(role)) {
                matchedProfiles.add(user.getValue());
            }
        }
        System.out.println(Arrays.asList(userDetails));
        return matchedProfiles;
    }
}
