package com.example.api.service;

import com.example.api.entity.Product;
import com.example.api.exeptons.IdNotFoundException;
import com.example.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientSrvice {

    @Autowired
    ProductRepository productRepository;


    public Iterable<Product> getAll(String lang, String currency, int page) throws IdNotFoundException {
        Iterable<Product> products = productRepository.findByLangAndCurrency(lang.toUpperCase(), currency.toUpperCase(), PageRequest.of(page, 2));
        products.forEach((p) -> p.getDescriptions().removeIf(d -> (!d.getLang().equals(lang.toUpperCase())))); //Здесь я вычищаю из List<Descriptions> и <Price> записи с неподходящими языком и валютой.
        products.forEach((p) -> p.getPrices().removeIf(price -> (!price.getCurrencyCode().equals(currency.toUpperCase())))); // Я недостаточно разобрался с JPA и Product подтягивает все Discription и Price. так себе решение.
        if (!products.iterator().hasNext()) {
            throw new IdNotFoundException("Product not found");
        }
        return products;
    }

    public Product getOne(String lang, String currency, String name, String description) throws IdNotFoundException {
        Product product = productRepository.findByLangAndCurrencyAndNameOrDesc(lang.toUpperCase(), currency.toUpperCase(), name, description);
        if (product != null) {
            throw new IdNotFoundException("Product not found");
        }
        product.getDescriptions().removeIf(d -> (!d.getLang().equals(lang.toUpperCase())));
        product.getPrices().removeIf(price -> (!price.getCurrencyCode().equals(currency.toUpperCase())));
        return product;
    }

    public Product getById(Long id, String lang, String currency) throws IdNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new IdNotFoundException("Product not found");
        }
        Product product = productRepository.findById(id).get();
        product.getDescriptions().removeIf(d -> (!d.getLang().equals(lang.toUpperCase())));
        product.getPrices().removeIf(price -> (!price.getCurrencyCode().equals(currency.toUpperCase())));
        return product;
    }


}
