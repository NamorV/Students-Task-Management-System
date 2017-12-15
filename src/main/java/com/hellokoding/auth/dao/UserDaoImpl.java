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
        String sql = "insert into app_user values(?,?,?)";
        jdbcTemplate.update(sql, new Object[]{null, user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword())});
    }

    @Override
    @Transactional(readOnly=true)
    public User findByUsername(String username) {
        String sql = "select * from app_user where username='" + username + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        //String password = bCryptPasswordEncoder.(users.get(0).getPassword());
        System.out.println(users.get(0).getPassword());
        return users.size() > 0 ? users.get(0) : null;
    }
}

class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));

        return user;
    }
}

