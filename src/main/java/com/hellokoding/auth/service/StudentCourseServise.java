package com.hellokoding.auth.service;

public interface StudentCourseServise {
    void addStudentToCourse(int student_id, int course_id);

    boolean isInCourse(int user_id, int course_id);

    void deleteStudentFromCourse(int studentId, int courseId);

    void deleteAllStudentsFromCourse(int courseId);
}
