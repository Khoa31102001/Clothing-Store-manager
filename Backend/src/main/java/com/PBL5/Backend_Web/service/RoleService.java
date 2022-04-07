package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.Role;

import java.util.List;

public interface RoleService {

    List<Role> GetlAllListRole();
    ResultResponse InsertRole( Role newRole);
    ResultResponse deleteRole(String id);
}
