package com.example.service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 
 * @author hondayuki
 *
 */
@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	public Page<Item> showList(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}
}
