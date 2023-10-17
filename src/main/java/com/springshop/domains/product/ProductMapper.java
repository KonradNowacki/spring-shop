package com.springshop.domains.product;

import com.springshop.openapi.model.ProductCreateDto;
import com.springshop.openapi.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToEntity(ProductCreateDto dto);


    ProductDto entityToDto(Product entity);

}
