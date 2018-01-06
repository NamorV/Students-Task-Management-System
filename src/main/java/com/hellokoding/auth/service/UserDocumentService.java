package com.hellokoding.auth.service;

import com.hellokoding.auth.model.UserDocument;

import java.util.List;

public interface UserDocumentService {
    void save(UserDocument userDocument, int user_id);

    List<UserDocument> findAllByUserId(int id);

    UserDocument findById(int id);

    void delete(int id);
}
