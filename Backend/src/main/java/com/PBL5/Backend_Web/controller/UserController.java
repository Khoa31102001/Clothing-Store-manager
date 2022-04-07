package com.PBL5.Backend_Web.controller;


import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.User;
import com.PBL5.Backend_Web.service.CloudinaryService;
import com.PBL5.Backend_Web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.xpath.XPath;
import java.util.List;

@RestController
@RequestMapping(path = "/api/User")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl service;


    @GetMapping(path = "")
    public List<User> GetAllUser() {
        return service.GetAllUser();

    }

    @GetMapping(path = "/ListUserByName")
    public ResponseEntity<ResponseObject> GetAllAccountByName(@RequestParam(name = "name") String name ) {
        List<User> users = service.GetAllUserByName(name);
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS,
                "List user", users));
    }

    @PostMapping(path = "/UpdateUser")
    public ResponseEntity<ResponseObject> InsertUser(@ModelAttribute  User user, @RequestParam("IdAccount") String IdAccount) {
        ResultResponse response1 = service.InsertUser(user,IdAccount);
        return ResponseEntity.status(response1.getStatus()).body(new ResponseObject(response1.getResult(), response1.getDataResult(), ""));
    }

    @DeleteMapping(path = "/DeleteUser/{id}")
    public ResponseEntity<ResponseObject> DeleteUser(@PathVariable String id) {
       ResultResponse response = service.DeleteUser(id);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }

}
