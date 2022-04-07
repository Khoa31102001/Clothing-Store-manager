package com.PBL5.Backend_Web.controller;

import com.PBL5.Backend_Web.persistence.Size;
import com.PBL5.Backend_Web.service.SizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Size")
public class SizeController {
    @Autowired
    private SizeServiceImpl service;

    @GetMapping(path = "")
    public List<Size> getAllStore() {
        return service.getAllSize();
    }
}
