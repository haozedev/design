package com.house.design.book.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName UserInfo
 * @Author haoZe
 * @Date 2023/12/3
 **/

@Data
@Entity
@Table
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private Date createDate;

    @Column
    private String userEmail;

}
