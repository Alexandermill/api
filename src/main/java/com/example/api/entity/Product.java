package com.example.api.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @CreationTimestamp
    @Column(name = "create_at")
    LocalDateTime creatAt;
    @CreationTimestamp
    @Column(name = "modified_at")
    LocalDateTime modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    List<Description> descriptions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    List<Price> prices;

    public  void addDescription(Description description){
        descriptions.add(description);
        description.setProduct(this);
    }

    public void removeDescription(Description description) {
        descriptions.remove(description);
        description.setProduct(null);
    }

    public  void addPrice(Price price ){
        prices.add(price);
        price.setProduct(this);
    }

    public void removePrice(Price price) {
        prices.remove(price);
        price.setProduct(null);
    }

    public Product() {
        this.descriptions = new ArrayList<>();
        this.prices = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions)
    {
//        descriptions.forEach((d) -> d.setProduct(this));
        this.descriptions = descriptions;

    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
