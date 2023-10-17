package com.springshop.domains.category;

import com.springshop.openapi.api.CategoriesApi;
import com.springshop.openapi.model.CategoryCreateDto;
import com.springshop.openapi.model.CategoryDto;
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
    public ResponseEntity<CategoryDto> createCategory(CategoryCreateDto categoryCreateDto) {

        log.info("Creating category: {}", categoryCreateDto);

        try {
            Category category = categoryMapper.dtoToEntity(categoryCreateDto);
            Category createdCategory = categoryService.createCategory(category);
            CategoryDto categoryDto = categoryMapper.entityToDto(createdCategory);

            // TODO Add better URI
            return ResponseEntity.created(new URI("/")).body(categoryDto);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getCategories() {
        log.info("Getting all categories");

        try {
            List<Category> categories = categoryService.getCategories();
            List<CategoryDto> categoryDtos = categories.stream()
                    .map(categoryMapper::entityToDto)
                    .toList();

            return ResponseEntity.ok(categoryDtos);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }
}