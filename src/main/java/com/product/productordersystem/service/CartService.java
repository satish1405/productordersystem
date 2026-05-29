package com.product.productordersystem.service;

import com.product.productordersystem.DTO.CartRequest;
import com.product.productordersystem.database.*;
import com.product.productordersystem.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemsRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartItems addToCart(Long userId, CartRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        AddCart cart = cartRepository.findByUser(user)
                .orElse(null);

        if(cart == null){
            cart = new AddCart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        CartItems item = new CartItems();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        return cartItemsRepository.save(item);
    }
}