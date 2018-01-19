package com.hellokoding.auth.dao;

public interface StudentCourseDao {
    void addStudentToCourse(int student_id, int course_id);

    boolean isInCourse(int user_id);

    void deleteStudentFromCourse(int studentId, int courseId);

    void deleteAllStudentsFromCourse(int courseId);
}
