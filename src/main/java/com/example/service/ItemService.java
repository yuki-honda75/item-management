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
	
	 /**
     * 商品を全件取得する
     * @param pageable
     * @return ページングされた商品データ
     */
	public Page<Item> showList(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	/**
	 * 商品をid検索する
	 * 
	 * @param itemId
	 * @return 商品情報
	 */
	public Item showDetail(Integer itemId) {
		return itemRepository.findById(itemId);
	}

	/**
	 * 商品を追加する
	 * 
	 * @param item
	 */
	public void insertItem(Item item) {
		itemRepository.insert(item);
	}
}
