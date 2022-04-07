package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.*;
import com.PBL5.Backend_Web.repository.AccountRepo;
import com.PBL5.Backend_Web.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service @Transactional @Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {
    @Autowired
    private  AccountRepo accountRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String Choise(String temp) {
        switch (temp) {
            case "A":
                return "Admin";
            case "E":
                return "Employee";
            case "M":
                return "Manager";
        }
        return "";
    }

    @Override
    public List<Account> GetAllAccount() {

        return accountRepo.findAll();
    }

    @Override
    public List<Account> GetAllAccountByName(String name) {
        List<Account> accounts =  accountRepo.findAll().stream().filter(t -> t.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        return accounts;
    }

    @Override
    public boolean InsertAccount(String password, String IdRole) {
        Role role = roleRepo.getById(IdRole);
        Account newAccount = new Account("", "",password, role);
        String tempName = String.valueOf(newAccount.getRole().getId().trim().toCharArray()[2]);
        long max = accountRepo.findAll().stream().map(Account::getId).map(t -> t = t.replace("A_", ""))
                .filter(t -> t.contains(tempName))
                .map(t -> {
                    if (t.length() == 1) return "0";
                    return t.substring(1, t.length());
                })
                .mapToLong(Long::parseLong).max().orElse(0L);
        max++;
        String NewTempId = "A_" + tempName + max;
        newAccount.setId(NewTempId);
        String NewTempName = Choise(tempName) + "_" + max;
        newAccount.setName(NewTempName);
        newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));

        return true ? accountRepo.save(newAccount)!=null : false ;
    }

    @Override
    public ResultResponse ChangePassword(String oldPass, String newPass, String accountid) {
        Account account = accountRepo.getById(accountid);
        if (!passwordEncoder.matches(oldPass,account.getPassword())) {
            return new ResultResponse(ResponseObject.FAIRLURE,"Old password not true",HttpStatus.NOT_IMPLEMENTED);
        }

        if (newPass.toLowerCase().trim().equals(account.getPassword().toLowerCase().trim())) {
            return new ResultResponse(ResponseObject.FAIRLURE,"new password same old password",HttpStatus.NOT_IMPLEMENTED);
        }

        account.setPassword(passwordEncoder.encode(newPass));
        accountRepo.save(account);
        return new ResultResponse(ResponseObject.SUCCESS,"Change password success",HttpStatus.OK);
    }


    @Override
    public ResultResponse deleteAccount(String id) {
        boolean valid = accountRepo.existsById(id);

        if (valid) {
            accountRepo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS,"Delete account successfully with id = " + id,HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE,"account not Exists",HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public Account GetAccountByName(String name) {
        return accountRepo.findByName(name);
    }

    @Override
    public User getUserByAccount(String IdAccount) {
        return accountRepo.getById(IdAccount).getUser();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByName(username);
        if(account==null){
            log.error("account not found");
            throw new UsernameNotFoundException("account not found");
        }else{
            log.info("account found in database {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(account.getName(),account.getPassword(),authorities);
    }
}
