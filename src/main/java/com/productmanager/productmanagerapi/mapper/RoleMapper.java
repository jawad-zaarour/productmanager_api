package com.productmanager.productmanagerapi.mapper;

import com.productmanager.productmanagerapi.dto.RoleDTO;
import com.productmanager.productmanagerapi.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO roleToRoleDTO(Role role);

    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);

    Role roleDtoToRole(RoleDTO roleDTO);
}
