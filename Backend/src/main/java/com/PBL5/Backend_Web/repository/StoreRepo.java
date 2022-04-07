package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepo extends JpaRepository<Store,String> {

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query(value = "select * from store where lower(address) = :address",nativeQuery = true)
    List<Store> GetAllListByAddress(@Param("address") String address);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query(value = "select * from store where lower(phone) = :phone",nativeQuery = true)
    List<Store> GetAllListByPhone(@Param("phone") String phone);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    boolean existsById(String id);
}
