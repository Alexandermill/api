package com.example.api.service;

import com.example.api.entity.Product;
import com.example.api.exeptons.IdNotFoundException;
import com.example.api.repository.DescriptionRepository;
import com.example.api.repository.PriceRepository;
import com.example.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ClientSrvice {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    PriceRepository priceRepository;


    public Iterable<Product> getAll(String lang, String currency) {
        Iterable<Product> products = productRepository.findByLangAndCurrency(lang.toUpperCase(), currency.toUpperCase());
        products.forEach((p) -> p.getDescriptions().removeIf(d -> (!d.getLang().equals(lang.toUpperCase()))));
        products.forEach((p) -> p.getPrices().removeIf(price -> (!price.getCurrencyCode().equals(currency.toUpperCase()))));
        return products;
    }

    public Product getOne(String lang, String currency, String name, String description) throws IdNotFoundException {
        Product product = productRepository.findByLangAndCurrencyAndNameOrDesc(lang.toUpperCase(), currency.toUpperCase(), name, description);
        if (product.getDescriptions().isEmpty() || product.getPrices().isEmpty()) {
            throw new IdNotFoundException("Product not found");
        }
        product.getDescriptions().removeIf(d -> (!d.getLang().equals(lang.toUpperCase())));
        product.getPrices().removeIf(price -> (!price.getCurrencyCode().equals(currency.toUpperCase())));
        return product;
    }

    public Product getById(Long id, String lang, String currency) throws IdNotFoundException {
        Product product = productRepository.findById(id).get();
        if (!productRepository.existsById(id)) {
            throw new IdNotFoundException("Product not found");
        }
        product.getDescriptions().removeIf(d -> (!d.getLang().equals(lang.toUpperCase())));
        product.getPrices().removeIf(price -> (!price.getCurrencyCode().equals(currency.toUpperCase())));
        return product;
    }


}
