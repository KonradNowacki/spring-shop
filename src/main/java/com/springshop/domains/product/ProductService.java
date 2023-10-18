package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.domains.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product createProduct(Product product, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
