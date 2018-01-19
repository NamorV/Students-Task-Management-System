package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.User;

import java.util.List;

public interface UserDao {

    void save (User user);

    User findByUsername(String username);

    User findById(int id);

    List<User> findAllStudents();

    List<User> findAllUsers();

    void deleteById(int userId);
}
