package com.house.design.book.service;

import com.house.design.book.bridge.abst.AbstractRegisterLoginComponent;
import com.house.design.book.bridge.abst.factory.RegisterLoginComponentFactory;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserService
 * @Author haoZe
 * @Date 2023/12/3
 **/
@Service
public class UserBridgeService {

    @Autowired
    private UserRepository userRepository;

    public String login(String account,String passWord){
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");

        return component.login(account,passWord);
    }

    /**
     *  注册
     * @param userInfo
     * @return
     */
    public String register(UserInfo userInfo){
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");

        return component.register(userInfo);
    }

    public String login3rd(HttpServletRequest request,String type) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent(type);
        return component.login3rd(request);
    }

    /**
     *        检查name是否重复
     * @param userName
     * @return
     */
    public boolean checkUserExists(String userName){
        return false;
    }

}
