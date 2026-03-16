package com.product.productordersystem.service;

import com.product.productordersystem.DTO.ProductDTO;
import com.product.productordersystem.database.ProductRepository;
import com.product.productordersystem.entity.Product;
import com.product.productordersystem.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDTO dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setName(dto.getName());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findByEnabledTrue();
    }

    public List<Product> getAllProductsForAdmin(){
        return productRepository.findAll();
    }

    public List<Product> getEnabledProducts(){
        return productRepository.findByEnabledTrue();
    }

    public Product toggleProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product not found"));

        product.setEnabled(!product.getEnabled());

        return productRepository.save(product);
    }
}