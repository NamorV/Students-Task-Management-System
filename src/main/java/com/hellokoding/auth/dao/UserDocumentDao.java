package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.UserDocument;

import java.util.List;

public interface UserDocumentDao {
    void save(UserDocument userDocument, int user_id);

    List<UserDocument> findAllByUserId(int id);

    void delete(int id);
}
