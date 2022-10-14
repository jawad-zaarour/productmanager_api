package com.productmanager.productmanagerapi.repository;

import com.productmanager.productmanagerapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface  UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

    List<User> findUsersByRolesId(int roleId);

}
