package com.example.api.controllers;

import com.example.api.entity.Product;
import com.example.api.exeptons.IdNotFoundException;
import com.example.api.service.ClientSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/client/products")
public class ClientController {

    @Autowired
    ClientSrvice clientSrvice;

    @GetMapping
    ResponseEntity<Iterable<Product>> getAllForClient(@RequestParam @NotBlank String lang, @RequestParam @NotBlank String currency){
        Iterable<Product> products = clientSrvice.getAll(lang, currency);
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/product")
    ResponseEntity<Product> getOne(@RequestParam @NotBlank String lang, @RequestParam @NotBlank String currency,
                                   @RequestParam @NotBlank String name, @RequestParam @NotBlank String description) throws IdNotFoundException {
        Product product = clientSrvice.getOne(lang, currency, name, description);
        return new ResponseEntity(product, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getById(@PathVariable("id") @Min(1) Long id, @RequestParam @NotBlank String lang, @RequestParam @NotBlank String currency) throws IdNotFoundException {
        Product product = clientSrvice.getById(id, lang, currency);
        return new ResponseEntity(product, HttpStatus.OK);
    }






}
