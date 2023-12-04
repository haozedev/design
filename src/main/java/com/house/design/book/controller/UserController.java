package com.house.design.book.controller;

import com.house.design.book.pojo.UserInfo;
import com.house.design.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author haoZe
 * @Date 2023/12/3
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String account,String passWord){
        return userService.login(account, passWord);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo){
        return userService.register(userInfo);
    }
}
