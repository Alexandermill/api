package com.example.api.controllers;

import com.example.api.entity.Product;
import com.example.api.exeptons.IdNotFoundException;
import com.example.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/products")
@Validated
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping
    ResponseEntity<Product> addProduct (@Valid @RequestBody Product product){
            adminService.addProduct(product);
        return new  ResponseEntity("saved", HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Iterable<Product>> getAll(){
        return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getOne(@PathVariable("id") @Min(1) Long id) throws IdNotFoundException {
        return new ResponseEntity(adminService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity <String> updateProduct (@PathVariable("id") @Min(1) Long id, @RequestBody @Valid Product product) throws IdNotFoundException {
        adminService.updateProduct(id, product);
        return new ResponseEntity("updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteProduct (@PathVariable("id") @Min(1) Long id) throws IdNotFoundException {
        adminService.deleteProduct(id);
        return new ResponseEntity("deleted id"+id, HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>("ERROR FORMAT REQUEST", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {

        return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

}
