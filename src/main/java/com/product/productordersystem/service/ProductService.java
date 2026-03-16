package com.product.productordersystem.service;


import com.product.productordersystem.DTO.ProductDTO;
import com.product.productordersystem.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDTO dto);

    Product updateProduct(Long id, ProductDTO dto);

    List<Product> getAllProducts();
    List<Product> getAllProductsForAdmin();
    List<Product> getEnabledProducts();
     Product toggleProduct(Long id);
}