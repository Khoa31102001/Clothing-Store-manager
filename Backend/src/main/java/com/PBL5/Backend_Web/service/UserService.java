package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.User;

import java.util.List;

public interface UserService {
    List<User> GetAllUser();


    List<User> GetAllUserByName(String name);

    ResultResponse InsertUser( User user,String idAccount);

    ResultResponse DeleteUser(String id);
}
