package com.example.Server.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;

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
    @RequestMapping(value = "/passmessage", method = RequestMethod.POST)
    @ResponseBody
    public void storeValue(@RequestBody String message) {
        System.out.println(message);
        try (PrintWriter out = new PrintWriter("File.txt")) {
            out.println(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updatemessage", method = RequestMethod.PUT)
    @ResponseBody
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
}
