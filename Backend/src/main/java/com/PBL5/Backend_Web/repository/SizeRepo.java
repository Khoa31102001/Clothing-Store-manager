package com.PBL5.Backend_Web.repository;

import com.PBL5.Backend_Web.persistence.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepo extends JpaRepository<Size,String> {

}
