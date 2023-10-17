package com.springshop.domains.product;

import com.springshop.openapi.api.ProductsApi;
import com.springshop.openapi.model.ProductCreateDto;
import com.springshop.openapi.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @Override
    public ResponseEntity<ProductDto> createProduct(ProductCreateDto productCreateDto) {
        log.info("Creating product: {}", productCreateDto);

        try {
            Product product = productMapper.dtoToEntity(productCreateDto);
            System.out.printf("Product mapped: %s%n", product.getName());
            Product createdProduct = productService.createProduct(product);
            ProductDto productDto = productMapper.entityToDto(createdProduct);

            System.out.printf("Created product: %s%n", productDto.getName());

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(productDto);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }
}
