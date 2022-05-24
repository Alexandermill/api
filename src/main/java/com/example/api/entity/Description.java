package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Description {
    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotBlank
    String lang;

    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    @JsonIgnore
    Product product;

    public Description() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
