package com.house.design.book.items.vistor;

import com.house.design.book.items.composite.AbstractProductItem;
import com.house.design.book.items.composite.ProductComposite;
import com.house.design.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName AddItemVisitor
 * @Author haoZe
 * @Date 2024/1/15
 **/
@Component
public class AddItemVisitor implements ItemVisitor<AbstractProductItem>{

    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Override
    public AbstractProductItem visitor(AbstractProductItem productItem) {

        ProductComposite currentItem = (ProductComposite) redisProcessor.get("items");

        ProductComposite addItem = (ProductComposite) productItem;

        if (addItem.getId() == currentItem.getId()){
            currentItem.addProductItem(addItem);
            return currentItem;
        }
        addChild(addItem,currentItem);
        return currentItem;
    }

    private void addChild(ProductComposite addItem,ProductComposite currentItem){
        for (AbstractProductItem abstractItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite)abstractItem;
            if (item.getId() == addItem.getPid()){
                item.addProductItem(addItem);
                break;
            }else {
                addChild(addItem,item);
            }
        }
    }
}
