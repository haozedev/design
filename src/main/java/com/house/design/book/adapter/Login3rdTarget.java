package com.house.design.book.adapter;

/**
 * @ClassName Login3rdTarget
 * @Author haoZe
 * @Date 2023/12/4
 **/
public interface Login3rdTarget {

    public String loginByGite(String code,String state);
    public String loginByWechat(String code,String state);
    public String loginByQQ(String code,String state);



}
