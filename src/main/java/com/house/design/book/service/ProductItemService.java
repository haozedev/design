package com.house.design.book.service;

import com.house.design.book.items.composite.AbstractProductItem;
import com.house.design.book.items.composite.ProductComposite;
import com.house.design.book.items.vistor.AddItemVisitor;
import com.house.design.book.items.vistor.DelItemVisitor;
import com.house.design.book.pojo.ProductItem;
import com.house.design.book.repo.ProductItemRepository;
import com.house.design.book.utils.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ProductItemService
 * @Author haoZe
 * @Date 2023/12/27
 **/
@Service
@Transactional
public class ProductItemService {

    @Autowired
    private RedisCommonProcessor redisProcessor;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private AddItemVisitor addItemVisitor;

    @Autowired
    private DelItemVisitor delItemVisitor;

    public ProductComposite fetchAllItems(){

        Object cacheItems = redisProcessor.get("items");
        if (cacheItems!=null){
            return (ProductComposite)cacheItems;
        }
        List<ProductItem> fetchDbItems = productItemRepository.findAll();

        ProductComposite items = generateProductTree(fetchDbItems);
        if (items==null){
            throw new UnsupportedOperationException("Product items should not be empty in DB!");
        }
        redisProcessor.set("items",items);
        return items;

    }


    private ProductComposite generateProductTree(List<ProductItem> fetchDbItems){
        List<ProductComposite> composites = new ArrayList<>(fetchDbItems.size());

        fetchDbItems.forEach(dbItem->composites.add(ProductComposite.builder()
                .id(dbItem.getId())
                .pid(dbItem.getPid())
                .name(dbItem.getName())
                .build()));
        Map<Integer, List<ProductComposite>> groupingList =
                composites.stream().collect(Collectors.groupingBy(ProductComposite::getPid));
        composites.stream().forEach(item->{
            List<ProductComposite> list = groupingList.get(item.getId());
            item.setChild(list == null ? new ArrayList<>():list.
                    stream().map(x ->(AbstractProductItem)x).collect(Collectors.toList()));
        });

        ProductComposite composite = composites.size() == 0 ? null : composites.get(0);

        return composite;
    }

    public ProductComposite addItems(ProductItem item){

        productItemRepository.addItem(item.getName(),item.getPid());

        ProductComposite addItem = ProductComposite.builder()
                .id(productItemRepository.findByNameAndPid(item.getName(), item.getPid()).getId())
                .name(item.getName())
                .child(new ArrayList<>())
                .build();

        AbstractProductItem updateItems = addItemVisitor.visitor(addItem);

        redisProcessor.set("items",updateItems);
        return (ProductComposite) updateItems;
    }

    public ProductComposite delItems(ProductItem item){

        productItemRepository.delItem(item.getId());

        ProductComposite delItem = ProductComposite.builder()
                .id(item.getId())
                .name(item.getName())
                .pid(item.getPid()).build();

        AbstractProductItem updateItems = delItemVisitor.visitor(delItem);

        redisProcessor.set("items",updateItems);
        return (ProductComposite) updateItems;
    }
}
