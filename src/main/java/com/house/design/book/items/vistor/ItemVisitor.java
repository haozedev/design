package com.house.design.book.items.vistor;

import com.house.design.book.items.composite.AbstractProductItem;

/**
 * @ClassName ItemVistor
 * @Author haoZe
 * @Date 2024/1/15
 **/
public interface ItemVisitor<T> {

    T visitor(AbstractProductItem productItem);
}
