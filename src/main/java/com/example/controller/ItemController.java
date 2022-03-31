package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author hondayuki
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @RequestMapping("/list")
    public String showList() {
        return "list";
    }
}
