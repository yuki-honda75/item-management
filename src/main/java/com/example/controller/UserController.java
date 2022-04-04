package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author hondayuki
 *
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新規登録画面へ遷移する
     * 
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister() {

        return "register";
    }

    /**
     * ユーザーを新規登録する
     * 
     * @return
     */
    @RequestMapping("/insert")
    public String register() {
        User user = new User();
        userService.register(user);

        return "redirect:/toRegister";
    }
}
