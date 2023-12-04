package com.house.design.book.repo;

import com.house.design.book.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRepository
 * @Author haoZe
 * @Date 2023/12/3
 **/
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByUserName(String userName);

    UserInfo findByUserNameAndUserPassword(String account,String passWord);

}
