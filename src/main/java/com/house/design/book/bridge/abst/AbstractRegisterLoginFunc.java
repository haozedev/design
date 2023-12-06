package com.house.design.book.bridge.abst;

import com.house.design.book.bridge.function.RegisterLoginFuncInterface;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author haoze
 * @create 2023/12/5 16:58
 * @description
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    protected String commonLogin(String account, String passWord,
                                 UserRepository userRepository){
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, passWord);
        if (userInfo == null){
            return "account / passWord ERROR!";
        }
        return "Login SUCCESS!";
    };
    public String register(UserInfo userInfo,UserRepository userRepository){
        if(checkUserExists(userInfo.getUserName())){
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());

        userRepository.save(userInfo);
        return "Register Success";
    };

    public boolean commonCheckUserExists(String userName,UserRepository userRepository){
        UserInfo userInfo = userRepository.findByUserName(userName);

        if (userInfo==null){
            return false;
        }
        return true;
    }

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
