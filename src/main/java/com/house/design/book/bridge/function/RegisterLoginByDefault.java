package com.house.design.book.bridge.function;

import com.house.design.book.bridge.abst.AbstractRegisterLoginFunc;
import com.house.design.book.bridge.abst.factory.RegisterLoginComponentFactory;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author haoze
 * @create 2023/12/5 15:23
 * @description
 */
@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {


    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String account, String passWord) {
        return super.commonLogin(account, passWord, userRepository);
    }

    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    @Override
    public String register(UserInfo userInfo) {
        return super.register(userInfo, userRepository);
    }

    /**
     * 检查name是否重复
     *
     * @param userName
     * @return
     */
    @Override
    public boolean checkUserExists(String userName) {
        return super.commonCheckUserExists(userName, userRepository);
    }

    @PostConstruct
    private void initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("Default", this);
    }
}
