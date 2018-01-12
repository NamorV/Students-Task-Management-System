package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Course;

import java.util.List;

public interface CourseService {
    void save (Course course);

    List<Course> findByFacultyId(int id);
}
