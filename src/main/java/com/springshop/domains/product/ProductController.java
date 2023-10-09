package com.springshop.domains.product;

import com.springshop.openapi.api.ProductsApi;
import com.springshop.openapi.model.ProductResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductsApi {

    @Override
    public ResponseEntity<List<ProductResponseModel>> getProductById() {

        ProductResponseModel productResponseModel = new ProductResponseModel();
        productResponseModel.setProductName("Product 1");

        return ResponseEntity.ok(List.of(productResponseModel));
    }

}
