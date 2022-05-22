package com.example.api.service;

import com.example.api.entity.Product;
import com.example.api.exeptons.IdNotFoundException;
import com.example.api.repository.DescriptionRepository;
import com.example.api.repository.PriceRepository;
import com.example.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    PriceRepository priceRepository;

    public Product addProduct(Product product){
        Product productNew = new Product();
        product.getDescriptions().forEach((d) -> productNew.addDescription(d));
        product.getPrices().forEach((p) -> productNew.addPrice(p));
        return productRepository.save(productNew);
    }

    public Iterable<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getById(Long id) throws IdNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new IdNotFoundException("");
        }
        return productRepository.findById(id).get();
    }

    public void updateProduct(Long id , Product product) throws IdNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new IdNotFoundException("");
        }
        Product productNew = productRepository.findById(id).get();
        product.getDescriptions().clear();
        product.getPrices().clear();
        product.getDescriptions().forEach((d) -> productNew.addDescription(d));
        product.getPrices().forEach((p) -> productNew.addPrice(p));
        productRepository.save(productNew);
    }

    public void deleteProduct(Long id) throws IdNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new IdNotFoundException("");
        }
        productRepository.deleteById(id);
    }
}
