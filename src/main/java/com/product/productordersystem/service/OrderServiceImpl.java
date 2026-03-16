package com.product.productordersystem.service;
import com.product.productordersystem.entity.AddCart;
import com.product.productordersystem.entity.CartItems;
import com.product.productordersystem.entity.OrderItems;
import com.product.productordersystem.entity.Orders;
import com.product.productordersystem.entity.Product;
import com.product.productordersystem.database.CartRepository;
import com.product.productordersystem.database.OrderRepository;
import com.product.productordersystem.database.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Orders placeOrder(Long cartId) {

        AddCart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        Orders order = new Orders();

        List<OrderItems> orderItems = new ArrayList<>();

        double totalAmount = 0;

        for (CartItems cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            int requestedQty = cartItem.getQuantity();


            if (product.getQuantity() < requestedQty) {
                throw new RuntimeException(
                        "Insufficient inventory for product: " + product.getName());
            }


            product.setQuantity(product.getQuantity() - requestedQty);
            productRepository.save(product);

            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(requestedQty);
            orderItem.setPrice(product.getPrice());

            totalAmount += product.getPrice() * requestedQty;

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);


        return orderRepository.save(order);
    }
}