package com.example.restapiprojectsem4.controller;

import com.example.restapiprojectsem4.entity.Category;
import com.example.restapiprojectsem4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, path = "list")
    public ResponseEntity<Object> fillAll(){
        return new ResponseEntity<>(categoryService.getAllByStatus(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "search/{name}")
    public ResponseEntity<Object> findByName(@PathVariable String name){
        return new ResponseEntity<>(categoryService.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,path = "detail/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if(optionalCategory.isPresent()){
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "create")
    public ResponseEntity<Object> create(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,path = "update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id,@RequestBody Category category){
        return new ResponseEntity<>(categoryService.updated(id,category),HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "delete/{id}")
    public ResponseEntity<Object> deleted(@PathVariable int id){
        return new ResponseEntity<>(categoryService.deleted(id), HttpStatus.OK);
    }
}
