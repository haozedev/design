package com.house.design.book.repo;

import com.house.design.book.pojo.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ProductItemRepository
 * @Author haoZe
 * @Date 2023/12/27
 **/
@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {


    @Modifying
    @Query(value = "INSERT INTO PRODUCT_ITEM(id,name,pid)"+
            "values ((select max(id)+1 from PRODUCT_ITEM),?1,?2)",nativeQuery = true)
    public void addItem(String name,int pid);

    @Modifying
    @Query(value="DELETE FROM PRODUCT_ITEM WHERE " +
            "id=?1 or pid=?1",nativeQuery=true)
    public void delItem(int id);

    public ProductItem findByNameAndPid(String name, int pid);

}
