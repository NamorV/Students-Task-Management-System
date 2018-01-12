package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean isStudent(int user_id) {
        String sql = "select * from role where id = (select role_id from user_roles where user_id = "
                     + user_id +");";

        List<Role> role = jdbcTemplate.query(sql, new RoleMapper());
        if(role.get(0).getRole().equals("student")) {
            return true;
        } else {
            return false;
        }
    }
}

class RoleMapper implements RowMapper<Role> {

    public Role mapRow(ResultSet rs, int arg1) throws SQLException {
        Role role = new Role();

        role.setId(rs.getInt("id"));
        role.setRole(rs.getString("role"));

        return role;
    }
}