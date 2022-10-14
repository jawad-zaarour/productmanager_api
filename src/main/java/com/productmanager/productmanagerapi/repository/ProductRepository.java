package com.productmanager.productmanagerapi.repository;

import com.productmanager.productmanagerapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductsByCategoryId(Long CategoryID);

}
