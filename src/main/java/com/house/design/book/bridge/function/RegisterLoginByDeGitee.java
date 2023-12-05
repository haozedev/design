package com.house.design.book.bridge.function;

import com.alibaba.fastjson.JSONObject;
import com.house.design.book.bridge.abst.AbstractRegisterLoginFunc;
import com.house.design.book.pojo.UserInfo;
import com.house.design.book.repo.UserRepository;
import com.house.design.book.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author haoze
 * @create 2023/12/5 16:47
 * @description
 */
public class RegisterLoginByDeGitee extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface{

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login3rd(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (!giteeState.equals(state)){
            throw new UnsupportedOperationException("Invalid state");
        }
        //请求Gitee平台获取token,并携带code
        String userUrl = giteeTokenUrl.concat(code);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.POST);
        //获取用户信息，username添加前缀GITEE@，密码保持与username一致，
        String userName = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String passWord = userName;
        //自动注册和登录功能，此处体现了方法的服用
        return autoRegister3rdAndLogin(userName,passWord);
    }

    private String autoRegister3rdAndLogin(String userName,String passWord){
        if (checkUserExists(userName)){
            return login(userName,passWord);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(passWord);
        userInfo.setCreateDate(new Date());
        register(userInfo);
        return login(userName,passWord);
    }
}
