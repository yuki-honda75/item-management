package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));
        item.setCondition(rs.getInt("condition"));
        item.setSCategory(rs.getString("s_category"));
        item.setMCategory(rs.getString("m_category"));
        item.setLCategory(rs.getString("l_category"));
        item.setBrand(rs.getString("brand"));
        item.setPrice(rs.getDouble("price"));
        item.setShipping(rs.getInt("shipping"));
        item.setDescription(rs.getString("description"));
        return item;
    };
	
	@Autowired
    private NamedParameterJdbcTemplate template;
	
	public Page<Item> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" i.id as item_id, i.name as item_name, condition, sc.name as s_category, mc.name as m_category, lc.name as l_category, brand, price, shipping, description");
		sql.append(" FROM items as i LEFT OUTER JOIN category as sc ON i.category = sc.id");
        sql.append(" LEFT OUTER JOIN category as mc ON sc.parent = mc.id");
        sql.append(" LEFT OUTER JOIN category as lc ON mc.parent = lc.id");
        sql.append(" ORDER BY i.id");
		sql.append(" LIMIT "); 
		sql.append(pageable.getPageSize());
		sql.append(" OFFSET ");
		sql.append(pageable.getOffset());
		int count = template.queryForObject("SELECT count(id) FROM items", new MapSqlParameterSource(), Integer.class);
		List<Item> itemList = template.query(sql.toString(), ITEM_ROW_MAPPER);
		
		return new PageImpl<>(itemList, pageable, count);
	}
	
}