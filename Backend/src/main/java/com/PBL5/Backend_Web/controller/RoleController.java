package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.Role;
import com.PBL5.Backend_Web.service.RoleServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl service;

    @GetMapping(path = "")
    public List<Role> GetAllRole() {
        return service.GetlAllListRole();
    }

    @PostMapping(path = "/InsertRole")
    public ResponseEntity<ResponseObject> insertRole(@RequestBody @NotNull Role newRole) {
        ResultResponse response = service.InsertRole(newRole);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }

//    @PutMapping(path = "/updateRole/{id}")
//    public ResponseEntity<ResponseObject> updateRole(@RequestBody @NotNull Role newUpdateRole, @PathVariable String id) {
//        List<Role> roles = roleRepo.findByname(newUpdateRole.getName().trim());
//        if (roles.size() > 0||newUpdateRole.getName().equals("")) {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(ResponseObject.FAIRLURE, "This role is available now", ""));
//        }
//        Optional<Role> UpdateRole = roleRepo.findById(id);
//     return UpdateRole.map(r -> {
//            r.setName(newUpdateRole.getName());
//            r.setAccount(newUpdateRole.getAccount());
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Insert Role successfully", roleRepo.save(r)));
//        }).orElseGet(()->{
//            newUpdateRole.setId(id);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Insert Role successfully", roleRepo.save(newUpdateRole)));
//        });
//    }

    @DeleteMapping(path = "/deleteRole/{id}")
    public ResponseEntity<ResponseObject> deleteRole(@PathVariable String id) {
       ResultResponse response = service.deleteRole(id);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }
}

