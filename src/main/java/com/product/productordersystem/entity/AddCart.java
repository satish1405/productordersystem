package com.product.productordersystem.entity;

import jakarta.persistence.*;
        import lombok.*;
        import java.util.List;

@Entity
@Table(name = "add_cart")
@Getter
@Setter
public class AddCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItems> items;
}