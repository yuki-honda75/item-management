package com.example.controller;

import com.example.domain.Item;
import com.example.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @author hondayuki
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    
    /**
     * 商品一覧を表示する
     * 
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String showList(@PageableDefault(size = 40) Pageable pageable, Model model) {
        Page<Item> page = itemService.showList(pageable);

        model.addAttribute("page", page);
        model.addAttribute("itemList", page.getContent());

        return "list";
    }

    @RequestMapping("/detail")
    public String showDetail(Integer itemId, Model model) {
        Item item = itemService.showDetail(1);
        
        model.addAttribute("item", item);
        return "detail";
    }
    
}
