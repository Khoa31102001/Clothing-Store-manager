package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.Store;

import java.util.List;

public interface StoreService {

    List<Store> getAllStore();

    ResultResponse InsertStore(Store store);

    ResultResponse DeleteStore(String id);
}
