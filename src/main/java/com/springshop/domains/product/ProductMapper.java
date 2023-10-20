package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.openapi.model.CategoryDto;
import com.springshop.openapi.model.ProductCreateDto;
import com.springshop.openapi.model.ProductDto;
import com.springshop.openapi.model.ProductUpdateDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product createDtoToEntity(ProductCreateDto dto);
    CategoryDto categoryEntityToDto(Category category);

    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoryEntityToDto")
    ProductDto entityToDto(Product entity);

    @Named("categoryEntityToDto")
    default List<CategoryDto> categoryEntityToDto(List<Category> categories) {
        if (isNull(categories)) {
            return new ArrayList<>();
        }
        return categories.stream().map(this::categoryEntityToDto).toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductUpdateDto dto, @MappingTarget Product entity);
}
