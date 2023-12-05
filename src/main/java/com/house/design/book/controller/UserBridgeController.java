package com.house.design.book.controller;

import com.house.design.book.pojo.UserInfo;
import com.house.design.book.service.UserBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ClassName UserBridgeController
 * @Author haoZe
 * @Date 2023/12/5
 **/

@RestController
@RequestMapping("/bridge")
public class UserBridgeController {

    @Autowired
    private UserBridgeService userBridgeService;

    @PostMapping("/login")
    public String login(String account,String passWord){
        return userBridgeService.login(account, passWord);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo){
        return userBridgeService.register(userInfo);
    }

    @GetMapping("/gitee")
    public String gitee(String code,String state) throws IOException {
        return userBridgeService.login(code,state);
    }

}
