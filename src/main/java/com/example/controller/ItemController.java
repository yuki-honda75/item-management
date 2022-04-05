package com.example.controller;

import java.util.List;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.domain.ItemSearchCondition;
import com.example.form.ItemInsertForm;
import com.example.form.ItemUpdateForm;
import com.example.service.CategoryService;
import com.example.service.ItemService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    public ItemInsertForm setUpItemInsertForm() {
        return new ItemInsertForm();
    }
    @ModelAttribute
    public ItemUpdateForm setUpItemUpdateForm() {
        return new ItemUpdateForm();
    }
    
    /**
     * 商品一覧を表示する
     * 
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String showList(@PageableDefault(size = 40) Pageable pageable, Model model) {
        //Page<Item> page = itemService.showList(pageable);
        ItemSearchCondition condition = new ItemSearchCondition();
        Page<Item> page = itemService.search(condition, pageable);

        model.addAttribute("page", page);
        model.addAttribute("itemList", page.getContent());

        return "list";
    }
    
    /**
     * 詳細画面を表示する
     * 
     * @param itemId
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/detail")
    public String showDetail(Integer itemId, Integer pageNum, Model model) {
        Item item = itemService.showDetail(itemId);

        model.addAttribute("item", item);
        model.addAttribute("pageNum", pageNum);
        return "detail";
    }

    /**
     * 商品追加画面を表示する
     * 
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String showAdd(Model model) {
        List<Category> categoryList = categoryService.getCategory();

        model.addAttribute("categoryList", categoryList);
        
        return "add";
    }

    /**
     * 商品を追加する
     * 
     * @param form
     * @param result
     * @param model
     * @return
     */
    @RequestMapping("/insert")
    public String insertItem(@Validated ItemInsertForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return showAdd(model);
        }

        Item item = new Item();
        BeanUtils.copyProperties(form, item);
        item.setCondition(form.getIntCondition());
        item.setsCategoryId(form.getIntSCategory());
        item.setPrice(form.getDoublePrice());

        itemService.insertItem(item);
        
        return "redirect:/item/list";
    }

    /**
     * 編集画面を表示する
     * 
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEdit(ItemUpdateForm form, Integer itemId, Model model) {
        Item item = itemService.showDetail(itemId);
        List<Category> categoryList = categoryService.getCategory();
        BeanUtils.copyProperties(item, form);
        form.setCondition(item.getCondition().toString());
        form.setPrice(item.getPrice().toString());
        form.setsCategory(item.getsCategoryId().toString());

        model.addAttribute("item", item);
        model.addAttribute("categoryList", categoryList);
        
        return "edit";
    }
    
    @RequestMapping("/update")
    public String update(@Validated ItemUpdateForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
			return showEdit(form, form.getId(), model);
		}

        Item item = new Item();
        BeanUtils.copyProperties(form, item);
        item.setCondition(form.getIntCondition());
        item.setsCategoryId(form.getIntSCategory());
        item.setPrice(form.getDoublePrice());

        itemService.updateItem(item);
        
        return "redirect:/item/list";
    }
}
