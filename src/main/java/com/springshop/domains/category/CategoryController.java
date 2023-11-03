package com.springshop.domains.category;

import com.springshop.openapi.api.CategoriesApi;
import com.springshop.openapi.model.CategoryCreateRequest;
import com.springshop.openapi.model.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public final class CategoryController implements CategoriesApi {

    private final CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryCreateRequest categoryCreateRequest) {

        log.info("Creating category: {}", categoryCreateRequest);

        try {
            CategoryResponse category = categoryService.createCategory(categoryCreateRequest);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(category);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        log.info("Getting all categories");

        try {
            List<CategoryResponse> categories = categoryService.getCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Error getting categories", e);
        }

        return null;
    }
}
