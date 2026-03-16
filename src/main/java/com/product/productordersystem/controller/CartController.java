package com.product.productordersystem.controller;

import com.product.productordersystem.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addToCart(
            @RequestHeader Long userId,
            @RequestParam Long productId,
            @RequestParam Integer quantity){

        return cartService.addToCart(userId, productId, quantity);
    }

}