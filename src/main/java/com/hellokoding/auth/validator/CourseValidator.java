package com.hellokoding.auth.validator;

import com.hellokoding.auth.model.Course;
import com.hellokoding.auth.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CourseValidator implements Validator {
    @Autowired
    private CourseService courseService;

    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean supports(Class<?> aClass) {
        return Course.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Course course = (Course) o;
        Date today = new Date();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (course.getName().length() < 1 || course.getName().length() > 32) {
            errors.rejectValue("name", "Size.courseForm.name");
        }
    }
}
