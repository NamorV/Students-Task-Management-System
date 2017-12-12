package com.hellokoding.auth.service;

import com.hellokoding.auth.model.UserDocument;

import java.util.List;

public interface UserDocumentService {
    void save(UserDocument userDocument);

    UserDocument findById(int id);

    List<UserDocument> findAllByUserId(int userId);

    void deleteById(int id);
}
