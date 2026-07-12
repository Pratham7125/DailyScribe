package com.pc.journalApp.controller;

import com.pc.journalApp.api.response.WeatherResponse;
import com.pc.journalApp.dto.UserRequest;
import com.pc.journalApp.entity.User;
import com.pc.journalApp.respository.UserRepository;
import com.pc.journalApp.service.UserService;
import com.pc.journalApp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "2. User APIs",
        description = "Manage User Profile"
)
@SecurityRequirement(name = "basicAuth")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    @Operation(
//            summary = "Get All Users",
//            description = "Returns the list of all Users"
//    )
//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }

    @Operation(
            summary = "1. Update User Profile",
            description = "Updates email, password and preferences."
    )
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userInDb.setUserName(user.getUserName());
        userInDb.setPassword((user.getPassword()));
        userInDb.setEmail((user.getEmail()));
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "2. Delete User",
            description = "Deletes the User From the Application"
    )
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "3. Greet Users",
            description = "Greets the User with its name and provides Temperature using external Weather API"
    )
    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", Weather feels like "+weatherResponse.getCurrent().getFeelsLike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greeting,HttpStatus.OK);
    }
}
