package com.springshop.domains.category;

import com.springshop.openapi.model.CategoryCreateRequest;
import com.springshop.openapi.model.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category dtoToEntity(CategoryCreateRequest categoryCreateRequest);
    CategoryResponse entityToDto(Category category);
}
