package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.ProductType;
import com.PBL5.Backend_Web.persistence.ResponseObject;
import com.PBL5.Backend_Web.persistence.ResultResponse;
import com.PBL5.Backend_Web.repository.ProductTypeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepo repo;

    @Override
    public List<ProductType> GetAllProductType() {
        return repo.findAll();
    }

    @Override
    public List<ProductType> getAllProductTypeByName(String name) {
        return repo.findAll().stream().filter(t -> t.getName().trim().toLowerCase().contains(name.trim().toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public ResultResponse InsertProductType(ProductType productType) {
        if (repo.GetAllListByName(productType.getName().trim().toLowerCase()).size() > 0 || productType.getName().equals(""))
            return new ResultResponse(ResponseObject.FAIRLURE, "Product Type is not valid", HttpStatus.NOT_IMPLEMENTED);

        Long max = repo.findAll().stream().map(ProductType::getId).map(t -> t.replace("PT_", "")).mapToLong(Long::parseLong).max().orElse(0L);
        max++;
        productType.setId("PT_" + max);


        return new ResultResponse(ResponseObject.SUCCESS, "Insert success fully", HttpStatus.OK);
    }

    @Override
    public ResultResponse DeleteProductType(String id) {
        boolean valid = repo.existsById(id);
        if (valid) {
            repo.deleteById(id);
            return new ResultResponse(ResponseObject.SUCCESS,"Delete productType successfully with id = " + id,HttpStatus.OK);
        }
        return new ResultResponse(ResponseObject.FAIRLURE,"productType not Exists",HttpStatus.NOT_FOUND);
    }
}
