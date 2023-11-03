package com.springshop.domains.category;

import com.springshop.openapi.model.CategoryCreateRequest;
import com.springshop.openapi.model.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    CategoryResponse createCategory(CategoryCreateRequest categoryCreateRequest) {
        Category category = categoryMapper.createDtoToEntity(categoryCreateRequest);
        return categoryMapper.entityToResponse(categoryRepository.save(category));
    }

    List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::entityToResponse)
                .toList();
    }

}
