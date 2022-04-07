package com.example.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.example.domain.Category;
import com.example.domain.CsvItem;
import com.example.domain.Item;
import com.example.domain.ItemSearchCondition;
import com.example.form.ItemInsertForm;
import com.example.form.ItemSearchForm;
import com.example.form.ItemUpdateForm;
import com.example.service.CategoryService;
import com.example.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;


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
    @ModelAttribute
    public ItemSearchForm setUpItemSearchForm() {
        return new ItemSearchForm();
    }
    
    /**
     * 商品一覧を表示する
     * 
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String showList(ItemSearchForm form, @PageableDefault(size = 40) Pageable pageable, Model model) {
        //Page<Item> page = itemService.showList(pageable);
        List<Category> categoryList = categoryService.getCategory();

        ItemSearchCondition condition = new ItemSearchCondition();
        BeanUtils.copyProperties(form, condition);
        try {
            condition.setCategory(form.getIntCategory());
        } catch (NumberFormatException e) {
            condition.setCategory(null);
        }
        Page<Item> page = itemService.search(condition, pageable);

        model.addAttribute("page", page);
        model.addAttribute("categoryList", categoryList);
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
        createLog(item);
        
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
        createLog(item);
        
        return "redirect:/item/list";
    }

    /**
     * csvをダウンロードする
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/csv", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        addContentDisposition(headers, "item.csv");
        List<Item> itemList = itemService.showList();
        List<CsvItem> csv = new ArrayList<>();
        for (Item item : itemList) {
            CsvItem csvItem = new CsvItem();
            BeanUtils.copyProperties(item, csvItem);
            csv.add(csvItem);
        }
        

        return new ResponseEntity<>(getCsvText(csv), headers, HttpStatus.OK);
    }
    
    /**
     * ログを出力する処理
     * 
     * @param logName
     * @param item
     */
    public void createLog(Item item) {
        
        Logger logger = Logger.getLogger("ADD & EDIT");
        String LogPath = "log.txt";
        Handler handler = null;

        StringBuilder stb = new StringBuilder();
        stb.append("\nname:");
        stb.append(item.getName());
        stb.append("\ncondition:");
        stb.append(item.getCondition());
        stb.append("\nsCategoryId:");
        stb.append(item.getsCategoryId());
        stb.append("\nbrand:");
        stb.append(item.getBrand());
        stb.append("\nprice:");
        stb.append(item.getPrice());
        stb.append("\ndescription:");
        stb.append(item.getDescription());
        stb.append("\n");
        try {
        	handler = new FileHandler(LogPath, true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
            logger.log(Level.INFO, stb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (handler != null) {
                handler.flush();
                handler.close();
            }
        }
    }

    /**
     * csvを作成する
     * 
     * @return
     * @throws JsonProcessingException
     */
    public byte[] getCsvText(List<CsvItem> csvItemList) throws JsonProcessingException {
        CsvMapper csvMapper = new CsvMapper();

        csvMapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);

        CsvSchema schema = csvMapper.schemaFor(CsvItem.class).withHeader();

        return csvMapper.writer(schema).writeValueAsBytes(csvItemList);
    }

    /**
     * ファイル名を日本語対応する
     * 
     * @param headers
     * @param fileName
     * @throws UnsupportedEncodingException
     */
    public void addContentDisposition(HttpHeaders headers, String fileName)
            throws UnsupportedEncodingException {
        String headerValue = String.format("attachment; filename=\"%s\"; filename*=UTF-8''%s",
                fileName, UriUtils.encode(fileName, StandardCharsets.UTF_8.name()));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
    }
}
