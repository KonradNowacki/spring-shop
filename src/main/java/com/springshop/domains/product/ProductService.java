package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.domains.category.CategoryRepository;
import com.springshop.openapi.model.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
        productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %d not found", id)));

        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductUpdateRequest productUpdateDto) {
        Product p = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %d not found", id)));

        productMapper.updateProductFromDto(productUpdateDto, p);

        if (productUpdateDto.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(productUpdateDto.getCategoryIds());
            p.setCategories(categories);
        }

        return productRepository.save(p);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %d not found", id)));
    }

}
