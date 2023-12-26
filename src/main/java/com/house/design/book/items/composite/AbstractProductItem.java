package com.house.design.book.items.composite;

/**
 * @ClassName AbstractProductItem
 * @Author haoZe
 * @Date 2023/12/26
 **/
public class AbstractProductItem {
    //增加商品类目
    protected void addProductItem(AbstractProductItem item){
        throw new UnsupportedOperationException("Not Support child add!");
    }
    //移除商品类目
    protected void delProductItem(AbstractProductItem item){
        throw new UnsupportedOperationException("Not Support child remove!");
    }
}
