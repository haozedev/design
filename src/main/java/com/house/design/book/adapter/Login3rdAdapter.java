package com.house.design.book.adapter;

import com.alibaba.fastjson.JSONObject;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.service.UserService;
import com.house.design.book.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName Login3rdAdapter
 * @Author haoZe
 * @Date 2023/12/4
 **/
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget{

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Override
    public String loginByGitee(String code, String state) {
        if (!giteeState.equals(state)){
            throw new UnsupportedOperationException("Invalid state");
        }
        //请求Gitee平台获取token,并携带code
        String userUrl = giteeTokenUrl.concat(code);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.POST);
        //获取用户信息，username添加前缀GITEE@，密码保持与username一致，
        String userName = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String passWord = userName;
        //自动注册和登录功能，此处体现了方法的复用
        return autoRegister3rdAndLogin(userName,passWord);
    }

    /**
     *    复用父类的注册和登录方法
     * @param userName
     * @param passWord
     * @return
     */
    private String autoRegister3rdAndLogin(String userName,String passWord){
        if (super.checkUserExists(userName)){
            return super.login(userName,passWord);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(passWord);
        userInfo.setCreateDate(new Date());
        super.register(userInfo);
        return super.login(userName,passWord);
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
