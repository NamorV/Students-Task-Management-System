package com.hellokoding.auth.service;

import com.hellokoding.auth.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public boolean isStudent(int user_id) {
        return roleDao.isStudent(user_id);
    }
}
