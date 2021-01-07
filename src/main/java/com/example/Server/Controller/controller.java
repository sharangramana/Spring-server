package com.example.Server.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class controller {

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "Hello World!";
    }

    @PostMapping("/")
    @ResponseBody
    public String postMap(@RequestBody String message) {
        System.out.println(message);
        return "success";
    }
}
