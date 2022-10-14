package com.productmanager.productmanagerapi.controller;

import com.productmanager.productmanagerapi.dto.ProductCreationDTO;
import com.productmanager.productmanagerapi.dto.ProductDTO;
import com.productmanager.productmanagerapi.models.Product;
import com.productmanager.productmanagerapi.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Save product in Db.", operationId = "saveCategory")
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductCreationDTO productCreationDTO) {
        ProductDTO response = productService.saveProduct(productCreationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch all the products from Db.", operationId = "getProducts")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> response = productService.getProducts();
        if(CollectionUtils.isEmpty(response)){
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Fetch a specific product from Db.", operationId = "getProduct")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") long id) {
        ProductDTO response = productService.getProductDTO(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete specific product from Db.", operationId = "deleteProduct")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        Boolean isRemoved = productService.deleteProduct(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Fetch all products of a specific category from Db.", operationId = "getAllProductsByCategoryId")
    @GetMapping("/category/{CategoryId}")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategoryId(
            @PathVariable(value = "CategoryId") Long categoryId) {
        List<ProductDTO> products = productService.getAllProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



}
