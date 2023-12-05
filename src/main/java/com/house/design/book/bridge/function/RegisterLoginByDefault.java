package com.house.design.book.bridge.function;

import com.house.design.book.bridge.abst.AbstractRegisterLoginFunc;
import com.house.design.book.bridge.abst.factory.RegisterLoginComponentFactory;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author haoze
 * @create 2023/12/5 15:23
 * @description
 */
@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface{


    @Autowired
    private UserRepository userRepository;
    @Override
    public String login(String account,String passWord){
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, passWord);

        if (userInfo == null){
            return "account / passWord ERROR!";
        }
        return "Login SUCCESS!";
    }

    /**
     *  注册
     * @param userInfo
     * @return
     */
    @Override
    public String register(UserInfo userInfo){
        if(checkUserExists(userInfo.getUserName())){
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());

        userRepository.save(userInfo);
        return "Register Success";
    }

    /**
     *        检查name是否重复
     * @param userName
     * @return
     */
    @Override
    public boolean checkUserExists(String userName){
        UserInfo userInfo = userRepository.findByUserName(userName);

        if (userInfo==null){
            return false;
        }
        return true;
    }

    @PostConstruct
    private void initFuncMap(){
        RegisterLoginComponentFactory.funcMap.put("Default",this);
    }
}
