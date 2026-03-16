package com.product.productordersystem.entity;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Getter
@Setter
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orders order;

    @ManyToOne
    private Product product;

    private Integer quantity;

    private Double price;
}