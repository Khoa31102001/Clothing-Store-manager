package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.Product;
import com.PBL5.Backend_Web.persistence.ProductType;
import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.repository.ProductRepo;
import com.PBL5.Backend_Web.repository.ProductTypeRepo;
import com.PBL5.Backend_Web.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/Product")
public class ProductController {

    @Autowired
    private ProductService service;

    public List<Product> getAllProduct() {
        return service.getAllProduct();
    }

    @GetMapping(path = "/ListProductByName")
    public List<Product> getAllProductTypeByName(@RequestParam("name") String name) {
        return service.getAllProductByName(name);
    }

    @PostMapping(path = "/InsertProduct")
    public ResponseEntity<ResponseObject> InsertProductType(@org.jetbrains.annotations.NotNull @RequestBody Product product, @RequestParam("idPt") String idPt) {
        ResultResponse response = service.InsertProductType(product,idPt);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(),response.getDataResult(),""));
    }
//    @PutMapping(path = "/updateProduct/{idPt}")
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).
//                    body(new ResponseObject(ResponseObject.FAIRLURE, "Product Type is available", ""));
//        Optional<ProductType> type = repo.findById(productType.getId().trim()).map(t->{
//            t.setName(productType.getName());
//            return t;
//        });
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Update success fully", repo.save(type.get())));
//
//    }

    @DeleteMapping(path = "/DeleteProduct/{id}")
    public ResponseEntity<ResponseObject> DeleteProduct(@PathVariable String id) {
     ResultResponse response = service.DeleteProduct(id);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(),response.getDataResult(),""));
    }
}
