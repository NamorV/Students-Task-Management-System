package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Course course) {
        String sql = "insert into course values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{null, course.getName(), course.getTeacher_id(), course.getFaculty_id(),
                            course.getDueDate()});
    }

    @Override
    public void delete(int course_id) {
        String sql = "delete from course where id = '" + course_id + "'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public List<Course> findByFacultyId(int id) {
        String sql = "select * from course where faculty_id = " + id + ";";
        List<Course> faculties = jdbcTemplate.query(sql, new CourseMapper());
        return faculties;
    }

    @Override
    public Course findById(int id) {
        String sql = "select * from course where id='" + id + "'";
        List<Course> courses = jdbcTemplate.query(sql, new CourseMapper());
        return courses.size() > 0 ? courses.get(0) : null;
    }

    @Override
    public Date getDueDateForCourse(int id) {
        Course course = findById(id);
        return course.getDueDate();
    }

    @Override
    public void setDueDate(String dueDate, int courseId) {
        String sql = "update course set due_date ='" + dueDate + "' where id =" + courseId + ";";
        jdbcTemplate.execute(sql);
    }

    @Override
    public boolean isCourseTeacher(int id) {
        String sql = "select * from course where teacher_id = " + id +";";
        List<Course> courses = jdbcTemplate.query(sql, new CourseMapper());
        return courses.size() > 0 ? true : false;
    }


}

class CourseMapper implements RowMapper<Course> {

    public Course mapRow(ResultSet rs, int arg1) throws SQLException {
        Course course = new Course();

        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setFaculty_id(rs.getInt("faculty_id"));
        course.setTeacher_id(rs.getInt("teacher_id"));
        course.setDueDate(rs.getDate("due_date"));
        return course;
    }
}