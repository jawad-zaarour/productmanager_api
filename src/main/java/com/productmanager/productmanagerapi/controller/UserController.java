package com.productmanager.productmanagerapi.controller;

import com.productmanager.productmanagerapi.dto.UserCreationDTO;
import com.productmanager.productmanagerapi.dto.UserDTO;
import com.productmanager.productmanagerapi.models.User;
import com.productmanager.productmanagerapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Fetch all the users from Db.", operationId = "getUsers")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> response = userService.getUsers();
        if(CollectionUtils.isEmpty(response)){
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Fetch specific user from Db.", operationId = "getUser")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        UserDTO userResponse = userService.getUserDTO(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete specific user from Db.", operationId = "deleteUser")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        Boolean isRemoved = userService.deleteUser(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Save a user in the Db.", operationId = "saveUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful: the user has been saved."),
            @ApiResponse(responseCode = "400", description = "The provided User is not valid")
    })
    @PostMapping("/")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserCreationDTO userCreationDTO)
    {
        UserDTO userResponse = userService.saveUser(userCreationDTO);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update user in the Db.", operationId = "updateUser")
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        UserDTO response = userService.updateUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Set a role to a user.", operationId = "addRoleToUser")
    @PutMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDTO> addRoleToUser(@PathVariable int roleId, @PathVariable long userId) {
        // try/catch(NotFoundException): in case of catch return http 404
        UserDTO response = userService.addRoleToUser(roleId, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Remove a role from a user.", operationId = "removeRoleFromUser")
    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable int roleId, @PathVariable long userId) {

        userService.removeRoleFromUser(roleId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(summary = "Fetch all the users of a specific role.", operationId = "getAllUsersByRoleId")
    @GetMapping("/role/{RoleId}")
    public ResponseEntity<List<UserDTO>> getAllUsersByRoleId(
            @PathVariable(value = "RoleId") int roleId) {
        List<UserDTO> users = userService.getAllUsersByRoleId(roleId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
