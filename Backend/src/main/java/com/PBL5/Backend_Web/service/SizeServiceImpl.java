package com.PBL5.Backend_Web.service;

import com.PBL5.Backend_Web.persistence.Size;
import com.PBL5.Backend_Web.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements  SizeService{

    @Autowired
    private SizeRepo sizeRepo;
    @Override
    public List<Size> getAllSize() {
        return sizeRepo.findAll();
    }
}
