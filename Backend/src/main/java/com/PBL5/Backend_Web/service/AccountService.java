package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.Account;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.User;

import java.util.List;

public interface AccountService {
    List<Account> GetAllAccount();

    List<Account> GetAllAccountByName(String name);

    boolean InsertAccount(String password, String IdRole);



    ResultResponse ChangePassword(String oldPass , String newPass, String accountid);

    ResultResponse deleteAccount(String id);
    Account GetAccountByName(String name);

    User getUserByAccount(String IdAccount);
}
