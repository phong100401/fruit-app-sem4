package com.example.restapiprojectsem4.controller;

import com.example.restapiprojectsem4.dto.ChartDTO;
import com.example.restapiprojectsem4.dto.ProductDTO;
import com.example.restapiprojectsem4.entity.Product;
import com.example.restapiprojectsem4.response.RESTPagination;
import com.example.restapiprojectsem4.response.RESTResponse;
import com.example.restapiprojectsem4.service.ProductService;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET, path = "list")
    public ResponseEntity<?> fillAll(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "pageSize",defaultValue = "5") int pageSize,
            @RequestParam(name = "minPrice",defaultValue = "-1") int minPrice,
            @RequestParam(name = "maxPrice",defaultValue = "-1") int maxPrice,
            @RequestParam(name = "categoryId", defaultValue = "-1") int categoryId,
            @RequestParam(name = "status", defaultValue = "ACTIVE") String status,
            @RequestParam(name = "name", required = false) String name
    ){
        if (page <= 0 ){
            page = 1;
        }
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withCategoryId(categoryId)
                .withName(name)
                .withStatus(status)
                .withPage(page)
                .withMinPrice(minPrice)
                .withMaxPrice(maxPrice)
                .withPageSize(pageSize)
                .build();
        Page<Product> productPage = productService.findAll(filter);
        if(productPage.getContent().size() == 0){
            return new ResponseEntity<>(
                    new RESTResponse.Success().setMessage("Product list is empty").build(), HttpStatus.OK);
        }
        List<ProductDTO> dto = new ArrayList<>();
        for (Product p : productPage.getContent()) {
            ProductDTO productDTO = ProductDTO.convertEntityToDTO(p);
            dto.add(productDTO);
        }
        return new ResponseEntity<>(
                new RESTResponse.Success()
                        .addDatas(dto)
                        .setPagination(new RESTPagination(productPage.getNumber() + 1,productPage.getSize(),productPage.getTotalPages(),productPage.getTotalElements()))
                        .setStatus(HttpStatus.OK.value())
                        .build(),HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.GET,path = "search")
    public ResponseEntity<Object> findByName(@RequestParam(name = "name") String name){
        return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "create")
    public ResponseEntity<Object> create(@RequestBody Product product){
        return new ResponseEntity<>(productService.created(product), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET,path = "detail/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id){
        Optional<Product> optionalProduct = productService.findById(id);
        if(optionalProduct.isPresent()){
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT,path = "update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id,@RequestBody Product product){
        return new ResponseEntity<>(productService.edit(id,product),HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "delete/{id}")
    public ResponseEntity<Object> deleted(@PathVariable int id){
        return new ResponseEntity<>(productService.deleted(id), HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.POST, path = "s")
//    public ResponseEntity<Page<Product>> search(@RequestBody ProductDTO productDTO){
//        return new ResponseEntity<>(productService.search(productDTO), HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.GET, path = "dataChart")
    public ResponseEntity chart(){
        return new ResponseEntity<>(productService.chartProduct(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "topUser")
    public ResponseEntity topChart(){
        return new ResponseEntity(productService.topUserChart(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "totalChart")
    public ResponseEntity totalChart(){
        return new ResponseEntity(productService.totalChart(), HttpStatus.OK);
    }
}
