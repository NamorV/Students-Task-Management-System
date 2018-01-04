package com.hellokoding.auth.service;

import com.hellokoding.auth.model.UserDocument;

public interface UserDocumentService {
    void save(UserDocument userDocument, int user_id);

    void findByUserId(int id);
}
