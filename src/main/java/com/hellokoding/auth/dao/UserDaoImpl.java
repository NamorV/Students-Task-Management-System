package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    DataSource datasource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        String sql = "insert into app_user values(?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{null, user.getUsername(), user.getPassword(), user.getFaculty_id(),
                            user.getRole_id()});
    }

    @Override
    @Transactional(readOnly=true)
    public User findByUsername(String username) {
        String sql = "select * from app_user where username='" + username + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    @Transactional(readOnly=true)
    public User findById(int id) {
        String sql = "select * from app_user where id='" + id + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public List<User> findAllStudents() {
        String sql = "select * from app_user where role_id= 3";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users;
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "select * from app_user where not role_id = 1";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users;
    }

    @Override
    public void deleteById(int userId) {
        String sql = "delete from user_document where user_id = '" + userId + "'";
        jdbcTemplate.execute(sql);
        sql = "delete from user_roles where user_id = '" + userId + "'";
        jdbcTemplate.execute(sql);
        sql = "delete from student_course where student_id = '" + userId + "'";
        jdbcTemplate.execute(sql);
        sql = "delete from course where teacher_id = '" + userId + "'";
        jdbcTemplate.execute(sql);
        sql = "delete from app_user where id = '" + userId + "'";
        jdbcTemplate.execute(sql);
    }
}

class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFaculty_id(rs.getInt("faculty_id"));
        user.setRole_id(rs.getInt("role_id"));

        return user;
    }
}

