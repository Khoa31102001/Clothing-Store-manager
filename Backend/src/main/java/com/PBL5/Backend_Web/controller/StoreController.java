package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.persistence.Store;
import com.PBL5.Backend_Web.service.StoreServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Store")
public class StoreController{
    @Autowired
    private StoreServiceImpl service;

    @GetMapping(path = "")
    public List<Store> getAllStore() {
        return service.getAllStore();
    }

    @PostMapping(path = "/InsertStore")
    public ResponseEntity<ResponseObject> InsertStore(@NotNull @RequestBody Store store) {
        ResultResponse response = service.InsertStore(store);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(),""));
    }

//    @PutMapping(path = "/updateStore/{id}")
//    public ResponseEntity<ResponseObject> UpdateProductType(@RequestBody @NotNull Store store,@PathVariable("id")String id) {
//        if (repo.GetAllListByAddress(store.getAddress().trim().toLowerCase()).size()>0||repo.GetAllListByPhone(store.getPhone().trim().toLowerCase()).size()>0||store.getName().equals("")||store.getAddress().equals(""))
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
//                    body(new ResponseObject(ResponseObject.FAIRLURE, "not valid", ""));
//        Optional<Store> type = repo.findById(id.trim());
//        return type.map(t->{
//            t.setName(store.getName());
//            t.setAddress(store.getAddress());
//            t.setPhone(store.getPhone());
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Update success fully", repo.save(t)));
//        }).orElseGet(()->{
//            store.setId(id);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "insert success fully", repo.save(store)));
//        });
//    }

    @DeleteMapping(path = "/DeleteStore/{id}")
    public ResponseEntity<ResponseObject> DeleteStore(@PathVariable String id) {
       ResultResponse response = service.DeleteStore(id);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }

}
