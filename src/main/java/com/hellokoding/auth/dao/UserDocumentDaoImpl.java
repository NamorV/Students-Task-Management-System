package com.hellokoding.auth.dao;

import com.hellokoding.auth.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDocumentDaoImpl implements UserDocumentDao {
    @Autowired
    DataSource datasource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserDocument userDocument, int user_id, int courseId) {
        String sql = "insert into user_document values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{null, user_id, userDocument.getName(),
                            userDocument.getDescription(), userDocument.getType(), userDocument.getContent(), courseId});
    }

    @Override
    public UserDocument findById(int id) {
        String sql = "select * from user_document where id='" + id + "'";
        List<UserDocument> document = jdbcTemplate.query(sql, new DocumentMapper());
        return document.size() > 0 ? document.get(0) : null;
    }

    @Override
    @Transactional(readOnly=true)
    public List<UserDocument> findAllForTeacher(int course_id) {
        String sql = "select * from user_document WHERE course_id = " + course_id + ";";
        List<UserDocument> documents = jdbcTemplate.query(sql, new DocumentMapper());
        return documents;
    }

    @Override
    public List<UserDocument> findAllForStudent(int course_id, int user_id) {
        String sql = "select * from user_document WHERE course_id = " + course_id + " and user_id = "
                + user_id +";";
        List<UserDocument> documents = jdbcTemplate.query(sql, new DocumentMapper());
        return documents;
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user_document where id = '" + id + "'";
        jdbcTemplate.execute(sql);
    }
}

class DocumentMapper implements RowMapper<UserDocument> {

    public UserDocument mapRow(ResultSet rs, int arg1) throws SQLException {
        UserDocument document = new UserDocument();

        document.setId(rs.getInt("id"));
        document.setUserId(rs.getInt("user_id"));
        document.setName(rs.getString("name"));
        document.setDescription(rs.getString("description"));
        document.setType(rs.getString("type"));
        document.setContent(rs.getBytes("content"));
        document.setCourse_id(rs.getInt("course_id"));

        return document;
    }
}

