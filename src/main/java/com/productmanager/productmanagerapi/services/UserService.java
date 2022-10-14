package com.productmanager.productmanagerapi.services;

import com.productmanager.productmanagerapi.dto.UserCreationDTO;
import com.productmanager.productmanagerapi.dto.UserDTO;
import com.productmanager.productmanagerapi.mapper.UserMapper;
import com.productmanager.productmanagerapi.models.Role;
import com.productmanager.productmanagerapi.models.User;
import com.productmanager.productmanagerapi.repository.RoleRepository;
import com.productmanager.productmanagerapi.repository.UserRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserDTO getUserDTO(long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO).orElse(null);
    }

    public User getUser(long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public Boolean deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public UserDTO saveUser(UserCreationDTO userCreationDTO)  {
        //try {
        User user = userMapper.userCreationDtoToUser(userCreationDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userMapper.userToUserDTO(userRepository.save(user));
        /*} catch (ServiceException serviceException) {
            throw new ServiceException("Error in converting btw DTO and Entity" + serviceException);
        }*/
    }

    private void loadFile(String fileName) throws FileNotFoundException {
        //try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        /*} catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/
    }

    public UserDTO updateUser(User user) {
        if (user != null) {
            User existingUser =
                    userRepository.findById(user.getId()).orElse(null);

            if (existingUser != null) {
                existingUser.setUserName(user.getUserName());
                existingUser.setActive(user.isActive());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                existingUser.setRoles(user.getRoles());
                return userMapper.userToUserDTO(userRepository.save(existingUser));
            } else {
                throw new ServiceException("Syntax Error In The Json Body");
            }
        }
        return null;
    }


    public UserDTO addRoleToUser(int roleId, long userId) {
        Optional<Role> role = roleRepository.findById(roleId);
        User user = this.getUser(userId);
        if(role.isEmpty()){
            //throw new NotFoundException("");
        }
        user.addRole(role.get());
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }


    public void removeRoleFromUser(int roleId, long userId) {
        User user = this.getUser(userId);
        user.removeRole(roleId);
        userRepository.save(user);
        // never return hardcoded strings, or create them as global variables
        //in this case we should make the method as void
    }

    public List<UserDTO> getUsers() {
        List<User> lstUsers = userRepository.findAll();
        if (CollectionUtils.isNotEmpty(lstUsers)) {
            return userMapper.usersToUserDTOs(lstUsers);
        } else return Collections.emptyList();
    }

    public List<UserDTO> getAllUsersByRoleId(int roleId) {
        List<User> lstUsers = userRepository.findUsersByRolesId(roleId);
        if (CollectionUtils.isNotEmpty(lstUsers)) {
            return userMapper.usersToUserDTOs(lstUsers);
        } else return Collections.emptyList();
    }




}
