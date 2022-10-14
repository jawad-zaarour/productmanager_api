package com.productmanager.productmanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class ProductCreationDTO {
    @JsonIgnore
    private Long id;
    @NotEmpty(message = "The name of product is required.")
    private String name;
    private int quantityInStock;
    private float price;
    private boolean isAvailable;
    private String imageURL;
    @FutureOrPresent(message = "Creation date must be future or present.")
    private Date created_TS;
    private String created_By;
    private String updated_By;
    @FutureOrPresent(message = "Updated date must be future or present.")
    private Date updated_TS;

    public ProductCreationDTO(Long id, String name, int quantityInStock, float price, boolean isAvailable, String imageURL, Date created_TS, String created_By, String updated_By, Date updated_TS) {
        this.id = id;
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imageURL = imageURL;
        this.created_TS = created_TS;
        this.created_By = created_By;
        this.updated_By = updated_By;
        this.updated_TS = updated_TS;
    }
}
