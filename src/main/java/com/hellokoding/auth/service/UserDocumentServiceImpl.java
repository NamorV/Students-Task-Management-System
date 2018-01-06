package com.hellokoding.auth.service;

import com.hellokoding.auth.dao.UserDocumentDao;
import com.hellokoding.auth.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDocumentService")
public class UserDocumentServiceImpl implements UserDocumentService {
    @Autowired
    UserDocumentDao documentDao;

    @Override
    public void save(UserDocument userDocument, int user_id) {
        documentDao.save(userDocument, user_id);
    }

    @Override
    public List<UserDocument> findAllByUserId(int id) {
        return documentDao.findAllByUserId(id);
    }

    @Override
    public void delete(int id) {
        documentDao.delete(id);
    }


}
