package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ProductType;
import com.PBL5.Backend_Web.persistence.ResultResponse;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> GetAllProductType();


    List<ProductType> getAllProductTypeByName( String name);

    ResultResponse InsertProductType(ProductType productType);

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

    ResultResponse DeleteProductType( String id);
}
