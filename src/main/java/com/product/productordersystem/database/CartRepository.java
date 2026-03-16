package com.product.productordersystem.database;

import com.product.productordersystem.entity.AddCart;
import com.product.productordersystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<AddCart, Long> {

    Optional<AddCart> findByUser(User user);

}