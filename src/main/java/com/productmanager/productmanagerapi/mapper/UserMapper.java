package com.productmanager.productmanagerapi.mapper;

import com.productmanager.productmanagerapi.dto.UserCreationDTO;
import com.productmanager.productmanagerapi.dto.UserDTO;
import com.productmanager.productmanagerapi.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);

    User userDtoToUser(UserDTO userDTO);

    UserCreationDTO userToUserCreationDTO(User user);

    User userCreationDtoToUser(UserCreationDTO userCreationDTO);

}
