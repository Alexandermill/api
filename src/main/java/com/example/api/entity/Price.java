package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Price {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    double price;

    @Column(name = "currency_code")
    @NotBlank
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
