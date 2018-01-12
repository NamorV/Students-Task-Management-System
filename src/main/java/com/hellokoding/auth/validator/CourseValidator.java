package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Course;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CourseValidator implements Validator {
    @Autowired
    private CourseService courseService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Course course = (Course) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (course.getName().length() < 1 || course.getName().length() > 32) {
            errors.rejectValue("name", "Size.courseForm.name");
        }
    }
}
