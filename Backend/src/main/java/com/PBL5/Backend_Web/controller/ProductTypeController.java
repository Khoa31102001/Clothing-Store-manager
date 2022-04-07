package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.ProductType;
import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.service.ProductTypeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ProductType")
public class ProductTypeController {
    @Autowired
    private ProductTypeService service;

    @GetMapping(path = "")
    public List<ProductType> getAllProductType() {
        return service.GetAllProductType();
    }

    @GetMapping(path = "/ListProductTypeByName")
    public List<ProductType> getAllProductTypeByName( @NotNull @RequestParam(name = "name") String name) {
    return service.getAllProductTypeByName(name);
    }

        @PostMapping(path = "/InsertProductType")
    public ResponseEntity<ResponseObject> InsertProductType(@org.jetbrains.annotations.NotNull  ProductType productType){
            ResultResponse response = service.InsertProductType(productType);
            return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(),response.getDataResult(),""));
    }

//    @PutMapping(path = "/updateProductType/{id}")
//    public ResponseEntity<ResponseObject> UpdateProductType(@RequestBody @NotNull ProductType productType, @PathVariable("id") String id) {
//        if (repo.GetAllListByName(productType.getName().trim().toLowerCase()).size() > 0||productType.getName().equals(""))
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
//                    body(new ResponseObject(ResponseObject.FAIRLURE, "Product Type is available", ""));
//        Optional<ProductType> type = repo.findById(id.trim());
//        return type.map(t -> {
//            t.setName(productType.getName());
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Update success fully", repo.save(t)));
//        }).orElseGet(()->{
//            productType.setId(id);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Insert success fully", repo.save(productType)));
//        });
//    }

    @DeleteMapping(path = "/DeleteProductType/{id}")
    public ResponseEntity<ResponseObject> DeleteProductType(@PathVariable String id) {
       ResultResponse response= service.DeleteProductType(id);
       return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(),response.getDataResult(),""));
    }
}
