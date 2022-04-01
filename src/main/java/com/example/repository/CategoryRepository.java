package com.example.repository;

import java.util.List;

import com.example.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author hondayuki
 *
 */
@Repository
public class CategoryRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

    public List<Category> findAll() {
        String sql = "SELECT id, parent, name, name_all FROM category ORDER BY id";
        List<Category> categoryList = template.query(sql, new MapSqlParameterSource(), CATEGORY_ROW_MAPPER);

        return categoryList;
    }
}
