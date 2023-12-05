package com.house.design.book.bridge.abst;

import com.house.design.book.bridge.function.RegisterLoginFuncInterface;
import com.house.design.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoze
 * @create 2023/12/5 15:50
 * @description
 */
public class RegisterLoginComponent extends AbstractRegisterLoginComponent{
    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    @Override
    public String login(String userName, String passWord) {
        return funcInterface.login(userName,passWord);
    }

    @Override
    public String register(UserInfo userInfo) {
        return funcInterface.register(userInfo);
    }

    @Override
    protected boolean checkUserExists(String userName) {
        return funcInterface.checkUserExists(userName);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
