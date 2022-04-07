package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.Product;
import com.PBL5.Backend_Web.persistence.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    @Modifying(clearAutomatically = true)
    @Query(value = "select * from product where lower(product_Name) = :name and lower (color) = :color",nativeQuery = true)
    List<Store> GetAllListByNameAndColor(@Param("name") String name,@Param("color") String color);
}
