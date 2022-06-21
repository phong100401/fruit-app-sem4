package com.example.restapiprojectsem4.service;

import com.example.restapiprojectsem4.dto.ChartDTO;
import com.example.restapiprojectsem4.dto.TopUserDTO;
import com.example.restapiprojectsem4.dto.TotalChart;
import com.example.restapiprojectsem4.entity.Product;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(ObjectFilter objectFilter);
    Optional<Product> findById(int id);
    Product created(Product product);
    Product edit(int id,Product product);
    List<Product> findByName(String name);
    Product deleted(int id);
    List<Object> chart();
    List<ChartDTO> chartProduct();
    List<TopUserDTO> topUserChart();
    List<Map<Object, TotalChart>> totalChart();
//    Page<Product> search(ProductDTO criteria);
}
