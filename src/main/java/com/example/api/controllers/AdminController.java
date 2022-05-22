package com.example.api.controllers;

import com.example.api.entity.Product;
import com.example.api.model.ProductModel;
import com.example.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping
    ResponseEntity<Product> addProduct (@RequestBody ProductModel productModel){
        adminService.addProduct(productModel);
        return new  ResponseEntity("saved", HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Iterable<Product>> getAll(){
        return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getOne(@PathVariable("id") Long id){
        return new ResponseEntity(adminService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity <String> updateProduct (@PathVariable("id") Long id, @RequestBody ProductModel productModel){
        adminService.updateProduct(id, productModel);
        return new ResponseEntity("updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteProduct (@PathVariable("id") Long id){
        adminService.deleteProduct(id);
        return new ResponseEntity("deleted id"+id, HttpStatus.OK);
    }

}
