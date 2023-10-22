package com.springshop.domains.product;

import com.springshop.openapi.api.ProductsApi;
import com.springshop.openapi.model.ProductCreateDto;
import com.springshop.openapi.model.ProductCreateRequest;
import com.springshop.openapi.model.ProductResponse;
import com.springshop.openapi.model.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductCreateRequest productCreateRequest) {
        log.info("Creating product: {}", productCreateRequest);

        try {
            Product product = productMapper.createDtoToEntity(productCreateRequest);
            Product createdProduct = productService.createProduct(product, productCreateRequest.getCategoryIds());
            ProductResponse productResponse = productMapper.entityToDto(createdProduct);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(productResponse);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error creating product: {}", productCreateRequest, e);
        }

        return null;
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProducts() {
        log.info("Getting all products");

        try {
            List<Product> products = productService.getProducts();
            List<ProductResponse> productDtos = products.stream().map(productMapper::entityToDto).toList();

            return ResponseEntity.ok(productDtos);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);

        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting product with id: {}", id, e);
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
        }

        return null;
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(Long id, ProductUpdateRequest productUpdateDto) {
        log.info("Updating product: {}", productUpdateDto);

        try {
            Product createdProduct = productService.updateProduct(id, productUpdateDto);
            ProductResponse productDto = productMapper.entityToDto(createdProduct);

            System.out.println("productDto: " + productDto);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(productDto);
        } catch (Exception e) {
            log.error("Error updating product: {}", productUpdateDto, e);
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
        }

        return null;
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long id) {
        log.info("Getting product with id: {}", id);

        try {
            Product createdProduct = productService.getProductById(id);
            ProductResponse productDto = productMapper.entityToDto(createdProduct);

            System.out.println("productDto: " + productDto);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(productDto);
        } catch (Exception e) {
            log.error("Error getting product with id: {}", id, e);
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
        }

        return null;
    }
}
