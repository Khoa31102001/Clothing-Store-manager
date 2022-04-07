package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.Product;
import com.PBL5.Backend_Web.persistence.ResultResponse;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    List<Product> getAllProductByName(String name);


    ResultResponse InsertProductType(Product product, String idPt);

    ResultResponse DeleteProduct(String id);
}
