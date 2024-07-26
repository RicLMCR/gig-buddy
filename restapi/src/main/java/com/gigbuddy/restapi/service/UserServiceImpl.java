package com.gigbuddy.restapi.service;

//import com.gigbuddy.restapi.dao.LoginCredentialsRepository;
//import com.gigbuddy.restapi.dao.LoginCredentialsRepository;
import com.gigbuddy.restapi.dao.UserRepository;
//import com.gigbuddy.restapi.entity.LoginCredentials;
//import com.gigbuddy.restapi.entity.LoginCredentials;
import com.gigbuddy.restapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    // talk to database
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }

//    ***
//    @Autowired
//    public LoginCredentialsRepository loginCredentialsRepository;

    // services logic
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId){
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if(result.isPresent()){
            theUser = result.get();
        }
        else {
            throw new RuntimeException("Did not find user id - " + theId);
        }
        return theUser;
    }

//    ***
    @Override
    public User save(User theUser){
        return userRepository.save(theUser);
    }

//    public User save(User theUser, String thePassword){
//        User savedUser = userRepository.save(theUser);
//        String password = thePassword;
//        LoginCredentials loginCredentials = new LoginCredentials(savedUser, password);
//        loginCredentialsRepository.save(loginCredentials);
//        savedUser.setLoginCredentials(loginCredentials);
//
//        return savedUser;
////        return userRepository.save(theUser);
//    }

    @Override
    public void deleteById(int theId){
        userRepository.deleteById(theId);
    }
}

