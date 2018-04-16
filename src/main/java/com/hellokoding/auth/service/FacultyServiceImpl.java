package com.hellokoding.auth.service;


import com.hellokoding.auth.dao.FacultyDao;
import com.hellokoding.auth.model.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("facultyService")
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyDao facultyDao;

    @Override
    public List<Faculty> findAll() {
        return facultyDao.findAll();
    }

    @Override
    public Faculty getFacultyById(int id) {
        return facultyDao.getFacultyById(id);
    }
}
