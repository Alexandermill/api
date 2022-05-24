package com.example.api.repository;

import com.example.api.entity.Description;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DescriptionRepository extends CrudRepository<Description, Long> {

    List<Description> deleteByProduct_id(Long id);
}
