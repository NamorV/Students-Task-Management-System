package com.hellokoding.auth.dao;


import com.hellokoding.auth.model.Faculty;

import java.util.List;

public interface FacultyDao {
    List<Faculty> findAll();

    Faculty getFacultyById(int id);
}
