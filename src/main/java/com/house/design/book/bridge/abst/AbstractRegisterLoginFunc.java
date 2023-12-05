package com.house.design.book.bridge.abst;

import com.house.design.book.bridge.function.RegisterLoginFuncInterface;
import com.house.design.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoze
 * @create 2023/12/5 16:58
 * @description
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {
    public String login(String account,String passWord){
        throw new UnsupportedOperationException();
    };
    public String register(UserInfo userInfo){
        throw new UnsupportedOperationException();
    };
    public boolean checkUserExists(String userName){
        throw new UnsupportedOperationException();
    };
    public String login3rd(HttpServletRequest request){
        throw new UnsupportedOperationException();
    };
}
