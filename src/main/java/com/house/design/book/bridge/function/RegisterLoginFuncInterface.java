package com.house.design.book.bridge.function;

import com.house.design.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoze
 * @create 2023/12/5 14:32
 * @description
 */
public interface RegisterLoginFuncInterface {
    public String login(String account,String passWord);
    public String register(UserInfo userInfo);
    public boolean checkUserExists(String userName);
    public String login3rd(HttpServletRequest request);
}
