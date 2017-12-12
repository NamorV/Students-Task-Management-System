package com.hellokoding.auth.service;

import com.hellokoding.auth.model.UserDocument;
import com.hellokoding.auth.repository.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {

    @Autowired
    private UserDocumentRepository userDocumentRepositoryRepository;

    @Override
    public void save(UserDocument userDocument) {
        userDocumentRepositoryRepository.save(userDocument);
    }

    @Override
    public UserDocument findById(int id) {
        return userDocumentRepositoryRepository.findById(id);
    }

    @Override
    public List<UserDocument> findAllByUserId(int userId) {
        return userDocumentRepositoryRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteById(int id) {
        userDocumentRepositoryRepository.findById(id);
    }
}
