package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.UserDocument;

import java.util.List;

public interface UserDocumentDao {
    void save(UserDocument userDocument, int user_id, int courseId);

    UserDocument findById(int id);

    List<UserDocument> findAllForTeacher(int course_id);

    List<UserDocument> findAllForStudent(int course_id, int user_id, int teacher_id);

    void delete(int id);

    void deleteAllFromCourse(int course_id);
}
