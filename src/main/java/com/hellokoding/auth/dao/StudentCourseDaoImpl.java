package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentCourseDaoImpl implements StudentCourseDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addStudentToCourse(int student_id, int course_id) {
        String sql = "insert into student_course values(?,?,?)";
        jdbcTemplate.update(sql, new Object[]{null, student_id, course_id});
    }

    @Override
    public boolean isInCourse(int user_id) {
        String sql = "select * from student_course where student_id='" + user_id + "'";
        List<StudentCourse> users = jdbcTemplate.query(sql, new SudentCourseMapper());
        return users.size() > 0 ? true : false;
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        String sql = "delete from student_course where student_id = '"+ studentId +"' and " +
                "course_id = '"+ courseId +"'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        String sql = "delete from student_course where course_id = '"+ courseId +"'";
        jdbcTemplate.execute(sql);
    }
}

class SudentCourseMapper implements RowMapper<StudentCourse> {

    public StudentCourse mapRow(ResultSet rs, int arg1) throws SQLException {
        StudentCourse user = new StudentCourse();

        user.setId(rs.getInt("id"));
        user.setStudent_id(rs.getInt("student_id"));
        user.setCourse_id(rs.getInt("course_id"));

        return user;
    }
}
