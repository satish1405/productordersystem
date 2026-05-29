package com.product.productordersystem.controller;

import com.product.productordersystem.DTO.RoleChange;
import com.product.productordersystem.database.UserRepository;
import com.product.productordersystem.entity.Product;
import com.product.productordersystem.entity.Role;
import com.product.productordersystem.entity.User;
import com.product.productordersystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("api/products/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final UserRepository userRepo;
    private final ProductService productService;
    //  Create new user (ADMIN / USER)
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    //  Change role
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/change-role/{id}")
    public User changeRole(@PathVariable Long id, @RequestBody RoleChange request) {
        User user = userRepo.findById(id).orElseThrow();

        // Use only the role field from the JSON
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));

        return userRepo.save(user);
    }

    //  View all users
   @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    //  View all product
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/allproduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProductsForAdmin());
    }
}
