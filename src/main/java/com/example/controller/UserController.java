package com.example.controller;

import com.example.domain.User;
import com.example.form.UserInsertForm;
import com.example.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ModelAttribute
    public UserInsertForm setUpUserInsertForm() {
        return new UserInsertForm();
    }

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
    public String register(@Validated UserInsertForm form, BindingResult result) {
        if (userService.checkUser(form.getName()) != null) {
            result.rejectValue("name", "", "そのメールアドレスは既に登録されています");
        }

        if (result.hasErrors()) {
            return toRegister();
        }

        User user = new User();
        BeanUtils.copyProperties(form, user);
        userService.register(user);

        return "redirect:/";
    }

    /**
     * ログイン画面を表示する
     * 
     * @return
     */
    @RequestMapping("/")
    public String toLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "メールアドレスまたはパスワードが不正です");
        }

        return "login";
    }
}
