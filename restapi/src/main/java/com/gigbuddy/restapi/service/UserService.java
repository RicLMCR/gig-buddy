package com.gigbuddy.restapi.service;

import com.gigbuddy.restapi.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    // add additional CRUD commands - delete(by id)
    User save(User theUser);

    void deleteById(int theId)
;}
