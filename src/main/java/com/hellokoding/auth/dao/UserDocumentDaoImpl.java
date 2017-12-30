package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDocumentDaoImpl implements UserDocumentDao {
    @Autowired
    DataSource datasource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserDocument userDocument, int user_id) {
        String sql = "insert into user_document values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{null, user_id, userDocument.getName(),
                            userDocument.getDescription(), userDocument.getType(), userDocument.getContent()});
    }

    @Override
    @Transactional(readOnly=true)
    public List<UserDocument> findAllByUserId(int user_id) {
        String sql = "select * from user_document where user_id='" + user_id + "'";
        List<UserDocument> documents = jdbcTemplate.query(sql, new DocumentMapper());
        //String password = bCryptPasswordEncoder.(users.get(0).getPassword());
        //System.out.println(users.get(0).getPassword());
        return documents;
    }
}

class DocumentMapper implements RowMapper<UserDocument> {

    public UserDocument mapRow(ResultSet rs, int arg1) throws SQLException {
        UserDocument document = new UserDocument();

        document.setUserId(rs.getInt("user_id"));
        document.setName(rs.getString("name"));
        document.setDescription(rs.getString("description"));
        document.setType(rs.getNString("type"));
        document.setContent(rs.getBytes("content"));

        return document;
    }
}

