package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.ProductDetail;
import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.service.CloudinaryService;
import com.PBL5.Backend_Web.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ProductDetails")
public class ProductDetailsController {

    @Autowired
    private ProductDetailsService service;

    @GetMapping(path = "")
    public List<ProductDetail> getAllProductDetail() {
        return service.getAllProductDetail();
    }


    @PostMapping(path = "/InsertProductDetails")
    public ResponseEntity<ResponseObject> InsertProductDetails(@Param("P_id") String P_id,
                                                               @Param("S_id") String S_id,
                                                               @Param("St_id") String St_id, @ModelAttribute ProductDetail productDetail) {

        ResultResponse response = service.InsertProductDetails(P_id, S_id, St_id, productDetail);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }

    @PostMapping (path = "/UpdateProductDetails")
    public ResponseEntity<ResponseObject> UpdateProductDetails(@Param("P_id") String P_id,
                                                               @Param("S_id") String S_id,
                                                               @Param("St_id") String St_id, @ModelAttribute ProductDetail productDetail) {


        ResultResponse response = service.UpdateProductDetails(P_id, S_id, St_id, productDetail);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }

    @DeleteMapping(path = "/DeleteProductDetails")
    public ResponseEntity<ResponseObject> DeleteProductDetails(@Param("P_id") String P_id, @Param("S_id") String S_id) {
        ResultResponse response = service.DeleteProductDetails(P_id, S_id);
        return ResponseEntity.status(response.getStatus()).body(new ResponseObject(response.getResult(), response.getDataResult(), ""));
    }
    @PostMapping (path = "/GetSameImage")
    public List<String> GetSameImage(@ModelAttribute MultipartFile file){
        return service.GetSameImageFromClient(file);
    }
}
