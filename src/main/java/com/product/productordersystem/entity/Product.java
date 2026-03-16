package com.product.productordersystem.entity;
import jakarta.persistence.*;
        import lombok.*;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private Boolean enabled = true;
}