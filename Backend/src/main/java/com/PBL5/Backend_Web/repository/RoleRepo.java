package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    Role findByname(String role_name);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    boolean existsById(String id);
}
