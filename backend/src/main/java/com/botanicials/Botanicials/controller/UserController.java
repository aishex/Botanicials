package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // POST - add a new user
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(userService.convertToDTO(savedUser), HttpStatus.CREATED);
    }

    // GET - find user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.convertToDTO(user), HttpStatus.OK);
    }

    // GET - get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = userService.getAllUsersDTO();
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    // PUT - update user's details by id
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(userService.convertToDTO(updatedUser), HttpStatus.OK);
    }

    // DELETE - delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
