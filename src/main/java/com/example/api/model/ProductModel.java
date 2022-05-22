package com.example.api.model;

import com.example.api.entity.Description;
import com.example.api.entity.Price;

import java.util.List;

public class ProductModel {
    List<Description> descriptions;
    List<Price> prices;

    public ProductModel(List<Description> descriptions, List<Price> prices) {
        this.descriptions = descriptions;
        this.prices = prices;
    }

    public ProductModel() {
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
