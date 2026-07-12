package com.pc.journalApp.controller;

import com.pc.journalApp.dto.UserRequest;
import com.pc.journalApp.entity.User;
import com.pc.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "1. Public APIs",
        description = "User Registration and Public Endpoints"
)
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "2. Health Check",
            description = "Checks if the Application is up and running"
    )
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @Operation(
            summary = "1. Create User",
            description = "Registers a new user."
    )
    @PostMapping(("/create-user"))
    public void createUser(@RequestBody UserRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userService.saveNewUser(user);
    }

}
