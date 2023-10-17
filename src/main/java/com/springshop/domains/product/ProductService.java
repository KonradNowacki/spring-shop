package com.springshop.domains.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        System.out.printf("Creating product: %s%n", product);
        return productRepository.save(product);
    }

}
