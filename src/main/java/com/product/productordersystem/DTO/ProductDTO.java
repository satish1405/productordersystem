package com.product.productordersystem.DTO;

import jakarta.validation.constraints.*;
        import lombok.*;

@Getter
@Setter

@Data
public class ProductDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    @Positive(message = "Cannot be negative value")
    private Double price;

    @Positive(message = "Cannot be negative quantity")
    private Integer quantity;
}