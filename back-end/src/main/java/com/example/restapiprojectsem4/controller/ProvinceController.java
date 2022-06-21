package com.example.restapiprojectsem4.controller;

import com.example.restapiprojectsem4.response.RESTResponse;
import com.example.restapiprojectsem4.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/provinces")
@CrossOrigin("*")
public class ProvinceController {
    @Autowired
    ProvinceService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(new RESTResponse.Success()
                .addData(service.getAllProvince())
                .buildData(), HttpStatus.OK);
    }
}
