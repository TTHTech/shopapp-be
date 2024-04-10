package com.project.shopapp.controller;


import com.project.shopapp.exception.FileStorageException;
import com.project.shopapp.service.BrandService;
import com.project.shopapp.service.FlieStorageService;
import com.project.shopapp.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private FlieStorageService flieStorageService;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
}
