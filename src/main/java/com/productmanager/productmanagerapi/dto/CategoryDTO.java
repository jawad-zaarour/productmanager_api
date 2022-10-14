package com.productmanager.productmanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productmanager.productmanagerapi.models.Product;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private String imageURL;
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public CategoryDTO(String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }
}
