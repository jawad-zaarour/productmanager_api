package com.productmanager.productmanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Date;

@Data
public class ProductDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private int quantityInStock;
    private float price;
    private boolean isAvailable;
    private String imageURL;
    private CategoryDTO category;
    @JsonIgnore
    private Date created_TS;
    @JsonIgnore
    private String created_By;
    @JsonIgnore
    private String updated_By;
    @JsonIgnore
    private Date updated_TS;

    public ProductDTO(Long id, String name, int quantityInStock, float price, boolean isAvailable, String imageURL, CategoryDTO category, Date created_TS, String created_By, String updated_By, Date updated_TS) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imageURL = imageURL;
        this.category = category;
        this.created_TS = created_TS;
        this.created_By = created_By;
        this.updated_By = updated_By;
        this.updated_TS = updated_TS;
    }
}
