package com.house.design.book.service;

import com.house.design.book.pojo.UserInfo;
import com.house.design.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName UserService
 * @Author haoZe
 * @Date 2023/12/3
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
    public boolean checkUserExists(String userName){
        UserInfo userInfo = userRepository.findByUserName(userName);

        if (userInfo==null){
            return false;
        }
        return true;
    }

}
