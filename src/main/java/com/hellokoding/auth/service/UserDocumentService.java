package com.hellokoding.auth.service;

import com.hellokoding.auth.model.UserDocument;

import java.util.List;

public interface UserDocumentService {
    void save(UserDocument userDocument, int user_id, int courseId);

    List<UserDocument> findAllForTeacher(int course_id);

    List<UserDocument> findAllForStudent(int course_id, int user_id, int teacher_id);

    UserDocument findById(int id);

    void delete(int id);

    void deleteAllFromCourse(int course_id);
}
