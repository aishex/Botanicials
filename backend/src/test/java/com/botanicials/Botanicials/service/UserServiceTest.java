package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setName("Anna");

        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.addUser(user);

        assertNotNull(saved);
        assertEquals("Anna", saved.getName());
    }

    @Test
    void testGetUserById_Found() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User result = userService.getUserById(1L);

        assertNull(result);
    }

    @Test
    void testFindByEmail_Found() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User result = userService.findByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }


    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setName("A");

        when(userRepository.findAll()).thenReturn(List.of(user1));

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("A", result.get(0).getName());
    }

    @Test
    void testDeleteUser_Success() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository).delete(user);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.deleteUser(2L));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testUpdateUser_Success() {
        User user = new User();
        user.setId(1L);
        user.setName("Old");

        User newData = new User();
        newData.setName("New");
        newData.setEmail("new@mail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updated = userService.updateUser(1L, newData);

        assertEquals("New", updated.getName());
        assertEquals("new@mail.com", updated.getEmail());
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.updateUser(1L, new User()));

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void testConvertToDTO() {
        User user = new User();
        user.setId(10L);
        user.setEmail("mail@x.com");
        user.setName("Zoe");
        user.setImageUrl("img.png");

        UserDTO dto = userService.convertToDTO(user);

        assertEquals(10L, dto.getId());
        assertEquals("Zoe", dto.getName());
        assertEquals("mail@x.com", dto.getEmail());
        assertEquals("img.png", dto.getImageUrl());
    }

    @Test
    void testGetAllUsersDTO() {
        User user = new User();
        user.setName("Jon");

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDTO> result = userService.getAllUsersDTO();

        assertEquals(1, result.size());
        assertEquals("Jon", result.get(0).getName());
    }
}
