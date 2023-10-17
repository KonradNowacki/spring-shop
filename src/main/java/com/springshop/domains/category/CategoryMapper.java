package com.springshop.domains.category;

import com.springshop.openapi.model.CategoryCreateDto;
import com.springshop.openapi.model.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category dtoToEntity(CategoryCreateDto categoryCreateDto);
    CategoryDto entityToDto(Category category);
}
