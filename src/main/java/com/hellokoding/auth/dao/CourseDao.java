package com.hellokoding.auth.dao;


import com.hellokoding.auth.model.Course;

import java.util.List;

public interface CourseDao {

    void save (Course course);

    void delete (int id);

    List<Course> findByFacultyId(int id);
}
