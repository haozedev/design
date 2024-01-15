package com.house.design.book.controller;

import com.house.design.book.items.composite.ProductComposite;
import com.house.design.book.pojo.ProductItem;
import com.house.design.book.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductItemController
 * @Author haoZe
 * @Date 2023/12/28
 **/
@RestController
@RequestMapping("/product")
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;

    @PostMapping("/fetchAllItems")
    public ProductComposite fetchAllItems(){
        return productItemService.fetchAllItems();
    }

    @PostMapping("/addItems")
    public ProductComposite addItems(@RequestBody ProductItem item){
        return productItemService.addItems(item);
    }

    @PostMapping("/delItems")
    public ProductComposite delItems(@RequestBody ProductItem item){
        return productItemService.delItems(item);
    }
}
