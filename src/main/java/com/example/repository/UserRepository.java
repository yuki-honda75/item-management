package com.example.repository;

import java.util.List;

import com.example.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author hondayuki
 *
 */
@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    /**
     * ユーザーを登録します
     * 
     * @param user
     */
    public void insert(User user) {
        String sql = "INSERT INTO users (name, password, authority) VALUES (:name, :password, 0)";
        SqlParameterSource param = 
            new MapSqlParameterSource()
            .addValue("name", user.getName()).addValue("password", user.getPassword());

        template.update(sql, param);
    }

    public User findByName(String name) {
        String sql = "SELECT id, name, password, authority FROM users WHERE name=:name";
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", name);
        List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
        if (userList.isEmpty()) {
            return null;
        }
        
        return userList.get(0);
    }
}
