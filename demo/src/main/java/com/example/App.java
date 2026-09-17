package com.example;

import com.example.controller.Controller;
import com.example.model.User;

public class App {

    public static void main(String[] args) {

        Controller controller = new Controller();

        User user = controller.getUserByUsername("admin");

        System.out.println("Reading user info from the database...");
        System.out.println("User email: " + user.getEmail());

        System.out.println("Inserting user...");
        if (controller.createUser("Pesho", "Peshov@example.com", "Pesho123"))
            System.out.println("User created successfully.");
        else 
            System.out.println("Failed to create user.");
        
    }
}