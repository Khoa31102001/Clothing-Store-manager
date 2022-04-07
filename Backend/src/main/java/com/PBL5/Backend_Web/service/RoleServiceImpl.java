package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.Role;
import com.PBL5.Backend_Web.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service @Transactional @Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<Role> GetlAllListRole() {
        return roleRepo.findAll();
    }

    @Override
    public ResultResponse InsertRole(Role newRole) {
        Role role = roleRepo.findByname(newRole.getName());
        if(role!=null){
            return new ResultResponse(ResponseObject.FAIRLURE,"role already exists",HttpStatus.NOT_FOUND);
        }
        String NewTempId = "R_" + newRole.getName();
        newRole.setId(NewTempId);
        roleRepo.save(newRole);
        return new ResultResponse(ResponseObject.SUCCESS,"Insert Role successfully",HttpStatus.OK);
    }

    @Override
    public ResultResponse deleteRole(String id) {
        boolean valid = roleRepo.existsById(id);
        if (valid) {
            roleRepo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS,"Delete role successfully with id = " + id,HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE,"role not Exists",HttpStatus.NOT_FOUND);
    }
}
