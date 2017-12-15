package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.User;

public interface UserDao {

    void save (User user);

    User findByUsername(String username);
}
