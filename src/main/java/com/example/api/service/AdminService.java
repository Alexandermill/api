
package com.example.api.service;

import com.example.api.entity.Description;
import com.example.api.entity.Product;
import com.example.api.model.ProductModel;
import com.example.api.repository.DescriptionRepository;
import com.example.api.repository.PriceRepository;
import com.example.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    PriceRepository priceRepository;

    public Product addProduct(ProductModel productModel){
        Product product = new Product();
        productModel.getDescriptions().forEach((d) -> product.addDescription(d));
        productModel.getPrices().forEach((p) -> product.addPrice(p));
        return productRepository.save(product);
    }

    public Iterable<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(Long id){
        return productRepository.findById(id).get();
    }

    public void updateProduct(Long id , ProductModel productModel){
        Product product = productRepository.findById(id).get();
        product.getDescriptions().clear();
        product.getPrices().clear();
        productModel.getDescriptions().forEach((d) -> product.addDescription(d));
        productModel.getPrices().forEach((p) -> product.addPrice(p));
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
