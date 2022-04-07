package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.Product;
import com.PBL5.Backend_Web.persistence.ProductType;
import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.repository.ProductRepo;
import com.PBL5.Backend_Web.repository.ProductTypeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo repo;
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Override
    public List<Product> getAllProduct() {
        return repo.findAll();
    }

    @Override
    public List<Product> getAllProductByName(String name) {
        return repo.findAll().stream().filter(t -> t.getName().trim().toLowerCase().contains(name.trim().toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public ResultResponse InsertProductType(Product product, String idPt) {

        ProductType productType = productTypeRepo.getById(idPt);
        if(repo.GetAllListByNameAndColor(product.getName(),product.getColor()).size()>0){
            return new ResultResponse(ResponseObject.FAIRLURE,"Name and color already available",HttpStatus.NOT_IMPLEMENTED);
        }
        if (product.getColor().equals("")||product.getName().equals(""))
            return new ResultResponse(ResponseObject.FAIRLURE,"not valid",HttpStatus.NOT_FOUND);

        Long max = repo.findAll().stream().map(Product::getId).map(t -> t.replace("P_", "")).mapToLong(Long::parseLong).max().orElse(0L);
        max++;
        product.setId("P_" + max);
        product.setProductType(productType);
        return new ResultResponse(ResponseObject.SUCCESS,"Insert success fully",HttpStatus.OK);
    }

    @Override
    public ResultResponse DeleteProduct(String id) {
        boolean valid = repo.existsById(id);
        if (valid) {
            repo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS,"Delete product successfully with id = " + id,HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE,"product not Exists",HttpStatus.NOT_FOUND);
    }
}
