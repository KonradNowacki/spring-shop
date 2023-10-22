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
public class CategoryController implements CategoriesApi {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryResponse> createCategory(CategoryCreateRequest categoryCreateRequest) {

        log.info("Creating category: {}", categoryCreateRequest);

        try {
            Category category = categoryMapper.dtoToEntity(categoryCreateRequest);
            Category createdCategory = categoryService.createCategory(category);
            CategoryResponse categoryDto = categoryMapper.entityToDto(createdCategory);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(categoryDto);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        log.info("Getting all categories");

        try {
            List<Category> categories = categoryService.getCategories();
            List<CategoryResponse> categoryDtos = categories.stream()
                    .map(categoryMapper::entityToDto)
                    .toList();

            return ResponseEntity.ok(categoryDtos);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }
}
