package com.house.design.book.items.vistor;

import com.house.design.book.items.composite.AbstractProductItem;
import com.house.design.book.items.composite.ProductComposite;
import com.house.design.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName DelItemVisitor
 * @Author haoZe
 * @Date 2024/1/15
 **/
@Component
public class DelItemVisitor implements ItemVisitor<AbstractProductItem>{

    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {

        ProductComposite currentItem = (ProductComposite)redisProcessor.get("items");

        ProductComposite delItem = (ProductComposite) productItem;

        if (delItem.getId() == currentItem.getId()){
            currentItem.delProductItem(delItem);
            return currentItem;
        }
        delChild(delItem,currentItem);
        return currentItem;
    }

    private void delChild(ProductComposite productItem, ProductComposite currentItem){
        for (AbstractProductItem abstractItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite)abstractItem;
            if (item.getId() == productItem.getPid()){
                item.delProductItem(productItem);
            }else {
                delChild(productItem,item);
            }
        }
    }
}
