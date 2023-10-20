package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.domains.category.CategoryRepository;
import com.springshop.openapi.model.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public Product createProduct(Product product, List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product p = productRepository.findById(id).orElseThrow(/* TODO Add Custom Exception */);
        System.out.println("productUpdateDto = " + productUpdateDto.getCategoryIds());
        productMapper.updateProductFromDto(productUpdateDto, p);

        if (productUpdateDto.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(productUpdateDto.getCategoryIds());
            p.setCategories(categories);
        }

        return productRepository.save(p);
    }

}
