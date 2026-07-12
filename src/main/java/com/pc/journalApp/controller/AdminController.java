package com.pc.journalApp.controller;

import com.pc.journalApp.dto.UserRequest;
import com.pc.journalApp.entity.User;
import com.pc.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(
        name = "4. Admin APIs",
        description = "Administrative Operations"
)
@SecurityRequirement(name = "basicAuth")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "2. Create Admin User",
            description = "Creates more Admin Users"
    )
    @PostMapping("/create-admin-user")
    void createUser(@RequestBody UserRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());;
        userService.saveAdmin(user);
    }

    @Operation(
            summary = "1. Get All Users",
            description = "Returns the list of all Users"
    )
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
