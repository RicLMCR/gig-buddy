package com.gigbuddy.restapi.rest;


import com.gigbuddy.restapi.entity.User;
import com.gigbuddy.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    // get user
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId){
        User theUser = userService.findById(userId);
        if (theUser == null ) {
            throw new RuntimeException("User not found - " + userId);
        }
        return theUser;
    }

    // add user
    @PostMapping("/users")
    public User addUser(@RequestBody User theUser){
        theUser.setId(0);
        User dbUser = userService.save(theUser);
        return dbUser;
    }
    // edit user
    @PutMapping("/users")
        public User updateUser(@RequestBody User theUser){
            User dbUser = userService.save(theUser);
            return dbUser;
    }
    // delete user
    @DeleteMapping("/users/{userId}")
        public String deleteUser(@PathVariable int userId){
            User tempUser = userService.findById(userId);
            if(tempUser == null){
                throw new RuntimeException("User id not found - " + userId);
            }
            userService.deleteById(userId);
            return "Deleted user id - " + userId;
        }
    }

