package com.productmanager.productmanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import javax.validation.constraints.*;

@Data
public class UserCreationDTO {
    @JsonIgnore
    private Long id;
    @NotEmpty(message = "The user name is required.")
    private String userName;
    @NotEmpty(message = "The password is required.")
    private String password;
    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;
    private boolean active;
    @FutureOrPresent(message = "Creation date must be future or present.")
    private Date created_TS;
    private String created_By;
    private String updated_By;
    @FutureOrPresent(message = "Updated date must be future or present.")
    private Date updated_TS;
}
