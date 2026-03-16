package com.product.productordersystem.database;


import org.springframework.data.jpa.repository.JpaRepository;
import com.product.productordersystem.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByEnabledTrue();

}