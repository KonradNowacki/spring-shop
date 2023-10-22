package com.springshop.domains.product;

import com.springshop.domains.category.Category;
import com.springshop.openapi.model.*;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product createDtoToEntity(ProductCreateRequest dto);
    CategoryResponse categoryEntityToDto(Category category);

    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoryEntityToDto")
    ProductResponse entityToDto(Product entity);

    @Named("categoryEntityToDto")
    default List<CategoryResponse> categoryEntityToDto(List<Category> categories) {
        if (isNull(categories)) {
            return new ArrayList<>();
        }
        return categories.stream().map(this::categoryEntityToDto).toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductUpdateRequest dto, @MappingTarget Product entity);
}
