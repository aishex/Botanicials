package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // save new user in database
    public User addUser(User user){
        return userRepository.save(user);
    }

    // find user by id
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    // get all users from database
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // delete user by id
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    // update user's details by id
    public User updateUser(Long id, User userDetails){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    // conversion user -> userDTO
    public UserDTO convertToDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setImageUrl(user.getImageUrl());
        return dto;
    }

    // for list
    public List<UserDTO> getAllUsersDTO(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
