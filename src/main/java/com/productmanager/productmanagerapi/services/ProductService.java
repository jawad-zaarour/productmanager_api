package com.productmanager.productmanagerapi.services;

import com.productmanager.productmanagerapi.dto.ProductCreationDTO;
import com.productmanager.productmanagerapi.dto.ProductDTO;
import com.productmanager.productmanagerapi.mapper.ProductMapper;
import com.productmanager.productmanagerapi.models.Product;
import com.productmanager.productmanagerapi.repository.ProductRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO saveProduct(ProductCreationDTO productCreationDTO) {
        Product product = productMapper.productCreationDtoToProduct(productCreationDTO);
        return productMapper.productToProductDTO(productRepository.save(product));
    }

    public List<ProductDTO> getProducts() {
        List<Product> lstProducts = productRepository.findAll();
        if (CollectionUtils.isNotEmpty(lstProducts)) {
            return productMapper.productsToProductDTOs(lstProducts);
        } else return Collections.emptyList();
    }

    public ProductDTO getProductDTO(long id) {
        return productRepository.findById(id)
                .map(productMapper::productToProductDTO).orElse(null);
    }

    public Product getProduct(long id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    public List<ProductDTO> getAllProductsByCategoryId(Long categoryId) {

        List<Product> lstProducts = productRepository.findProductsByCategoryId(categoryId);
        if (CollectionUtils.isNotEmpty(lstProducts)) {
            return productMapper.productsToProductDTOs(lstProducts);
        } else return Collections.emptyList();

    }

    public Boolean deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
