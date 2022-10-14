package com.productmanager.productmanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class RoleDTO {

    @JsonIgnore
    private int id;

    private String name;


}
