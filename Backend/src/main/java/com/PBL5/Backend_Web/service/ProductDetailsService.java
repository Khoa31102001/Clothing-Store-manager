package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ProductDetail;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductDetailsService {

    List<ProductDetail> getAllProductDetail();

    ResultResponse InsertProductDetails(String P_id,
                                        String S_id,
                                        String St_id, ProductDetail productDetail);

    ResultResponse UpdateProductDetails(String P_id,
                                        String S_id,
                                        String St_id, ProductDetail productDetail);

    ResultResponse DeleteProductDetails(String P_id, String S_id);

    List<String> GetSameImageFromClient(MultipartFile file);
}
