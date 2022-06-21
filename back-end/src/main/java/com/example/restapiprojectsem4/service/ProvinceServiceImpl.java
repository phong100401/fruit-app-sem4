package com.example.restapiprojectsem4.service;

import com.example.restapiprojectsem4.entity.Province;
import com.example.restapiprojectsem4.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService{
    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvince(){
        return provinceRepository.findAll();
    }
}
