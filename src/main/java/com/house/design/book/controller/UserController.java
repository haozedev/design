package com.house.design.book.controller;

import com.house.design.book.adapter.Login3rdAdapter;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ClassName UserController
 * @Author haoZe
 * @Date 2023/12/3
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Login3rdAdapter login3rdAdapter;

    @PostMapping("/login")
    public String login(String account,String passWord){
        return userService.login(account, passWord);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo){
        return userService.register(userInfo);
    }

    @GetMapping("/gitee")
    public String gitee(String code,String state) throws IOException {
        return login3rdAdapter.loginByGitee(code,state);
    }
}
