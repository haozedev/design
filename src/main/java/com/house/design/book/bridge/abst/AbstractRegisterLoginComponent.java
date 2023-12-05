package com.house.design.book.bridge.abst;

import com.house.design.book.bridge.function.RegisterLoginFuncInterface;
import com.house.design.book.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haoze
 * @create 2023/12/5 15:27
 * @description
 */
public abstract class AbstractRegisterLoginComponent {

    protected RegisterLoginFuncInterface funcInterface;

    public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface){
        validate(funcInterface);
        this.funcInterface = funcInterface;
    }

    protected final void validate(RegisterLoginFuncInterface funcInterface) {
        if (!(funcInterface instanceof RegisterLoginFuncInterface)){
            throw new UnsupportedOperationException("Unknown register/login function type!");
        }
    }

    public abstract String login(String userName, String passWord);
    public abstract String register(UserInfo userInfo);
    protected abstract boolean checkUserExists(String userName);
    public abstract String login3rd(HttpServletRequest request);
}
