package com.house.design.book.items.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName ProductComposite
 * @Author haoZe
 * @Date 2023/12/26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComposite extends AbstractProductItem{
    private int id;
    private int pid;
    private String name;
    private List<AbstractProductItem> child = new ArrayList<>();

    @Override
    public void addProductItem(AbstractProductItem item){
        child.add(item);
    }
    @Override
    protected void delProductItem(AbstractProductItem item){
        ProductComposite removeItem =(ProductComposite)item;
        Iterator iterator = child.iterator();

        while (iterator.hasNext()) {
            ProductComposite composite = (ProductComposite)iterator.next();

            //移除ID相同的商品类目
            if (composite.getId()==removeItem.getId()){
                iterator.remove();
                break;
            }
        }
    }
}
