package com.productmanager.productmanagerapi.services;

import com.productmanager.productmanagerapi.dto.CategoryDTO;
import com.productmanager.productmanagerapi.mapper.CategoryMapper;
import com.productmanager.productmanagerapi.models.Category;
import com.productmanager.productmanagerapi.models.Product;
import com.productmanager.productmanagerapi.models.User;
import com.productmanager.productmanagerapi.repository.CategoryRepository;
import com.productmanager.productmanagerapi.repository.ProductRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDTO);
        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }

    public List<CategoryDTO> getCategories() {
        List<Category> lstCategories = categoryRepository.findAll();
        if (CollectionUtils.isNotEmpty(lstCategories)) {
            return categoryMapper.categoriesToCategoryDTOs(lstCategories);
        } else return Collections.emptyList();
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElse(null);
    }

    public CategoryDTO getCategoryDTO(long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategoryDTO).orElse(null);
    }

    public CategoryDTO getCategoryByProductId(long id) {
        if (productRepository.existsById(id)) {
            return categoryMapper.
                    categoryToCategoryDTO(categoryRepository.findCategoryByProductsId(id));
        }
        return null;
    }


    public CategoryDTO addProductToCategory(long productId, long categoryId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isEmpty() || optionalProduct.isEmpty()){
            //throw an exception
        }
        Product product = optionalProduct.get();
        Category category = optionalCategory.get();
        category.addProduct(product);
        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }

    public void removeProductFromCategory(long productId, long categoryId) {
        Category category = this.getCategory(categoryId);
        Optional<Product> product = productRepository.findById(productId);
        category.removeProduct(product.get());
        categoryRepository.save(category);
    }

    public Boolean deleteCategory(long id) {

        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
