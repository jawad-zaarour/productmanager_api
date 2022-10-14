package com.productmanager.productmanagerapi.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(unique = true,name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(unique = true,name = "EMAIL")
    private String email;

    @Column(name = "IS_ACTIVE")
    private boolean active;

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

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.setUser(this);
    }

    public void removeProduct(Product product) {
        product.setUser(null);
        this.products.remove(product);
    }

    public void removeRole(int roleId) {
        Role role = this.roles.stream().filter(t -> t.getId() == roleId).findFirst().orElse(null);
        if (role != null) {
            this.roles.remove(role);
            role.getUsers().add(this);
        }
    }
}
