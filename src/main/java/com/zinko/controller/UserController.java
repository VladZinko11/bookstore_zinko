package com.zinko.controller;

import com.zinko.data.dao.connection.ConnectionContext;
import com.zinko.data.dao.entity.User;
import com.zinko.service.UserService;
import com.zinko.service.dto.UserDto;
import com.zinko.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class UserController {
    public static void main(String[] args) {
        ConnectionContext.InitDb();
        UserService userService = new UserServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean execution = true;
        System.out.println("""
                Enter the command:
                     all - to get a list of all users
                     get - to get user by id
                     login - to check authentication
                     delete - to delete user by id
                     create - to create and add user
                     update - to update user
                     exit - to finish work with the catalog
                     """);
        while (execution) {
            String command = scanner.nextLine();
            switch (command) {
                case "all" -> userService.findAll().forEach(System.out::println);
                case "get" -> {
                    System.out.println("Enter id: ");
                    String id = scanner.nextLine();
                    UserDto user = userService.findById(Long.parseLong(id));
                    if (user != null)
                        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getEmail());
                    else System.out.println("user with id = " + id + "not found");
                }
                case "delete" -> {
                    System.out.println("Enter id: ");
                    String id = scanner.nextLine();
                    UserDto user = userService.findById(Long.parseLong(id));
                    if (userService.delete(Long.parseLong(id)))
                        System.out.println(user.getFirstName() + " " + user.getLastName() + " is deleted");
                    else System.out.println("User with id = " + id + " not found");
                }
                case "create" -> {
                    System.out.println("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.println("Enter role");
                    String role = scanner.nextLine();
                    User user = userService.createUser(null, firstName, lastName, email, password, role);
                    if (userService.create(user)) {
                        UserDto userDto = userService.createUserDtoFromUser(user);
                        System.out.println(userDto.getFirstName() + " " + userDto.getLastName() + " is created");
                    } else System.out.println("User with email = " + user.getEmail() + " is exist");
                }
                case "update" -> {
                    System.out.println("Enter id user that you want to update");
                    Long id = scanner.nextLong();
                    if (userService.findById(id) != null) {
                        System.out.println("Enter first name or empty line: ");
                        scanner.nextLine();
                        String firstName = scanner.nextLine();
                        System.out.println("Enter last name or empty line: ");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter email or empty line: ");
                        String email = scanner.nextLine();
                        System.out.println("Enter password or empty line: ");
                        String password = scanner.nextLine();
                        System.out.println("Enter role or empty line");
                        String role = scanner.nextLine();
                        User user = userService.createUser(id, firstName, lastName, email, password, role);
                        if(userService.update(user)) System.out.println(userService.findById(id) + " is updated");
                    } else System.out.println("User with id = " + id + " not found");
                }
                case "login" -> {
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter your password: ");
                    String password = scanner.nextLine();
                    UserDto userDto = userService.login(email, password);
                    if(userDto!=null)
                        System.out.println(userDto.getFirstName() + " " + userDto.getLastName());
                    else System.out.println("authentication error, incorrect password or email");
                }
                case "exit" -> execution = false;
            }
        }
    }
}