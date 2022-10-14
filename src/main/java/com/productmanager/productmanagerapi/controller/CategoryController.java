package com.productmanager.productmanagerapi.controller;

import com.productmanager.productmanagerapi.dto.CategoryDTO;
import com.productmanager.productmanagerapi.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Save category in Db .", operationId = "saveCategory")
    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO response = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch all the categories from Db.", operationId = "getCategories")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<CategoryDTO> response = categoryService.getCategories();
        if(CollectionUtils.isEmpty(response)){
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Fetch a specific category from Db.", operationId = "getCategory")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") long id) {
        CategoryDTO response = categoryService.getCategoryDTO(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Fetch the category of product using id from Db.", operationId = "getCategoryByProductId")
    @GetMapping("/product/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByProductId(@PathVariable("id") long productId) {
        CategoryDTO response = categoryService.getCategoryByProductId(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete specific category from Db.", operationId = "deleteCategory")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        Boolean isRemoved = categoryService.deleteCategory(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Set a product to a category.", operationId = "addProductToCategory")
    @PutMapping("/{categoryId}/product/{productId}")
    public ResponseEntity<CategoryDTO> addProductToCategory(@PathVariable long productId,
                                                           @PathVariable long categoryId) {
        CategoryDTO response = categoryService.addProductToCategory(productId, categoryId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Remove a product from a category.", operationId = "removeProductFromCategory")
    @DeleteMapping("/{categoryId}/product/{productId}")
    public ResponseEntity<String> removeProductFromCategory(@PathVariable long categoryId,
                                                            @PathVariable long productId) {

        categoryService.removeProductFromCategory(productId, categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
