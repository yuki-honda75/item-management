package com.example.repository;

import com.example.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
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
}
