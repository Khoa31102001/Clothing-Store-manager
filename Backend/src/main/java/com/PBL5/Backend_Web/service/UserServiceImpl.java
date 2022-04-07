package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.Account;
import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.User;
import com.PBL5.Backend_Web.repository.AccountRepo;
import com.PBL5.Backend_Web.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<User> GetAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<User> GetAllUserByName(String name) {
        List<User> users = userRepo.findAll().stream().filter(t -> t.getFirst_name().toLowerCase().contains(name.toLowerCase()) || t.getLast_name().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        return users;
    }

    @Override
    public ResultResponse InsertUser(User user, String idAccount) {
        if(user.getId()!=null)
        {
            User user1 = userRepo.getById(user.getId());

            boolean isvalid =false;
            if(user1.getAvatar()!=null){
                isvalid = cloudinaryService.DeleteImage("avatar", user1.getAvatar());
                System.out.println(isvalid);
            }
        }

        ResultResponse responseCloudinary = cloudinaryService.UploadImage(user.getFile(), "avatar");

        if (responseCloudinary.getResult().equals(ResponseObject.FAIRLURE))
            return new ResultResponse(responseCloudinary.getResult(), responseCloudinary.getDataResult(), responseCloudinary.getStatus());
        String img = responseCloudinary.getDataResult();
        user.setAvatar(img);

        if (user.getFirst_name().trim().equals("") || user.getLast_name().trim().equals("")) {
            return new ResultResponse(ResponseObject.FAIRLURE, "not valid", HttpStatus.NOT_IMPLEMENTED);
        }
        Account account = accountRepo.getById(idAccount);
        if (user.getId() == null) {
            Long max = userRepo.findAll().stream().map(User::getId).map(t -> t.replace("U_", "")).mapToLong(Long::parseLong).max().orElse(0L);
            max++;
            user.setId("U_" + max);
            user.setAccount(account);
            userRepo.save(user);
            return new ResultResponse(ResponseObject.SUCCESS, "Insert success fully", HttpStatus.OK);
        }
        Optional<User> UpdateRole = userRepo.findById(user.getId());
        UpdateRole.map(r -> {
            r.setFirst_name(user.getFirst_name());
            r.setLast_name(user.getLast_name());
            r.setBirthday(user.getBirthday());
            r.setGender(user.getGender());
            r.setAddress(user.getAddress());
            r.setAvatar(user.getAvatar());
            r.setPhone(user.getPhone());
            userRepo.save(r);
            return r;
        });

        return new ResultResponse(ResponseObject.SUCCESS, "Update success fully", HttpStatus.OK);
    }

    @Override
    public ResultResponse DeleteUser(String id) {
        boolean valid = userRepo.existsById(id);
        if (valid) {
            userRepo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS, "Delete user successfully with id = " + id, HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE, "user not Exists", HttpStatus.NOT_FOUND);
    }




}
