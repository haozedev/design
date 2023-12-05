package com.house.design.book.adapter;

import com.house.design.book.service.UserService;

/**
 * @ClassName Login3rdAdapter
 * @Author haoZe
 * @Date 2023/12/4
 **/
public class Login3rdAdapter extends UserService implements Login3rdTarget{

    @Override
    public String loginByGitee(String code, String state) {
        return null;
    }

    @Override
    public String loginByWechat(String code, String state) {
        return null;
    }

    @Override
    public String loginByQQ(String code, String state) {
        return null;
    }
}
