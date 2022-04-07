package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductDetailsRepo extends JpaRepository<ProductDetail, ProductDetail.Id> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    boolean existsById(ProductDetail.Id id);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    @Query(value ="UPDATE product_details SET store_id = :st_id, image = :image, price= :price, amount = :amount where product_id = :p_id and size_id = :s_id " ,nativeQuery = true)
    void UpdateDetailsById(@Param("p_id") String P_id, @Param("s_id") String S_id,@Param("st_id") String St_id, @Param("image") String image, @Param("price") Double price, @Param("amount") Double amount);
}
