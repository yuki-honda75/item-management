package com.example.service;

import java.util.List;

import com.example.domain.Item;
import com.example.domain.ItemSearchCondition;
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
	public List<Item> showList() {
		return itemRepository.findAll();
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

	/**
	 * 商品を編集する
	 * 
	 * @param item
	 */
	public void updateItem(Item item) {
		itemRepository.update(item);
	}

	/**
	 * 検索条件によって検索する
	 * 
	 * @param condition
	 * @param pageable
	 * @return
	 */
	public Page<Item> search(ItemSearchCondition condition, Pageable pageable) {
		return itemRepository.findByCondition(condition, pageable);
	}
}
