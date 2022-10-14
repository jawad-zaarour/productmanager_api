package com.productmanager.productmanagerapi.controller;

import com.productmanager.productmanagerapi.dto.RoleDTO;
import com.productmanager.productmanagerapi.dto.UserDTO;
import com.productmanager.productmanagerapi.models.Role;
import com.productmanager.productmanagerapi.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Save role in Db .", operationId = "saveRole")
    @PostMapping
    public ResponseEntity<RoleDTO> saveRole(@RequestBody Role role) {
        RoleDTO response = roleService.saveRole(role);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch a specific role from Db with Id parameter .", operationId = "getRoleDTOById")
    @GetMapping("{id}")
    public ResponseEntity<RoleDTO> getRoleDTOById(@PathVariable("id") int id) {
        RoleDTO response = roleService.getRoleDTOById(id);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete role from Db .", operationId = "deleteRole")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        Boolean isRemoved = roleService.deleteRole(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Fetch all the roles of a specific user.", operationId = "getAllRolesByUserId")
    @GetMapping("/user/{UserId}")
    public ResponseEntity<List<RoleDTO>> getAllRolesByUserId(
            @PathVariable(value = "UserId") Long userId) {
        List<RoleDTO> roles = roleService.getAllRolesByUserId(userId);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
