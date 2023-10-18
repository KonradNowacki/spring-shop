package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.openapi.model.CategoryDto;
import com.springshop.openapi.model.ProductCreateDto;
import com.springshop.openapi.model.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToEntity(ProductCreateDto dto);
    CategoryDto categoryEntityToDto(Category category);

    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoryEntityToDto")
    ProductDto entityToDto(Product entity);

    @Named("categoryEntityToDto")
    default List<CategoryDto> categoryEntityToDto(List<Category> categories) {
        return categories.stream().map(this::categoryEntityToDto).toList();
    }
}
