package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDocumentRepository")
public interface UserDocumentRepository extends JpaRepository<UserDocument, Long> {
    UserDocument findById(int id);

    List<UserDocument> findAllByUserId(int userId);

    void deleteById(int id);
}
