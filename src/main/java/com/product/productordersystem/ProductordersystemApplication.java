package com.product.productordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
public class ProductordersystemApplication {

	public static void main(String[] args) {
        System.out.println("encode pass" +new BCryptPasswordEncoder().encode("123")+".."); // ✅ a
        SpringApplication.run(ProductordersystemApplication.class, args);
	}

}
