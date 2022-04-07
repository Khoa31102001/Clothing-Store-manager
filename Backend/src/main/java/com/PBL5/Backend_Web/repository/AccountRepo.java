package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,String> {
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    boolean existsById(String id);

    Account findByName(String name);
}
