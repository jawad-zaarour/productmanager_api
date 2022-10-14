package com.productmanager.productmanagerapi.repository;

import com.productmanager.productmanagerapi.models.Category;
import com.productmanager.productmanagerapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findCategoryByProductsId(Long productId);

}
