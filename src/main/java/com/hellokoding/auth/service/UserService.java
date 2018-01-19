package com.hellokoding.auth.service;

import com.hellokoding.auth.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(int id);

    List<User> findAllStudents();

    List<User> findAllUsers();

    void deleteById(int userId);
}
