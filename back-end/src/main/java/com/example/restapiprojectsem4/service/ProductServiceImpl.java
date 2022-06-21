package com.example.restapiprojectsem4.service;

import com.example.restapiprojectsem4.dto.ChartDTO;
import com.example.restapiprojectsem4.dto.TopUserDTO;
import com.example.restapiprojectsem4.dto.TotalChart;
import com.example.restapiprojectsem4.entity.Product;
import com.example.restapiprojectsem4.enums.Status;
import com.example.restapiprojectsem4.repository.ProductRepository;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import com.example.restapiprojectsem4.specification.ProductSpecification;
import com.example.restapiprojectsem4.specification.SearchCriteria;
import com.example.restapiprojectsem4.util.SQLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSpecification productSpecification;

    @Override
    public Page<Product> findAll(ObjectFilter objectFilter) {
        Specification<Product> spec = Specification.where(null);

        PageRequest paging = PageRequest.of(objectFilter.getPage() - 1, objectFilter.getPageSize());

        if(objectFilter.getStatus().equals(Status.DELETE.name())) {
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.STATUS, SQLConstant.EQUAL, Status.DELETE.name())));
        }else {
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.STATUS, SQLConstant.EQUAL, Status.ACTIVE.name())));
        }
        if(objectFilter.getDetail() != null && objectFilter.getDetail().length() >0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.DETAIL, SQLConstant.LIKE,objectFilter.getDetail())));
        }
        if (objectFilter.getName() != null && objectFilter.getName().length() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.PRODUCT_NAME, SQLConstant.LIKE,objectFilter.getName().trim())));
            System.out.println(objectFilter.getName());
        }
        if (objectFilter.getCategoryId() > 0){
            System.out.println(objectFilter.getCategoryId());
            spec = spec.and(new ProductSpecification(new SearchCriteria("categoryId",SQLConstant.EQUAL,objectFilter.getCategoryId())));
        }
        if (objectFilter.getMaxPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.PRICE,SQLConstant.GREATER_THAN_OR_EQUAL_TO,objectFilter.getMaxPrice())));
        }
        if (objectFilter.getMinPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.PRICE,SQLConstant.LESS_THAN_OR_EQUAL_TO,objectFilter.getMinPrice())));
        }
        return productRepository.findAll(spec,paging);
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product created(Product product) {
        Product p = productRepository.save(product);
        return p;
    }

    @Override
    public Product edit(int id,Product product) {
        Product exist = productRepository.getById(id);
        if(exist == null){
            return null;
        }
        exist.setName(product.getName());
        exist.setPrice(product.getPrice());
        exist.setThumbnail(product.getThumbnail());
        exist.setQuantity(product.getQuantity());
        exist.setDescription(product.getDescription());
        exist.setDetail(product.getDetail());
        exist.setStatus(product.getStatus());
        exist.setCategoryId(product.getCategoryId());
        return productRepository.save(exist);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name.trim().toUpperCase());
    }

    @Override
    public Product deleted(int id) {
        Product deleted = productRepository.getById(id);
        if(deleted == null){
            return null;
        }
        deleted.setStatus("DELETE");
        return productRepository.save(deleted);
    }

    @Override
    public List<Object> chart() {
        chartProduct();
        Object obj = productRepository.chart();
        return (List<Object>) obj;
    }

    @Override
    public List<ChartDTO> chartProduct() {
        List<ChartDTO> chartDTO = productRepository.findAllChart();
        return chartDTO;
    }

    @Override
    public List<TopUserDTO> topUserChart() {
        List<TopUserDTO> topUserDTOS = productRepository.findUserChart();
        return topUserDTOS;
    }
//    @Override
//    public Map<Object, TotalChart> totalChart() {
//        Map<Object, TotalChart> totalChart = productRepository.totalChart();
//        return totalChart;
//    }
    @Override
    public List<Map<Object, TotalChart>> totalChart() {
        List<Map<Object, TotalChart>> result = new ArrayList<>();
        Map<Object, TotalChart> totalChart = null;
        for (int i = 1; i <= 12 ; i++) {
            totalChart = productRepository.totalChart(2022, i);
            if (!totalChart.isEmpty()){
                result.add(totalChart);
            }
        }
        return result;
    }


//    @Override
//    public Page<Product> search(ProductDTO criteria) {
//        Page<Product> page = productRepository.findAll(productSpecification.filter(criteria), PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by("name").descending()));
//        return page;
//    }
}
