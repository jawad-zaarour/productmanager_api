package com.productmanager.productmanagerapi.services;

import com.productmanager.productmanagerapi.dto.RoleDTO;
import com.productmanager.productmanagerapi.mapper.RoleMapper;
import com.productmanager.productmanagerapi.models.Role;
import com.productmanager.productmanagerapi.repository.RoleRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;


    public RoleDTO saveRole(Role role) {
        return roleMapper.roleToRoleDTO(roleRepository.save(role));
    }

    public RoleDTO getRoleDTOById(int id) {
        return roleRepository.findById(id)
                .map(roleMapper::roleToRoleDTO).orElse(null);
    }

    public Boolean deleteRole(int id) {
        if (!roleRepository.existsById(id)) {
            return false;
        }
        roleRepository.deleteById(id);
        return true;
    }

    public List<RoleDTO> getAllRolesByUserId(Long userId) {

        List<Role> lstRoles = roleRepository.findRolesByUsersId(userId);
        if (CollectionUtils.isNotEmpty(lstRoles)) {
            return roleMapper.rolesToRoleDTOs(lstRoles);
        } else return Collections.emptyList();

    }
}
