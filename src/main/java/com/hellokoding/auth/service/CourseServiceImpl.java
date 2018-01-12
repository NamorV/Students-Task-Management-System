package com.hellokoding.auth.service;

import com.hellokoding.auth.dao.CourseDao;
import com.hellokoding.auth.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public List<Course> findByFacultyId(int id) {
        return courseDao.findByFacultyId(id);
    }
}
