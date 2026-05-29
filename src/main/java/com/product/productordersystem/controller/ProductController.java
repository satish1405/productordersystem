package com.product.productordersystem.controller;

import com.product.productordersystem.DTO.CartRequest;
import com.product.productordersystem.DTO.ProductDTO;
import com.product.productordersystem.entity.CartItems;
import com.product.productordersystem.entity.Product;
import com.product.productordersystem.service.CartService;
import com.product.productordersystem.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    // Add Product (ADMIN only)
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.addProduct(dto));
    }

    // Update Product (ADMIN only)
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
    //  Get All Products (ADMIN only)
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProductsForAdmin());
    }
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<List<Product>> getEnabledProductsForUsers() {
        return ResponseEntity.ok(productService.getEnabledProducts());
    }

    //  Public endpoint (no auth required OR logged user)
    @GetMapping
    public ResponseEntity<List<Product>> getEnabledProducts() {
        return ResponseEntity.ok(productService.getEnabledProducts());
    }

    //  Enable / Disable Product (ADMIN only)
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/toggle/{id}")
    public ResponseEntity<Product> toggleProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.toggleProduct(id));
    }

        @PostMapping("/cart/add")
        public ResponseEntity<CartItems> addToCart(
                @RequestHeader Long userId,
                @RequestBody CartRequest request) {

            CartItems item = cartService.addToCart(userId, request);
            return ResponseEntity.ok(item);
        }
}








//package com.product.productordersystem.controller;
//
//import com.product.productordersystem.DTO.CartRequest;
//import com.product.productordersystem.DTO.ProductDTO;
//import com.product.productordersystem.entity.CartItems;
//import com.product.productordersystem.entity.Product;
//import com.product.productordersystem.entity.User;
//import com.product.productordersystem.database.UserRepository;
//import com.product.productordersystem.exception.CustomException;
//import com.product.productordersystem.service.ProductService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/products")
//@RequiredArgsConstructor
//public class ProductController {
//
//    private final ProductService productService;
//    private final UserRepository userRepository; // important!
//
//
//    @PostMapping
//    public ResponseEntity<Product> addProduct(@RequestHeader Long userId,
//                                              @Valid @RequestBody ProductDTO dto) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!"ADMIN".equals(user.getRole())) {
//            throw new CustomException("Access to the resource is prohibited.");        }
//
//        Product product = productService.addProduct(dto);
//        return ResponseEntity.ok(product);
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@RequestHeader Long userId,
//                                                 @PathVariable Long id,
//                                                 @RequestBody ProductDTO dto) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!"ADMIN".equals(user.getRole())) {
//            throw new CustomException("Access to the resource is prohibited.");
//        }
//
//        Product product = productService.updateProduct(id, dto);
//        return ResponseEntity.ok(product);
//    }
//
//    // All users can view products
//    @GetMapping("/admin")
//    public List<Product> getAllProducts(@RequestHeader Long userId){
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new CustomException("User not found"));
//
//        if(!"ADMIN".equals(user.getRole())){
//            throw new CustomException("Access denied. Only ADMIN can view all products");
//        }
//
//        return productService.getAllProductsForAdmin();
//    }
//    @GetMapping
//    public List<Product> getEnabledProducts() {
//        return productService.getEnabledProducts();
//    }
//
////    @PostMapping("/cart/add")
////    public ResponseEntity<CartItems> addToCart(
////            @RequestHeader Long userId,
////            @RequestBody CartRequest request) {
////
////        CartItems item = cartService.addToCart(userId, request);
////        return ResponseEntity.ok(item);
////    }
//
//    @PutMapping("/toggle/{id}")
//    public ResponseEntity<Product> toggleProduct(
//            @RequestHeader Long userId,
//            @PathVariable Long id) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new CustomException("User not found"));
//
//        if(!"ADMIN".equals(user.getRole())){
//            throw new CustomException("Only ADMIN can enable or disable product");
//        }
//
//        return ResponseEntity.ok(productService.toggleProduct(id));
//    }
//}
