package com.hellokoding.auth.dao;


import com.hellokoding.auth.model.Course;

import java.util.Date;
import java.util.List;

public interface CourseDao {

    void save (Course course);

    void delete (int course_id);

    List<Course> findByFacultyId(int id);

    Course findById(int id);

    Date getDueDateForCourse(int id);

    void setDueDate(String dueDate, int courseId);

    boolean isCourseTeacher(int id);
}
