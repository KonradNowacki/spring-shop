package com.springshop.domains.product;

import com.springshop.openapi.api.ProductsApi;
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
public final class ProductController implements ProductsApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductCreateRequest productCreateRequest) {
        log.info("Creating product: {}", productCreateRequest);

        try {
            ProductResponse productResponse = productService.createProduct(productCreateRequest);
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
            List<ProductResponse> products = productService.getProducts();
            return ResponseEntity.ok(products);
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
            ProductResponse product = productService.updateProduct(id, productUpdateDto);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(product);
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
            ProductResponse product = productService.getProductById(id);
            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(product);
        } catch (Exception e) {
            log.error("Error getting product with id: {}", id, e);
            if (e instanceof NotFoundException) {
                return ResponseEntity.notFound().build();
            }
        }

        return null;
    }
}
