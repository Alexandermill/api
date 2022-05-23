package com.example.api.repository;

import com.example.api.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Long> {

    @Query("select distinct p from Product p left join p.descriptions descriptions left join p.prices prices where descriptions.lang = ?1 and prices.currencyCode = ?2 order by p.id")
    Iterable<Product> findByLangAndCurrency(String lang, String currency);

    @Query("select p from Product p inner join p.descriptions descriptions where descriptions.name = ?1 or descriptions.description = ?2")
    Product findByNameOrDescription(String name, String description);

    @Query("select distinct p from Product p left join p.descriptions d left join p.prices prices where (d.name = :name or d.description = :desc) and (d.lang = :lang and prices.currencyCode = :currency) order by p.id" )
    Product findByLangAndCurrencyAndNameOrDesc(String lang, String currency, String name, String desc);



}
