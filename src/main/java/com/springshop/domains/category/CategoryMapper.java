package com.springshop.domains.category;

import com.springshop.openapi.model.CategoryCreateRequest;
import com.springshop.openapi.model.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category createDtoToEntity(CategoryCreateRequest categoryCreateRequest);
    CategoryResponse entityToResponse(Category category);
}
