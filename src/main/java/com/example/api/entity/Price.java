package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue
    Long id;

    double price;
    @Column(name = "currency_code")
    String currencyCode;

    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    @JsonIgnore
    Product product;

    public Price() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
