package com.gigbuddy.restapi.dao;

import com.gigbuddy.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
