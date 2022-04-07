package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.ProductType;
import com.PBL5.Backend_Web.persistence.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType,String> {
    @Modifying(clearAutomatically = true)
    @Query(value = "select * from product_type where lower(Product_Type_Name) = :name",nativeQuery = true)
    List<Store> GetAllListByName(@Param("name") String name);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    boolean existsById(String id);
}
