package com.productmanager.productmanagerapi.repository;

import com.productmanager.productmanagerapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    List<Role> findRolesByUsersId(Long UserId);

}
