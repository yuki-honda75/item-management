package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * 
 * @author hondayuki
 *
 */
@Repository
public class ItemRepository {
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
	
	@Autowired
    private NamedParameterJdbcTemplate template;
	
	public Page<Item> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" id, name, condition, category, brand, price, shipping, description");
		sql.append(" FROM items");
		sql.append(" LIMIT "); 
		sql.append(pageable.getPageSize());
		sql.append(" OFFSET ");
		sql.append(pageable.getOffset());
		int count = template.queryForObject("SELECT count(id) FROM items", new MapSqlParameterSource(), Integer.class);
		List<Item> itemList = template.query(sql.toString(), ITEM_ROW_MAPPER);
		
		return new PageImpl<>(itemList, pageable, count);
	}
	
}
