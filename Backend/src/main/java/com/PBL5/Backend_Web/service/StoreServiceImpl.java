package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.Store;
import com.PBL5.Backend_Web.repository.StoreRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepo repo;

    @Override
    public List<Store> getAllStore() {
        return repo.findAll();
    }

    @Override
    public ResultResponse InsertStore(Store store) {
        if (repo.GetAllListByAddress(store.getAddress().trim().toLowerCase()).size() > 0 || repo.GetAllListByPhone(store.getPhone().trim().toLowerCase()).size() > 0 || store.getName().equals("") || store.getAddress().equals(""))
            return new ResultResponse(ResponseObject.FAIRLURE,"not valid",HttpStatus.NOT_IMPLEMENTED);
        Long max = repo.findAll().stream().map(Store::getId).map(t -> t.replace("ST_", "")).mapToLong(Long::parseLong).max().orElse(0L);
        max++;
        store.setId("ST_" + max);
        repo.save(store);
        return new ResultResponse(ResponseObject.SUCCESS,"Insert success fully",HttpStatus.OK);
    }

    @Override
    public ResultResponse DeleteStore(String id) {
        boolean valid = repo.existsById(id);
        if (valid) {
            repo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS,"Delete store successfully with id = " + id,HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE,"store not Exists",HttpStatus.NOT_FOUND);
    }
}
