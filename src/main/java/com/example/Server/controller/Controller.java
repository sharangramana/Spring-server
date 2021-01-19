package com.example.Server.controller;

import com.example.Server.exceptions.*;
import com.example.Server.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class Controller {

    // task 1
    @GetMapping("/")
    public String home() {
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

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String updatedMessage = oldContent.replaceAll("client", message);
            FileWriter writer = new FileWriter(updateFile);
            writer.write(updatedMessage);
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    HashMap<Integer, User> userDetails = new HashMap<>();

    @PostMapping("/users/add/")
    public String addUserDetails(@Valid @RequestBody User user) {
        if (userDetails.containsKey(user.getId())) {
            throw new UserAlreadyPresentException("User is already present with this ID");
        }
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                        "[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            throw new EmailNotValidException("Enter a valid email ID");
        }
        userDetails.put(user.getId(), user);
        System.out.println(Arrays.asList(userDetails));
        return user.toString();
    }

    @PutMapping("/users/update/")
    public String updateUserMobilenumById(@RequestBody(required = false) ObjectNode userObject) {
        try {
            int id = userObject.get("id").asInt();
            String phoneNumber = userObject.get("phoneNumber").asText();
            if (userDetails.containsKey(id)) {
                User temp = userDetails.get(id);
                temp.setPhoneNumber(phoneNumber);
                userDetails.put(id, temp);
                System.out.println(Collections.singletonList(userDetails));
                return "success";
            }
            return "Invalid User ID";
        } catch (Exception e) {
            throw new MissingMobileNumException("Please enter valid Credentials to update");
        }
    }

    // delete
    @DeleteMapping("/users/delete/{id}")
    public String deleteUserById(@PathVariable int id) {
        if (userDetails.containsKey(id)) {
            try {
                userDetails.remove(id);
            } catch (Exception e) {
                throw new SomethingWentWrongException("The requested User is not removed!");
            }
            System.out.println(Collections.singletonList(userDetails));
            return "User is Successfully deleted";
        } else {
            throw new UserNotFoundException("The requested user is not present in the database");
        }
    }

    //get Users List By roles
    @GetMapping("/users/get/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        //ArrayList<User> matchedProfiles = new ArrayList<>();
//        for(Map.Entry<Integer, User> user: userDetails.entrySet()) {
//            if(user.getValue().getRole().equals(role)) {
//                matchedProfiles.add(user.getValue());
//            }
//        }
        List<User> matchedProfiles = userDetails.entrySet().stream()
                .filter(user -> user.getValue().getRole().equals(role))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        //.forEach(matcheduser -> matchedProfiles.add(matcheduser.getValue()));

        System.out.println(Collections.singletonList(matchedProfiles));
        return matchedProfiles;
    }
}
