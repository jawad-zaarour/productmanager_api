package com.productmanager.productmanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productmanager.productmanagerapi.models.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    @JsonIgnore
    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private boolean active;
    @JsonIgnore
    private Date created_TS;
    @JsonIgnore
    private String created_By;
    @JsonIgnore
    private String updated_By;
    @JsonIgnore
    private Date updated_TS;

    private Set<RoleDTO> roles = new HashSet<>();

}
