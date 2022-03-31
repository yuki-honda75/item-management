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

    @RequestMapping("/list")
    public String showList(@PageableDefault(page = 0, size = 20) Pageable pageable, Model model) {
        Page<Item> page = itemService.showList(pageable);

        model.addAttribute("page", page);
        model.addAttribute("itemList", page.getContent());

        return "list";
    }
    
}
