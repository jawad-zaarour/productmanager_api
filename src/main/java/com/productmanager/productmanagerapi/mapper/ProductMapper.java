package com.productmanager.productmanagerapi.mapper;

import com.productmanager.productmanagerapi.dto.ProductCreationDTO;
import com.productmanager.productmanagerapi.dto.ProductDTO;
import com.productmanager.productmanagerapi.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productsToProductDTOs(List<Product> products);

    Product productDtoToProduct(ProductDTO productDTO);

    ProductCreationDTO productToProductCreationDTO(Product product);

    List<ProductCreationDTO> productsToProductCreationDTOs(List<Product> products);

    Product productCreationDtoToProduct(ProductCreationDTO productCreationDTO);
}
