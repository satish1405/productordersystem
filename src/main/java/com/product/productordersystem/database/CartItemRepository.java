package com.product.productordersystem.database;


import com.product.productordersystem.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
}