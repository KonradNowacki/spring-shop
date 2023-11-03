package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.domains.category.CategoryRepository;
import com.springshop.openapi.model.ProductCreateRequest;
import com.springshop.openapi.model.ProductResponse;
import com.springshop.openapi.model.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    ProductResponse createProduct(ProductCreateRequest productCreateRequest) {

        Product product = productMapper.createDtoToEntity(productCreateRequest);

        List<Category> categories = categoryRepository.findAllById(productCreateRequest.getCategoryIds());
        product.setCategories(categories);

        Product createdProduct = productRepository.save(product);
        return productMapper.entityToResponse(createdProduct);
    }

    List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(productMapper::entityToResponse).toList();
    }

    void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %d not found", id)));

        productRepository.deleteById(id);
    }

    ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateDto) {
        Product p = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %d not found", id)));

        productMapper.updateProductFromDto(productUpdateDto, p);

        if (productUpdateDto.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(productUpdateDto.getCategoryIds());
            p.setCategories(categories);
        }

        Product updatedProduct = productRepository.save(p);

        return productMapper.entityToResponse(updatedProduct);
    }

    ProductResponse getProductById(Long id) {
        return productRepository
                .findById(id)
                .map(productMapper::entityToResponse)
                .orElseThrow(
                () -> new NotFoundException(String.format("Product with id %d not found", id)));
    }

}
