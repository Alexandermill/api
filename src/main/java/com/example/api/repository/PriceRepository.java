package com.example.api.repository;

import com.example.api.entity.Price;
import org.springframework.data.repository.CrudRepository;


public interface PriceRepository extends CrudRepository<Price, Long> {
}
