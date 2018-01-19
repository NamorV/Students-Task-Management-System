package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Role;
import com.hellokoding.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        int user_id = (Integer) o;

        if (roleService.isStudent(user_id)) {
            errors.rejectValue("username", "PerformAction.false");
        }
    }
}
