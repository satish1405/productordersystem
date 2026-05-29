package com.product.productordersystem.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RoleChange {
    private String role;

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}