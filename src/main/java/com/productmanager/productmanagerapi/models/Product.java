package com.productmanager.productmanagerapi.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY_IN_STOCK")
    private int quantityInStock;

    @Column(name = "PRICE")
    private float price;

    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TS")
    private Date created_TS;

    @Column(name = "CREATED_BY")
    private String created_By;

    @Column(name = "UPDATED_BY")
    private String updated_By;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TS")
    private Date updated_TS;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreated_TS() {
        return created_TS;
    }

    public void setCreated_TS(Date created_TS) {
        this.created_TS = created_TS;
    }

    public String getCreated_By() {
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    public String getUpdated_By() {
        return updated_By;
    }

    public void setUpdated_By(String updated_By) {
        this.updated_By = updated_By;
    }

    public Date getUpdated_TS() {
        return updated_TS;
    }

    public void setUpdated_TS(Date updated_TS) {
        this.updated_TS = updated_TS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}