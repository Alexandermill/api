package com.example.api.controllers;

import com.example.api.entity.Product;
import com.example.api.exeptons.IdNotFoundException;
import com.example.api.service.ClientSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@Validated
@RequestMapping("/api/client/products")
public class ClientController {

    @Autowired
    ClientSrvice clientSrvice;

    @GetMapping
    ResponseEntity<Iterable<Product>> getAllForClient(@RequestParam @NotBlank(message = "Lang must not be blank") String lang, @RequestParam
                                                      @NotBlank(message = "Currency must not be blank") String currency,
                                                      @RequestParam("page") @Min(value = 0, message = "Page must be >=0") int page) throws IdNotFoundException {
        Iterable<Product> products = clientSrvice.getAll(lang, currency, page);
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/product")
    ResponseEntity<Product> getOne(@RequestParam @NotBlank(message = "Lang must not be blank")  String lang,
                                   @RequestParam @NotBlank(message = "Currency must not be blank")  String currency,
                                   @RequestParam String name, @RequestParam String description) throws IdNotFoundException {
        Product product = clientSrvice.getOne(lang, currency, name, description);
        return new ResponseEntity(product, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getById(@PathVariable("id") @Min(1) Long id, @RequestParam @NotBlank String lang,
                                    @RequestParam @NotBlank String currency) throws IdNotFoundException {
        Product product = clientSrvice.getById(id, lang, currency);
        return new ResponseEntity(product, HttpStatus.OK);
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        return new ResponseEntity<>("error format requst", HttpStatus.BAD_REQUEST);
//    }
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


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {

        return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {

        return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUnexpectedTypeException(UnexpectedTypeException e) {

        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
